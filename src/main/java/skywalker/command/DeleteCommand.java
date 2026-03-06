package skywalker.command;

import skywalker.exception.SkywalkerException;
import skywalker.filesystem.FileSystem;
import skywalker.taskmanager.TaskList;
import skywalker.ui.SkywalkerUi;

/**
 * Represents the command to delete a task from the task list by its index.
 */
public class DeleteCommand extends Command {

    /** The raw string representing the task number to delete. */
    private final String taskNumberStr;

    /**
     * Constructs a DeleteCommand with the raw task number string.
     *
     * @param part The string representation of the 1-based task index to delete.
     */
    public DeleteCommand(String part) {
        this.taskNumberStr = part;
    }

    /**
     * Executes the command by parsing the task number, removing the task from the
     * task list, and deleting it from the file system.
     *
     * @param fileSystem The file system instance used to persist the deletion.
     * @throws SkywalkerException If the input is not a valid integer, or the task number
     *                            is out of range.
     * @throws Exception If an error occurs while updating the file system.
     */
    @Override
    public void execute(FileSystem fileSystem) throws Exception {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(taskNumberStr.trim());
        } catch (NumberFormatException e) {
            throw new SkywalkerException(SkywalkerUi.ERROR_NOT_INT);
        }

        if (taskNumber <= 0 || taskNumber > TaskList.getCount()) {
            throw new SkywalkerException(SkywalkerUi.getOutOfBoundsMessage(TaskList.getCount()));
        }

        String taskStr = TaskList.getTask(taskNumber - 1).toString();
        fileSystem.deleteTask(taskNumber - 1);
        TaskList.delete(taskNumber - 1);

        String message = SkywalkerUi.MESSAGE_DELETE_SUCCESS + "\t    " + taskStr
                + "\n" + SkywalkerUi.getTaskCountMessage(TaskList.getCount());
        SkywalkerUi.printWithLines(message);
    }
}