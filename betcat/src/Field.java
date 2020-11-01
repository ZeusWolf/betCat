import java.util.*;

public class Field {
    public static final int FIELD_WIDTH = 20;
    private static final int FIELD_HEIGHT = 10;
    private final Lane lane;

    public Field() {
        this.lane = new Lane(FIELD_WIDTH);
        buildField();
    }

    public void buildField() {

        String separator = "----------------------------------------------------------------------------";

        Collections.fill(this.lane.getLane(), " ");

        this.lane.add(0, "H");
        this.lane.add(FIELD_WIDTH + 1, "FINISH LINE");

        for (int i = 1; i < FIELD_HEIGHT - 1; i++) {

            if (i % 2 == 0) {
                System.out.println(lane);
            }
            System.out.println(separator);
        }
    }


}
