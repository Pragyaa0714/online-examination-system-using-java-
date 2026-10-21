import java.util.Scanner;

public class a6 {
    private int accumulator;

    private void execute(String instruction, Scanner scanner) {
        switch (instruction.toUpperCase()) {
            case "IN":
                System.out.print("Enter an integer: ");
                while (!scanner.hasNextInt()) {
                    System.out.print("Invalid input. Enter an integer: ");
                    scanner.next();
                }
                accumulator = scanner.nextInt();
                System.out.println("Input instruction completed. Accumulator = " + accumulator);
                break;
            case "OUT":
                System.out.println("Output instruction: " + accumulator);
                break;
            default:
                System.out.println("Invalid instruction. Use IN, OUT, or EXIT.");
        }
    }

    public static void main(String[] args) {
        a6 ioUnit = new a6();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Input/Output Instruction Unit");
            System.out.println("IN: read an integer into the accumulator");
            System.out.println("OUT: display the accumulator");
            System.out.println("EXIT: stop the program");

            while (true) {
                System.out.print("\nEnter instruction: ");
                String instruction = scanner.next();

                if (instruction.equalsIgnoreCase("EXIT")) {
                    System.out.println("Program terminated.");
                    break;
                }

                ioUnit.execute(instruction, scanner);
            }
        }
    }
}