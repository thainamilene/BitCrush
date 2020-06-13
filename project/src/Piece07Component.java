import javax.swing.*;

public class Piece07Component extends NormalPieces{
    private static final long serialVersionUID = 740585015752277972L;

    public Piece07Component() {
        super();
        setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "Images/Piece08.png"));
        setType();
    }

    private void setType() {
        this.x = 6;
        this.type = '&';
    }
    public char gettType() {
        return '&';
    }
}
