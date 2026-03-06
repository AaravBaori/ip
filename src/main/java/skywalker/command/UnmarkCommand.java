package skywalker.command;

import skywalker.exception.SkywalkerException;
import skywalker.filesystem.FileSystem;
import skywalker.task.Task;
import skywalker.taskmanager.TaskList;
import skywalker.ui.SkywalkerUi;

public class UnmarkCommand extends Command{
    private final String userInput;

    public UnmarkCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(FileSystem file) throws Exception {
        try {
            int num = getMarkNumber(userInput);
            Task t = TaskList.getTask(num - 1);
            if (!t.isDone()) throw new SkywalkerException(SkywalkerUi.ERROR_NOT_DONE_YET);

            t.setDone(false);
            file.updateFile(num - 1, false);
            SkywalkerUi.printWithLines(SkywalkerUi.MESSAGE_MARK_SUCCESS + "\t    " + t);
        } catch (NumberFormatException e) {
            throw new SkywalkerException(SkywalkerUi.ERROR_NOT_INT);
        }
    }

    private int getMarkNumber(String userInput) throws SkywalkerException {
        String[] parts = userInput.split(" ");
        if (parts.length < 2) throw new SkywalkerException(SkywalkerUi.ERROR_INVALID_FORMAT);

        int num = Integer.parseInt(parts[1]);
        if (num <= 0 || num > TaskList.getCount()) {
            throw new SkywalkerException("\t That mission does not exist in the Jedi archives! Choose 1 to " + TaskList.getCount());
        }
        return num;
    }
}
