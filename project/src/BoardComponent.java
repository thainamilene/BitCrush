import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BoardComponent extends JPanel implements IBoard{
    private static final long serialVersionUID = -2624435075244032415L;
    public IPieces[][] board;
    public IScoreboard score;
    private ITranslateMovementC move;
    private int counter;
    private final Container mainPanel;
    private final int lv;

    public BoardComponent(int lv, Container mainPanel) {
        counter = 0;
        this.lv = lv;
        this.mainPanel = mainPanel;
        //mainPanel.remove(0);
        mainPanel.remove(1);
        mainPanel.remove(1);
        mainPanel.remove(1);
        mainPanel.setBackground(new Color(0xffffff));
        score = new ScoreboardComponent();
        board = new IPieces[9][9];
        setSize(450,450);
        setBackground(new Color(0x847C9D));
        assembleBoard();
    }

    private void assembleBoard() {
        Random random = new Random();
        int cont = 0;
        int x;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = new NormalPiecesComponent();
                x = random.nextInt(lv);
                board[i][j].setType(x);
                board[i][j].setIndex(cont);
                board[i][j].setPosition(i, j);
                board[i][j].setBoard(this);
                cont++;
            }
        }
        verifyFirstBoard();
        mainPanel.add(this, BorderLayout.CENTER);
        addNormalPieces(mainPanel);
        SwingUtilities.updateComponentTreeUI(mainPanel);
        printBoard();
    }

    private void verifyFirstBoard() {
        Random random = new Random();
        boolean v = false;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (j < 7) {
                    if (board[i][j].getType() == board[i][j + 1].getType() && board[i][j].getType() == board[i][j + 2].getType()) {
                        v = removePieceFirstBoard(random, i, j);
                    }
                }
                if (i < 7) {
                    if (board[i][j].getType() == board[i+1][j].getType() && board[i][j].getType() == board[i+2][j].getType()) {
                        v = removePieceFirstBoard(random, i, j);
                    }
                }
                if (0 < j && j < 8) {
                    if (board[i][j].getType() == board[i][j + 1].getType() && board[i][j].getType() == board[i][j - 1].getType()) {
                        v = removePieceFirstBoard(random, i, j);
                    }
                }
                if (i>0 && i<8) {
                    if (board[i][j].getType() == board[i-1][j].getType() && board[i][j].getType() == board[i + 1][j].getType()) {
                        v = removePieceFirstBoard(random, i, j);
                    }
                }
            }
        }
        if (v) {
            verifyFirstBoard();
        }
    }

    private boolean removePieceFirstBoard(Random random, int i, int j) {
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

    private void addPieces(int x, int i, int j, int index) {
        board[i][j].setType(x);
        board[i][j].setIndex(index);
        board[i][j].setPosition(i, j);
        board[i][j].setBoard(this);
        JPiecesComponent piece = new JPiecesComponent();
        piece.setPieces(board[i][j]);
        this.add(piece, index);
        SwingUtilities.updateComponentTreeUI(mainPanel);
    }

    private void addPieces(int i, int j, int index) {
        board[i][j].setIndex(index);
        board[i][j].setPosition(i, j);
        board[i][j].setBoard(this);
        JPiecesComponent piece = new JPiecesComponent();
        piece.setPieces(board[i][j]);
        this.add(piece, index);
        SwingUtilities.updateComponentTreeUI(mainPanel);
    }

    private void addNormalPieces(Container mainPanel) {
        setLayout(new GridLayout(9 ,9));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                JPiecesComponent piece = new JPiecesComponent();
                piece.setPieces(board[i][j]);
                this.add(piece);
            }
            SwingUtilities.updateComponentTreeUI(mainPanel);
        }
    }

    public void printBoard() {
        System.out.println("------------------------------");
        System.out.println("RODADAS RESTANTES | PONTUAÇÃO");
        System.out.println((20-score.getRound()) + "|" + score.getScore());
        System.out.println("------------------------------");
        for (int i=0; i < 9; i++) {
            System.out.print(i);
            System.out.print(' ');
            for (int j = 0; j < 9; j++) {
                if (board[i][j].isDead()) {
                    System.out.print("D ");
                } else {
                    System.out.print(board[i][j].getType() + " ");
                }
            }
            System.out.println(" ");
        }
        System.out.println("  a b c d e f g h i\n");
    }


    public void translate(int[] position) {
        if (counter==0) {
            move = new TranslateMovementComponent();
            move.setSource(position);
            counter ++;
        } else {
            move.setTarget(position);
            counter--;
            movePieces(move);
        }
    }

    //-----------------------------------------------------------------------------------------------------------------//

    public void movePieces(ITranslateMovementC xy) {
        if (!(board[xy.getSource()[0]][xy.getSource()[1]] instanceof NormalPiecesComponent && board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof NormalPiecesComponent)) {
            if (board[xy.getSource()[0]][xy.getSource()[1]] instanceof Bonus01Component || board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus01Component) {
                if (board[xy.getSource()[0]][xy.getSource()[1]] instanceof Bonus01Component && board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus01Component) {
                    destroyBonus01plus01(xy.getTarget()[0], xy.getTarget()[1]);
                } else if (board[xy.getSource()[0]][xy.getSource()[1]] instanceof Bonus01Component || board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus02Component) {
                    destroyBonus01plus02(xy.getTarget()[0], xy.getTarget()[1], xy.getSource()[0]);
                } else if (board[xy.getSource()[0]][xy.getSource()[1]] instanceof Bonus01Component || board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus03Component)
                    destroyBonus01plus03();
            } else if (board[xy.getSource()[0]][xy.getSource()[1]] instanceof Bonus02Component || board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus02Component) {
                if (board[xy.getSource()[0]][xy.getSource()[1]] instanceof Bonus02Component && board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus02Component) {
                    destroyBonus02plus02(xy.getTarget()[0], xy.getTarget()[1]);
                } else if (board[xy.getSource()[0]][xy.getSource()[1]] instanceof Bonus02Component || board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus03Component) {
                    destroyBonus02plus03();
                }
            } else if (board[xy.getSource()[0]][xy.getSource()[1]] instanceof Bonus03Component || board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus03Component) {
                destroyBonus03plus03(xy.getTarget()[0], xy.getTarget()[1]);
            }
        } else {
            boolean v = board[xy.getSource()[0]][xy.getSource()[1]].verifyMovement(xy, board);
            IPieces aux = new NormalPiecesComponent();
            if (v) {
                if (board[xy.getSource()[0]][xy.getSource()[1]].getMoves()[0].isV()) {
                    if ((board[xy.getSource()[0]][xy.getSource()[1]] instanceof NormalPiecesComponent)) {
                        aux.setType(board[xy.getSource()[0]][xy.getSource()[1]].getX());
                        aux.setIndex(board[xy.getSource()[0]][xy.getSource()[1]].getIndex());
                        aux.setPosition(board[xy.getSource()[0]][xy.getSource()[1]].getPosition()[0], board[xy.getSource()[0]][xy.getSource()[1]].getPosition()[0]);
                        destroyNormalPieces(xy.getSource()[0], xy.getSource()[1], 0);
                    } else if (board[xy.getSource()[0]][xy.getSource()[1]] instanceof Bonus01Component) {
                        aux = new Bonus01Component();
                        aux.setIndex(board[xy.getSource()[0]][xy.getSource()[1]].getIndex());
                        aux.setPosition(board[xy.getSource()[0]][xy.getSource()[1]].getPosition()[0], board[xy.getSource()[0]][xy.getSource()[1]].getPosition()[0]);
                        destroyBonus01(xy.getSource()[0], xy.getSource()[1], xy.getTarget()[0], 0);
                    } else if (board[xy.getSource()[0]][xy.getSource()[1]] instanceof Bonus02Component) {
                        aux = new Bonus02Component();
                        aux.setIndex(board[xy.getSource()[0]][xy.getSource()[1]].getIndex());
                        aux.setPosition(board[xy.getSource()[0]][xy.getSource()[1]].getPosition()[0], board[xy.getSource()[0]][xy.getSource()[1]].getPosition()[0]);
                        destroyBonus02(xy.getTarget()[0], xy.getTarget()[1], 0);
                    } else if (board[xy.getSource()[0]][xy.getSource()[1]] instanceof Bonus03Component) {
                        aux = new Bonus03Component();
                        aux.setIndex(board[xy.getSource()[0]][xy.getSource()[1]].getIndex());
                        aux.setPosition(board[xy.getSource()[0]][xy.getSource()[1]].getPosition()[0], board[xy.getSource()[0]][xy.getSource()[1]].getPosition()[0]);
                        destroyBonus03(xy.getTarget()[0], xy.getTarget()[1], 0);
                    }
                } else {
                    if (board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof NormalPiecesComponent) {
                        aux.setType(board[xy.getSource()[0]][xy.getSource()[1]].getX());
                        aux.setIndex(board[xy.getSource()[0]][xy.getSource()[1]].getIndex());
                        aux.setPosition(board[xy.getSource()[0]][xy.getSource()[1]].getPosition()[0], board[xy.getSource()[0]][xy.getSource()[1]].getPosition()[0]);
                        board[xy.getSource()[0]][xy.getSource()[1]] = new NormalPiecesComponent();
                        remove(board[xy.getSource()[0]][xy.getSource()[1]].getIndex());
                        addPieces(board[xy.getSource()[0]][xy.getSource()[1]].getX(), xy.getTarget()[0],  xy.getTarget()[1], board[xy.getTarget()[0]][xy.getTarget()[1]].getIndex());
                    } else {
                        if (board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus01Component) {
                            aux = new Bonus01Component();
                            board[xy.getSource()[0]][xy.getSource()[1]] = new Bonus01Component();
                        } else if (board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus02Component) {
                            aux = new Bonus02Component();
                            board[xy.getSource()[0]][xy.getSource()[1]] = new Bonus02Component();
                        } else if (board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus03Component) {
                            aux = new Bonus03Component();
                            board[xy.getSource()[0]][xy.getSource()[1]] = new Bonus03Component();
                        }
                        aux.setIndex(board[xy.getSource()[0]][xy.getSource()[1]].getIndex());
                        aux.setPosition(board[xy.getSource()[0]][xy.getSource()[1]].getPosition()[0], board[xy.getSource()[0]][xy.getSource()[1]].getPosition()[0]);
                        remove(board[xy.getTarget()[0]][xy.getTarget()[1]].getIndex());
                        addPieces(xy.getTarget()[0],  xy.getTarget()[1], board[xy.getTarget()[0]][xy.getTarget()[1]].getIndex());
                    }
                   // board[xy.getSource()[0]][xy.getSource()[1]] = board[xy.getTarget()[0]][xy.getTarget()[1]];
                }
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
                         remove(aux.getIndex());
                         addPieces(board[xy.getTarget()[0]][xy.getTarget()[1]].getX(), xy.getSource()[0], xy.getSource()[1], aux.getIndex());
                    } else {
                         if (aux instanceof Bonus01Component) {
                             board[xy.getTarget()[0]][xy.getTarget()[1]] = new Bonus01Component();
                         } else if (aux instanceof Bonus02Component) {
                             board[xy.getTarget()[0]][xy.getTarget()[1]] = new Bonus02Component();
                         } else {
                             board[xy.getTarget()[0]][xy.getTarget()[1]] = new Bonus03Component();
                         }
                         remove(aux.getIndex());
                         addPieces(xy.getSource()[0], xy.getSource()[1], aux.getIndex());
                     }
                    // board[xy.getTarget()[0]][xy.getTarget()[1]] = aux;
                }
            }
        }
    }

    private void destroyNormalPieces(int l, int c, int k) {
        int i = 0;
     //   board[l][c].setDead(true);
        while (i<5 && board[l][c].getMoves()[k].getVct()[i][0] != -1) {
            board[board[l][c].getMoves()[k].getVct()[i][0]][board[l][c].getMoves()[k].getVct()[i][1]].setDead(true);
            int aux = board[board[l][c].getMoves()[k].getVct()[i][0]][board[l][c].getMoves()[k].getVct()[i][1]].getIndex();
            remove(aux);
            System.out.println(aux);
            add(new JLabel(new ImageIcon(Main.class.getResource(".").getPath() +  "/Images/field.png")), aux);
            i++;
        }
        SwingUtilities.updateComponentTreeUI(mainPanel);
        printBoard();
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


   /* private void rebuildBoard(int l, int c, int k) {
        int i = 0, j;
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
         //   rebuildBoard(l, c, k);
        }
    }*/

     private void rebuildBoard(int l, int c, int k) {
         int i = 0;
         int p;
         while (i!=5 && board[l][c].getMoves()[k].getVct()[i][0] != -1) {
             i++;
             for (int j = 0; j < 8-board[l][c].getMoves()[k].getVct()[i][0]; j++) {
                 p = 1;
                 while((board[l][c].getMoves()[k].getVct()[i][0] - j - p > 0) && board[board[l][c].getMoves()[k].getVct()[i][0] - j - p][board[l][c].getMoves()[k].getVct()[i][1]].isDead()) {
                     p++;
                 }
                 p --;
                 System.out.println(j);
                 remove(board[board[l][c].getMoves()[k].getVct()[i][0] - j][board[l][c].getMoves()[k].getVct()[i][1]].getIndex());
                 IPieces piece = board[board[l][c].getMoves()[k].getVct()[i][0] - j - p][board[l][c].getMoves()[k].getVct()[i][1]];
                 if (board[l][c].getMoves()[k].getVct()[i][0] - j - p > 0) {
                     board[board[l][c].getMoves()[k].getVct()[i][0] - j - p][board[l][c].getMoves()[k].getVct()[i][1]].setDead(true);
                     addPieces(piece.getX(),board[l][c].getMoves()[k].getVct()[i][0] - j, board[l][c].getMoves()[k].getVct()[i][0], board[board[l][c].getMoves()[k].getVct()[i][0] - j][board[l][c].getMoves()[k].getVct()[i][1]].getIndex());
                 } else {
                     Random random = new Random();
                     addPieces(random.nextInt(lv),board[l][c].getMoves()[k].getVct()[i][0] - j, board[l][c].getMoves()[k].getVct()[i][1], board[board[l][c].getMoves()[k].getVct()[i][0] - j][board[l][c].getMoves()[k].getVct()[i][1]].getIndex());
                 }
                 SwingUtilities.updateComponentTreeUI(mainPanel);
                 board[board[l][c].getMoves()[k].getVct()[i][0] - j][board[l][c].getMoves()[k].getVct()[i][1]].setDead(false);
             }
         }
     }

    public void transformsPieces(char type) {

    }

}

