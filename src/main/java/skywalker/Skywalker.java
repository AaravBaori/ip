package skywalker;
import skywalker.command.Command;
import skywalker.filesystem.FileSystem;
import skywalker.parser.Parser;
import skywalker.taskmanager.TaskList;
import skywalker.exception.SkywalkerException;
import skywalker.ui.SkywalkerUi;

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
    // = new FileSystem("./SkywalkerData.txt");
    private final TaskList tasks = new TaskList();
    private final SkywalkerUi ui = new SkywalkerUi();
    private final FileSystem file;

    public Skywalker() {
        file = new FileSystem("./SkywalkerData.txt");

        try {
            file.parseFile(TaskList.getTasks());
        } catch (SkywalkerException e) {
            SkywalkerUi.printWithLines("Error loading archives.");
        }
    }

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
        SkywalkerUi.printWithLines(SkywalkerUi.WELCOME_MESSAGE);
        file.parseFile(TaskList.getTasks());
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase(BYE)) break;

            try {
                Command command = Parser.parse(userInput);
                command.execute(file);
            } catch (SkywalkerException e) {
                SkywalkerUi.printWithLines("\t A disturbance in the Force: " + e.getMessage());
            } catch (Exception e) {
                SkywalkerUi.printWithLines("\t Unexpected error in the galaxy: " + e.getMessage());
            }
        }
        SkywalkerUi.printWithLines(SkywalkerUi.BYE_MESSAGE);
    }
}