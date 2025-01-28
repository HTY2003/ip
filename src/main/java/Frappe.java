import java.util.Arrays;
import java.util.Scanner;

public class Frappe {

    public static Task[] tasks = new Task[100];
    public static int cur_idx = 0;

    public static void printUnderscoreLine() {
        System.out.println(new String(new char[50]).replace("\0", "_")
        );
    }

    public static void updateTaskComplete(String[] words, Boolean complete) {
        if (words.length < 3) {
            if (words.length > 1) {
                int idx = Integer.parseInt(words[1]) - 1;
                if (idx >= 0 && idx < cur_idx) {
                    tasks[idx].setComplete(complete);
                    if (complete)
                        System.out.println("Nice! I've marked this task as done:");
                    else
                        System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks[idx].getPrintOutput());
                } else
                    System.out.println("Given task number does not exist in list. Please try again.");
            } else
                System.out.println("No task number provided. Please try again.");
        } else
            System.out.println("Too many input parameters provided. Please try again.");
    }

    public static void main(String[] args) {
        printUnderscoreLine();
        System.out.println("Hello! I'm Frappe.");
        System.out.println("What can I do for you?");
        printUnderscoreLine();

        while (true) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine().trim();

            printUnderscoreLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                printUnderscoreLine();
                break;
            } else if (!input.equals("")) {
                String[] words = input.split(" ");
                switch (input) {
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < cur_idx; i++)
                        System.out.println(String.format("%d. ", i + 1) + tasks[i].getPrintOutput());
                    break;
                default:
                    switch (words[0]) {
                    case "mark":
                        updateTaskComplete(words, true);
                        break;
                    case "unmark":
                        updateTaskComplete(words, false);
                        break;
                    default:
                        tasks[cur_idx] = new Task(input);
                        cur_idx++;
                        System.out.println("Task added: " + input);
                    }
                }
            }
            printUnderscoreLine();
        }
    }
}