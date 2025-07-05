package Services;

import Models.Book;
import Services.Exceptions.BookAlreadyRegisteredException;
import Services.Exceptions.BookNotFoundException;
import Services.Exceptions.InvalidIsbnException;

public interface LibraryEntriesManager {
    void addBook(Book newBook) throws InvalidIsbnException, BookAlreadyRegisteredException;
    void deleteBook(String isbn) throws InvalidIsbnException, BookNotFoundException;
}
