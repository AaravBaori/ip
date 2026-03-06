package skywalker.command;

import skywalker.exception.SkywalkerException;
import skywalker.filesystem.FileSystem;
import skywalker.task.Event;
import skywalker.task.Task;
import skywalker.taskmanager.TaskList;
import skywalker.ui.SkywalkerUi;

/**
 * Represents the command to add a new Event task to the task list.
 * An Event task includes a description, a start time (/from), and an end time (/to).
 */
public class AddEventCommand extends Command {

    /** The raw argument string containing the description, /from, and /to fields. */
    private final String part;

    /**
     * Constructs an AddEventCommand with the raw argument string.
     *
     * @param part The argument string in the format:
     *             {@code <description> /from <start> /to <end>} (or /to before /from).
     */
    public AddEventCommand(String part) {
        this.part = part;
    }

    /**
     * Executes the command by parsing the event description, start time, and end time,
     * creating a new Event task, adding it to the task list, and saving it to the file system.
     *
     * @param fileSystem The file system instance used to persist the new task.
     * @throws SkywalkerException If /from or /to delimiters are missing, or any field is empty.
     * @throws Exception If an error occurs while saving to the file system.
     */
    @Override
    public void execute(FileSystem fileSystem) throws Exception {
        String args = this.part;
        if (!args.contains("/from") || !args.contains("/to")) {
            throw new SkywalkerException(SkywalkerUi.ERROR_MISSING_EVENT_DELIMITERS);
        }

        int fIdx = args.indexOf("/from");
        int tIdx = args.indexOf("/to");
        String desc;
        String from;
        String to;

        try {
            if (fIdx < tIdx) {
                desc = args.substring(0, fIdx).trim();
                from = args.substring(fIdx + 5, tIdx).trim();
                to = args.substring(tIdx + 3).trim();
            } else {
                desc = args.substring(0, tIdx).trim();
                to = args.substring(tIdx + 3, fIdx).trim();
                from = args.substring(fIdx + 5).trim();
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new SkywalkerException(SkywalkerUi.ERROR_EVENT_PART_EMPTY);
        }

        if (desc.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new SkywalkerException(SkywalkerUi.ERROR_EVENT_PART_EMPTY);
        }

        Task event = new Event(from, to, desc);
        TaskList.add(event);
        fileSystem.addTask(event);
        notifyAdd(event);
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