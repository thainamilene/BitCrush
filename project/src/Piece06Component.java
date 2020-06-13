import javax.swing.*;

public class Piece06Component extends NormalPieces{
    private static final long serialVersionUID = -5703589171751732281L;

    public Piece06Component() {
        super();
        setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "Images/Piece06.png"));
        setType();
    }

    private void setType() {
        this.x = 5;
        this.type = '?';
    }
    public char gettType() {
        return '?';
    }
}
