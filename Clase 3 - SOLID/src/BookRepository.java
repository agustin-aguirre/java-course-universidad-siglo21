import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.Optional;

public interface BookRepository {
    void add(Book newBook);
    Optional<Book> get(String isbn);
    Iterable<Book> getAll();
    boolean delete(String isbn);
}


