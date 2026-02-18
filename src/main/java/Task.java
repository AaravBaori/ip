/**
 * Represents a generic mission or task in the Skywalker system.
 * This class serves as a base for specific task types like Todo, Deadline, and Event.
 */
public class Task {
    /** The description of the task. */
    protected String description;

    /** The completion status of the task. */
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     * By default, the task is initialized as not completed.
     *
     * @param description The text describing the mission.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns an icon representing the completion status.
     *
     * @return "X" if the task is done, a blank space " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Gets the description of the task.
     *
     * @return The description string.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Updates the completion status of the task.
     *
     * @param done True to mark the task as completed, false to unmark it.
     */
    public void setDone(boolean done){
        this.isDone = done;
    }

    /**
     * Returns a string representation of the task, including its status icon
     * and description.
     *
     * @return Formatted string: [Status] Description.
     */
    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    /**
     * Checks if the task has been completed.
     *
     * @return True if done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }
}