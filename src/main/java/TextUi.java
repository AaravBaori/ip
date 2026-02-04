public class TextUi {
    private static final String LINE_BREAK = "____________________________________________________________";

    public void welcomeUser(){
        System.out.println(LINE_BREAK);
        System.out.println("Hello! I'm Skywalker");
        System.out.println("What can I do for you?");
        System.out.println(LINE_BREAK);
    }

    public void showLine(){
        System.out.println(LINE_BREAK);
    }

    public void showTaskAdded(TaskManager taskManager){
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + taskManager.getTask(taskManager.getCount()-1));
        System.out.println("\tNow you have " + taskManager.getCount() + " tasks in the list");
    }

    public void showGoodbye(){
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE_BREAK);
    }

    public void showError(String message){
        System.out.println("\tError: " + message);
    }

    public void showUnmark(Task unmarkedTask){
        System.out.println("\tNice! I've unmarked this task as done:");
        System.out.printf("\t\t[%s] %s%n", unmarkedTask.getStatusIcon(), unmarkedTask);
    }

    public void showMark(Task markedTask){
        System.out.println("\tNice! I've unmarked this task as done:");
        System.out.printf("\t\t[%s] %s%n", markedTask.getStatusIcon(), markedTask);
    }
}
