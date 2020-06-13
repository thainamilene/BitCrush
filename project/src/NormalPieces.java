public class NormalPieces extends Pieces{

    IPieces piece;

    public NormalPieces() {
        super();
    }

    public boolean verifyMovement(ITranslateMovementC xy, IPieces[][] board) {
        for (int i = 0; i < 2; i++) {
            moves[i] = new MovementComponent();
        }
        if ((xy.getSource()[0] != xy.getTarget()[0]) || (xy.getSource()[1] != xy.getTarget()[1])) {
            if (xy.getSource()[0] == xy.getTarget()[0]) { //pecas se movendo na mesma linha
                if (getType() == getPieceOnTop(xy.getTarget()[0], xy.getTarget()[1], board) && getType() == getPieceOnTop(xy.getTarget()[0] + 1, xy.getTarget()[1], board)) {
                    moves[0].setV(true);
                    moves[0].setMovetype('c');
                    moves[0].setVct(xy.getTarget());
                    moves[0].setVct(new int[]{xy.getTarget()[0] + 1, xy.getTarget()[1]});
                    moves[0].setVct(new int[]{xy.getTarget()[0] + 2, xy.getTarget()[1]});
                }
                if (getType() == getPieceInBottom(xy.getTarget()[0], xy.getTarget()[1], board) && getType() == getPieceInBottom(xy.getTarget()[0] - 1, xy.getTarget()[1], board)) {
                    if (moves[0].isV()) {
                        moves[0].setMovetype('b');
                    } else {
                        moves[0].setV(true);
                        moves[0].setMovetype('c');
                    }
                    moves[0].setVct(xy.getTarget());
                    moves[0].setVct(new int[]{xy.getTarget()[0] - 1, xy.getTarget()[1]});
                    moves[0].setVct(new int[]{xy.getTarget()[0] - 2, xy.getTarget()[1]});
                }
                if (moves[0].getMovetype() != 'b') {
                    if (getType() == getPieceInRight(xy.getTarget()[0], xy.getTarget()[1], board) && getType() == getPieceInRight(xy.getTarget()[0], xy.getTarget()[1] + 1, board)) {
                        if (moves[0].isV()) {
                            moves[0].setMovetype('t');
                        } else {
                            moves[0].setV(true);
                            if (getType() == getPieceOnTop(xy.getTarget()[0], xy.getTarget()[1], board) && getType() == getPieceInBottom(xy.getTarget()[0], xy.getTarget()[1], board)) {
                                moves[0].setMovetype('t');
                                moves[0].setVct(new int[]{xy.getTarget()[0] + 1, xy.getTarget()[1]});
                                moves[0].setVct(new int[]{xy.getTarget()[0] - 1, xy.getTarget()[1]});
                            } else {
                                moves[0].setMovetype('l');
                            }
                        }
                        moves[0].setVct(xy.getTarget());
                        moves[0].setVct(new int[]{xy.getTarget()[0], xy.getTarget()[1] + 1});
                        moves[0].setVct(new int[]{xy.getTarget()[0], xy.getTarget()[1] + 2});
                    } else if (getType() == getPieceOnTop(xy.getTarget()[0], xy.getTarget()[1], board) && getType() == getPieceInBottom(xy.getTarget()[0], xy.getTarget()[1], board)) {
                        moves[0].setV(true);
                        moves[0].setMovetype('c');
                        moves[0].setVct(new int[]{xy.getTarget()[0] + 1, xy.getTarget()[1]});
                        moves[0].setVct(new int[]{xy.getTarget()[0] - 1, xy.getTarget()[1]});
                    }
                }
            } else {
                if (getType() == getPieceInLeft(xy.getTarget()[0], xy.getTarget()[1], board) && getType() == getPieceInLeft(xy.getTarget()[0], xy.getTarget()[1] - 1, board)) {
                    moves[0].setV(true);
                    moves[0].setMovetype('l');
                    moves[0].setVct(xy.getTarget());
                    moves[0].setVct(new int[]{xy.getTarget()[0], xy.getTarget()[1] - 1});
                    moves[0].setVct(new int[]{xy.getTarget()[0], xy.getTarget()[1] - 2});
                }
                if (getType() == getPieceInRight(xy.getTarget()[0], xy.getTarget()[1], board) && getType() == getPieceInRight(xy.getTarget()[0], xy.getTarget()[1] + 1, board)) {
                    if (moves[0].isV()) {
                        moves[0].setMovetype('b');
                    } else {
                        moves[0].setV(true);
                        moves[0].setMovetype('l');
                    }
                    moves[0].setVct(xy.getTarget());
                    moves[0].setVct(new int[]{xy.getTarget()[0], xy.getTarget()[1] - 1});
                    moves[0].setVct(new int[]{xy.getTarget()[0], xy.getTarget()[1] - 2});
                }
                if (moves[0].getMovetype() != 'b') {
                    if (getType() == getPieceOnTop(xy.getTarget()[0], xy.getTarget()[1], board) && getType() == getPieceOnTop(xy.getTarget()[0] + 1, xy.getTarget()[1], board)) {
                        if (moves[0].isV()) {
                            moves[0].setMovetype('t');
                        } else {
                            moves[0].setV(true);
                            if (getType() == getPieceInLeft(xy.getTarget()[0], xy.getTarget()[1], board) && getType() == getPieceInRight(xy.getTarget()[0], xy.getTarget()[1], board)) {
                                moves[0].setMovetype('t');
                                moves[0].setVct(new int[]{xy.getTarget()[0], xy.getTarget()[1] + 1});
                                moves[0].setVct(new int[]{xy.getTarget()[0], xy.getTarget()[1] - 1});
                            } else {
                                moves[0].setMovetype('c');
                            }
                        }
                        moves[0].setVct(xy.getTarget());
                        moves[0].setVct(new int[]{xy.getTarget()[0] + 1, xy.getTarget()[1]});
                        moves[0].setVct(new int[]{xy.getTarget()[0] + 2, xy.getTarget()[1]});
                    } else if (getType() == getPieceInLeft(xy.getTarget()[0], xy.getTarget()[1], board) && getType() == getPieceInRight(xy.getTarget()[0], xy.getTarget()[1], board)) {
                        moves[0].setV(true);
                        moves[0].setMovetype('l');
                        moves[0].setVct(new int[]{xy.getTarget()[0], xy.getTarget()[1] + 1});
                        moves[0].setVct(new int[]{xy.getTarget()[0], xy.getTarget()[1] - 1});
                    }
                }

            }
            verifyTargetMovement(xy, board);
        }
        return moves[0].isV() || moves[1].isV();
    }

    public void setType(int x) {
        this.x = x;
        switch (x) {
            case 0:
                piece = new Piece01Component();
                break;
            case 1:
                piece = new Piece02Component();
                break;
            case 2:
                piece = new Piece03Component();
                break;
            case 3:
                piece = new Piece04Component();
                break;
            case 4:
                piece = new Piece05Component();
                break;
            case 5:
                piece = new Piece06Component();
                break;
            case 6:
                piece = new Piece07Component();
                break;
            case 7:
                piece = new Piece08Component();
                break;
            case 8:
                piece = new Piece09Component();
                break;
        }
        this.type = piece.gettType();
    }

    public char gettType() {
        return piece.gettType();
    }
}
