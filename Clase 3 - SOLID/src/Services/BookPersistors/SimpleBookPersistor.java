package Services.BookPersistors;

import Exceptions.BookNotFoundException;
import Exceptions.DuplicatedBookException;
import Models.Book;
import Repositories.BookRepository;
import Repositories.Exceptions.DuplicatedKeyException;
import Repositories.Exceptions.KeyNotFoundException;

public class SimpleBookPersistor implements Services.BookPersistors.BookPersistor {
    private final BookRepository bookRepo;

    private static final String DUPLICATED_BOOK_TEMPLATE_MSG = "Attempted to add a book with isbn %s, wich is already registered.";
    private static final String BOOK_NOT_FOUND_TEMPLATE_MSG = "Book with isbn %s is not registered or does not exist.";

    public SimpleBookPersistor(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    public void addBook(Book newBook) {
        try {
            bookRepo.add(newBook);
        }
        catch (DuplicatedKeyException e) {
            throw new DuplicatedBookException(String.format(DUPLICATED_BOOK_TEMPLATE_MSG, newBook.getIsbn()));
        }
    }

    public void deleteBook(String isbn) {
        try {
            bookRepo.delete(isbn);
        }
        catch (KeyNotFoundException e) {
            throw new BookNotFoundException(String.format(BOOK_NOT_FOUND_TEMPLATE_MSG, isbn));
        }
    }
}
