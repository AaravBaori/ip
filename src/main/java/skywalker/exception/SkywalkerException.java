package skywalker.exception;

/**
 * Represents a domain-specific exception for the Skywalker chatbot.
 * Thrown when user input is invalid or an expected application error occurs.
 */

public class SkywalkerException extends Exception{
    /**
     * Constructs a new SkywalkerException with the specified error message.
     *
     * @param message The message describing the error to display to the user.
     */
    public SkywalkerException(String message){
        super(message);
    }
}
