package skywalker.command;

import skywalker.filesystem.FileSystem;
import skywalker.exception.SkywalkerException;
import skywalker.task.Task;
import skywalker.taskmanager.TaskList;
import skywalker.ui.SkywalkerUi;

public class MarkCommand extends Command {
    private final String userInput;

    public MarkCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(FileSystem file) throws Exception {
        try {
            int num = getMarkNumber(userInput);
            Task t = TaskList.getTask(num - 1);
            if (t.isDone()) throw new SkywalkerException(SkywalkerUi.ERROR_ALREADY_DONE);

            t.setDone(true);
            file.updateFile(num - 1, true);
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
