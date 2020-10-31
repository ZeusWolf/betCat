import org.academiadecodigo.bootcamp.InputScanner;
import org.academiadecodigo.bootcamp.Prompt;
import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player /*implements Runnable*/ {

    /**
     * determines the number of credits a player gets
     **/

    private static final int CREDITS = 15;


    private int bet;
    private String nickname = "lola";
    private Horse horse;
    private Socket playerSocket;
    private PrintWriter writer;
    private BufferedReader reader;
    private Prompt myPrompt;

    private String message;  // feito dps
=======
    private Socket playerSocket;
    private PrintWriter out;
    private String nickname = "lola";
    private int bet;
>>>>>>> main


    public Player(Socket playerSocket) {
        this.playerSocket = playerSocket;
        try {
            myPrompt=new Prompt(this.playerSocket.getInputStream(), new PrintStream(this.playerSocket.getOutputStream()));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
<<<<<<< HEAD

    public void openStreams() throws IOException {
        reader = new BufferedReader(new InputStreamReader(playerSocket.getInputStream()));
        System.out.println(playerSocket + "wtf");
        writer = new PrintWriter(new OutputStreamWriter(playerSocket.getOutputStream()));

    }

    private void iMessage(){   //feito dps
        try {
            this.message = reader.readLine();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void send(String message){

            writer.write(message);
            writer.flush();

    }

    public String getNickname() {
        return nickname;
    }

    public Socket getPlayerSocket() {
        return playerSocket;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }


    public String sendUserQuestion(InputScanner inputScanner){

       return (String) myPrompt.getUserInput(inputScanner);
    }
   /* @Override
    public void run() {
        try {

            BufferedReader in = openStreams();
            openStreams();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }


    }*/



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
