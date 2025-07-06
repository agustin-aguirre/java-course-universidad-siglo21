package Repositories;

import Exceptions.BookNotFoundException;
import Exceptions.DuplicatedBookException;
import Models.Book;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

public class ArrayBookRepository implements BookRepository {

    private Book[] books;
    private int booksVirtualLength = 0;
    private int firstEmptyPosition = 0;

    public ArrayBookRepository(Collection<Book> initialBooks) {
        var initialBooksArray = initialBooks.toArray(Book[]::new);
        int initialBooksArrayLength = initialBooksArray.length;
        books = Arrays.copyOf(initialBooksArray, initialBooksArrayLength + 10);
        booksVirtualLength = initialBooksArrayLength;
        firstEmptyPosition = initialBooksArrayLength;
    }

    @Override
    public void add(Book newBook) {
        if (get(newBook.getIsbn()).isPresent()) {
            throw new DuplicatedBookException(String.format("Attempted to add a book with an already registered isbn: %s", newBook.getIsbn()));
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
            Book currentBook = books[i];
            if (currentBook.getIsbn().equals(isbn)) {
                result = Optional.of(currentBook);
                break;
            }
        }
        return result;
    }

    @Override
    public Collection<Book> getAll() {
        return Arrays.stream(books).filter(Objects::nonNull).toList();
    }

    @Override
    public void delete(String isbn) {
        int targetIndex = indexOf(isbn);
        if (targetIndex == -1) {
            throw new BookNotFoundException("Book with isbn %s is not registered or does not exist.");
        }
        books[targetIndex] = null;
        booksVirtualLength -= 1;
        firstEmptyPosition = Math.min(targetIndex, firstEmptyPosition);
    }

    private int indexOf(String isbn) {
        int targetIndex = -1;
        int i = -1;
        while (i < booksVirtualLength) {
            i += 1;
            var current = books[i];
            if (current == null) {
                continue;
            }
            if (books[i].getIsbn().equals(isbn)) {
                targetIndex = i;
                break;
            }
        }
        return targetIndex;
    }
}
