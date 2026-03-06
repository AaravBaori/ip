package skywalker.task;

/**
 * Represents a task with a specific deadline.
 * A Deadline includes a description and a date/time by which the task must be completed.
 */
public class Deadline extends Task {

    /** The date or time by which this task must be finished. */
    private String by;

    /**
     * Constructs a new Deadline task with a description and a due date.
     *
     * @param description The text describing the mission.
     * @param by The date/time string representing the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task, including the
     * [D] identifier, completion status, description, and due date.
     *
     * @return Formatted string: [D][Status] Description (by: time).
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Updates the deadline time for this task.
     *
     * @param by The new date/time string.
     */
    public void setBy(String by) {
        this.by = by;
    }

    /**
     * Returns the deadline time of this task.
     *
     * @return The deadline string.
     */
    public String getBy() {
        return this.by;
    }

    /**
     * Returns the task type symbol for a Deadline.
     *
     * @return "D".
     */
    @Override
    public String getTaskSymbol() {
        return "D";
    }

    /**
     * Returns the formatted string used to persist this Deadline to the save file.
     *
     * @return File-format string: {@code D | <status> | <description> | By: <deadline>}.
     */
    @Override
    public String fileFormat() {
        int status = this.isDone() ? 1 : 0;
        return this.getTaskSymbol() + " | " + status + " | " + this.description + " | By: " + this.by + "\n";
    }
}