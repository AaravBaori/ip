import java.util.Scanner;

public class Skywalker {
    static final String LINE_BREAK = "____________________________________________________________";
    static final String TODO = "todo";
    static final String MARK = "mark";
    static final String UNMARK = "unmark";
    static final String ADD = "add";
    static final String DEADLINE = "deadline";
    static final String EVENT = "event";
    static final String LIST = "list";
    private final TextUi textUi = new TextUi();
    private final TaskManager taskManager = new TaskManager();

    public static void main(String[] args){
        Skywalker bot = new Skywalker();
        bot.run();
    }
    public void run() {
        textUi.welcomeUser();

        Scanner scanner = new Scanner(System.in);

        // Standard W3.6b: Changed user_input to userInput (camelCase)
        String userInput = scanner.nextLine();

        while (!userInput.equalsIgnoreCase("bye")) {
            textUi.showLine();

            String instructionType = handleUserInput(userInput).toLowerCase();

            switch (instructionType) {
                case LIST:
                    handleList();
                    break;

                case MARK:
                    // Descriptive noun used as per W3.6d
                    int markNumber = Integer.parseInt(userInput.split(" ")[1]);
                    handleMark(markNumber);
                    break;

                case UNMARK:
                    int unmarkNumber = Integer.parseInt(userInput.split(" ")[1]);
                    handleUnmark(unmarkNumber);
                    break;

                case TODO:
                    handleTodo(userInput);
                    break;

                case DEADLINE:
                    handleDeadline(userInput);
                    break;

                case EVENT:
                    handleEvent(userInput);
                    break;

                case ADD:
                    String addDesc = userInput.split(" ", 2)[1];
                    handleAdd(addDesc);
                    break;

                default:
                    System.out.println("\tI'm sorry, I don't recognize that command.");
                    break;
            }

            textUi.showLine();
            userInput = scanner.nextLine();
        }
        textUi.showGoodbye();
    }

    public String handleUserInput(String userInput){
        //returns instruction type
        return userInput.split(" ")[0];
    }

    public void handleList(){
        System.out.println("\tHere are all the tasks in your list:");
        for(int i = 0; i < taskManager.getCount(); i++){
            Task currentTask = taskManager.getTask(i);
            System.out.printf("\t%d.%s\n", i+1,currentTask);
        }
    }

    public void handleMark(int taskNumber){
        Task taskToMark = taskManager.getTask(taskNumber - 1);
        taskToMark.setDone(true);
        textUi.showMark(taskToMark);
    }

    public void handleUnmark(int taskNumber){
        Task taskToUnmark = taskManager.getTask(taskNumber - 1);
        taskToUnmark.setDone(false);
        textUi.showUnmark(taskToUnmark);
    }

    public void handleTodo(String userInputs){
        String[] parts = userInputs.split(" ", 2);

        if(parts.length < 2 || parts[1].trim().isEmpty()){
            textUi.showError("The description of todo cannot be empty.");
            return;
        }

        String taskDescription = parts[1].trim();
        Todo todo = new Todo(taskDescription);
        taskManager.add(todo);
        textUi.showTaskAdded(taskManager);
    }

    public void handleDeadline(String userInput){
        String[] parts = userInput.split(" ", 2);

        if(parts.length < 2 || parts[1].trim().isEmpty()){
            textUi.showError("The description of a deadline cannot be empty!");
            return;
        }

        String[] deadlineParts = parts[1].split(" /by", 2);

        if(deadlineParts.length < 2 || deadlineParts[1].trim().isEmpty()){
            textUi.showError("A deadline must have a date (use /by [date]).");
            return;
        }

        String taskDescription = deadlineParts[0].trim();
        String by = deadlineParts[1].trim();

        Deadline deadline = new Deadline(taskDescription, by);
        taskManager.add(deadline);
        textUi.showTaskAdded(taskManager);
    }

    public void handleEvent(String userInput){
        String[] parts = userInput.split(" ", 2);

        if(parts.length < 2 || parts[1].trim().isEmpty()){
            textUi.showError("The description of an event cannot be empty!");
            return;
        }

        String[] eventParts = parts[1].split(" /from ", 2);
        if(eventParts.length < 2 || eventParts[1].trim().isEmpty()){
            textUi.showError("An event must have a start time (use /from [time]).");
            return;
        }

        String[] timeParts = eventParts[1].split(" /to ", 2);
        if(timeParts.length < 2 || timeParts[1].trim().isEmpty()){
            textUi.showError("An event must have an end time (use /to [time]).");
            return;
        }

        String taskDescription = eventParts[0].trim();
        String from = timeParts[0].trim();
        String to = timeParts[1].trim();
        Event event = new Event(from, to, taskDescription);
        taskManager.add(event);
        textUi.showTaskAdded(taskManager);
    }

    public void handleAdd(String userInput){
        String[] parts = userInput.split(" ");

        if(parts.length < 2 || parts[1].trim().isEmpty()){
            textUi.showError("There is no description provided with the add command");
            return;
        }

        String taskDescription = parts[1].trim();
        Task task = new Task(taskDescription);
        taskManager.add(task);
        System.out.printf("\tadded: %s%n", task);
    }
}