import java.util.Random;
import java.util.Scanner;
public class NumberGuessingGame {
    public static void main(String[] args) {
        Random rand = new Random();
        int numberToGuess = rand.nextInt(100) + 1;

        System.out.println("Welcome to the number guessing game!");
        System.out.println("Guess a number between 1 and 100:");

        try (Scanner scanner = new Scanner(System.in)) {
            int guess;

            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    guess = scanner.nextInt();

                    if (guess < 1 || guess > 100) {
                        System.out.println("Please enter a number between 1 and 100.");
                        continue;
                    }
                    if (guess == numberToGuess) {
                        System.out.println("Congratulations, you guessed the number!");
                        break;
                    } else if (guess < numberToGuess) {
                        System.out.println("Your guess is too low. Try again:");
                    } else {
                        System.out.println("Your guess is too high. Try again:");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 100:");
                    scanner.next();
                }
            }
        }
    }
}
