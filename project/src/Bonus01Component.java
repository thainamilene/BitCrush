public class Bonus01Component extends Bonus {

    public void setType(int x) {
        this.type = '1';
    }

    @Override
    public boolean verifyMovement(ITranslateMovementC xy, BoardComponent board) {
        return false;
    }
}