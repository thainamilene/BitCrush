public class Bonus01Component extends Pieces {

    public Bonus01Component() {
        super();
        setImageIcon(Main.class.getResource(".").getPath() + "/Images/Bonus01.png");
    }

    public void setType(int x) {
        this.type = '1';
    }

    public boolean verifyMovement(ITranslateMovementC xy, IPieces[][] board) {
       for (int i = 0; i < 2; i++) {
            moves[i] = new MovementComponent();
       }
       moves[0].setV(true);
       moves[0].setMovetype('1');
       verifyTargetMovement(xy, board);
       return true;
    }

    public void setImageIcon(String imageIcon) {
        this.imageIcon = imageIcon;
    }
}