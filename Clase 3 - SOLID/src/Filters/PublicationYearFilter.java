package Filters;

import Models.Book;

public class PublicationYearFilter implements BookFilter {

    private int year;

    public PublicationYearFilter(int year){
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    @Override
    public boolean check(Book book) {
        return book.getYearPublished() == year;
    }
}
