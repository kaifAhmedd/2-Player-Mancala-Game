package mancala;

public class Pit{
    private int stones = 0;

    public Pit(){
        
    }

    public int getStoneCount(){
        return stones;
    }

    public void addStone(){
        stones++;
    }

    public int removeStones(){
        int stoneSaver = stones;
        stones = 0;
        return stoneSaver;
    }

    public String toString(){
        return "The number of stones is: " + getStoneCount();
    }

}