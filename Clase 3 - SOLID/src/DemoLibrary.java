import Filters.AuthorFilter;
import Filters.AvailabilityFilter;
import Filters.PublicationYearFilter;
import Models.Book;
import Repositories.BookRepository;
import Services.Finders.BookFinder;
import Services.LoanManagers.LoanManager;

import java.util.Collection;
import java.util.Optional;

public class DemoLibrary implements Library {

    private final BookRepository bookRepo;
    private final BookFinder bookFinder;
    private final LoanManager loanManager;

    public DemoLibrary(BookRepository bookRepo, BookFinder bookFinder, LoanManager loanManager) {
        this.bookRepo = bookRepo;
        this.bookFinder = bookFinder;
        this.loanManager = loanManager;
    }

    @Override
    public void addBook(Book newBook) {
        bookRepo.add(newBook);
    }

    @Override
    public void deleteBook(String isbn) {
        bookRepo.delete(isbn);
    }

    @Override
    public void lendBook(String isbn) {
        loanManager.lendBook(isbn);
    }

    @Override
    public void returnBook(String isbn) {
        loanManager.returnBook(isbn);
    }

    @Override
    public Optional<Book> findBookWithIsbn(String isbn) {
        return bookFinder.findBookWithIsbn(isbn);
    }

    @Override
    public Collection<Book> getBooksFromAuthor(String author) {
        return bookFinder.filterBooks(new AuthorFilter(author));
    }

    @Override
    public Collection<Book> getBooksByAvailability(boolean areAvailable) {
        return bookFinder.filterBooks(new AvailabilityFilter(areAvailable));
    }

    @Override
    public Collection<Book> getBooksPublishedAtYear(int year) {
        return bookFinder.filterBooks(new PublicationYearFilter(year));
    }
}
