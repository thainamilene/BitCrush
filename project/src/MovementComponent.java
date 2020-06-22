public class MovementComponent implements IMovementAttributes{
    private boolean v;
    private char moveType;
    private int[] vct;
    private int cont;

    public MovementComponent() {
        /*Guarda as informacoes da rodada atual*/
        vct = new int[5];
        for (int i = 0; i < 5; i++) {
            vct[i] = -1;
        }
        cont = 0;
        v = false;
        moveType = ' ';
    }

    public void setMoveType(char moveType) {
        /*guarda o tipo de movimento:
           'c' - para combinacoes de 3 pecas na vertical
           'l' - para combinacoes de 3 pecas na horizontal
           '1' - para combinacoes que geram o bonus tipo 1
           '2' - para combinacoes que geram o bonus tipo 2
           'b' - para combinacoes que geram o bonus tipo 3
           '+' - para peca do tipo 1
           '*' - para peca do tipo 2
           'o' - para peca do tipo 3
         */
        this.moveType = moveType;
    }

    public void setVct(int index) {
        /*guarda os index das posicoes a serem removidas*/
        vct[cont] = index;
        cont ++;
    }

    public void setV(boolean v) {
        /*guarda se a jogada e valida ou nao*/
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
        /*remove o ultimo elemento a ser adicionado, pois este sera sobrescrito*/
        cont --;
    }
}
