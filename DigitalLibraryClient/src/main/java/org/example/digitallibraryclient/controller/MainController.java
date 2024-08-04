package org.example.digitallibraryclient.controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.example.digitallibraryclient.model.Book;
import org.example.digitallibraryclient.model.BookClient;

import java.io.IOException;
import java.util.List;

public class MainController {
    @FXML
    private VBox resultsVBox;
    @FXML
    private ListView<String> searchResultsList;
    @FXML
    private TableColumn<BookItem, String> idColumn;
    @FXML
    private TableColumn<BookItem, String> nameColumn;
    @FXML
    private TableColumn<BookItem, String> authorColumn;
    @FXML
    private TableColumn<BookItem, String> genreColumn;
    @FXML
    private TableColumn<BookItem, Double> priceColumn;
    @FXML
    private TableColumn<BookItem, Integer> amountColumn;
    @FXML
    private TextField idField, nameField, authorField, amountField, priceField, genreField;
    @FXML
    private Pane functionalityPane;
    @FXML
    public Label searchResultLabel, deleteStatusLabel;

    private final BookClient bookClient;

    public MainController() {
        this.bookClient = new BookClient();
    }
    @FXML
    public void addBook() {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String author = authorField.getText().trim();
        String amount = amountField.getText().trim();
        String price = priceField.getText().trim();
        String genre = genreField.getText().trim();

        // Check if any of the fields are empty
        if (id.isEmpty() || name.isEmpty() || author.isEmpty() || amount.isEmpty() || price.isEmpty() || genre.isEmpty()) {
            showAlert("Error", "All fields are required.");
            return;
        }

        try {
            int m_amount = Integer.parseInt(amount);
            double m_price = Double.parseDouble(price);

            // Create the book object
            Book book = new Book(name, author, id, genre, m_price, m_amount);
            // Add the book
            if (bookClient.addBook(book)) {
                showAlert("Success", "Book added successfully!");
            } else {
                showAlert("Error", "Something went wrong");
            }

        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter valid numbers for Amount and Price.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void buyBook() throws IOException {
        String id = idField.getText().trim();
        String amountStr = amountField.getText().trim();

        if (id.isEmpty() || amountStr.isEmpty()) {
            showAlert("Error", "ID and Amount fields are required.");
            return;
        }

        try {
            int amount = Integer.parseInt(amountStr);
            boolean success = bookClient.buyBook(id, amount);
            if (success) {
                showAlert("Success", "Book purchased successfully.");
            } else {
                showAlert("Error", "Failed to purchase book.");
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid number for the amount.");
        }
    }

    @FXML
    public void deleteBook() throws IOException {
        String bookId = idField.getText();
        try {
            bookClient.deleteBook(bookId);
            deleteStatusLabel.setText("Deletion successful");
        } catch (IOException e) {
            e.printStackTrace(); // Handle or log any exceptions
            deleteStatusLabel.setText("Failed to delete book");
        }
    }

    @FXML
    public void getBookById() throws IOException {
        Book book = bookClient.getBookById(idField.getText());
        if (book != null) {
            nameField.setText(book.getName());
            authorField.setText(book.getAuthor());
            amountField.setText(Integer.toString(book.getAmount()));
            priceField.setText(Double.toString(book.getPrice()));
            genreField.setText(book.getGenre());
        } else {
            showAlert("Error", "Book not found.");
        }
    }

    @FXML
    public void searchBooksByName() throws IOException {
        String name = nameField.getText();
        List<Book> searchResults = bookClient.searchBooksByName(name);
        if (searchResults != null && !searchResults.isEmpty()) {
            populateSearchResults(searchResults);
        } else {
            showAlert("Error", "Results not found.");
        }
    }

    @FXML
    public void searchBooksByGenre() throws IOException {
        String genre = genreField.getText();
        List<Book> searchResults = bookClient.searchBooksByGenre(genre); // Implement this method in BookClient
        if (searchResults != null && !searchResults.isEmpty()) {
            populateSearchResults(searchResults); // Reuse the method for populating results
        } else {
            showAlert("Error", "No books found for the specified genre.");
        }
    }


    private void populateSearchResults(List<Book> results) {
        resultsVBox.getChildren().clear(); // Clear previous results
        Label headerLabel = new Label("Search Results:");
        headerLabel.getStyleClass().add("custom-list-header"); // Style for header
        resultsVBox.getChildren().add(headerLabel); // Add header to VBox

        for (Book book : results) {
            VBox bookItemBox = new VBox();
            bookItemBox.getStyleClass().add("custom-list"); // Apply custom list style

            // Create Labels for book information
            Label nameLabel = new Label("Name: " + book.getName());
            nameLabel.getStyleClass().add("custom-list-label"); // Style for item labels

            Label authorLabel = new Label("Author: " + book.getAuthor());
            authorLabel.getStyleClass().add("custom-list-label");

            Label genreLabel = new Label("Genre: " + book.getGenre());
            genreLabel.getStyleClass().add("custom-list-label");

            Label priceLabel = new Label("Price: " + book.getPrice());
            priceLabel.getStyleClass().add("custom-list-label");

            Label amountLabel = new Label("Amount: " + book.getAmount());
            amountLabel.getStyleClass().add("custom-list-label");

            // Add labels to the VBox for this book item
            bookItemBox.getChildren().addAll(nameLabel, authorLabel, genreLabel, priceLabel, amountLabel);
            resultsVBox.getChildren().add(bookItemBox); // Add the book item VBox to the results VBox
        }

        // Add some spacing at the end
        resultsVBox.getChildren().add(new Label(" ")); // Just to add some space
    }



    @FXML
    public void openAddBook() {
        loadScreenIntoPane("/org/example/digitallibraryclient/addbook_tab.fxml");
    }

    @FXML
    public void openDeleteBook() {
        loadScreenIntoPane("/org/example/digitallibraryclient/deletebook_tab.fxml");
    }

    @FXML
    public void openBuyBook() {
        loadScreenIntoPane("/org/example/digitallibraryclient/buyingbook_tab.fxml");
    }

    @FXML
    public void openSearchByID() {
        loadScreenIntoPane("/org/example/digitallibraryclient/get_by_id.fxml");
    }

    @FXML
    public void openSearchByName() {
        loadScreenIntoPane("/org/example/digitallibraryclient/get_by_name.fxml");
    }

    @FXML
    public void openSearchByGenre() {
        loadScreenIntoPane("/org/example/digitallibraryclient/get_by_genre.fxml");
    }

    private void loadScreenIntoPane(String fxmlFile) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Pane root = fxmlLoader.load();
            functionalityPane.getChildren().setAll(root);
        } catch (IOException e) {
            showAlert("Error", "Failed to load screen: " + fxmlFile);
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
