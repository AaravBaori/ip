package skywalker.task;

/**
 * Represents a simple task without any date or time constraints.
 * A Todo focuses solely on the description of the mission.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the specified description.
     *
     * @param description The text describing the mission.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task, including the
     * [T] identifier, completion status, and description.
     *
     * @return Formatted string: [T][Status] Description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the task type symbol for a Todo.
     *
     * @return "T".
     */
    @Override
    public String getTaskSymbol() {
        return "T";
    }

    /**
     * Returns the formatted string used to persist this Todo to the save file.
     *
     * @return File-format string: {@code T | <status> | <description>}.
     */
    @Override
    public String fileFormat() {
        int status = this.isDone() ? 1 : 0;
        return this.getTaskSymbol() + " | " + status + " | " + this.description + "\n";
    }
}