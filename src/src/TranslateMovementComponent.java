public class TranslateMovementComponent implements ITranslateMovementC {
    private int source; //index da primeira peca selecionada
    private int target; //index da primeira segunda selecionada

    public void setSource(int source) {
        this.source = source;
    }

    public void setTarget(int target) throws NonAdjacentPieces {
        this.target = target;
        compareSourceAndTarget();
    }

    public int getSource() {
        return source;
    }

    public int getTarget() {
        return target;
    }

    private void compareSourceAndTarget() throws NonAdjacentPieces{
        if ((source-target)*(source-target) != 1 && (source-target)*(source-target) != 81) {
            throw new NonAdjacentPieces("Peças não adjacentes");
        }
        if (source > target) { //garante que o source esta sempre a esquerda ou em cima do target (o source sempre tera o index menor que o target)
            int aux = target;
            target = source;
            source = aux;
        }
    }
}
