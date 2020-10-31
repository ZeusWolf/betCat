import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player implements Runnable {

    /**
     * determines the number of credits a player gets
     **/

    private static final int CREDITS = 15;

    private Socket playerSocket;
    private PrintWriter out;
    private String nickname = "lola";
    private int bet;


    public Player(Socket playerSocket) {
        this.playerSocket = playerSocket;
    }


    @Override
    public void run() {
        try {

            BufferedReader in = openStreams();
            openStreams();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }


    private BufferedReader openStreams() throws IOException {
        out = new PrintWriter(playerSocket.getOutputStream(), true);
        return new BufferedReader(new InputStreamReader(playerSocket.getInputStream()));

    }

    public void send() {
        out.write("jjjjjjjjjjjj");
    }

    public String getNickname() {
        return nickname;
    }

}
