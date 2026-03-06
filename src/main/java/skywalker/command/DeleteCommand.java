package skywalker.command;

import skywalker.filesystem.FileSystem;
import skywalker.exception.SkywalkerException;
import skywalker.taskmanager.TaskList;
import skywalker.ui.SkywalkerUi;

public class DeleteCommand extends Command{
    private final String taskNumberStr;

    public DeleteCommand(String part) {
        this.taskNumberStr = part;
    }

    @Override
    public void execute(FileSystem file) throws Exception {
        int taskNumberInt;
        try{
            taskNumberInt = Integer.parseInt(taskNumberStr);
        }catch (NumberFormatException e){
            System.out.println(STR."Disturbance in the force: \{e.getMessage()}");
            throw new SkywalkerException(SkywalkerUi.ERROR_INVALID_FORMAT);
        }
        System.out.println(STR."\{SkywalkerUi.MESSAGE_DELETE_SUCCESS}\t\t\{TaskList.getTask(taskNumberInt - 1).toString()}");
        file.deleteTask(taskNumberInt - 1);
        TaskList.delete(taskNumberInt - 1);
        System.out.println(STR."\tNow you have \{TaskList.getCount()} missions in the list");
    }
}
