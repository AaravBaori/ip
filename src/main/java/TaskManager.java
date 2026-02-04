public class TaskManager {

    private final int MAX = 100;
    private Task[] allTasks = new Task[MAX];
    private int count = 0;

    public void add(Task task){
        allTasks[count++] = task;
    }

    public void mark(int index){
        allTasks[index].setDone(true);
    }

    public void unmark(int index){
        allTasks[index].setDone(false);
    }

    public Task[] getAllTasks(){
        return allTasks;
    }

    public Task getTask(int index){
        return allTasks[index];
    }

    public int getCount(){
        return this.count;
    }
}
