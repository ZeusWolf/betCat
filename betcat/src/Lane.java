import java.util.Vector;

public class Lane {
    private final Vector<String> lane;
    private int size;


    public Lane(int size){
        this.lane = new Vector<>();
        this.size = size;
        lane.setSize(size);
    }

    public Vector<String> getLane() {
        return lane;
    }

    public void add(int index, String element) {
        this.lane.add(index, element);
    }

    public void set(int index, String element){
        this.lane.set(index, element);
    }

    //public void update()

    public int getSize() {
        return this.size;
    }


    @Override
    public String toString(){
        return this.lane.toString();
    }







}
