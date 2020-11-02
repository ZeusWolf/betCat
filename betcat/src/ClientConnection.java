import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerRangeInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringSetInputScanner;

import java.io.*;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ClientConnection implements Runnable {

    public static final String RED_BOLD = "\033[1;31m";
    public static final String YELLOW_BOLD = "\033[1;33m";
    public static final String WHITE_BOLD = "\033[1;37m";
    public static final String GREEN_BOLD = "\033[1;32m";


    private Prompt prompt;
    private Socket socket;
    private ServerTry server;
    private PrintWriter out;
    private String name;
    private String picked;
    private int credits;
    private int bet;

    public ClientConnection(Socket socket, ServerTry server) {
        this.socket = socket;
        this.server = server;
        this.credits = 100;
        try {
            this.prompt = new Prompt(socket.getInputStream(), new PrintStream(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        getNameFromUser();

    }

    @Override
    public void run() {
        try {
            BufferedReader in = openStreams();
            send(WHITE_BOLD + "========================================================================" + "\n");
            send(RED_BOLD + "Welcome to BetCat " + YELLOW_BOLD + name + "! 🤩" + "\n" + RED_BOLD + "Type " + YELLOW_BOLD + "start" + RED_BOLD +
                    " to enter the app!");

            if (!server.addClient(this)) {
                send(RED_BOLD + "The server is full, try again later 😴");
                close();
            }

            while (!socket.isClosed()) {
                listen(in);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listen(BufferedReader in) throws IOException {
        String message = in.readLine();
        send("\n" + WHITE_BOLD + "========================================================================");

        if (message.equals("start")) {
            InitialMenu menu = new InitialMenu(prompt, server, this);
        }
    }

    public BufferedReader openStreams() throws IOException {
        out = new PrintWriter(socket.getOutputStream(), true);
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    private void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String message) {
        out.println(message);
    }

    private void getNameFromUser() {
        try {
            Scanner scanner = new Scanner(openStreams());
            this.out.println("\033[H\033[2J");
            this.out.flush();
            send(RED_BOLD + "Insert your name 📌" + GREEN_BOLD);
            this.name = scanner.nextLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getBetFromUser() {

        IntegerRangeInputScanner value = new IntegerRangeInputScanner(1, credits);
        value.setMessage(RED_BOLD + "You have " + YELLOW_BOLD + credits + " credits 💸" + "\n" + RED_BOLD +
                "Place your bet 📌" + "\n");
        value.setError("❌ Not a valid bet ❌");
        this.bet = prompt.getUserInput(value);
        server.addBet();
        server.setPrizePool(server.getPrizePool() + bet);
    }

    public synchronized void playerHorse() {

        Set<String> horse = new HashSet<>();
        horse.add("Mustang");
        horse.add("Speedy");
        horse.add("Lightning");
        horse.add("Zeus");
        StringSetInputScanner horseName = new StringSetInputScanner(horse);
        horseName.setMessage("\n" + RED_BOLD + "Choose your horse " + "📌" + "\n" + "\n" + YELLOW_BOLD + " Mustang " + "🚘"
                + "\n" + " Speedy " + "🎠" + "\n" + " Lightning " + "⚡️" + "\n" + " Zeus " + "🙏🏻" + "\n" + "\n");
        this.picked = prompt.getUserInput(horseName);
    }

    public int getBet() {
        return bet;
    }

    public int getCredits() {
        return credits;
    }

    public String getPicked() {
        return this.picked;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getName() {
        return name;
    }
}


