package library.controller;

import library.model.Book;
import library.server.BookListResponse;
import library.server.Request;
import library.server.Response;
import library.service.LibraryService;

import java.net.Socket;
import java.util.List;

import library.model.Book;
import library.server.BookListResponse;
import library.server.Request;
import library.server.Response;
import library.service.LibraryService;

import java.net.Socket;
import java.util.List;

public class GetBooksByGenreController extends BookController {
    private final LibraryService libraryService;

    public GetBooksByGenreController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @Override
    public Response handleRequest(Socket socket, Request request) {
        try {
            String genre = (String) request.getBody().get("genre");

            List<Book> searchResults = libraryService.searchBooksByGenre(genre);

            System.out.println("Search results: " + searchResults); // Debugging output

            // Check if searchResults is empty
            if (searchResults.isEmpty()) {
                System.out.println("No books found for the genre: " + genre);
            }

            return new BookListResponse("Books found by genre", searchResults);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle any exceptions and return an error response if needed
            return new BookListResponse("Error occurred during search", null);
        }
    }

}
