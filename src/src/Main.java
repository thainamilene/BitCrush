import java.awt.Color;
import javax.swing.ImageIcon;

public class Main {

    public static void main(String[] args) {
    	Window window = new Window(); //Cria a janela do jogo
    	window.addImage(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Bitcrush.png"));
    	
    	window.addTips(new Tips(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip01.png"), new Color(0x2C67BA), new Color(0x4496EC)));
    	window.addTips(new Tips(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip02.png"), new Color(0x2C67BA), new Color(0x4496EC)));
    	window.addTips(new Tips(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip03.png"), new Color(0x457B3A), new Color(0x67AC5B)));
    	window.addTips(new Tips(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip04.png"), new Color(0x457B3A), new Color(0x67AC5B)));
    	window.addTips(new Tips(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip05.png"), new Color(0x457B3A), new Color(0x67AC5B)));
    	window.addTips(new Tips(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip06.png"), new Color(0x457B3A), new Color(0x67AC5B)));
    	window.addTips(new Tips(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip07.png"), new Color(0x457B3A), new Color(0x67AC5B)));
    	window.addTips(new Tips(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip08.png"), new Color(0xB63830), new Color(0xD73964)));
    	window.addTips(new Tips(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip09.png"), new Color(0x457B3A), new Color(0x67AC5B)));
    	window.addTips(new Tips(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip10.png"), new Color(0x2C67BA), new Color(0x4496EC)));
    	window.addTips(new Tips(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip11.png"), new Color(0xB63830), new Color(0xD73964)));
    	window.addTips(new Tips(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/tip12.png"), new Color(0xF8D859), new Color(0xF6C244)));
    	
    	window.setTitle(new ImageIcon(Main.class.getResource(".").getPath() +  "/Images/select.png"));
    	window.setLogo(new ImageIcon(Main.class.getResource(".").getPath() +  "/Images/name.png"));
    	
    	
    	NormalPiecesComponent initiator0 = new NormalPiecesComponent();
    	initiator0.addImage(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Piece01.png"));
        initiator0.addImage(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Piece02.png"));
        initiator0.addImage(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Piece03.png"));
        initiator0.addImage(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Piece04.png"));
        initiator0.addImage(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Piece05.png"));
        initiator0.addImage(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Piece06.png"));
        initiator0.addImage(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Piece07.png"));
        initiator0.addImage(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Piece08.png"));
        initiator0.addImage(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Piece09.png"));
        
        Bonus01Component initiator1 = new Bonus01Component();
        initiator1.addImage(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Bonus01.png"));
        
        Bonus02Component initiator2 = new Bonus02Component();
        initiator2.addImage(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Bonus02.png"));
        
        Bonus03Component initiator3 = new Bonus03Component();
        initiator3.addImage(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Bonus03.png"));
    }
}
