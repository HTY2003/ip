import java.util.Scanner;

/**
 * Frappe is a simple CLI chatbot that can be used to store simple tasks, such as
 * todos, deadlines and events. Additionally, you can search for tasks, and mark them as completed.
 *
 * @author Heng Teng Yi
 * @version 1.0
 * @since 2025-03-07
 */
public class Frappe {
    private TaskList tasks;
    private Storage storage;

    /**
     * Constructs Frappe object with given file path, and attempts to load previous save data from it.
     * Given file path will also be where any new data is saved.
     *
     * @param filePath path of save file location
     */
    public Frappe(String filePath) {
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (FrappeFileException e) {
            tasks = new TaskList();
            Printer.printException(e);
        }
    }

    private void processInput(Parser parsedInput) throws FrappeException {
        switch (parsedInput.getCommand()) {
            case "list":
                Printer.printAllTasks(tasks);
                break;
            case "find":
                TaskList results = tasks.getMatchingTasks(parsedInput.getSearchTerm());
                Printer.printMatchingTasks(results);
                break;
            case "mark":
                tasks.setTaskDone(parsedInput.getTaskIndex(tasks), true);
                break;
            case "unmark":
                tasks.setTaskDone(parsedInput.getTaskIndex(tasks), false);
                break;
            case "todo":
                tasks.addTodo(parsedInput.getTaskInfo());
                break;
            case "deadline":
                tasks.addDeadline(parsedInput.getDeadlineInfo());
                break;
            case "event":
                tasks.addEvent(parsedInput.getEventInfo());
                break;
            case "delete":
                tasks.removeTask(parsedInput.getTaskIndex(tasks));
                break;
            default:
                throw new FrappeException(FrappeException.UNKNOWN_COMMAND);
        }

        storage.save(tasks);
    }

    private void run() {
        Printer.printUnderscoreLine();
        Printer.printWelcome();
        Printer.printUnderscoreLine();

        while (true) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine().trim();
            Parser parsedInput = new Parser(input);

            Printer.printUnderscoreLine();

            if (parsedInput.getCommand().equals("bye")) {
                Printer.printBye();
                break;
            }

            try {
                processInput(parsedInput);
            } catch (FrappeException e) {
                Printer.printException(e);
            }

            Printer.printUnderscoreLine();
        }
    }

    /**
     * Runs CLI chatbot with default save file path ("./data/frappe.txt").
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        new Frappe(Storage.defaultSaveFilePath).run();
    }
}
