package skywalker.taskmanager;
import skywalker.task.*;

/**
 * Manages an internal collection of tasks.
 * Handles the storage, retrieval, and status updates of tasks within a fixed-size array.
 */
public class TaskManager {

    /** The maximum number of tasks that can be stored in the archives. */
    private final int MAX = 100;

    /** Array to store the task objects. */
    private Task[] allTasks = new Task[MAX];

    /** The current number of tasks stored in the array. */
    private int count = 0;

    /**
     * Adds a new task to the task list.
     *
     * @param task The Task object to be added to the archives.
     */
    public void add(Task task){
        allTasks[count++] = task;
    }

    /**
     * Marks a task as completed based on its array index.
     *
     * @param index The zero-based index of the task to be marked.
     */
    public void mark(int index){
        allTasks[index].setDone(true);
    }

    /**
     * Reverts a task's status to not completed based on its array index.
     *
     * @param index The zero-based index of the task to be unmarked.
     */
    public void unmark(int index){
        allTasks[index].setDone(false);
    }

    /**
     * Retrieves the entire array of tasks.
     *
     * @return An array of Task objects, including null entries up to MAX.
     */
    public Task[] getAllTasks(){
        return allTasks;
    }

    /**
     * Retrieves a single task from the archives by its index.
     *
     * @param index The zero-based index of the mission.
     * @return The Task object at the specified index.
     */
    public Task getTask(int index){
        return allTasks[index];
    }

    /**
     * Gets the current number of tasks stored.
     *
     * @return The integer count of tasks currently in the list.
     */
    public int getCount(){
        return this.count;
    }

    /**
     * Checks if there are currently no missions in the archives.
     *
     * @return True if the count is zero, false otherwise.
     */
    public boolean isEmpty(){
        return this.getCount() == 0;
    }
}
