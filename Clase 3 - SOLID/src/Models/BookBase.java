package Models;// ● Libro (Models.Book)
//  ○ Atributos:
//      ■ ISBN (String, único)
//      ■ Título (String)
//      ■ Autor (String)
//      ■ Año de publicación (int)
//      ■ Disponibilidad (boolean)
//  ○ Métodos:
//      ■Constructor con validación de campos obligatorios.
//      ■Getters y Setters con validaciones (ej: año no puede ser negativo).


public class BookBase implements Book {

    private String isbn;
    private String title;
    private String author;
    private int yearPublished;
    private boolean isAvailable = true;


    public BookBase(String isbn, String title, String author, int yearPublished) {
        setIsbn(isbn);
        setTitle(title);
        setAuthor(author);
        setYearPublished(yearPublished);
    }


    @Override
    public String getIsbn() {
        return isbn;
    }

    @Override
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String getTitle() {

        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public int getYearPublished() {
        return yearPublished;
    }

    @Override
    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    @Override
    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        String template = "[Models.Book: (ISBN=%s) (Title=%s) (Author=%s) (Year Published=%d)";
        return String.format(template, isbn, title, author, yearPublished);
    }
}
