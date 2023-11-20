import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Board {
    private List<Pit> pits;
    private Player player1;
    private Player player2;

    public Board(int initialStonesPerPit) {
        pits = new ArrayList<>();
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");

        for (int i = 0; i < 12; i++) {
            if (i == 6 || i == 13) {
                pits.add(new Store(0));
            } else {
                pits.add(new Pit(initialStonesPerPit));
            }
        }
    }

    public List<Pit> getPits() {
        return pits;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}
