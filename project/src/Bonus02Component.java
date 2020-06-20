import java.awt.event.ActionEvent;

public class Bonus02Component extends Pieces {


    public Bonus02Component() {
        super();
        style();
    }


    public void setType(int x) {
        this.type = '*';
    }

    public char getType() {
        return 0;
    }

    public void setDead(boolean dead) {

    }

    public boolean isDead() {
        return false;
    }

    public int getX() {
        return 0;
    }

    public IMovementAttributes[] getMoves() {
        return new IMovementAttributes[0];
    }

    public void setIndex(int index) {

    }

    public int getIndex() {
        return 0;
    }

    public String getImageIcon() {
        return null;
    }

    public boolean verifyMovement(ITranslateMovementC xy) {
        return false;
    }

    public void setImageIcon(String imageIcon) {

    }

    public void setBoard(IBoard board) {

    }

    public void setPosition(int i, int j) {

    }

    public int[] getPosition() {
        return new int[0];
    }

    public void actionPerformed(ActionEvent actionEvent) {

    }
}
