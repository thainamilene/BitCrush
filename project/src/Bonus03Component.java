import javax.swing.ImageIcon;

public class Bonus03Component extends Pieces {

    public Bonus03Component() {
        super();
        style();
        setType(0);
    }

    public void setType(int x) {
        if (x == -1) {
            this.x = -1;
            button.setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/field.png"));
            type = 'D';
        } else {
            this.x = x;
            type = 'o';
            button.setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Bonus03.png"));
        }
    }

    public boolean verifyMovement(int target) {
       if (((index - board[target].getIndex()) * (index - board[target].getIndex()) == 1) || ((index - board[target].getIndex()) * (index - board[target].getIndex()) == 81)) {
           /*verifica se as pecas a serem movidas sao adjacentes*/
            moves[0] = new MovementComponent();
            moves[1] = new MovementComponent();
            moves[0].setMoveType('b');
            moves[0].setV(true);
            verifyTargetMovement(target);
            return true; //movimentos com os bonus sao sempre validos
        }
        return false;
    }
}
