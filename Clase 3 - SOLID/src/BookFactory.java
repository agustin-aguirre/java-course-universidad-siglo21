import java.util.ArrayList;

public interface BookFactory {
    ArrayList<Book> create(int amount) throws IllegalArgumentException;
}
