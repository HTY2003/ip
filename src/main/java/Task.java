public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getPrintOutput() {
        return this.getTypeString() + (isDone ? "[X] " : "[ ] ") + this.name;
    }

    public void setDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public Boolean getDone() {
        return this.isDone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getTypeString() {
        return "[ ]";
    }

    public String getDoBy() {
        return "";
    }

    public String getFrom() {
        return "";
    }

    public String getTo() {
        return "";
    }
}