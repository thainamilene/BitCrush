public interface IBoard {

    IPieces[] getBoard();
    void movePieces() throws InvalidPlay;
}
