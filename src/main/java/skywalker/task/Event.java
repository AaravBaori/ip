package skywalker.task;

/**
 * Represents a task that occurs within a specific time frame.
 * An Event includes a description, a start time (/from), and an end time (/to).
 */
public class Event extends Task {

    /** The start time or date of the event. */
    private String from;

    /** The end time or date of the event. */
    private String to;

    /**
     * Constructs a new Event task with a description and a defined duration.
     *
     * @param from The start time/date of the mission.
     * @param to The end time/date of the mission.
     * @param description The text describing the event.
     */
    public Event(String from, String to, String description) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task, including the
     * [E] identifier, completion status, description, and duration.
     *
     * @return Formatted string: [E][Status] Description (from: start to: end).
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    /**
     * Updates the start time of this event.
     *
     * @param from The new start time/date string.
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * Updates the end time of this event.
     *
     * @param to The new end time/date string.
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * Returns the start time of this event.
     *
     * @return The start time string.
     */
    public String getFrom() {
        return this.from;
    }

    /**
     * Returns the end time of this event.
     *
     * @return The end time string.
     */
    public String getTo() {
        return this.to;
    }

    /**
     * Returns the task type symbol for an Event.
     *
     * @return "E".
     */
    @Override
    public String getTaskSymbol() {
        return "E";
    }

    /**
     * Returns the formatted string used to persist this Event to the save file.
     *
     * @return File-format string: {@code E | <status> | <description> | From: <from> | To: <to>}.
     */
    @Override
    public String fileFormat() {
        int status = this.isDone() ? 1 : 0;
        return this.getTaskSymbol() + " | " + status + " | " + this.description
                + " | From: " + this.from + " | To: " + this.to + "\n";
    }
}