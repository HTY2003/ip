import java.util.Arrays;

public class Parser {
    protected String[] words;
    protected TaskList tasks;

    public Parser(String input, TaskList tasks) {
        this.words = input.split(" ");
        this.tasks = tasks;
    }

    public String getCommand() {
        return this.words[0];
    }

    public int getTaskIndex() throws FrappeException {
        if (this.words.length >= 3) {
            throw new FrappeException(FrappeException.TOO_MANY_WORDS);
        }

        if (this.words.length <= 1) {
            throw new FrappeException(FrappeException.NO_TASK_NUMBER);
        }

        if (!(this.words[1].matches("[0-9]+"))) {
            throw new FrappeException(FrappeException.NON_NUM_TASK_NUMBER);
        }

        int index = Integer.parseInt(this.words[1]) - 1;

        if (index < 0 || index >= tasks.getSize()) {
            throw new FrappeException(FrappeException.OUT_OF_RANGE_TASK_NUMBER);
        }

        return index;
    }

    public String getSearchTerm() throws FrappeException {
        String[] preInput = Arrays.copyOfRange(this.words, 1, this.words.length);
        if (preInput.length == 0) {
            throw new FrappeException(FrappeException.NO_SEARCH_TERM);
        }

        return String.join(" ", preInput).trim();
    }

    public String[] getTaskInfo() throws FrappeException {
        String[] preInput = Arrays.copyOfRange(this.words, 1, this.words.length);
        if (preInput.length == 0) {
            throw new FrappeException(FrappeException.NO_NAME);
        }

        String name = String.join(" ", preInput).trim();

        return new String[]{name};
    }

    public String[] getDeadlineInfo() throws FrappeException {
        String[] preInput = Arrays.copyOfRange(words, 1, words.length);
        if (preInput.length == 0) {
            throw new FrappeException(FrappeException.NO_NAME);
        }

        String[] input = String.join(" ", preInput).split("/by");
        if (input.length < 2) {
            throw new FrappeException(FrappeException.NO_BY);
        }
        if (input.length > 2) {
            throw new FrappeException(FrappeException.TOO_MANY_BY);
        }

        String name = input[0].trim();
        String doBy = input[1].trim();

        if (name.isEmpty()) {
            throw new FrappeException(FrappeException.NO_NAME);
        }

        return new String[]{name, doBy};
    }

    public String[] getEventInfo() throws FrappeException {
        String[] preInput = Arrays.copyOfRange(words, 1, words.length);
        if (preInput.length == 0) {
            throw new FrappeException(FrappeException.NO_NAME);
        }

        String[] input = String.join(" ", preInput).split("/from");
        if (input.length < 2) {
            throw new FrappeException(FrappeException.NO_FROM);
        }
        if (input.length > 2) {
            throw new FrappeException(FrappeException.TOO_MANY_FROM);
        }
        if (input[0].contains("/to")) {
            throw new FrappeException(FrappeException.WRONG_TO_PLACEMENT);
        }

        String[] input2 = String.join(" ", input[1]).split("/to");
        if (input2.length < 2) {
            throw new FrappeException(FrappeException.NO_TO);
        }
        if (input2.length > 2) {
            throw new FrappeException(FrappeException.TOO_MANY_TO);
        }

        String name = input[0].trim();
        String from = input2[0].trim();
        String to = input2[1].trim();

        if (name.isEmpty()) {
            throw new FrappeException(FrappeException.NO_NAME);
        }
        if (from.isEmpty()) {
            throw new FrappeException(FrappeException.NO_FROM);
        }

        return new String[]{name, from, to};
    }
}
