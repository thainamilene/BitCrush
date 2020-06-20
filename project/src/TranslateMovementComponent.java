public class TranslateMovementComponent implements ITranslateMovementC {
    int[] source;
    int[] target;
    String source2;
    String target2;

    public TranslateMovementComponent() {
        source = new int[2];
        target = new int[2];
    }

    public void setSource(int[] source) {
        this.source = source;
    }

    public void setTarget(int[] target) {
        this.target = target;
        compareSourceAndTarget();
    }

    private void compareSourceAndTarget() {
        System.out.println("here i am");
        if (source[1] > target[1]) { //garantir que o target esta sempre a direita do source se não estiverem na mesma coluna
            int[] aux = target;
            target = source;
            source = aux;
        }
        if (source[0] > target[0]) { // garantir que o  target esta sempre em baixo do source se não estiverem na mesma linha
            int[] aux = target;
            target = source;
            source = aux;
        }
    }

    public int[] getSource() {
        return source;
    }

    public String getSource2() {
        return source2;
    }

    public int[] getTarget() {
        return target;
    }

    public String getTarget2() {
        return target2;
    }
}
