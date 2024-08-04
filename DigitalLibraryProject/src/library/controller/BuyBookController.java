package library.controller;

import library.server.Request;
import library.server.Response;
import library.service.LibraryService;

import java.net.Socket;
import java.util.Map;

public class BuyBookController extends BookController {
    private final LibraryService libraryService;

    public BuyBookController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @Override
    public Response handleRequest(Socket socket, Request request) {
        try {
            // Print the request body for debugging
            System.out.println("Request Body: " + request.getBody());

            Map<String, Object> body = request.getBody();
            if (body == null) {
                throw new IllegalArgumentException("Request body is null");
            }

            // Check if the 'id' and 'amount' keys are present
            if (!body.containsKey("id")) {
                return new Response("Error: 'id' key is missing in the request body", false);
            }
            if (!body.containsKey("amount")) {
                return new Response("Error: 'amount' key is missing in the request body", false);
            }

            // Extract the book ID and amount from the request
            String id = body.get("id").toString();
            String amountStr = body.get("amount").toString();

            // Check if the amount string is a valid number
            if (!isNumeric(amountStr)) {
                return new Response("Error: 'amount' must be a number", false);
            }

            int amount = Integer.parseInt(amountStr);

            // Call the buyBook method from the LibraryService
            boolean result = libraryService.buyBook(id, amount);

            // Create and return the response based on the result
            if (result) {
                return new Response("Book purchased successfully", true);
            } else {
                return new Response("Error: Book could not be purchased", false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Send error response
            return new Response("Error occurred during purchase", false);
        }
    }

    // Helper method to check if a string is numeric
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
