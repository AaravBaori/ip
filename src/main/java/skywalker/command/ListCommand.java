package skywalker.command;

import skywalker.filesystem.FileSystem;
import skywalker.taskmanager.TaskList;
import skywalker.ui.SkywalkerUi;

public class ListCommand extends Command {
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
