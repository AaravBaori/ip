package skywalker.exception;

public class SkywalkerException extends Exception{
    /**
     * Constructor for the Exception Class, prints out the message provided
     * 
     * @param message It is the custom message that is to be logged when the error occurs
     */
    public SkywalkerException(String message){
        super(message);
    }
}
