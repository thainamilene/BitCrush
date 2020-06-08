public class Bonus03Component extends Pieces {

    public Bonus03Component() {
        super();
    }

    public void setType(int x) {
        type = '3';
    }

    public boolean verifyMovement(ITranslateMovementC xy, BoardComponent board) {
       for (int i = 0; i < 2; i++) {
            moves[i] = new MovementComponent();
        }
        moves[0].setV(true);
        moves[0].setMovetype('3');
        verifyTargetMovement(xy, board);
        return true;
    }
}
