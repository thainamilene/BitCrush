import java.awt.*;

public interface IBoardManager {
    void assembleBoard(int lv, Container mainPanel);
    void printBoard();

    void translate(int[] position);
}