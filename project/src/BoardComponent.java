import java.util.Random;

public class BoardComponent implements IBoard{
    public IPieces[][] board;
    public IScoreboard score;

    public BoardComponent() {
        board = new IPieces[9][9];
    }

    //complete
    private void verifyFirstBoard() {
        int[][] line = new int[3][2];
        int[][] column = new int[3][2];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

            }
        }
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
    public void movePieces(IMovementC xy) {

    }

    //To do
    public void transformsPieces(char type) {

    }
}
