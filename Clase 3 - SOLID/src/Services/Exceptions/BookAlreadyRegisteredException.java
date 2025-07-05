package Services.Exceptions;

public class BookAlreadyRegisteredException extends LibraryException {
    public BookAlreadyRegisteredException(String message) {
        super(message);
    }
}
