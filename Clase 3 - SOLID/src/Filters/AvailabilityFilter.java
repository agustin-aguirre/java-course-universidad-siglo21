package Filters;

import Models.Book;

public class AvailabilityFilter implements BookFilter {
    private boolean shouldBeAvailable;

    public AvailabilityFilter(boolean isAvailable) {
        this.shouldBeAvailable = isAvailable;
    }

    public boolean isShouldBeAvailable() {
        return shouldBeAvailable;
    }

    public void setShouldBeAvailable(boolean shouldBeAvailable) {
        this.shouldBeAvailable = shouldBeAvailable;
    }

    @Override
    public boolean check(Book book) {
        return book.isAvailable() == shouldBeAvailable;
    }
}
