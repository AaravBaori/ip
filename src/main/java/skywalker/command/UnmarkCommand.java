package skywalker.command;

import skywalker.exception.SkywalkerException;
import skywalker.filesystem.FileSystem;
import skywalker.task.Task;
import skywalker.taskmanager.TaskList;
import skywalker.ui.SkywalkerUi;

/**
 * Represents the command to unmark a completed task, resetting it to not done.
 */
public class UnmarkCommand extends Command {

    /** The full original user input string, used to extract the task number. */
    private final String userInput;

    /**
     * Constructs an UnmarkCommand with the full user input string.
     *
     * @param userInput The full command string entered by the user (e.g., "unmark 2").
     */
    public UnmarkCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the command by parsing the task number, validating its range,
     * unmarking the task, and updating the file system.
     *
     * @param fileSystem The file system instance used to persist the status change.
     * @throws SkywalkerException If the task number is invalid, out of range, or not yet done.
     * @throws Exception If an error occurs while updating the file system.
     */
    @Override
    public void execute(FileSystem fileSystem) throws Exception {
        try {
            int num = parseTaskNumber(userInput);
            Task task = TaskList.getTask(num - 1);
            if (!task.isDone()) {
                throw new SkywalkerException(SkywalkerUi.ERROR_NOT_DONE_YET);
            }
            task.setDone(false);
            fileSystem.updateFile(num - 1, false);
            SkywalkerUi.printWithLines(SkywalkerUi.MESSAGE_UNMARK_SUCCESS + "\t    " + task);
        } catch (NumberFormatException e) {
            throw new SkywalkerException(SkywalkerUi.ERROR_NOT_INT);
        }
    }

    /**
     * Parses and validates the task number from the user input string.
     *
     * @param input The full command string to parse.
     * @return The validated 1-based task number.
     * @throws SkywalkerException If the format is invalid or the number is out of range.
     */
    private int parseTaskNumber(String input) throws SkywalkerException {
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new SkywalkerException(SkywalkerUi.ERROR_INVALID_FORMAT);
        }
        int num = Integer.parseInt(parts[1]);
        if (num <= 0 || num > TaskList.getCount()) {
            throw new SkywalkerException(SkywalkerUi.getOutOfBoundsMessage(TaskList.getCount()));
        }
        return num;
    }
}