import org.academiadecodigo.bootcamp.Prompt;

import java.io.IOException;
import java.util.Scanner;

public class Game {

    private ClientConnection connection;
    private GameLogic gameLogic;
    private ServerTry server;
    private Prompt prompt;
    private int totalBets;

    public Game(ServerTry server, ClientConnection connection) {
        this.server = server;
        this.connection = connection;
        this.gameLogic = new GameLogic(connection, server);
    }

    public synchronized void gameStart() {

        while (connection.getCredits() > 0) {
            connection.playerHorse();
            connection.send("\n");
            connection.getBetFromUser();
            connection.setCredits(connection.getCredits() - connection.getBet());
            gameLogic.start();

            if (connection.getCredits() == 0) {

                connection.send("You ran out of credits!");

                try {
                    connection.openStreams().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}




