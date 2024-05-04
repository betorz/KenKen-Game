package main.domain.classes.exceptions;

/**
 * The ExceptionUser class represents an exception that is thrown when there is an error related to a user.
 */
public class ExceptionUser extends Exception {
    
    /**
     * Constructs a new ExceptionUser object with the specified error message.
     *
     * @param message the error message
     */
    public ExceptionUser(String message) {
        super("\nUser exception: "+ message);
    }

}
