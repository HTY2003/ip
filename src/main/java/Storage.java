import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Helper class which handles all save file-related tasks,
 * namely loading save files during startup and saving to the save file after each command.
 */
public class Storage {
    public static final String defaultSaveFilePath = "./data/frappe.txt";
    protected String saveFilePath;

    /**
     * Constructs Storage object with default save file path ("./data/frappe.txt").
     */
    public Storage() {
        this.saveFilePath = defaultSaveFilePath;
    }

    /**
     * Constructs Storage object with given save file path
     *
     * @param saveFilePath File path where tasks are stored and loaded
     */
    public Storage(String saveFilePath) {
        this.saveFilePath = saveFilePath;
    }

    /**
     * Saves all Task objects in given TaskList to file at saveFilePath.
     * Throws FrappeFileException if file cannot be created at saveFilePath.
     *
     * @param tasks TaskList object to be saved
     * @throws FrappeFileException
     */
    public void saveTasks(TaskList tasks) throws FrappeFileException {
        try {
            File saveFile = new File(saveFilePath);
            saveFile.getParentFile().mkdirs();
            saveFile.createNewFile();

            FileWriter writer = new FileWriter(saveFilePath);

            for (int i = 0; i < tasks.getSize(); i++) {
                Task task = tasks.getTask(i);
                this.saveTask(writer, task);
            }

            writer.close();
        } catch (IOException e) {
            throw new FrappeFileException(FrappeFileException.SAVE_FAILED_WARNING);
        }
    }

    private void saveTask(FileWriter writer, Task task) throws FrappeFileException {
        try {
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
        } catch (IOException e) {
            throw new FrappeFileException(FrappeFileException.SAVE_FAILED_WARNING);
        }
    }

    /**
     * Retrieves and returns Task objects stored in save file used to reconstruct TaskList objects.
     * Throws FrappeFileException if save file does not exist, or is corrupted.
     *
     * @return ArrayList of Task objects
     * @throws FrappeFileException
     */
    public ArrayList<Task> loadTasks() throws FrappeFileException {
        try {
            ArrayList<Task> loadedTasks = new ArrayList<Task>();

            File saveFile = new File(saveFilePath);
            Scanner in = new Scanner(saveFile);
            in.useDelimiter("\n\n");

            while (in.hasNext()) {
                String rawInput = in.next();
                String[] inputs = this.parseRawInput(rawInput);
                this.checkInputs(inputs);
                loadedTasks.add(this.createTask(inputs));
            }

            return loadedTasks;

        } catch (FileNotFoundException e) {
            throw new FrappeFileException(FrappeFileException.NO_SAVE_WARNING);
        }
    }

    private String[] parseRawInput(String rawInput) {
        String[] inputs = rawInput.split("\n");

        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = inputs[i].trim();
        }

        return inputs;
    }

    private void checkInputs(String[] inputs) throws FrappeFileException {
        if (inputs.length < 2) {
            throw new FrappeFileException(FrappeFileException.CORRUPTED_SAVE_WARNING);
        }

        if (!(inputs[1].equals("0") || inputs[1].equals("1"))) {
            throw new FrappeFileException(FrappeFileException.CORRUPTED_SAVE_WARNING);
        }

        if (!(inputs[0].equals("[T]") || inputs[0].equals("[D]") || inputs[0].equals("[E]"))) {
            throw new FrappeFileException(FrappeFileException.CORRUPTED_SAVE_WARNING);
        }

        if (inputs[0].equals("[T]") && inputs.length != 3) {
            throw new FrappeFileException(FrappeFileException.CORRUPTED_SAVE_WARNING);
        }

        if (inputs[0].equals("[D]") && inputs.length != 4) {
            throw new FrappeFileException(FrappeFileException.CORRUPTED_SAVE_WARNING);
        }

        if (inputs[0].equals("[E]") && inputs.length != 5) {
            throw new FrappeFileException(FrappeFileException.CORRUPTED_SAVE_WARNING);
        }
    }

    private Task createTask(String[] inputs) {
        Task task = new Task("");
        String name, doBy, from, to;

        switch (inputs.length) {
            case 3:
                name = inputs[2];
                task = new Todo(name);
                break;
            case 4:
                name = inputs[2];
                doBy = inputs[3];
                task = new Deadline(name, doBy);
                break;
            case 5:
                name = inputs[2];
                from = inputs[3];
                to = inputs[4];
                task = new Event(name, from, to);
                break;
            default:
                break;
        }

        boolean isDone = inputs[1].equals("1");
        task.setDone(isDone);
        return task;
    }
}
