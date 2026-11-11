# Java Programming Labs

Java lab exercises and assignments from my third semester at Zhengzhou
University in 2024. The exercises cover arrays, input, classes, encapsulation,
inheritance, interfaces, polymorphism and file I/O.

These are reviewed versions of the code in my submitted reports. Corrections
made in November 2026 are listed in [REVIEW_NOTES.md](REVIEW_NOTES.md).
The final Office Supply Management project is separate from this repository.

## Exercises

| Coursework | Folder | Programs |
| --- | --- | --- |
| First assignment | [`assignment01-hello-java`](assignment01-hello-java/) | Hello Java |
| Lab 1 | [`lab01-arrays-and-input`](lab01-arrays-and-input/) | 2D array; number guessing game |
| Lab 2 | [`lab02-numbers-and-classes`](lab02-numbers-and-classes/) | Perfect numbers; Circle and ShapeTester |
| Lab 3 | [`lab03-encapsulation-and-static`](lab03-encapsulation-and-static/) | Employee; shared family name |
| Lab 4 | [`lab04-inheritance-and-polymorphism`](lab04-inheritance-and-polymorphism/) | Inheritance; insurance premium polymorphism |
| Second assignment | [`assignment02-interfaces`](assignment02-interfaces/) | Shape interface; cylinder with multiple interfaces |
| Third assignment | [`assignment03-file-io`](assignment03-file-io/) | FileWriter comparison; read student scores |

There are 13 independent exercises, all written in Java. Compile one exercise
folder at a time: several exercises intentionally reuse class names such as
`Main` and `Employee`.

## Compile and run

Requires JDK 17 or newer. No Maven, Gradle or external Java libraries are needed.
From the repository root, in PowerShell:

```powershell
New-Item -ItemType Directory -Force build/circle | Out-Null
javac -encoding UTF-8 -Xlint:all -Werror -d build/circle lab02-numbers-and-classes/02-circle/*.java
java -cp build/circle ShapeTester
```

On Linux/macOS, replace the `New-Item` line with `mkdir -p build/circle`; the
`javac` and `java` lines are the same. Use a separate output directory for each
exercise to avoid class-name conflicts.

The guessing game chooses a number from 1 to 100, handles invalid guesses and
exits when input ends. The array example contains fixed values, not random data.
The insurance example deliberately generates random employee/manager objects;
its output changes between runs. Employee exercises are small demonstrations,
not payroll or financial software. The inheritance exercise retains the separate
manager salary field required by its assignment.

## File I/O exercises

Run these commands from the repository root, because both programs use paths
relative to the working directory (on Linux/macOS, use
`mkdir -p build/writers build/scores` for the first line):

```powershell
New-Item -ItemType Directory -Force build/writers,build/scores | Out-Null
javac -d build/writers assignment03-file-io/01-writer-comparison/*.java
java -cp build/writers BufferedStreamComparison
javac -d build/scores assignment03-file-io/02-read-scores/*.java
java -cp build/scores ReadScores
```

The writer comparison creates two files under `build/writer-output/`. Both
contain the same 100,000 generated integers. An optional first argument changes
the output directory. Existing files with these names are replaced. Timings are
a simple classroom observation, not a benchmark or a guarantee that either
writer will always be faster.

The score reader uses the included fictional sample `score.txt`. Enter the
public demonstration password `demo123`; three wrong attempts end the program.
This illustrates control flow, not secure authentication. An optional first
argument supplies a different input file. Records use this format:

```text
Name: Alex, English: 80, Math: 90
```

Scores must be integers from 0 to 100; names must be nonempty and cannot contain
commas. Averages keep their fractional part, for example `80.50`, and use a
decimal point regardless of the system locale. The file is read as UTF-8, with
or without a byte order mark. Blank lines are ignored. A malformed record or
missing file reports an error and exits unsuccessfully. Scores are processed in
order, so earlier valid records may already have been printed when a later record
fails.

Circle, rectangle and cylinder dimensions must be non-negative; floating-point
dimensions must also be finite. The cylinder retains the assignment's `P = 3.14`.
Geometry results use ordinary floating-point arithmetic, with its range and precision limits.

## Tests

The exercises are Java only. Python 3 is used solely by `tests/test_labs.py`,
which compiles and runs the Java programs for the automated checks. With JDK
tools on PATH and Python 3 installed:

```powershell
python tests/test_labs.py
```

The suite compiles all exercises independently with `-Xlint:all -Werror`, then
checks sample outputs, EOF/invalid guesses, perfect-number boundaries, circle
validation, premium dispatch, writer file contents, fractional averages and
malformed score records. Tests build into a temporary directory and write the
writer-comparison output there too. `JAVA` and `JAVAC` environment variables
can select tool executables.

GitHub Actions runs the suite on Temurin JDK 17, 21 and 25. The
[baseline run for commit 5999b6d](https://github.com/devbyabir/java-programming-labs/actions/runs/42003459750)
passed on all three. Later changes and their own verification status are listed in
[REVIEW_NOTES.md](REVIEW_NOTES.md#verification).
