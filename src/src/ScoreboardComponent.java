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
import static java.lang.System.exit;


public class ScoreboardComponent extends JPanel implements IScoreboard {
    private static final long serialVersionUID = 6251202006907429652L;
    private final Container mainPanel;
    private int score = 0;
    private int round = 0;
    private JLabel jScore, jRound;

    public ScoreboardComponent(Container mainPanel) {
        super();
        style();
        this.mainPanel = mainPanel;
    }

    public void sumRound() {
        /*Soma as rodadas se a jogada for válida*/
        round ++;
        jRound.setText(String.valueOf(round));
        SwingUtilities.updateComponentTreeUI(mainPanel);
    }

    public void sumScore(int n) {
        /*Soma um valor a pontuacao atual*/
        score += n;
        jScore.setText(String.valueOf(score));
        verifyRound();
        SwingUtilities.updateComponentTreeUI(mainPanel);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        exit(0); //encerra o programa
    }

    private void style() {
        setLayout(new GridLayout());
        setSize(450,62);
        setBackground(new Color(0x289dfd));

        jScore = new JLabel(new ImageIcon(Main.class.getResource(".").getPath() +  "/Images/score.png"));
        jScore.setText(String.valueOf(score));
        jScore.setForeground(new Color(0xffffff));
        jScore.setHorizontalTextPosition(SwingConstants.CENTER);
        jScore.setVerticalTextPosition(SwingConstants.CENTER);

        jRound = new JLabel(new ImageIcon(Main.class.getResource(".").getPath() +  "/Images/round.png"));
        jRound.setForeground(new Color(0xffffff));
        jRound.setText(String.valueOf(round));
        jRound.setHorizontalTextPosition(SwingConstants.CENTER);
        jRound.setVerticalTextPosition(SwingConstants.CENTER);

        add(jScore);
        add(jRound);
    }

    private void verifyRound() {
        if (round == 20) {
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
            JLabel lose = new JLabel(new ImageIcon(Main.class.getResource(".").getPath() +  "/Images/lose.png"));
            mainPanel.add(lose, BorderLayout.CENTER);
        } else {
            JLabel win = new JLabel(new ImageIcon(Main.class.getResource(".").getPath() +  "/Images/win.png"));
            mainPanel.add(win, BorderLayout.CENTER);
        }
        SwingUtilities.updateComponentTreeUI(mainPanel);
    }
}