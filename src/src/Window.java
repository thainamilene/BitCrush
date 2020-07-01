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
    private int cont = 0;
    private ButtonStyle01 back, jump, next;
    private JPanel buttons, buttonPanel;
    private JLabel imageLabel;
    private boolean v = false;
    private ButtonStyle01 play, rules;
    private Tips tip01, tip02, tip03, tip04, tip05, tip06, tip07, tip08, tip09, tip10, tip11, tip12;
    private ImageIcon image;

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
        imageLabel = new JLabel();
        imageLabel.setSize(450, 555);
        if (image != null) {
        	imageLabel.setIcon(image);
        }
        mainPanel.add(imageLabel, BorderLayout.CENTER);
        mainPanel.add(imageLabel, BorderLayout.CENTER);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout());
        buttonPanel.setSize(450, 50);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        rules = new ButtonStyle01(new Color(0x2C67BA), "Ver Regras");
        rules.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent actionEvent) {
                     rules();
                    }
                }
        );
        play = new ButtonStyle01(new Color(0x4496EC), "Jogar");
        play.addActionListener(this);
        buttonPanel.add(rules);
        buttonPanel.add(play);
        this.setVisible(true);
    }

    public void rules() {
        /*Criando a primeira página de dicas*/
    	cont = 0;

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
        
        createTips();

        mainPanel.add(buttons, BorderLayout.SOUTH);
        mainPanel.add(tip01, BorderLayout.CENTER);

        SwingUtilities.updateComponentTreeUI(mainPanel);
    }

    private void createTips() {
    	tip01 = new Tips(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip01.png"));
    	tip02 = new Tips(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip02.png"));
    	tip03 = new Tips(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip03.png"));
    	tip04 = new Tips(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip04.png"));
    	tip05 = new Tips(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip05.png"));
    	tip06 = new Tips(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip06.png"));
    	tip07 = new Tips(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip07.png"));
    	tip08 = new Tips(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip08.png"));
    	tip09 = new Tips(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip09.png"));
    	tip10 = new Tips(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip10.png"));
    	tip11 = new Tips(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip11.png"));
    	tip12 = new Tips(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip12.png"));
    	
    	tip01.setColors(new Color(0x2C67BA), new Color(0x4496EC));
    	tip02.setColors(new Color(0x2C67BA), new Color(0x4496EC));
    	tip03.setColors(new Color(0x457B3A), new Color(0x67AC5B));
    	tip04.setColors(new Color(0x457B3A), new Color(0x67AC5B));
    	tip05.setColors(new Color(0x457B3A), new Color(0x67AC5B));
    	tip06.setColors(new Color(0x457B3A), new Color(0x67AC5B));
    	tip07.setColors(new Color(0x457B3A), new Color(0x67AC5B));
    	tip08.setColors(new Color(0x457B3A), new Color(0x67AC5B));
    	tip09.setColors(new Color(0x457B3A), new Color(0x67AC5B));
    	tip10.setColors(new Color(0x2C67BA), new Color(0x4496EC));
    	tip11.setColors(new Color(0xB63830), new Color(0xD73964));
    	tip12.setColors(new Color(0xF8D859), new Color(0xF6C244));
    	
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
            mainPanel.add(tip01);
            back.setBackground(tip01.getColor01());
            jump.setBackground(tip01.getColor02());
            next.setBackground(tip01.getColor01());
        }
        if (cont == 1) {
            mainPanel.remove(1);
            mainPanel.add(tip02);
            back.setBackground(tip02.getColor01());
            jump.setBackground(tip02.getColor02());
            next.setBackground(tip02.getColor01());
        }
        if (cont == 2) {
            mainPanel.remove(1);
            mainPanel.add(tip03);
            back.setBackground(tip03.getColor01());
            jump.setBackground(tip03.getColor02());
            next.setBackground(tip03.getColor01());
        }
        if (cont == 3) {
            mainPanel.remove(1);
            mainPanel.add(tip04);
            back.setBackground(tip04.getColor01());
            jump.setBackground(tip04.getColor02());
            next.setBackground(tip04.getColor01());
        }
        if (cont == 4) {
            mainPanel.remove(1);
            mainPanel.add(tip05);
            back.setBackground(tip05.getColor01());
            jump.setBackground(tip05.getColor02());
            next.setBackground(tip05.getColor01());
        }
        if (cont == 5) {
            mainPanel.remove(1);
            mainPanel.add(tip06);
            back.setBackground(tip06.getColor01());
            jump.setBackground(tip06.getColor02());
            next.setBackground(tip06.getColor01());
        }
        if (cont == 6) {
            mainPanel.remove(1);
            mainPanel.add(tip07);
            back.setBackground(tip07.getColor01());
            jump.setBackground(tip07.getColor02());
            next.setBackground(tip07.getColor01());
        }
        if (cont == 7) {
            mainPanel.remove(1);
            mainPanel.add(tip08);
            back.setBackground(tip08.getColor01());
            jump.setBackground(tip08.getColor02());
            next.setBackground(tip08.getColor01());
        }
        if (cont == 8) {
            mainPanel.remove(1);
            mainPanel.add(tip09);
            back.setBackground(tip09.getColor01());
            jump.setBackground(tip09.getColor02());
            next.setBackground(tip09.getColor01());
        }
        if (cont == 9) {
            mainPanel.remove(1);
            mainPanel.add(tip10);
            back.setBackground(tip10.getColor01());
            jump.setBackground(tip10.getColor02());
            next.setBackground(tip10.getColor01());
        }
        if (cont == 10) {
            mainPanel.remove(1);
            mainPanel.add(tip11);
            back.setBackground(tip11.getColor01());
            jump.setBackground(tip11.getColor02());
            next.setBackground(tip11.getColor01());
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
            mainPanel.add(tip12);
            back.setBackground(tip12.getColor01());
            jump.setBackground(tip12.getColor02());
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
                       IBoard BoardPanel = new BoardComponent(5, mainPanel); //cria o campo celular que se da o jogo
                    }
                }
        );
        lvl2.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent actionEvent) {
                    	IBoard BoardPanel = new BoardComponent(7, mainPanel); //cria o campo celular que se da o jogo
                    }
                }
        );
        lvl3.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent actionEvent) {
                    	IBoard BoardPanel = new BoardComponent(8, mainPanel); //cria o campo celular que se da o jogo
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

    public void addImageInitial(ImageIcon image) {
    	imageLabel.setIcon(image);
    	this.image = image;
    }
    
    public void actionPerformed(ActionEvent actionEvent) {
        startGame(); //inicializa o jogo
    }
    
    public ButtonStyle01 getButton01(int index) {
    	if (index == 0) {
    		return rules;
    	} 
    	if (index == 1) {
    		return play;
    	}
    	return null;
    }
    
    public void removeButton01 (int index) {
	   buttonPanel.remove(index);
    }
    
    public Tips getTip (int cont) {
    	if (cont == 0) {
            return tip01;
        }
        if (cont == 1) {
        	return tip02;
        }
        if (cont == 2) {
        	return tip03;
        }
        if (cont == 3) {
        	return tip04;
        }
        if (cont == 4) {
        	return tip05;
        }
        if (cont == 5) {
        	return tip06;
        }
        if (cont == 6) {
        	return tip07;
        }
        if (cont == 7) {
        	return tip08;
        }
        if (cont == 8) {
        	return tip09;
        }
        if (cont == 9) {
        	return tip10;
        }
        if (cont == 10) {
        	return tip11;
        }
        if (cont == 11) {
        	return tip12;
        }
        return null;
    }
}
