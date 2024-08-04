package org.example.digitallibraryclient.controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.example.digitallibraryclient.model.Book;

public class BookItem {
    private final SimpleStringProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty author;
    private final SimpleStringProperty genre;
    private final SimpleDoubleProperty price;
    private final SimpleIntegerProperty amount;

    public BookItem(String id, String name, String author, String genre, double price, int amount) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.author = new SimpleStringProperty(author);
        this.genre = new SimpleStringProperty(genre);
        this.price = new SimpleDoubleProperty(price);
        this.amount = new SimpleIntegerProperty(amount);
    }

    public static BookItem fromBook(Book book) {
        return new BookItem(
                book.getId(),
                book.getName(),
                book.getAuthor(),
                book.getGenre(),
                book.getPrice(),
                book.getAmount()
        );
    }

    // Getters for properties
    public String getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public String getAuthor() {
        return author.get();
    }

    public String getGenre() {
        return genre.get();
    }

    public double getPrice() {
        return price.get();
    }

    public int getAmount() {
        return amount.get();
    }

    // Properties for TableView bindings
    public SimpleStringProperty idProperty() {
        return id;
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleStringProperty authorProperty() {
        return author;
    }

    public SimpleStringProperty genreProperty() {
        return genre;
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public SimpleIntegerProperty amountProperty() {
        return amount;
    }
}
