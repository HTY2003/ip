/**
 * Exception for save file-related errors when using Frappe.
 * This class also stores error messages commonly used with this exception.
 */
public class FrappeFileException extends FrappeException {
    /**
     * Constructs FrappeFileException object with empty message
     */
    FrappeFileException() {
    }

    /**
     * Constructs FrappeFileException object with given message
     *
     * @param message: Exception message to be used
     */
    FrappeFileException(String message) {
        this.message = message;
    }

    public static final String NO_SAVE_WARNING = "Warning: No save file detected. " +
            "A new file will be created for you when a task is added.";

    public static final String CORRUPTED_SAVE_WARNING = "Warning: Save file is corrupted. " +
            "A new file will be created when a task is added";

    public static final String SAVE_FAILED_WARNING = "Warning: Operation to save to save file has failed.";

}