import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

public class Actions {

    private Player1 player;

    public void askNickname() {
        StringInputScanner askUsername = new StringInputScanner();
        askUsername.setMessage("Insert your Nickname: ");
        player.setNickname(player.getMyPrompt().getUserInput(askUsername));
        player.send("que raiva");
        System.out.println("welcome " + player.getNickname());

    }

    public void askBet() {

        IntegerInputScanner askBet = new IntegerInputScanner();
        askBet.setMessage("How much you wanna bet: ");
        player.setBet(player.getMyPrompt().getUserInput(askBet));
        player.send("batata doce");

        System.out.println(player.getCurrentCredit() - player.getBet());

    }

}
