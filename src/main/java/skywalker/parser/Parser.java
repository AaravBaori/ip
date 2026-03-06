package skywalker.parser;

import skywalker.command.*;
import skywalker.exception.SkywalkerException;
import skywalker.ui.SkywalkerUi;

public class Parser {
    public static Command parse(String fullCommand) throws SkywalkerException {
        String[] parts = fullCommand.trim().split(" ", 2);
        String action = parts[0].toLowerCase();

        if (parts.length < 2 || parts[1].trim().isEmpty()) throw new SkywalkerException(SkywalkerUi.ERROR_EMPTY_EVENT);

        return switch (action) {
            case "bye" -> new ExitCommand();
            case "list" -> new ListCommand();
            case "mark" -> new MarkCommand(fullCommand);
            case "delete" -> new DeleteCommand(parts[1]);
            case "todo" -> new AddTodoCommand(parts[1]);
            case "deadline" -> new AddDeadlineCommand(parts[1]);
            case "event" -> new AddEventCommand(parts[1]);
            default -> throw new SkywalkerException("I find your lack of command clarity... disturbing.");
        };
    }
}
