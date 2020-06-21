import javax.swing.*;

public class Bonus02Component extends Pieces {


    public Bonus02Component() {
        super();
        style();
        setType(0);
    }


    public void setType(int x) {
        this.type = '*';
        this.x = x;
        if (x == -1) {
            button.setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/field.png"));
        } else {
            button.setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Bonus02.png"));
        }
    }

    @Override
    public boolean verifyMovement(int target) {
        if (((index - board[target].getIndex()) * (index - board[target].getIndex()) == 1) || ((index - board[target].getIndex()) * (index - board[target].getIndex()) == 81)) {
            moves[0] = new MovementComponent();
            moves[1] = new MovementComponent();
            moves[0].setMoveType('2');
            moves[0].setV(true);
            verifyTargetMovement(target);
            return true;
        }
        return false;
    }
}
