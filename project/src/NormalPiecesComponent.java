public class NormalPiecesComponent extends Pieces{

    public NormalPiecesComponent() {
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
                this.type = '0';
                setImageIcon(Main.class.getResource(".").getPath() + "/Images/Piece01.png");
                break;
            case 1:
                this.type = '*';
                setImageIcon(Main.class.getResource(".").getPath() + "/Images/Piece02.png");
                break;
            case 2:
                this.type = '3';
                setImageIcon(Main.class.getResource(".").getPath() + "/Images/Piece03.png");
                break;
            case 3:
                this.type = '$';
                setImageIcon(Main.class.getResource(".").getPath() + "/Images/Piece04.png");
                break;
            case 4:
                this.type = '>';
                setImageIcon(Main.class.getResource(".").getPath() + "/Images/Piece05.png");
                break;
            case 5:
                this.type = '?';
                setImageIcon(Main.class.getResource(".").getPath() + "/Images/Piece06.png");
                break;
            case 6:
                this.type = '&';
                setImageIcon(Main.class.getResource(".").getPath() + "/Images/Piece07.png");
                break;
            case 7:
                this.type = '§';
                setImageIcon(Main.class.getResource(".").getPath() + "/Images/Piece08.png");

                break;
            case 8:
                this.type = 'º';
                setImageIcon(Main.class.getResource(".").getPath() + "/Images/Piece09.png");
                break;
        }
        this.x = x;
    }

    public void setImageIcon(String imageIcon) {
        this.imageIcon = imageIcon;
    }
}
