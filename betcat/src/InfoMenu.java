import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

public class InfoMenu implements MenuInterface {

    public static final String RED_BOLD = "\033[1;31m";
    public static final String YELLOW_BOLD = "\033[1;33m";

    private InitialMenu menu;
    private Prompt prompt;
    private String[] info = {"Back"};
    private int answerIndex;

    public InfoMenu(Prompt prompt, InitialMenu menu) {
        this.prompt = prompt;
        this.menu = menu;
        init();
    }

    @Override
    public void init() {
        MenuInputScanner scanner = new MenuInputScanner(info);
        scanner.setMessage(YELLOW_BOLD + "Info: " + RED_BOLD + "This is a gambling series of games were you pick a horse,"
                + " and if you win \n" + "enough credits, you reach the goal, that's it, you win! If you hit the bottom," +
                " we'll talk later..." + "\n" + "\n" + "About us: We are the most prestigious gambling Company of Agualva," +
                " created in 1996 by " + "\n" + "the major " + YELLOW_BOLD + "Lord Queimado" + RED_BOLD + ", divided " +
                "between 4 masterminds:" + "\n" + YELLOW_BOLD + "- Lord Queimado" + RED_BOLD + ", the creator himself" +
                "\n" + "- Old man" + YELLOW_BOLD + " Travanca" + RED_BOLD + ", the janitor" + "\n" + "- " + YELLOW_BOLD +
                "Mário" + RED_BOLD + ", the 'Ronaldinho Caruncho' of programming " + "\n" + "- " + YELLOW_BOLD + "Cat" +
                RED_BOLD + ", the lunch lady");
        this.answerIndex = prompt.getUserInput(scanner);

        verifier();

    }

    @Override
    public void verifier() {
        if (info[answerIndex - 1].equals("Back")) {
            menu.init();
        }
    }
}