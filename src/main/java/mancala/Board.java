package mancala;
import java.util.ArrayList;

public class Board{
    private ArrayList<Pit> pits = new ArrayList<>();
    private ArrayList<Store> stores = new ArrayList<>();

    public Board(){
        setUpPits();
        setUpStores();
    }

    public void setUpPits(){
        pits.clear();
        for(int i = 0; i<12; i++){
            Pit pit = new Pit();
            pits.add(pit);
        }
    }

    public ArrayList<Pit> getPits(){
        return pits;
    }

    public ArrayList<Store> getStores(){
        return stores;
    }

    public void setUpStores(){
        stores.clear();
        for(int i = 0; i<2; i++){
            Store store = new Store();
            stores.add(store);
        }
    }

    public void initializeBoard(){
        setUpPits();
        setUpStores();
        resetBoard();
    }

    public void resetBoard(){
        int numberOfPits = getPits().size();
        int maxStones = 4;
        for(int i = 0; i<numberOfPits; i++){
            Pit currentPit = getPits().get(i);
            while(currentPit.getStoneCount() != maxStones){
                currentPit.addStone();
            }
        }
    }

    public void registerPlayers(Player one, Player two){
        int i = 0;
        Store store1 = getStores().get(i);
        one.setStore(store1);
        store1.setOwner(one);
        i++;
        Store store2 = getStores().get(i);
        two.setStore(store2);
        store2.setOwner(two);
    }

    public int moveStones(int startPit, Player player) throws InvalidMoveException{
        
        int numberOfPits = getPits().size();
        if(startPit>=1 && startPit<=numberOfPits){
            if(getPits().get(startPit-1).getStoneCount() == 0){
                throw new InvalidMoveException();
            }
            
            int initialStoreCount = player.getStoreCount();
            int movedStones = 0;
            boolean onSameSide = false;
            int captureAnswer = 0;

            try{
                movedStones = distributeStones(startPit);
            }catch(PitNotFoundException e){
                throw new InvalidMoveException();
            }

            int differenceOfStores = player.getStoreCount() - initialStoreCount;
            int stoppedPoint = ((startPit - 1) + movedStones - differenceOfStores) % 12;

            if((startPit>=1 && startPit<=6 && stoppedPoint+1>=1 && stoppedPoint+1<=6) 
            || (startPit>=7 && startPit<=12 && stoppedPoint+1>=7 && stoppedPoint+1<=12)){
                onSameSide = true;
            }

            if(getPits().get(stoppedPoint).getStoneCount() == 1 && onSameSide){
                try{
                    captureAnswer = captureStones(stoppedPoint + 1);
                    player.getStore().addStones(captureAnswer);
                }catch(PitNotFoundException e){
                    throw new InvalidMoveException();
                }
            }
            int returner = captureAnswer + differenceOfStores;
            return returner;
        }else{
            throw new InvalidMoveException();
        }
    }


    public int distributeStones(int startingPoint) throws PitNotFoundException{
        int numberOfPits = getPits().size();
        if(startingPoint>=1 && startingPoint<=numberOfPits){
            int totalStonesInPit = getPits().get(startingPoint-1).removeStones();
            int stonesToReturn = totalStonesInPit;
            int toPlace = startingPoint;
            int interativeStop = (startingPoint + totalStonesInPit) -1;
            boolean isStore = false;

            for(int i = toPlace; i <= interativeStop; i++){
                if(i%6 == 0 && !isStore){
                    int currentIndex = (i-1) % 12;
                    if((startingPoint-1)/6 == currentIndex/6){
                        getStores().get(currentIndex/6).addStones(1);
                        totalStonesInPit--;
                        i++;
                    }
                    i--;
                    isStore = true;
                }else{
                    getPits().get(i%12).addStone();
                    isStore = false;
                }
            }
            return stonesToReturn;
        }else{
            throw new PitNotFoundException();
        }
    }

    public int captureStones(int stoppingPoint) throws PitNotFoundException{
        int index = 0;
        int stonesCaptured = 0;
        int numberOfPits = getPits().size();
        if(stoppingPoint >=1 && stoppingPoint<=numberOfPits){
            index = 11 - stoppingPoint;
            if(getPits().get(index).getStoneCount() == 0){
                return 0;
            }
            stonesCaptured = stonesCaptured + getPits().get(index).removeStones();
            stonesCaptured = stonesCaptured + getPits().get(stoppingPoint-1).removeStones();

            return stonesCaptured;

        }else{
            throw new PitNotFoundException();
        }
    }

    public int getNumStones(int pitNum) throws PitNotFoundException{
        int numberOfPits = getPits().size();
        if(pitNum >= 1 && pitNum<=numberOfPits){
            Pit foundingPit = getPits().get(pitNum-1);
            int stoneNumber = foundingPit.getStoneCount();
            return stoneNumber;
        }else{
            throw new PitNotFoundException();
        }
    }

    public boolean isSideEmpty(int pitNum) throws PitNotFoundException{
        int numberOfPits = getPits().size();
        if(pitNum >=1 && pitNum<=numberOfPits){
            if(pitNum>=1 && pitNum<=6 && getPits().get(0).getStoneCount() == 0 && getPits().get(1).getStoneCount() == 0
            && getPits().get(2).getStoneCount() == 0 && getPits().get(3).getStoneCount() == 0 
            && getPits().get(4).getStoneCount() == 0 && getPits().get(5).getStoneCount() == 0){
                return true;
            }else if(pitNum>=7 && pitNum<=12 && getPits().get(6).getStoneCount() == 0 
            && getPits().get(7).getStoneCount() == 0
            && getPits().get(8).getStoneCount() == 0 && getPits().get(9).getStoneCount() == 0 
            && getPits().get(10).getStoneCount() == 0 && getPits().get(11).getStoneCount() == 0){
                return true;
            }
            return false;
        }else{
            throw new PitNotFoundException();
        }
    }

    public String toString(){
        // return "The number of pits on the board are" + pits.size() 
        // + "and the number of stores on the board are" + stores.size() + ".";

        return "Mancala Game \n"
        + getStores().get(1).getTotalStones() + "\t" + getPits().get(11).getStoneCount()
        + " " + getPits().get(10).getStoneCount() + " " + getPits().get(9).getStoneCount()
        + " " + getPits().get(8).getStoneCount() + " " + getPits().get(7).getStoneCount()
        + " " + getPits().get(6).getStoneCount() + "\n" + " " + "\t" 
        + getPits().get(0).getStoneCount() + " " + getPits().get(1).getStoneCount()
        + " " + getPits().get(2).getStoneCount() + " " + getPits().get(3).getStoneCount()
        + " " + getPits().get(4).getStoneCount() + " " + getPits().get(5).getStoneCount()
        + "\t" + getStores().get(0).getTotalStones();
    }

}