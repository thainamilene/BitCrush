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
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                vct[i][j] = -1;
            }
        }
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

    public void remove() {
        cont --;
    }
}
