public class NormalPiecesComponent implements IPieces{
    char type;
    IMovementAttributes[] moves;

    public NormalPiecesComponent() {
        moves = new IMovementAttributes[2];
    }

    //to do
    public boolean verifyMovement(ITranslateMovementC xy, BoardComponent board) {
        for (int i = 0; i < 2; i++) {
            moves[i] = new MovementComponent();
        }
        if ((xy.getSource()[0] != xy.getTarget()[0]) || (xy.getSource()[1] != xy.getTarget()[1])) {
            if (xy.getSource()[0] == xy.getTarget()[0]) { //pecas se movendo na mesma linha
            System.out.println("same line");
                if (getType() == getPieceOnTop(xy.getTarget()[0], xy.getTarget()[1], board) && getType() == getPieceOnTop(xy.getTarget()[0] + 1, xy.getTarget()[1], board)) {
            System.out.println("same line 1111");
                    moves[0].setV(true);
                    moves[0].setMovetype('c');
                    moves[0].setVct(xy.getTarget());
                    moves[0].setVct(new int[]{xy.getTarget()[0] + 1, xy.getTarget()[1]});
                    moves[0].setVct(new int[]{xy.getTarget()[0] + 2, xy.getTarget()[1]});
                }
                if (getType() == getPieceInBottom(xy.getTarget()[0], xy.getTarget()[1], board) && getType() == getPieceInBottom(xy.getTarget()[0] - 1, xy.getTarget()[1], board)) {
            System.out.println("same line 2222");
                    if (moves[0].isV()) {
            System.out.println("same line 3333");
                        moves[0].setMovetype('b');
                    } else {
            System.out.println("same line 4444");
                        moves[0].setV(true);
                        moves[0].setMovetype('c');
                    }
                    moves[0].setVct(xy.getTarget());
                    moves[0].setVct(new int[]{xy.getTarget()[0] - 1, xy.getTarget()[1]});
                    moves[0].setVct(new int[]{xy.getTarget()[0] - 2, xy.getTarget()[1]});
                }
                if (moves[0].getMovetype() != 'b') {
                    if (getType() == getPieceInRight(xy.getTarget()[0], xy.getTarget()[1], board) && getType() == getPieceInRight(xy.getTarget()[0], xy.getTarget()[1] + 1, board)) {
                        System.out.println("same line 5555");
                        if (moves[0].isV()) {
                            System.out.println("same line 6666");
                            moves[0].setMovetype('t');
                        } else {
                            System.out.println("same line 7777");
                            moves[0].setV(true);
                            if (getType() == getPieceOnTop(xy.getTarget()[0], xy.getTarget()[1], board) && getType() == getPieceInBottom(xy.getTarget()[0], xy.getTarget()[1], board)) {
                                System.out.println("same line 8888");
                                moves[0].setMovetype('t');
                                moves[0].setVct(new int[]{xy.getTarget()[0] + 1, xy.getTarget()[1]});
                                moves[0].setVct(new int[]{xy.getTarget()[0] - 1, xy.getTarget()[1]});
                            } else {
                                System.out.println("same line 9999");
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
                if (getType() == getPieceInLeft(xy.getTarget()[0], xy.getTarget()[1], board) && getType() == getPieceInLeft(xy.getTarget()[0] , xy.getTarget()[1] - 1, board)) {
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
            if (board.board[xy.getTarget()[0]][xy.getTarget()[1]] instanceof NormalPiecesComponent){
                if (xy.getTarget()[0] == xy.getSource()[0]){
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
                    if (board.board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceInRight(xy.getSource()[0], xy.getSource()[1], board) && board.board[xy.getTarget()[0]][xy.getTarget()[1]].getType() == getPieceInRight(xy.getSource()[0] , xy.getSource()[1] - 1, board)) {
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
            }
        }
        return moves[0].isV() || moves[1].isV();
    }

    private char getPieceInRight(int xcoordinate, int ycoordinate, BoardComponent board) {
        return ycoordinate < 8 ? board.board[xcoordinate][ycoordinate + 1].getType() : ' ';
    }

     private char getPieceInLeft(int xcoordinate, int ycoordinate, BoardComponent board) {
         return ycoordinate > 1 ? board.board[xcoordinate][ycoordinate - 1].getType() : ' ';
     }

    private char getPieceOnTop(int xcoordinate, int ycoordinate, BoardComponent board) {
        return xcoordinate < 8 ? board.board[xcoordinate + 1][ycoordinate].getType() : ' ';
    }

    private char getPieceInBottom(int xcoordinate, int ycoordinate, BoardComponent board) {
        return xcoordinate > 0 ? board.board[xcoordinate - 1][ycoordinate].getType() : ' ';
    }

    public void setType(int x) {
        switch (x) {
            case 0:
                this.type = '+';
                break;
            case 1:
                this.type = '*';
                break;
            case 2:
                this.type = '@';
                break;
            case 3:
                this.type = '$';
                break;
            case 4:
                this.type = '#';
                break;
            case 5:
                this.type = '?';
                break;
            case 6:
                this.type = '&';
                break;
            case 7:
                this.type = '§';
                break;
            default:
                this.type = 'º';
                break;
        }

    }
    public char getType() {
        return type;
    }
}
