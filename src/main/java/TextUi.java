/**
 * Handles the text-based user interface for the application.
 * Responsible for printing formatted messages, including welcome/goodbye greetings,
 * task updates, and error notifications to the console.
 */
public class TextUi {
    /** Boundary line used to separate distinct sections of the UI output. */
    private static final String LINE_BREAK = "____________________________________________________________";

    /**
     * Displays a welcome message to the user when the application starts.
     * Framed by horizontal line breaks for clarity.
     */
    public void welcomeUser(){
        System.out.println(LINE_BREAK);
        System.out.println("Hello! I'm Skywalker");
        System.out.println("What can I do for you?");
        System.out.println(LINE_BREAK);
    }

    /**
     * Prints a single horizontal line break to the console.
     */
    public void showLine(){
        System.out.println(LINE_BREAK);
    }

    /**
     * Informs the user that a task has been successfully added to the system.
     * Displays the details of the most recently added task and the updated total count.
     *
     * @param taskManager The TaskManager containing the current list of tasks.
     */
    public void showTaskAdded(TaskManager taskManager){
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + taskManager.getTask(taskManager.getCount()-1));
        System.out.println("\tNow you have " + taskManager.getCount() + " tasks in the list");
    }

    /**
     * Displays a farewell message and a closing line break to the user upon exit.
     */
    public void showGoodbye(){
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE_BREAK);
    }

    /**
     * Prints an error message formatted with a specific prefix to indicate a failure.
     *
     * @param message The detailed description of the error that occurred.
     */
    public void showError(String message){
        System.out.println("\tError: " + message);
    }

    /**
     * Confirms to the user that a specific task has been unmarked (reverted to not done).
     *
     * @param unmarkedTask The task object that was updated.
     */
    public void showUnmark(Task unmarkedTask){
        System.out.println("\tNice! I've unmarked this task as done:");
        System.out.printf("\t\t[%s] %s%n", unmarkedTask.getStatusIcon(), unmarkedTask);
    }

    /**
     * Confirms to the user that a specific task has been marked as completed.
     *
     * @param markedTask The task object that was updated.
     */
    public void showMark(Task markedTask){
        System.out.println("\tNice! I've marked this task as done:");
        System.out.printf("\t\t[%s] %s%n", markedTask.getStatusIcon(), markedTask);
    }
}
