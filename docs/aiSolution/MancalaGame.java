import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class MancalaGame {
    private Board board;
    private Player currentPlayer;

    public MancalaGame(int initialStonesPerPit) {
        board = new Board(initialStonesPerPit);
        currentPlayer = board.getPlayer1();
    }

    public void play(int pitIndex) {
        List<Pit> pits = board.getPits();
        Pit selectedPit = pits.get(pitIndex);

        if (selectedPit instanceof Store || selectedPit.getStones() == 0) {
            System.out.println("Invalid move. Choose a non-empty pit.");
            return;
        }

        int stonesToDistribute = selectedPit.getStones();
        selectedPit.setStones(0);

        int currentIndex = pitIndex + 1;

        while (stonesToDistribute > 0) {
            if (currentIndex == 13) {
                currentIndex = 0; // Wrap around to player 1's pits
            }

            if (currentIndex != 6) { // Skip player 2's store
                pits.get(currentIndex).addStone();
                stonesToDistribute--;
            }

            currentIndex++;
        }

        // Check for capture
        int lastStoneIndex = currentIndex - 1;
        if (lastStoneIndex < 0) {
            lastStoneIndex = 11;
        }

        Pit lastPit = pits.get(lastStoneIndex);
        if (lastPit instanceof Pit && lastPit.getStones() == 1 && lastStoneIndex < 6) {
            int oppositePitIndex = 11 - lastStoneIndex;
            Pit oppositePit = pits.get(oppositePitIndex);

            int capturedStones = oppositePit.getStones();
            oppositePit.setStones(0);

            Store currentPlayerStore = currentPlayer.getStore();
            currentPlayerStore.setStones(currentPlayerStore.getStones() + capturedStones + 1);
        }

        // Switch player
        currentPlayer = (currentPlayer == board.getPlayer1()) ? board.getPlayer2() : board.getPlayer1();
    }

    public void displayBoard() {
        List<Pit> pits = board.getPits();

        System.out.println("Player 2 Store: " + pits.get(6).getStones() + "\t\t\t\t\t\t\t\tPlayer 1 Store: " + pits.get(13).getStones());
        System.out.println();

        System.out.print("\t\t\t\t\t");
        for (int i = 11; i >= 7; i--) {
            System.out.print(pits.get(i).getStones() + "\t");
        }
        System.out.println();
        System.out.println();

        System.out.println("Player 2 Pits:\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPlayer 1 Pits:");
        System.out.print("\t\t\t\t\t");
        for (int i = 0; i <= 5; i++) {
            System.out.print(pits.get(i).getStones() + "\t");
        }
        System.out.println();
        System.out.println();
    }

    public Player getWinner() {
        int player1StoreStones = board.getPlayer1().getStore().getStones();
        int player2StoreStones = board.getPlayer2().getStore().getStones();

        if (player1StoreStones > player2StoreStones) {
            return board.getPlayer1();
        } else if (player2StoreStones > player1StoreStones) {
            return board.getPlayer2();
        } else {
            return null; // It's a tie
        }
    }
}
