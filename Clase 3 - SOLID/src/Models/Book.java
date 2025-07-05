package Models;

public interface Book {
    String getIsbn();
    void setIsbn(String isbn) throws IllegalArgumentException;

    String getTitle();
    void setTitle(String title) throws IllegalArgumentException;

    String getAuthor();
    void setAuthor(String author) throws IllegalArgumentException;

    int getYearPublished();
    void setYearPublished(int yearPublished) throws IllegalArgumentException;

    boolean isAvailable();
    void setIsAvailable(boolean isAvailable);
}
