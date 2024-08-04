package library.controller;

import library.model.Book;
import library.server.Request;
import library.server.Response;
import library.service.LibraryService;

import java.net.Socket;

public class GetBookController extends BookController {
    private final LibraryService libraryService;

    public GetBookController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @Override
    public Response handleRequest(Socket socket, Request request) {
        try {
            // Retrieve the book from the database or library.repository
            Book book = libraryService.getBookByID(request.getBody().get("id").toString());

            if (book != null) {
                // Create a response with the book data
                return new Response("Book found", book);
            } else {
                // Book not found, send appropriate message
                return new Response("Book not found", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Send error response
            return new Response("Error processing request", null);
        }
    }
}
