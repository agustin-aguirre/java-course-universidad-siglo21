package Services;// ● Gestión de Préstamos (Services.LoanManager)
// ○ Debe permitir:
//        ■Prestar un libro (marcar como no disponible).
//        ■Devolver un libro (marcar como disponible).
//        ■Validar reglas (ej: no prestar un libro ya prestado).

import Repositories.BookRepository;
import Services.Exceptions.LibraryException;

public class LoanManager {

    private final BookRepository repo;

    public LoanManager(BookRepository repo) {
        this.repo = repo;
    }

    public void LendBook(String isbn) throws LibraryException {
        repo.get(isbn).ifPresentOrElse(
            book -> {
                if (!book.isAvailable()) {
                    throw new LibraryException(String.format("Models.Book with ISBN %s is already lended.", book.getIsbn()));
                }
                book.setIsAvailable(false);
            },
            () -> {
                throw new LibraryException(String.format("Models.Book with ISBN %s couldn't be found.", isbn));
            }
        );
    }

    public void ReturnBook(String isbn) {
        repo.get(isbn).ifPresentOrElse(
            book -> {
                if (book.isAvailable()) {
                    throw new LibraryException(String.format("Attempting to return Models.Book with ISBN %s but it was never lended.", book.getIsbn()));
                }
                book.setIsAvailable(false);
            },
            () -> {
                throw new LibraryException(String.format("Models.Book with ISBN %s couldn't be found.", isbn));
            }
        );
    }
}
