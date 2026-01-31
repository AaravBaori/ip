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
        int index = 0;
        String[] user_inputs = new String[100];

        while(!user_input.equalsIgnoreCase("bye")){
            System.out.println("____________________________________________________________");
            if(user_input.equalsIgnoreCase("list")){
                int num = 1;
                for(int i = 0; i< index; i++){
                    System.out.printf("\t%d. %s\n",num, user_inputs[i]);
                    num++;
                }
            }
            else {
                user_inputs[index++] = user_input;
                System.out.printf("\tadded: %s\n", user_input);
            }
                System.out.println("____________________________________________________________");
                user_input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
