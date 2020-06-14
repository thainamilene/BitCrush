import javax.swing.*;
import java.awt.*;

public class JPiecesComponent extends JButton {
    private static final long serialVersionUID = 6312441989891652136L;
    IPieces pieces;
    public JPiecesComponent() {
        super();
        style();
    }

    private void style() {
        setSize(50 , 50);
        setBorderPainted(false);
        setBackground(new Color(0x657B88));
    }
    public void setPieces (IPieces pieces) {
        this.pieces = pieces;
        System.out.println(pieces.getImageIcon());
        ImageIcon image = new ImageIcon(pieces.getImageIcon());
        super.setIcon(image);
        this.addActionListener(pieces);
    }
}
