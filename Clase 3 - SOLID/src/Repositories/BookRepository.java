package Repositories;

import Exceptions.BookNotFoundException;
import Exceptions.DuplicatedIsbnException;
import Filters.BookFilter;
import Models.Book;

import java.util.Collection;
import java.util.Optional;

public interface BookRepository {
    void add(Book newBook);
    Optional<Book> get(String isbn);
    Collection<Book> getAll();
    Collection<Book> filter(BookFilter condition);
    void delete(String isbn);
}