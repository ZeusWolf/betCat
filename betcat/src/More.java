import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

public class More {

    private Prompt prompt;
    private String[] info = {"Back"};
    private int answerIndex;
    private Menu menu;

    public More(Prompt prompt, Menu menu) {
        this.prompt = prompt;
        this.menu = menu;
        initMore();
    }

    private void initMore() {
        MenuInputScanner scanner = new MenuInputScanner(info);
        scanner.setMessage("Info: thisis sohbasoiahs iaguosyvdbaosvdh sofuays doai");
        this.answerIndex = prompt.getUserInput(scanner);

        infoVerifier();

    }

    private void infoVerifier() {          //mudar nome para optionVEgascvcs e implementar ambos os menus da Interface
        /*if (info[answerIndex - 1].equals("Info")) {
            System.out.println("this is just a piece of shit");


        }*/
        if (info[answerIndex - 1].equals("Back")) {
            System.out.println("okok");
             menu.initMenu();
        }
    }
}