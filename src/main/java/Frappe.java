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
    private Parser parser;

    /**
     * Constructs Frappe object with given file path, and attempts to load previous save data from it.
     * Given file path will also be where any new data is saved.
     *
     * @param filePath path of save file location
     */
    public Frappe(String filePath) {
        storage = new Storage(filePath);
        parser = new Parser();

        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (FrappeFileException e) {
            tasks = new TaskList();
            Printer.printException(e);
        }
    }

    private void processInput(Parser parser) throws FrappeException {
        int tasksSize = tasks.getSize();

        switch (parser.getCommandWord()) {
            case "todo":
                tasks.addTodo(parser.getTodoInfo());
                break;
            case "deadline":
                tasks.addDeadline(parser.getDeadlineInfo());
                break;
            case "event":
                tasks.addEvent(parser.getEventInfo());
                break;
            case "mark":
                tasks.setTaskDone(parser.getTaskIndex(tasksSize), true);
                break;
            case "unmark":
                tasks.setTaskDone(parser.getTaskIndex(tasksSize), false);
                break;
            case "delete":
                tasks.removeTask(parser.getTaskIndex(tasksSize));
                break;
            case "list":
                Printer.printAllTasks(tasks);
                break;
            case "find":
                String searchTerm = parser.getSearchTerm();
                TaskList results = tasks.getMatchingTasks(searchTerm);
                Printer.printMatchingTasks(results);
                break;
            default:
                throw new FrappeException(FrappeException.UNKNOWN_COMMAND);
        }

        storage.saveTasks(tasks);
    }

    private void run() {
        Printer.printUnderscoreLine();
        Printer.printWelcome();
        Printer.printUnderscoreLine();

        while (true) {
            parser.collectNewCommand();
            Printer.printUnderscoreLine();

            String command = parser.getCommandWord();
            if (command.equals("bye")) {
                Printer.printBye();
                break;
            }

            try {
                processInput(parser);
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
