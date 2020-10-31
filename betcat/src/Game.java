import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Game {

    private static Player player;
    private Socket playerSocket;
    private Horse horse;
    private Prompt prompt;
    private GameServer gameServer;
    private Socket socket;

    public Game(GameServer gameServer) {
           this.gameServer = gameServer;
           this.socket = gameServer.getPlayerSocket();

    }

    public void setUser() {

        StringInputScanner askUsername = new StringInputScanner();
        askUsername.setMessage("Insert your Nickname: ");
        //player.setNickname(prompt.getUserInput(askUsername));
//        System.out.println("Welcome " + player.getNickname());

    }


}
