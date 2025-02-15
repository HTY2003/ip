import java.util.Arrays;
import java.util.Scanner;

public class Frappe {

    public static Task[] tasks = new Task[100];
    public static int currentIndex = 0;

    private static void updateTaskDone(String[] words, Boolean isDone) throws FrappeException {

        if (words.length >= 3) {
            Printer.printTooManyWords();
            throw new FrappeException();
        }

        if (words.length <= 1) {
            Printer.printNoTaskNumber();
            throw new FrappeException();
        }

        if (!(words[1].matches("[0-9]+"))) {
            Printer.printNonNumTaskNumber();
            throw new FrappeException();
        }

        int index = Integer.parseInt(words[1]) - 1;

        if (index < 0 || index >= currentIndex) {
            Printer.printInvalidTaskNumber();
            throw new FrappeException();
        }

        tasks[index].setDone(isDone);
        Printer.printTaskDone(isDone, index);
    }

    private static void addTodo(String[] words) throws FrappeException {
        String[] pre_input = Arrays.copyOfRange(words, 1, words.length);

        if (pre_input.length == 0) {
            Printer.printNoName();
            throw new FrappeException();
        }

        String name = String.join(" ", pre_input).trim();
        tasks[currentIndex] = new Todo(name);
        currentIndex++;
        Printer.printTaskAdded(tasks[currentIndex - 1]);
    }

    private static void addDeadline(String[] words) throws FrappeException {
        String[] pre_input = Arrays.copyOfRange(words, 1, words.length);

        if (pre_input.length == 0) {
            Printer.printNoName();
            throw new FrappeException();
        }

        String[] input = String.join(" ", pre_input).split("/by");

        if (input.length < 2) {
            Printer.printNoBy();
            throw new FrappeException();
        }

        if (input.length > 2) {
            Printer.printTooManyBy();
            throw new FrappeException();
        }

        String name = input[0].trim();
        String doBy = input[1].trim();

        if (name.length() == 0) {
            Printer.printNoName();
            throw new FrappeException();
        }

        tasks[currentIndex] = new Deadline(name, doBy);
        currentIndex++;
        Printer.printTaskAdded(tasks[currentIndex - 1]);
    }

    private static void addEvent(String[] words) throws FrappeException {
        String[] pre_input = Arrays.copyOfRange(words, 1, words.length);

        if (pre_input.length == 0) {
            Printer.printNoName();
            throw new FrappeException();
        }

        String[] input = String.join(" ", pre_input).split("/from");

        if (input.length < 2) {
            Printer.printNoFrom();
            throw new FrappeException();
        }

        if (input.length > 2) {
            Printer.printTooManyFrom();
            throw new FrappeException();
        }

        if (input[0].contains("/to")) {
            Printer.printWrongToPosition();
            throw new FrappeException();
        }

        String[] input2 = String.join(" ", input[1]).split("/to");

        if (input2.length < 2) {
            Printer.printNoTo();
            throw new FrappeException();
        }

        if (input2.length > 2) {
            Printer.printTooManyTo();
            throw new FrappeException();
        }

        String name = input[0].trim();
        String from = input2[0].trim();
        String to = input2[1].trim();

        if (name.length() == 0) {
            Printer.printNoName();
            throw new FrappeException();
        }

        if (from.length() == 0) {
            Printer.printNoFrom();
            throw new FrappeException();
        }

        tasks[currentIndex] = new Event(name, from, to);
        currentIndex++;
        Printer.printTaskAdded(tasks[currentIndex - 1]);
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
        default:
            Printer.printUnknownCommand();
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

            }

            Printer.printUnderscoreLine();
        }
    }
}