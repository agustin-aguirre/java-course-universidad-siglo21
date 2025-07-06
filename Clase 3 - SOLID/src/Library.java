import Models.Book;

import java.util.Collection;
import java.util.Optional;

public interface Library {
    void addBook(Book newBook);

    void deleteBook(String isbn);

    void lendBook(String isbn);

    void returnBook(String isbn);

    Optional<Book> findBookWithIsbn(String isbn);

    Collection<Book> getBooksFromAuthor(String author);

    Collection<Book> getBooksByAvailability(boolean areAvailable);

    Collection<Book> getBooksPublishedAtYear(int year);
}
