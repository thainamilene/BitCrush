public class MovementComponent implements IMovementAttributes{
    protected boolean v;
    protected char movetype;
    protected int[][] vct;
    protected int cont;

    public MovementComponent() {
        vct = new int[5][2];
        v = false;
        cont = 0;
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
