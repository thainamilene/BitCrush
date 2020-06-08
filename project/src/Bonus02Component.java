public class Bonus02Component extends Pieces {

    public void setType(int x) {
        type = '2';
    }

    public Bonus02Component() {
        super();
    }

    public boolean verifyMovement(ITranslateMovementC xy, BoardComponent board) {
      for (int i = 0; i < 2; i++) {
            moves[i] = new MovementComponent();
      }
      moves[0].setV(true);
      moves[0].setMovetype('2');
      verifyTargetMovement(xy, board);
      return true;
    }
}
