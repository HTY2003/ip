/**
 * Represents tasks created using Frappe's todo command, which have only one attribute: name.
 * Subclass of Task
 *
 * @see Task
 */

public class Todo extends Task {

    /**
     * Constructs Event object with given name attribute
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Returns type indicator of task ([T] for todos).
     *
     * @return type indicator of task
     */
    @Override
    public String getTypeString() {
        return "[T]";
    }
}