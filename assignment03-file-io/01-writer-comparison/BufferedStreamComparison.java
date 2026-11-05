import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public class BufferedStreamComparison {
    public static void main(String[] args) throws IOException {
        Path directory = Path.of(args.length == 0 ? "build/writer-output" : args[0]);
        Files.createDirectories(directory);
        int[] numbers = new int[100000];
        Random random = new Random(2024);
        for (int i = 0; i < numbers.length; i++) numbers[i] = random.nextInt(10000);

        long start = System.nanoTime();
        try (FileWriter writer = new FileWriter(directory.resolve("file-writer.txt").toFile(),
                                                StandardCharsets.UTF_8)) {
            for (int number : numbers) writer.write(number + "\n");
        }
        System.out.printf("FileWriter: %.2f ms%n", (System.nanoTime() - start) / 1_000_000.0);

        start = System.nanoTime();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(
                directory.resolve("buffered-writer.txt").toFile(), StandardCharsets.UTF_8))) {
            for (int number : numbers) writer.write(number + "\n");
        }
        System.out.printf("BufferedWriter: %.2f ms%n", (System.nanoTime() - start) / 1_000_000.0);
    }
}
