package skywalker;

import skywalker.command.Command;
import skywalker.exception.SkywalkerException;
import skywalker.filesystem.FileSystem;
import skywalker.parser.Parser;
import skywalker.taskmanager.TaskList;
import skywalker.ui.SkywalkerUi;

import java.util.Scanner;

/**
 * Main entry point for the Skywalker chatbot.
 * Handles user input, command routing, and task orchestration.
 */
public class Skywalker {
    /** Initializes the static task list used across the application. */
    private final TaskList tasks = new TaskList();
    private final FileSystem file;

    /**
     * Constructs a new Skywalker instance and loads saved tasks from disk.
     * If the save file cannot be read, an error message is displayed and the app starts fresh.
     */
    public Skywalker() {
        file = new FileSystem("./SkywalkerData.txt");

        try {
            file.parseFile(TaskList.getTasks());
        } catch (SkywalkerException e) {
            SkywalkerUi.printWithLines("Error loading archives.");
        }
    }

    /**
     * Creates a new Skywalker instance and starts the main execution loop.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Skywalker().run();
    }

    /**
     * Starts the main program loop.
     * Welcomes the user and continuously processes input until the 'bye' command is received.
     * All exceptions are caught and displayed to the user without terminating the loop.
     */
    public void run() {
        SkywalkerUi.printWithLines(SkywalkerUi.WELCOME_MESSAGE);
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase(Parser.COMMAND_BYE)) {
                break;
            }

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