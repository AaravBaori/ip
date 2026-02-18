public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    public void setDone(boolean done){
        this.isDone = done;
    }

    @Override
    public String toString(){
        return "["+this.getStatusIcon()+"] " + this.getDescription();
    }

    public boolean isDone() {
        return this.isDone;
    }
}