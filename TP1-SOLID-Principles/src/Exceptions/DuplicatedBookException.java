package Exceptions;

public class DuplicatedBookException extends LibraryException {
    public DuplicatedBookException(String message) {
        super(message);
    }
}
