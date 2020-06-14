import javax.swing.*;

public class Bonus03Component extends Pieces {

    public Bonus03Component() {
        super();
        setImageIcon(Main.class.getResource(".").getPath() + "/Images/Bonus03.png");
    }

    public void setType(int x) {
        type = '3';
    }

    public boolean verifyMovement(ITranslateMovementC xy, IPieces[][] board) {
       for (int i = 0; i < 2; i++) {
            moves[i] = new MovementComponent();
        }
        moves[0].setV(true);
        moves[0].setMovetype('3');
        verifyTargetMovement(xy, board);
        return true;
    }

    public void setImageIcon(String imageIcon) {
        this.imageIcon = imageIcon;
    }
}
