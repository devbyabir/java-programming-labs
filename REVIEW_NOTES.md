# Review notes

## Sources

The four experiment reports each contain two exercises. The second assignment
report contains two more. The third assignment's two file-I/O programs were
recovered from its PDF. Hello Java was transcribed from the first assignment's
code screenshot, with the class renamed from its student-specific filename.

The reports contain a March 2024 template date, while the third-semester course
material is dated later in 2024. This repository uses the course year and does
not present the template date as a verified lab execution date.

Report prose, teacher materials, screenshots, compiled classes, IDE files and
student identifiers are not included. Sample employee IDs and score records
are fictional. These reviewed files are not unchanged original submissions.

## Corrections

The first review (commit 5999b6d, November 2026) changed the 2024 submissions
as follows:

- Guessing game: check EOF before consuming a token and reject guesses outside 1–100.
- Perfect numbers: return false for values below 2; use a long divisor sum.
- Circle: reject negative radius in both the constructor and setter.
- Interface shapes: reject negative/non-finite dimensions; retain the original
  interface structure and cylinder constant of 3.14.
- Writer comparison: replace the drive-specific path, use separate output files,
  compare identical generated data, close writers before stopping the timer and
  propagate I/O failures. Millisecond timing is derived from `nanoTime`.
- Score reader: repair PDF extraction line breaks, check EOF, validate records,
  preserve fractional averages and report failures. Replace the original literal
  password with an explicitly public demo value. Add fictional sample input.
- Separate exercises with duplicate class names, remove personal identifiers and
  repair formatting lost in report extraction.

The basic OOP exercises retain their small course structure. No frameworks or
application architecture were added. Standard algorithm/class examples may have
course or reference origins; this repository does not claim exclusive invention
or grant a new licence over third-party teaching material.

## Final review (2026-11)

A second review after commit 5999b6d rechecked all 13 exercises, focusing on
invalid input and EOF in the guessing game, geometry validation, and the two
file-I/O assignments (malformed records, missing files, fractional averages,
the working directory, and identical writer output in a temporary directory).

- Score reader: a score file saved as "UTF-8 with BOM" (for example by Windows
  Notepad) was rejected with "Invalid score record at line 1", because the byte
  order mark became part of the `Name` label. The reader now skips a leading BOM.
- Cylinder: the surface-area and volume comments had been double-encoded during
  extraction (`2Ï€r(r + h)`, `Ï€rÂ²h`); they now read `2πr(r + h)` and `πr²h`.
  The code and the assignment's `P = 3.14` are unchanged.
- Tests: added a fractional-average case (75 and 86 give 80.50) and a BOM case
  for the score reader.
- This file was converted from Windows-1252 to UTF-8.

No other reproducible bugs were found. The fictional samples, the public demo
password and the classroom timing comparison were kept as they are.

## Verification

**Earlier local check.** Temurin JDK 21.0.13 on Windows: 13 exercise folders
compiled using `javac -encoding UTF-8 -Xlint:all -Werror`, and the seven
regression-test groups then in the suite passed, including invalid/EOF inputs
and file-content checks.

**Baseline CI, commit 5999b6d.** The
[GitHub Actions run](https://github.com/devbyabir/java-programming-labs/actions/runs/42003459750)
triggered by the push of 5999b6d on 5 November 2026 succeeded. The `test (17)`,
`test (21)` and `test (25)` jobs each compiled all exercises and passed the
suite on Temurin JDK 17, 21 and 25. This result covers the code as of 5999b6d only.

**Final-review changes.** No JDK was available on the review machine, so the
score-reader fix, the cylinder comment change and the new test cases have not
been compiled or run yet. GitHub Actions will verify them on JDK 17, 21 and 25
once they are pushed.

Tests cover the stated exercise behavior; they do not establish correctness for
every possible input or floating-point value.
