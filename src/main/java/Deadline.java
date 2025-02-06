public class Deadline extends Task {
    protected String do_by;

    public Deadline(String name, String do_by) {
        super(name);
        this.do_by = do_by;
    }

    public void setDoBy(String do_by) {
        this.do_by = do_by;
    }

    public String getDoBy() {
        return this.do_by;
    }

    @Override
    public String getPrintOutput() {
        return super.getPrintOutput() + " (by: " + this.do_by + ")";
    }

    @Override
    public String getTypeString() {
        return "[D]";
    }

}