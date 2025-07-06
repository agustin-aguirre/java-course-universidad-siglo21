package Models;

import Exceptions.InvalidBookFieldException;
import Validators.IntBiggerThanOrEqualToCeroValidator;
import Validators.IsbnValidator;
import Validators.StringNotNullNorEmptyValidator;
import Validators.Validator;

public class Book {

    private static final String ILLEGAL_ARGUMENT_TEMPLATE_MSG = "Passed value: %s to field: %s is not in valid";
    private static final String TO_STRING_TEMPLATE = "[Book: (ISBN=%s) (Title=%s) (Author=%s) (YearPublished=%d) (IsAvailable=%b)]";

    private String isbn;
    private String title;
    private String author;
    private int yearPublished;
    private boolean isAvailable = true;

    public Book(String isbn, String title, String author, int yearPublished) {
        setIsbn(isbn);
        setTitle(title);
        setAuthor(author);
        setYearPublished(yearPublished);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) throws IllegalArgumentException {
        assertFieldIsValid(isbn, new IsbnValidator(), "ISBN");
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws IllegalArgumentException {
        assertFieldIsValid(title, new StringNotNullNorEmptyValidator(), "Title");
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) throws IllegalArgumentException {
        assertFieldIsValid(author, new StringNotNullNorEmptyValidator(), "Author");
        this.author = author;
    }
    
    public int getYearPublished() {
        return yearPublished;
    }
    
    public void setYearPublished(int yearPublished) throws IllegalArgumentException{
        assertFieldIsValid(yearPublished, new IntBiggerThanOrEqualToCeroValidator(), "YearPublished");
        this.yearPublished = yearPublished;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    
    public String toString() {
        return String.format(TO_STRING_TEMPLATE, isbn, title, author, yearPublished, isAvailable);
    }

    protected <T> void assertFieldIsValid(T value, Validator<T> validator, String fieldName) {
        if (!validator.check(value)) {
            throw new InvalidBookFieldException(String.format(ILLEGAL_ARGUMENT_TEMPLATE_MSG, value.toString(), fieldName));
        }
    }
}
