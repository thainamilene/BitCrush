public interface IMovementAttributes {

     void setMoveType(char moveType);

    void setVct(int index);

    void setV(boolean v);

    char getMovetype();

    int[] getVct();

    boolean isV();

    void remove();
}
