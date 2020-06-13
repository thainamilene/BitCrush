import javax.swing.*;

public class Piece05Component extends NormalPieces {
    private static final long serialVersionUID = -5864482811337604046L;

    public Piece05Component() {
        super();
        setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "Images/Piece05.png"));
        setType();
    }

    private void setType() {
        this.x = 4;
        this.type = '>';
    }
    public char gettType() {
        return '>';
    }
}
