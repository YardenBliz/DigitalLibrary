package org.example.digitallibraryclient.model;

import com.google.gson.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookClient {

    private static final Gson gson = new GsonBuilder().registerTypeAdapter(Integer.class, (JsonDeserializer<Integer>) (json, typeOfT, context) -> {
        if(json.isJsonPrimitive()) {
            JsonPrimitive primitive = json.getAsJsonPrimitive();
            if(primitive.isNumber()) {
                return primitive.getAsNumber().intValue();
            }
        }
        throw new JsonParseException("Failed to deserialize integer value: " + json);
    }).create();

    public boolean addBook(Book book) throws IOException {
        Request request = new Request("addBook", createRequestMap("book", book));
        Response response = sendRequestToServer(request);
        // Process the response if needed
        return response.getMessage().equals("Book added successfully");
    }

    public Book getBookById(String idValue) throws IOException {
        Request request = new Request("getBookById", createRequestMap("id", idValue));
        Response response = sendRequestToServer(request);

        if (response != null && response.getData() != null) {
            JsonObject bookJson = gson.fromJson(gson.toJson(response.getData()), JsonObject.class);

            // Deserialize the book object using Gson
            return gson.fromJson(bookJson, Book.class);
        } else {
            // Handle case where response or book data is null
            return null;
        }
    }

    public boolean buyBook(String id, int amount) throws IOException {
        Request request = new Request("buyBook", createRequestMap("id", id, "amount", String.valueOf(amount)));
        Response response = sendRequestToServer(request);
        if (response == null) {
            throw new IOException("Received null response from server.");
        }

        return response.getMessage() != null && response.getMessage().equals("Book purchased successfully");
    }


    private Map<String, Object> createRequestMap(String... keyValues) {
        if (keyValues.length % 2 != 0) {
            throw new IllegalArgumentException("Key-values must be in pairs");
        }
        Map<String, Object> reqMap = new HashMap<>();
        for (int i = 0; i < keyValues.length; i += 2) {
            reqMap.put(keyValues[i], keyValues[i + 1]);
        }
        return reqMap;
    }


    public void deleteBook(String id) throws IOException {
        Request request = new Request("deleteBook", createRequestMap("id", id));
        sendRequestToServer(request);
    }

    public List<Book> searchBooksByName(String name) {
        Request request = new Request("searchBooksByName", createRequestMap("name", name));
        Response response = null;
        try {
            response = sendRequestToServer(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (response != null && response instanceof BookListResponse) {
            List<Book> books = ((BookListResponse) response).getData(); // Cast it properly to BookListResponse
            return books; // Return the list of books found
        } else {
            System.out.println("No books found or error occurred: " + response.getMessage());
            return Collections.emptyList(); // Return an empty list
        }
    }

    public List<Book> searchBooksByGenre(String genre) {
        Request request = new Request("searchBooksByGenre", createRequestMap("genre", genre));
        Response response = null;
        try {
            response = sendRequestToServer(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (response != null && response instanceof BookListResponse) {
            List<Book> books = ((BookListResponse) response).getData(); // Cast it properly to BookListResponse
            return books; // Return the list of books found
        } else {
            System.out.println("No books found or error occurred: " + response.getMessage());
            return Collections.emptyList(); // Return an empty list
        }
    }

    private Map<String, Object> createRequestMap(String name, Object value) {
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put(name, value);
        return reqMap;
    }

    private Response sendRequestToServer(Request request) throws IOException {
        try {
            Socket socket = new Socket("localhost", 8000);
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String jsonRequest = gson.toJson(request);
            System.out.println("Sending JSON request to server: " + jsonRequest);
            writer.println(jsonRequest); // Append a newline character
            writer.flush(); // Flush the output stream to ensure data is sent
            String jsonResponse = reader.readLine();

            // Check the action to determine the response type
            if ("searchBooksByName".equals(request.getAction())) {
                // Deserialize specifically into BookListResponse
                BookListResponse response = gson.fromJson(jsonResponse, BookListResponse.class);
                reader.close();
                writer.close();
                socket.close();
                return response;
            } else if ("searchBooksByGenre".equals(request.getAction())) {
                // Deserialize specifically into BookListResponse
                BookListResponse response = gson.fromJson(jsonResponse, BookListResponse.class);
                reader.close();
                writer.close();
                socket.close();
                return response;
            } else {
                // Default deserialization for other response types
                Response response = gson.fromJson(jsonResponse, Response.class);
                reader.close();
                writer.close();
                socket.close();
                return response;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }



}
