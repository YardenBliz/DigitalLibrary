package library.model;

import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID = 6080880048565398576L;
    private String name;
    private String author;
    private String id;
    private String genre;
    private double price;
    private int amount;
    //#######################################################Plus stock all project ########################################################
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

    public int buyingBook(int minusAmount) {
        this.amount -= minusAmount;
        return this.amount;
    }
}



