import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<Task>(tasks);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public void removeTask(int index) {
        Task task = this.tasks.get(index);
        this.tasks.remove(task);
        Printer.printTaskRemoved(task, this.tasks.size());
    }

    public void setTaskDone(int index, boolean isDone) {
        Task task = this.tasks.get(index);
        task.setDone(isDone);
        Printer.printTaskDone(task, isDone);
    }

    public void addTodo(String[] info) {
        String name = info[0];
        Task task = new Todo(name);
        tasks.add(task);
        Printer.printTaskAdded(task, this.tasks.size());
    }

    public void addDeadline(String[] info) {
        String name = info[0];
        String doBy = info[1];
        Task task = new Deadline(name, doBy);
        tasks.add(task);
        Printer.printTaskAdded(task, this.tasks.size());
    }

    public void addEvent(String[] info) {
        String name = info[0];
        String from = info[1];
        String to = info[2];
        Task task = new Event(name, from, to);
        tasks.add(task);
        Printer.printTaskAdded(task, this.tasks.size());
    }

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
