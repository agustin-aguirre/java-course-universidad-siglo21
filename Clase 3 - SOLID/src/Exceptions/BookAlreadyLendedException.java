package Exceptions;

public class BookAlreadyLendedException extends LibraryException {
    public BookAlreadyLendedException(String message) {
        super(message);
    }
}
