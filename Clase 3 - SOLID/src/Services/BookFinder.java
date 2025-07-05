package Services;

import Filters.BookFilter;
import Models.Book;
import Repositories.BookRepository;

import java.util.Collection;

public class BookFinder {

    private final BookRepository repo;

    public BookFinder(BookRepository bookRepository) {
        repo = bookRepository;
    }

    public Collection<Book> getBooksWithAuthor(String author) {
        return filterBooks(book -> book.getAuthor().equals(author));
    }

    public Collection<Book> getBooksPublishedAtYear(int publicationYear) {
        return filterBooks(book -> book.getYearPublished() == publicationYear);
    }

    private Collection<Book> filterBooks(BookFilter condition) {
        return repo.getAll()
                .stream()
                .filter(condition::check)
                .toList();
    }
}

