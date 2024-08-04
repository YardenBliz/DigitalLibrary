package org.example.digitallibraryclient.model;

import java.util.List;

public class BookListResponse extends Response {
    private List<Book> books;

    public BookListResponse(String message, List<Book> books) {
        super(message, books);
        this.books = books;
    }

    @Override
    public List<Book> getData() {
        return books;
    }
}
