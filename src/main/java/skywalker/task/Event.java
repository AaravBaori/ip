package skywalker.task;

/**
 * Represents a task that occurs within a specific time frame.
 * An Event object includes a description, a start time (/from),
 * and an end time (/to).
 */
public class Event extends Task {
    /** The start time or date of the event. */
    protected String from;

    /** The end time or date of the event. */
    protected String to;

    /**
     * Constructs a new Event task with a description and a defined duration.
     *
     * @param from The start time/date of the mission.
     * @param to The end time/date of the mission.
     * @param description The text describing the event.
     */
    public Event(String from, String to, String description){
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task, including the
     * [E] identifier, completion status, description, and the duration.
     *
     * @return Formatted string: [E][Status] Description (from: start to: end).
     */
    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    /**
     * Updates the start time of this event.
     *
     * @param from The new start time/date string.
     */
    public void setFrom(String from){
        this.from = from;
    }

    /**
     * Updates the end time of this event.
     *
     * @param to The new end time/date string.
     */
    public void setTo(String to){
        this.to = to;
    }

    /**
     * Retrieves the start time of this event.
     *
     * @return The start time string.
     */
    public String getFrom(){
        return this.from;
    }

    /**
     * Retrieves the end time of this event.
     *
     * @return The end time string.
     */
    public String getTo(){
        return this.to;
    }
}
