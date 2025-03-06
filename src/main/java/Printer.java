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

    private static void printTaskAndCount(Task task, int count) {
        System.out.println(task.getPrintOutput());
        System.out.println("Now you have " +
                String.valueOf(count) +
                (count == 1 ? " task " : " tasks ") + "in the list.");
    }

    public static void printTaskAdded(Task task, int count) {
        System.out.println("Got it. I've added this task: ");
        printTaskAndCount(task, count);
    }

    public static void printTaskRemoved(Task task, int count) {
        System.out.println("Got it. I've removed this task: ");
        printTaskAndCount(task, count);
    }

    public static void printTasks(TaskList tasks, String lineBeforePrint) {
        if (tasks.getSize() == 0) {
            System.out.println("You have no tasks in your list.");
            return;
        }

        System.out.println(lineBeforePrint);
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            System.out.println(String.format("%d. ", i + 1) +
                    task.getPrintOutput());
        }
    }

    public static void printAllTasks(TaskList tasks) {
        printTasks(tasks, "Here are the tasks in your list:");
    }

    public static void printMatchingTasks(TaskList tasks) {
        printTasks(tasks, "Here are the matching tasks in your list:");
    }

    public static void printTaskDone(Task task, boolean isDone) {
        if (isDone) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
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