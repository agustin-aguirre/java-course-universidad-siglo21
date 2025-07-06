package Services.BookPersistors;

import Models.Book;
import Repositories.BookRepository;

public class SimpleBookPersistor implements Services.BookPersistors.BookPersistor {
    private final BookRepository bookRepo;

    public SimpleBookPersistor(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    public void addBook(Book newBook) {
        bookRepo.add(newBook);
    }

    public void deleteBook(String isbn) {
        bookRepo.delete(isbn);
    }
}
