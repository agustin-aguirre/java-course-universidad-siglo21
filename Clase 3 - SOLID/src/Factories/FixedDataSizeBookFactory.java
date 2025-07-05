package Factories;

import Models.Book;

import java.util.ArrayList;
import java.util.UUID;


public class FixedDataSizeBookFactory implements BookFactory {

    private final int dataSize = 10;

    private final String[] authors = {
        "Gabriel García Márquez",
        "Jane Austen",
        "Haruki Murakami",
        "Isabel Allende",
        "George Orwell",
        "J.K. Rowling",
        "Jorge Luis Borges",
        "Ernest Hemingway",
        "Franz Kafka",
        "Mario Vargas Llosa",
    };

    private final String[] titles = {
        "Cien años de soledad",
        "Orgullo y prejuicio",
        "Kafka en la orilla",
        "La casa de los espíritus",
        "1984",
        "Harry Potter y la piedra filosofal",
        "El viejo y el mar",
        "Ficciones",
        "La metamorfosis",
        "La ciudad y los perros"
    };

    private final int[] publicationYears = {
        1967,
        1813,
        2002,
        1982,
        1949,
        1997,
        1952,
        1944,
        1915,
        1963
    };

    public ArrayList<Book> create(int amount) throws IllegalArgumentException {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be less than 0");
        }
        var result = new ArrayList<Book>(amount);
        for (int i = 0; i < amount; i++) {
            int index = (i + dataSize) % dataSize;
            var book = createBookWithDataAtIndex(index);
            result.addLast(book);
        }
        return result;
    }

    private Book createBookWithDataAtIndex(int index) {
        return new Book(UUID.randomUUID().toString(), titles[index], authors[index], publicationYears[index]);
    }
}
