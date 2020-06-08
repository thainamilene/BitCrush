public abstract class Pieces implements IPieces {
    char type;
    IMovementAttributes[] moves;

    public Pieces() {
        moves = new IMovementAttributes[2];
    }

    public abstract void setType(int x);

    public abstract boolean verifyMovement(ITranslateMovementC xy, BoardComponent board);

    protected void verifyTargetMovement(ITranslateMovementC xy, BoardComponent board) {

        for (int i = 0; i < 2; i++) {
            moves[i] = new MovementComponent();
        }
        if (board.board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof NormalPiecesComponent) {
            if (xy.getTarget()[0] == xy.getSource()[0]) {
                if (board.board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceOnTop(xy.getSource()[0], xy.getSource()[1], board) && board.board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceOnTop(xy.getSource()[0] + 1, xy.getSource()[1], board)) {
                    moves[1].setV(true);
                    moves[1].setMovetype('c');
                    moves[1].setVct(xy.getSource());
                    moves[1].setVct(new int[]{xy.getSource()[0] + 1, xy.getSource()[1]});
                    moves[1].setVct(new int[]{xy.getSource()[0] + 2, xy.getSource()[1]});
                }
                if (board.board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceInBottom(xy.getSource()[0], xy.getSource()[1], board) && board.board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceInBottom(xy.getSource()[0] - 1, xy.getSource()[1], board)) {
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
                    if (board.board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceInLeft(xy.getSource()[0], xy.getSource()[1], board) && board.board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceInLeft(xy.getSource()[0], xy.getSource()[1] - 1, board)) {
                        if (moves[1].isV()) {
                            moves[1].setMovetype('t');
                        } else {
                            moves[1].setV(true);
                            if (board.board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceOnTop(xy.getSource()[0], xy.getSource()[1], board) && board.board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceInBottom(xy.getSource()[0], xy.getSource()[1], board)) {
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
                    } else if (board.board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceOnTop(xy.getSource()[0], xy.getSource()[1], board) && board.board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceInBottom(xy.getSource()[0], xy.getSource()[1], board)) {
                        moves[1].setMovetype('c');
                        moves[1].setV(true);
                        moves[1].setVct(new int[]{xy.getSource()[0] + 1, xy.getSource()[1]});
                        moves[1].setVct(new int[]{xy.getSource()[0] - 1, xy.getSource()[1]});
                    }
                }
            } else {
                if (board.board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceInLeft(xy.getSource()[0], xy.getSource()[1], board) && board.board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceInLeft(xy.getSource()[0], xy.getSource()[1] - 1, board)) {
                    moves[1].setV(true);
                    moves[1].setMovetype('l');
                    moves[1].setVct(xy.getSource());
                    moves[1].setVct(new int[]{xy.getSource()[0], xy.getSource()[1] + 1});
                    moves[1].setVct(new int[]{xy.getSource()[0], xy.getSource()[1] + 2});
                }
                if (board.board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceInRight(xy.getSource()[0], xy.getSource()[1], board) && board.board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceInRight(xy.getSource()[0], xy.getSource()[1] - 1, board)) {
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
                    if (board.board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceInBottom(xy.getSource()[0], xy.getSource()[1], board) && board.board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceInBottom(xy.getSource()[0], xy.getSource()[1], board)) {
                        if (moves[1].isV()) {
                            moves[1].setMovetype('t');
                        } else {
                            moves[1].setV(true);
                            if (board.board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceInLeft(xy.getSource()[0], xy.getSource()[1], board) && board.board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceInRight(xy.getSource()[0], xy.getSource()[1], board)) {
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
                    } else if (board.board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceInLeft(xy.getSource()[0], xy.getSource()[1], board) && board.board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceInRight(xy.getSource()[0], xy.getSource()[1], board)) {
                        moves[1].setMovetype('c');
                        moves[1].setV(true);
                        moves[1].setVct(new int[]{xy.getSource()[0], xy.getSource()[1] - 1});
                        moves[1].setVct(new int[]{xy.getSource()[0], xy.getSource()[1] - 2});
                    }
                }
            }
        } else {
            moves[1].setV(true);
            moves[1].setMovetype(board.board[xy.getTarget()[0]][xy.getTarget()[1]].getType());
        }
    }

     protected char getPieceInRight(int xcoordinate, int ycoordinate, BoardComponent board) {
        return ycoordinate < 8 ? board.board[xcoordinate][ycoordinate + 1].getType() : ' ';
    }

     protected char getPieceInLeft(int xcoordinate, int ycoordinate, BoardComponent board) {
         return ycoordinate > 1 ? board.board[xcoordinate][ycoordinate - 1].getType() : ' ';
     }

    protected char getPieceOnTop(int xcoordinate, int ycoordinate, BoardComponent board) {
        return xcoordinate < 8 ? board.board[xcoordinate + 1][ycoordinate].getType() : ' ';
    }

    protected char getPieceInBottom(int xcoordinate, int ycoordinate, BoardComponent board) {
        return xcoordinate > 0 ? board.board[xcoordinate - 1][ycoordinate].getType() : ' ';
    }

    public char getType() {
        return type;
    }
}
