package library.server;

import library.ControllerFactory;
import library.repository.DaoFileImpl;
import library.service.LibraryService;

import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    private final int port;
    private final ControllerFactory controllerFactory;

    public Server(int port) {
        this.port = port;
        this.controllerFactory = new ControllerFactory(new LibraryService(new DaoFileImpl()));
    }

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                HandleRequest handleRequest = new HandleRequest(socket, controllerFactory);
                handleRequest.process();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

