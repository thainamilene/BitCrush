public class TranslateMovementComponent implements ITranslateMovementC {
    int[] source;
    int[] target;
    String source2;
    String target2;

    public TranslateMovementComponent() {
        source = new int[2];
        target = new int[2];
    }

    public void translateMove(String move) {
        source2 = move.substring(0,2);
		target2 = move.substring(3,5);
        String ini = move.substring(0,1);
        String fim = move.substring(3,4);
        translateString(ini, source);
        translateString(fim, target);
        source[0] = Integer.parseInt(move.substring(1,2));
        target[0] = Integer.parseInt(move.substring(4,5));
        source[0]--;
        target[0]--;
        if (source[1] > target[1]) { //garantir que o target esta sempre a direita do source se não estiverem na mesma coluna
            int[] aux = target;
            target = source;
            source = aux;
        }
        if (source[0] > target[0]) { // garantir que o  target esta sempre em cima do source se não estiverem na mesma linha
            int[] aux = target;
            target = source;
            source = aux;
        }
    }

    private void translateString(String str, int[] source) {
        switch (str) {
            case "a":
                source[1] = 0;
                break;
            case "b":
                source[1] = 1;
                break;
            case "c":
                source[1] = 2;
                break;
            case "d":
                source[1] = 3;
                break;
            case "e":
                source[1] = 4;
                break;
            case "f":
                source[1] = 5;
                break;
            case "g":
                source[1] = 6;
                break;
            case "h":
                source[1] = 7;
                break;
            case "i":
                source[1] = 8;
                break;
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
