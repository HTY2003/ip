import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Frappe {
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static final String saveFilePath = "./data/frappe.txt";

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

        if (name.isEmpty()) {
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

        if (name.isEmpty()) {
            throw new FrappeException(FrappeException.NO_NAME);
        }

        if (from.isEmpty()) {
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
            saveTasks();
            break;
        case "unmark":
            updateTaskDone(words, false);
            saveTasks();
            break;
        case "todo":
            addTodo(words);
            saveTasks();
            break;
        case "deadline":
            addDeadline(words);
            saveTasks();
            break;
        case "event":
            addEvent(words);
            saveTasks();
            break;
        case "delete":
            removeTask(words);
            saveTasks();
            break;
        default:
            throw new FrappeException(FrappeException.UNKNOWN_COMMAND);
        }
    }

    private static void saveTasks() throws FrappeFileException {

        try {
            File saveFile = new File(saveFilePath);
            saveFile.getParentFile().mkdirs();
            saveFile.createNewFile();

            FileWriter writer = new FileWriter(saveFilePath);

            for (Task task : tasks) {
                writer.write(task.getTypeString());
                writer.write("\n");
                writer.write((task.getDone() ? "1" : "0"));
                writer.write("\n");
                writer.write(task.getName());
                writer.write("\n");

                if (task instanceof Deadline) {
                    writer.write(task.getDoBy());
                    writer.write("\n");
                } else if (task instanceof Event) {
                    writer.write(task.getFrom());
                    writer.write("\n");
                    writer.write(task.getTo());
                    writer.write("\n");
                }

                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new FrappeFileException(FrappeFileException.SAVE_FAILED_WARNING);
        }
    }

    private static void loadTasks() throws FrappeFileException {
        try {
            File saveFile = new File(saveFilePath);
            Scanner in = new Scanner(saveFile);
            in.useDelimiter("\n\n");
            while (in.hasNext()) {
                String input = in.next();
                String[] inputs = input.split("\n");

                for (int i = 0; i < inputs.length; i++) {
                    inputs[i] = inputs[i].trim();
                }

                if (inputs.length < 2) {
                    throw new FrappeFileException(FrappeFileException.CORRUPTED_SAVE_WARNING);
                }

                Task task;
                String name = inputs[2];
                boolean marked;

                if (inputs[1].equals("0")) {
                    marked = false;
                } else if (inputs[1].equals("1")) {
                    marked = true;
                } else {
                    throw new FrappeFileException(FrappeFileException.CORRUPTED_SAVE_WARNING);
                }

                switch (inputs[0]) {
                case "[T]":
                    if (inputs.length != 3) {
                        throw new FrappeFileException(FrappeFileException.CORRUPTED_SAVE_WARNING);
                    }
                    task = new Todo(name);
                    break;

                case "[D]":
                    if (inputs.length != 4) {
                        throw new FrappeFileException(FrappeFileException.CORRUPTED_SAVE_WARNING);
                    }
                    String by = inputs[3];
                    task = new Deadline(name, by);
                    break;

                case "[E]":
                    if (inputs.length != 5) {
                        throw new FrappeFileException(FrappeFileException.CORRUPTED_SAVE_WARNING);
                    }
                    String from = inputs[3];
                    String to = inputs[4];
                    task = new Event(name, from, to);
                    break;

                default:
                    throw new FrappeFileException(FrappeFileException.CORRUPTED_SAVE_WARNING);
                }

                task.setDone(marked);
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new FrappeFileException(FrappeFileException.NO_SAVE_WARNING);
        }
    }

    public static void main(String[] args) {
        try {
            loadTasks();
        } catch (FrappeFileException e) {
            Printer.printException(e);
        }

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
