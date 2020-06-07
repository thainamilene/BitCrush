public interface IMovementAttributes {

     void setMovetype(char type);

    void setVct(int[] add);

    void setV(boolean v);

    char getMovetype();

    int[][] getVct();

    boolean isV();
}
