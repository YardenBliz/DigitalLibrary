package org.example.digitallibraryclient.model;

import java.io.Serializable;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class Book implements Serializable {
    private String name;
    private String author;
    private String id;
    private String genre;
    private double price;
    private int amount;
    public Book(String name, String author, String id ,String genre, double price, int amount) {
        this.name = name;
        this.author = author;
        this.id = id;
        this.genre = genre;
        this.price = price;
        this.amount = amount;
    }
    public Book() {
    }
    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}"; // Return an empty JSON object on error
        }
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", id='" + id + '\'' +
                ", genre='" + genre + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}


