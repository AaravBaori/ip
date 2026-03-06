package skywalker.command;

import skywalker.filesystem.FileSystem;
import skywalker.task.Task;
import skywalker.task.Todo;
import skywalker.taskmanager.TaskList;
import skywalker.ui.SkywalkerUi;

/**
 * Represents the command to add a new Todo task to the task list.
 * A Todo is a simple task with only a description and no date or time constraints.
 */
public class AddTodoCommand extends Command {

    /** The description of the todo task to be added. */
    private final String part;

    /**
     * Constructs an AddTodoCommand with the specified task description.
     *
     * @param part The description of the todo task.
     */
    public AddTodoCommand(String part) {
        this.part = part;
    }

    /**
     * Executes the command by creating a new Todo, adding it to the task list,
     * and saving it to the file system.
     *
     * @param fileSystem The file system instance used to persist the new task.
     * @throws Exception If an error occurs while saving to the file system.
     */
    @Override
    public void execute(FileSystem fileSystem) throws Exception {
        Task todo = new Todo(this.part.trim());
        TaskList.add(todo);
        fileSystem.addTask(todo);
        notifyAdd(todo);
    }

    /**
     * Prints a confirmation message showing the added task and updated task count.
     *
     * @param task The task that was successfully added.
     */
    private void notifyAdd(Task task) {
        String message = SkywalkerUi.MESSAGE_ADD_SUCCESS + "\t    " + task
                + "\n" + SkywalkerUi.getTaskCountMessage(TaskList.getCount());
        SkywalkerUi.printWithLines(message);
    }
}