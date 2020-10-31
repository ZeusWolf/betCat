import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

public class Menu {

    private Prompt prompt;
    private String [] options = {"Start Gambling", "Info1", "Exit"};
    private int answerIndex;


    public Menu(Prompt prompt){
        this.prompt = prompt;
        initMenu();
    }

    public void initMenu(){
        System.out.println("______      _   _____       _   \n" +
                "| ___ \\    | | /  __ \\     | |  \n" +
                "| |_/ / ___| |_| /  \\/ __ _| |_ \n" +
                "| ___ \\/ _ \\ __| |    / _` | __|\n" +
                "| |_/ /  __/ |_| \\__/\\ (_| | |_ \n" +
                "\\____/ \\___|\\__|\\____/\\__,_|\\__|");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MenuInputScanner scanner = new MenuInputScanner(options);

        scanner.setMessage("Welcome to BetCat, gambling for you.");
        this.answerIndex = prompt.getUserInput(scanner);

        optionVerifier();

    }

    private void optionVerifier(){
        if(options[answerIndex -1].equals("Start")){
            System.out.println("Be our guest, have fun!");
        }

        if(options[answerIndex-1].equals("Info")) {
            System.out.println("Here's all the info you need to know:");
            new More(prompt, this);
        }

        if(options[answerIndex-1].equals("Exit")){

            try {
                System.out.println("Thank you!");
                Thread.sleep(1000);
                System.exit(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
