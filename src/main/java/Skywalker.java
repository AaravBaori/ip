import java.lang.*;
import java.util.Scanner;

public class Skywalker {
    public static void main(String[] args) {

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Skywalker");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        Scanner scanner = new Scanner(System.in);

        String user_input;
        user_input = scanner.nextLine();

        while (!user_input.equalsIgnoreCase("bye")) {
            System.out.println("____________________________________________________________");
            if (user_input.equalsIgnoreCase("list")) {
                int num = 1;
                for (int i = 0; i < Task.count - 1; i++) {
                    System.out.printf("\t%d. [%s] %s\n", num, Task.getAllTasks()[i].getStatusIcon(), Task.getAllTasks()[i].getDescription());
                    num++;
                }
            } else if (user_input.split(" ")[0].equalsIgnoreCase("mark")) {
                int count = Integer.parseInt(user_input.split(" ")[1]);
                Task.markAsDone(count);
                System.out.println("\tNice! I've marked this task as done:");
                System.out.printf("\t\t[%s] %s\n", Task.getAllTasks()[count - 1].getStatusIcon(), Task.getAllTasks()[count - 1].getDescription());
            } else if (user_input.split(" ")[0].equalsIgnoreCase("unmark")) {
                int count = Integer.parseInt(user_input.split(" ")[1]);
                Task.markAsNotDone(count);
                System.out.println("\tNice! I've unmarked this task as not done yet:");
                System.out.printf("\t\t[%s] %s\n", Task.getAllTasks()[count - 1].getStatusIcon(), Task.getAllTasks()[count - 1].getDescription());
            } else {
                Task.addTask(new Task(user_input));
                System.out.printf("\tadded: %s\n", user_input);
            }
            System.out.println("____________________________________________________________");
            user_input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
