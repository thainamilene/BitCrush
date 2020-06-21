import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public abstract class Pieces implements IPieces {
    protected int x;
    protected char type;
    protected int index;
    protected JButton button;
    protected IBoard Board;
    protected IPieces[] board;
    protected IMovementAttributes[] moves;

    public Pieces() {
        super();
        button = new JButton();
        moves = new IMovementAttributes[2];
        moves[0] = new MovementComponent();
        moves[1] = new MovementComponent();
        button.addActionListener(this);
    }

    public abstract void setType (int x);

    public abstract boolean verifyMovement(int target);

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public char getType() {
        return type;
    }

    public JButton getButton() {
        return button;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("index: " + index);
        Board.translate(index);
    }

    public void setBoard(IBoard Board) {
        this.Board = Board;
        this.board = Board.getBoard();
    }

    public int getX() {
        return this.x;
    }

    public IMovementAttributes[] getMoves() {
        return moves;
    }

    protected void style() {
        button.setSize(50 , 50);
        button.setBorderPainted(true);
        button.setBackground(new Color(0x847C9D));
    }

    protected char getPieceInLeft (int xIndex) {
        return (xIndex%9)!=0 ? board[xIndex-1].getType() : ' ';
    }
    protected char getPieceInRight (int xIndex) {
        return (xIndex+1)%9 != 0 || xIndex == 0 ? board[xIndex + 1].getType() : ' ';
    }
    protected char getPieceInBottom (int xIndex) {
        return xIndex < 72 ? board[xIndex + 9].getType() : ' ';
    }
    protected char getPieceOnTop (int xIndex) {
        return xIndex > 8 ? board[xIndex - 9].getType() : ' ';
    }

    protected void verifyTargetMovement(int target) {
        if (board[target] instanceof  NormalPiecesComponent) {
            if ((index - board[target].getIndex()) * (index - board[target].getIndex()) == 1) { // pecas movendo na mesma linha
                if (board[target].getType() == getPieceOnTop(index) && board[target].getType() == getPieceOnTop(index - 9)) {
                    moves[1].setV(true);
                    moves[1].setMoveType('c');
                    moves[1].setVct(index);
                    moves[1].setVct(index - 9);
                    moves[1].setVct(index - 18);
                    if (board[target].getType() == getPieceInBottom(index)) {
                        moves[1].setMoveType('1');
                        moves[1].setVct(index + 9);
                    }
                }
                if (board[target].getType() == getPieceInBottom(index) && board[target].getType() == getPieceInBottom(index + 9)) {
                    if (moves[1].isV()) {
                        moves[1].setMoveType('b');
                        moves[1].setVct(index + 18);
                    } else {
                        moves[1].setV(true);
                        moves[1].setMoveType('c');
                        moves[1].setVct(index);
                        moves[1].setVct(index + 9);
                        moves[1].setVct(index + 18);
                        if (board[target].getType() == getPieceOnTop(index)) {
                            moves[1].setMoveType('1');
                            moves[1].setVct(index - 9);
                        }
                    }
                } else if (!moves[1].isV() && board[target].getType() == getPieceOnTop(index) && board[target].getType() == getPieceInBottom(index)) {
                    moves[1].setV(true);
                    moves[1].setMoveType('c');
                    moves[1].setVct(index);
                    moves[1].setVct(index + 9);
                    moves[1].setVct(index - 9);
                }
                if (moves[1].getMovetype() != 'b') {
                    if (board[target].getType() == getPieceInLeft(index) && board[target].getType() == getPieceInLeft(index - 1)) {
                        if (moves[1].isV()) {
                            moves[1].setMoveType('2');
                        } else {
                            moves[1].setV(true);
                            moves[1].setMoveType('l');
                            moves[1].setVct(index);
                        }
                        moves[1].setVct(index - 1);
                        moves[1].setVct(index - 2);
                    }
                }
            } else {
                if (board[target].getType() == getPieceInRight(index) && board[target].getType() == getPieceInRight(index + 1)) {
                    moves[1].setV(true);
                    moves[1].setMoveType('l');
                    moves[1].setVct(index);
                    moves[1].setVct(index + 1);
                    moves[1].setVct(index + 2);
                    if (board[target].getType() == getPieceInLeft(index)) {
                        moves[1].setMoveType('1');
                        moves[1].setVct(index - 1);
                    }
                }
                if (board[target].getType() == getPieceInLeft(index) && board[target].getType() == getPieceInLeft(index - 1)) {
                    if (moves[1].isV()) {
                        moves[1].setMoveType('b');
                        moves[1].setVct(index - 2);
                    } else {
                        moves[1].setV(true);
                        moves[1].setMoveType('l');
                        moves[1].setVct(index);
                        moves[1].setVct(index - 1);
                        moves[1].setVct(index - 2);
                        if (board[target].getType() == getPieceInRight(index)) {
                            moves[1].setMoveType('1');
                            moves[1].setVct(index + 1);
                        }
                    }
                } else if (!moves[1].isV() && board[target].getType() == getPieceInLeft(index) && board[target].getType() == getPieceInRight(index)) {
                    moves[1].setV(true);
                    moves[1].setMoveType('l');
                    moves[1].setVct(index);
                    moves[1].setVct(index + 1);
                    moves[1].setVct(index - 1);
                }
                if (moves[1].getMovetype() != 'b') {
                    if (board[target].getType() == getPieceOnTop(index) && board[target].getType() == getPieceOnTop(index - 9)) {
                        if (moves[1].isV()) {
                            moves[1].setMoveType('2');
                        } else {
                            moves[1].setV(true);
                            moves[1].setMoveType('c');
                            moves[1].setVct(index);
                        }
                        moves[1].setVct(index - 9);
                        moves[1].setVct(index - 18);
                    }
                }
            }
        } else {
            System.out.println(board[target].getClass());
            moves[1].setV(true);
            moves[1].setMoveType(board[target].getType());
        }
    }
}
