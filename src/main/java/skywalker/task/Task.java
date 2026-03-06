package skywalker.task;

/**
 * Represents a generic mission or task in the Skywalker system.
 * Serves as the base class for specific task types: {@link Todo}, {@link Deadline}, and {@link Event}.
 */
public abstract class Task {

    /** The description of the task. */
    protected String description;

    /** The completion status of the task. */
    protected boolean done;

    /**
     * Constructs a new Task with the specified description.
     * The task is initialised as not completed by default.
     *
     * @param description The text describing the mission.
     */
    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    /**
     * Returns an icon representing the completion status.
     *
     * @return "X" if the task is done, a blank space " " otherwise.
     */
    public String getStatusIcon() {
        return (done ? "X" : " ");
    }

    /**
     * Returns the description of the task.
     *
     * @return The description string.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Updates the completion status of the task.
     *
     * @param isDone True to mark the task as completed, false to unmark it.
     */
    public void setDone(boolean isDone) {
        this.done = isDone;
    }

    /**
     * Returns whether the task has been completed.
     *
     * @return True if done, false otherwise.
     */
    public boolean isDone() {
        return this.done;
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return Formatted string: [Status] Description.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    /**
     * Returns the single-character symbol identifying the task type (e.g., "T", "D", "E").
     *
     * @return The task type symbol.
     */
    public abstract String getTaskSymbol();

    /**
     * Returns the formatted string used to persist this task to the save file.
     *
     * @return The file-format string for this task.
     */
    public abstract String fileFormat();
}