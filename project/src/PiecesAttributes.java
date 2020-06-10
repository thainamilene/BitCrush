public interface PiecesAttributes {
    void setType(int x);
    char getType();
    void setDead(boolean dead);
    boolean isDead();
    int getX();
    IMovementAttributes[] getMoves();
}
