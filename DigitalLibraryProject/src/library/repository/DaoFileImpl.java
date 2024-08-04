package library.repository;

import library.model.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DaoFileImpl implements IDao<String,Book> {

    public static final String FILE_PATH = "Library.txt";

    public DaoFileImpl() {
        try {
            ObjectOutputStream oos;
            boolean emptyData = emptyData();
            if (emptyData) {
                oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
                oos.writeObject(new HashMap<String, Book>());
                oos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DaoFileImpl(String path) {
        try {
            ObjectOutputStream oos;
            boolean emptyData = emptyData();
            if (emptyData) {
                oos = new ObjectOutputStream(new FileOutputStream(path));
                oos.writeObject(new HashMap<String, Book>());
                oos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean add(Book book) throws IllegalArgumentException {
        if (book == null || book.getId() == null) {
            throw new IllegalArgumentException("Book or Book ID cannot be null");
        }
        boolean result = false;
        try {
            HashMap<String, Book> books;
            File file = new File(FILE_PATH);
            if (file.exists() && file.length() != 0) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
                    books = (HashMap<String, Book>) ois.readObject();
                }
            } else {
                books = new HashMap<>();
            }

            books.put(book.getId(), book);
            result = true;

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
                oos.writeObject(books);
                oos.flush();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }
    public void delete(String id) {
        if (id == null) {
            return;
        }

        try {
            HashMap<String, Book> books;
            File file = new File(FILE_PATH);
            if (file.exists() && file.length() != 0) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
                    books = (HashMap<String, Book>) ois.readObject();
                }
            } else {
                return;
            }

            boolean wasDeleted = books.remove(id) != null;

            if (wasDeleted) {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
                    oos.writeObject(books);
                    oos.flush();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Book getByID(String id) throws IllegalArgumentException {
        HashMap<String, Book> books = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH));
            books = (HashMap<String, Book>) ois.readObject();
            System.out.println("Books data: " + books);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return books != null ? books.get(id) : null;
    }
    @Override
    public boolean save(Book book) throws IllegalArgumentException {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH));
            HashMap<String, Book> books = (HashMap<String, Book>) ois.readObject();
            books.put(book.getId(), book);
            System.out.println("Saving book: " + book);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
            oos.writeObject(books);
            oos.flush();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public List<Book> getAll() {
        List<Book> resultList = new ArrayList<>();
        try {
            File file = new File(FILE_PATH);
            if (file.exists() && file.length() != 0) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
                    HashMap<String, Book> books = (HashMap<String, Book>) ois.readObject();
                    resultList.addAll(books.values());
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultList;
    }
    private boolean emptyData() throws IOException {
        File file = new File(FILE_PATH);
        return !file.exists() || file.length() == 0;
    }
}
