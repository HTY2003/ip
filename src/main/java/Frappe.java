import java.util.Scanner;

public class Frappe {
    private TaskList tasks;
    private Storage storage;

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
                Printer.printTasks(tasks);
                break;
            case "mark":
                tasks.setTaskDone(parsedInput.getTaskIndex(), true);
                break;
            case "unmark":
                tasks.setTaskDone(parsedInput.getTaskIndex(), false);
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
                tasks.removeTask(parsedInput.getTaskIndex());
                break;
            default:
                throw new FrappeException(FrappeException.UNKNOWN_COMMAND);
        }

        storage.save(tasks);
    }

    public void run() {
        Printer.printUnderscoreLine();
        Printer.printWelcome();
        Printer.printUnderscoreLine();

        while (true) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine().trim();
            Parser parsedInput = new Parser(input, tasks);

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

    public static void main(String[] args) {
        new Frappe(Storage.defaultSaveFilePath).run();
    }
}
