public class Task {
    protected String name;
    protected boolean complete;

    public Task(String name) {
        this.name = name;
        this.complete = false;
    }

    public String getPrintOutput() {
        return (complete ? "[X] " : "[ ] ") + this.name;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public Boolean getComplete() {
        return this.complete;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}