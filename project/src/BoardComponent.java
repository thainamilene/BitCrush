import java.util.Random;

public class BoardComponent implements IBoard{
    public IPieces[][] board;
    public IScoreboard score;

    public BoardComponent() {
        board = new IPieces[9][9];
    }

    //complete com função de remover
    private void verifyFirstBoard() {
        int[][] line = new int[3][2];
        int[][] column = new int[3][2];
        boolean l, c;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {
               l = verifyFPieces(line, i, j);
               c = verifyFPieces(column, j, i);
            }
        }
    }
    //modificar talvez
    private boolean verifyFPieces(int[][] vector, int i, int j) {
        if (board[i][j].getType() == board[i+1][j].getType() && board[i][j].getType() == board[i+2][j].getType()) {
            vector[0][0] = i;
            vector[1][0] = i+1;
            vector[2][0] = i+2;
            vector[0][1] = j;
            vector[1][1] = j+1;
            vector[2][1] = j+2;
            return true;
        }
        return false;
    }

    //complete
    private void destroyNormalPieces(int[][] vector, int n) {
       /*  for (int i = 0; i < n; i++) {

        }*/
    }

    public void assembleBoard(int lv) {
        Random random = new Random();
        int x;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
               x = random.nextInt(lv);
               board[i][j] = new NormalPiecesComponent();
               board[i][j].setType(x);
            }
        }
        verifyFirstBoard();
        printBoard();
    }

    //Arrumar print
    public void printBoard() {
        System.out.println("------------------------------");
        System.out.println("RODADAS RESTANTES | PONTUAÇÃO");
        System.out.println((20-score.getRound()) + "|" + score.getScore());
        System.out.println("------------------------------");
        for (int i=9; i>0; i--) {
            System.out.print(i);
            System.out.print(' ');
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i - 1][j].getType() + " ");
            }
            System.out.println(" ");
        }
        System.out.println("  a b c d e f g h i\n");
    }

    // To do
    public void movePieces(ITranslateMovementC xy) {

    }

    //To do
    public void transformsPieces(char type) {

    }
}
