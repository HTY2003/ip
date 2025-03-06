/**
 * Superclass for all tasks created using Frappe and stored in TaskList.
 * All tasks can be marked as done/undone and be printed for the user.
 *
 * @see Todo
 * @see Deadline
 * @see Event
 */

public class Task {
    protected String name;
    protected boolean isDone;

    /**
     * Constructs Task object with given name attribute
     *
     * @param name Name of task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Returns printable summary of task
     *
     * @return String containing all task attributes
     */
    public String getPrintOutput() {
        return this.getTypeString() + (isDone ? "[X] " : "[ ] ") + this.name;
    }

    /**
     * Sets isDone attribute to given input
     *
     * @param isDone Whether task is completed
     */
    public void setDone(Boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns isDone attribute of task
     *
     * @return Whether task is completed
     */
    public Boolean getDone() {
        return this.isDone;
    }

    /**
     * Sets name attribute to given input
     *
     * @param name Name of task
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns name attribute of task
     *
     * @return Name of task
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns type indicator of task.
     *
     * @return Type indicator of task
     */
    public String getTypeString() {
        return "[ ]";
    }

    /**
     * Returns doBy attribute of task (only relevant for Deadline objects)
     *
     * @return Time task must be completed by
     */
    public String getDoBy() {
        return "";
    }

    /**
     * Returns from attribute of task (only relevant for Event objects)
     *
     * @return Start time of event
     */
    public String getFrom() {
        return "";
    }

    /**
     * Returns to attribute of task (only relevant for Event objects)
     *
     * @return End time of event
     */
    public String getTo() {
        return "";
    }
}