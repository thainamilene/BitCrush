import javax.swing.*;

public abstract class Pieces extends JButton implements IPieces {
   protected char type;
   protected int x = -1;
   protected IMovementAttributes[] moves;
   protected boolean dead = false;

    public Pieces() {
        super();
        moves = new IMovementAttributes[2];
    }

    public abstract void setType(int x);

    public abstract boolean verifyMovement(ITranslateMovementC xy, IPieces[][] board);

    protected void verifyTargetMovement(ITranslateMovementC xy, IPieces[][] board) {

     /*   for (int i = 0; i < 2; i++) {
            moves[i] = new MovementComponent();
        }*/
        if (board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof NormalPieces) {
            if (xy.getTarget()[0] == xy.getSource()[0]) {
                if (board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceOnTop(xy.getSource()[0], xy.getSource()[1], board) && board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceOnTop(xy.getSource()[0] + 1, xy.getSource()[1], board)) {
                    moves[1].setV(true);
                    moves[1].setMovetype('c');
                    moves[1].setVct(xy.getSource());
                    moves[1].setVct(new int[]{xy.getSource()[0] + 1, xy.getSource()[1]});
                    moves[1].setVct(new int[]{xy.getSource()[0] + 2, xy.getSource()[1]});
                }
                if (board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceInBottom(xy.getSource()[0], xy.getSource()[1], board) && board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceInBottom(xy.getSource()[0] - 1, xy.getSource()[1], board)) {
                    if (moves[1].isV()) {
                        moves[1].setMovetype('b');
                    } else {
                        moves[1].setV(true);
                        moves[1].setMovetype('c');
                    }
                    moves[1].setVct(xy.getSource());
                    moves[1].setVct(new int[]{xy.getSource()[0] - 1, xy.getSource()[1]});
                    moves[1].setVct(new int[]{xy.getSource()[0] - 2, xy.getSource()[1]});
                }
                if (moves[1].getMovetype() != 'b') {
                    if (board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceInLeft(xy.getSource()[0], xy.getSource()[1], board) && board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceInLeft(xy.getSource()[0], xy.getSource()[1] - 1, board)) {
                        if (moves[1].isV()) {
                            moves[1].setMovetype('t');
                        } else {
                            moves[1].setV(true);
                            if (board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceOnTop(xy.getSource()[0], xy.getSource()[1], board) && board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceInBottom(xy.getSource()[0], xy.getSource()[1], board)) {
                                moves[1].setMovetype('t');
                                moves[1].setVct(new int[]{xy.getSource()[0] + 1, xy.getSource()[1]});
                                moves[1].setVct(new int[]{xy.getSource()[0] - 1, xy.getSource()[1]});
                            } else {
                                moves[1].setMovetype('l');
                            }
                        }
                        moves[1].setVct(xy.getTarget());
                        moves[1].setVct(new int[]{xy.getSource()[0], xy.getSource()[1] + 1});
                        moves[1].setVct(new int[]{xy.getSource()[0], xy.getSource()[1] + 2});
                    } else if (board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceOnTop(xy.getSource()[0], xy.getSource()[1], board) && board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceInBottom(xy.getSource()[0], xy.getSource()[1], board)) {
                        moves[1].setMovetype('c');
                        moves[1].setV(true);
                        moves[1].setVct(new int[]{xy.getSource()[0] + 1, xy.getSource()[1]});
                        moves[1].setVct(new int[]{xy.getSource()[0] - 1, xy.getSource()[1]});
                    }
                }
            } else {
                if (board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceInLeft(xy.getSource()[0], xy.getSource()[1], board) && board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceInLeft(xy.getSource()[0], xy.getSource()[1] - 1, board)) {
                    moves[1].setV(true);
                    moves[1].setMovetype('l');
                    moves[1].setVct(xy.getSource());
                    moves[1].setVct(new int[]{xy.getSource()[0], xy.getSource()[1] + 1});
                    moves[1].setVct(new int[]{xy.getSource()[0], xy.getSource()[1] + 2});
                }
                if (board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceInRight(xy.getSource()[0], xy.getSource()[1], board) && board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceInRight(xy.getSource()[0], xy.getSource()[1] + 1, board)) {
                    if (moves[1].isV()) {
                        moves[1].setMovetype('b');
                    } else {
                        moves[1].setV(true);
                        moves[1].setMovetype('l');
                    }
                    moves[1].setVct(xy.getSource());
                    moves[1].setVct(new int[]{xy.getSource()[0], xy.getSource()[1] - 1});
                    moves[1].setVct(new int[]{xy.getSource()[0], xy.getSource()[1] - 2});
                }
                if (moves[1].getMovetype() != 'b') {
                    if (board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceInBottom(xy.getSource()[0], xy.getSource()[1], board) && board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceInBottom(xy.getSource()[0], xy.getSource()[1], board)) {
                        if (moves[1].isV()) {
                            moves[1].setMovetype('t');
                        } else {
                            moves[1].setV(true);
                            if (board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceInLeft(xy.getSource()[0], xy.getSource()[1], board) && board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceInRight(xy.getSource()[0], xy.getSource()[1], board)) {
                                moves[1].setMovetype('t');
                                moves[1].setVct(new int[]{xy.getSource()[0], xy.getSource()[1] - 1});
                                moves[1].setVct(new int[]{xy.getSource()[0], xy.getSource()[1] - 2});
                            } else {
                                moves[1].setMovetype('l');
                            }
                        }
                        moves[1].setVct(xy.getTarget());
                        moves[1].setVct(new int[]{xy.getSource()[0] + 1, xy.getSource()[1]});
                        moves[1].setVct(new int[]{xy.getSource()[0] + 2, xy.getSource()[1]});
                    } else if (board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceInLeft(xy.getSource()[0], xy.getSource()[1], board) && board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceInRight(xy.getSource()[0], xy.getSource()[1], board)) {
                        moves[1].setMovetype('c');
                        moves[1].setV(true);
                        moves[1].setVct(new int[]{xy.getSource()[0], xy.getSource()[1] - 1});
                        moves[1].setVct(new int[]{xy.getSource()[0], xy.getSource()[1] - 2});
                    }
                }
            }
        } else {
            moves[1].setV(true);
            moves[1].setMovetype(board[xy.getTarget()[0]][xy.getTarget()[1]].getType());
        }
    }

     protected char getPieceInRight(int xCoordinate, int yCoordinate, IPieces[][] board) {
        return yCoordinate < 8 ? board[xCoordinate][yCoordinate + 1].getType() : ' ';
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
        return this.type;
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
}
