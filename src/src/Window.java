import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener {
    private static final long serialVersionUID = -1282228310983130932L;
    private Container mainPanel;
    private BoardComponent BoardPanel;
    private int cont = 0;
    private ButtonStyle01 back, jump, next;
    private JPanel buttons;
    boolean v = false;

    public Window() {
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        visual();
    }

    private void visual() {
    
        this.setSize(450, 580);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        mainPanel = getContentPane();
        mainPanel.setBounds(0,0,0,0);
        mainPanel.setLayout(new BorderLayout());
        /*Criando a página inicial*/
        JLabel imageLabel = new JLabel(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Bitcrush.png"));
        imageLabel.setSize(450, 555);
        mainPanel.add(imageLabel, BorderLayout.CENTER);
        mainPanel.add(imageLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout());
        buttonPanel.setSize(450, 50);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        ButtonStyle01 rules = new ButtonStyle01(new Color(0x2C67BA), "Ver Regras");
        rules.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent actionEvent) {
                     cont = 0;
                     rules();
                    }
                }
        );
        ButtonStyle01 play = new ButtonStyle01(new Color(0x4496EC), "Jogar");
        play.addActionListener(this);
        buttonPanel.add(rules);
        buttonPanel.add(play);
        this.setVisible(true);
    }

    private void rules() {
        /*Criando a primeira página de dicas*/

        mainPanel.remove(0);
        mainPanel.remove(0);
        buttons = new JPanel(new GridLayout());

        back = new ButtonStyle01(new Color(0x4496EC),"Voltar");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                cont --;
                tips(); //Volta para a dica anterior
            }
        });
        buttons.add(back);

        jump = new ButtonStyle01(new Color(0x2C67BA),"Pular");
        jump.addActionListener(this); //Pula as dicas e inicializa o jogo
        buttons.add(jump);

        next = new ButtonStyle01(new Color(0x4496EC),"Próxima");
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cont ++;
                tips(); //Vai para a proxima dica
            }
        });
        buttons.add(next);

        JLabel tip01 = new JLabel(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip01.png"));
        mainPanel.add(buttons, BorderLayout.SOUTH);
        mainPanel.add(tip01, BorderLayout.CENTER);

        SwingUtilities.updateComponentTreeUI(mainPanel);
    }

    private void tips() {
        /* De acordo com o contador vai para a pagina especifica*/

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

    private void startGame() {
        /* Inicializa o jogo */
        mainPanel.remove(0);
        ButtonStyle02 lvl1 = new ButtonStyle02 (new ImageIcon(Main.class.getResource(".").getPath() +  "/Images/lvl1.png"));
        ButtonStyle02 lvl2 = new ButtonStyle02 (new ImageIcon(Main.class.getResource(".").getPath() +  "/Images/lvl2.png"));
        ButtonStyle02 lvl3 = new ButtonStyle02 (new ImageIcon(Main.class.getResource(".").getPath() +  "/Images/lvl3.png"));

        /*seleciona os niveis*/

        lvl1.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent actionEvent) {
                        BoardPanel = new BoardComponent(5, mainPanel); //cria o campo celular que se da o jogo
                    }
                }
        );
        lvl2.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent actionEvent) {
                        BoardPanel = new BoardComponent(7, mainPanel); //cria o campo celular que se da o jogo
                    }
                }
        );
        lvl3.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent actionEvent) {
                        BoardPanel = new BoardComponent(8, mainPanel); //cria o campo celular que se da o jogo
                    }
                }
        );
        mainPanel.remove(0);
        JLabel title = new JLabel(new ImageIcon(Main.class.getResource(".").getPath() +  "/Images/select.png"));
        JLabel name = new JLabel(new ImageIcon(Main.class.getResource(".").getPath() +  "/Images/name.png"));

        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(lvl1, BorderLayout.WEST);
        mainPanel.add(lvl2, BorderLayout.CENTER);
        mainPanel.add(lvl3, BorderLayout.EAST);
        mainPanel.add(name, BorderLayout.SOUTH);

        SwingUtilities.updateComponentTreeUI(this);

    }

    public void actionPerformed(ActionEvent actionEvent) {
        startGame(); //inicializa o jogo
    }
}
