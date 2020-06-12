import javax.swing.*;
import java.util.Random;

public class BoardComponent extends JPanel implements IBoard{
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
                if (board[i-1][j].isDead()) {
                    System.out.print("D ");
                } else {
                    System.out.print(board[i - 1][j].getType() + " ");
                }
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
            if (!(board[xy.getSource()[0]][xy.getSource()[1]] instanceof NormalPiecesComponent && board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof NormalPiecesComponent)) {
                if (board[xy.getSource()[0]][xy.getSource()[1]] instanceof Bonus01Component || board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus01Component) {
                   if (board[xy.getSource()[0]][xy.getSource()[1]] instanceof Bonus01Component && board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus01Component) {
                       destroyBonus01plus01(xy.getTarget()[0], xy.getTarget()[1]);
                   } else if (board[xy.getSource()[0]][xy.getSource()[1]] instanceof Bonus02Component || board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus02Component) {
                       destroyBonus01plus02(xy.getTarget()[0], xy.getTarget()[1], xy.getSource()[0]);
                   } else if (board[xy.getSource()[0]][xy.getSource()[1]] instanceof Bonus03Component || board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus03Component)
                       destroyBonus01plus03();
                } else if (board[xy.getSource()[0]][xy.getSource()[1]] instanceof Bonus02Component || board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus02Component) {
                    if (board[xy.getSource()[0]][xy.getSource()[1]] instanceof Bonus02Component && board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus02Component) {
                        destroyBonus02plus02(xy.getTarget()[0], xy.getTarget()[1]);
                    } else if (board[xy.getSource()[0]][xy.getSource()[1]] instanceof Bonus03Component || board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus03Component) {
                        destroyBonus02plus03();
                    }
                } else if (board[xy.getSource()[0]][xy.getSource()[1]] instanceof Bonus03Component || board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus03Component) {
                    destroyBonus03plus03(xy.getTarget()[0], xy.getTarget()[1]);
                }
            } if (board[xy.getSource()[0]][xy.getSource()[1]].getMoves()[0].isV()) {
                if ((board[xy.getSource()[0]][xy.getSource()[1]] instanceof NormalPiecesComponent)) {
                    destroyNormalPieces(xy.getSource()[0], xy.getSource()[1], 0);
                } else if (board[xy.getSource()[0]][xy.getSource()[1]] instanceof Bonus01Component) {
                    destroyBonus01(xy.getSource()[0], xy.getSource()[1], xy.getTarget()[0], 0);
                } else if (board[xy.getSource()[0]][xy.getSource()[1]] instanceof Bonus02Component) {
                    destroyBonus02(xy.getTarget()[0], xy.getTarget()[1], 0);
                } else if (board[xy.getSource()[0]][xy.getSource()[1]] instanceof Bonus03Component) {
                    destroyBonus03(xy.getTarget()[0], xy.getTarget()[1], 0);
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
            System.out.println("sdfljskldjf = " + board[xy.getSource()[0]][xy.getSource()[1]].getMoves()[1].isV());
            if (board[xy.getSource()[0]][xy.getSource()[1]].getMoves()[1].isV()) {
                if (board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof NormalPiecesComponent) {
                    destroyNormalPieces(xy.getSource()[0], xy.getSource()[1], 1);
                } else if (board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus01Component) {
                    destroyBonus01(xy.getSource()[0], xy.getSource()[1], xy.getTarget()[0], 1);
                } else if (board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus02Component) {
                    destroyBonus02(xy.getSource()[0], xy.getSource()[1], 1);
                } else if (board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus03Component) {
                    destroyBonus03(xy.getSource()[0], xy.getSource()[1], 1);
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

    private void destroyNormalPieces(int l, int c, int k) {
        int i = 0;
        board[l][c].setDead(true);
        while (i!=5 && board[l][c].getMoves()[k].getVct()[i][0] != -1) {
            board[board[l][c].getMoves()[k].getVct()[i][0]][board[l][c].getMoves()[k].getVct()[i][1]].setDead(true);
            i++;
        }
        rebuildBoard(l, c, k);
    }

    private void destroyBonus01(int sl, int c, int tl,  int k) {
        if (sl == tl) {
            for (int i = 0; i < 9; i++) {
                board[sl][i].setDead(true);
            }
        } else {
            for (int i = 0; i < 9; i++) {
                board[i][c].setDead(true);
            }
        }
        rebuildBoard(sl, c, k);
    }

    private void destroyBonus02(int l, int c, int k) {
        board[l][c].setDead(true);
        if (l > 0) {
            board[l-1][c].setDead(true);
            if (c > 0) {
                board[l-1][c-1].setDead(true);
            }
            if (c < 8) {
                board[l-1][c+1].setDead(true);
            }
        }
        if (l < 8) {
            board[l+1][c].setDead(true);
            if (c > 0) {
                board[l+1][c-1].setDead(true);
            }
            if (c < 8) {
                board[l+1][c+1].setDead(true);
            }
        }
        if (c > 0) {
            board[l][c-1].setDead(true);
        }
        if (c < 8) {
            board[l][c+1].setDead(true);
        }
        rebuildBoard(l, c, k);
    }

    private void destroyBonus03(int l, int c, int k) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j].getType() == board[l][c].getType()) {
                    board[i][j].setDead(true);
                }
            }
        }
        rebuildBoard(l, c, k);
    }

    private void destroyBonus01plus01(int l, int c) {
        for (int i = 0; i < 9; i++) {
            board[l][i].setDead(true);
            board[i][c].setDead(true);
        }
        rebuildBoard(l, c, 0);
    }

    private void destroyBonus01plus02(int sl, int c, int tl) {
        if (sl == tl) {
            for (int i = 0; i < 9; i++) {
                board[sl][i].setDead(true);
                if (sl>0) {
                    board[sl - 1][i].setDead(true);
                }
                if (sl<8) {
                    board[sl + 1][i].setDead(true);
                }
            }
        } else {
            for (int i = 0; i < 9; i++) {
                board[i][c].setDead(true);
                if (c>0) {
                    board[i][c-1].setDead(true);
                }
                if (c<8) {
                    board[i][c+1].setDead(true);
                }
            }
        }
    }

    private void destroyBonus01plus03() {
        Random random = new Random();
        int x = random.nextInt(5);
        int[][] vct = new int[45][2];
        int cont = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j].getX() == x) {
                    board[i][j] = new Bonus01Component();
                    vct[cont][0] = i;
                    vct[cont][1] = j;
                    cont++;
                }
            }
        }
        for (int i = 0; i < cont; i++) {
            if (i%2 == 0) {
                destroyBonus01(vct[i][0], vct[i][1], vct[i][0], 0);
            } else {
                destroyBonus01(vct[i][0], vct[i][1], -1, 0);
            }
        }
    }

    private void destroyBonus02plus02(int c, int l) {
        board[l][c].setDead(true);
        if (l > 1) {
            board[l-1][c].setDead(true);
            board[l-2][c].setDead(true);
            if (c > 1) {
                board[l-1][c-1].setDead(true);
                board[l-1][c-2].setDead(true);
                board[l-2][c-1].setDead(true);
                board[l-2][c-2].setDead(true);
            } else if (c>0) {
                board[l-1][c-1].setDead(true);
                board[l-2][c-1].setDead(true);
            }
            if (c < 7) {
                board[l-1][c+1].setDead(true);
                board[l-1][c+2].setDead(true);
                board[l-2][c+1].setDead(true);
                board[l-2][c+2].setDead(true);
            } else if (c < 8) {
                board[l-1][c+1].setDead(true);
                board[l-2][c+1].setDead(true);
            }
        } else if (l == 1) {
            board[l-1][c].setDead(true);
            if (c > 1) {
                board[l-1][c-1].setDead(true);
                board[l-1][c-2].setDead(true);
            } else if (c>0) {
                board[l-1][c-1].setDead(true);
            }
            if (c < 7) {
                board[l-1][c+1].setDead(true);
                board[l-1][c+2].setDead(true);
            } else if (c < 8) {
                board[l-1][c+1].setDead(true);
            }
        }
        if (l < 7) {
            board[l+1][c].setDead(true);
            board[l+2][c].setDead(true);
            if (c > 1) {
                board[l+1][c-1].setDead(true);
                board[l+1][c-2].setDead(true);
                board[l+2][c-1].setDead(true);
                board[l+2][c-2].setDead(true);
            } else if (c>0) {
                board[l+1][c-1].setDead(true);
                board[l+2][c-1].setDead(true);
            }
            if (c < 7) {
                board[l+1][c+1].setDead(true);
                board[l+1][c+2].setDead(true);
                board[l+2][c+1].setDead(true);
                board[l+2][c+2].setDead(true);
            } else if (c < 8) {
                board[l+1][c+1].setDead(true);
                board[l+2][c+1].setDead(true);
            }
        } else if (l == 7) {
            board[l+1][c].setDead(true);
            if (c > 1) {
                board[l+1][c-1].setDead(true);
                board[l+1][c-2].setDead(true);
            } else if (c>0) {
                board[l+1][c-1].setDead(true);
            }
            if (c < 7) {
                board[l+1][c+1].setDead(true);
                board[l+1][c+2].setDead(true);
            } else if (c < 8) {
                board[l+1][c+1].setDead(true);
            }
        }
        if (c > 1) {
            board[l][c-1].setDead(true);
            board[l][c-2].setDead(true);
        } else if (c == 1) {
            board[l][c-1].setDead(true);
        }
        if (c < 7) {
            board[l][c+1].setDead(true);
            board[l][c+2].setDead(true);
        } else if (c == 7) {
            board[l][c+1].setDead(true);
        }
    }

    private void destroyBonus02plus03() {
        Random random = new Random();
        int x = random.nextInt(5);
        int[][] vct = new int[45][2];
        int cont = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j].getX() == x) {
                    board[i][j] = new Bonus02Component();
                    vct[cont][0] = i;
                    vct[cont][1] = j;
                    cont++;
                }
            }
        }
        for (int i = 0; i < cont; i++) {
            destroyBonus02(vct[i][0], vct[i][1], 0);
        }
    }

    private void destroyBonus03plus03(int c, int l) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j].setDead(true);
            }
        }
        rebuildBoard(l, c, 0);
    }

    private void rebuildBoard(int l, int c, int k) {
      /*  int i = 0, j;
        while (i!=5 && board[l][c].getMoves()[k].getVct()[i][0] != -1) {
            if (!board[board[l][c].getMoves()[k].getVct()[i][0]][board[l][c].getMoves()[k].getVct()[i][1] + 1].isDead()) {
               j = board[l][c].getMoves()[k].getVct()[i][1];
               board[board[l][c].getMoves()[k].getVct()[i][0]][board[l][c].getMoves()[k].getVct()[i][1]] = new NormalPiecesComponent();
               board[board[l][c].getMoves()[k].getVct()[i][0]][board[l][c].getMoves()[k].getVct()[i][1]].setType(board[board[l][c].getMoves()[k].getVct()[i][0]][board[l][c].getMoves()[k].getVct()[i][1] + 1].getX());
               while (j!=8) {
                   board[board[l][c].getMoves()[k].getVct()[i][0]][j]
                   j++;
               }
            }
            i++;
            rebuildBoard(l, c, k);
        }*/
    }

    public void transformsPieces(char type) {

    }
}

