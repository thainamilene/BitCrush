import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private static final long serialVersionUID = -1282228310983130932L;
    private JPanel ScoreboardPanel, ButtonPanel;
    private BoardComponent BoardPanel;


    public Window() {
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        visual();
    }

    private void visual() {
        setSize(900, 900);
        Container mainPanel = getContentPane();
        mainPanel.setLayout(new BorderLayout());

        BoardPanel = new BoardComponent();
        BoardPanel.setLayout(new GridLayout());
        mainPanel.add(BoardPanel, BorderLayout.CENTER);

        ScoreboardPanel = new JPanel();
        ScoreboardPanel.setLayout(new BorderLayout());
        mainPanel.add(ScoreboardPanel, BorderLayout.NORTH);

        ButtonPanel = new JPanel();
        ButtonPanel.setLayout(new GridLayout());
        mainPanel.add(ButtonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void addBoardPanel(Pieces img, int index) {
        BoardPanel.add(img, index);
    }
}
