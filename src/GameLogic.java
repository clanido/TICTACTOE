import java.util.Random;

/**
 * GameLogic.java
 * Logic class. Handles move validation, winner checking, draw checking,
 * and the computer's move. This class does not know anything about Swing;
 * it only manages the board data, so it can be tested independently from the GUI.
 */
public class GameLogic {

    private char[] board;
    private Random random;

    public GameLogic() {
        board = new char[9];
        random = new Random();
        resetBoard();
    }

    public void resetBoard() {
        for (int i = 0; i < board.length; i++) {
            board[i] = ' ';
        }
    }

    /**
     * Places a symbol on the board at the given index.
     * Returns false if the index is invalid or the cell is already occupied.
     */
    public boolean makeMove(int index, char symbol) {
        if (index < 0 || index >= 9) {
            return false;
        }
        if (board[index] != ' ') {
            return false;
        }
        board[index] = symbol;
        return true;
    }

    /**
     * Checks whether the given symbol has three in a row, column, or diagonal.
     */
    public boolean checkWinner(char symbol) {
        int[][] patterns = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };

        for (int i = 0; i < patterns.length; i++) {
            int a = patterns[i][0];
            int b = patterns[i][1];
            int c = patterns[i][2];
            if (board[a] == symbol && board[b] == symbol && board[c] == symbol) {
                return true;
            }
        }
        return false;
    }

    /**
     * The board is a draw when every cell is filled and checkWinner() is false
     * for both symbols (the caller is responsible for checking the winner first).
     */
    public boolean isDraw() {
        for (int i = 0; i < board.length; i++) {
            if (board[i] == ' ') {
                return false;
            }
        }
        return true;
    }

    /**
     * Selects a random empty cell for the computer's move.
     * Returns -1 if there is no empty cell left.
     */
    public int computerMove() {
        java.util.List<Integer> emptyCells = new java.util.ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            if (board[i] == ' ') {
                emptyCells.add(i);
            }
        }
        if (emptyCells.isEmpty()) {
            return -1;
        }
        int randomPosition = random.nextInt(emptyCells.size());
        return emptyCells.get(randomPosition);
    }

    public char[] getBoard() {
        return board;
    }
}
