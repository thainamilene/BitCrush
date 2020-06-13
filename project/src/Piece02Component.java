import javax.swing.*;

public class Piece02Component extends NormalPieces{
    private static final long serialVersionUID = 7620223557339690938L;

    public Piece02Component() {
        super();
        setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "Images/Piece02.png"));
        setType();
    }

    private void setType() {
        this.x = 1;
        this.type = '*';
    }
    public char gettType() {
        return '*';
    }
}
