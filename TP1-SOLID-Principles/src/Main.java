import Exceptions.*;
import Factories.BookFactory;
import Factories.EBookFactory;
import Factories.FixedDataSizeBookFactory;
import Models.Book;
import Printers.LibraryReportPrinter;
import Printers.ReportPrinter;
import Repositories.ArrayBookRepository;
import Repositories.ArrayListBookRepository;
import Repositories.BookRepository;
import Services.Finders.SimpleBookFinder;
import Services.LoanManagers.SimpleLoanManager;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {
        var bookFactory = new FixedDataSizeBookFactory();
        var ebookFactory = new EBookFactory();
        var printer = new LibraryReportPrinter();

        // demo with array repository
        var arrayDemoLibrary = createDemoLibrary(new ArrayBookRepository(bookFactory.create(50)));
        runLibraryDemo(arrayDemoLibrary, ebookFactory, printer, "===============Array===============");

        // demo with arrayList repository
        var arrayListDemoLibrary = createDemoLibrary(new ArrayListBookRepository(ebookFactory.create(50)));
        runLibraryDemo(arrayListDemoLibrary, bookFactory, printer, "=============ArrayList=============");
    }

    private static Library createDemoLibrary(BookRepository bookRepo) {
        return new DemoLibrary(
                bookRepo,
                new SimpleBookFinder(bookRepo),
                new SimpleLoanManager(bookRepo)
        );
    }

    private static void runLibraryDemo(Library library, BookFactory factory, ReportPrinter printer, String header) {
        printer.println(header);

        printer.println("Showing all books from Jorge Luis Borges:");
        var borgesBooks = library.getBooksFromAuthor("Jorge Luis Borges").toArray(Book[]::new);
        printer.println(borgesBooks);

        printer.println("Lending the first 3 of them...");
        library.lendBook(borgesBooks[0].getIsbn());
        library.lendBook(borgesBooks[1].getIsbn());
        library.lendBook(borgesBooks[2].getIsbn());
        var lendedBooks = library.getBooksByAvailability(false).toArray(Book[]::new);
        printer.println(lendedBooks);

        var book = borgesBooks[0];
        printer.printlnf("Attempting to lend book with ISBN: %s (the first one):", book.getIsbn());
        try {
            library.lendBook(book.getIsbn());
        }
        catch (BookAlreadyLendedException e) {
            printer.printErr(e);
        }

        printer.println("Rerturning the book now...");
        library.returnBook(book.getIsbn());
        printer.println(book.toString());

        printer.println("Deleting all but the first three...");
        String deletedBookIsbn = borgesBooks[3].getIsbn();
        for (Book b: Arrays.stream(borgesBooks).skip(3).toList()) {
            library.deleteBook(b.getIsbn());
        }
        borgesBooks = library.getBooksFromAuthor("Jorge Luis Borges").toArray(Book[]::new);
        printer.println(borgesBooks);

        printer.printlnf("Attempting to delete an already deleted isbn: %s", deletedBookIsbn);
        try {
            library.deleteBook(deletedBookIsbn);
        }
        catch (BookNotFoundException e) {
            printer.printErr(e);
        }

        Book newRandomBook = factory.create();
        printer.printlnf("Adding new book: %s", newRandomBook.toString());
        library.addBook(newRandomBook);
        printer.println("Trying to add the same book...");
        try {
            library.addBook(newRandomBook);
        }
        catch (DuplicatedBookException e) {
            printer.printErr(e);
        }

        printer.println("Trying to create books with invalid field values:");
        printer.println("\tISBN empty: ");
        trySetField(newRandomBook, b -> b::setIsbn, "", printer);
        printer.println("\tAuthor empty: ");
        trySetField(newRandomBook, b -> b::setAuthor, "", printer);
        printer.println("\tTitle empty: ");
        trySetField(newRandomBook, b -> b::setTitle, "", printer);
        printer.println("\tPublishing Year = -1: ");
        trySetField(newRandomBook, b -> b::setYearPublished, -1, printer);

        printer.println("===================================\n");
    }

    private static <T> void trySetField(Book book, Function<Book, Consumer<T>> objectSetter, T value, ReportPrinter printer) {
        try {
            var setter = objectSetter.apply(book);
            setter.accept(value);
        }
        catch (InvalidBookFieldException e) {
            printer.printErr(e);
        }
    }
}