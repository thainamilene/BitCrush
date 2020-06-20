import java.awt.event.ActionEvent;

public class Bonus01Component extends Pieces {

    public Bonus01Component() {
        super();
        style();
    }

    public void setType(int x) {
        type = '+';
    }

    @Override
    public boolean verifyMovement(int target) {
        return false;
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