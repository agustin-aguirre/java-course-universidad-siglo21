package Filters;

import Models.Book;

public class AvailabilityFilter implements BookFilter {
    private final boolean shouldBeAvailable;

    public AvailabilityFilter(boolean isAvailable) {
        this.shouldBeAvailable = isAvailable;
    }

    @Override
    public boolean check(Book book) {
        return book.isAvailable() == shouldBeAvailable;
    }
}
