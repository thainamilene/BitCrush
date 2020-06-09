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
                        v = removePieceFirstBoard(lv, random, i, j);
                    }
                }
                if (i < 7) {
                    if (board[i][j].getType() == board[i+1][j].getType() && board[i][j].getType() == board[i+2][j].getType()) {
                        v = removePieceFirstBoard(lv, random, i, j);
                    }
                }
                if (0 < j && j < 8) {
                    if (board[i][j].getType() == board[i][j + 1].getType() && board[i][j].getType() == board[i][j - 1].getType()) {
                        v = removePieceFirstBoard(lv, random, i, j);
                    }
                }
                if (i>0 && i<8) {
                    if (board[i][j].getType() == board[i-1][j].getType() && board[i][j].getType() == board[i + 1][j].getType()) {
                        v = removePieceFirstBoard(lv, random, i, j);
                    }
                }
            }
        }
        if (v) {
            verifyFirstBoard(lv);
        }
    }

    private boolean removePieceFirstBoard(int lv, Random random, int i, int j) {
        int x = random.nextInt(lv);
        char aux = board[i][j].getType();
        board[i][j].setType(x);
        if (board[i][j].getType() == aux) {
            if (x > 0) {
                board[i][j].setType(x - 1);
            } else {
                board[i][j].setType(x + 1);
            }
        }
        return true;
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
        boolean v = board[xy.getSource()[0]][xy.getSource()[1]].verifyMovement(xy, board);
        IPieces aux = board[xy.getSource()[0]][xy.getSource()[1]];
        if (v) {
            if (board[xy.getSource()[0]][xy.getSource()[1]].getMoves()[0].isV()) {
                if (board[xy.getSource()[0]][xy.getSource()[1]] instanceof NormalPiecesComponent) {
                    destroyNormalPieces(xy.getSource()[0],xy.getSource()[1]);
                } else if (board[xy.getSource()[0]][xy.getSource()[1]] instanceof Bonus01Component) {
                    destroyBonus01(xy.getSource()[0],xy.getSource()[1]);
                } else if (board[xy.getSource()[0]][xy.getSource()[1]] instanceof Bonus02Component) {
                    destroyBonus02(xy.getSource()[0], xy.getSource()[1]);
                } else if (board[xy.getSource()[0]][xy.getSource()[1]] instanceof Bonus03Component) {
                    destroyBonus02(xy.getSource()[0], xy.getSource()[1]);
                }
            } else {
                if (board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof NormalPiecesComponent) {
                    board[xy.getSource()[0]][xy.getSource()[1]] = new NormalPiecesComponent();
                } else if (board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus01Component) {
                    board[xy.getSource()[0]][xy.getSource()[1]] = new Bonus01Component();
                } else if (board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus02Component) {
                    board[xy.getSource()[0]][xy.getSource()[1]] = new Bonus02Component();
                } else if (board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus03Component) {
                    board[xy.getSource()[0]][xy.getSource()[1]] = new Bonus03Component();
                }
                board[xy.getSource()[0]][xy.getSource()[1]] = board[xy.getTarget()[0]][xy.getTarget()[1]];
            }
            if (board[xy.getTarget()[0]][xy.getTarget()[1]].getMoves()[0].isV()) {
                if (board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof NormalPiecesComponent) {
                    destroyNormalPieces(xy.getTarget()[0],xy.getTarget()[1]);
                } else if (board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus01Component) {
                    destroyBonus01(xy.getTarget()[0],xy.getTarget()[1]);
                } else if (board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus02Component) {
                    destroyBonus02(xy.getTarget()[0], xy.getTarget()[1]);
                } else if (board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus03Component) {
                    destroyBonus02(xy.getTarget()[0], xy.getTarget()[1]);
                }
            } else {
                 if (aux instanceof NormalPiecesComponent) {
                    board[xy.getTarget()[0]][xy.getTarget()[1]] = new NormalPiecesComponent();
                } else if (aux instanceof Bonus01Component) {
                    board[xy.getTarget()[0]][xy.getTarget()[1]] = new Bonus01Component();
                } else if (aux instanceof Bonus02Component) {
                    board[xy.getTarget()[0]][xy.getTarget()[1]] = new Bonus02Component();
                } else if (aux instanceof Bonus03Component) {
                    board[xy.getTarget()[0]][xy.getTarget()[1]] = new Bonus03Component();
                }
                 board[xy.getTarget()[0]][xy.getTarget()[1]] = aux;
            }
        }
    }

    private void destroyBonus01(int l, int c) {

    }

    private void destroyNormalPieces(int l, int c) {

    }

    //To do
    public void transformsPieces(char type) {

    }
    private void destroyBonus02(int i, int i1) {
    }
}

