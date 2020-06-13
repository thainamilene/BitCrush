import javax.swing.*;

public class Piece09Component extends NormalPieces {
    private static final long serialVersionUID = 6304784601070650666L;

    public Piece09Component() {
        super();
        setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "Images/Piece09.png"));
        setType();
    }

    private void setType() {
        this.x = 5;
        this.type = 'º';
    }
    public char gettType() {
        return 'º';
    }
}
