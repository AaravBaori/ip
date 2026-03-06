package skywalker.command;

import skywalker.filesystem.FileSystem;
import skywalker.task.Task;
import skywalker.task.Todo;
import skywalker.taskmanager.TaskList;
import skywalker.ui.SkywalkerUi;

public class AddTodoCommand extends Command {
    private final String part;
    public AddTodoCommand(String part) {
        this.part = part;
    }

    @Override
    public void execute(TaskList tasks, SkywalkerUi ui, FileSystem fileSystem) throws Exception {
        Task todo = new Todo(this.part.trim());
        TaskList.add(todo);
        fileSystem.addTask(todo);
        notifyAdd(todo);
    }

    private void notifyAdd(Task task) {
        String message = SkywalkerUi.MESSAGE_ADD_SUCCESS + "\t    " + task + "\n" + SkywalkerUi.getTaskCountMessage(TaskList.getCount());
        SkywalkerUi.printWithLines(message);
    }
}
