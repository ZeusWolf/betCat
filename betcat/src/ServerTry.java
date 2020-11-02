import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerTry {

    private static final int PORT = 8080;
    private static final int MAXIMUM_PLAYERS = 2;


    private ServerSocket socket;
    private ExecutorService service;
    private List<ClientConnection> clients;
    private volatile String winnerHorse;
    private boolean allBetsArePlaced;
    private int prizePool;
    private int numberOfBets;
    private int winners;


    public ServerTry() {
        try {
            this.socket = new ServerSocket(PORT);
            this.clients = Collections.synchronizedList(new LinkedList<>());
            this.service = Executors.newCachedThreadPool();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        while (true) {
            waitConnection();
        }
    }

    private synchronized void waitConnection() {
        try {
            Socket clientSocket = socket.accept();
            ClientConnection connect = new ClientConnection(clientSocket, this);
            service.submit(connect);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean addClient(ClientConnection client) {
        synchronized (clients) {
            if (clients.size() >= MAXIMUM_PLAYERS) {
                return false;
            }
            clients.add(client);
            return true;
        }
    }

    /*public void broadcast(String message) {

            if (allBetsArePlaced) {
                return;
            }

            for (int i = 0; i < clients.size(); i++) {
                clients.get(getClients().size()).send(message);
            }
        }*/

    public void addBet() {
        numberOfBets++;

        if (numberOfBets == clients.size()) {
            setAllBetsArePlaced(true);
        }
    }

    public void addWinner() {
        winners++;
    }

    public List<ClientConnection> getClients() {
        return clients;
    }

    public int deliverPrizePool(int winners) {
        if (winners == 0) {
            setPrizePool(0);
        }
        return prizePool / winners;
    }

    public int getPrizePool() {
        return prizePool;
    }

    public int getWinners() {
        return winners;
    }

    public void setPrizePool(int prizePool) {
        this.prizePool = prizePool;
    }

    public void setAllBetsArePlaced(boolean allBetsArePlaced) {
        this.allBetsArePlaced = allBetsArePlaced;
    }

    public boolean isAllBetsArePlaced() {
        return allBetsArePlaced;
    }

    public String getWinnerHorse() {
        return winnerHorse;
    }

    public void setWinnerHorse(String winnerHorse) {
        this.winnerHorse = winnerHorse;
    }
}
