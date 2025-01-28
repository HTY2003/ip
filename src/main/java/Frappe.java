import java.util.Scanner;

public class Frappe {

    public static void printUnderscoreLine() {
        System.out.println(new String(new char[50]).replace("\0", "_")
        );
    }

    public static void main(String[] args) {
        String[] items = new String[100];
        int cur_idx = 0;

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
                switch (input) {
                case "list":
                    for (int i = 0; i < cur_idx; i++) {
                        System.out.print(String.format("%d. ", i + 1));
                        System.out.println(items[i]);
                    }
                    break;
                default:
                    items[cur_idx] = input;
                    cur_idx++;
                    System.out.println("added: " + input);
                }
            }
            printUnderscoreLine();
        }
    }
}