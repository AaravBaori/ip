package skywalker.command;

import skywalker.filesystem.FileSystem;
import skywalker.taskmanager.TaskList;
import skywalker.ui.SkywalkerUi;

/**
 * Represents the command to display all tasks currently in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the command by printing all tasks in the task list.
     * If the list is empty, a message is shown instead.
     * The trailing newline is stripped to prevent an extra blank line before the closing line break.
     *
     * @param fileSystem The file system instance (not used by this command).
     * @throws Exception Not thrown by this command.
     */
    @Override
    public void execute(FileSystem fileSystem) throws Exception {
        if (TaskList.isEmpty()) {
            SkywalkerUi.printWithLines(SkywalkerUi.MESSAGE_EMPTY_LIST);
            return;
        }
        StringBuilder sb = new StringBuilder(SkywalkerUi.MESSAGE_LIST_HEADER);
        for (int i = 0; i < TaskList.getCount(); i++) {
            sb.append(String.format("\t  %d.%s\n", i + 1, TaskList.getTask(i)));
        }
        SkywalkerUi.printWithLines(sb.toString());
    }
}