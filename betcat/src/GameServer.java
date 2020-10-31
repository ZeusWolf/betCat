import menus.InitialMenu;
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

<<<<<<< HEAD
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
=======
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
>>>>>>> main
        }
        //InitialMenu menu = InitialMenu();
    }

    public void waitConnection() {
        try {
<<<<<<< HEAD
            this.playerSocket = gameSocket.accept();
=======
            Socket playerSocket = gameServer.accept();

>>>>>>> main
            Player player = new Player(playerSocket);
            //System.out.println(playerSocket);
            ThisIsRunnable thisIsRunnable = new ThisIsRunnable(player);
            service.submit(thisIsRunnable);


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

    /*public void prompt() {

        try {
            prompt = new Prompt(playerSocket.getInputStream(), new PrintStream(playerSocket.getOutputStream()));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }*/

    public void broadCast() {

        for (Player player : players) {
<<<<<<< HEAD
            String answer = player.sendUserQuestion();
            player.send("The player " + player.getNickname() + " has joined!");
=======
            player.send();
            System.out.println("broadcast");
>>>>>>> main
        }
    }

    public Socket getPlayerSocket() {
        return playerSocket;
    }

}
