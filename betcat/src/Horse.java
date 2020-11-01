import java.lang.reflect.Array;
import java.util.Vector;

public class Horse {
    private final String name;
    private final boolean isBoosted;
    private final Lane lane;
    private String color;
    private int currenPosition;
    private int previousPosition;



    public Horse(String name){
        this.name = name;
        this.isBoosted = false;
        this.lane = new Lane(Field.FIELD_WIDTH);
        this.currenPosition = Game.INITIAL_POSITION;
    }
    /*
    public int move(){

        if(canMove()) {
            int positionsToMove = NumberRandomizer.normalRandom();
            if(currentIndex + positionsToMove > lane.getSize()){
                return currentIndex = lane.getSize() - 1;
            }
            this.previousPosition = currentIndex;
            return currentIndex += positionsToMove;
        }
        return currentIndex;
    }
    */
    public void move(){
        int positionsToMove = NumberRandomizer.normalRandom();

        if(isBoosted){
            positionsToMove = NumberRandomizer.specialRandom();
        }

        if(canMove()) {
            if(currenPosition + positionsToMove > lane.getSize()){
                setPosition(lane.getSize() - 1);
                positionUpdate();
            }
            this.previousPosition = currenPosition;
                setPosition(currenPosition + positionsToMove);
                positionUpdate();

        }
        //lane.set(currentIndex, this.toString());
    }


    private void setPosition(int i){
        this.lane.set(i, this.toString());
    }
    /*
    public int boostedMove() {
        if (canMove()) {
            int positionsToMove = NumberRandomizer.specialRandom();
            if(currentIndex + positionsToMove > lane.getSize()) {
                return currentIndex = lane.getSize() - 1;
            }
            return currentIndex += positionsToMove;
        }
        return currentIndex;
    }
*/
    public boolean isBoosted() {
        return isBoosted;
    }

    public int getCurrentIndex() {
        return currenPosition;
    }

    public Lane getLane() {
        return lane;
    }

    public int getPreviousPosition() {
        return previousPosition;
    }

    public void positionUpdate(){
        this.lane.set(this.previousPosition, " ");
        this.lane.add(this.currenPosition, this.toString());
    }

    public boolean canMove(){
        return currenPosition < lane.getSize();
    }

    public String finishedRace(){
        if(currenPosition == lane.getSize() - 1){
            return this.name;
        }
        return null;
    }

    @Override
    public String toString(){
        return "H";
    }
}
