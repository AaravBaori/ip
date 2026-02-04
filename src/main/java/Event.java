public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String from, String to, String description){
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to;
    }

    public void setFrom(String from){
        this.from = from;
    }

    public void setTo(String to){
        this.to = to;
    }

    public String getFrom(){
        return this.from;
    }

    public String getTo(){
        return this.to;
    }
}
