import java.util.Random;

public class BoardComponent implements IBoard{
    public IPieces[][] board;
    public IScoreboard score;

    public BoardComponent() {
        board = new IPieces[9][9];
    }

    //complete com função de remover
    private void verifyFirstBoard(int lv, int ver) {
        int x;
        System.out.println("entrei aqui lalalalal");
        Random random = new Random();
        int[][] line = new int[3][2];
        int[][] column = new int[3][2];
        boolean l = false, c = false, v = false;
        for (int i = 8; i >= 0; i--) {
            for (int j = 8; j >= 0; j--) {
                if (j < 7) {
                    if(board[i][j].getType() == board[i][j + 1].getType() && board[i][j].getType() == board[i][j + 2].getType()) {
                        line[0][0] = i;
                        line[1][0] = i;
                        line[2][0] = i;
                        line[0][1] = j;
                        line[1][1] = j + 1;
                        line[2][1] = j + 2;
                        l = true;
                    }
                }
                if (j > 1)
                    if (board[j][i].getType() == board[j - 1][i].getType() && board[i][j].getType() == board[j - 2][i].getType()) {
                        column[0][0] = j;
                        column[1][0] = j + 1;
                        column[2][0] = j + 2;
                        column[0][1] = i;
                        column[1][1] = i;
                        column[2][1] = i;
                        c = true;
                    }
            }
            if(l) {
                v = true;
                for (int o = 0; o < 3; o++) {
                    x = random.nextInt(lv);
                    board[line[o][0]][line[o][1]].setType(x);
                }
            } else if (c) {
                v = true;
                for (int p = 0; p < 3; p++) {
                    x = random.nextInt(lv);
                    board[column[p][0]][column[p][1]].setType(x);
                }
            }
        }
        if (v) {
            verifyFirstBoard(lv, 1);
        } else if (ver == 1) {
            verifyFirstBoard(lv, 0);
        }
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
        printBoard();
        verifyFirstBoard(lv, 1);
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
