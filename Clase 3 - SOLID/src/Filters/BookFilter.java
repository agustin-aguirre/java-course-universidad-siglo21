package Filters;

import Models.Book;

@FunctionalInterface
public interface BookFilter {
    boolean check(Book books);
}
