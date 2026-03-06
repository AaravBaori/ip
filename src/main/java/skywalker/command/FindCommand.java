package skywalker.command;

import skywalker.filesystem.FileSystem;
import skywalker.taskmanager.TaskList;
import skywalker.ui.SkywalkerUi;
import skywalker.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command{
    String search;

    public FindCommand(String search){
        this.search = search;
    }

    public void execute(FileSystem fileSystem){
        ArrayList<Integer> tasksHavingSearch = new ArrayList<Integer>();
        ArrayList<Task> tasks = TaskList.getTasks();
        if (TaskList.isEmpty()) {
            SkywalkerUi.printWithLines(SkywalkerUi.MESSAGE_EMPTY_LIST);
            return;
        }
        for(Task task: tasks){
            if(task.getDescription().contains(this.search)){
                tasksHavingSearch.add(tasks.indexOf(task));
            }
        }
        StringBuilder sb = new StringBuilder(SkywalkerUi.MESSAGE_FIND_HEADER);
        for (int i = 0; i < tasksHavingSearch.size(); i++) {
            sb.append(String.format("\t  %d.%s\n", i + 1, TaskList.getTask(tasksHavingSearch.get(i))));
        }
        SkywalkerUi.printWithLines(sb.toString());
    }
}
