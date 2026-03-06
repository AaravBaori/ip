package skywalker.taskmanager;

import skywalker.task.Task;

import java.util.ArrayList;

/**
 * Manages the in-memory list of tasks for the Skywalker chatbot.
 * All operations are static, acting on a single shared task list.
 * The list is initialised when a {@code TaskList} instance is constructed.
 */
public class TaskList {

    /** The shared list of tasks used across the application. */
    private static ArrayList<Task> tasks;

    /**
     * Constructs a TaskList and initialises the shared task list to an empty list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList and initialises the shared task list with the given tasks.
     *
     * @param tasks The initial list of tasks to use.
     */
    public TaskList(ArrayList<Task> tasks) {
        TaskList.tasks = tasks;
    }

    /**
     * Adds a task to the end of the task list.
     *
     * @param task The task to add.
     */
    public static void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the task at the specified index from the task list.
     *
     * @param index The 0-based index of the task to remove.
     */
    public static void delete(int index) {
        tasks.remove(index);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The 0-based index of the task to retrieve.
     * @return The task at the given index.
     */
    public static Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks currently in the list.
     *
     * @return The total task count.
     */
    public static int getCount() {
        return tasks.size();
    }

    /**
     * Returns the full list of tasks.
     *
     * @return The ArrayList of all tasks.
     */
    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns whether the task list is empty.
     *
     * @return True if there are no tasks, false otherwise.
     */
    public static boolean isEmpty() {
        return tasks.isEmpty();
    }
}