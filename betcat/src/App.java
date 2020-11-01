import menus.InitialMenu;
import org.academiadecodigo.bootcamp.Prompt;

public class App {

    public static void main(String[] args) {
        Prompt prompt = new Prompt(System.in, System.out);
        InitialMenu menu = new InitialMenu(prompt);
    }
}
