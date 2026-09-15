import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    private static final String LINE = "==================================================";

    private static void printTitle(String title) {
        System.out.println("\n" + LINE);
        System.out.println("                 " + title);
        System.out.println(LINE);
    }

    private static void printAttempts(int used, int total) {
        StringBuilder bar = new StringBuilder("[");
        for (int index = 0; index < total; index++) {
            bar.append(index < used ? "#" : "-");
        }
        bar.append("]");
        System.out.println("Attempts: " + bar + " " + used + "/" + total);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int round = 1;
        int totalScore = 0;
        String playAgain = "yes";

        printTitle("NUMBER GUESSING GAME");
        System.out.println("                 Test your luck and skill!");

        while (playAgain.equalsIgnoreCase("yes")) {

            printTitle("ROUND " + round);
            System.out.println("Choose your challenge:");
            System.out.println("  [1] EASY    | Range: 1-50  | Attempts: 10");
            System.out.println("  [2] MEDIUM  | Range: 1-100 | Attempts: 7");
            System.out.println("  [3] HARD    | Range: 1-200 | Attempts: 5");

            System.out.print("Enter your choice (1-3): ");
            int choice = scanner.nextInt();

            int maxNumber;
            int maxAttempts;
            String difficulty;

            if (choice == 1) {
                maxNumber = 50;
                maxAttempts = 10;
                difficulty = "Easy";

            } else if (choice == 2) {
                maxNumber = 100;
                maxAttempts = 7;
                difficulty = "Medium";

            } else if (choice == 3) {
                maxNumber = 200;
                maxAttempts = 5;
                difficulty = "Hard";

            } else {
                System.out.println("Invalid choice. Medium difficulty selected by default.");
                maxNumber = 100;
                maxAttempts = 7;
                difficulty = "Medium";
            }

            // Generate random number
            int secretNumber = random.nextInt(maxNumber) + 1;

            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("\n+------------------------------------------------+");
            System.out.println("| Difficulty : " + difficulty);
            System.out.println("| Target     : Pick a number from 1 to " + maxNumber);
            System.out.println("| Attempts   : " + maxAttempts);
            System.out.println("+------------------------------------------------+");

            // Guessing loop
            while (attempts < maxAttempts && !guessedCorrectly) {

                System.out.print("\nEnter your guess: ");

                int guess = scanner.nextInt();
                attempts++;

                if (guess > secretNumber) {

                        System.out.println("Too high! Try a smaller number.");
                        printAttempts(attempts, maxAttempts);

                } else if (guess < secretNumber) {

                        System.out.println("Too low! Try a larger number.");
                        printAttempts(attempts, maxAttempts);

                } else {

                        System.out.println("\n*** CORRECT! ***");
                        System.out.println("You found the number in " + attempts + " attempts.");
                        printAttempts(attempts, maxAttempts);

                    guessedCorrectly = true;
                    totalScore++;
                }
            }

            // If player loses
            if (!guessedCorrectly) {

                System.out.println("\nOUT OF ATTEMPTS");
                System.out.println("The correct number was: " + secretNumber);
            }

            // Round summary
            printTitle("ROUND " + round + " SUMMARY");
            System.out.println("Result     : " + (guessedCorrectly ? "WIN" : "LOSS"));
            System.out.println("Difficulty : " + difficulty);
            System.out.println("Used       : " + attempts + " of " + maxAttempts + " attempts");
            System.out.println("Score      : " + totalScore + " round(s) won");

            // Play again
            System.out.print("\nDo you want to play again? (yes/no): ");
            playAgain = scanner.next();

            round++;
        }

        // Final result
        printTitle("GAME OVER");
        System.out.println("Rounds played : " + (round - 1));
        System.out.println("Rounds won    : " + totalScore);
        System.out.println("Final score   : " + totalScore + "/" + (round - 1));
        System.out.println("Thanks for playing!");
        System.out.println(LINE);

        scanner.close();
    }
}