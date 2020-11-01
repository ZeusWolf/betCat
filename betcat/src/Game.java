import org.academiadecodigo.bootcamp.Prompt;

import java.net.Socket;

public class Game {

    private Player1 player;
    private Socket playerSocket;
    private Horse horse;
    private Prompt prompt;
    private GameServer gameServer;
    private Socket socket;

    public Game(GameServer gameServer) {
        this.gameServer = gameServer;
        //this.socket = gameServer.getPlayerSocket();

    }


}




