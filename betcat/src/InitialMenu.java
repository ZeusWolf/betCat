import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

public class InitialMenu implements MenuInterface {

    public static final String GREEN_BOLD = "\033[1;32m";

    private ClientConnection player;
    private String[] options = {"Start Gambling", "Info", "Exit"};
    private int answerIndex;
    private Prompt prompt;
    private Game game;


    public InitialMenu(Prompt prompt, ServerTry serverTry, ClientConnection clientConnection) {
        this.prompt = prompt;
        this.game = new Game(serverTry, clientConnection);
        init();
    }

    @Override
    public void init() {

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MenuInputScanner scanner = new MenuInputScanner(options);
        scanner.setMessage(GREEN_BOLD + "______      _   _____       _   \n" +
                "| ___ \\    | | /  __ \\     | |  \n" +
                "| |_/ / ___| |_| /  \\/ __ _| |_ \n" +
                "| ___ \\/ _ \\ __| |    / _` | __|\n" +
                "| |_/ /  __/ |_| \\__/\\ (_| | |_ \n" +
                "\\____/ \\___|\\__|\\____/\\__,_|\\__|" + "  , gambling for you!" + "\n");

        this.answerIndex = prompt.getUserInput(scanner);
        verifier();
    }

    @Override
    public void verifier() {

        if (options[answerIndex - 1].equals("Start Gambling")) {
            game.gameStart();
        }

        if (options[answerIndex - 1].equals("Info")) {
            new InfoMenu(prompt, this);
        }

        if (options[answerIndex - 1].equals("Exit")) {
            try {
                Thread.sleep(1000);
                System.exit(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}