package Repositories;

import Models.Book;

import java.util.Collection;
import java.util.Optional;

public interface BookRepository {
    void add(Book newBook);
    Optional<Book> get(String isbn);
    Collection<Book> getAll();
    boolean delete(String isbn);
}