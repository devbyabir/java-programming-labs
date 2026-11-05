"""Compile exercises independently, then check outputs and boundary behavior."""
from pathlib import Path
import os
import subprocess
import tempfile
import unittest

ROOT = Path(__file__).resolve().parents[1]
JAVA = os.environ.get("JAVA", "java")
JAVAC = os.environ.get("JAVAC", "javac")

class Labs(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        cls.temp = tempfile.TemporaryDirectory()
        cls.builds = {}
        for folder in sorted({p.parent for p in ROOT.rglob("*.java")}):
            key = folder.relative_to(ROOT).as_posix()
            build = Path(cls.temp.name) / key
            build.mkdir(parents=True)
            subprocess.run([JAVAC, "-encoding", "UTF-8", "-Xlint:all", "-Werror", "-d", str(build),
                            *map(str, folder.glob("*.java"))], check=True)
            cls.builds[key] = build

    @classmethod
    def tearDownClass(cls):
        cls.temp.cleanup()

    def run_java(self, key, main, data="", args=(), success=True):
        result = subprocess.run([JAVA, "-Duser.language=en", "-Duser.country=US", "-cp",
                                 str(self.builds[key]), main, *map(str, args)], input=data,
                                capture_output=True, text=True, timeout=20, cwd=ROOT)
        self.assertEqual(result.returncode == 0, success, result.stdout + result.stderr)
        return result.stdout

    def harness(self, key, source):
        build = self.builds[key]
        file = Path(self.temp.name) / "Check.java"
        file.write_text(source, encoding="utf-8")
        subprocess.run([JAVAC, "-cp", str(build), "-d", str(build), str(file)], check=True)
        self.run_java(key, "Check")

    def test_basic_outputs(self):
        cases = [
            ("assignment01-hello-java", "HelloJava", "Hello Java!"),
            ("lab01-arrays-and-input/01-array", "TwoDimensionalArray", "23 25 27"),
            ("lab02-numbers-and-classes/01-perfect-numbers", "PerfectNumber", "6\n28\n496"),
            ("lab02-numbers-and-classes/02-circle", "ShapeTester", "Radius: 10"),
            ("lab03-encapsulation-and-static/01-employee", "Employee", "Employee ID: E001"),
            ("lab03-encapsulation-and-static/02-family-name", "FamilyName", "Full Name:"),
            ("lab04-inheritance-and-polymorphism/01-inheritance", "Main", "Manager ID: M1"),
            ("lab04-inheritance-and-polymorphism/02-insurance", "Main", "Premium:"),
            ("assignment02-interfaces/01-shapes", "Main", "24.0"),
            ("assignment02-interfaces/02-cylinder", "Main", "785.0"),
        ]
        for key, main, expected in cases:
            with self.subTest(key=key):
                self.assertIn(expected, self.run_java(key, main))

    def test_guessing_input_and_eof(self):
        key = "lab01-arrays-and-input/02-guessing-game"
        guesses = "abc\n0\n101\n" + "\n".join(map(str, range(1, 101))) + "\n"
        output = self.run_java(key, "NumberGuessingGame", guesses)
        self.assertIn("Invalid input", output)
        self.assertIn("Congratulations", output)
        self.run_java(key, "NumberGuessingGame", "")
        self.run_java(key, "NumberGuessingGame", "abc\n")

    def test_perfect_boundaries(self):
        self.harness("lab02-numbers-and-classes/01-perfect-numbers", """
public class Check {
    public static void main(String[] args) {
        for (int n : new int[]{-1, 0, 1, 2, 12})
            if (PerfectNumber.isPerfect(n)) throw new AssertionError(n);
        for (int n : new int[]{6, 28, 496})
            if (!PerfectNumber.isPerfect(n)) throw new AssertionError(n);
    }
}
""")

    def test_circle_boundaries(self):
        self.harness("lab02-numbers-and-classes/02-circle", """
public class Check {
    public static void main(String[] args) {
        Circle c = new Circle(0);
        if (c.area() != 0 || c.circumference() != 0) throw new AssertionError();
        c.setRadius(2);
        if (Math.abs(c.area() - 4 * Math.PI) > 1e-9) throw new AssertionError();
        try { new Circle(-1); throw new AssertionError(); }
        catch (IllegalArgumentException expected) { }
        try { c.setRadius(-1); throw new AssertionError(); }
        catch (IllegalArgumentException expected) { }
    }
}
""")

    def test_insurance_dispatch(self):
        self.harness("lab04-inheritance-and-polymorphism/02-insurance", """
public class Check {
    public static void main(String[] args) {
        Insurable a = new Employee("E1", "Alex", 30, 5000);
        Insurable b = new Manager("M1", "Sam", 40, 8000, "Planning");
        if (a.calculatePremium() != 500 || b.calculatePremium() != 1400)
            throw new AssertionError();
    }
}
""")

    def test_writer_contents(self):
        directory = Path(self.temp.name) / "writer"
        self.run_java("assignment03-file-io/01-writer-comparison", "BufferedStreamComparison", args=[directory])
        first = (directory / "file-writer.txt").read_bytes()
        self.assertEqual(first, (directory / "buffered-writer.txt").read_bytes())
        values = list(map(int, first.splitlines()))
        self.assertEqual(len(values), 100000)
        self.assertTrue(all(0 <= n < 10000 for n in values))

    def test_scores(self):
        key = "assignment03-file-io/02-read-scores"
        self.assertIn("Average = 85.00", self.run_java(key, "ReadScores", "demo123\n"))
        output = self.run_java(key, "ReadScores", "wrong\n" * 3)
        self.assertIn("Too many", output)
        self.assertNotIn("Average =", output)
        self.run_java(key, "ReadScores", "")
        sample = Path(self.temp.name) / "score.txt"
        sample.write_text("Name: Test,English: 0,Math: 100\n")
        self.assertIn("Average = 50.00", self.run_java(key, "ReadScores", "demo123\n", [sample]))
        for line in ("broken", "Name: Test, English: 101, Math: 50", "Name: Test, English: abc, Math: 50"):
            sample.write_text(line)
            self.run_java(key, "ReadScores", "demo123\n", [sample], False)
        self.run_java(key, "ReadScores", "demo123\n", [sample.parent / "missing.txt"], False)

if __name__ == "__main__":
    unittest.main(verbosity=2)
