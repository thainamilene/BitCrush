import javax.swing.*;
import java.awt.*;

public class ButtonStyle01 extends JButton {
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
}
