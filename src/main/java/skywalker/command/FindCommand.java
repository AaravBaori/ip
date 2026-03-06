package skywalker.command;

import skywalker.filesystem.FileSystem;
import skywalker.task.Task;
import skywalker.taskmanager.TaskList;
import skywalker.ui.SkywalkerUi;

import java.util.ArrayList;

/**
 * Represents the command to search for tasks whose descriptions contain a given keyword.
 */
public class FindCommand extends Command {

    /** The keyword to search for within task descriptions. */
    private final String search;

    /**
     * Constructs a FindCommand with the specified search keyword.
     *
     * @param search The keyword to match against task descriptions.
     */
    public FindCommand(String search) {
        this.search = search;
    }

    /**
     * Executes the command by searching all tasks for the keyword and printing any matches.
     * If the task list is empty or no matches are found, an appropriate message is shown.
     * The trailing newline is stripped to prevent an extra blank line before the closing line break.
     *
     * @param fileSystem The file system instance (not used by this command).
     */
    @Override
    public void execute(FileSystem fileSystem) {
        if (TaskList.isEmpty()) {
            SkywalkerUi.printWithLines(SkywalkerUi.MESSAGE_EMPTY_LIST);
            return;
        }

        ArrayList<Task> allTasks = TaskList.getTasks();
        ArrayList<Integer> matchingIndices = new ArrayList<>();

        for (Task task : allTasks) {
            if (task.getDescription().contains(this.search)) {
                matchingIndices.add(allTasks.indexOf(task));
            }
        }

        if (matchingIndices.isEmpty()) {
            SkywalkerUi.printWithLines(SkywalkerUi.MESSAGE_NO_RESULTS);
            return;
        }

        StringBuilder sb = new StringBuilder(SkywalkerUi.MESSAGE_FIND_HEADER);
        for (int i = 0; i < matchingIndices.size(); i++) {
            sb.append(String.format("\t  %d.%s\n", i + 1, TaskList.getTask(matchingIndices.get(i))));
        }
        SkywalkerUi.printWithLines(sb.toString());
    }
}