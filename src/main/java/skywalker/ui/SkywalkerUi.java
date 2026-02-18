package skywalker.ui;

public class SkywalkerUi {
    /** UI Formatting. */
    public static final String LINE_BREAK = "\t______________________________________________________________________";

    /** General Bot Messages. */
    public static final String WELCOME_MESSAGE = "\t  Greetings, young Padawan. I am Skywalker.\n" +
            "\t  May the Force guide your tasks today.";
    public static final String BYE_MESSAGE = "\t  May the Force be with you. Always.";
    public static final String UNKNOWN_COMMAND = "\t  I find your lack of command clarity... disturbing. Try again?";

    /** Task List Messages. */
    public static final String MESSAGE_EMPTY_LIST = "\t  Your archives are incomplete! There are no tasks here.";
    public static final String MESSAGE_LIST_HEADER = "\t  Here are the missions in your Jedi Holocron:\n";
    public static final String MESSAGE_ADD_SUCCESS = "\t  R2-D2 has logged this mission into the system:\n";
    public static final String MESSAGE_MARK_SUCCESS = "\t  Impressive. Most impressive. I've marked:\n";
    public static final String MESSAGE_UNMARK_SUCCESS = "\t  Patience! I've marked this task as not yet completed:\n";

    /** Error Messages. */
    public static final String ERROR_INVALID_FORMAT = "\t  The Jedi Code requires: [command] [task number]";
    public static final String ERROR_NOT_INT = "\t  That is not a valid coordinate. Please use an integer.";
    public static final String ERROR_ALREADY_DONE = "\t  This mission was already completed. Great job, kid!";
    public static final String ERROR_NOT_DONE_YET = "\t  This task is still in progress. Stay on target!";
    public static final String ERROR_EMPTY_TODO = "\t  A Jedi does not act without a description! Todo cannot be empty.";
    public static final String ERROR_EMPTY_DEADLINE = "\t  The deadline description cannot be lost in space!";
    public static final String ERROR_MISSING_BY = "\t  Use '/by' to tell me when this mission must end.";
    public static final String ERROR_DEADLINE_PART_EMPTY = "\t  The deadline or time is missing from the nav-computer!";
    public static final String ERROR_EMPTY_EVENT = "\t  An event without a description? Impossible.";
    public static final String ERROR_MISSING_EVENT_DELIMITERS = "\t  Use /from and /to to define the timeline of this event!";
    public static final String ERROR_EVENT_TIME_ORDER = "\t  The start time must be earlier than the end time. Time travel is not for Jedi!";
    public static final String ERROR_EVENT_PART_EMPTY = "\t  The event details are clouding my vision. Please fill all parts!";

    /**
     * Returns the formatted string for the total task count.
     *
     * @param size The current number of tasks.
     * @return Formatted string with singular/plural handling.
     */
    public static String getTaskCountMessage(int size) {
        String taskWord = (size == 1) ? "mission" : "missions";
        return "\t  Now you have " + size + " " + taskWord + " in your records.";
    }

    /**
     * Prints a message wrapped in horizontal line breaks.
     *
     * @param message The text to be displayed.
     */
    public static void printWithLines(String message) {
        System.out.println(LINE_BREAK);
        System.out.println(message);
        System.out.println(LINE_BREAK);
    }
}