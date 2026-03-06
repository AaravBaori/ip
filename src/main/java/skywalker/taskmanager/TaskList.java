package skywalker.taskmanager;

import skywalker.task.Task;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList() { tasks = new ArrayList<>(); }
    public TaskList(ArrayList<Task> tasks) { TaskList.tasks = tasks; }

    public static void add(Task t) { tasks.add(t); }
    public static void delete(int index) { tasks.remove(index); }
    public static Task getTask(int index) { return tasks.get(index); }
    public static int getCount() { return tasks.size(); }
    public static ArrayList<Task> getTasks() { return tasks; }

    public static boolean isEmpty() { return tasks.isEmpty(); }
}
