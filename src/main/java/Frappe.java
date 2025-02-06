import java.util.Arrays;
import java.util.Scanner;

public class Frappe {

    public static Task[] tasks = new Task[100];
    public static int cur_idx = 0;

    public static void printWelcome() {
        System.out.println("Hello! I'm Frappe.");
        System.out.println("What can I do for you?");
    }

    public static void printUnderscoreLine() {
        System.out.println(new String(new char[50]).replace("\0", "_")
        );
    }

    public static void printTaskAdded(Task task) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(task.getPrintOutput());
        System.out.println("Now you have " + String.valueOf(cur_idx) + (cur_idx == 1 ? " task " : " tasks ") + "in the list.");
    }

    public static void printInvalidCommand() {
        System.out.println("Invalid command format. Please try again.");
    }

    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
        printUnderscoreLine();
    }

    public static void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < cur_idx; i++)
            System.out.println(String.format("%d. ", i + 1) + tasks[i].getPrintOutput());
    }

    public static void updateTaskComplete(String[] words, Boolean complete) {

        if (words.length >= 3) {
            printInvalidCommand();
            return;
        }

        if (words.length <= 1) {
            printInvalidCommand();
            return;
        }

        if (!(words[1].matches("[0-9]+"))) {
            printInvalidCommand();
            return;
        }

        int idx = Integer.parseInt(words[1]) - 1;

        if (idx < 0 || idx >= cur_idx) {
            printInvalidCommand();
            return;
        }

        tasks[idx].setComplete(complete);
        if (complete)
            System.out.println("Nice! I've marked this task as done:");
        else
            System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks[idx].getPrintOutput());
    }

    public static void addTodo(String[] words) {
        String[] pre_input = Arrays.copyOfRange(words, 1, words.length);
        String name = String.join(" ", pre_input).trim();

        if (name.length() == 0) {
            printInvalidCommand();
            return;
        }

        tasks[cur_idx] = new Todo(name);
        cur_idx++;
        printTaskAdded(tasks[cur_idx - 1]);
    }

    public static void addDeadline(String[] words) {
        String[] pre_input = Arrays.copyOfRange(words, 1, words.length);
        String[] input = String.join(" ", pre_input).split("/by ");

        if (input.length != 2) {
            printInvalidCommand();
            return;
        }

        String name = input[0].trim();
        String by = input[1].trim();

        if (name.length() == 0 || by.length() == 0) {
            printInvalidCommand();
            return;
        }

        tasks[cur_idx] = new Deadline(name, by);
        cur_idx++;
        printTaskAdded(tasks[cur_idx - 1]);
    }

    public static void addEvent(String[] words) {
        String[] pre_input = Arrays.copyOfRange(words, 1, words.length);
        String[] input = String.join(" ", pre_input).split("/from ");

        if (input.length != 2) {
            printInvalidCommand();
            return;
        }

        String[] input2 = input[1].split("/to");

        if (input2.length != 2) {
            printInvalidCommand();
            return;
        }

        String name = input[0].trim();
        String from = input2[0].trim();
        String to = input2[1].trim();

        if (name.length() == 0 || from.length() == 0 || to.length() == 0) {
            printInvalidCommand();
            return;
        }

        tasks[cur_idx] = new Event(name, from, to);
        cur_idx++;
        printTaskAdded(tasks[cur_idx - 1]);
    }

    public static void main(String[] args) {
        printUnderscoreLine();
        printWelcome();
        printUnderscoreLine();

        while (true) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine().trim();

            printUnderscoreLine();

            if (input.equals("bye")) {
                printBye();
                break;
            }

            String[] words = input.split(" ");

            switch (words[0]) {
            case "list":
                printTasks();
                break;
            case "mark":
                updateTaskComplete(words, true);
                break;
            case "unmark":
                updateTaskComplete(words, false);
                break;
            case "todo":
                addTodo(words);
                break;
            case "deadline":
                addDeadline(words);
                break;
            case "event":
                addEvent(words);
                break;
            default:
                printInvalidCommand();
            }

            printUnderscoreLine();
        }
    }
}