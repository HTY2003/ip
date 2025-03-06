import java.util.ArrayList;

/**
 * Represents a list of all tasks being tracked by the user in the form of Task objects
 * Used to add, remove, access and search for tasks.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     * Constructs empty TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructs pre-filled TaskList object from given ArrayList of Task objects.
     *
     * @param tasks ArrayList of Task objects to be stored
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<Task>(tasks);
    }

    /**
     * Returns number of Task objects being stored.
     *
     * @return Number of Task objects being stored
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns Task object with the given index.
     *
     * @param index Index of Task object to be retrieved
     * @return Task object at given index
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Removes Task object at given index.
     *
     * @param index Index of Task object to be removed
     */
    public void removeTask(int index) {
        Task task = this.tasks.get(index);
        this.tasks.remove(task);
        Printer.printTaskRemoved(task, this.tasks.size());
    }

    /**
     * Sets completion status of Task object at given index.
     *
     * @param index  Index of Task object to be modified
     * @param isDone Whether task is completed
     */
    public void setTaskDone(int index, boolean isDone) {
        Task task = this.tasks.get(index);
        task.setDone(isDone);
        Printer.printTaskDone(task, isDone);
    }

    /**
     * Adds given Todo task to TaskList.
     *
     * @param info Array containing name of task
     */
    public void addTodo(String[] info) {
        String name = info[0];
        Task task = new Todo(name);
        tasks.add(task);
        Printer.printTaskAdded(task, this.tasks.size());
    }

    /**
     * Adds given Deadline task to TaskList.
     *
     * @param info Array containing name and doBy fields of deadline
     */
    public void addDeadline(String[] info) {
        String name = info[0];
        String doBy = info[1];
        Task task = new Deadline(name, doBy);
        tasks.add(task);
        Printer.printTaskAdded(task, this.tasks.size());
    }

    /**
     * Adds given Event task to TaskList.
     *
     * @param info Array containing name, from and to fields of event
     */
    public void addEvent(String[] info) {
        String name = info[0];
        String from = info[1];
        String to = info[2];
        Task task = new Event(name, from, to);
        tasks.add(task);
        Printer.printTaskAdded(task, this.tasks.size());
    }

    /**
     * Returns new TaskList object containing a subset of the current TaskList's
     * tasks which contain the given search term in their print outputs.
     *
     * @param searchTerm Search term
     * @return TaskList object containing all tasks which contain search term in their print output
     */
    public TaskList getMatchingTasks(String searchTerm) {
        TaskList results = new TaskList();
        for (Task task : this.tasks) {
            if (task.getPrintOutput().contains(searchTerm)) {
                results.tasks.add(task);
            }
        }

        return results;
    }
}
