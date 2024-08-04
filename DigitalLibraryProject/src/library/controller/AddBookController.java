package library.controller;



import com.google.gson.*;
import library.server.Request;
import library.server.Response;
import library.service.LibraryService;

import java.net.Socket;

public class AddBookController extends BookController {
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(Integer.class, (JsonDeserializer<Integer>) (json, typeOfT, context) -> {
        if (json.isJsonPrimitive()) {
            JsonPrimitive primitive = json.getAsJsonPrimitive();
            if (primitive.isNumber()) {
                return primitive.getAsNumber().intValue();
            }
        }
        throw new JsonParseException("Failed to deserialize integer value: " + json);
    }).create();

    private final LibraryService libraryService;

    public AddBookController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @Override
    public Response handleRequest(Socket socket, Request request) {
        try {
            JsonObject bodyObject = gson.fromJson(gson.toJson(request.getBody()), JsonObject.class);
            JsonObject bookObject = bodyObject.getAsJsonObject("book");

            String name = bookObject.get("name").getAsString();
            String author = bookObject.get("author").getAsString();
            String id = bookObject.get("id").getAsString();
            String genre = bookObject.get("genre").getAsString();
            double price = bookObject.get("price").getAsInt();
            int amount = bookObject.get("amount").getAsInt();

            libraryService.addBook(name, author, id, genre ,price ,amount);

            return new Response("Book added successfully", null);
        } catch (Exception e) {
            e.printStackTrace();
            // Send error response
            return null;
        }
    }
}
