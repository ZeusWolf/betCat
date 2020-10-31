import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

public class Menu {

    private Prompt prompt;
    private String [] options = {"Start", "More", "Exit"};


    public Menu(Prompt prompt){
        this.prompt = prompt;
        initMenu();
    }

    private void initMenu(){
        MenuInputScanner scanner = new MenuInputScanner(options);
        scanner.setMessage("Welcome tho BetCat, gambling for you.");
        int answerIndex = prompt.getUserInput(scanner);
        if(options[answerIndex -1].equals("Start")){
            System.out.println("Be our guest, have fun!");

        }
        if(options[answerIndex-1].equals("More")) {
            System.out.println("Here's all the info you need to know:");
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
