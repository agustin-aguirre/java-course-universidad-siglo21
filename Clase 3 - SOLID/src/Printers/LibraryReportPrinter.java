package Printers;

import Models.Book;
import Exceptions.LibraryException;

import java.util.Arrays;

public class LibraryReportPrinter implements Printers.ReportPrinter {

    @Override
    public void println(String msg) {
        System.out.println(msg);
    }

    @Override
    public void println(Book[] books) {
        println(Arrays.toString(books));
    }

    @Override
    public void printErr(LibraryException e) {
        println(String.format("There was an error: %s", e.getMessage()));
    }

    @Override
    public void printlnf(String template, Object... values) {
        println(String.format(template, values));
    }
}
