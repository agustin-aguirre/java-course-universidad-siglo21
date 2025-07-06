package Repositories;

import Exceptions.BookNotFoundException;
import Exceptions.DuplicatedBookException;
import Models.Book;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class ArrayListBookRepository implements BookRepository {

    private final ArrayList<Book> books;

    public ArrayListBookRepository(Collection<Book> initialBooks) {
        books = new ArrayList<>(initialBooks);
    }

    @Override
    public void add(Book newBook) {
        if (get(newBook.getIsbn()).isPresent()) {
            throw new DuplicatedBookException(String.format("Attempted to add a book with an already registered isbn: %s", newBook.getIsbn()));
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
            throw new BookNotFoundException(String.format("Book with isbn %s is not registered or does not exist.", isbn));
        }
    }
}
