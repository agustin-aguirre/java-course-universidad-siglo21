package Models;

import Exceptions.InvalidBookFieldException;

import java.util.function.Predicate;

public class Book {

    protected static final String ILLEGAL_ARGUMENT_TEMPLATE_MSG = "Passed value: %s to field: %s is not in valid";
    protected static final String TO_STRING_TEMPLATE = "ISBN=%s, Title=%s, Author=%s, YearPublished=%d, IsAvailable=%b";

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
        assertStringFieldIsValid(isbn, "ISBN");
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws IllegalArgumentException {
        assertStringFieldIsValid(title, "Title");
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) throws IllegalArgumentException {
        assertStringFieldIsValid(author, "Author");
        this.author = author;
    }
    
    public int getYearPublished() {
        return yearPublished;
    }
    
    public void setYearPublished(int yearPublished) throws IllegalArgumentException{
        assertFieldIsValid(yearPublished, (year) -> year >= 0, "YearPublished");
        this.yearPublished = yearPublished;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String toString() {
        return String.format("%s{%s}", this.getClass().getSimpleName(), getFieldsString());
    }

    protected void assertStringFieldIsValid(String value, String fieldName) {
        assertFieldIsValid(value, (s) -> s != null && !s.isEmpty(), fieldName);
    }

    protected <T> void assertFieldIsValid(T value, Predicate<T> predicate, String fieldName) {
        if (!predicate.test(value)) {
            throw new InvalidBookFieldException(String.format(ILLEGAL_ARGUMENT_TEMPLATE_MSG, value.toString(), fieldName));
        }
    }

    protected String getFieldsString() {
        return String.format(TO_STRING_TEMPLATE, isbn, title, author, yearPublished, isAvailable);
    }
}
