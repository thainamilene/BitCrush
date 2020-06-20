import javax.swing.*;

public interface PiecesAttributes {
    void setType(int x);
    char getType();
    int getX();
    IMovementAttributes[] getMoves();
    void setIndex(int index);
    int getIndex();
    void setBoard(IBoard Board);
    JButton getButton();
}
