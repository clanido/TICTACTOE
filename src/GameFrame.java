import javax.swing.*;
import java.awt.*;

/**
 * GameFrame.java
 * Swing window for playing the game. Connects the GUI buttons with the
 * GameLogic class and updates the database after the game ends.
 */
public class GameFrame extends JFrame {

    private Player currentPlayer;
    private PlayerService playerService;
    private GameLogic gameLogic;
    private JButton[] buttons;
    private JLabel lblStatus;
    private boolean gameOver;

    public GameFrame(Player player) {
        this.currentPlayer = player;
        this.playerService = new PlayerService();
        this.gameLogic = new GameLogic();
        this.gameOver = false;

        setTitle("Tic-Tac-Toe - Game");
        setSize(360, 430);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        lblStatus = new JLabel("Your turn (X)", SwingConstants.CENTER);
        lblStatus.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblStatus.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JPanel boardPanel = new JPanel(new GridLayout(3, 3, 4, 4));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        buttons = new JButton[9];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("SansSerif", Font.BOLD, 36));
            boardPanel.add(buttons[i]);
        }

        JButton btnBackToMenu = new JButton("Back to Main Menu");
        btnBackToMenu.addActionListener(e -> {
            MainMenuFrame menuFrame = new MainMenuFrame(currentPlayer);
            menuFrame.setVisible(true);
            this.dispose();
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnBackToMenu);

        add(lblStatus, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Assume buttons[0] until buttons[8] represent the board cells.
        for (int i = 0; i < buttons.length; i++) {
            final int index = i;
            buttons[i].addActionListener(e -> handlePlayerMove(index));
        }
    }

    private void handlePlayerMove(int index) {
        if (gameOver) {
            return;
        }

        // Make player move using gameLogic.makeMove(index, 'X')
        boolean validMove = gameLogic.makeMove(index, 'X');
        if (!validMove) {
            // Cell already occupied or invalid index - ignore the click.
            return;
        }

        // Update button text to X
        buttons[index].setText("X");
        buttons[index].setEnabled(false);

        // Check whether player wins
        if (gameLogic.checkWinner('X')) {
            finishGame("WIN");
            return;
        }

        // Check draw
        if (gameLogic.isDraw()) {
            finishGame("DRAW");
            return;
        }

        // Generate computer move
        int computerIndex = gameLogic.computerMove();
        if (computerIndex != -1) {
            gameLogic.makeMove(computerIndex, 'O');
            // Update computer button text to O
            buttons[computerIndex].setText("O");
            buttons[computerIndex].setEnabled(false);
        }

        // Check whether computer wins
        if (gameLogic.checkWinner('O')) {
            finishGame("LOSE");
            return;
        }

        if (gameLogic.isDraw()) {
            finishGame("DRAW");
            return;
        }

        lblStatus.setText("Your turn (X)");
    }

    private void finishGame(String result) {
        gameOver = true;

        if (result.equals("WIN")) {
            lblStatus.setText("You win!");
        } else if (result.equals("LOSE")) {
            lblStatus.setText("You lose!");
        } else {
            lblStatus.setText("It's a draw!");
        }

        for (JButton b : buttons) {
            b.setEnabled(false);
        }

        // Update database statistics after game ends.
        playerService.updateStatistics(currentPlayer, result);

        JOptionPane.showMessageDialog(this, "Game result: " + result);

        MainMenuFrame menuFrame = new MainMenuFrame(currentPlayer);
        menuFrame.setVisible(true);
        this.dispose();
    }
}
