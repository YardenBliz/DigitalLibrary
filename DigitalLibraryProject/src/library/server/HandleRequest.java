package library.server;

import com.google.gson.Gson;
import library.ControllerFactory;
import library.controller.BookController;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class HandleRequest {
    private final Socket socket;
    private final ControllerFactory controllerFactory;

    Gson gson = new Gson();

    public HandleRequest(Socket socket, ControllerFactory controllerFactory) {
        this.socket = socket;
        this.controllerFactory = controllerFactory;
    }

    public void process() {
        try {
            PrintWriter writer = new PrintWriter(this.socket.getOutputStream(), true);
            Scanner reader = new Scanner(new InputStreamReader(this.socket.getInputStream()));

            String line = reader.nextLine();
            System.out.println("Received JSON request from client: " + line);

            Request request = gson.fromJson(line, Request.class);

            String action = request.getAction();
            BookController controller = controllerFactory.getController(action);
            Response response = controller.handleRequest(socket, request);

            System.out.println(response.getMessage());

            writer.println(gson.toJson(response));
            writer.flush();

            writer.close();
            reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
