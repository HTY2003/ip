/**
 * Exception for user input-related errors when using Frappe.
 * This class also stores error messages commonly used with this exception.
 */
public class FrappeException extends Exception {
    public static String message = "";

    /**
     * Constructs FrappeException object with empty message
     */
    FrappeException() {

    }

    /**
     * Constructs FrappeException object with given message
     *
     * @param message: Exception message to be used
     */
    FrappeException(String message) {
        this.message = message;
    }

    public static final String UNKNOWN_COMMAND = "Sorry, but I do not recognise this command. " +
            "Please try again.";

    public static final String TOO_MANY_WORDS = "Too many inputs provided. " +
            "Please try command again with just 1 task number.";

    public static final String NO_TASK_NUMBER = "No task number provided. " +
            "Please try command again with a task number at the end.";

    public static final String NON_NUM_TASK_NUMBER = "Task number provided is not numerical. " +
            "Please try command again with a numerical task number.";

    public static final String OUT_OF_RANGE_TASK_NUMBER = "Task number provided is out of range. " +
            "Please try command again with valid task number.";

    public static final String NO_NAME = "No name provided. " +
            "Please try command again with a name following it.";

    public static final String NO_BY = "No /by input provided. " +
            "Please try command again with /by input. e.g. deadline shower /by 10pm";

    public static final String TOO_MANY_BY = "Too many /by inputs provided. " +
            "Please try command again with only 1 /by input.";

    public static final String NO_FROM = "No /from input provided. " +
            "Please try command again with /from input. e.g. event shower /from 1pm /to 2pm";

    public static final String TOO_MANY_FROM = "Too many /from inputs provided. " +
            "Please try command again with only 1 /from input.";

    public static final String WRONG_TO_PLACEMENT = "Invalid placement of /to input. " +
            "Please try command again with /to input placed after /from input.";

    public static final String NO_TO = "No /to input provided. " +
            "Please try command again with /to input. e.g. event shower /from 1pm /to 2pm";

    public static final String TOO_MANY_TO = "Too many /to inputs provided. " +
            "Please try command again with only 1 /to input.";

    public static final String NO_SEARCH_TERM = "No search term provided. " +
            "Please try command again with a search term following it.";
}