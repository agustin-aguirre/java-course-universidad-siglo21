package Services;

import Models.Book;
import Repositories.BookRepository;
import Services.Exceptions.BookNotFoundException;
import Services.Exceptions.BookAlreadyRegisteredException;
import Services.Exceptions.InvalidIsbnException;

public class SimpleLibraryEntriesManager implements LibraryEntriesManager {

    private final BookRepository bookRepo;

    public SimpleLibraryEntriesManager(BookRepository repo) {
        bookRepo = repo;
    }

    @Override
    public void addBook(Book newBook) throws InvalidIsbnException, BookAlreadyRegisteredException {
        var isbn = newBook.getIsbn();
        assertIsbnIsValid(isbn);
        if (bookExists(isbn)) {
            throw new BookAlreadyRegisteredException(String.format("Book with ISBN: %s is already registered.", isbn));
        }
        bookRepo.add(newBook);
    }

    @Override
    public void deleteBook(String isbn) throws InvalidIsbnException, BookNotFoundException {
        assertIsbnIsValid(isbn);
        if (!bookExists(isbn)) {
            throw new BookNotFoundException(String.format("Book with ISBN: %s couldn't be found.", isbn));
        }
        bookRepo.delete(isbn);
    }


    private boolean bookExists(String isbn) {
        return bookRepo.get(isbn).isPresent();
    }

    private void assertIsbnIsValid(String isbn) throws InvalidIsbnException {
        if(isbn == null || isbn.isBlank()) {
            throw new InvalidIsbnException("");
        }
    }
}
