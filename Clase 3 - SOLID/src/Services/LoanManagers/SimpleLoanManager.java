package Services.LoanManagers;

import Exceptions.InvalidIsbnException;
import Repositories.BookRepository;
import Exceptions.BookAlreadyLendedException;
import Exceptions.BookNotFoundException;
import Validators.Validator;

public class SimpleLoanManager implements LoanManager {

    private static final String BOOK_NOT_FOUND_TEMPLATE_MSG = "Book with isbn %s is not registered or does not exist.";
    private final static String ALREADY_LENDED_TEMPLATE_MSG = "Book with ISBN %s is already lended.";

    private final BookRepository bookRepo;
    private Validator<String> isbnValidator;

    public SimpleLoanManager(BookRepository repo, Validator<String> isbnValidator) {
        this.isbnValidator = isbnValidator;
        this.bookRepo = repo;
    }

    @Override
    public void lendBook(String isbn) {
        assertIsbnIsValid(isbn);
        bookRepo.get(isbn).ifPresentOrElse(
            book -> {
                if (!book.isAvailable()) {
                    throw new BookAlreadyLendedException(String.format(ALREADY_LENDED_TEMPLATE_MSG, book.getIsbn()));
                }
                book.setIsAvailable(false);
            },
            () -> {
                throw new BookNotFoundException(String.format(BOOK_NOT_FOUND_TEMPLATE_MSG, isbn));
            }
        );
    }

    @Override
    public void returnBook(String isbn) {
        assertIsbnIsValid(isbn);
        bookRepo.get(isbn).ifPresentOrElse(
            book -> {
                if (!book.isAvailable()) {
                    book.setIsAvailable(true);
                }
            },
            () -> {
                throw new BookNotFoundException(String.format(BOOK_NOT_FOUND_TEMPLATE_MSG, isbn));
            }
        );
    }

    private void assertIsbnIsValid(String isbn) {
        if (!isbnValidator.check(isbn)) {
            throw new InvalidIsbnException("");
        }
    }
}
