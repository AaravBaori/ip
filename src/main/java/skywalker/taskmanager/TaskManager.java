package skywalker.taskmanager;
import skywalker.exception.SkywalkerException;
import skywalker.task.*;

import java.io.File;
import java.util.ArrayList;
import skywalker.FileSystem;

/**
 * Manages an internal collection of tasks.
 * Handles the storage, retrieval, and status updates of tasks within a fixed-size array.
 */
public class TaskManager{

    /** Array to store the task objects. */
    private final ArrayList<Task> allTasks = new ArrayList<>();

    public void pastEntries() throws SkywalkerException {
        FileSystem f = new FileSystem("./SkywalkerData.txt");
        f.parseFile(allTasks);
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task The Task object to be added to the archives.
     */
    public void add(Task task){
        allTasks.add(task);
    }

    /**
     * Retrieves the entire array of tasks.
     *
     * @return An array of Task objects, including null entries up to MAX.
     */
    public ArrayList<Task> getAllTasks(){
        return allTasks;
    }

    /**
     * Retrieves a single task from the archives by its index.
     *
     * @param index The zero-based index of the mission.
     * @return The Task object at the specified index.
     */
    public Task getTask(int index){
        return allTasks.get(index);
    }

    /**
     * Gets the current number of tasks stored.
     *
     * @return The integer count of tasks currently in the list.
     */
    public int getCount(){
        return allTasks.toArray().length;
    }

    /**
     * Checks if there are currently no missions in the archives.
     *
     * @return True if the count is zero, false otherwise.
     */
    public boolean isEmpty(){
        return this.getCount() == 0;
    }

    public void delete(Integer index){ allTasks.remove(allTasks.get(index)); }
}
