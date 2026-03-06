package skywalker.command;

import skywalker.filesystem.FileSystem;
import skywalker.exception.SkywalkerException;
import skywalker.task.Deadline;
import skywalker.task.Task;
import skywalker.taskmanager.TaskList;
import skywalker.ui.SkywalkerUi;

public class AddDeadlineCommand extends Command {
    private String part;
    public AddDeadlineCommand(String part) {
        this.part = part;
    }

    @Override
    public void execute(FileSystem fileSystem) throws Exception {

        String[] details = this.part.split(" /by ", 2);
        if (details.length < 2 || details[1].trim().isEmpty()) {
            throw new SkywalkerException(SkywalkerUi.ERROR_MISSING_BY);
        }
        Task d = new Deadline(details[0].trim(), details[1].trim());
        TaskList.add(d);
        fileSystem.addTask(d);
        notifyAdd(d);
    }

    private void notifyAdd(Task task) {
        String message = SkywalkerUi.MESSAGE_ADD_SUCCESS + "\t    " + task + "\n" + SkywalkerUi.getTaskCountMessage(TaskList.getCount());
        SkywalkerUi.printWithLines(message);
    }
}
