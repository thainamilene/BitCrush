import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public abstract class Pieces implements IPieces {
    protected int x;
    protected char type;
    protected int index;
    protected int[] position;
    protected JButton button;
    protected IBoard Board;
    protected IPieces[][] board;
    protected IMovementAttributes[] moves;

    public Pieces() {
        super();
        button = new JButton();
        moves = new IMovementAttributes[2];
        moves[0] = new MovementComponent();
        moves[1] = new MovementComponent();
    }

    public abstract void setType (int x);

    public abstract boolean verifyMovement(ITranslateMovementC xy);

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public char getType() {
        return type;
    }

    public void setPosition(int i, int j) {
        position = new int[]{i, j};
    }

    public JButton getButton() {
        return button;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println(position[0] + ", " + position[1]);
        Board.translate(position);
    }

    public void setBoard(IBoard Board) {
        this.Board = Board;
        this.board = Board.getBoard();
    }

    public int[] getPosition() {
        return position;
    }

    public int getX() {
        return x;
    }

    public IMovementAttributes[] getMoves() {
        return moves;
    }

    protected void style() {
        button.setSize(50 , 50);
        button.setBorderPainted(true);
        button.setBackground(new Color(0x847C9D));
    }

    protected char getPieceInLeft (int line, int column) {
        return column > 0 ? board[line][column - 1].getType() : ' ';
    }
    protected char getPieceInRight (int line, int column) {
        return column < 8 ? board[line][column + 1].getType() : ' ';
    }
     protected char getPieceInBottom (int line, int column) {
        return line < 8 ? board[line + 1][column].getType() : ' ';
    }
     protected char getPieceOnTop (int line, int column) {
        return line > 0 ? board[line - 1][column].getType() : ' ';
    }

    protected void verifyTargetMovement(ITranslateMovementC xy) {

    }
}
