package Factories;

import Models.Book;
import Models.EBook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.UUID;

public class EBookFactory implements BookFactory {
    private final int dataSize = 10;
    private final Random random = new Random();

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

    private final String[] supportedFormats = { "pdf", "txt", "docx" };

    public Book create() {
        return createEBookWithDataAtIndex(random.nextInt(dataSize));
    }

    public Collection<Book> create(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be less than 0");
        }
        var result = new ArrayList<Book>(amount);
        for (int i = 0; i < amount; i++) {
            int index = (i + dataSize) % dataSize;
            var book = createEBookWithDataAtIndex(index);
            result.addLast(book);
        }
        return result;
    }

    private EBook createEBookWithDataAtIndex(int index) {
        return new EBook(
                UUID.randomUUID().toString(),
                titles[index],
                authors[index],
                publicationYears[index],
                supportedFormats[random.nextInt(supportedFormats.length)]
        );
    }
}
