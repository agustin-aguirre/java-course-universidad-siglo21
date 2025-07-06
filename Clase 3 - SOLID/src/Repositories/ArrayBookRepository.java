package Repositories;

import Exceptions.BookNotFoundException;
import Exceptions.DuplicatedIsbnException;
import Factories.BookFactory;
import Filters.BookFilter;
import Models.Book;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

public class ArrayBookRepository implements BookRepository {

    private Book[] books;
    private int booksVirtualLength = 0;
    private int firstEmptyPosition = 0;

    public ArrayBookRepository(BookFactory bookFactory) {
        books = bookFactory.create(10).toArray(Book[]::new);
    }

    @Override
    public void add(Book newBook) {
        if (get(newBook.getIsbn()).isPresent()) {
            throw new DuplicatedIsbnException("");
        }

        books[firstEmptyPosition] = newBook;
        booksVirtualLength += 1;

        if (booksVirtualLength < books.length) {
            for (int i = (firstEmptyPosition + 1); i < books.length; i++) {
                if (books[i] == null) {
                    firstEmptyPosition = i;
                    break;
                }
            }
        }
        else {
            books = Arrays.copyOf(books, books.length +10);
            firstEmptyPosition = booksVirtualLength;
        }
    }

    @Override
    public Optional<Book> get(String isbn) {
        Optional<Book> result = Optional.empty();
        for (int i = 0; i < booksVirtualLength; i++) {
            if (books[i].getIsbn().equals(isbn)) {
                result = Optional.of(books[i]);
                break;
            }
        }
        return result;
    }

    @Override
    public Collection<Book> getAll() {
        return Arrays.asList(books);
    }

    @Override
    public Collection<Book> filter(BookFilter condition) {
        return getAll().stream()
                .filter(condition::check)
                .toList();
    }

    @Override
    public void delete(String isbn) {
        int targetIndex = indexOf(isbn);
        if (targetIndex == -1) {
            throw new BookNotFoundException("");
        }
        books[targetIndex] = null;
        booksVirtualLength -= 1;
        firstEmptyPosition = Math.min(targetIndex, firstEmptyPosition);
    }

    private int indexOf(String isbn) {
        int targetIndex = -1;
        for (int i = 0; i < booksVirtualLength; i++) {
            if (books[i].getIsbn().equals(isbn)) {
                targetIndex = i;
                break;
            }
        }
        return targetIndex;
    }
}
