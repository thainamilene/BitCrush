import javax.swing.*;

public class Bonus03Component extends Pieces {


    public Bonus03Component() {
        super();
        style();
        setType(0);
    }



    public void setType(int x) {
        this.x = x;
        type = '+';
        if (x == -1) {
            button.setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/field.png"));
        } else {
            button.setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Bonus03.png"));
        }
    }

    public boolean verifyMovement(int target) {
       if (((index - board[target].getIndex()) * (index - board[target].getIndex()) == 1) || ((index - board[target].getIndex()) * (index - board[target].getIndex()) == 81)) {
            moves[0] = new MovementComponent();
            moves[1] = new MovementComponent();
            moves[0].setMoveType('b');
            moves[0].setV(true);
            verifyTargetMovement(target);
            return true;
        }
        return false;
    }
}
