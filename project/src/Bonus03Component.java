public class Bonus03Component extends Bonus{

    public void setType(int x) {
        type = '3';
    }

    //to do
       @Override
    public boolean verifyMovement(ITranslateMovementC xy, BoardComponent board) {
        return false;
    }
}
