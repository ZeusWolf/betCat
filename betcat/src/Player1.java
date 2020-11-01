import org.academiadecodigo.bootcamp.Prompt;

import java.io.*;
import java.net.Socket;

public class Player1 implements Runnable {
        /**
         * determines the number of credits a player gets
         **/

        private static final int INITIAL_CREDITS = 15;

        private int currentCredit;
        private int bet;
        private String nickname = "lola";
        private Horse horse;
        private Socket playerSocket;
        private PrintWriter writer;
        private BufferedReader reader;
        private Prompt myPrompt;
        private String message;  // feito dps
        private GameServer game;


        public Player1(Socket playerSocket, GameServer game) {

            this.currentCredit = INITIAL_CREDITS;
            this.playerSocket = playerSocket;
            this.game = game;

            try {
                this.myPrompt = new Prompt(this.playerSocket.getInputStream(), new PrintStream(this.playerSocket.getOutputStream()));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }


        public void openStreams() throws IOException {
            reader = new BufferedReader(new InputStreamReader(playerSocket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(playerSocket.getOutputStream()));

        }

        private void iMessage() {   //feito dps
            try {
                this.message = reader.readLine();

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        public void send(String message) {
            writer.write(this.message);
            writer.flush();

        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public Prompt getMyPrompt() {
            return this.myPrompt;
        }

        public Socket getPlayerSocket() {
            return playerSocket;
        }

        public int getBet() {
            return bet;
        }

        public void setBet(int bet) {
            this.bet = bet;
        }

        public int getCurrentCredit() {
            return currentCredit;
        }

    @Override
    public void run() {
        try {
            Player1 player1 = new Player1(playerSocket,game);
            openStreams();
            game.addPlayer(player1);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

}
