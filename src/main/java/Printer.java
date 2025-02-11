public class Printer {
    Printer() {
    }

    private static final int NUM_OF_UNDERSCORES = 50;

    public static void printWelcome() {
        System.out.println("Hello! I'm Frappe.");
        System.out.println("What can I do for you?");
    }

    public static void printUnderscoreLine() {
        System.out.println(new String(new char[NUM_OF_UNDERSCORES]).replace("\0", "_"));
    }

    public static void printTaskAdded(Task task) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(task.getPrintOutput());
        System.out.println("Now you have " + String.valueOf(Frappe.currentIndex) + (Frappe.currentIndex == 1 ? " task " : " tasks ") + "in the list.");
    }

    public static void printInvalidCommand() {
        System.out.println("Invalid command format. Please try again.");
    }

    public static void printUnknownCommand() {
        System.out.println("Sorry, but I do not recognise this command. Please try again.");
    }

    public static void printTooManyWords() {
        System.out.println("Too many inputs provided. Please try command again with just 1 task number.");
    }

    public static void printNoTaskNumber() {
        System.out.println("No task number provided. Please try command again with a task number at the end.");
    }

    public static void printNonNumTaskNumber() {
        System.out.println("Task number provided is not numerical. Please try command again with a numerical task number.");
    }

    public static void printInvalidTaskNumber() {
        System.out.println("Task number provided is out of range. Please try command again with valid task number.");
    }

    public static void printNoName() {
        System.out.println("No name provided. Please try command again with a name following it.");
    }

    public static void printNoBy() {
        System.out.println("No /by input provided. Please try command again with /by input. e.g. deadline shower /by 10pm");
    }

    public static void printTooManyBy() {
        System.out.println("Too many /by inputs provided. Please try command again with only 1 /by input.");
    }

    public static void printNoFrom() {
        System.out.println("No /from input provided. Please try command again with /from input. e.g. event shower /from 1pm /to 2pm");
    }

    public static void printTooManyFrom() {
        System.out.println("Too many /from inputs provided. Please try command again with only 1 /from input.");
    }

    public static void printWrongToPosition() {
        System.out.println("Invalid placement of /to input. Please try command again with /to input placed after /from input.");
    }

    public static void printNoTo() {
        System.out.println("No /to input provided. Please try command again with /to input. e.g. event shower /from 1pm /to 2pm");
    }

    public static void printTooManyTo() {
        System.out.println("Too many /to inputs provided. Please try command again with only 1 /to input.");
    }

    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
        printUnderscoreLine();
    }

    public static void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Frappe.currentIndex; i++) {
            System.out.println(String.format("%d. ", i + 1) + Frappe.tasks[i].getPrintOutput());
        }
    }

    public static void printTaskDone(boolean isDone, int idx) {
        if (isDone) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(Frappe.tasks[idx].getPrintOutput());
    }
}