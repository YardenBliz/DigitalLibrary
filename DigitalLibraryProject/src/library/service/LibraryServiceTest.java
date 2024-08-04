package library.service;
import library.model.Book;
import library.repository.DaoFileImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceTest {
    private LibraryService libraryService;
    private DaoFileImpl daoFileImpl;

    @BeforeEach
    public void setUp() {
        daoFileImpl = new DaoFileImpl(); // Assuming you have a default constructor or mock this
        libraryService = new LibraryService(daoFileImpl);
    }

    @Test
    public void testAddBook() {
        System.out.println("Running testAddBook...");
        String title = "The Great Gatsby";
        String author = "F. Scott Fitzgerald";
        String id = "9780743273565";
        String genre = "Novel";
        double price = 10.99;
        int amount = 10;

        // Adding a book
        boolean result = libraryService.addBook(title, author, id, genre, price, amount);
        System.out.println("Add book result: " + result);

        // Retrieve the added book
        Book book = libraryService.getBookByID(id);
        System.out.println("Retrieved book: " + book);

        // Assertions
        assertTrue(result, "The book should be added successfully.");
        assertNotNull(book, "Book should be retrievable after being added.");
        assertEquals(title, book.getName(), "Book title should match.");
        assertEquals(author, book.getAuthor(), "Book author should match.");
        assertEquals(id, book.getId(), "Book ID should match.");
        assertEquals(genre, book.getGenre(), "Book genre should match.");
        assertEquals(price, book.getPrice(), "Book price should match.");
        assertEquals(amount, book.getAmount(), "Book amount should match.");
    }

    @Test
    void testGetBookByID() {
        System.out.println("Running testGetBookByID...");
        String id = "12213";
        libraryService.addBook("Sample Book", "Author", id, "Fiction", 9.99, 5);

        Book book = libraryService.getBookByID(id);
        System.out.println("Retrieved book by ID: " + book);

        assertNotNull(book, "Book should be found by ID.");
        assertEquals("Sample Book", book.getName(), "Book name should match the expected value.");
    }

    @Test
    void testSearchBooksByName() {
        System.out.println("Running testSearchBooksByName...");
        libraryService.addBook("Name Rashi", "Author", "ID1", "Genre", 10.0, 1);

        List<Book> books = libraryService.searchBooksByName("Rashi");
        System.out.println("Books found by name: " + books);

        assertNotNull(books, "Books list should not be null.");
        assertFalse(books.isEmpty(), "Books list should not be empty.");
        assertEquals("Name Rashi", books.get(0).getName(), "Book name should match the expected value.");
    }

    @Test
    void testSearchBooksByGenre() {
        System.out.println("Running testSearchBooksByGenre...");
        libraryService.addBook("Book Name", "Author", "ID2", "Genre Type", 12.0, 2);

        List<Book> books = libraryService.searchBooksByGenre("Genre Type");
        System.out.println("Books found by genre: " + books);

        assertNotNull(books, "Books list should not be null.");
        assertFalse(books.isEmpty(), "Books list should not be empty.");
        assertEquals("Genre Type", books.get(0).getGenre(), "Book genre should match the expected value.");
    }
}
