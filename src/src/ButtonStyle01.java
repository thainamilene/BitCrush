import javax.swing.*;
import java.awt.Color;
import java.awt.Font;

public class ButtonStyle01 extends JButton {
   
	private static final long serialVersionUID = 6393361553441226268L;

	public ButtonStyle01(Color color, String text) {
        super(text);
        visual(color);
    }

    private void visual(Color color) {
        Font font = new Font("Sans Serif", Font.BOLD, 25);
        setSize(225, 50);
        setBorderPainted(false);
        setSelected(false);
        setFont(font);
        setFocusPainted(false);
        setForeground(new Color(0xffffff));
        setBackground(color);
    }
    public void changeColor(Color color) {
    	setBackground(color);
    }
    
    public void changeFont (Font font) {
    	setFont(font);
    }
    public void changeTextColor (Color color) {
    	setForeground (color);
    }
    public void changeText (String text) {
    	setText(text);
    }
}
