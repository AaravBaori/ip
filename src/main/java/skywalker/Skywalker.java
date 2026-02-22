package skywalker;
import skywalker.task.Task;
import skywalker.task.Todo;
import skywalker.task.Event;
import skywalker.task.Deadline;
import skywalker.taskmanager.TaskManager;
import skywalker.exception.SkywalkerException;
import skywalker.ui.SkywalkerUi;
import skywalker.FileSystem;

import java.io.IOException;
import java.util.Scanner;

/**
 * Main entry point for the Skywalker chatbot.
 * Handles user input, command routing, and task orchestration.
 */
public class Skywalker {
    static final String UNMARK = "unmark";
    static final String TODO = "todo";
    static final String MARK = "mark";
    static final String DEADLINE = "deadline";
    static final String EVENT = "event";
    static final String LIST = "list";
    static final String DELETE = "delete";
    static final String BYE = "bye";

    private final TaskManager taskManager = new TaskManager();
    private final FileSystem file = new FileSystem("./SkywalkerData.txt");

    /**
     * Initializes the Skywalker bot and begins the execution loop.
     * * @param args Command line arguments (not used).
     */
    public static void main(String[] args) throws SkywalkerException {
        new Skywalker().run();
    }

    /**
     * Starts the main program loop. Welcomes the user and continuously
     * processes input until the 'bye' command is received.
     */
    public void run() throws SkywalkerException {
        taskManager.pastEntries();
        SkywalkerUi.printWithLines(SkywalkerUi.WELCOME_MESSAGE);
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase(BYE)) break;

            try {
                String command = handleUserInput(userInput).toLowerCase();

                switch (command) {
                    case LIST:     handleList(); break;
                    case MARK:     handleMark(userInput); break;
                    case UNMARK:   handleUnmark(userInput); break;
                    case TODO:     handleTodo(userInput); break;
                    case DEADLINE: handleDeadline(userInput); break;
                    case EVENT:    handleEvent(userInput); break;
                    case DELETE: handleDelete(userInput); break;
                    default:
                        SkywalkerUi.printWithLines(SkywalkerUi.UNKNOWN_COMMAND);
                        break;
                }
            } catch (SkywalkerException e) {
                SkywalkerUi.printWithLines("\t A disturbance in the Force: " + e.getMessage());
            } catch (Exception e) {
                SkywalkerUi.printWithLines("\t Unexpected error in the galaxy: " + e.getMessage());
            }
        }
        SkywalkerUi.printWithLines(SkywalkerUi.BYE_MESSAGE);
    }

    /**
     * Extracts the primary command keyword from the user's raw input string.
     * * @param userInput The full line of text entered by the user.
     * @return The first word of the input, representing the command.
     */
    public String handleUserInput(String userInput) {
        return userInput.split(" ")[0];
    }

    /**
     * Displays all current tasks in the task manager.
     * If the list is empty, a themed empty-state message is shown.
     */
    public void handleList() {
        if (taskManager.isEmpty()) {
            SkywalkerUi.printWithLines(SkywalkerUi.MESSAGE_EMPTY_LIST);
            return;
        }
        StringBuilder sb = new StringBuilder(SkywalkerUi.MESSAGE_LIST_HEADER);
        for (int i = 0; i < taskManager.getCount(); i++) {
            sb.append(String.format("\t  %d.%s\n", i + 1, taskManager.getTask(i)));
        }
        SkywalkerUi.printWithLines(sb.toString());
    }

    /**
     * Marks a specific task as completed based on its index in the list.
     * * @param userInput Raw input containing the task number.
     * @throws SkywalkerException If the input is invalid or the task is already done.
     */
    public void handleMark(String userInput) throws SkywalkerException {
        try {
            int num = getMarkNumber(userInput);
            Task t = taskManager.getTask(num - 1);
            if (t.isDone()) throw new SkywalkerException(SkywalkerUi.ERROR_ALREADY_DONE);

            t.setDone(true);
            file.updateFile(num - 1, true);
            SkywalkerUi.printWithLines(SkywalkerUi.MESSAGE_MARK_SUCCESS + "\t    " + t);
        } catch (NumberFormatException e) {
            throw new SkywalkerException(SkywalkerUi.ERROR_NOT_INT);
        }
    }

    /**
     * Unmarks a completed task, reverting its status to not done.
     * * @param userInput Raw input containing the task number.
     * @throws SkywalkerException If the input is invalid or the task isn't marked yet.
     */
    public void handleUnmark(String userInput) throws SkywalkerException {
        try {
            int num = getMarkNumber(userInput);
            Task t = taskManager.getTask(num - 1);
            if (!t.isDone()) throw new SkywalkerException(SkywalkerUi.ERROR_NOT_DONE_YET);

            t.setDone(false);
            file.updateFile(num - 1, false);
            SkywalkerUi.printWithLines(SkywalkerUi.MESSAGE_UNMARK_SUCCESS + "\t    " + t);
        } catch (NumberFormatException e) {
            throw new SkywalkerException(SkywalkerUi.ERROR_NOT_INT);
        }
    }

    /**
     * Creates and adds a simple 'Todo' task to the manager.
     * * @param userInput Raw input containing the task description.
     * @throws SkywalkerException If the description is empty.
     */
    public void handleTodo(String userInput) throws SkywalkerException, IOException {
        String[] parts = userInput.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new SkywalkerException(SkywalkerUi.ERROR_EMPTY_TODO);
        }
        Task todo = new Todo(parts[1].trim());
        taskManager.add(todo);
        file.addTask(todo);
        notifyAdd(todo);
    }

    /**
     * Creates and adds a 'Deadline' task with a specific due date/time.
     * * @param userInput Raw input containing description and /by clause.
     * @throws SkywalkerException If parts are missing or formatted incorrectly.
     */
    public void handleDeadline(String userInput) throws SkywalkerException, IOException {
        String[] parts = userInput.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new SkywalkerException(SkywalkerUi.ERROR_EMPTY_DEADLINE);
        }
        String[] details = parts[1].split(" /by ", 2);
        if (details.length < 2 || details[1].trim().isEmpty()) {
            throw new SkywalkerException(SkywalkerUi.ERROR_MISSING_BY);
        }
        Task d = new Deadline(details[0].trim(), details[1].trim());
        taskManager.add(d);
        file.addTask(d);
        notifyAdd(d);
    }

    /**
     * Creates and adds an 'Event' task with a start and end time.
     * This method supports flexible flag order for /from and /to.
     * * @param userInput Raw input containing description, /from, and /to clauses.
     * @throws SkywalkerException If the event structure is invalid or parts are empty.
     */
    public void handleEvent(String userInput) throws SkywalkerException, IOException {
        String[] parts = userInput.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) throw new SkywalkerException(SkywalkerUi.ERROR_EMPTY_EVENT);

        String args = parts[1];
        if (!args.contains("/from") || !args.contains("/to")) throw new SkywalkerException(SkywalkerUi.ERROR_MISSING_EVENT_DELIMITERS);

        int fIdx = args.indexOf("/from");
        int tIdx = args.indexOf("/to");

        Task e = getTask(fIdx, tIdx, args);
        taskManager.add(e);
        file.addTask(e);
        notifyAdd(e);
    }

    private static Task getTask(int fIdx, int tIdx, String args) throws SkywalkerException {
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
        } catch (Exception e) {
            throw new SkywalkerException(SkywalkerUi.ERROR_EVENT_PART_EMPTY);
        }

        return new Event(from, to, desc);
    }

    /**
     * Formats and prints a success message whenever a task is added.
     * * @param task The task that was successfully added.
     */
    private void notifyAdd(Task task) {
        String message = SkywalkerUi.MESSAGE_ADD_SUCCESS + "\t    " + task + "\n" + SkywalkerUi.getTaskCountMessage(taskManager.getCount());
        SkywalkerUi.printWithLines(message);
    }

    /**
     * Validates and parses the task index from user input for mark/unmark commands.
     * * @param userInput The raw input string from the user.
     * @return The validated integer representing the task number.
     * @throws SkywalkerException If the number is out of bounds or the command is malformed.
     */
    private int getMarkNumber(String userInput) throws SkywalkerException {
        String[] parts = userInput.split(" ");
        if (parts.length < 2) throw new SkywalkerException(SkywalkerUi.ERROR_INVALID_FORMAT);

        int num = Integer.parseInt(parts[1]);
        if (num <= 0 || num > taskManager.getCount()) {
            throw new SkywalkerException("\t That mission does not exist in the Jedi archives! Choose 1 to " + taskManager.getCount());
        }
        return num;
    }

    private void handleDelete(String userInput) throws SkywalkerException, IOException {
        String[] parts = userInput.split(" ");

        if (parts.length < 2 || parts[1].trim().isEmpty()) throw new SkywalkerException(SkywalkerUi.ERROR_EMPTY_EVENT);

        String taskNumberStr = parts[1];
        int taskNumberInt;
        try{
            taskNumberInt = Integer.parseInt(taskNumberStr);
        }catch (NumberFormatException e){
            System.out.println("Disturbance in the force: " + e.getMessage());
            throw new SkywalkerException(SkywalkerUi.ERROR_INVALID_FORMAT);
        }
        SkywalkerUi.printWithLines(SkywalkerUi.MESSAGE_DELETE_SUCCESS + "\t\t" + taskManager.getTask(taskNumberInt-1).toString() + "\n" + "\tNow you have " + taskManager.getCount() + " missions in the list");
        file.deleteTask(taskNumberInt - 1);
        taskManager.delete(taskNumberInt - 1);
    }


}