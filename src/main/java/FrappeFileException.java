public class FrappeFileException extends FrappeException {
    FrappeFileException() {
    }

    FrappeFileException(String message) {
        this.message = message;
    }

    public static final String NO_SAVE_WARNING = "Warning: No save file detected. " +
            "A new file will be created for you when a task is added.";

    public static final String CORRUPTED_SAVE_WARNING = "Warning: Save file is corrupted. " +
            "A new file will be created when a task is added";

    public static final String SAVE_FAILED_WARNING = "Warning: Operation to save to save file has failed.";

}