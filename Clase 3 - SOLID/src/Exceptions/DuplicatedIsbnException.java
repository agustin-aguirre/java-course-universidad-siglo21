package Exceptions;

public class DuplicatedIsbnException extends LibraryException {
    public DuplicatedIsbnException(String message) {
        super(message);
    }
}
