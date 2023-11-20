package mancala;
import java.util.ArrayList;

public class MancalaGame{
    private ArrayList<Player> players = new ArrayList<>();
    private Player currPlayer;
    private Board  newBoard = new Board();

    public MancalaGame(){
        
    }

    public void setPlayers(Player onePlayer, Player twoPlayer){
        Player playerOne = onePlayer;
        Player playerTwo = twoPlayer;
        currPlayer = onePlayer;

        players.add(playerOne);
        players.add(playerTwo);
    }

    public ArrayList<Player> getPlayers(){
        return players;
    }

    public Player getCurrentPlayer(){
        return currPlayer;
    }

    public void setCurrentPlayer(Player player){
        currPlayer = player;
    }

    public void setBoard(Board theBoard){
        newBoard = theBoard;
    }

    public Board getBoard(){
        return newBoard;
    }

    public int getNumStones(int pitNum) throws PitNotFoundException{
        int numberofPits = getBoard().getPits().size();
        if(pitNum>=1 && pitNum<=numberofPits){
            return getBoard().getNumStones(pitNum);
        }else{
            throw new PitNotFoundException();
        }
    }

    public int move(int startPit) throws InvalidMoveException{
        int currentPlayerIndex = 0;
        int otherPlayerIndex = 0;
        int secondPitSet = 0;
        int firstPitSet = 0;
        
        if(startPit<1 || startPit>12){
            throw new InvalidMoveException();
        }
        currentPlayerIndex = getPlayers().indexOf(getCurrentPlayer());
        if(currentPlayerIndex == 0){
            otherPlayerIndex = 1;
        }else{
            otherPlayerIndex = 0;
        }

        if((currentPlayerIndex == 1 && startPit<=6) || (currentPlayerIndex == 0 && startPit>6)){
            throw new InvalidMoveException();
        }
        
        getBoard().moveStones(startPit, getCurrentPlayer());

        if(getBoard().getPits().get(0).getStoneCount() == 0 && getBoard().getPits().get(1).getStoneCount() == 0
        && getBoard().getPits().get(2).getStoneCount() == 0 && getBoard().getPits().get(3).getStoneCount() == 0 
        && getBoard().getPits().get(4).getStoneCount() == 0 && getBoard().getPits().get(5).getStoneCount() == 0){
            secondPitSet = playerTwoPitsCalcAndRemove();
            getBoard().getStores().get(1).addStones(secondPitSet);
        } else if((getBoard().getPits().get(6).getStoneCount() == 0 
        && getBoard().getPits().get(7).getStoneCount() == 0
        && getBoard().getPits().get(8).getStoneCount() == 0 && getBoard().getPits().get(9).getStoneCount() == 0 
        && getBoard().getPits().get(10).getStoneCount() == 0 && getBoard().getPits().get(11).getStoneCount() == 0)){
            firstPitSet = playerOnePitsCalcAndRemove();
            getBoard().getStores().get(0).addStones(firstPitSet);
        }else{
            firstPitSet = playerOnePitsCalc();
            secondPitSet = playerTwoPitsCalc();
        }

        currPlayer = getPlayers().get(otherPlayerIndex);
        if(currentPlayerIndex == 0){
            return firstPitSet;
        }
        return secondPitSet;
    }

    private int playerOnePitsCalcAndRemove(){
        int adder = 0;
        adder = getBoard().getPits().get(0).removeStones()
        + getBoard().getPits().get(1).removeStones() + getBoard().getPits().get(2).removeStones()
        + getBoard().getPits().get(3).removeStones() + getBoard().getPits().get(4).removeStones()
        + getBoard().getPits().get(5).removeStones();
        return adder;
    }

    private int playerTwoPitsCalcAndRemove(){
        int adder = 0;
        adder = getBoard().getPits().get(6).removeStones()
        + getBoard().getPits().get(7).removeStones() + getBoard().getPits().get(8).removeStones()
        + getBoard().getPits().get(9).removeStones() + getBoard().getPits().get(10).removeStones()
        + getBoard().getPits().get(11).removeStones();
        return adder;
    }

    private int playerOnePitsCalc(){
        int adder = 0;
        adder = getBoard().getPits().get(0).getStoneCount()
        + getBoard().getPits().get(1).getStoneCount() + getBoard().getPits().get(2).getStoneCount()
        + getBoard().getPits().get(3).getStoneCount() + getBoard().getPits().get(4).getStoneCount()
        + getBoard().getPits().get(5).getStoneCount();
        return adder;
    }

    private int playerTwoPitsCalc(){
        int adder = 0;
        adder = getBoard().getPits().get(6).getStoneCount()
        + getBoard().getPits().get(7).getStoneCount() + getBoard().getPits().get(8).getStoneCount()
        + getBoard().getPits().get(9).getStoneCount() + getBoard().getPits().get(10).getStoneCount()
        + getBoard().getPits().get(11).getStoneCount();
        return adder;
    }

    public int getStoreCount(Player player) throws NoSuchPlayerException{
        if(player == getPlayers().get(0) || player == getPlayers().get(1)){
            return player.getStoreCount();
        }else{
            throw new NoSuchPlayerException();
        }
    }

    public Player getWinner() throws GameNotOverException{
        if(isGameOver()){
            int playerOneCount =  getPlayers().get(0).getStoreCount();
            int playerTwoCount =  getPlayers().get(1).getStoreCount();

            if(playerOneCount > playerTwoCount){
                return getPlayers().get(0);
            }else if(playerTwoCount > playerOneCount){
                return getPlayers().get(1);
            }else{
                return null;
            }
        }else{
            throw new GameNotOverException();
        }
    }

    public boolean isGameOver(){
        if((getBoard().getPits().get(0).getStoneCount() == 0 && getBoard().getPits().get(1).getStoneCount() == 0
        && getBoard().getPits().get(2).getStoneCount() == 0 && getBoard().getPits().get(3).getStoneCount() == 0 
        && getBoard().getPits().get(4).getStoneCount() == 0 && getBoard().getPits().get(5).getStoneCount() == 0)
        || (getBoard().getPits().get(6).getStoneCount() == 0 
        && getBoard().getPits().get(7).getStoneCount() == 0
        && getBoard().getPits().get(8).getStoneCount() == 0 && getBoard().getPits().get(9).getStoneCount() == 0 
        && getBoard().getPits().get(10).getStoneCount() == 0 && getBoard().getPits().get(11).getStoneCount() == 0)){
            return true;
        }
        return false;
    }

    public void startNewGame(){
        int numberofPlayers = getPlayers().size();
        getBoard().initializeBoard();
        if(numberofPlayers != 0){
            currPlayer = getPlayers().get(0);
        }
    }

    public String toString(){
        return getBoard().toString();
    }

}