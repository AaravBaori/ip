package skywalker.ui;

/**
 * Centralises all user-facing strings and output formatting for the Skywalker chatbot.
 * All UI constants and print utilities are static, so no instance is required.
 */
public class SkywalkerUi {

    /** Horizontal line used to wrap all printed messages. */
    public static final String LINE_BREAK =
            "\t______________________________________________________________________";

    /** Greeting shown when the chatbot starts. */
    public static final String WELCOME_MESSAGE =
            "\t  Greetings, young Padawan. I am Skywalker.\n"
                    + "\t  May the Force guide your tasks today.";

    /** Farewell shown when the user exits. */
    public static final String BYE_MESSAGE = "\t  May the Force be with you. Always.";

    /** Shown when the task list is empty. */
    public static final String MESSAGE_EMPTY_LIST =
            "\t  Your archives are incomplete! There are no tasks here.";

    /** Shown when a search returns no matching tasks. */
    public static final String MESSAGE_NO_RESULTS =
            "\t  The Force finds no missions matching your search.";

    /** Header line printed before listing all tasks. */
    public static final String MESSAGE_LIST_HEADER =
            "\t  Here are the missions in your Jedi Holocron:\n";

    /** Header line printed before listing search results. */
    public static final String MESSAGE_FIND_HEADER =
            "\t  Here are the missions you searched for:\n";

    /** Confirmation shown after a task is successfully added. */
    public static final String MESSAGE_ADD_SUCCESS =
            "\t  R2-D2 has logged this mission into the system:\n";

    /** Confirmation shown after a task is successfully deleted. */
    public static final String MESSAGE_DELETE_SUCCESS =
            "\t  R2-D2 has deleted this mission from the system:\n";

    /** Confirmation shown after a task is marked as done. */
    public static final String MESSAGE_MARK_SUCCESS =
            "\t  Impressive. Most impressive. I've marked:\n";

    /** Confirmation shown after a task is unmarked. */
    public static final String MESSAGE_UNMARK_SUCCESS =
            "\t  Patience! I've marked this task as not yet completed:\n";

    /** Error shown when the command format is invalid. */
    public static final String ERROR_INVALID_FORMAT =
            "The Jedi Code requires: [command] [task number]";

    /** Error shown when a non-integer is given where a task number is expected. */
    public static final String ERROR_NOT_INT =
            "That is not a valid coordinate. Please use an integer.";

    /** Error shown when trying to mark a task that is already done. */
    public static final String ERROR_ALREADY_DONE =
            "This mission was already completed. Great job, kid!";

    /** Error shown when trying to unmark a task that is not yet done. */
    public static final String ERROR_NOT_DONE_YET =
            "This task is still in progress. Stay on target!";

    /** Error shown when a todo description is empty. */
    public static final String ERROR_EMPTY_TODO =
            "A Jedi does not act without a description! Todo cannot be empty.";

    /** Error shown when a deadline description is empty. */
    public static final String ERROR_EMPTY_DEADLINE =
            "The deadline description cannot be lost in space!";

    /** Error shown when the /by delimiter is missing from a deadline command. */
    public static final String ERROR_MISSING_BY =
            "Use '/by' to tell me when this mission must end.";

    /** Error shown when an event description is empty. */
    public static final String ERROR_EMPTY_EVENT =
            "An event without a description? Impossible.";

    /** Error shown when /from or /to delimiters are missing from an event command. */
    public static final String ERROR_MISSING_EVENT_DELIMITERS =
            "Use /from and /to to define the timeline of this event!";

    /** Error shown when event start or end time fields are empty. */
    public static final String ERROR_EVENT_PART_EMPTY =
            " The event details are clouding my vision. Please fill all parts!";

    /**
     * Returns a formatted error message for when a task number is out of range.
     *
     * @param count The current number of tasks in the list.
     * @return Formatted error string indicating the valid range.
     */
    public static String getOutOfBoundsMessage(int count) {
        return "That mission does not exist in the Jedi archives! Choose 1 to " + count + ".";
    }

    /**
     * Returns the formatted string for the total task count.
     * Uses singular "mission" when only one task exists, plural otherwise.
     *
     * @param size The current number of tasks.
     * @return Formatted string with singular/plural handling.
     */
    public static String getTaskCountMessage(int size) {
        String taskWord = (size == 1) ? "mission" : "missions";
        return "\t  Now you have " + size + " " + taskWord + " in your records.";
    }

    /**
     * Prints a message to the console wrapped in horizontal line breaks.
     * Trims any trailing whitespace from the message to prevent extra blank lines.
     *
     * @param message The text to be displayed.
     */
    public static void printWithLines(String message) {
        System.out.println(LINE_BREAK);
        System.out.println(message.stripTrailing());
        System.out.println(LINE_BREAK);
    }
}