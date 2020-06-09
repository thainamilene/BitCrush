import java.util.Random;

public class BoardComponent implements IBoard{
    public IPieces[][] board;
    public IScoreboard score;

    public BoardComponent() {
        board = new IPieces[9][9];
    }

    private void verifyFirstBoard(int lv) {
        Random random = new Random();
        boolean v = false;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (j < 7) {
                    if (board[i][j].getType() == board[i][j + 1].getType() && board[i][j].getType() == board[i][j + 2].getType()) {
                        v = isVForVerifyFirstBoard(lv, random, i, j);
                    }
                }
                
                if (i < 7) {
                    if (board[i][j].getType() == board[i+1][j].getType() && board[i][j].getType() == board[i+2][j].getType()) {
                        v = isVForVerifyFirstBoard(lv, random, i, j);
                    }
                }
           
                if (0 < j && j < 8) {
                    if (board[i][j].getType() == board[i][j + 1].getType() && board[i][j].getType() == board[i][j - 1].getType()) {
                        v = isVForVerifyFirstBoard(lv, random, i, j);
                    }
                }
                
                if (i>0 && i<8) {
                    if (board[i][j].getType() == board[i-1][j].getType() && board[i][j].getType() == board[i + 1][j].getType()) {
                        v = isVForVerifyFirstBoard(lv, random, i, j);
                    }
                }
            }
        }
        if (v) {
            verifyFirstBoard(lv);
        }
    }

    private boolean isVForVerifyFirstBoard(int lv, Random random, int i, int j) {
        boolean v;
        int x;
        char aux;
        v = true;
        x = random.nextInt(lv);
        aux = board[i][j].getType();
        board[i][j].setType(x);
        if (board[i][j].getType() == aux) {
            if (x > 0) {
                board[i][j].setType(x - 1);
            } else {
                board[i][j].setType(x + 1);
            }
        }
        return v;
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
        verifyFirstBoard(lv);
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
