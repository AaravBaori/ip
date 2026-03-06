package skywalker.command;

import skywalker.filesystem.FileSystem;
import skywalker.exception.SkywalkerException;
import skywalker.task.Event;
import skywalker.task.Task;
import skywalker.taskmanager.TaskList;
import skywalker.ui.SkywalkerUi;

public class AddEventCommand extends Command {
    private String part;
    public AddEventCommand(String part) {
        this.part = part;
    }

    @Override
    public void execute(FileSystem fileSystem) throws Exception {
        String args = this.part;
        if (!args.contains("/from") || !args.contains("/to")) throw new SkywalkerException(SkywalkerUi.ERROR_MISSING_EVENT_DELIMITERS);

        int fIdx = args.indexOf("/from");
        int tIdx = args.indexOf("/to");
        String desc, from, to;

        try {
            if (fIdx < tIdx) {
                desc = args.substring(0, fIdx).trim();
                from = args.substring(fIdx + 5, tIdx).trim();
                to = args.substring(tIdx + 3).trim();
            } else {
                desc = args.substring(0, tIdx).trim();
                to = args.substring(tIdx + 3, fIdx).trim();
                from = args.substring(fIdx + 5).trim();
            }
            if (desc.isEmpty() || from.isEmpty() || to.isEmpty()) throw new SkywalkerException(SkywalkerUi.ERROR_EVENT_PART_EMPTY);

            Task e = new Event(from, to, desc);
            TaskList.add(e);
            fileSystem.addTask(e);
            notifyAdd(e);
        } catch (Exception e) {
            throw new SkywalkerException(SkywalkerUi.ERROR_EVENT_PART_EMPTY);
        }
    }

    private void notifyAdd(Task task) {
        String message = SkywalkerUi.MESSAGE_ADD_SUCCESS + "\t    " + task + "\n" + SkywalkerUi.getTaskCountMessage(TaskList.getCount());
        SkywalkerUi.printWithLines(message);
    }
}
