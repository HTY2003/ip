import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Frappe {
    public static ArrayList<Task> tasks = new ArrayList<>();

    private static int getTaskIndex(String[] words) throws FrappeException {
        if (words.length >= 3) {
            throw new FrappeException(FrappeException.TOO_MANY_WORDS);
        }

        if (words.length <= 1) {
            throw new FrappeException(FrappeException.NO_TASK_NUMBER);
        }

        if (!(words[1].matches("[0-9]+"))) {
            throw new FrappeException(FrappeException.NON_NUM_TASK_NUMBER);
        }

        int index = Integer.parseInt(words[1]) - 1;

        if (index < 0 || index >= tasks.size()) {
            throw new FrappeException(FrappeException.OUT_OF_RANGE_TASK_NUMBER);
        }

        return index;
    }

    private static void updateTaskDone(String[] words, Boolean isDone) throws FrappeException {

        int index = getTaskIndex(words);
        Task task = tasks.get(index);
        task.setDone(isDone);
        Printer.printTaskDone(isDone, index);
    }

    private static void addTodo(String[] words) throws FrappeException {
        String[] preInput = Arrays.copyOfRange(words, 1, words.length);

        if (preInput.length == 0) {
            throw new FrappeException(FrappeException.NO_NAME);
        }

        String name = String.join(" ", preInput).trim();
        Task task = new Todo(name);
        tasks.add(task);
        Printer.printTaskAdded(task);
    }

    private static void addDeadline(String[] words) throws FrappeException {
        String[] preInput = Arrays.copyOfRange(words, 1, words.length);

        if (preInput.length == 0) {
            throw new FrappeException(FrappeException.NO_NAME);
        }

        String[] input = String.join(" ", preInput).split("/by");

        if (input.length < 2) {
            throw new FrappeException(FrappeException.NO_BY);
        }

        if (input.length > 2) {
            throw new FrappeException(FrappeException.TOO_MANY_BY);
        }

        String name = input[0].trim();
        String doBy = input[1].trim();

        if (name.length() == 0) {
            throw new FrappeException(FrappeException.NO_NAME);
        }

        Task task = new Deadline(name, doBy);
        tasks.add(task);
        Printer.printTaskAdded(task);
    }

    private static void addEvent(String[] words) throws FrappeException {
        String[] preInput = Arrays.copyOfRange(words, 1, words.length);

        if (preInput.length == 0) {
            throw new FrappeException(FrappeException.NO_NAME);
        }

        String[] input = String.join(" ", preInput).split("/from");

        if (input.length < 2) {
            throw new FrappeException(FrappeException.NO_FROM);
        }

        if (input.length > 2) {
            throw new FrappeException(FrappeException.TOO_MANY_FROM);
        }

        if (input[0].contains("/to")) {
            throw new FrappeException(FrappeException.WRONG_TO_PLACEMENT);
        }

        String[] input2 = String.join(" ", input[1]).split("/to");

        if (input2.length < 2) {
            throw new FrappeException(FrappeException.NO_TO);
        }

        if (input2.length > 2) {
            throw new FrappeException(FrappeException.TOO_MANY_TO);
        }

        String name = input[0].trim();
        String from = input2[0].trim();
        String to = input2[1].trim();

        if (name.length() == 0) {
            throw new FrappeException(FrappeException.NO_NAME);
        }

        if (from.length() == 0) {
            throw new FrappeException(FrappeException.NO_FROM);
        }

        Task task = new Event(name, from, to);
        tasks.add(task);
        Printer.printTaskAdded(task);
    }

    private static void removeTask(String[] words) throws FrappeException {
        int index = getTaskIndex(words);
        Task task = tasks.get(index);
        tasks.remove(task);
        Printer.printTaskRemoved(task);
    }

    private static void processInput(String[] words) throws FrappeException {
        switch (words[0]) {
        case "list":
            Printer.printTasks();
            break;
        case "mark":
            updateTaskDone(words, true);
            break;
        case "unmark":
            updateTaskDone(words, false);
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
        case "delete":
            removeTask(words);
            break;
        default:
            throw new FrappeException(FrappeException.UNKNOWN_COMMAND);
        }
    }

    public static void main(String[] args) {
        Printer.printUnderscoreLine();
        Printer.printWelcome();
        Printer.printUnderscoreLine();

        while (true) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine().trim();

            Printer.printUnderscoreLine();

            if (input.equals("bye")) {
                Printer.printBye();
                break;
            }

            String[] words = input.split(" ");

            try {
                processInput(words);
            } catch (FrappeException e) {
                Printer.printException(e);
            }

            Printer.printUnderscoreLine();
        }
    }
}