package skywalker.parser;

import skywalker.command.*;
import skywalker.exception.SkywalkerException;
import skywalker.ui.SkywalkerUi;

public class Parser {
    public static Command parse(String fullCommand) throws SkywalkerException {
        String[] parts = fullCommand.trim().split(" ", 2);
        String action = parts[0].toLowerCase();

        return switch (action) {
            case "bye" -> {
                yield new ExitCommand();
            }
            case "list" -> new ListCommand();
            case "mark" ->{
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new SkywalkerException("What are you looking for, Padawan? Provide a keyword.");
                }
                yield new MarkCommand(fullCommand);
            }
            case "delete" ->{
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new SkywalkerException("What are you looking for, Padawan? Provide a keyword.");
                }
                yield new DeleteCommand(parts[1]);
            }
            case "todo" ->{
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new SkywalkerException("What are you looking for, Padawan? Provide a keyword.");
                }
                yield new AddTodoCommand(parts[1]);
            }
            case "deadline" ->{
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new SkywalkerException("What are you looking for, Padawan? Provide a keyword.");
                }
                yield new AddDeadlineCommand(parts[1]);
            }
            case "event" ->{
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new SkywalkerException("What are you looking for, Padawan? Provide a keyword.");
                }
                yield new AddEventCommand(parts[1]);
            }
            case "find" -> {
                if (parts[1].trim().isEmpty()) {
                    throw new SkywalkerException("What are you looking for, Padawan? Provide a keyword.");
                }
                yield new FindCommand(parts[1]);
            }
            case "unmark" -> {
                if (parts[1].trim().isEmpty()) {
                    throw new SkywalkerException("What are you looking for, Padawan? Provide a keyword.");
                }
                yield new UnmarkCommand(fullCommand);
            }
            default -> throw new SkywalkerException("I find your lack of command clarity... disturbing.");
        };
    }
}
