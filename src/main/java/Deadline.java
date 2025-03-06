/**
 * Represents tasks created using Frappe's deadline command, which have two attributes: name and doBy.
 * Subclass of Task
 *
 * @see Task
 */
public class Deadline extends Task {
    protected String doBy;

    /**
     * Constructs Deadline object with given name and doBy attributes
     *
     * @param name Name of task
     * @param doBy Time task must be completed by
     */
    public Deadline(String name, String doBy) {
        super(name);
        this.doBy = doBy;
    }

    /**
     * Sets doBy attribute to given input
     *
     * @param doBy Time task must be completed by
     */
    public void setDoBy(String doBy) {
        this.doBy = doBy;
    }

    /**
     * Returns doBy attribute of task
     *
     * @return Time task must be completed by
     */
    @Override
    public String getDoBy() {
        return this.doBy;
    }

    /**
     * Returns printable summary of task
     *
     * @return String containing all task attributes
     */
    @Override
    public String getPrintOutput() {
        return super.getPrintOutput() + " (by: " + this.doBy + ")";
    }

    /**
     * Returns type indicator of task ([D] for deadlines).
     *
     * @return type indicator of task
     */
    @Override
    public String getTypeString() {
        return "[D]";
    }

}