import javax.swing.*;
import java.awt.*;

/**
 * MainMenuFrame.java
 * Swing window for the main menu after login.
 */
public class MainMenuFrame extends JFrame {

    private Player currentPlayer;
    private JButton btnStartGame;
    private JButton btnStatistics;
    private JButton btnTopScorers;
    private JButton btnExit;

    public MainMenuFrame(Player player) {
        this.currentPlayer = player;

        setTitle("Tic-Tac-Toe - Main Menu");
        setSize(350, 280);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel lblWelcome = new JLabel("Welcome, " + currentPlayer.getUsername() + "!");
        lblWelcome.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblWelcome.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnStartGame = new JButton("Start Game");
        btnStatistics = new JButton("My Statistics");
        btnTopScorers = new JButton("Top 5 Scorers");
        btnExit = new JButton("Exit");

        for (JButton b : new JButton[]{btnStartGame, btnStatistics, btnTopScorers, btnExit}) {
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            b.setMaximumSize(new Dimension(200, 35));
        }

        panel.add(lblWelcome);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnStartGame);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnStatistics);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnTopScorers);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnExit);

        add(panel);

        btnStartGame.addActionListener(e -> {
            GameFrame gameFrame = new GameFrame(currentPlayer);
            gameFrame.setVisible(true);
            this.dispose();
        });

        btnStatistics.addActionListener(e -> {
            StatisticsFrame statisticsFrame = new StatisticsFrame(currentPlayer);
            statisticsFrame.setVisible(true);
        });

        btnTopScorers.addActionListener(e -> {
            TopScorersFrame topFrame = new TopScorersFrame();
            topFrame.setVisible(true);
        });

        btnExit.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?",
                    "Exit Application", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }
}
