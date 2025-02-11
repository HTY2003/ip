import java.util.Arrays;
import java.util.Scanner;

public class Frappe {

    public static Task[] tasks = new Task[100];
    public static int currentIndex = 0;

    public static void updateTaskDone(String[] words, Boolean isDone) {

        if (words.length >= 3) {
            Printer.printTooManyWords();
            return;
        }

        if (words.length <= 1) {
            Printer.printNoTaskNumber();
            return;
        }

        if (!(words[1].matches("[0-9]+"))) {
            Printer.printNonNumTaskNumber();
            return;
        }

        int index = Integer.parseInt(words[1]) - 1;

        if (index < 0 || index >= currentIndex) {
            Printer.printInvalidTaskNumber();
            return;
        }

        tasks[index].setDone(isDone);
        Printer.printTaskDone(isDone, index);
    }

    public static void addTodo(String[] words) {
        String[] pre_input = Arrays.copyOfRange(words, 1, words.length);

        if (pre_input.length == 0) {
            Printer.printNoName();
            return;
        }

        String name = String.join(" ", pre_input).trim();
        tasks[currentIndex] = new Todo(name);
        currentIndex++;
        Printer.printTaskAdded(tasks[currentIndex - 1]);
    }

    public static void addDeadline(String[] words) {
        String[] pre_input = Arrays.copyOfRange(words, 1, words.length);

        if (pre_input.length == 0) {
            Printer.printNoName();
            return;
        }

        String[] input = String.join(" ", pre_input).split("/by");

        if (input.length < 2) {
            Printer.printNoBy();
            return;
        }

        if (input.length > 2) {
            Printer.printTooManyBy();
            return;
        }

        String name = input[0].trim();
        String doBy = input[1].trim();

        if (name.length() == 0) {
            Printer.printNoName();
            return;
        }

        tasks[currentIndex] = new Deadline(name, doBy);
        currentIndex++;
        Printer.printTaskAdded(tasks[currentIndex - 1]);
    }

    public static void addEvent(String[] words) {
        String[] pre_input = Arrays.copyOfRange(words, 1, words.length);

        if (pre_input.length == 0) {
            Printer.printNoName();
            return;
        }

        String[] input = String.join(" ", pre_input).split("/from");

        if (input.length < 2) {
            Printer.printNoFrom();
            return;
        }

        if (input.length > 2) {
            Printer.printTooManyFrom();
            return;
        }

        if (input[0].contains("/to")) {
            Printer.printWrongToPosition();
            return;
        }

        String[] input2 = String.join(" ", input[1]).split("/to");

        if (input2.length < 2) {
            Printer.printNoTo();
            return;
        }

        if (input2.length > 2) {
            Printer.printTooManyTo();
            return;
        }

        String name = input[0].trim();
        String from = input2[0].trim();
        String to = input2[1].trim();

        if (name.length() == 0) {
            Printer.printNoName();
            return;
        }

        if (from.length() == 0) {
            Printer.printNoFrom();
            return;
        }

        tasks[currentIndex] = new Event(name, from, to);
        currentIndex++;
        Printer.printTaskAdded(tasks[currentIndex - 1]);
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

            Printer.printUnderscoreLine();
        }
    }
}