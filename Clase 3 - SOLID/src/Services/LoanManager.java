package Services;

import Services.Exceptions.LibraryException;

public interface LoanManager {
    void lendBook(String isbn) throws LibraryException;
    void returnBook(String isbn);
}
