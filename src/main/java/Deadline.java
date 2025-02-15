public class Deadline extends Task {
    protected String doBy;

    public Deadline(String name, String doBy) {
        super(name);
        this.doBy = doBy;
    }

    public void setDoBy(String doBy) {
        this.doBy = doBy;
    }

    @Override
    public String getDoBy() {
        return this.doBy;
    }

    @Override
    public String getPrintOutput() {
        return super.getPrintOutput() + " (by: " + this.doBy + ")";
    }

    @Override
    public String getTypeString() {
        return "[D]";
    }

}