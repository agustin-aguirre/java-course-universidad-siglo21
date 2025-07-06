package Filters;

import Models.Book;

public class AuthorFilter implements BookFilter {

    private String author;

    public AuthorFilter(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean check(Book book) {
        return book.getAuthor().equals(author);
    }
}
