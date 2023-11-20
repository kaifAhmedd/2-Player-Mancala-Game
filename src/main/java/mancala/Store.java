package mancala;

public class Store{

    private Player owner;
    private int stoneCount;

    public Store(){

    }

    public void setOwner(Player player){
        owner = player;
    }

    public Player getOwner(){
        return owner;
    }

    public void addStones(int amount){
        stoneCount =  stoneCount + amount;
    }    

    public int getTotalStones(){
        return stoneCount;
    }

    public int emptyStore(){
        int storeSaver = stoneCount;
        stoneCount = 0;
        return storeSaver;
    }

    public String toString(){
        return getOwner() + "has" + getTotalStones() + "stones!";
    }

}