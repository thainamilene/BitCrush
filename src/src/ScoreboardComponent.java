import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Vector;

import static java.lang.System.exit;


public class ScoreboardComponent extends JPanel implements IScoreboard {
    private static final long serialVersionUID = 6251202006907429652L;
    private final Container mainPanel;
    private int score = 0;
    private int round = 1;
    private Vector <JLabel> scoreboard;
    private int scoreboardlength = 0;
    private JLabel win, lose;

    public ScoreboardComponent(Container mainPanel) {
        super();
        win = new JLabel();
        lose = new JLabel();
        scoreboard = new Vector<JLabel>();
        scoreboard.add(new JLabel("round"));
        scoreboard.add(new JLabel("score"));
        add(scoreboard.get(1));
        add(scoreboard.get(0));
        style();
        this.mainPanel = mainPanel;
    }

    public void sumRound() {
        /*Soma as rodadas se a jogada for válida*/
        round ++;
        scoreboard.get(0).setText(String.valueOf(round));
        SwingUtilities.updateComponentTreeUI(mainPanel);
    }

    public void sumScore(int n) {
        /*Soma um valor a pontuacao atual*/
        score += n;
        scoreboard.get(1).setText(String.valueOf(score));
        verifyRound();
        SwingUtilities.updateComponentTreeUI(mainPanel);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        exit(0); //encerra o programa
    }

    private void style() {
        setLayout(new GridLayout(1,0));
        setSize(450,62);
        setBackground(new Color(0x289dfd));

        scoreboard.get(1).setText(String.valueOf(score));
        scoreboard.get(1).setForeground(new Color(0xffffff));
        scoreboard.get(1).setHorizontalTextPosition(SwingConstants.CENTER);
        scoreboard.get(1).setVerticalTextPosition(SwingConstants.CENTER);

        scoreboard.get(0).setForeground(new Color(0xffffff));
        scoreboard.get(0).setText(String.valueOf(round));
        scoreboard.get(0).setHorizontalTextPosition(SwingConstants.CENTER);
        scoreboard.get(0).setVerticalTextPosition(SwingConstants.CENTER);

    }

    private void verifyRound() {
        if (round == 21) {
            end(); //termina o jogo
        }
    }

    private void addFinalButton() {
        /*adiciona o botao de sair*/
        JButton button = new JButton("Sair");
        Font font = new Font("Sans Serif", Font.BOLD, 25);
        button.setSize(450, 50);
        button.setBorderPainted(false);
        button.setSelected(false);
        button.setFont(font);
        button.setFocusPainted(false);
        button.setForeground(new Color(0xffffff));
        button.setBackground(new Color(0xD73964));
        button.addActionListener(this);
        mainPanel.add(button, BorderLayout.SOUTH);
    }

    private void end() {
        while (mainPanel.getComponents().length != 0) { //remove todos os componentes restantes do mainPanel
            mainPanel.remove(0);
        }
        addFinalButton(); //adiciona o botao de sair
        if (score<300) { //verifica se ganhou ou perdeu o jogo
            mainPanel.add(lose, BorderLayout.CENTER);
        } else {
            mainPanel.add(win, BorderLayout.CENTER);
        }
        SwingUtilities.updateComponentTreeUI(mainPanel);
    }

	public void addImage(ImageIcon image) {
		scoreboard.get(scoreboardlength).setIcon(image);
		scoreboardlength ++;
	}
	public void addWinImage(ImageIcon image) {
		win.setIcon(image);
	}
	public void addLoseImage(ImageIcon image) {
		lose.setIcon(image);
	}
}
