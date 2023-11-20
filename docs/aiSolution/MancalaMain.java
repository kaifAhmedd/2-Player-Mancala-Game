// import java.util.ArrayList;
// import java.util.List;
// import java.util.Scanner;
// import aiSolution.MancalaGame;

// public class MancalaMain {
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);

//         System.out.println("Welcome to Mancala!");

//         MancalaGame mancalaGame = new MancalaGame(4); // You can adjust the initial number of stones per pit

//         while (true) {
//             System.out.println("\nCurrent Board:");
//             mancalaGame.displayBoard();

//             System.out.println("Current Turn: " + mancalaGame.getCurrentPlayer().getName());
//             System.out.print("Enter the index of the pit you want to play (0-5 for Player 1, 7-12 for Player 2): ");
            
//             int pitIndex = scanner.nextInt();

//             if (pitIndex < 0 || pitIndex > 12) {
//                 System.out.println("Invalid pit index. Please enter a valid index.");
//                 continue;
//             }

//             mancalaGame.play(pitIndex);

//             if (mancalaGame.isGameOver()) {
//                 System.out.println("\nGame Over!");

//                 Player winner = mancalaGame.getWinner();

//                 if (winner != null) {
//                     System.out.println("Player " + winner.getName() + " wins!");
//                 } else {
//                     System.out.println("It's a tie!");
//                 }

//                 break;
//             }
//         }

//         scanner.close();
//     }
// }

import java.util.Scanner;

public class MancalaMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Mancala!");

        MancalaGame mancalaGame = new MancalaGame(4); // You can adjust the initial number of stones per pit

        while (true) {
            System.out.println("\nCurrent Board:");
            mancalaGame.displayBoard();

            System.out.println("Current Turn: " + mancalaGame.getCurrentPlayer().getName());
            System.out.print("Enter the index of the pit you want to play (0-5 for Player 1, 7-12 for Player 2): ");

            int pitIndex = scanner.nextInt();

            if (pitIndex < 0 || pitIndex > 12) {
                System.out.println("Invalid pit index. Please enter a valid index.");
                continue;
            }

            mancalaGame.play(pitIndex);

            if (mancalaGame.isGameOver()) {
                System.out.println("\nGame Over!");

                Player winner = mancalaGame.getWinner();

                if (winner != null) {
                    System.out.println("Player " + winner.getName() + " wins!");
                } else {
                    System.out.println("It's a tie!");
                }

                break;
            }
        }

        scanner.close();
    }
}