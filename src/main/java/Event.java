/**
 * Represents tasks created using Frappe's event command, which have three attributes: name, from and to.
 * Subclass of Task
 *
 * @see Task
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs Event object with given name, from and to attributes
     *
     * @param name Name of event
     * @param from Start time of event
     * @param to   End time of event
     */
    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    /**
     * Sets from attribute to given input
     *
     * @param from Start time of event
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * Returns from attribute of task
     *
     * @return Start time of event
     */
    @Override
    public String getFrom() {
        return this.from;
    }

    /**
     * Sets to attribute to given input
     *
     * @param to End time of event
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * Returns to attribute of task
     *
     * @return End time of event
     */
    @Override
    public String getTo() {
        return this.to;
    }

    /**
     * Returns printable summary of task
     *
     * @return String containing all task attributes
     */
    @Override
    public String getPrintOutput() {
        return super.getPrintOutput() + " (from: " + this.from + " to: " + this.to + ")";
    }

    /**
     * Returns type indicator of task ([E] for events).
     *
     * @return Type indicator of task
     */
    @Override
    public String getTypeString() {
        return "[E]";
    }
}
