import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ConcurrentModificationException;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameServer {

    private static final int DEFAULT_PORT = 8080;

    private Actions actions;
    private ServerSocket gameServer;
    private ExecutorService service;
    private Vector<Player1> players;

    private Socket playerSocket;

    public GameServer() throws IOException {
        actions = new Actions();
        gameServer = new ServerSocket(DEFAULT_PORT);
        players = new Vector<Player1>();
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
        while (true) {
            waitConnection();
        }
        //InitialMenu menu = InitialMenu();
    }

    public void waitConnection() {


        try {
            Socket playerSocket = gameServer.accept();

            //Socket playerSocket = gameServer.accept();


            this.playerSocket = gameServer.accept();


            //ThisIsRunnable thisIsRunnable = new ThisIsRunnable(playerSocket, this);
            //service.submit(thisIsRunnable);

            //service.submit(new ThisIsRunnable(playerSocket, this));

            //Player1 player1 = new Player1(playerSocket, this);
            //service.submit(player1);

            service.submit(new Player1(playerSocket, this));


            //System.out.println(thisIsRunnable.getPlayer().getNickname() + " connected");
            //addPlayer(thisIsRunnable.getPlayer());
            //players.add(thisIsRunnable.getPlayer());


            System.out.println(players);
            System.out.println(players.size());
            chooses();
            broadCast();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public void addPlayer(Player1 player){
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


            public void chooses () {


                try {
                    for (Player1 player : players) {

                        StringInputScanner askNickname = new StringInputScanner();
                        askNickname.setMessage("Insert your Nickname: ");
                        player.setNickname(player.getMyPrompt().getUserInput(askNickname));
            /*String nick = player.getMyPrompt().getUserInput(askNickname);
            player.setNickname(nick);*/
                        player.send("The player " + player.getNickname() + " has joined!");
                        System.out.println("welcome " + player.getNickname());

                    }
                } catch (ConcurrentModificationException e) {
                    e.printStackTrace();
                }
            }


            public void broadCast () {

                for (Player1 player : players) {


                    player.send("The player " + player.getNickname() + " has joined!");

                    player.send("ola");
                    System.out.println("broadcast");


                    //String answer = player.sendUserQuestion();


                    //String answer = player.sendUserQuestion();


                }
            }






   /* public Socket getPlayerSocket() {

    /*public Socket getPlayerSocket() {

        return playerSocket;
    }*/

        }
