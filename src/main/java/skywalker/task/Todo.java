package skywalker.task;

/**
 * Represents a simple task without any date or time constraints.
 * A Todo object focuses solely on the description of the mission.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the specified description.
     *
     * @param description The text describing the mission.
     */
    public Todo(String description){
        super(description);
    }

    /**
     * Returns a string representation of the Todo task, including the
     * [T] identifier, completion status, and description.
     *
     * @return Formatted string: [T][Status] Description.
     */
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

    public String getTaskSymbol(){
        return "T";
    }

    public String fileFormat(){
        int status = this.isDone() ? 1 : 0;
        return this.getTaskSymbol() + " | " + status + " | " + this.description + "\n";
    }
}