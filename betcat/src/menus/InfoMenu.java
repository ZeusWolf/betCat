package menus;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

public class InfoMenu implements MenuInterface {

    private Prompt prompt;
    private String[] info = {"Back"};
    private int answerIndex;
    private InitialMenu menu;

    public InfoMenu(Prompt prompt, InitialMenu menu) {
        this.prompt = prompt;
        this.menu = menu;
        init();
    }

    @Override
    public void init() {
        MenuInputScanner scanner = new MenuInputScanner(info);
        scanner.setMessage("Info: This is a gambling series of games were you pick a horse, and if you win \n" +
                "enough credits, you reach the goal, that's it, you win! If you hit the bottom, we talk later..." + "\n" + "\n" +"About us: We are the most prestigious gambling Company of Agualva, created in 1996 by " + "\n" + "the major Lord Queimado, divided between 4 masterminds:" + "\n" +"- Lord Queimado, the creator himself" +"\n"+ "- Old man Travanca, the janitor" + "\n" + "- Mário, the 'Ronaldinho Caruncho' of programming " + "\n" +"- Cat, the lunch lady");
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