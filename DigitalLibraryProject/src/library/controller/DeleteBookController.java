package library.controller;

import library.server.Request;
import library.server.Response;
import library.service.LibraryService;

import java.net.Socket;

public class DeleteBookController extends BookController {
    private final LibraryService libraryService;

    public DeleteBookController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @Override
    public Response handleRequest(Socket socket, Request request) {
        try {
            String bookId = (String) request.getBody().get("id"); // Get the ID of the book to delete
            if (bookId == null) {
                return new Response("Book ID is required", null);
            }
            libraryService.deleteBook(bookId); // Delete the book
            return new Response("Book deleted successfully", null);
        } catch (Exception e) {
            e.printStackTrace(); // Handle or log any exceptions
            return new Response("Failed to delete book: " + e.getMessage(), null);
        }
    }
}
