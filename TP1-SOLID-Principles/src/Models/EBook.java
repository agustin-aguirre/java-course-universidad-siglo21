package Models;

import java.util.Arrays;

public class EBook extends Book {

    private static final String[] SUPPORTED_FORMATS = { "pdf", "txt", "docx" };
    private String format;

    public EBook(String isbn, String title, String author, int yearPublished, String format) {
        super(isbn, title, author, yearPublished);
        setFormat(format);
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        assertStringFieldIsValid(format, "Format");
        assertFieldIsValid(format, (s) -> Arrays.stream(SUPPORTED_FORMATS).toList().contains(s.toLowerCase()), "Format");
        this.format = format;
    }

    @Override
    protected String getFieldsString() {
        return super.getFieldsString() + String.format(", Format=%s", getFormat());
    }
}