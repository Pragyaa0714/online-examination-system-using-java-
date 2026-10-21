import java.util.Scanner;

public class InputOutputUnit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter data: ");
        int data = sc.nextInt();

        System.out.println("Output data: " + data);
        sc.close();
    }
}
