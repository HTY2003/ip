import java.util.Arrays;
import java.util.Scanner;

/**
 * Helper class which handles all tasks related to parsing user input and ensuring correct formats.
 * Once the data is parsed (or rejected, in which case a FrappeException is thrown),
 * the output usually gets passed to TaskList.
 */
public class Parser {
    protected Scanner in;
    protected String[] words;

    /**
     * Constructs Parser object
     */
    public Parser() {
        this.in = new Scanner(System.in);
    }

    /**
     * Stalls program until user gives new line of input.
     */
    public void collectNewCommand() {
        String rawInput = in.nextLine();
        String trimmedInput = rawInput.trim();
        this.words = trimmedInput.split(" ");
    }

    /**
     * Returns command word of user input
     *
     * @return Command word of user input (first word of user input if any, "" otherwise)
     */
    public String getCommandWord() {
        return this.words[0];
    }

    /**
     * Returns task index from user input if it is specified in the correct format,
     * and is not out of the allowed range (current number of tasks).
     * <p>
     * If a valid task index cannot be extracted, FrappeException is thrown.
     *
     * @return Index of task to be retrieved
     * @throws FrappeException
     */
    public int getTaskIndex(int tasksSize) throws FrappeException {
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

        if (index < 0 || index >= tasksSize) {
            throw new FrappeException(FrappeException.OUT_OF_RANGE_TASK_NUMBER);
        }

        return index;
    }

    /**
     * Retrieves and returns search term from user input.
     * <p>
     * If search term is empty, FrappeException is thrown.
     *
     * @return Search term of user input
     * @throws FrappeException
     */
    public String getSearchTerm() throws FrappeException {
        String[] preInput = Arrays.copyOfRange(this.words, 1, this.words.length);
        if (preInput.length == 0) {
            throw new FrappeException(FrappeException.NO_SEARCH_TERM);
        }

        return String.join(" ", preInput).trim();
    }

    /**
     * Returns array containing task name retrieved from user input.
     * <p>
     * If user input format is invalid or any fields are empty, FrappeException is thrown.
     *
     * @return Array containing name of task to be added
     * @throws FrappeException
     */
    public String[] getTodoInfo() throws FrappeException {
        String[] preInput = Arrays.copyOfRange(this.words, 1, this.words.length);
        if (preInput.length == 0) {
            throw new FrappeException(FrappeException.NO_NAME);
        }

        String name = String.join(" ", preInput).trim();
        return new String[]{name};
    }

    /**
     * Returns array containing task name and doBy attributes retrieved from user input.
     * <p>
     * If user input format is invalid or any fields are empty, FrappeException is thrown.
     *
     * @return Array containing name and doBy of task to be added
     * @throws FrappeException
     */
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

    /**
     * Returns array containing task name, from and to attributes retrieved from user input.
     * <p>
     * If user input format is invalid or any fields are empty, FrappeException is thrown.
     *
     * @return Array containing name, from and to of task to be added
     * @throws FrappeException
     */
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
