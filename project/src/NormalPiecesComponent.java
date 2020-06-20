import java.util.IllegalFormatCodePointException;

public class NormalPiecesComponent extends Pieces{

    public NormalPiecesComponent() {
        super();
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
    }

    public boolean verifyMovement(ITranslateMovementC xy, IPieces[][] board) {
        moves[0] = new MovementComponent();
        moves[1] = new MovementComponent();

        if ((position[0] != xy.getTarget()[0] || position[1] != xy.getTarget()[1]) && (position[0] == xy.getTarget()[0] || position[1] == xy.getTarget()[1])) {
            if (position[0] == xy.getTarget()[0]) { //movendo na mesma linha
                if((type == getPieceOnTop(xy.getTarget()[0], xy.getTarget()[1], board) && (type == getPieceOnTop(xy.getTarget()[0] + 1, xy.getTarget()[1], board)))) {
                    moves[0].setV(true);
                    moves[0].setMovetype('c');
                    moves[0].setVct(xy.getTarget());
                    moves[0].setVct(new int[]{xy.getTarget()[0] + 1, xy.getTarget()[1]});
                    moves[0].setVct(new int[]{xy.getTarget()[0] + 2, xy.getTarget()[1]});
                    if (type == getPieceInBottom(xy.getTarget()[0], xy.getTarget()[1], board)) {
                        moves[0].setMovetype('4');
                        moves[0].setVct(new int[]{xy.getTarget()[0] - 1, xy.getTarget()[1]});
                    }
                }
                if ((type == getPieceInBottom(xy.getTarget()[0], xy.getTarget()[1], board)) && type == getPieceInBottom(xy.getTarget()[0] - 1, xy.getTarget()[1], board)) {
                    if (moves[0].isV()) {
                        moves[0].setMovetype('b');
                    } else {
                        moves[0].setV(true);
                        moves[0].setMovetype('c');
                        moves[0].setVct(xy.getTarget());
                        moves[0].setVct(new int[]{xy.getTarget()[0] - 1, xy.getTarget()[1]});
                        if (type == getPieceOnTop(xy.getTarget()[0], xy.getTarget()[1], board)) {
                            moves[0].setMovetype('4');
                            moves[0].setVct(new int[]{xy.getTarget()[0] + 1 , xy.getTarget()[1]});
                        }
                    }
                    moves[0].setVct(new int[]{xy.getTarget()[0] - 2, xy.getTarget()[1]});
                }
                if (moves[0].getMovetype() != 'b') {
                    if (type == getPieceInRight(xy.getTarget()[0], xy.getTarget()[1], board) && type == getPieceInRight(xy.getTarget()[0], xy.getTarget()[1] + 1, board)) {
                        if (moves[0].isV()) {
                            if (moves[0].getMovetype() == '4') {
                                moves[0].remove();
                            }
                            moves[0].setMovetype('t');
                        } else {
                            moves[0].setV(true);
                            moves[0].setMovetype('c');
                            moves[0].setVct(xy.getTarget());
                        }
                        moves[0].setVct(new int[]{xy.getTarget()[0], xy.getTarget()[1] + 1});
                        moves[0].setVct(new int[]{xy.getTarget()[0], xy.getTarget()[1] + 2});
                    }
                }
            }
            if (position[1] == xy.getTarget()[1]){ //movendo na mesma coluna
                if (type == getPieceInLeft(xy.getTarget()[0], xy.getTarget()[1], board) && type == getPieceInLeft(xy.getTarget()[0], xy.getTarget()[1] - 1, board)) {
                    moves[0].setV(true);
                    moves[0].setMovetype('c');
                    moves[0].setVct(xy.getTarget());
                    moves[0].setVct(new int[]{xy.getTarget()[0], xy.getTarget()[1] - 1});
                    moves[0].setVct(new int[]{xy.getTarget()[0], xy.getTarget()[1] - 2});
                    if (type == getPieceInRight(xy.getTarget()[0], xy.getTarget()[1],board)) {
                        moves[0].setMovetype('4');
                        moves[0].setVct(new int[]{xy.getTarget()[0], xy.getTarget()[1] + 1});
                    }
                }
                if (type == getPieceInRight(xy.getTarget()[0], xy.getTarget()[1], board) && type == getPieceInRight(xy.getTarget()[0], xy.getTarget()[1] + 1, board)) {
                    if (moves[0].isV()) {
                        moves[0].setMovetype('b');
                    } else {
                        moves[0].setMovetype('c');
                        moves[0].setV(true);
                        moves[0].setVct(xy.getTarget());
                        moves[0].setVct(new int[]{xy.getTarget()[0], xy.getTarget()[1] + 1});
                        if (type == getPieceInLeft(xy.getTarget()[0], xy.getTarget()[1], board)) {
                            moves[0].setMovetype('4');
                            moves[0].setVct(new int[]{xy.getTarget()[0], xy.getTarget()[1] - 1});
                        }
                    }
                    moves[0].setVct(new int[]{xy.getTarget()[0], xy.getTarget()[1] + 2});
                }
                if (moves[0].getMovetype() != 'b') {
                    if (type == getPieceOnTop(xy.getTarget()[0], xy.getTarget()[1], board) && type == getPieceOnTop(xy.getTarget()[0] + 1, xy.getTarget()[1], board)) {
                        if (moves[0].isV()) {
                            if (moves[0].getMovetype() == '4') {
                                moves[0].remove();
                            }
                            moves[0].setMovetype('t');
                        } else {
                            moves[0].setV(true);
                            moves[0].setMovetype('c');
                            moves[0].setVct(xy.getTarget());
                        }
                        moves[0].setVct(new int[]{xy.getTarget()[0] + 1, xy.getTarget()[1]});
                        moves[0].setVct(new int[]{xy.getTarget()[0] + 2, xy.getTarget()[1]});
                    }
                }
            }
        verifyTargetMovement(xy, board);
        }
        return moves[0].isV() || moves[1].isV();
    }

    public void setImageIcon(String imageIcon) {
        this.imageIcon = imageIcon;
    }
}
