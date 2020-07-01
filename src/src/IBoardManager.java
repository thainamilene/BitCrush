
public interface IBoardManager {

    IPieces[] getBoard();
    void movePieces() throws InvalidPlay;
}
