public interface IBoardManager {
    void printBoard();

    void translate(int position);

    IPieces[] getBoard();
}