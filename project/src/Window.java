import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener {
    private static final long serialVersionUID = -1282228310983130932L;
    Container mainPanel;
    private BoardComponent BoardPanel;
    private int cont = 0;
    private JButton back, jump, next;
    private JPanel buttons;
    boolean v = false;

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
        imageLabel.setSize(450, 555);
        mainPanel.add(imageLabel, BorderLayout.CENTER);
        JLabel images = new JLabel(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Bitcrush.png"));
        imageLabel.add(images);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout());
        buttonPanel.setSize(450, 50);
        mainPanel.add(imageLabel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        JButton rules = buttonStyle(new Color(0x2C67BA), "Ver Regras");
        rules.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent actionEvent) {
                     cont = 0;
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
        mainPanel.remove(0);
        mainPanel.remove(0);
        System.out.println("dska");
        buttons = new JPanel(new GridLayout());
        back = buttonStyle(new Color(0x4496EC),"Voltar");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                cont --;
                tips();
            }
        });
        jump = buttonStyle(new Color(0x2C67BA),"Pular");
        jump.addActionListener(this);
        next = buttonStyle(new Color(0x4496EC),"Próxima");
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cont ++;
                tips();
            }
        });
        buttons.add(back);
        buttons.add(jump);
        buttons.add(next);
        JLabel tip01 = new JLabel(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip01.png"));
        mainPanel.add(buttons, BorderLayout.SOUTH);
        mainPanel.add(tip01, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(mainPanel);
    }

    private void tips() {
        if (cont == -1) {
            mainPanel.remove(1);
            mainPanel.remove(0);
            visual();
        }
        if (cont == 0) {
            mainPanel.remove(1);
            mainPanel.add(new JLabel(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip01.png")));
            back.setBackground(new Color(0x2C67BA));
            jump.setBackground(new Color(0x4496EC));
            next.setBackground(new Color(0x2C67BA));
        }
        if (cont == 1) {
            mainPanel.remove(1);
            mainPanel.add(new JLabel(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip02.png")));
            back.setBackground(new Color(0x2C67BA));
            jump.setBackground(new Color(0x4496EC));
            next.setBackground(new Color(0x2C67BA));
        }
        if (cont == 2) {
            mainPanel.remove(1);
            mainPanel.add(new JLabel(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip03.png")));
            back.setBackground(new Color(0x457B3A));
            jump.setBackground(new Color(0x67AC5B));
            next.setBackground(new Color(0x457B3A));
        }
        if (cont == 3) {
            mainPanel.remove(1);
            mainPanel.add(new JLabel(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip04.png")));
            back.setBackground(new Color(0x457B3A));
            jump.setBackground(new Color(0x67AC5B));
            next.setBackground(new Color(0x457B3A));
        }
        if (cont == 4) {
            mainPanel.remove(1);
            mainPanel.add(new JLabel(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip05.png")));
            back.setBackground(new Color(0x457B3A));
            jump.setBackground(new Color(0x67AC5B));
            next.setBackground(new Color(0x457B3A));
        }
        if (cont == 5) {
            mainPanel.remove(1);
            mainPanel.add(new JLabel(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip06.png")));
            back.setBackground(new Color(0x457B3A));
            jump.setBackground(new Color(0x67AC5B));
            next.setBackground(new Color(0x457B3A));
        }
        if (cont == 6) {
            mainPanel.remove(1);
            mainPanel.add(new JLabel(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip07.png")));
            back.setBackground(new Color(0x457B3A));
            jump.setBackground(new Color(0x67AC5B));
            next.setBackground(new Color(0x457B3A));
        }
        if (cont == 7) {
            mainPanel.remove(1);
            mainPanel.add(new JLabel(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip08.png")));
            back.setBackground(new Color(0x74564A));
            jump.setBackground(new Color(0xE25341));
            next.setBackground(new Color(0x74564A));
        }
        if (cont == 8) {
            mainPanel.remove(1);
            mainPanel.add(new JLabel(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip09.png")));
            back.setBackground(new Color(0x457B3A));
            jump.setBackground(new Color(0x67AC5B));
            next.setBackground(new Color(0x457B3A));
        }
        if (cont == 9) {
            mainPanel.remove(1);
            mainPanel.add(new JLabel(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip10.png")));
            back.setBackground(new Color(0x2C67BA));
            jump.setBackground(new Color(0x4496EC));
            next.setBackground(new Color(0x2C67BA));
        }
        if (cont == 10) {
            mainPanel.remove(1);
            mainPanel.add(new JLabel(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip11.png")));
            back.setBackground(new Color(0xB63830));
            jump.setBackground(new Color(0xD73964));
            next.setBackground(new Color(0xB63830));
            if (v) {
                buttons.add(next);
                jump.setText("Pular");
            }
        }
        if (cont == 11) {
            v = true;
            mainPanel.remove(1);
            buttons.remove(next);
            jump.setText("Jogar");
            mainPanel.add(new JLabel(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip12.png")));
            back.setBackground(new Color(0xF8D859));
            jump.setBackground(new Color(0xF6C244));
        }
        SwingUtilities.updateComponentTreeUI(mainPanel);
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
                        BoardPanel = new BoardComponent(8, mainPanel);
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

    public void actionPerformed(ActionEvent actionEvent) {
        setGame();
    }

    public Component add(Component component) {
        super.add(component);
        SwingUtilities.updateComponentTreeUI(this);
        return component;
    }
}
