public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFrom() {
        return this.from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTo() {
        return this.to;
    }

    @Override
    public String getPrintOutput() {
        return super.getPrintOutput() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String getTypeString() {
        return "[E]";
    }
}
