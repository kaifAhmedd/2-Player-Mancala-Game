package ui;
import mancala.Player;
import mancala.MancalaGame;
import mancala.InvalidMoveException;
import mancala.GameNotOverException;
import java.util.Scanner;

public class TextUI{
    public static void main(String[] args){
        Scanner scannedPit = new Scanner(System.in);
        MancalaGame newGame = new MancalaGame();
        Player player1 = new Player("Bob(Player 1)");
        Player player2 = new Player("Jessica(Player 2)");
        int currentPlayerChecker = 0;
        int pitPicked = 0;
        newGame.setPlayers(player1, player2);
        newGame.getBoard().initializeBoard();

        while(!(newGame.getBoard().getPits().get(0).getStoneCount() == 0 
        && newGame.getBoard().getPits().get(1).getStoneCount() == 0
        && newGame.getBoard().getPits().get(2).getStoneCount() == 0 
        && newGame.getBoard().getPits().get(3).getStoneCount() == 0 
        && newGame.getBoard().getPits().get(4).getStoneCount() == 0 
        && newGame.getBoard().getPits().get(5).getStoneCount() == 0)
        || !(newGame.getBoard().getPits().get(6).getStoneCount() == 0 
        && newGame.getBoard().getPits().get(7).getStoneCount() == 0
        && newGame.getBoard().getPits().get(8).getStoneCount() == 0 
        && newGame.getBoard().getPits().get(9).getStoneCount() == 0 
        && newGame.getBoard().getPits().get(10).getStoneCount() == 0 
        && newGame.getBoard().getPits().get(11).getStoneCount() == 0)){
            System.out.println(newGame.toString());
            currentPlayerChecker = newGame.getPlayers().indexOf(newGame.getCurrentPlayer());
            System.out.println("It is currently " + newGame.getCurrentPlayer().getName() + " turn!");
            if(currentPlayerChecker == 0){
                System.out.println(newGame.getCurrentPlayer().getName() + " Please enter a pit number between 1-6");
            }else{
                System.out.println(newGame.getCurrentPlayer().getName() + " Please enter a pit number between 7-12");
            }

            try{
                pitPicked = scannedPit.nextInt();
                newGame.move(pitPicked);
            }catch(InvalidMoveException e){
                System.out.println("The move made is invalid!");
            }


        }

        try{
            System.out.println("The game has ended!");
            Player theWinner = newGame.getWinner();
            System.out.println("And the winner is: " +theWinner.getName() + "!");
            System.out.println(newGame.toString());
        }catch(GameNotOverException e){
            System.out.println("THE GAME HAS NOT ENDED YET!");
        }
    }
}