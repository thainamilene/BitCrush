public class Bonus02Component extends Bonus {

    public void setType(int x) {
        type = '2';
    }

    @Override
    public boolean verifyMovement(ITranslateMovementC xy, BoardComponent board) {
        return false;
    }
}
