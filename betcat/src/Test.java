import java.io.IOException;

public class Test {

    public static void main(String[] args)throws IOException {

        GameServer gameServer = new GameServer();
        Game game = new Game(gameServer);
<<<<<<< HEAD
        game.setUser();
=======
        //   game.setUser();
>>>>>>> serverLogic
        gameServer.gameStart();




    }



}

