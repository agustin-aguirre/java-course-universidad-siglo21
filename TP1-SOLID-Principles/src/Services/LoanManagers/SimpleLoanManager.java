package Services.LoanManagers;

import Repositories.BookRepository;
import Exceptions.BookAlreadyLendedException;
import Exceptions.BookNotFoundException;

public class SimpleLoanManager implements LoanManager {

    private static final String BOOK_NOT_FOUND_TEMPLATE_MSG = "Book with isbn %s is not registered or does not exist.";
    private final static String ALREADY_LENDED_TEMPLATE_MSG = "Book with ISBN %s is already lended.";

    private final BookRepository bookRepo;

    public SimpleLoanManager(BookRepository repo) {
        this.bookRepo = repo;
    }

    @Override
    public void lendBook(String isbn) {
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
}
