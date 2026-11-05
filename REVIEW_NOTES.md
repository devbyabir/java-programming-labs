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

## Verification

Temurin JDK 21.0.13 on Windows: 13 exercise folders compiled using
`javac -encoding UTF-8 -Xlint:all -Werror`. Seven regression-test groups passed,
including invalid/EOF inputs and file-content checks. GitHub Actions is configured
for JDK 17, 21 and 25 but has not yet run. Tests cover the stated exercise behavior;
they do not establish correctness for every possible input or floating-point value.
