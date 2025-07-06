package Models;

public class BookBase implements Book {

    private String isbn;
    private String title;
    private String author;
    private int yearPublished;
    private boolean isAvailable = true;

    public BookBase(String isbn, String title, String author, int yearPublished) {
        setIsbn(isbn);
        setTitle(title);
        setAuthor(author);
        setYearPublished(yearPublished);
    }

    @Override
    public String getIsbn() {
        return isbn;
    }

    @Override
    public void setIsbn(String isbn) throws IllegalArgumentException {
        assertStringFieldIsValid(isbn, "ISBN");
        this.isbn = isbn;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) throws IllegalArgumentException {
        assertStringFieldIsValid(title, "Title");
        this.title = title;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public void setAuthor(String author) throws IllegalArgumentException {
        assertStringFieldIsValid(author, "Author");
        this.author = author;
    }

    @Override
    public int getYearPublished() {
        return yearPublished;
    }

    @Override
    public void setYearPublished(int yearPublished) throws IllegalArgumentException{
        if (yearPublished < 0) {
            throw new IllegalArgumentException("Year cannot be less than 0");
        }
        this.yearPublished = yearPublished;
    }

    @Override
    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        String template = "[Models.Book: (ISBN=%s) (Title=%s) (Author=%s) (Year Published=%d)";
        return String.format(template, isbn, title, author, yearPublished);
    }


    private void assertStringFieldIsValid(String value, String fieldName) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException(String.format("Passed value for %s is not in a valid format", fieldName));
        }
    }
}
