package Factories;

import Models.Book;

import java.util.Collection;

public interface BookFactory {
    Book create();
    Collection<Book> create(int amount);
}
