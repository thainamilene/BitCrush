import javax.swing.*;

public class Piece08Component extends NormalPieces {
    private static final long serialVersionUID = -5703589171751732281L;

    public Piece08Component() {
        super();
        setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "Images/Piece08.png"));
        setType();
    }

    private void setType() {
        this.x = 7;
        this.type = '§';
    }
    public char gettType() {
        return '§';
    }
}
