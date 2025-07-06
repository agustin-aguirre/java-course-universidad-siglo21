package Repositories;

import Exceptions.BookNotFoundException;
import Exceptions.DuplicatedIsbnException;
import Factories.BookFactory;
import Models.Book;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class ArrayListBookRepository implements BookRepository {

    private final ArrayList<Book> books;

    public ArrayListBookRepository(BookFactory bookFactory) {
        books = new ArrayList<Book>(bookFactory.create(10));
    }

    @Override
    public void add(Book newBook) {
        if (get(newBook.getIsbn()).isPresent()) {
            throw new DuplicatedIsbnException("");
        }
        books.addLast(newBook);
    }

    @Override
    public Optional<Book> get(String isbn) {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst();
    }

    @Override
    public Collection<Book> getAll() {
        return books;
    }

    @Override
    public void delete(String isbn) {
        boolean success = books.removeIf(book -> book.getIsbn().equals(isbn));
        if (!success) {
            throw new BookNotFoundException("");
        }
    }
}
