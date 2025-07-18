package Printers;

import Exceptions.LibraryException;
import Models.Book;

import java.util.Collection;

public interface ReportPrinter {
    void println(String msg);

    void println(Book[] books);

    void println(Collection<Book> books);

    void printErr(LibraryException e);

    void printlnf(String template, Object... values);
}
