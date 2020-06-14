import javax.swing.*;

public interface PiecesAttributes {
    void setType(int x);
    char getType();
    void setDead(boolean dead);
    boolean isDead();
    int getX();
    IMovementAttributes[] getMoves();
    void setIndex(int index);
    int getIndex();
    String getImageIcon();
    void setImageIcon(String imageIcon);
}
