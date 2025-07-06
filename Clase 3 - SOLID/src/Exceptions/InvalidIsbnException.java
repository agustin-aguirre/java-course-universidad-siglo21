package Exceptions;

public class InvalidIsbnException extends LibraryException {
  public InvalidIsbnException(String message) {
    super(message);
  }
}