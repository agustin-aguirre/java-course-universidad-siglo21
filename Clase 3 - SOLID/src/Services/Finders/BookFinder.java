package Services.Finders;

import Filters.BookFilter;
import Models.Book;

import java.util.Collection;
import java.util.Optional;

public interface BookFinder {
    Optional<Book> findBookWithIsbn(String isbn);
    Collection<Book> filterBooks(BookFilter condition);
}
