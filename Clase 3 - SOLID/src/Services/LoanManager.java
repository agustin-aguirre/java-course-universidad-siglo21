package Services;

import Services.Exceptions.BookLendedException;
import Services.Exceptions.BookNotFoundException;

public interface LoanManager {
    void lendBook(String isbn) throws BookNotFoundException, BookLendedException;
    void returnBook(String isbn) throws BookNotFoundException;
}
