package library.controller;

import library.model.Book;
import library.server.BookListResponse;
import library.server.Request;
import library.server.Response;
import library.service.LibraryService;

import java.net.Socket;
import java.util.List;

public class GetBooksByNameController extends BookController {
    private final LibraryService libraryService;

    public GetBooksByNameController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @Override
    public Response handleRequest(Socket socket, Request request) {
        try {
            String name = (String) request.getBody().get("name");

            List<Book> searchResults = libraryService.searchBooksByName(name);

            System.out.println("Search results: " + searchResults); // Debugging output

            // Check if searchResults is empty
            if (searchResults.isEmpty()) {
                System.out.println("No books found for the name: " + name);
            }

            return new BookListResponse("Books found by name", searchResults);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle any exceptions and return an error response if needed
            return new BookListResponse("Error occurred during search", null);
        }
    }

}
