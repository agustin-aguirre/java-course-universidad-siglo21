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

    public SimpleBookFinder(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public Optional<Book> findBookWithIsbn(String isbn) {
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
