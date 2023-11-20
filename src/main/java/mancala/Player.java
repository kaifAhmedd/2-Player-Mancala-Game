package mancala;

public class Player{

    private String userName = "";
    private Store  playerStore = new Store();

    public Player(){

    }

    public Player(String name){
        userName = name;
    }

    public String getName(){
        return userName;
    }

    public void setName(String name){
        userName = name;
    }    

    public Store getStore(){
        return playerStore;
    }

    public int getStoreCount(){
        return  playerStore.getTotalStones();
    }

    public void setStore(Store store){
        playerStore = store;
    }

    public String toString(){
        return getName() + "has" + getStoreCount() + "stones!";
    }
}