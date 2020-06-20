
public class MovementComponent implements IMovementAttributes{
    boolean v;
    char moveType;
    int[][] vct;
    int cont;

    public MovementComponent() {
        vct = new int[5][2];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                vct[i][j] = -1;
            }
        }
        cont = 0;
        v = false;
        moveType = ' ';
    }

    public void setMoveType(char moveType) {
        this.moveType = moveType;
    }

    public void setVct(int[] add) {
        vct[cont] = add;
        cont ++;
    }

    public void setV(boolean v) {
        this.v = v;
    }

    public char getMovetype() {
        return moveType;
    }

    public int[][] getVct() {
        return vct;
    }

    public boolean isV() {
        return v;
    }

    public void remove() {
        cont --;
    }
}
