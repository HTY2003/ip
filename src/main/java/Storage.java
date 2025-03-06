import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static final String defaultSaveFilePath = "./data/frappe.txt";
    protected String saveFilePath;

    public Storage() {
        this.saveFilePath = defaultSaveFilePath;
    }

    public Storage(String saveFilePath) {
        this.saveFilePath = saveFilePath;
    }

    public void save(TaskList tasks) throws FrappeFileException {
        try {
            File saveFile = new File(saveFilePath);
            saveFile.getParentFile().mkdirs();
            saveFile.createNewFile();

            FileWriter writer = new FileWriter(saveFilePath);

            for (int i = 0; i < tasks.getSize(); i++) {
                Task task = tasks.getTask(i);
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

    public ArrayList<Task> load() throws FrappeFileException {
        try {
            File saveFile = new File(saveFilePath);
            Scanner in = new Scanner(saveFile);
            ArrayList<Task> loadedTasks = new ArrayList<Task>();
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
                loadedTasks.add(task);
            }
            return loadedTasks;
        } catch (FileNotFoundException e) {
            throw new FrappeFileException(FrappeFileException.NO_SAVE_WARNING);
        }
    }
}
