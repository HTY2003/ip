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

    private static void printTaskAndCount(Task task) {
        System.out.println(task.getPrintOutput());
        int size = Frappe.tasks.size();
        System.out.println("Now you have " +
                String.valueOf(size) +
                (size == 1 ? " task " : " tasks ") + "in the list.");
    }

    public static void printTaskAdded(Task task) {
        System.out.println("Got it. I've added this task: ");
        printTaskAndCount(task);
    }

    public static void printTaskRemoved(Task task) {
        System.out.println("Got it. I've removed this task: ");
        printTaskAndCount(task);
    }

    public static void printTasks() {
        if (Frappe.tasks.size() == 0) {
            System.out.println("You have no tasks in your list.");
            return;
        }

        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Frappe.tasks.size(); i++) {
            Task task = Frappe.tasks.get(i);
            System.out.println(String.format("%d. ", i + 1) +
                    task.getPrintOutput());
        }
    }

    public static void printTaskDone(boolean isDone, int index) {
        if (isDone) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }

        Task task = Frappe.tasks.get(index);
        System.out.println(task.getPrintOutput());
    }

    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
        printUnderscoreLine();
    }

    public static void printException(FrappeException e) {
        System.out.println(e.message);
    }
}