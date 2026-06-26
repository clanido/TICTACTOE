import javax.swing.*;
import java.awt.*;

/**
 * StatisticsFrame.java
 * Swing window for showing personal statistics. Data is re-fetched from the
 * database so the numbers shown are always up to date, even right after a game.
 */
public class StatisticsFrame extends JFrame {

    private Player currentPlayer;
    private PlayerService playerService;

    private JLabel lblUsername;
    private JLabel lblWins;
    private JLabel lblLosses;
    private JLabel lblDraws;
    private JLabel lblScore;

    public StatisticsFrame(Player player) {
        this.currentPlayer = player;
        this.playerService = new PlayerService();

        setTitle("Tic-Tac-Toe - My Statistics");
        setSize(320, 260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(6, 1, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        lblUsername = new JLabel();
        lblUsername.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblWins = new JLabel();
        lblLosses = new JLabel();
        lblDraws = new JLabel();
        lblScore = new JLabel();

        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.addActionListener(e -> loadStatistics());

        panel.add(lblUsername);
        panel.add(lblWins);
        panel.add(lblLosses);
        panel.add(lblDraws);
        panel.add(lblScore);
        panel.add(btnRefresh);

        add(panel);

        loadStatistics();
    }

    private void loadStatistics() {
        Player latest = playerService.getPlayerById(currentPlayer.getId());

        // Fall back to the player object passed in if the database is unreachable.
        Player toShow = (latest != null) ? latest : currentPlayer;

        lblUsername.setText("Player: " + toShow.getUsername());
        lblWins.setText("Wins   : " + toShow.getWins());
        lblLosses.setText("Losses : " + toShow.getLosses());
        lblDraws.setText("Draws  : " + toShow.getDraws());
        lblScore.setText("Score  : " + toShow.getScore());
    }
}
