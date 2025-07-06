package Services;

import Filters.BookFilter;
import Models.Book;

import java.util.Collection;

public interface BookFinder {
    Book findBookWithIsbn(String isbn);
    Collection<Book> filterBooks(BookFilter condition);
}
