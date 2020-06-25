public interface IBoard {

    void printBoard();
    void translate(int position) throws InvalidPlay;
    IPieces[] getBoard();
}
