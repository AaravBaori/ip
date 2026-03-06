package skywalker.parser;

import skywalker.command.AddDeadlineCommand;
import skywalker.command.AddEventCommand;
import skywalker.command.AddTodoCommand;
import skywalker.command.Command;
import skywalker.command.DeleteCommand;
import skywalker.command.FindCommand;
import skywalker.command.ListCommand;
import skywalker.command.MarkCommand;
import skywalker.command.UnmarkCommand;
import skywalker.exception.SkywalkerException;
import skywalker.ui.SkywalkerUi;

/**
 * Parses raw user input and maps it to the appropriate Command object.
 * Acts as the entry point for all command interpretation in the Skywalker chatbot.
 */
public class Parser {
    /** Command keyword for adding a deadline task. */
    public static final String COMMAND_DEADLINE = "deadline";

    /** Command keyword for adding an event task. */
    public static final String COMMAND_EVENT = "event";

    /** Command keyword for adding a todo task. */
    public static final String COMMAND_TODO = "todo";

    /** Command keyword for deleting a task. */
    public static final String COMMAND_DELETE = "delete";

    /** Command keyword for finding tasks by keyword. */
    public static final String COMMAND_FIND = "find";

    /** Command keyword for listing all tasks. */
    public static final String COMMAND_LIST = "list";

    /** Command keyword for marking a task as done. */
    public static final String COMMAND_MARK = "mark";

    /** Command keyword for unmarking a completed task. */
    public static final String COMMAND_UNMARK = "unmark";

    /** Command keyword for exiting the application. */
    public static final String COMMAND_BYE = "bye";

    /** Error message shown when a command is given without the required argument. */
    private static final String ERROR_MISSING_ARGUMENT =
            "What are you looking for, Padawan? Provide a keyword.";

    /**
     * Parses a full command string entered by the user and returns the matching Command.
     * The first word is treated as the action keyword; remaining text is passed as arguments.
     *
     * @param fullCommand The raw input string from the user.
     * @return The corresponding Command object to execute.
     * @throws SkywalkerException If the command is unrecognised or required arguments are missing.
     */
    public static Command parse(String fullCommand) throws SkywalkerException {
        String[] parts = fullCommand.trim().split(" ", 2);
        String action = parts[0].toLowerCase();
        boolean hasArgument = parts.length >= 2 && !parts[1].trim().isEmpty();

        return switch (action) {
            case COMMAND_LIST -> new ListCommand();
            case COMMAND_MARK -> {
                if (!hasArgument) {
                    throw new SkywalkerException(ERROR_MISSING_ARGUMENT);
                }
                yield new MarkCommand(fullCommand);
            }
            case COMMAND_UNMARK -> {
                if (!hasArgument) {
                    throw new SkywalkerException(ERROR_MISSING_ARGUMENT);
                }
                yield new UnmarkCommand(fullCommand);
            }
            case COMMAND_DELETE -> {
                if (!hasArgument) {
                    throw new SkywalkerException(ERROR_MISSING_ARGUMENT);
                }
                yield new DeleteCommand(parts[1]);
            }
            case COMMAND_TODO -> {
                if (!hasArgument) {
                    throw new SkywalkerException(SkywalkerUi.ERROR_EMPTY_TODO);
                }
                yield new AddTodoCommand(parts[1]);
            }
            case COMMAND_DEADLINE -> {
                if (!hasArgument) {
                    throw new SkywalkerException(SkywalkerUi.ERROR_EMPTY_DEADLINE);
                }
                yield new AddDeadlineCommand(parts[1]);
            }
            case COMMAND_EVENT -> {
                if (!hasArgument) {
                    throw new SkywalkerException(SkywalkerUi.ERROR_EMPTY_EVENT);
                }
                yield new AddEventCommand(parts[1]);
            }
            case COMMAND_FIND -> {
                if (!hasArgument) {
                    throw new SkywalkerException(ERROR_MISSING_ARGUMENT);
                }
                yield new FindCommand(parts[1]);
            }
            default -> throw new SkywalkerException("I find your lack of command clarity... disturbing.");
        };
    }
}
