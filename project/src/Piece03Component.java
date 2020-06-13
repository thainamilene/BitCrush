import javax.swing.*;

public class Piece03Component extends NormalPieces {
    private static final long serialVersionUID = 6612432957059081939L;

    public Piece03Component() {
        super();
        setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "Images/Piece03.png"));
        setType();
    }

    private void setType() {
        this.x = 2;
        this.type = '3';
    }
    public char gettType() {
        return '3';
    }
}
