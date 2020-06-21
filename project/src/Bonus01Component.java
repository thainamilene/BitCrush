import javax.swing.*;

public class Bonus01Component extends Pieces {

    public Bonus01Component() {
        super();
        style();
        setType(0);
    }

    public void setType(int x) {
        if (x == -1) {
            this.x = -1;
            type = 'D';
            button.setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/field.png"));
        } else {
            this.x = x;
            type = '+';
            button.setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Bonus01.png"));
        }
    }

    public boolean verifyMovement(int target) {
        if (((index - board[target].getIndex()) * (index - board[target].getIndex()) == 1) || ((index - board[target].getIndex()) * (index - board[target].getIndex()) == 81)) {
            moves[0] = new MovementComponent();
            moves[1] = new MovementComponent();
            moves[0].setMoveType('1');
            moves[0].setV(true);
            verifyTargetMovement(target);
            return true;
        }
        return false;
    }
}