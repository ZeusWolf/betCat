import menus.InitialMenu;
import org.academiadecodigo.bootcamp.Prompt;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameServer {

    private static final int DEFAULT_PORT = 8080;

    private ServerSocket gameServer;
    private ExecutorService service;
    private Vector<Player> players;

    public GameServer() throws IOException {
        gameServer = new ServerSocket(DEFAULT_PORT);
        players = new Vector<Player>();
        service = Executors.newCachedThreadPool();

    }

    public static void main(String[] args) {

        try {
            GameServer gameServer = new GameServer();
            gameServer.gameStart();

        } catch (IOException e) {
            System.err.println("Error opening gamer server: " + e.getMessage());
        }

    }

    public void gameStart() {
        while (gameServer.isBound()) {
            waitConnection();
        }
        //InitialMenu menu = InitialMenu();
    }

    public void waitConnection() {
        try {
            Socket playerSocket = gameServer.accept();

            Player player = new Player(playerSocket);
            service.submit(player);


            System.out.println(player.getNickname() + " connected");
            addPlayer(player);
            broadCast();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPlayer(Player player) {
        synchronized (players) {
            players.add(player);
        }
    }

    public void broadCast() {

        for (Player player : players) {
            player.send();
            System.out.println("broadcast");
        }
    }
}
