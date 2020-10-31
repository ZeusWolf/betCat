package menus;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

public class InitialMenu implements MenuInterface {

    private Prompt prompt;
    private String [] options = {"Start", "Info", "Exit"};
    private int answerIndex;

<<<<<<< HEAD:betcat/src/menus/InitialMenu.java

    public InitialMenu(Prompt prompt){
=======
    public Menu(Prompt prompt){
>>>>>>> main:betcat/src/Menu.java
        this.prompt = prompt;
        init();
    }

    @Override
    public void init(){
        MenuInputScanner scanner = new MenuInputScanner(options);
        scanner.setMessage("Welcome tho BetCat, gambling for you.");
        this.answerIndex = prompt.getUserInput(scanner);

        verifier();

    }

    @Override
    public void verifier(){
        if(options[answerIndex -1].equals("Start")){
            System.out.println("Be our guest, have fun!");
        }

        if(options[answerIndex-1].equals("Info")) {
            new InfoMenu(prompt, this);
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
