import java.util.Collections;

public class GameLogic {
    private Horse[] horses;
    private boolean allBetsArePlaced;
    private Horse winner;

    public GameLogic(){
       this.horses = new Horse[4];
    }

    public void start(){
        horses[0] = new Horse("Mustang");
        horses[1] = new Horse("Speedy");
        horses[2] = new Horse("Lightning");
        horses[3] = new Horse("Zeus");

        pickHorse();
    }

    public Horse pickHorse() {
        winner = horses[NumberRandomizer.normalRandom()];
        return winner;
    }


    public void setAllBetsArePlaced(boolean allBetsArePlaced) {
        this.allBetsArePlaced = allBetsArePlaced;
    }

    public Horse getWinner() {
        return winner;
    }
}
