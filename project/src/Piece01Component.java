import javax.swing.*;

public class Piece01Component extends NormalPieces {
    private static final long serialVersionUID = 4103450828886363736L;

    public Piece01Component() {
        super();
        setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "Images/Piece01.png"));
        setType();
    }

    private void setType() {
        this.x = 0;
        this.type = '0';
    }
    public char gettType() {
        return '0';
    }
}

