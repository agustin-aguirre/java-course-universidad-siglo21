package Services.Exceptions;

public class DuplicatedBookException extends RuntimeException {
    public DuplicatedBookException(String message) {
        super(message);
    }
}
