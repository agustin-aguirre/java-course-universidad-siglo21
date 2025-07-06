package Services.Finders;

import Exceptions.InvalidBookFieldException;
import Filters.BookFilter;
import Models.Book;
import Repositories.BookRepository;
import Validators.Validator;

import java.util.Collection;
import java.util.Optional;

public class SimpleBookFinder implements BookFinder {

    private final BookRepository bookRepo;
    private final Validator<String> isbnValidator;

    public SimpleBookFinder(BookRepository bookRepo, Validator<String> isbnValidator) {
        this.bookRepo = bookRepo;
        this.isbnValidator = isbnValidator;
    }

    @Override
    public Optional<Book> findBookWithIsbn(String isbn) {
        if (!isbnValidator.check(isbn)) {
            throw new InvalidBookFieldException("");
        }
        return bookRepo.get(isbn);
    }

    @Override
    public Collection<Book> filterBooks(BookFilter condition) {
        return bookRepo.getAll()
                .stream()
                .filter(condition::check)
                .toList();
    }
}
