package Filters;

import Models.Book;

public class PublicationYearFilter implements BookFilter {

    private final int year;

    public PublicationYearFilter(int year){
        this.year = year;
    }

    @Override
    public boolean check(Book book) {
        return book.getYearPublished() == year;
    }
}
