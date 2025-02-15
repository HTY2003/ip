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
        System.out.println("Now you have " +
                String.valueOf(Frappe.currentIndex) +
                (Frappe.currentIndex == 1 ? " task " : " tasks ") + "in the list.");
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

    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
        printUnderscoreLine();
    }

    public static void printException(FrappeException e) {
        System.out.println(e.message);
    }
}