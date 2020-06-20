import java.awt.event.ActionEvent;

public abstract class Pieces implements IPieces{
   protected char type;
   protected int x = -1;
   protected IMovementAttributes[] moves;
   protected boolean dead = false;
   protected int index;
   protected String imageIcon;
   protected int[] position;
   protected IBoard board;

    public Pieces() {
        moves = new IMovementAttributes[2];
        moves[0] = new MovementComponent();
        moves[1] = new MovementComponent();
        position = new int[2];
    }

    public abstract void setType(int x);

    public abstract boolean verifyMovement(ITranslateMovementC xy, IPieces[][] board);

    protected char getPieceInRight(int xCoordinate, int yCoordinate, IPieces[][] board) {
        return (yCoordinate < 8) ? board[xCoordinate][yCoordinate + 1].getType() : ' ';
    }

    protected char getPieceInLeft(int xCoordinate, int yCoordinate, IPieces[][] board) {
         return yCoordinate > 1 ? board[xCoordinate][yCoordinate - 1].getType() : ' ';
     }

    protected char getPieceOnTop(int xCoordinate, int yCoordinate, IPieces[][] board) {
        return xCoordinate < 8 ? board[xCoordinate + 1][yCoordinate].getType() : ' ';
    }

    protected char getPieceInBottom(int xCoordinate, int yCoordinate, IPieces[][] board) {
        return xCoordinate > 0 ? board[xCoordinate - 1][yCoordinate].getType() : ' ';
    }

    public char getType() {
        return type;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public boolean isDead() {
        return dead;
    }

    public IMovementAttributes[] getMoves() {
        return moves;
    }

    public int getX() {
       return x;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println(position[0] + ", " + position[1]);
        board.translate(position);

    }

    public abstract void setImageIcon(String imageIcon);
    public String getImageIcon() {
        return imageIcon;
    }

    public void setPosition(int i, int j) {
        this.position[0] = i;
        this.position[1] = j;
    }

    public int[] getPosition() {
        return position;
    }

    public void setBoard(IBoard board) {
        this.board = board;
    }

    protected void verifyTargetMovement(ITranslateMovementC xy, IPieces[][] board) {
        if (this instanceof NormalPiecesComponent) {
             if (position[0] == xy.getTarget()[0]) { //movendo na mesma linha
                if((board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceOnTop(position[0], position[1], board) && (board[position[0]][position[1]].getType() == getPieceOnTop(position[0] + 1, position[1], board)))) {
                    moves[0].setV(true);
                    moves[0].setMovetype('c');
                    moves[0].setVct(position);
                    moves[0].setVct(new int[]{position[0] + 1, position[1]});
                    moves[0].setVct(new int[]{position[0] + 2, position[1]});
                    if (board[position[0]][position[1]].getType() == getPieceInBottom(position[0], position[1], board)) {
                        moves[0].setMovetype('4');
                        moves[0].setVct(new int[]{position[0] - 1, position[1]});
                    }
                }
                if ((board[position[0]][position[1]].getType() == getPieceInBottom(position[0], position[1], board)) && type == getPieceInBottom(position[0] - 1, position[1], board)) {
                    if (moves[0].isV()) {
                        moves[0].setMovetype('b');
                    } else {
                        moves[0].setV(true);
                        moves[0].setMovetype('c');
                        moves[0].setVct(position);
                        moves[0].setVct(new int[]{position[0] - 1, position[1]});
                        if (board[position[0]][position[1]].getType() == getPieceOnTop(position[0], position[1], board)) {
                            moves[0].setMovetype('4');
                            moves[0].setVct(new int[]{position[0] + 1 , position[1]});
                        }
                    }
                    moves[0].setVct(new int[]{position[0] - 2, position[1]});
                }
                if (moves[0].getMovetype() != 'b') {
                    if (board[position[0]][position[1]].getType() == getPieceInLeft(position[0], position[1], board) && type == getPieceInLeft(position[0], position[1] - 1, board)) {
                        if (moves[0].isV()) {
                            if (moves[0].getMovetype() == '4') {
                                moves[0].remove();
                            }
                            moves[0].setMovetype('t');
                        } else {
                            moves[0].setV(true);
                            moves[0].setMovetype('c');
                            moves[0].setVct(position);
                        }
                        moves[0].setVct(new int[]{position[0], position[1] - 1});
                        moves[0].setVct(new int[]{position[0], position[1] - 2});
                    }
                }
            } else { //movendo na mesma coluna
                if (board[position[0]][position[1]].getType() == getPieceInLeft(position[0], position[1], board) && type == getPieceInLeft(position[0], position[1] - 1, board)) {
                    moves[0].setV(true);
                    moves[0].setMovetype('c');
                    moves[0].setVct(position);
                    moves[0].setVct(new int[]{position[0], position[1] - 1});
                    moves[0].setVct(new int[]{position[0], position[1] - 2});
                    if (board[position[0]][position[1]].getType() == getPieceInRight(position[0], position[1],board)) {
                        moves[0].setMovetype('4');
                        moves[0].setVct(new int[]{position[0], position[1] + 1});
                    }
                }
                if (board[position[0]][position[1]].getType() == getPieceInLeft(position[0], position[1], board) && type == getPieceInLeft(position[0], position[1] + 1, board)) {
                    if (moves[0].isV()) {
                        moves[0].setMovetype('b');
                    } else {
                        moves[0].setMovetype('c');
                        moves[0].setV(true);
                        moves[0].setVct(position);
                        moves[0].setVct(new int[]{position[0], position[1] - 1});
                        if (board[position[0]][position[1]].getType() == getPieceInLeft(position[0], position[1], board)) {
                            moves[0].setMovetype('4');
                            moves[0].setVct(new int[]{position[0], position[1] + 1});
                        }
                    }
                    moves[0].setVct(new int[]{position[0], position[1] - 2});
                }
                if (moves[0].getMovetype() != 'b') {
                    if (board[position[0]][position[1]].getType() == getPieceInBottom(position[0], position[1], board) && type == getPieceInBottom(position[0] - 1, position[1], board)) {
                        if (moves[0].isV()) {
                            if (moves[0].getMovetype() == '4') {
                                moves[0].remove();
                            }
                            moves[0].setMovetype('t');
                        } else {
                            moves[0].setV(true);
                            moves[0].setMovetype('c');
                            moves[0].setVct(position);
                        }
                        moves[0].setVct(new int[]{position[0] - 1, position[1]});
                        moves[0].setVct(new int[]{position[0] - 2, position[1]});
                    }
                }
            }
        } else {
            moves[1].setV(true);
            moves[1].setMovetype(board[xy.getTarget()[0]][xy.getTarget()[1]].getType());
        }
    }
}
