import javax.swing.*;

public class NormalPiecesComponent extends Pieces {

    public NormalPiecesComponent() {
        super();
        style();
    }

    public void setType(int x) {
        this.x = x;
        switch (x) {
            case 0:
                this.type = '1';
                button.setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Piece01.png"));
                break;
            case 1:
                this.type = '2';
                button.setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Piece02.png"));
                break;
            case 2:
                this.type = '3';
                button.setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Piece03.png"));
                break;
            case 3:
                this.type = '4';
                button.setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Piece04.png"));
                break;
            case 4:
                this.type = '5';
                button.setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Piece05.png"));
                break;
            case 5:
                this.type = '6';
                button.setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Piece06.png"));
                break;
            case 6:
                this.type = '7';
                button.setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Piece07.png"));
                break;
            case 7:
                this.type = '8';
                button.setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Piece08.png"));

                break;
            case 8:
                this.type = '9';
                button.setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Piece09.png"));
                break;
        }
        button.addActionListener(this);
    }

    public char getType() {
        return type;
    }

    public void setDead(boolean dead) {
    }

    public boolean isDead() {
        return false;
    }

    public boolean verifyMovement(ITranslateMovementC xy) {
        if (((index - board[xy.getTarget()[0]][xy.getTarget()[1]].getIndex())*(index - board[xy.getTarget()[0]][xy.getTarget()[1]].getIndex()) == 1) || ((index - board[xy.getTarget()[0]][xy.getTarget()[1]].getIndex())*(index - board[xy.getTarget()[0]][xy.getTarget()[1]].getIndex()) == 81)) {

        }
        return true;
    }
}
