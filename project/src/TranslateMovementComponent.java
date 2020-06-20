public class TranslateMovementComponent implements ITranslateMovementC {
    int source;
    int target;

    public void setSource(int source) {
        this.source = source;
    }

    public void setTarget(int target) {
        this.target = target;
        compareSourceAndTarget();
    }

    public int getSource() {
        return source;
    }

    public int getTarget() {
        return target;
    }

    private void compareSourceAndTarget() {
        if (source > target) { //garantir que o source esta sempre a esquerda ou em cima do target
            int aux = target;
            target = source;
            source = aux;
        }
    }
}
