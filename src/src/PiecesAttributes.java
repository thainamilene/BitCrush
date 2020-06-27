import javax.swing.JButton;

public interface PiecesAttributes {
    void setType(int x);
    char getType();
    int getX();
    IMovement[] getMoves();
    void setIndex(int index);
    int getIndex();
    void setBoard(IBoard Board);
    JButton getButton();
}
