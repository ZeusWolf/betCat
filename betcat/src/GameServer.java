import org.academiadecodigo.bootcamp.Prompt;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameServer {

    private static final int DEFAULT_PORT = 8080;

    private ServerSocket gameSocket;
    private Socket playerSocket;
    private Vector<Player> players;
    private Prompt prompt;
    private ExecutorService service;

    public GameServer() throws IOException {

        players = new Vector<Player>();
        service = Executors.newFixedThreadPool(4);
        prompt = new Prompt(System.in, System.out);
    }

    public static void main(String[] args) {

        try {
            GameServer gameServer = new GameServer();
            gameServer.connection();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    public void connection() {

        try {
            gameSocket = new ServerSocket(DEFAULT_PORT);
            waitConnection();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void addPlayer(Player player) {
        synchronized (players) {
            players.add(player);
        }
    }

    public void waitConnection() {

        try {
            playerSocket = gameSocket.accept();
            Player player = new Player(playerSocket);
            service.submit(player);

            System.out.println(player.getNickname() + " connected");
            addPlayer(player);
            broadCast();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void broadCast() {

        for (Player player : players) {
            player.send();
            System.out.println("broadcast"); }
    }
}
