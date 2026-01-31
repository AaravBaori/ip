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
                for (int i = 0; i < Task.count - 1; i++) {
                    Task currentTask = Task.getAllTasks()[i];
                    System.out.printf("\t%d. [%s] %s%n",
                            displayIndex,
                            currentTask.getStatusIcon(),
                            currentTask.getDescription());
                    displayIndex++;
                }
            } else if (userInput.split(" ")[0].equalsIgnoreCase("mark")) {
                // Changed count to taskNumber to be a descriptive noun (W3.6d)
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                Task.markAsDone(taskNumber);

                Task taskToMark = Task.getAllTasks()[taskNumber - 1];
                System.out.println("\tNice! I've marked this task as done:");
                System.out.printf("\t\t[%s] %s%n", taskToMark.getStatusIcon(), taskToMark.getDescription());

            } else if (userInput.split(" ")[0].equalsIgnoreCase("unmark")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                Task.markAsNotDone(taskNumber);

                Task taskToUnmark = Task.getAllTasks()[taskNumber - 1];
                System.out.println("\tNice! I've unmarked this task as not done yet:");
                System.out.printf("\t\t[%s] %s%n", taskToUnmark.getStatusIcon(), taskToUnmark.getDescription());

            } else {
                Task.addTask(new Task(userInput));
                System.out.printf("\tadded: %s%n", userInput);
            }

            System.out.println(LINE_BREAK);
            userInput = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE_BREAK);
    }
}