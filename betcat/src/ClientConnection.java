import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientConnection implements Runnable{

    private Socket socket;
    private ServerTry server;
    private String name;
    private PrintWriter out;

    public ClientConnection(Socket socket, ServerTry server){
        this.socket = socket;
        this.server = server;
        getNameFromUser();
    }

    @Override
    public void run() {
        try {
            BufferedReader in = openStreams();


            send("Welcome to BetCat");

            if (!server.addClient(this)) {
                send("The server is full, try again later.");
                close();

            }
            while (!socket.isClosed()) {
                listen(in);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listen(BufferedReader in) throws IOException{
        String message = in.readLine();
    }

    private BufferedReader openStreams() throws  IOException{
        out = new PrintWriter(socket.getOutputStream(), true);
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    private void close(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String message){
        out.println(message);
    }

    private void getNameFromUser(){
        try {
            Scanner scanner = new Scanner(openStreams());
            send("Insert your name: ");
            this.name = scanner.nextLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }
}
