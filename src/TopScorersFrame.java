import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * TopScorersFrame.java
 * Swing window for showing the Top 5 scorers using a JTable.
 * The data is always retrieved from the database, never hardcoded.
 */
public class TopScorersFrame extends JFrame {

    private JTable table;
    private PlayerService playerService;

    public TopScorersFrame() {
        playerService = new PlayerService();

        setTitle("Tic-Tac-Toe - Top 5 Scorers");
        setSize(450, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {"Username", "Wins", "Losses", "Draws", "Score"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        // Get Top 5 players from playerService
        List<Player> topPlayers = playerService.getTopFiveScorers();

        // Add each player data into the table model
        for (Player p : topPlayers) {
            model.addRow(new Object[]{
                    p.getUsername(),
                    p.getWins(),
                    p.getLosses(),
                    p.getDraws(),
                    p.getScore()
            });
        }

        table = new JTable(model);
        table.setEnabled(false);

        add(new JScrollPane(table));
    }
}
