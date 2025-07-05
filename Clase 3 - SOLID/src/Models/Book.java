package Models;

public interface Book {
    String getIsbn();
    void setIsbn(String isbn);

    String getTitle();
    void setTitle(String title);

    String getAuthor();
    void setAuthor(String author);

    int getYearPublished();
    void setYearPublished(int yearPublished);

    boolean isAvailable();
    void setIsAvailable(boolean isAvailable);
}
