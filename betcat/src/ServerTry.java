import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerTry {

    private static final int MAXIMUM_PLAYERS = 4;
    private ServerSocket socket;
    private ExecutorService service;
    private List<ClientConnection> clients;
    private int port = 8079;

    public ServerTry(){
        try {
            this.socket = new ServerSocket(this.port);
            this.clients = Collections.synchronizedList(new LinkedList<>());
            this.service = Executors.newCachedThreadPool();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void start(){
        while(true){
            waitConnection();
        }
    }

    private void waitConnection(){
        try{
            Socket clientSocket = socket.accept();

            ClientConnection connect = new ClientConnection(clientSocket, this);
            service.submit(connect);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean addClient(ClientConnection client){
        synchronized (clients){
            if(clients.size() >= MAXIMUM_PLAYERS ){
                return false;
            }
            broadcast(client.getName() + " as joined");
                clients.add(client);
                return true;
        }
    }

    private void broadcast(String message){
        synchronized (clients){
            for(ClientConnection client : clients){
                client.send(message);
            }
        }
    }
}
