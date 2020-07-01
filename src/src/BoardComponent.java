import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.Random;

public class BoardComponent extends JPanel implements IBoard {
    private static final long serialVersionUID = -2624435075244032415L;
    private final IPieces[] board;
    private final ScoreboardComponent score;
    private final Container mainPanel;
    private final int lv;

    public BoardComponent(int lv, Container mainPanel) {
        super();
        TranslateMovement.setBoard(this);
        this.lv = lv;
        this.mainPanel = mainPanel;
        mainPanel.remove(0);
        mainPanel.remove(0);
        mainPanel.remove(0);
        mainPanel.remove(0);
        score = new ScoreboardComponent(mainPanel);
        score.addImage(new ImageIcon(Main.class.getResource(".").getPath() +  "/Images/round.png"));
        score.addImage(new ImageIcon(Main.class.getResource(".").getPath() +  "/Images/score.png"));
        score.addLoseImage(new ImageIcon(Main.class.getResource(".").getPath() +  "/Images/lose.png"));
        score.addWinImage(new ImageIcon(Main.class.getResource(".").getPath() +  "/Images/win.png"));
        mainPanel.add(score, BorderLayout.NORTH);
        board = new Pieces[81];
        setSize(450, 450);
        setBackground(new Color(0x847C9D));
        super.setLayout(new GridLayout(9, 9));
        assembleBoard();
    }

    public IPieces[] getBoard() {
        return board;
    }

    public void movePieces() throws InvalidPlay {
        /*Verifica se o movimento e valido, e caso seja, elimina as pecas que devem ser eliminadas*/
        if ((!(board[TranslateMovement.getSource()] instanceof NormalPiecesComponent) && !(board[TranslateMovement.getTarget()] instanceof NormalPiecesComponent))) {
            if (board[TranslateMovement.getSource()] instanceof Bonus01Component) {
                if (board[TranslateMovement.getTarget()] instanceof Bonus01Component) {
                    destroyBonus01plus01(TranslateMovement.getSource(), TranslateMovement.getTarget());
                } else if (board[TranslateMovement.getTarget()] instanceof Bonus02Component) {
                    destroyBonus01plus02(TranslateMovement.getSource(), TranslateMovement.getTarget());
                } else if (board[TranslateMovement.getTarget()] instanceof Bonus03Component) {
                    destroyBonus01plus03(TranslateMovement.getSource(), TranslateMovement.getTarget());
                }
            } else if (board[TranslateMovement.getSource()] instanceof Bonus02Component) {
                if (board[TranslateMovement.getTarget()] instanceof Bonus01Component) {
                    destroyBonus01plus02(TranslateMovement.getTarget(), TranslateMovement.getSource());
                } else if (board[TranslateMovement.getTarget()] instanceof Bonus02Component) {
                    destroyBonus02plus02(TranslateMovement.getSource(), TranslateMovement.getTarget());
                } else if (board[TranslateMovement.getTarget()] instanceof Bonus03Component) {
                    destroyBonus02plus03(TranslateMovement.getSource(), TranslateMovement.getTarget());
                }
            } else if (board[TranslateMovement.getSource()] instanceof Bonus03Component) {
                if (board[TranslateMovement.getTarget()] instanceof Bonus01Component) {
                    destroyBonus01plus03(TranslateMovement.getTarget(), TranslateMovement.getSource());
                } else if (board[TranslateMovement.getTarget()] instanceof Bonus02Component) {
                    destroyBonus02plus03(TranslateMovement.getTarget(), TranslateMovement.getSource());
                } else if (board[TranslateMovement.getTarget()] instanceof Bonus03Component) {
                    destroyBonus03plus03(TranslateMovement.getSource(), TranslateMovement.getTarget());
                }
            }
            score.sumRound();
        } else {
            if (!board[TranslateMovement.getSource()].verifyMovement(TranslateMovement.getTarget())) {
                throw new UselessMovement("Movimento inválido");
            }
            score.sumRound();
            IPieces aux = new NormalPiecesComponent();
            IPieces aux2 = new NormalPiecesComponent();
            boolean s = false,
                    t = false;

            if (board[TranslateMovement.getSource()] instanceof NormalPiecesComponent) {
                aux.setType(board[TranslateMovement.getSource()].getX());
                aux.setIndex(board[TranslateMovement.getSource()].getIndex());
            } else if (board[TranslateMovement.getSource()] instanceof Bonus01Component) {
                aux = new Bonus01Component();
                aux.setIndex(board[TranslateMovement.getSource()].getIndex());

            } else if (board[TranslateMovement.getSource()] instanceof Bonus02Component) {
                aux = new Bonus02Component();
                aux.setIndex(board[TranslateMovement.getSource()].getIndex());
            } else {
                aux = new Bonus03Component();
                aux.setIndex(board[TranslateMovement.getSource()].getIndex());
            }

            if (board[TranslateMovement.getTarget()] instanceof NormalPiecesComponent) {
                aux2.setType(board[TranslateMovement.getTarget()].getX());
                aux2.setIndex(board[TranslateMovement.getTarget()].getIndex());
            } else if (board[TranslateMovement.getTarget()] instanceof Bonus01Component) {
                aux2 = new Bonus01Component();
                aux2.setIndex(board[TranslateMovement.getTarget()].getIndex());
            } else if (board[TranslateMovement.getTarget()] instanceof Bonus02Component) {
                aux2 = new Bonus02Component();
                aux2.setIndex(board[TranslateMovement.getTarget()].getIndex());
            } else {
                aux2 = new Bonus03Component();
                aux2.setIndex(board[TranslateMovement.getTarget()].getIndex());
            }

            if (aux instanceof NormalPiecesComponent) {
                if (board[TranslateMovement.getSource()].getMoves()[0].isV()) {
                    s = true;
                    destroyNormalPieces(TranslateMovement.getSource(), 0, TranslateMovement.getTarget());
                }
            } else if (aux instanceof Bonus01Component) {
                if (board[TranslateMovement.getSource()].getMoves()[0].isV()) {
                    s = true;
                    destroyBonus01(TranslateMovement.getTarget(),TranslateMovement.getSource());
                }
            } else if (aux instanceof Bonus02Component) {
                if (board[TranslateMovement.getSource()].getMoves()[0].isV()) {
                    s = true;
                    destroyBonus02(TranslateMovement.getTarget(),TranslateMovement.getSource());
                }
            } else {
                if (board[TranslateMovement.getSource()].getMoves()[0].isV()) {
                    s = true;
                    destroyBonus03(TranslateMovement.getTarget(),TranslateMovement.getSource());
                }
            }

            if (aux2 instanceof NormalPiecesComponent) {
                if (board[TranslateMovement.getSource()].getMoves()[1].isV()) {
                    t = true;
                    destroyNormalPieces(TranslateMovement.getSource(), 1, TranslateMovement.getTarget());
                }
            } else if (aux2 instanceof Bonus01Component) {
                 if (board[TranslateMovement.getSource()].getMoves()[1].isV()) {
                    t = true;
                    destroyBonus01(TranslateMovement.getSource(), TranslateMovement.getTarget());
                }
            } else if (aux2 instanceof Bonus02Component) {
                 if (board[TranslateMovement.getSource()].getMoves()[1].isV()) {
                    t = true;
                    destroyBonus02(TranslateMovement.getSource(), TranslateMovement.getTarget());
                }
            } else {
                if (board[TranslateMovement.getSource()].getMoves()[1].isV()) {
                    t = true;
                    destroyBonus03(TranslateMovement.getSource(), TranslateMovement.getTarget());
                }
            }

            if (!s || !t) {
                if (!s) {
                    changesPieces(aux, aux2);
                } else {
                    changesPieces(aux2, aux);
                }
            }
        }
        rebuildBoard();
        verifyBoard();
        haveMovement();
        SwingUtilities.updateComponentTreeUI(mainPanel);
    }

    public void addImage(ImageIcon image) {
    	add(new JLabel(image));
    }

    private void assembleBoard() {
        /*monta o tabuleiro inicial*/
        Random random = new Random();
        int x;
        for (int i = 0; i < 81; i++) {
            board[i] = new NormalPiecesComponent();
            x = random.nextInt(lv); //cria um numero aleatorio que gerara uma peca
            board[i].setType(x);    //o lv e definido pelo nivel que a pessoa escolher: nivel 01 - lv = 05; nivel 02 - lv = 07; nivel 03 - lv = 09
            board[i].setIndex(i);
            board[i].setBoard(this, mainPanel);
        }
        verifyFirstBoard(); // verifica se ja ha combinacoes de 3 pecas no tabuleiro e ha elimina
        for (int i = 0; i < 81; i++) {
            /*adiciona os botoes no tabuleiro*/
            this.add(board[i].getButton(), i);
        }
        mainPanel.add(this, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(mainPanel);
    }

    private void verifyFirstBoard() {
        /* verifica se ja ha combinacoes de 3 pecas no tabuleiro inicial e ha elimina*/
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
        if (v) { //verifica ate que nao tenha mais
            verifyFirstBoard();
        }
    }

    private boolean removePiecesInFirstBoard(Random random, int i) {
        /* substitui as pecas do primeiro tabuleiro*/
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
  
    private void haveMovement() {
        /*Verifica se ha movimento possivel apos a jogada*/
        for (int i = 0; i < 81; i++) {
            if (i > 17) {
                if (board[i].getType() == board[i-9].getType()) {
                    if (board[i].getType() == verifyPieceRight(i - 9) || board[i].getType() == verifyPieceLeft(i - 9) || board[i].getType() == verifyPieceBottom(i - 9)) {
                        return;
                    }
                }
            }
            if (i<63) {
                if (board[i].getType() == board[i+9].getType()) {
                   if (board[i].getType() == verifyPieceRight(i + 9) || board[i].getType() == verifyPieceLeft(i + 9) || board[i].getType() == verifyPieceTop(i + 9)) {
                        return;
                   }
                }
            }
            if ((i%9) != 0 && (i-1)%9 != 0) {
                if (board[i].getType() == board[i-1].getType()) {
                   if (board[i].getType() == verifyPieceBottom(i - 1) || board[i].getType() == verifyPieceTop(i - 1) || board[i].getType() == verifyPieceRight(i - 1)) {
                        return;
                   }
                }
            }
            if ((i+1)%9 != 0 && (i+2)%9 != 0) {
                if (board[i].getType() == board[i+1].getType()) {
                    if (board[i].getType() == verifyPieceBottom(i + 1) || board[i].getType() == verifyPieceTop(i + 1) || board[i].getType() == verifyPieceLeft(i + 1)) {
                        return;
                   }
                }
            }
        }
        reassembleBoard();
    }

    private void reassembleBoard() {
        /*quando nao ha movimento possivel no tabuleiro, ele remonta o tabuleiro, substituindo todas as pecas normais*/
        Random random = new Random();
        int x;
        for (int i = 0; i < 81; i++) {
            if (board[i] instanceof NormalPiecesComponent) {
                remove(i);
                board[i] = new NormalPiecesComponent();
                x = random.nextInt(lv);
                board[i].setType(x);
                board[i].setIndex(i);
                board[i].setBoard(this, mainPanel);
                add(board[i].getButton(), i);
            }
        }
        verifyFirstBoard();
        for (int i = 0; i < 81; i++) {
            this.add(board[i].getButton(), i);
        }
        SwingUtilities.updateComponentTreeUI(mainPanel);
    }

    private char verifyPieceTop(int i) {
        /*Verifica se a peca em cima existe e retorna o char que define o tipo dela */
        return i > 8 ? board[i].getType() : ' ';
    }

    private char verifyPieceBottom(int i) {
        /*Verifica se a peca embaixo existe e retorna o char que define o tipo dela */
        return i < 72 ? board[i].getType() : ' ';
    }

    private char verifyPieceLeft(int i) {
        /*Verifica se a peca na esquerda existe e retorna o char que define o tipo dela */
        return i % 9 != 0 ? board[i].getType() : ' ';
    }

    private char verifyPieceRight(int i) {
        /*Verifica se a peca na direita existe e retorna o char que define o tipo dela */
        return ((i + 1) % 9 != 0) ? board[i].getType() : ' ';
    }

    private void verifyBoard() {
        /*Verifica se apos a rodada, combinacoes extras foram criadas no tabuleiro e as elimina*/
        boolean v = false;
        for (int i = 80; i >= 0; i--) {
            if (board[i] instanceof NormalPiecesComponent) {
                if (i > 17) {
                    if (board[i].getType() == board[i - 9].getType() && board[i].getType() == board[i - 18].getType()) {
                        v = true;
                        score.sumScore(5);
                        board[i].setType(-1);
                        board[i - 9].setType(-1);
                        board[i - 18].setType(-1);
                    }
                }
                if (i < 63) {
                    if (board[i].getType() == board[i + 9].getType() && board[i].getType() == board[i + 18].getType()) {
                        v = true;
                        score.sumScore(5);
                        board[i].setType(-1);
                        board[i + 9].setType(-1);
                        board[i + 18].setType(-1);
                    }
                }
                if ((i % 9) != 0 && (i - 1) % 9 != 0) {
                    if (board[i].getType() == board[i - 1].getType() && board[i].getType() == board[i - 2].getType()) {
                        v = true;
                        score.sumScore(5);
                        board[i].setType(-1);
                        board[i - 1].setType(-1);
                        board[i - 2].setType(-1);
                    }
                }
                if ((i + 1) % 9 != 0 && (i + 2) % 9 != 0) {
                    if (board[i].getType() == board[i + 1].getType() && board[i].getType() == board[i + 2].getType()) {
                        v = true;
                        score.sumScore(5);
                        board[i].setType(-1);
                        board[i + 1].setType(-1);
                        board[i + 2].setType(-1);
                    }
                }
            }
        }
        if (v) {
            rebuildBoard();
            verifyBoard();
        }
    }

    private void destroyNormalPieces(int s, int k, int t) {
        /*define as pecas a serem mortas como o tipo morta (setType(-1), e verifica se a peca deve trnasformar-se em um bonus*/
        int i = 0;
        while (i<5 && board[s].getMoves()[k].getVct()[i] != -1) {
            board[board[s].getMoves()[k].getVct()[i]].setType(-1);
            i ++;
        }

        if (i == 4) {
            int aux;
            if (k == 0) {
                aux = board[t].getIndex();
                remove(aux);
                board[t] = new Bonus01Component();
                board[t].setIndex(aux);
                board[t].setBoard(this, mainPanel);
                add(board[t].getButton(), aux);
            } else {
                aux = board[s].getIndex();
                remove(aux);
                board[s] = new Bonus01Component();
                board[s].setIndex(aux);
                board[s].setBoard(this, mainPanel);
                add(board[s].getButton(), aux);
            }
            score.sumScore(7);
        } else if (board[s].getMoves()[k].getMovetype() == 'b') {
            if (k == 0) {
                int aux = board[t].getIndex();
                remove(aux);
                board[t] = new Bonus03Component();
                board[t].setIndex(aux);
                board[t].setBoard(this, mainPanel);
                add(board[t].getButton(), aux);
            } else {
                int aux = board[s].getIndex();
                remove(aux);
                board[s] = new Bonus03Component();
                board[s].setIndex(aux);
                board[s].setBoard(this, mainPanel);
                add(board[s].getButton(), aux);
            }
            score.sumScore(12);
        } else if (i == 5) {
            if (k == 0) {
                int aux = board[t].getIndex();
                remove(aux);
                board[t] = new Bonus02Component();
                board[t].setIndex(aux);
                board[t].setBoard(this, mainPanel);
                add(board[t].getButton(), aux);
            } else {
                int aux = board[s].getIndex();
                remove(aux);
                board[s] = new Bonus02Component();
                board[s].setIndex(aux);
                board[s].setBoard(this, mainPanel);
                add(board[s].getButton(), aux);
            }
            score.sumScore(10);
        } else {
            score.sumScore(5);
        }
    }

    private void destroyBonus01(int s, int t) {
        /*elimina uma linha ou coluna*/
        score.sumScore(10);
        int line = (s/9) * 9;
        board[s].setType(-1);
        remove(t);
        board[t] = new NormalPiecesComponent();
        board[t].setType(-1);
        board[t].setIndex(t);
        add(board[t].getButton(), t);
        if ((s - t) * (s - t) == 81) {
            for (int i = 0; i < 9; i++) {
                board[line+i].setType(-1);
            }
        } else {
            int column = s - line;
            for (int i = 0; i < 9; i++) {
                board[column + i*9].setType(-1);
            }
        }
    }

    private void destroyBonus02(int s, int t) {
        /*elimina todas as pecas ao redor de um especifica*/
        score.sumScore(25);
        remove(t);
        board[t] = new NormalPiecesComponent();
        board[t].setType(-1);
        board[t].setIndex(t);
        board[s].setType(-1);
        add(board[t].getButton(), t);
        if (verifyPieceBottom(s) != ' ') {
            board[s+9].setType(-1);
        }
        if (verifyPieceTop(s) != ' ') {
            board[s-9].setType(-1);
        }
        if (verifyPieceLeft(s) != ' ') {
            board[s-1].setType(-1);
        }
         if (verifyPieceRight(s) != ' ') {
            board[s+1].setType(-1);
        }
         if (verifyPieceRight(s-9) != ' ') {
            board[s-8].setType(-1);
         }
         if (verifyPieceLeft(s-9) != ' ') {
            board[s-10].setType(-1);
         }
         if (verifyPieceRight(s+9) != ' ') {
            board[s+10].setType(-1);
         }
         if (verifyPieceLeft(s+9) != ' ') {
            board[s+8].setType(-1);
         }
    }

    private void destroyBonus03(int s, int t) {
        /*elimina todas as pecas de um tipo do tabuleiro*/
        score.sumScore(5);
        int aux = board[s].getX();
        board[s].setType(-1);
        remove(t);
        board[t] = new NormalPiecesComponent();
        board[t].setType(-1);
        board[t].setIndex(t);
        add(board[t].getButton(), t);
        remove(s);
        board[s] = new NormalPiecesComponent();
        board[s].setType(-1);
        board[s].setIndex(s);
        add(board[s].getButton(), s);
        for (int i = 0; i < 81; i++) {
            if (aux == board[i].getX()) {
                board[i].setType(-1);
                score.sumScore(1);
            }
        }
    }

    private void destroyBonus01plus01(int s, int t) {
        /*elimina uma linha e coluna*/
        score.sumScore(25);
        int line = (s/9) * 9;
        remove(t);
        board[t] = new NormalPiecesComponent();
        board[t].setType(-1);
        board[t].setIndex(t);
        add(board[t].getButton(), t);
        remove(s);
        board[s] = new NormalPiecesComponent();
        board[s].setType(-1);
        board[s].setIndex(s);
        add(board[s].getButton(), s);
        for (int i = 0; i < 9; i++) {
            board[line + i].setType(-1);
        }
        int column = s - line;
        for (int i = 0; i < 9; i++) {
            board[column + i * 9].setType(-1);
        }
    }

    private void destroyBonus01plus02(int s, int t) {
        /*elimina 3 linhas ou 3 colunas*/
        score.sumScore(40);
        int line = (s/9) * 9;
        remove(s);
        remove(t);
        board[t] = new NormalPiecesComponent();
        board[t].setType(-1);
        board[t].setIndex(t);

        board[s] = new NormalPiecesComponent();
        board[s].setType(-1);
        board[s].setIndex(s);

        add(board[t].getButton(), t);
        add(board[s].getButton(), s);
        if ((s - t) * (s - t) == 81) {
            for (int i = 0; i < 9; i++) {
                board[line+i].setType(-1);
            }
            if (s > 8) {
                for (int i = 0; i < 9; i++) {
                    board[line-9+i].setType(-1);
                }
            }
            if (s < 72) {
                for (int i = 0; i < 9; i++) {
                    board[line+9+i].setType(-1);
                }
            }
        } else {
            int column = s - line;
            for (int i = 0; i < 9; i++) {
                board[column + i*9].setType(-1);
            }
             if (s % 9 == 0) {
                for (int i = 0; i < 9; i++) {
                    board[column - 1 + i*9].setType(-1);
                }
            }
            if ((s + 1) % 9 == 0) {
                for (int i = 0; i < 9; i++) {
                    board[column + 1 + i*9].setType(-1);
                }
            }
        }
    }

    private void destroyBonus01plus03(int s, int t) {
        /*transforma todas as pecas de um tipo aleatoriamente em uma do tipo bonus 01 e as eliminam*/
        score.sumScore(10);
        remove(s);
        remove(t);
        board[t] = new NormalPiecesComponent();
        board[t].setType(-1);
        board[t].setIndex(t);

        board[s] = new NormalPiecesComponent();
        board[s].setType(-1);
        board[s].setIndex(s);

        add(board[t].getButton(), t);
        add(board[s].getButton(), s);
        int x = new Random().nextInt(lv);
        for (int i = 0; i < 81; i++) {
            if (board[i].getX() == x) {
               if (i < 80) {
                   destroyBonus01(i + 1, i);
               } else {
                   destroyBonus01(i - 1, i);
               }
            }
        }
    }

    private void destroyBonus02plus02(int s, int t) {
        /*transforma todas as pecas de um tipo aleatoriamente em uma do tipo bonus 02 e as eliminam*/
        score.sumScore(40);
        remove(t);
        board[t] = new NormalPiecesComponent();
        board[t].setType(-1);
        board[t].setIndex(t);
        board[s].setType(-1);
        remove(s);
        board[s] = new NormalPiecesComponent();
        board[s].setType(-1);
        board[s].setIndex(s);
        add(board[t].getButton(), t);
        add(board[s].getButton(), s);

        if (verifyPieceBottom(s) != ' ') {
            board[s+9].setType(-1);
        }
        if (verifyPieceTop(s) != ' ') {
            board[s-9].setType(-1);
        }
        if (verifyPieceLeft(s) != ' ') {
            board[s-1].setType(-1);
        }
         if (verifyPieceRight(s) != ' ') {
            board[s+1].setType(-1);
        }
         if (verifyPieceRight(s-9) != ' ') {
            board[s-8].setType(-1);
         }
         if (verifyPieceLeft(s-9) != ' ') {
            board[s-10].setType(-1);
         }
         if (verifyPieceRight(s+9) != ' ') {
            board[s+10].setType(-1);
         }
         if (verifyPieceLeft(s+9) != ' ') {
            board[s+8].setType(-1);
         }
         if (verifyPieceLeft(s - 1) != ' ') {
            board[s - 2].setType(-1);
         }
         if (verifyPieceRight(s + 1) != ' ') {
            board[s + 2].setType(-1);
         }
         if (verifyPieceBottom(s + 9) != ' ') {
            board[s + 18].setType(-1);
         }
         if (verifyPieceTop(s - 9) != ' ') {
            board[s - 18].setType(-1);
         }
         if (verifyPieceTop(s + 2) != ' ') {
            board[s - 7].setType(-1);
         }
         if (verifyPieceBottom(s + 2) != ' ') {
            board[s + 9].setType(-1);
         }
         if (verifyPieceTop(s - 2) != ' ') {
            board[s - 9].setType(-1);
         }
         if (verifyPieceBottom(s - 2) != ' ') {
            board[s + 7].setType(-1);
         }
         if (verifyPieceRight(s - 18) != ' ') {
            board[s - 17].setType(-1);
         }
         if (verifyPieceLeft(s - 18) != ' ') {
            board[s - 19].setType(-1);
         }
         if (verifyPieceRight(s + 18) != ' ') {
            board[s + 19].setType(-1);
         }
         if (verifyPieceLeft(s + 18) != ' ') {
            board[s + 17].setType(-1);
         }
         if (verifyPieceLeft(s - 19) != ' ') {
            board[s - 20].setType(-1);
         }
         if (verifyPieceLeft(s + 17) != ' ') {
            board[s - 16].setType(-1);
         }
         if (verifyPieceRight(s - 17) != ' ') {
            board[s - 16].setType(-1);
         }
         if (verifyPieceRight(s + 19) != ' ') {
            board[s + 20].setType(-1);
         }
    }

    private void destroyBonus02plus03(int s, int t) {
        score.sumScore(10);
        remove(s);
        remove(t);
        board[t] = new NormalPiecesComponent();
        board[t].setType(-1);
        board[t].setIndex(t);

        board[s] = new NormalPiecesComponent();
        board[s].setType(-1);
        board[s].setIndex(s);

        add(board[t].getButton(), t);
        add(board[s].getButton(), s);
        int x = new Random().nextInt(lv);
        for (int i = 0; i < 81; i++) {
            if (board[i].getX() == x) {
               if (i < 80) {
                   destroyBonus02(i + 1, i);
               } else {
                   destroyBonus02(i - 1, i);
               }
            }
        }
    }

    private void destroyBonus03plus03(int s, int t) {
        /*elimina todas as pecas do tabuleiro*/
        score.sumScore(100);
        remove(t);
        board[t] = new NormalPiecesComponent();
        board[t].setType(-1);
        board[t].setIndex(t);
        board[s].setType(-1);
        remove(s);
        board[s] = new NormalPiecesComponent();
        board[s].setType(-1);
        board[s].setIndex(s);
        add(board[t].getButton(), t);
        add(board[s].getButton(), s);
        for (int i = 0; i < 81; i++) {
            board[i].setType(-1);
        }
    }

    private void rebuildBoard() {
        /*elimina as pecas "setadas" com -1, desliza para baixo as pecas de cima e cria novas quando necessario*/
        for (int i = 80; i >= 0; i--) {
            if (board[i].getX() == -1) {
                if (i > 8) {
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
                                if (board[i - j].getX() == -1) {
                                    aux.setType(-1);
                                }
                            } else if (board[i - j] instanceof Bonus02Component) {
                                aux = new Bonus02Component();
                                aux.setIndex(board[i - j].getIndex());
                                if (board[i - j].getX() == -1) {
                                    aux.setType(-1);
                                }
                            } else {
                                aux = new Bonus03Component();
                                aux.setIndex(board[i - j].getIndex());
                                if (board[i - j].getX() == -1) {
                                    aux.setType(-1);
                                }
                            }
                            if (board[i] instanceof NormalPiecesComponent) {
                                aux2.setType(board[i].getX());
                                aux2.setIndex(board[i].getIndex());
                            } else if (board[i] instanceof Bonus01Component) {
                                aux2 = new Bonus01Component();
                                aux2.setIndex(board[i].getIndex());
                                if (board[i].getX() == -1) {
                                    aux.setType(-1);
                                }
                            } else if (board[i] instanceof Bonus02Component) {
                                aux2 = new Bonus02Component();
                                aux2.setIndex(board[i].getIndex());
                                if (board[i].getX() == -1) {
                                    aux.setType(-1);
                                }
                            } else {
                                aux2 = new Bonus03Component();
                                aux2.setIndex(board[i].getIndex());
                                if (board[i].getX() == -1) {
                                    aux.setType(-1);
                                }
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
        for (int i = 0; i < 81; i++) {
            if (board[i].getX() == -1) {
                board[i] = new NormalPiecesComponent();
                board[i].setType(new Random().nextInt(lv));
                board[i].setIndex(i);
                board[i].setBoard(this, mainPanel);
                remove(i);
                add(board[i].getButton(), i);
            }
        }
    }

    private void changesPieces (IPieces piece1, IPieces piece2) {
        /*muda duas pecas de lugar*/
        board[piece2.getIndex()].setType(piece1.getX());
        board[piece2.getIndex()].setIndex(piece2.getIndex());
        board[piece2.getIndex()].setBoard(this, mainPanel);
        SwingUtilities.updateComponentTreeUI(mainPanel);
    }

}