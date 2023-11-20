package mancala;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTest{
    private Board newBoard;
    private Player player1;
    private Player player2;

    @BeforeEach
    public void setUp(){
        newBoard = new Board();
        newBoard.initializeBoard();

        player1 = new Player("Player1");
        player2 = new Player("Player2");
        newBoard.registerPlayers(player1,player2);
    }

    @Test
    public void capturedStonesChecker(){
        newBoard.getPits().get(3).removeStones();
        newBoard.getPits().get(3).addStone();

        newBoard.getPits().get(4).removeStones();

        newBoard.getPits().get(7).removeStones();
        
        for(int i = 0; i<4; i++){
            newBoard.getPits().get(7).addStone();
        }
        int capturedStones = 0;
        try{
        capturedStones = newBoard.captureStones(4);
        }catch(PitNotFoundException e){

        }
        newBoard.getPits().get(3).removeStones();
        newBoard.getPits().get(7).removeStones();

        int pit4Count = newBoard.getPits().get(3).getStoneCount();
        int pit8Count = newBoard.getPits().get(7).getStoneCount();
        assertEquals(0, pit4Count);
        assertEquals(0, pit8Count);
        assertEquals(5, capturedStones);
    }

    @Test
    public void distributeStonesTester(){
        newBoard.initializeBoard();
        try{
        newBoard.distributeStones(1);
        }catch(PitNotFoundException e){

        }
        int pit1 = newBoard.getPits().get(0).getStoneCount();
        int pit2 = newBoard.getPits().get(1).getStoneCount();
        int pit6 = newBoard.getPits().get(5).getStoneCount();
        int store1 = newBoard.getStores().get(0).getTotalStones();
        int store2 = newBoard.getStores().get(1).getTotalStones();
        assertEquals(0,pit1);
        assertEquals(5,pit2);
        assertEquals(4,pit6);
        assertEquals(0,store1);
        assertEquals(0,store2);


    }

    @Test
    public void getNumberOfStonesAfterRemoval(){
        newBoard.getPits().get(2).removeStones();
        int numberOfStones = 0;
        try{
            numberOfStones = newBoard.getNumStones(3);
        }catch(PitNotFoundException e){

        }
        assertEquals(0, numberOfStones);
    }

    @Test
    public void getNumberOfStonesAfterReset(){
        newBoard.resetBoard();
        int numberOfStones = 0;
        try{
            numberOfStones = newBoard.getNumStones(1);
        }catch(PitNotFoundException e){

        }
        assertEquals(4, numberOfStones);
    }

    @Test
    public void isSideEmptyFalse(){
        for(int i = 0; i<6; i++){
            newBoard.getPits().get(i).addStone();
        }

        boolean checker = false;
        try{
            checker = newBoard.isSideEmpty(3);
        }catch(PitNotFoundException e){

        }
        assertEquals(false, checker);
    }

    @Test
    public void isSideEmptyTrue(){
        for(int i = 6; i<12; i++){
            newBoard.getPits().get(i).removeStones();
        }
        boolean checker = false;
        try{
            checker = newBoard.isSideEmpty(8);
        }catch(PitNotFoundException e){

        }
        assertEquals(true, checker);
    }

    @Test
    public void resetBoardAndStoresReset(){
        newBoard.resetBoard();

        int sumOfStores = newBoard.getStores().get(0).getTotalStones()
        + newBoard.getStores().get(1).getTotalStones();

        assertEquals(0, sumOfStores);
    }

    @Test
    public void correctOwnerTester(){
        newBoard.registerPlayers(player1,player2);

        String playerOneName = newBoard.getStores().get(0).getOwner().getName();
        assertEquals("Player1", playerOneName);
    }


}