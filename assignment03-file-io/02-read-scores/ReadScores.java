import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class ReadScores {
    // This public demo password illustrates attempts; it is not authentication.
    private static final String DEMO_PASSWORD = "demo123";

    static int score(String field, String label) {
        String[] pair = field.split(":", 2);
        if (pair.length != 2 || !pair[0].trim().equals(label))
            throw new IllegalArgumentException("Expected " + label);
        int value = Integer.parseInt(pair[1].trim());
        if (value < 0 || value > 100) throw new IllegalArgumentException("Score outside 0-100");
        return value;
    }

    public static void main(String[] args) throws IOException {
        boolean accepted = false;
        try (Scanner scanner = new Scanner(System.in)) {
            for (int attempt = 0; attempt < 3; attempt++) {
                System.out.print("Enter the demo password: ");
                if (!scanner.hasNextLine()) return;
                if (scanner.nextLine().equals(DEMO_PASSWORD)) { accepted = true; break; }
                System.out.println("Wrong password.");
            }
        }
        if (!accepted) { System.out.println("Too many incorrect attempts."); return; }
        Path file = Path.of(args.length == 0 ? "assignment03-file-io/02-read-scores/score.txt" : args[0]);
        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                // Final review (2026-11): ignore a UTF-8 byte order mark, e.g. from Notepad.
                if (lineNumber == 1 && line.startsWith("\uFEFF")) line = line.substring(1);
                if (line.isBlank()) continue;
                try {
                    String[] fields = line.split(",", -1);
                    if (fields.length != 3) throw new IllegalArgumentException("Expected three fields");
                    String[] name = fields[0].split(":", 2);
                    if (name.length != 2 || !name[0].trim().equals("Name") || name[1].isBlank())
                        throw new IllegalArgumentException("Missing name");
                    int english = score(fields[1], "English");
                    int math = score(fields[2], "Math");
                    System.out.printf("%s: English = %d, Math = %d, Average = %.2f%n",
                                      name[1].trim(), english, math, (english + math) / 2.0);
                } catch (IllegalArgumentException exception) {
                    throw new IOException("Invalid score record at line " + lineNumber, exception);
                }
            }
        }
    }
}
