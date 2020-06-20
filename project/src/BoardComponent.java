import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BoardComponent extends JPanel implements IBoard {
    private static final long serialVersionUID = -2624435075244032415L;
    public IPieces[] board;
    public IScoreboard score;
    private ITranslateMovementC move;
    private int counter;
    private final Container mainPanel;
    private final int lv;


    public BoardComponent(int lv, Container mainPanel) {
        counter = 0;
        this.lv = lv;
        this.mainPanel = mainPanel;
        mainPanel.remove(1);
        mainPanel.remove(1);
        mainPanel.remove(1);
        mainPanel.setBackground(new Color(0xffffff));
        score = new ScoreboardComponent();
        board = new Pieces[81];
        setSize(450, 450);
        setBackground(new Color(0x847C9D));
        super.setLayout(new GridLayout(9, 9));
        assembleBoard();
    }

    private void assembleBoard() {
        Random random = new Random();
        int x;
        for (int i = 0; i < 81; i++) {
            board[i] = new NormalPiecesComponent();
            x = random.nextInt(lv);
            board[i].setType(x);
            board[i].setIndex(i);
            board[i].setBoard(this);
        }
        verifyFirstBoard();
        for (int i = 0; i < 81; i++) {
            this.add(board[i].getButton(), i);
        }
        mainPanel.add(this, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(mainPanel);
        printBoard();
    }

    private void verifyFirstBoard() {         
        Random random = new Random();
        boolean v = false;
        for (int i = 0; i < 81; i++) {
            if (i > 17) {
                if (board[i].getType() == board[i-9].getType() && board[i].getType() == board[i-18].getType()) {
                   v = removePiecesInFirstBoard(random, i);
                }
            }
            if (i<63) {
                if (board[i].getType() == board[i+9].getType() && board[i].getType() == board[i+18].getType()) {
                   v = removePiecesInFirstBoard(random, i);
                }
            }
            if ((i%9) != 0 && (i-1)%9 != 0) {
                if (board[i].getType() == board[i-1].getType() && board[i].getType() == board[i-2].getType()) {
                   v = removePiecesInFirstBoard(random, i);
                }
            }
            if ((i+1)%9 != 0 && (i+2)%9 != 0) {
                if (board[i].getType() == board[i+1].getType() && board[i].getType() == board[i+2].getType()) {
                   v = removePiecesInFirstBoard(random, i);
                }
            }
        }
        if (v) {
            verifyFirstBoard();
        }
    }

    private boolean removePiecesInFirstBoard(Random random, int i) {
        int x = random.nextInt(lv);
        char aux = board[i].getType();
        board[i].setType(x);
        if (board[i].getType() == aux) {
            if (x > 0) { 
                board[i].setType(x - 1);
            } else {
            board[i].setType(x + 1);
            }
        }
        return true;
    }


    public void printBoard() {
        int c = 0;
         for (int i=0; i < 9; i++) {
            System.out.print(i);
            System.out.print(' ');
            for (int j = 0; j < 9; j++) {
                System.out.print(board[c].getType() + " ");
                c++;
            }
            System.out.println(" ");
        }
        System.out.println("  a b c d e f g h i\n");
    }

    public void translate(int position) {
         if (counter==0) {
            move = new TranslateMovementComponent();
            move.setSource(position);
            counter ++;
        } else {
            move.setTarget(position);
            counter--;
            movePieces(move);
            System.out.println("---------------------------------------------------------------------");
        }
    }

    public IPieces[] getBoard() {
        return board;
    }

    private void movePieces(ITranslateMovementC xy) {
        if (!(board[xy.getSource()] instanceof NormalPiecesComponent && board[xy.getTarget()] instanceof NormalPiecesComponent)) {
            if (board[xy.getSource()] instanceof Bonus01Component) {
                if (board[xy.getTarget()] instanceof Bonus01Component) {
                    destroyBonus01plus01();
                } else if (board[xy.getTarget()] instanceof Bonus02Component) {
                    destroyBonus01plus02();
                } else if (board[xy.getTarget()] instanceof Bonus03Component) {
                    destroyBonus01plus03();
                }
            } else if (board[xy.getSource()] instanceof Bonus02Component) {
                if (board[xy.getTarget()] instanceof Bonus01Component) {
                    destroyBonus01plus02();
                } else if (board[xy.getTarget()] instanceof Bonus02Component) {
                    destroyBonus02plus02();
                } else if (board[xy.getTarget()] instanceof Bonus03Component) {
                    destroyBonus02plus03();
                }
            } else if (board[xy.getSource()] instanceof Bonus03Component) {
                if (board[xy.getTarget()] instanceof Bonus01Component) {
                    destroyBonus01plus03();
                } else if (board[xy.getTarget()] instanceof Bonus02Component) {
                    destroyBonus02plus03();
                } else if (board[xy.getTarget()] instanceof Bonus03Component) {
                    destroyBonus03plus03();
                }
            }
        } else {
            if (board[xy.getSource()].verifyMovement(xy.getTarget())) {
                System.out.println(true);
                IPieces aux = new NormalPiecesComponent();
                IPieces aux2 = new NormalPiecesComponent();
                boolean s = false,
                        t = false;

                if (board[xy.getSource()] instanceof NormalPiecesComponent) {
                    aux.setType(board[xy.getSource()].getX());
                    aux.setIndex(board[xy.getSource()].getIndex());
                } else if (board[xy.getSource()] instanceof Bonus01Component) {
                    aux = new Bonus01Component();
                    aux.setIndex(board[xy.getSource()].getIndex());

                } else if (board[xy.getSource()] instanceof Bonus02Component) {
                    aux = new Bonus02Component();
                    aux.setIndex(board[xy.getSource()].getIndex());
                } else {
                    aux = new Bonus03Component();
                    aux.setIndex(board[xy.getSource()].getIndex());
                }

                if (board[xy.getTarget()] instanceof NormalPiecesComponent) {
                    aux2.setType(board[xy.getTarget()].getX());
                    aux2.setIndex(board[xy.getTarget()].getIndex());
                } else if (board[xy.getTarget()] instanceof Bonus01Component) {
                    aux2 = new Bonus01Component();
                    aux2.setIndex(board[xy.getTarget()].getIndex());
                } else if (board[xy.getTarget()] instanceof Bonus02Component) {
                    aux2 = new Bonus02Component();
                    aux2.setIndex(board[xy.getTarget()].getIndex());
                } else {
                    aux2 = new Bonus03Component();
                    aux2.setIndex(board[xy.getTarget()].getIndex());
                }

                if (board[xy.getSource()] instanceof NormalPiecesComponent) {
                    if (board[xy.getSource()].getMoves()[0].isV()) {
                        s = true;
                        destroyNormalPieces(xy.getSource(), 0);
                    }
                } else if (board[xy.getSource()] instanceof Bonus01Component) {
                    if (board[xy.getSource()].getMoves()[0].isV()) {
                        s = true;
                        destroyBonus01();
                    }
                } else if (board[xy.getSource()] instanceof Bonus02Component) {
                    if (board[xy.getSource()].getMoves()[0].isV()) {
                        s = true;
                        destroyBonus02();
                    }
                } else {
                    if (board[xy.getSource()].getMoves()[0].isV()) {
                        s = true;
                        destroyBonus03();
                    }
                }

                if (board[xy.getTarget()] instanceof NormalPiecesComponent) {
                    if (board[xy.getSource()].getMoves()[1].isV()) {
                        t = true;
                        destroyNormalPieces(xy.getSource(), 1);
                    }
                } else if (board[xy.getTarget()] instanceof Bonus01Component) {
                     if (board[xy.getSource()].getMoves()[1].isV()) {
                        t = true;
                        destroyBonus01();
                    }
                } else if (board[xy.getTarget()] instanceof Bonus02Component) {
                     if (board[xy.getSource()].getMoves()[1].isV()) {
                        t = true;
                        destroyBonus02();
                    }
                } else {
                    if (board[xy.getSource()].getMoves()[1].isV()) {
                        t = true;
                        destroyBonus03();
                    }
                }

                if (!s || !t) {
                    if (!s) {
                        System.out.println("1");
                        changesPieces(aux, aux2);
                    } else {
                        System.out.println("2");
                        changesPieces(aux2, aux);
                    }
                }
            }
        }
        rebuildBoard();
        SwingUtilities.updateComponentTreeUI(mainPanel);
        printBoard();
    }

    public void transformsPieces(char type) {

    }

    private void destroyBonus03() {
    }

    private void destroyBonus02() {
    }

    private void destroyBonus01() {
    }

    private void destroyNormalPieces(int s, int k) {
        int i = 0;
        while (i<5 && board[s].getMoves()[k].getVct()[i] != -1) {
            board[board[s].getMoves()[k].getVct()[i]].setType(-1);
            i ++;
        }
    }

    private void rebuildBoard() {
        for (int i = 80; i >= 0; i--) {
            if (board[i].getX() == -1) {
                if (i > 8) {
                    System.out.println(i + " ");
                    int j = 9;
                    IPieces aux = new NormalPiecesComponent(),
                            aux2 = new NormalPiecesComponent();
                    while (i - j > 0) {
                        if (board[i - j].getX() != -1) {
                            if (board[i - j] instanceof NormalPiecesComponent) {
                                aux.setType(board[i - j].getX());
                                aux.setIndex(board[i - j].getIndex());
                            } else if (board[i - j] instanceof Bonus01Component) {
                                aux = new Bonus01Component();
                                aux.setIndex(board[i - j].getIndex());
                            } else if (board[i - j] instanceof Bonus02Component) {
                                aux = new Bonus02Component();
                                aux.setIndex(board[i - j].getIndex());
                            } else {
                                aux = new Bonus03Component();
                                aux.setIndex(board[i - j].getIndex());
                            }
                            if (board[i] instanceof NormalPiecesComponent) {
                                aux2.setType(board[i].getX());
                                aux2.setIndex(board[i].getIndex());
                            } else if (board[i] instanceof Bonus01Component) {
                                aux2 = new Bonus01Component();
                                aux2.setIndex(board[i].getIndex());
                            } else if (board[i] instanceof Bonus02Component) {
                                aux2 = new Bonus02Component();
                                aux2.setIndex(board[i].getIndex());
                            } else {
                                aux2 = new Bonus03Component();
                                aux2.setIndex(board[i].getIndex());
                            }

                            if (aux instanceof NormalPiecesComponent) {
                                board[i] = new NormalPiecesComponent();
                            } else if (aux instanceof Bonus01Component) {
                                board[i] = new Bonus01Component();
                            } else if (aux instanceof Bonus02Component) {
                                board[i] = new Bonus02Component();
                            } else {
                                board[i] = new Bonus03Component();
                            }

                            if (aux2 instanceof NormalPiecesComponent) {
                                board[i - j] = new NormalPiecesComponent();
                            } else if (aux2 instanceof Bonus01Component) {
                                board[i - j] = new Bonus01Component();
                            } else if (aux2 instanceof Bonus02Component) {
                                board[i - j] = new Bonus02Component();
                            } else {
                                board[i - j] = new Bonus03Component();
                            }
                            changesPieces(aux2, aux);
                            changesPieces(aux, aux2);
                            remove(aux.getIndex());
                            add(board[i - j].getButton(), i - j);
                            remove(aux2.getIndex());
                            add(board[i].getButton(), i);
                            break;
                        }
                        j += 9;
                    }

                }
            }
        }
    }

    private void destroyBonus03plus03() {
    }

    private void destroyBonus02plus03() {
    }

    private void destroyBonus02plus02() {
    }

    private void destroyBonus01plus03() {
    }

    private void destroyBonus01plus02() {
    }

    private void destroyBonus01plus01() {
    }

    private void changesPieces (IPieces piece1, IPieces piece2) {
        board[piece2.getIndex()].setType(piece1.getX());
        board[piece2.getIndex()].setIndex(piece2.getIndex());
        board[piece2.getIndex()].setBoard(this);
        SwingUtilities.updateComponentTreeUI(mainPanel);
    }
}