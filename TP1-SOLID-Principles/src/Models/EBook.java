package Models;

import Validators.FormatValidator;

public class EBook extends Book {

    private String format;

    public EBook(String isbn, String title, String author, int yearPublished, String format) {
        super(isbn, title, author, yearPublished);
        setFormat(format);
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        assertFieldIsValid(format, new FormatValidator(), "Format");
        this.format = format;
    }

    @Override
    protected String getFieldsString() {
        return super.getFieldsString() + String.format(", Format=%s", getFormat());
    }
}