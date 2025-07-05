// ● Repositorio de Libros (BookRepository)
//  ○ Debe ser una interfaz con métodos para:
//      ■ Agregar un libro (validando ISBN único).
//      ■ Eliminar un libro por ISBN.
//      ■ Buscar un libro por ISBN.
//      ■ Listar todos los libros.
//
//  ○ Implementar dos versiones usando:
//      ■ ArrayList (ArrayListBookRepository)
//      ■ Arreglo estático (ArrayBookRepository, con tamaño fijo y manejo de límites).


import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class ArrayListBookRepository implements BookRepository {

    private final ArrayList<Book> books;

    public ArrayListBookRepository(BookFactory bookFactory) {
        books = new ArrayList<Book>(bookFactory.create(10));
    }

    @Override
    public void add(Book newBook) throws KeyAlreadyExistsException{
        var exists = books.stream().anyMatch(book -> book.getIsbn().equals(newBook.getIsbn()));
        if (exists) {
            throw new KeyAlreadyExistsException();
        }
        newBook.setIsbn(UUID.randomUUID().toString());
        books.addLast(newBook);
    }

    @Override
    public Optional<Book> get(String isbn) {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst();
    }

    @Override
    public Collection<Book> getAll() {
        return books;
    }

    @Override
    public boolean delete(String isbn) {
        return books.removeIf(book -> book.getIsbn().equals(isbn));
    }
}
