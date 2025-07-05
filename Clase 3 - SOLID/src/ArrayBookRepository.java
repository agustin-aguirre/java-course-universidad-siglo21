import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class ArrayBookRepository implements BookRepository {

    private Book[] books;
    private int booksVirtualLength = 0;
    private int firstEmptyPosition = 0;

    public ArrayBookRepository(BookFactory bookFactory) {
        books = bookFactory.create(10).toArray(new Book[10]);
    }

    @Override
    public void add(Book newBook) {
        if (booksVirtualLength == books.length - 1) {
            books = Arrays.copyOf(books, booksVirtualLength + 100);
        }
        newBook.setIsbn(UUID.randomUUID().toString());
        books[firstEmptyPosition] = newBook;
        booksVirtualLength += 1;
        for (int i = (firstEmptyPosition + 1); i < books.length; i++) {
            if (books[i] == null) {
                firstEmptyPosition = i;
                break;
            }
        }
    }

    @Override
    public Optional<Book> get(String isbn) {
        Optional<Book> result = Optional.empty();
        for (int i = 0; i < booksVirtualLength; i++) {
            if (books[i].getIsbn().equals(isbn)) {
                result = Optional.of(books[i]);
                break;
            }
        }
        return result;
    }

    @Override
    public Collection<Book> getAll() {
        return Arrays.asList(books);
    }

    @Override
    public boolean delete(String isbn) throws LibraryException {
        int targetIndex = -1;
        for (int i = 0; i < booksVirtualLength; i++) {
            if (books[i].getIsbn().equals(isbn)) {
                targetIndex = i;
                break;
            }
        }
        if (targetIndex == -1) return false;
        books[targetIndex] = null;
        booksVirtualLength -= 1;
        firstEmptyPosition = Math.min(targetIndex, firstEmptyPosition);
        return true;
    }
}
