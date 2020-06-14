import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener {
    private static final long serialVersionUID = -1282228310983130932L;
    private JPanel ScoreboardPanel, buttonPanel, imageLabel;
    private BoardComponent BoardPanel;
    Container mainPanel;


    public Window() {
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        visual();
    }

    private void visual() {
        setSize(450, 555);;
        mainPanel = getContentPane();
        mainPanel.setLayout(new BorderLayout());
        imageLabel = new JPanel();
        imageLabel.setLayout(new BorderLayout());
        mainPanel.add(imageLabel, BorderLayout.CENTER);

        imageLabel.setSize(450, 555);
        JLabel images = new JLabel(new ImageIcon(Main.class.getResource(".").getPath() +  "/Images/Bitcrush.png"));
        imageLabel.add(images);
        buttonPanel = new JPanel();
        mainPanel.add(imageLabel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        buttonPanel.setLayout(new GridLayout());
        buttonPanel.setSize(450, 50);
        JButton rules = buttonStyle(new Color(0x2C67BA), "Ver Regras");
        rules.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent actionEvent) {
                      rules();
                    }
                }
        );
        JButton play = buttonStyle(new Color(0x4496EC), "Jogar");
        play.addActionListener(this);
        buttonPanel.add(rules);
        buttonPanel.add(play);
        setVisible(true);
    }

    private void rules() {
    }

    public void setGame() {
        mainPanel.setBackground(new Color(0xffffff));
        mainPanel.remove(0);
        JButton lvl1 = buttonStyle(new Color(0x2C67BA), "Nível 01");
        JButton lvl2 = buttonStyle(new Color(0x2C67BA), "Nível 02");
        JButton lvl3 = buttonStyle(new Color(0x2C67BA), "Nível 03");

        JLabel title = new JLabel("Selecione o nível");
        lvl1.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent actionEvent) {
                        BoardPanel = new BoardComponent(5, mainPanel);
                    }
                }
        );
        lvl2.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent actionEvent) {
                        //BoardPanel = new BoardComponent(7);
                    }
                }
        );
        lvl3.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent actionEvent) {
                        BoardPanel = new BoardComponent(9, mainPanel);
                    }
                }
        );
        mainPanel.remove(0);
        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(lvl1, BorderLayout.WEST);
        mainPanel.add(lvl2, BorderLayout.CENTER);
        mainPanel.add(lvl3, BorderLayout.EAST);
/*        ScoreboardPanel = new JPanel();
        mainPanel.add(BoardPanel, BorderLayout.CENTER);
        ScoreboardPanel.setLayout(new BorderLayout());
        mainPanel.add(ScoreboardPanel, BorderLayout.NORTH);*/
        SwingUtilities.updateComponentTreeUI(this);

    }

    private JButton buttonStyle (Color color, String text) {
        JButton button = new JButton(text);
        Font font = new Font("Sans Serif", Font.BOLD, 25);
        button.setSize(225, 50);
        button.setBorderPainted(false);
        button.setSelected(false);
        button.setFont(font);
        button.setFocusPainted(false);
        button.setForeground(new Color(0xffffff));
        button.setBackground(color);
        return button;
    }


    public void addBoardPanel(JPiecesComponent img, int index) {
        BoardPanel.add(img, index);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        setGame();
    }
    public Component add(Component component) {
        super.add(component);
        SwingUtilities.updateComponentTreeUI(this);
        return component;
    }
}
