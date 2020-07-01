import javax.swing.Icon;
import javax.swing.JButton;
import java.awt.Insets;

public class ButtonStyle02 extends JButton {
   
	private static final long serialVersionUID = -9065299942788009861L;

	public ButtonStyle02(Icon icon) {
        super(icon);
        visual();
    }

    private void visual() {
        setSize(150, 450);
        setMargin(new Insets(-3,-3,-3,-3));
        setBorderPainted(true);
    }
    
    public void changeIcon (Icon icon) {
    	setIcon (icon);
    }
}
