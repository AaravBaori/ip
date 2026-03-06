package skywalker.command;

import skywalker.filesystem.FileSystem;
import skywalker.taskmanager.TaskList;
import skywalker.ui.SkywalkerUi;

public class ExitCommand extends Command {

    @Override
    public void execute(FileSystem storage) {
        SkywalkerUi.printWithLines(SkywalkerUi.BYE_MESSAGE);
    }

//    public boolean isExit() { return true; }
}