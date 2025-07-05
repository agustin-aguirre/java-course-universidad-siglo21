package Services;
// ● Gestión de Préstamos (Services.LoanManager)
// ○ Debe permitir:
//        ■Prestar un libro (marcar como no disponible).
//        ■Devolver un libro (marcar como disponible).
//        ■Validar reglas (ej: no prestar un libro ya prestado).

import Repositories.BookRepository;
import Services.Exceptions.BookAlreadyLendedException;
import Services.Exceptions.BookNotFoundException;
import Services.Exceptions.LibraryException;

public class SimpleLoanManager implements LoanManager {

    private final BookRepository bookRepo;

    private final static String NOT_FOUND_TEMPLATE_MSG = "Book with ISBN %s couldn't be found.";
    private final static String ALREADY_LENDED_TEMPLATE_MSG = "Book with ISBN %s is already lended.";

    public SimpleLoanManager(BookRepository repo) {
        this.bookRepo = repo;
    }

    @Override
    public void lendBook(String isbn) throws LibraryException {
        bookRepo.get(isbn).ifPresentOrElse(
            book -> {
                if (!book.isAvailable()) {
                    throw new BookAlreadyLendedException(String.format(ALREADY_LENDED_TEMPLATE_MSG, book.getIsbn()));
                }
                book.setIsAvailable(false);
            },
            () -> {
                throw new BookNotFoundException(String.format(NOT_FOUND_TEMPLATE_MSG, isbn));
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
                throw new BookNotFoundException(String.format(NOT_FOUND_TEMPLATE_MSG, isbn));
            }
        );
    }
}
