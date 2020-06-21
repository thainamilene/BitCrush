import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.System.exit;


public class ScoreboardComponent extends JPanel implements IScoreboard, ActionListener {
    private static final long serialVersionUID = 6251202006907429652L;
    private final Container mainPanel;
    int score = 0;
    int round = 0;
    JLabel jScore, jRound;

    public ScoreboardComponent(Container mainPanel) {
        super();
        style();
        this.mainPanel = mainPanel;
    }

    private void style() {
        setLayout(new GridLayout());
        setSize(450,62);
        setBackground(new Color(0x289dfd));

        jScore = new JLabel(new ImageIcon(Main.class.getResource(".").getPath() +  "/Images/score.png"));
        jScore.setText(String.valueOf(score));
        jScore.setForeground(new Color(0xffffff));
        jScore.setHorizontalTextPosition(jScore.CENTER);
        jScore.setVerticalTextPosition(jScore.CENTER);

        jRound = new JLabel(new ImageIcon(Main.class.getResource(".").getPath() +  "/Images/round.png"));
        jRound.setForeground(new Color(0xffffff));
        jRound.setText(String.valueOf(round));
        jRound.setHorizontalTextPosition(jRound.CENTER);
        jRound.setVerticalTextPosition(jRound.CENTER);

        add(jScore);
        add(jRound);
    }

    private void verifyRound() {
        if (round == 20) {
            mainPanel.remove(0);
            mainPanel.remove(0);
            mainPanel.remove(0);
            addEndButtons();
            end();
        }
    }

    private void addEndButtons() {
        JButton button = new JButton("Sair");
        Font font = new Font("Sans Serif", Font.BOLD, 25);
        button.setSize(450, 50);
        button.setBorderPainted(false);
        button.setSelected(false);
        button.setFont(font);
        button.setFocusPainted(false);
        button.setForeground(new Color(0xffffff));
        button.setBackground(new Color(0x2C67BA));
        button.addActionListener(this);
        mainPanel.add(button, BorderLayout.SOUTH);
    }

    private void end() {
        if (score<200) {
            JLabel lose = new JLabel(new ImageIcon(Main.class.getResource(".").getPath() +  "/Images/lose.png"));
            mainPanel.add(lose, BorderLayout.CENTER);
            System.out.println("loser");
        } else {
            JLabel win = new JLabel(new ImageIcon(Main.class.getResource(".").getPath() +  "/Images/win.png"));
            mainPanel.add(win, BorderLayout.CENTER);
            System.out.println("Congratulations");
        }
        SwingUtilities.updateComponentTreeUI(mainPanel);
    }

    public void sumRound(boolean v) {
        if (v) {
            round ++;
        }
        jRound.setText(String.valueOf(round));
        SwingUtilities.updateComponentTreeUI(mainPanel);
        verifyRound();
    }

    public void sumScore(int n) {
        score += n;
        jScore.setText(String.valueOf(score));
        SwingUtilities.updateComponentTreeUI(mainPanel);
        System.out.println("score: " + score);
    }

    public int getScore() {
        return score;
    }

    public int getRound() {
        return round;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        exit(0);
    }
}
