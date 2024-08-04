package library.server;

import library.model.Book;
import java.util.List;

public class BookListResponse extends Response {
    private List<Book> books;

    public BookListResponse(String message, List<Book> books) {
        super(message, books); // Ensure the base constructor is being called with the right parameters
        this.books = books;
    }

    @Override
    public List<Book> getData() {
        return books; // This should return the list of books
    }
}
