import Exceptions.*;
import Factories.BookFactory;
import Factories.FixedDataSizeBookFactory;
import Models.Book;
import Printers.LibraryReportPrinter;
import Printers.ReportPrinter;
import Repositories.ArrayBookRepository;
import Repositories.ArrayListBookRepository;
import Repositories.BookRepository;
import Services.Finders.BookFinder;
import Services.Finders.SimpleBookFinder;
import Services.LoanManagers.LoanManager;
import Services.LoanManagers.SimpleLoanManager;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {
        var factory = new FixedDataSizeBookFactory();
        var printer = new LibraryReportPrinter();
        runDemoWithArray(factory, printer);
        printer.println("");
        runDemoWithArrayList(factory, printer);
    }

    private static void runDemoWithArray(BookFactory factory, ReportPrinter printer) {
        var bookRepo = new ArrayBookRepository(factory.create(50));
        var demoTitle = "===============Array===============";
        runDemoWithDependencies(factory, bookRepo, new SimpleBookFinder(bookRepo), new SimpleLoanManager(bookRepo), demoTitle, printer);
    }

    private static void runDemoWithArrayList(BookFactory factory, ReportPrinter printer) {
        var bookRepo = new ArrayListBookRepository(factory.create(50));
        var demoTitle = "=============ArrayList=============";
        runDemoWithDependencies(factory, bookRepo, new SimpleBookFinder(bookRepo), new SimpleLoanManager(bookRepo), demoTitle, printer);
    }

    private static void runDemoWithDependencies(BookFactory factory, BookRepository bookRepo, BookFinder bookFinder, LoanManager loanManager, String demoTitle, ReportPrinter printer) {
        var library = new DemoLibrary(bookRepo, bookFinder, loanManager);
        printer.println(demoTitle);
        runLibraryDemo(library, factory, printer);
        printer.println("===================================");
    }

    private static void runLibraryDemo(Library library, BookFactory factory, ReportPrinter printer) {
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