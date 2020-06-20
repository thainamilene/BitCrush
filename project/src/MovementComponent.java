
public class MovementComponent implements IMovementAttributes{
    boolean v;
    char moveType;
    int[] vct;
    int cont;

    public MovementComponent() {
        vct = new int[5];
        for (int i = 0; i < 5; i++) {
            vct[i] = -1;
        }
        cont = 0;
        v = false;
        moveType = ' ';
    }

    public void setMoveType(char moveType) {
        this.moveType = moveType;
    }

    public void setVct(int index) {
        vct[cont] = index;
        cont ++;
    }

    public void setV(boolean v) {
        this.v = v;
    }

    public char getMovetype() {
        return moveType;
    }

    public int[] getVct() {
        return vct;
    }

    public boolean isV() {
        return v;
    }

    public void remove() {
        cont --;
    }
}
