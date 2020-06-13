import javax.swing.*;

public class Piece04Component extends NormalPieces {
    private static final long serialVersionUID = -3086749112756299996L;

    public Piece04Component() {
        super();
        setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "Images/Piece04.png"));
        setType();
    }

    private void setType() {
        this.x = 3;
        this.type = '$';
    }
    public char gettType() {
        return '$';
    }
}
