import javax.swing.*;
import java.awt.*;

public class ButtonStyle02 extends JButton {
    public ButtonStyle02(Icon icon) {
        super(icon);
        visual();
    }

    private void visual() {
        setSize(150, 450);
        setMargin(new Insets(-3,-3,-3,-3));
        setBorderPainted(true);
    }
}
