import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class Window extends JFrame implements IImages, ActionListener  {
    private static final long serialVersionUID = -1282228310983130932L;
    private Container mainPanel;
    private int cont = 0;
    private ButtonStyle01 back, jump, next;
    private JPanel buttons, buttonPanel;
    private JLabel imageLabel, title, logo;
    private boolean v = false;
    private ButtonStyle01 play, rules;
    private ButtonStyle02 lvl1, lvl2, lvl3;
    private Vector<Tips> tips;
    private int tipsLength = 0;
    private ImageIcon image;

    public Window() {
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        tips = new Vector<Tips>();
        title = new JLabel();
        logo = new JLabel();
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
        mainPanel.add(buttons, BorderLayout.SOUTH);
        mainPanel.add(tips.get(0), BorderLayout.CENTER);

        SwingUtilities.updateComponentTreeUI(mainPanel);
    }

	private void tips() {
        /* De acordo com o contador vai para a pagina especifica*/

        if (cont == -1) {
            mainPanel.remove(1);
            mainPanel.remove(0);
            visual();
        } else if (cont == tipsLength - 1) {
            v = true;
            mainPanel.remove(1);
            buttons.remove(next);
            jump.setText("Jogar");
            mainPanel.add(tips.get(cont));
            back.setBackground(tips.get(cont).getColor01());
            jump.setBackground(tips.get(cont).getColor02());
        } else if (cont == tipsLength - 2) {
            mainPanel.remove(1);
            mainPanel.add(tips.get(cont));
            back.setBackground(tips.get(cont).getColor01());
            jump.setBackground(tips.get(cont).getColor02());
            next.setBackground(tips.get(cont).getColor01());
            if (v) {
                buttons.add(next);
                jump.setText("Pular");
            }
        } else {
            mainPanel.remove(1);
            mainPanel.add(tips.get(cont));
            back.setBackground(tips.get(cont).getColor01());
            jump.setBackground(tips.get(cont).getColor02());
            next.setBackground(tips.get(cont).getColor01());
        }
        
        
        SwingUtilities.updateComponentTreeUI(mainPanel);
    }

    private void startGame() {
        /* Inicializa o jogo */
        mainPanel.remove(0);
        lvl1 = new ButtonStyle02 (new ImageIcon(Main.class.getResource(".").getPath() +  "/Images/lvl1.png"));
        lvl2 = new ButtonStyle02 (new ImageIcon(Main.class.getResource(".").getPath() +  "/Images/lvl2.png"));
        lvl3 = new ButtonStyle02 (new ImageIcon(Main.class.getResource(".").getPath() +  "/Images/lvl3.png"));

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
        
        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(lvl1, BorderLayout.WEST);
        mainPanel.add(lvl2, BorderLayout.CENTER);
        mainPanel.add(lvl3, BorderLayout.EAST);
        mainPanel.add(logo, BorderLayout.SOUTH);
        
        SwingUtilities.updateComponentTreeUI(this);

    }

    public void addImage(ImageIcon image) {
    	imageLabel.setIcon(image);
    	this.image = image;
    }
    
    public void actionPerformed(ActionEvent actionEvent) {
        startGame(); //inicializa o jogo
    }
    
    public Component getButton01(int index) {
    	return buttonPanel.getComponent(index);
    }
    
    public void removeButton01 (int index) {
	   buttonPanel.remove(index);
    }
    
    public Tips getTip (int index) {
        return tips.get(index);
    }
    
    public void addTips(Tips tip) {
    	tips.add(tip);
    	tipsLength ++;
    }
    
    public void removeTips (int index) {
    	tips.remove(index);
    }
    
    public Component getTipsButtons (int index) {
    	return buttons.getComponent(index);
    }
    
    public void removeTipsButton (int index) {
 	   buttons.remove(index);
    }
    
    public ButtonStyle02 getLvlButton (int lv) {
    	if (lv == 1) {
    		return lvl1;
    	} 
    	if (lv == 2) {
    		return lvl2;
    	}
    	if (lv == 3) {
    		return lvl3;
    	}
		return null;
    }

    public void setTitle (Icon icon) {
    	title.setIcon(icon);
    }
    public void setTitle (String text) {
    	title.setText(text);
    }
    public void setLogo (Icon icon) {
    	logo.setIcon(icon);
    }
    public void setLogo (String text) {
    	logo.setText(text);
    }
}
