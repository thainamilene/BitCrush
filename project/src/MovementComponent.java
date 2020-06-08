public class MovementComponent implements IMovementAttributes{
    boolean v;
    char movetype;
    int[][] vct;
    int cont;

    public MovementComponent() {
        vct = new int[5][2];
        v = false;
        cont = 0;
        movetype = 'n';
    }

    public void setMovetype(char movetype) {
        this.movetype = movetype;
    }

    public void setVct(int[] add) {
        if (cont < 5) {
            vct[cont] =  add;
            cont++;
        }
    }

    public void setV(boolean v) {
        this.v = v;
    }

    public char getMovetype() {
        return movetype;
    }

    public int[][] getVct() {
        return vct;
    }

    public boolean isV() {
        return v;
    }
}
