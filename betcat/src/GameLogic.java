import java.util.Collections;

public class GameLogic {
    public static final int INITIAL_POSITION = 0;
    private final Field field;
    private Horse[] horses;
    private boolean allBetsArePlaced;
    private String winner;

    public GameLogic(){
        this.field = new Field();
    }

    public void start(){
        this.horses = new Horse[4];

        horses[0] = new Horse("Mustang");
        horses[1] = new Horse("Speedy");
        horses[2] = new Horse("Lightning");
        horses[3] = new Horse("Zeus");

        for(Horse horse: horses){
            horse.getLane().add(INITIAL_POSITION, horse.toString());
            //Collections.fill(horse.getLane().getLane(), " ");
            //horse.getLane().add(0, horse.toString());

        }

        allBetsArePlaced = true;
        if(allBetsArePlaced) {
            initMovement();
        }
    }

    public void initMovement(){
        for(Horse horse : horses){
            while(horse.finishedRace() == null) {
                    horse.move();
                    laneUpdate(horse);
            }
            this.winner = horse.finishedRace();
        }
    }

    public void laneUpdate(Horse horse){
        horse.getLane().set(horse.getPreviousPosition(), " ");
        horse.getLane().add(horse.getCurrentIndex(), horse.toString());
    }

    public void setAllBetsArePlaced(boolean allBetsArePlaced) {
        this.allBetsArePlaced = allBetsArePlaced;
    }

    public String getWinner() {
        return winner;
    }
}
