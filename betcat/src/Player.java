import java.io.*;
import java.net.Socket;

public class Player implements Runnable {

    /** determines the number of credits a player gets **/
    private static final int CREDITS = 15;

    private int bet;
    private String nickname = "lola";
    private Socket playerSocket;
    private PrintWriter outPut;
    private BufferedReader inPut;

    public Player(Socket playerSocket){

        this.playerSocket = playerSocket;
    }
    private void openStreams() throws IOException {
        outPut = new PrintWriter(playerSocket.getOutputStream(), true);
        inPut = new BufferedReader(new InputStreamReader(playerSocket.getInputStream()));

    }
    public void send(){
        outPut.write("ola");
    }

    public String getNickname() {
        return nickname;
    }



    @Override
    public void run() {

        try {
            openStreams();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
}
