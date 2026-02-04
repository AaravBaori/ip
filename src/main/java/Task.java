public class Task {
    protected String description;
    protected boolean isDone;

    protected static Task[] allTasks = new Task[100];
    protected static int count = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public static void markAsDone(int taskNumber) {
        allTasks[taskNumber - 1].isDone = true;
    }

    public static void markAsNotDone(int taskNumber) {
        allTasks[taskNumber - 1].isDone = false;
    }

    public static void addTask(Task task) {
        allTasks[count] = task;
        count++;
    }

    public static Task[] getAllTasks() {
        return allTasks;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString(){
        return "["+this.getStatusIcon()+"] " + this.getDescription();
    }

    public static int getCount(){
        return Task.count;
    }
}