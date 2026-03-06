package skywalker.command;

import skywalker.exception.SkywalkerException;
import skywalker.filesystem.FileSystem;
import skywalker.task.Deadline;
import skywalker.task.Task;
import skywalker.taskmanager.TaskList;
import skywalker.ui.SkywalkerUi;

/**
 * Represents the command to add a new Deadline task to the task list.
 * A Deadline task includes a description and a due date specified with /by.
 */
public class AddDeadlineCommand extends Command {

    /** The raw argument string containing the description and /by date. */
    private final String part;

    /**
     * Constructs an AddDeadlineCommand with the raw argument string.
     *
     * @param part The argument string in the format: {@code <description> /by <date>}.
     */
    public AddDeadlineCommand(String part) {
        this.part = part;
    }

    /**
     * Executes the command by parsing the description and deadline, creating a new
     * Deadline task, adding it to the task list, and saving it to the file system.
     *
     * @param fileSystem The file system instance used to persist the new task.
     * @throws SkywalkerException If the /by delimiter is missing or the date field is empty.
     * @throws Exception If an error occurs while saving to the file system.
     */
    @Override
    public void execute(FileSystem fileSystem) throws Exception {
        String[] details = this.part.split(" /by ", 2);
        if (details.length < 2 || details[1].trim().isEmpty()) {
            throw new SkywalkerException(SkywalkerUi.ERROR_MISSING_BY);
        }
        Task deadline = new Deadline(details[0].trim(), details[1].trim());
        TaskList.add(deadline);
        fileSystem.addTask(deadline);
        notifyAdd(deadline);
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