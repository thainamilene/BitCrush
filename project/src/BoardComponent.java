import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BoardComponent extends JPanel implements IBoard {
    private static final long serialVersionUID = -2624435075244032415L;
//    public IPieces[][] board;
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
        board = new Pieces[9][9];
        setSize(450, 450);
        setBackground(new Color(0x847C9D));
        super.setLayout(new GridLayout(9, 9));
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
        cont = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.add(board[i][j].getButton(), cont);
                cont++;
            }
        }
        mainPanel.add(this, BorderLayout.CENTER);
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
                    if (board[i][j].getType() == board[i + 1][j].getType() && board[i][j].getType() == board[i + 2][j].getType()) {
                        v = removePieceFirstBoard(random, i, j);
                    }
                }
                if (0 < j && j < 8) {
                    if (board[i][j].getType() == board[i][j + 1].getType() && board[i][j].getType() == board[i][j - 1].getType()) {
                        v = removePieceFirstBoard(random, i, j);
                    }
                }
                if (i > 0 && i < 8) {
                    if (board[i][j].getType() == board[i - 1][j].getType() && board[i][j].getType() == board[i + 1][j].getType()) {
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

    public void printBoard() {
        for (int i = 0; i < 9; i++) {
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
        if (counter == 0) {
            move = new TranslateMovementComponent();
            move.setSource(position);
            counter++;
        } else {
            move.setTarget(position);
            counter--;
            movePieces(move);
        }
    }

    public IPieces[][] getBoard() {
        return board;
    }

    private void movePieces(ITranslateMovementC xy) {
        System.out.println("nha");
        if (!(board[xy.getSource()[0]][xy.getSource()[1]] instanceof NormalPiecesComponent && board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof NormalPiecesComponent)) {
            System.out.println("nh1");
            if (board[xy.getSource()[0]][xy.getSource()[1]] instanceof Bonus01Component) {
                if (board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus01Component) {
                    destroyBonus01plus01(xy.getTarget()[0], xy.getTarget()[1]);
                } else if (board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus02Component) {
                    destroyBonus01plus02(xy.getTarget()[0], xy.getTarget()[1], xy.getSource()[0]);
                } else if (board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus03Component)
                    destroyBonus01plus03();
            } else if (board[xy.getSource()[0]][xy.getSource()[1]] instanceof Bonus02Component) {
                if (board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus01Component) {
                    destroyBonus01plus02(xy.getTarget()[0], xy.getTarget()[1], xy.getSource()[0]);
                } else if (board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus02Component) {
                    destroyBonus02plus02(xy.getTarget()[0], xy.getTarget()[1]);
                } else if (board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus03Component)
                    destroyBonus02plus03();
            } else if (board[xy.getSource()[0]][xy.getSource()[1]] instanceof Bonus03Component) {
                if (board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus01Component) {
                    destroyBonus01plus03();
                } else if (board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus02Component) {
                    destroyBonus02plus03();
                } else if (board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus03Component)
                    destroyBonus03plus03();
            }
        } else {
            System.out.println("nh2");
            boolean v = board[xy.getSource()[0]][xy.getSource()[1]].verifyMovement(xy);
            System.out.println("v: " + v);
            IPieces aux = new NormalPiecesComponent();
            System.out.println(board[xy.getSource()[0]][xy.getSource()[1]].getMoves()[0].getMovetype());
            if (v) {
                System.out.println("is v()");
                if (board[xy.getSource()[0]][xy.getSource()[1]].getMoves()[0].isV()) {
                    if ((board[xy.getSource()[0]][xy.getSource()[1]] instanceof NormalPiecesComponent)) {
                        System.out.println("is 1");
                        aux.setType(board[xy.getSource()[0]][xy.getSource()[1]].getX());
                        destroyNormalPieces(xy.getSource()[0], xy.getSource()[1], 0);
                    } else if (board[xy.getSource()[0]][xy.getSource()[1]] instanceof Bonus01Component) {
                        aux = new Bonus01Component();
                        destroyBonus01(xy.getSource()[0], xy.getSource()[1], xy.getTarget()[0], 0);
                    } else if (board[xy.getSource()[0]][xy.getSource()[1]] instanceof Bonus02Component) {
                        aux = new Bonus02Component();
                        destroyBonus02(xy.getTarget()[0], xy.getTarget()[1], 0);
                    } else if (board[xy.getSource()[0]][xy.getSource()[1]] instanceof Bonus03Component) {
                        aux = new Bonus03Component();
                        destroyBonus03(xy.getTarget()[0], xy.getTarget()[1], 0);
                    }
                    aux.setIndex(board[xy.getSource()[0]][xy.getSource()[1]].getIndex());
                    aux.setPosition(board[xy.getSource()[0]][xy.getSource()[1]].getPosition()[0], board[xy.getSource()[0]][xy.getSource()[1]].getPosition()[0]);
                } else {
                    if (board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof NormalPiecesComponent) {
                        System.out.println("is 2");
                        aux.setType(board[xy.getSource()[0]][xy.getSource()[1]].getX());
                        board[xy.getSource()[0]][xy.getSource()[1]] = new NormalPiecesComponent();
//                        addPieces(board[xy.getSource()[0]][xy.getSource()[1]].getX(), xy.getTarget()[0],  xy.getTarget()[1], board[xy.getTarget()[0]][xy.getTarget()[1]].getIndex());
                    } else if (board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof Bonus01Component) {
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
                    System.out.println("r:" + board[xy.getTarget()[0]][xy.getTarget()[1]].getIndex());
                    remove(board[xy.getTarget()[0]][xy.getTarget()[1]].getIndex());
                    add(board[xy.getSource()[0]][xy.getSource()[1]].getButton(), board[xy.getTarget()[0]][xy.getTarget()[1]].getIndex());
                }
                if (board[xy.getSource()[0]][xy.getSource()[1]].getMoves()[1].isV()) {
                    if (board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof NormalPiecesComponent) {
                        System.out.println("is 3");
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
                        System.out.println("is 4");
                        board[xy.getTarget()[0]][xy.getTarget()[1]] = new NormalPiecesComponent();
                    } else if (aux instanceof Bonus01Component) {
                        board[xy.getTarget()[0]][xy.getTarget()[1]] = new Bonus01Component();
                    } else if (aux instanceof Bonus02Component) {
                        board[xy.getTarget()[0]][xy.getTarget()[1]] = new Bonus02Component();
                    } else {
                        board[xy.getTarget()[0]][xy.getTarget()[1]] = new Bonus03Component();
                    }
                    System.out.println("r:" + aux.getIndex());
                    remove(aux.getIndex());
                    add(board[xy.getTarget()[0]][xy.getTarget()[1]].getButton(), aux.getIndex());
                }
            }
        }
        SwingUtilities.updateComponentTreeUI(mainPanel);
    }

    public void transformsPieces(char type) {

    }

    private void destroyBonus03(int i, int i1, int i2) {
    }

    private void destroyBonus02(int i, int i1, int i2) {
    }

    private void destroyBonus01(int i, int i1, int i2, int i3) {
    }

    private void destroyNormalPieces(int i, int i1, int i2) {
    }

    private void destroyBonus03plus03() {
    }


    private void destroyBonus02plus03() {
    }

    private void destroyBonus02plus02(int i, int i1) {
    }

    private void destroyBonus01plus03() {
    }

    private void destroyBonus01plus02(int i, int i1, int i2) {
    }

    private void destroyBonus01plus01(int i, int i1) {
    }
}