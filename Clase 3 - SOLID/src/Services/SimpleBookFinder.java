package Services;

import Filters.BookFilter;
import Models.Book;
import Repositories.BookRepository;

import java.util.Collection;

public class SimpleBookFinder implements BookFinder {

    private final BookRepository bookRepo;

    public SimpleBookFinder(BookRepository bookRepository) {
        bookRepo = bookRepository;
    }

    public Collection<Book> getBooksWithAuthor(String author) {
        return filterBooks(book -> book.getAuthor().equals(author));
    }

    public Collection<Book> getBooksPublishedAtYear(int publicationYear) {
        return filterBooks(book -> book.getYearPublished() == publicationYear);
    }

    public Collection<Book> getBooksByAvailability(boolean available) {
        return filterBooks(book -> book.isAvailable() == available);
    }

    public Collection<Book> filterBooks(BookFilter condition) {
        return bookRepo.getAll()
                .stream()
                .filter(condition::check)
                .toList();
    }
}

