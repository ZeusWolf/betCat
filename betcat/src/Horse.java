public class Horse {
    private final String name;
    private final boolean isBoosted;
    private String color;
    private boolean isWinner;

    public Horse(String name){
        this.name = name;
        this.isBoosted = false;
        this.isWinner = false;
    }
    
    @Override
    public String toString(){
        return this.name;
    }

    public boolean isBoosted() {
        return isBoosted;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }
}
