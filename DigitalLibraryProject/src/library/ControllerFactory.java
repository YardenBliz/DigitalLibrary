package library;
import library.controller.*;
import library.service.LibraryService;

public class ControllerFactory {
    private final LibraryService libraryService;

    public ControllerFactory(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    public BookController getController(String action) {
        return switch (action) {
            case "addBook" -> new AddBookController(libraryService);
            case "getBookById" -> new GetBookController(libraryService);
            case "buyBook" -> new BuyBookController(libraryService);
            case "deleteBook" -> new DeleteBookController(libraryService);
            case "searchBooksByName" -> new GetBooksByNameController(libraryService);
            case "searchBooksByGenre" -> new GetBooksByGenreController(libraryService);
            default -> throw new IllegalArgumentException("Unsupported action: " + action);
        };
    }
}
