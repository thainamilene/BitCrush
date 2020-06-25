public interface ITranslateMovementC {
    int getSource();
    int getTarget();
    void setTarget(int target) throws NonAdjacentPieces;
    void setSource(int source);
}
