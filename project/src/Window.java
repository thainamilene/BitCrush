import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener {
    private static final long serialVersionUID = -1282228310983130932L;
    private JPanel ScoreboardPanel;
    private BoardComponent BoardPanel;
    Container mainPanel;


    public Window() {
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        visual();
    }

    private void visual() {
        setSize(450, 580);;
        mainPanel = getContentPane();
        mainPanel.setBounds(0,0,0,0);
        mainPanel.setLayout(new BorderLayout());
        JPanel imageLabel = new JPanel();
        imageLabel.setLayout(new BorderLayout());
        mainPanel.add(imageLabel, BorderLayout.CENTER);

        imageLabel.setSize(450, 555);
        JLabel images = new JLabel(new ImageIcon(Main.class.getResource(".").getPath() +  "/Images/Bitcrush.png"));
        imageLabel.add(images);
        JPanel buttonPanel = new JPanel();
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
        mainPanel.remove(0);
        JButton lvl1 = button2Style (new ImageIcon(Main.class.getResource(".").getPath() +  "/Images/lvl1.png"));
        JButton lvl2 = button2Style (new ImageIcon(Main.class.getResource(".").getPath() +  "/Images/lvl2.png"));
        JButton lvl3 = button2Style (new ImageIcon(Main.class.getResource(".").getPath() +  "/Images/lvl3.png"));

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
                        BoardPanel = new BoardComponent(7, mainPanel);
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
        JLabel title = titleStyle(Main.class.getResource(".").getPath() +  "/Images/select.png", 40);

        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(lvl1, BorderLayout.WEST);
        mainPanel.add(lvl2, BorderLayout.CENTER);
        mainPanel.add(lvl3, BorderLayout.EAST);
        JLabel name = titleStyle(Main.class.getResource(".").getPath() +  "/Images/name.png", 50);
        mainPanel.add(name, BorderLayout.SOUTH);
/*        ScoreboardPanel = new JPanel();
        mainPanel.add(BoardPanel, BorderLayout.CENTER);
        ScoreboardPanel.setLayout(new BorderLayout());
        mainPanel.add(ScoreboardPanel, BorderLayout.NORTH);*/
        SwingUtilities.updateComponentTreeUI(this);

    }

    private JLabel titleStyle(String text, int h) {
        JLabel label = new JLabel(new ImageIcon(text));
        label.setSize(450, h);
        label.setBackground(new Color(0));
        return label;
    }

     private JButton button2Style(ImageIcon image) {
        JButton button = new JButton(image);
        button.setSize(150, 450);
        button.setMargin(new Insets(-3,-3,-3,-3));
        button.setBorderPainted(true);
        return button;
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
