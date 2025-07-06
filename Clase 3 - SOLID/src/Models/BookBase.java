package Models;

import Validators.IntBiggerThanOrEqualToCeroValidator;
import Validators.StringNotNullNorEmptyValidator;
import Validators.Validator;

public class BookBase implements Book {

    private static final String ILLEGAL_ARGUMENT_TEMPLATE_MSG = "Passed value: %s to field: %s is not in valid";
    private static final String TO_STRING_TEMPLATE = "[Book: (ISBN=%s) (Title=%s) (Author=%s) (YearPublished=%d) (IsAvailable=%b)]";

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
        assertFieldIsValid(isbn, new StringNotNullNorEmptyValidator(), "ISBN");
        this.isbn = isbn;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) throws IllegalArgumentException {
        assertFieldIsValid(title, new StringNotNullNorEmptyValidator(), "Title");
        this.title = title;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public void setAuthor(String author) throws IllegalArgumentException {
        assertFieldIsValid(author, new StringNotNullNorEmptyValidator(), "Author");
        this.author = author;
    }

    @Override
    public int getYearPublished() {
        return yearPublished;
    }

    @Override
    public void setYearPublished(int yearPublished) throws IllegalArgumentException{
        assertFieldIsValid(yearPublished, new IntBiggerThanOrEqualToCeroValidator(), "YearPublished");
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
        return String.format(TO_STRING_TEMPLATE, isbn, title, author, yearPublished);
    }

    private <T> void assertFieldIsValid(T value, Validator<T> validator, String fieldName) {
        if (!validator.check(value)) {
            throw new IllegalArgumentException(String.format(ILLEGAL_ARGUMENT_TEMPLATE_MSG, value.toString(), fieldName));
        }
    }
}
