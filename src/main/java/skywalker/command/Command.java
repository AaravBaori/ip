package skywalker.command;
import skywalker.taskmanager.TaskList;
import skywalker.ui.SkywalkerUi;
import skywalker.filesystem.FileSystem;

public abstract class Command {
    public abstract void execute(FileSystem fileSystem) throws Exception;
    public boolean isExit() { return false; }
}