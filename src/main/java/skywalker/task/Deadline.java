package skywalker.task;

/**
 * Represents a task with a specific deadline.
 * A Deadline object includes a description and a date/time by which the task
 * must be completed.
 */
public class Deadline extends Task {

    /** The date or time by which this task must be finished. */
    protected String by;

    /**
     * Constructs a new Deadline task with a description and a deadline time.
     *
     * @param description The text describing the mission.
     * @param by The date/time string representing the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline task, including the
     * [D] identifier, completion status, description, and the deadline.
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
    public void setBy(String by){
        this.by = by;
    }

    /**
     * Retrieves the deadline time of this task.
     *
     * @return The deadline string.
     */
    public String getBy(){
        return this.by;
    }

    public String getTaskSymbol() {
        return "D";
    }

    public String fileFormat(){
        int status = this.isDone() ? 1 : 0;
        return this.getTaskSymbol() + " | " + status + " | " + this.description + " | By: " + this.by + "\n";
    }
}
