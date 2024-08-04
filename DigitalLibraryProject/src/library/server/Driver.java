package library.server;

public class Driver {
    public static void main(String[] args) {
        Server server = new Server(8000);
        new Thread(server).start();
    }
}

