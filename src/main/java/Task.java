public class Task {
    protected String description;
    protected boolean isDone;

    protected static Task[] allTasks = new Task[100];
    protected static int count = 1;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public static void markAsDone(int index){
        Task.allTasks[index-1].isDone = true;
    }

    public static void markAsNotDone(int index){
        Task.allTasks[index-1].isDone = false;
    }

    public static void addTask(Task task){
        Task.allTasks[Task.count - 1] = task;
        Task.count += 1;
    }

    public static Task[] getAllTasks(){
        return Task.allTasks;
    }

    public String getDescription(){
        return this.description;
    }
}

