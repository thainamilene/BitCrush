import javax.swing.*;

public class Bonus02Component extends Pieces {

    public void setType(int x) {
        type = '2';
    }

    @Override
    public char gettType() {
        return 0;
    }

    public char getType(int i, int l) {
        return 0;
    }

    public Bonus02Component() {
        super();
        setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "Images/Bonus02.png"));
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

    protected char getType(int i) {
        return 0;
    }
}
