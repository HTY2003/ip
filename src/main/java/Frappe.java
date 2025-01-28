import java.util.Scanner;

public class Frappe {

    public static void printUnderscoreLine() {
        System.out.println(new String(new char[50]).replace("\0", "_")
        );
    }

    public static void main(String[] args) {
        printUnderscoreLine();
        System.out.println("Hello! I'm Frappe.");
        System.out.println("What can I do for you?");
        printUnderscoreLine();

        while (true) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();

            printUnderscoreLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                printUnderscoreLine();
                break;
            } else {
                System.out.println(input);
            }
            printUnderscoreLine();
        }
    }
}