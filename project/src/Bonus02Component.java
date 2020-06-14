public class Bonus02Component extends Pieces {

    public Bonus02Component() {
        super();
        setImageIcon(Main.class.getResource(".").getPath() + "/Images/Bonus02.png");
    }

    public void setType(int x) {
        type = '2';
    }

    public boolean verifyMovement(ITranslateMovementC xy, IPieces[][] board) {
      for (int i = 0; i < 2; i++) {
            moves[i] = new MovementComponent();
      }
      moves[0].setV(true);
      moves[0].setMovetype('2');
      verifyTargetMovement(xy, board);
      return true;
    }

    public void setImageIcon(String imageIcon) {
        this.imageIcon = imageIcon;
    }
}
