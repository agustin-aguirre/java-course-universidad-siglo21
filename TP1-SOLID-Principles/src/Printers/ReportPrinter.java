package Printers;

import Exceptions.LibraryException;
import Models.Book;

public interface ReportPrinter {
    void println(String msg);

    void println(Book[] books);

    void printErr(LibraryException e);

    void printlnf(String template, Object... values);
}
