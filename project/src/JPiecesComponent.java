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
        setBorderPainted(true);
        setBackground(new Color(0x847C9D));
    }
    public void setPieces (IPieces pieces) {
        this.pieces = pieces;
        ImageIcon image = new ImageIcon(pieces.getImageIcon());
        super.setIcon(image);
        this.addActionListener(pieces);
    }
}
