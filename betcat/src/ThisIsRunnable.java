import java.io.IOException;

public class ThisIsRunnable implements Runnable{

    private Player player;

    public ThisIsRunnable (Player player){
        this.player = player;
    }

    @Override
    public void run() {
        try {
            player.openStreams();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
