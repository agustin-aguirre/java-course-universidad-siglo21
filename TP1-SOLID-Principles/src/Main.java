import Exceptions.*;

import Filters.AuthorFilter;
import Filters.AvailabilityFilter;
import Models.Book;
import Models.EBook;
import Printers.LibraryReportPrinter;
import Repositories.ArrayBookRepository;
import Repositories.ArrayListBookRepository;
import Services.Finders.SimpleBookFinder;
import Services.LoanManagers.SimpleLoanManager;

public class Main {

    public static void main(String[] args) {
        Book[] books = {
                new Book("1", "Cien años de soledad", "Gabriel García Márquez", 1967),
                new EBook("2", "Orgullo y prejuicio", "Jane Austen", 1813, "pdf"),
                new Book("3", "Kafka en la orilla", "Haruki Murakami", 2002),
                new EBook("4", "La casa de los espíritus", "Isabel Allende", 1982, "pdf"),
                new Book("5", "1984", "George Orwell", 1949),
                /// ///
                new EBook("6", "Harry Potter y la piedra filosofal", "J.K. Rowling", 1997, "txt"),
                new Book("7", "El viejo y el mar", "Ernest Hemingway", 1952),
                new EBook("8", "Ficciones", "Jorge Luis Borges", 1944, "docx"),
                new Book("9", "La metamorfosis", "Franz Kafka", 1915),
                new EBook("10", "La ciudad y los perros", "Mario Vargas Llosa", 1963, "docx"),
        };

        var printer = new LibraryReportPrinter();

        printer.println("\n* REPOSITORIES ADD ELEMENTS DEMO:");
        var arrayRepo = new ArrayBookRepository();
        var arraylistRepo = new ArrayListBookRepository();

        for (int i = 0; i < 5; i++) {
            arrayRepo.add(books[i]);
        }
        printer.println(arrayRepo.getAll());
        try {
            arrayRepo.add(books[0]);
        }
        catch (DuplicatedBookException e) {
            printer.printErr(e);
        }

        for (int i = 5; i < 10; i++) {
            arraylistRepo.add(books[i]);
        }
        printer.println(arraylistRepo.getAll());
        try {
            arraylistRepo.add(books[5]);
        }
        catch (DuplicatedBookException e) {
            printer.printErr(e);
        }


        printer.println("\n* DELETE DEMO:");
        printer.println("** ARRAY REPO");
        arrayRepo.delete("1");
        printer.println(arrayRepo.getAll());
        try {
            arrayRepo.delete("10");
        }
        catch (BookNotFoundException e) {
            printer.printErr(e);
        }

        printer.println("** ARRAYLIST REPO");
        arraylistRepo.delete("6");
        printer.println(arraylistRepo.getAll());
        try {
            arraylistRepo.delete("1");
        }
        catch (BookNotFoundException e) {
            printer.printErr(e);
        }

        printer.println("** GET BY ISBN:");
        printer.println(arrayRepo.get("5").toString());
        printer.println(arraylistRepo.get("10").toString());


        printer.println("\n* LOANS");
        printer.println("** ARRAY REPO:");
        var loanManager1 = new SimpleLoanManager(arrayRepo);
        printer.println("Lending book with isbn 2");
        loanManager1.lendBook("2");
        try {
            loanManager1.lendBook("2");
        }
        catch (BookAlreadyLendedException e) {
            printer.printErr(e);
        }
        try {
            loanManager1.lendBook("10");
        }
        catch (BookNotFoundException e) {
            printer.printErr(e);
        }

        printer.println("** ARRAYLIST REPO:");
        var loanManager2 = new SimpleLoanManager(arraylistRepo);
        printer.println("Lending book with isbn 7");
        loanManager2.lendBook("7");
        try {
            loanManager2.lendBook("7");
        }
        catch (BookAlreadyLendedException e) {
            printer.printErr(e);
        }
        try {
            loanManager2.lendBook("1");
        }
        catch (BookNotFoundException e) {
            printer.printErr(e);
        }


        printer.println("\n* ADVANCED FILTERING");
        printer.println("** ARRAY REPO:");
        var finder1 = new SimpleBookFinder(arrayRepo);
        printer.println("** Book with isbn 3:");
        printer.println(finder1.findBookWithIsbn("3").get().toString());
        printer.println("** All George Orwell books:");
        printer.println(finder1.filterBooks(new AuthorFilter("George Orwell")));
        printer.println("** All available books:");
        printer.println(finder1.filterBooks(new AvailabilityFilter(true)));
        printer.println("** All lended books:");
        printer.println(finder1.filterBooks(new AvailabilityFilter(false)));

        printer.println("** ARRAYLIST REPO:");
        var finder2 = new SimpleBookFinder(arraylistRepo);
        printer.println("** Book with isbn 7:");
        printer.println(finder2.findBookWithIsbn("7").get().toString());
        printer.println("** All Jorge Luis Borges books:");
        printer.println(finder2.filterBooks(new AuthorFilter("Jorge Luis Borges")));
        printer.println("** All available books:");
        printer.println(finder2.filterBooks(new AvailabilityFilter(true)));
        printer.println("** All lended books:");
        printer.println(finder2.filterBooks(new AvailabilityFilter(false)));
    }
}