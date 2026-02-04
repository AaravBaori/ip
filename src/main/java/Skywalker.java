import java.util.Scanner;

public class Skywalker {
    public static void main(String[] args) {

        String LINE_BREAK = "____________________________________________________________";
        System.out.println(LINE_BREAK);
        System.out.println("Hello! I'm Skywalker");
        System.out.println("What can I do for you?");
        System.out.println(LINE_BREAK);
        Scanner scanner = new Scanner(System.in);

        // Standard W3.6b: Changed user_input to userInput (camelCase)
        String userInput = scanner.nextLine();

        while (!userInput.equalsIgnoreCase("bye")) {
            System.out.println(LINE_BREAK);

            if (userInput.equalsIgnoreCase("list")) {
                int displayIndex = 1; // Changed num to displayIndex (W3.6d)
                // Corrected loop to include the last task
                System.out.println("\tHere are the tasks in your list:");
                for (int i = 0; i < Task.getCount(); i++) {
                    Task currentTask = Task.getAllTasks()[i];
                    System.out.printf("\t%d.%s\n",i, currentTask);
                    displayIndex++;
                }
            } else if (userInput.split(" ")[0].equalsIgnoreCase("mark")) {
                // Changed count to taskNumber to be a descriptive noun (W3.6d)
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                Task.markAsDone(taskNumber);

                Task taskToMark = Task.getAllTasks()[taskNumber - 1];
                System.out.println("\tNice! I've marked this task as done:");
                System.out.printf("\t\t[%s] %s%n", taskToMark.getStatusIcon(), taskToMark);

            } else if (userInput.split(" ")[0].equalsIgnoreCase("unmark")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                Task.markAsNotDone(taskNumber);

                Task taskToUnmark = Task.getAllTasks()[taskNumber - 1];
                System.out.println("\tNice! I've unmarked this task as not done yet:");
                System.out.printf("\t\t[%s] %s%n", taskToUnmark.getStatusIcon(), taskToUnmark);

            } else if (userInput.split(" ")[0].equalsIgnoreCase("todo")){
                String description = userInput.split(" ", 2)[1];
                Todo todo = new Todo(description);
                Task.addTask(todo);
                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t\t" + todo);
                System.out.println("\tNow you have " + Task.getCount() + " tasks in the list");

            } else if(userInput.split(" ")[0].equalsIgnoreCase("add")){
                Task.addTask(new Task(userInput));
                System.out.printf("\tadded: %s%n", userInput);
            } else if(userInput.split(" ")[0].equalsIgnoreCase("deadline")){
                String description = userInput.split(" /")[0].split(" ", 2)[1];
                String by = userInput.split("/by")[1].strip();
                Deadline deadline = new Deadline(description, by);
                Task.addTask(deadline);
                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t\t" + deadline);
                System.out.println("\tNow you have " + Task.getCount() + " tasks in the list");
            } else if(userInput.split(" ")[0].equalsIgnoreCase("event")){
                String description = userInput.split(" /")[0].split(" ", 2)[1];
                String from = userInput.split("/from")[1].strip().split("/to")[0].strip();
                String to = userInput.split("/from")[1].strip().split("/to")[1].strip();
                Event event = new Event(from, to, description);
                Task.addTask(event);
                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t\t" + event);
                System.out.println("\tNow you have " + Task.getCount() + " tasks in the list");
            }

            System.out.println(LINE_BREAK);
            userInput = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE_BREAK);
    }
}