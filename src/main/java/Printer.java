/**
 * Helper class which handles all tasks related to printing chatbot responses.
 */
public class Printer {
    /**
     * Constructs Printer object.
     * Serves no function in this class.
     */
    Printer() {
    }

    private static final int NUM_OF_UNDERSCORES = 50;

    /**
     * Prints welcome message, typically played on startup
     */
    public static void printWelcome() {
        System.out.println("Hello! I'm Frappe.");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints line of underscores (printed before and after each chatbot response).
     */
    public static void printUnderscoreLine() {
        System.out.println(new String(new char[NUM_OF_UNDERSCORES]).replace("\0", "_"));
    }

    private static void printTaskAndCount(Task task, int count) {
        System.out.println(task.getPrintOutput());
        System.out.println("Now you have " +
                String.valueOf(count) +
                (count == 1 ? " task " : " tasks ") + "in the list.");
    }

    /**
     * Prints newly added task and total number of tasks (used for todo, deadline and event commands).
     *
     * @param task  Task that was added
     * @param count Current number of tasks
     */
    public static void printTaskAdded(Task task, int count) {
        System.out.println("Got it. I've added this task: ");
        printTaskAndCount(task, count);
    }

    /**
     * Prints newly removed task and total number of tasks (used for delete commands).
     *
     * @param task  Task that was deleted
     * @param count Current number of tasks
     */
    public static void printTaskRemoved(Task task, int count) {
        System.out.println("Got it. I've removed this task: ");
        printTaskAndCount(task, count);
    }

    private static void printTasks(TaskList tasks, String lineBeforeTasks, String noTasksOutput) {
        if (tasks.getSize() == 0) {
            System.out.println(noTasksOutput);
            return;
        }

        System.out.println(lineBeforeTasks);
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            System.out.println(String.format("%d. ", i + 1) +
                    task.getPrintOutput());
        }
    }

    /**
     * Prints all tasks in given list (used for list command).
     *
     * @param tasks Tasks to be printed
     */
    public static void printAllTasks(TaskList tasks) {
        printTasks(tasks, "Here are the tasks in your list:",
                "You have no tasks in your list.");
    }

    /**
     * Prints all tasks in given list (used for find command).
     *
     * @param tasks Tasks to be printed
     */
    public static void printMatchingTasks(TaskList tasks) {
        printTasks(tasks, "Here are the matching tasks in your list:",
                "You have no matching tasks in your list.");
    }

    /**
     * Prints task and its completion status (used for mark/unmark commands).
     *
     * @param task   Task to be printed
     * @param isDone Completion status of task
     */
    public static void printTaskDone(Task task, boolean isDone) {
        if (isDone) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(task.getPrintOutput());
    }

    /**
     * Prints farewell message (used for bye command).
     */
    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
        printUnderscoreLine();
    }

    /**
     * Prints message of given exception.
     *
     * @param e Exception whose message is to be printed
     */
    public static void printException(FrappeException e) {
        System.out.println(e.message);
    }
}