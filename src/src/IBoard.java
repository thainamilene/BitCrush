public interface IBoard {

    void translate(int position) throws InvalidPlay;
    IPieces[] getBoard();
}
