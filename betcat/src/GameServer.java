import org.academiadecodigo.bootcamp.Prompt;

import java.io.IOException;
import java.io.PrintStream;
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
    private ExecutorService service;
    private Prompt prompt;

    public GameServer() throws IOException {
        gameSocket = new ServerSocket(DEFAULT_PORT);
        players = new Vector<Player>();
        service = Executors.newFixedThreadPool(4);
    }

    public void connection() {

        while (gameSocket.isBound()) {
            waitConnection();
        }
    }

    public void addPlayer(Player player) {
        synchronized (players) {
            players.add(player);
        }
    }

    public void waitConnection() {

        try {
            this.playerSocket = gameSocket.accept();
            Player player = new Player(playerSocket);
            //System.out.println(playerSocket);
            ThisIsRunnable thisIsRunnable = new ThisIsRunnable(player);
            service.submit(thisIsRunnable);

            System.out.println(player.getNickname() + " connected");
            addPlayer(player);
            broadCast();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /*public void prompt() {

        try {
            prompt = new Prompt(playerSocket.getInputStream(), new PrintStream(playerSocket.getOutputStream()));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }*/

    public void broadCast() {

        for (Player player : players) {
            String answer = player.sendUserQuestion();
            player.send("The player " + player.getNickname() + " has joined!");
        }
    }

    public Socket getPlayerSocket() {
        return playerSocket;
    }

}
