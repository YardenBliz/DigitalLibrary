package library.service;

import algorithm.IAlgoStringMatching;
import algorithm.KMPStringMatching;
import library.model.Book;
import library.repository.DaoFileImpl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LibraryService {
    private final DaoFileImpl libraryRepository;

    public LibraryService(DaoFileImpl libraryRepository) {
        this.libraryRepository = libraryRepository;

    }

    public boolean addBook(String name, String author, String id, String genre, double price, int amount) {
        Book book = new Book(name, author, id, genre, price, amount);
        boolean addResult = libraryRepository.add(book);
        return addResult; // Return the result of the add operation
    }


    public List<Book> getBooks() {
        return libraryRepository.getAll();
    }
    public Book getBookByID(String id) {
        return libraryRepository.getByID(id);

    }
    public void deleteBook(String id) {
        libraryRepository.delete(id);
    }
    public List<Book> searchBooksByName(String name) {
        if (name == null || name.isEmpty()) {
            return Collections.emptyList(); // Return an empty list if the search term is null or empty
        }

        IAlgoStringMatching stringMatchAlgo = new KMPStringMatching();
        List<Book> allbooks = libraryRepository.getAll();

        return allbooks.stream()
                .filter(book -> book.getName() != null &&
                        stringMatchAlgo.findPattern(book.getName().toLowerCase(), name.toLowerCase()) != -1)
                .collect(Collectors.toList());
    }

    public List<Book> searchBooksByGenre(String genre) {
        if (genre == null || genre.isEmpty()) {
            return Collections.emptyList(); // Return an empty list if the search term is null or empty
        }

        IAlgoStringMatching stringMatchAlgo = new KMPStringMatching();
        List<Book> allBooks = libraryRepository.getAll();

        return allBooks.stream()
                .filter(book -> book.getGenre() != null &&
                        stringMatchAlgo.findPattern(book.getGenre().toLowerCase(), genre.toLowerCase()) != -1)
                .collect(Collectors.toList());
    }

    public boolean buyBook(String id, int amount) {
        Book book = libraryRepository.getByID(id);
        if (book != null) {
            System.out.println("Book found: " + book);
            System.out.println("Current amount: " + book.getAmount());
            if (book.getAmount() >= amount) {
                book.buyingBook(amount);
                System.out.println("Updated amount: " + book.getAmount());
                boolean saveResult = libraryRepository.save(book);
                System.out.println("Save result: " + saveResult);
                return saveResult;
            } else {
                System.out.println("Not enough stock to buy");
            }
        } else {
            System.out.println("Book not found");
        }
        return false;
    }

}

