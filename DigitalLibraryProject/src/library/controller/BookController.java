package library.controller;

import com.google.gson.*;
import library.model.Book;
import library.server.BookListResponse;
import library.server.Request;
import library.server.Response;
import library.service.LibraryService;

import java.net.Socket;
import java.util.List;

public abstract class BookController {
    public abstract Response handleRequest(Socket socket, Request request);
}

