public class TranslateMovement{
    private static int source; //index da primeira peca selecionada
    private static int target; //index da primeira segunda selecionada
    private static int cont = 0;
    private static IBoard Board;
    
    public static void addIndex (int index) throws InvalidPlay {
    	if (cont == 0) {
    		setSource(index);
    		cont ++;
    	} else {
    		cont = 0;
    		setTarget(index);
    	}
    }
    
    public static int getSource() {
    	return source;
    }
    
    public static int getTarget() {
    	return target;
    }

    private static void setSource(int source1) {
        source = source1;
    }

    private static void setTarget(int target1) throws InvalidPlay {
        target = target1;
        compareSourceAndTarget();
    }

    private static void compareSourceAndTarget() throws InvalidPlay{
        if ((source-target)*(source-target) != 1 && (source-target)*(source-target) != 81) {
            throw new NonAdjacentPieces("Peças não adjacentes");
        }
        if (source > target) { //garante que o source esta sempre a esquerda ou em cima do target (o source sempre tera o index menor que o target)
            int aux = target;
            target = source;
            source = aux;
        }
        Board.movePieces();
    }

	public static void setBoard(BoardComponent Board) {
		TranslateMovement.Board = Board;
	}
}
