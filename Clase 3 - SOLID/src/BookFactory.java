import java.util.ArrayList;

public interface BookFactory {
    ArrayList<Book> Create(int amount) throws IllegalArgumentException;
}
