import java.util.Scanner;
import skywalker.responses.SkywalkerResponses;

public class Skywalker {
    static final String UNMARK = "unmark";
    static final String TODO = "todo";
    static final String MARK = "mark";
    static final String DEADLINE = "deadline";
    static final String EVENT = "event";
    static final String LIST = "list";
    static final String BYE = "bye";

    private final TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {
        new Skywalker().run();
    }

    public void run() {
        SkywalkerResponses.printWithLines(SkywalkerResponses.WELCOME_MESSAGE);
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
                    default:
                        SkywalkerResponses.printWithLines(SkywalkerResponses.UNKNOWN_COMMAND);
                        break;
                }
            } catch (SkywalkerException e) {
                SkywalkerResponses.printWithLines("\t A disturbance in the Force: " + e.getMessage());
            } catch (Exception e) {
                SkywalkerResponses.printWithLines("\t Unexpected error in the galaxy: " + e.getMessage());
            }
        }
        SkywalkerResponses.printWithLines(SkywalkerResponses.BYE_MESSAGE);
    }

    public String handleUserInput(String userInput) {
        return userInput.split(" ")[0];
    }

    public void handleList() {
        if (taskManager.isEmpty()) {
            SkywalkerResponses.printWithLines(SkywalkerResponses.MESSAGE_EMPTY_LIST);
            return;
        }
        StringBuilder sb = new StringBuilder(SkywalkerResponses.MESSAGE_LIST_HEADER);
        for (int i = 0; i < taskManager.getCount(); i++) {
            sb.append(String.format("\t  %d.%s\n", i + 1, taskManager.getTask(i)));
        }
        SkywalkerResponses.printWithLines(sb.toString().trim());
    }

    public void handleMark(String userInput) throws SkywalkerException {
        try {
            int num = getMarkNumber(userInput);
            Task t = taskManager.getTask(num - 1);
            if (t.isDone()) throw new SkywalkerException(SkywalkerResponses.ERROR_ALREADY_DONE);

            t.setDone(true);
            SkywalkerResponses.printWithLines(SkywalkerResponses.MESSAGE_MARK_SUCCESS + "\t    " + t);
        } catch (NumberFormatException e) {
            throw new SkywalkerException(SkywalkerResponses.ERROR_NOT_INT);
        }
    }

    public void handleUnmark(String userInput) throws SkywalkerException {
        try {
            int num = getMarkNumber(userInput);
            Task t = taskManager.getTask(num - 1);
            if (!t.isDone()) throw new SkywalkerException(SkywalkerResponses.ERROR_NOT_DONE_YET);

            t.setDone(false); // Fixed: set to false
            SkywalkerResponses.printWithLines(SkywalkerResponses.MESSAGE_UNMARK_SUCCESS + "\t    " + t); // Fixed: unmark message
        } catch (NumberFormatException e) {
            throw new SkywalkerException(SkywalkerResponses.ERROR_NOT_INT);
        }
    }

    public void handleTodo(String userInput) throws SkywalkerException {
        String[] parts = userInput.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new SkywalkerException(SkywalkerResponses.ERROR_EMPTY_TODO);
        }
        Task todo = new Todo(parts[1].trim());
        taskManager.add(todo);
        notifyAdd(todo);
    }

    public void handleDeadline(String userInput) throws SkywalkerException {
        String[] parts = userInput.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new SkywalkerException(SkywalkerResponses.ERROR_EMPTY_DEADLINE);
        }
        String[] details = parts[1].split(" /by ", 2);
        if (details.length < 2 || details[1].trim().isEmpty()) {
            throw new SkywalkerException(SkywalkerResponses.ERROR_MISSING_BY);
        }
        Task d = new Deadline(details[0].trim(), details[1].trim());
        taskManager.add(d);
        notifyAdd(d);
    }

    public void handleEvent(String userInput) throws SkywalkerException {
        String[] parts = userInput.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) throw new SkywalkerException(SkywalkerResponses.ERROR_EMPTY_EVENT);

        String args = parts[1];
        if (!args.contains("/from") || !args.contains("/to")) throw new SkywalkerException(SkywalkerResponses.ERROR_MISSING_EVENT_DELIMITERS);

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
            if (desc.isEmpty() || from.isEmpty() || to.isEmpty()) throw new SkywalkerException(SkywalkerResponses.ERROR_EVENT_PART_EMPTY);

            Task e = new Event(from, to, desc);
            taskManager.add(e);
            notifyAdd(e);
        } catch (Exception e) {
            throw new SkywalkerException(SkywalkerResponses.ERROR_EVENT_PART_EMPTY);
        }
    }

    private void notifyAdd(Task task) {
        String message = SkywalkerResponses.MESSAGE_ADD_SUCCESS + "\t    " + task + "\n" +
                SkywalkerResponses.getTaskCountMessage(taskManager.getCount());
        SkywalkerResponses.printWithLines(message);
    }

    private int getMarkNumber(String userInput) throws SkywalkerException {
        String[] parts = userInput.split(" ");
        if (parts.length < 2) throw new SkywalkerException(SkywalkerResponses.ERROR_INVALID_FORMAT);

        int num = Integer.parseInt(parts[1]);
        if (num <= 0 || num > taskManager.getCount()) {
            throw new SkywalkerException("\t That mission does not exist in the Jedi archives! Choose 1 to " + taskManager.getCount());
        }
        return num;
    }
}