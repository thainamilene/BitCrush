import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

public class Main {

    public static void main(String[] args) {
		Window window = new Window(); //Cria a janela do jogo
		window.addImageInitial(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Bitcrush.png"));
    }
}
