import Exceptions.BookAlreadyLendedException;
import Exceptions.BookNotFoundException;
import Exceptions.DuplicatedBookException;
import Exceptions.LibraryException;
import Factories.BookFactory;
import Factories.FixedDataSizeBookFactory;
import Models.Book;
import Repositories.ArrayBookRepository;
import Repositories.ArrayListBookRepository;
import Repositories.BookRepository;
import Services.Finders.BookFinder;
import Services.Finders.SimpleBookFinder;
import Services.LoanManagers.LoanManager;
import Services.LoanManagers.SimpleLoanManager;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        var factory = new FixedDataSizeBookFactory();
        runDemoWithArray(factory);
        println("");
        runDemoWithArrayList(factory);
    }

    private static void runDemoWithArray(BookFactory factory) {
        var bookRepo = new ArrayBookRepository(factory.create(50));
        var demoTitle = "===============Array===============";
        runDemoWithDependencies(factory, bookRepo, new SimpleBookFinder(bookRepo), new SimpleLoanManager(bookRepo), demoTitle);
    }

    private static void runDemoWithArrayList(BookFactory factory) {
        var bookRepo = new ArrayListBookRepository(factory.create(50));
        var demoTitle = "=============ArrayList=============";
        runDemoWithDependencies(factory, bookRepo, new SimpleBookFinder(bookRepo), new SimpleLoanManager(bookRepo), demoTitle);
    }

    private static void runDemoWithDependencies(BookFactory factory, BookRepository bookRepo, BookFinder bookFinder, LoanManager loanManager, String demoTitle) {
        var library = new DemoLibrary(bookRepo, bookFinder, loanManager);
        println(demoTitle);

        println("Showing all books from Jorge Luis Borges:");
        var borgesBooks = library.getBooksFromAuthor("Jorge Luis Borges").toArray(Book[]::new);
        println(borgesBooks);

        println("Lending the first 3 of them...");
        library.lendBook(borgesBooks[0].getIsbn());
        library.lendBook(borgesBooks[1].getIsbn());
        library.lendBook(borgesBooks[2].getIsbn());
        var lendedBooks = library.getBooksByAvailability(false).toArray(Book[]::new);
        println(lendedBooks);

        var book = borgesBooks[0];
        printlnf("Attempting to lend book with ISBN: %s (the first one):", book.getIsbn());
        try {
            library.lendBook(book.getIsbn());
        }
        catch (BookAlreadyLendedException e) {
            printErr(e);
        }

        println("Rerturning the book now...");
        library.returnBook(book.getIsbn());
        println(book.toString());

        println("Deleting all but the first three...");
        String deletedBookIsbn = borgesBooks[3].getIsbn();
        for (Book b: Arrays.stream(borgesBooks).skip(3).toList()) {
            library.deleteBook(b.getIsbn());
        }
        borgesBooks = library.getBooksFromAuthor("Jorge Luis Borges").toArray(Book[]::new);
        println(borgesBooks);

        printlnf("Attempting to delete an already deleted isbn: %s", deletedBookIsbn);
        try {
            library.deleteBook(deletedBookIsbn);
        }
        catch (BookNotFoundException e) {
            printErr(e);
        }

        Book newRandomBook = factory.create();
        printlnf("Adding new book: %s", newRandomBook.toString());
        library.addBook(newRandomBook);
        println("Trying to add the same book...");
        try {
            library.addBook(newRandomBook);
        }
        catch (DuplicatedBookException e) {
            printErr(e);
        }

        println("===================================");
    }

    private static void println(String msg) {
        System.out.println(msg);
    }

    private static void println(Book[] books) {
        println(Arrays.toString(books));
    }

    private static void printErr(LibraryException e) {
        println(String.format("There was an error: %s", e.getMessage()));
    }

    private static void printlnf(String template, Object... values) {
        println(String.format(template, values));
    }
}


// Subir a youtube ?
// Entrega: Armar un video en Youtube explicando el código fuente y ejecutando
// el mismo. En la explicación es necesario que expliquen los conceptos teóricos
// empleados. También deben enviar el código.
// Los que manejan el concepto GIT lo pueden hacer por ahí, sino en archivos está bien.
// Plazo de entrega máximo  lunes 7/07, 9.30 hs