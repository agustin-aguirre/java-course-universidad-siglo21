package Filters;

import Models.Book;

public class AuthorFilter implements BookFilter {

    private final String author;

    public AuthorFilter(String author) {
        this.author = author;
    }

    @Override
    public boolean check(Book book) {
        return book.getAuthor().equals(author);
    }
}
