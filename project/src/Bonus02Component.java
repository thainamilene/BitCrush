import javax.swing.ImageIcon;

public class Bonus02Component extends Pieces {

    public Bonus02Component() {
        super();
        style();
        setType(0);
    }

    public void setType(int x) {
        if (x == -1) {
            this.x = -1;
            button.setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/field.png"));
            this.type = 'D';
        } else {
            this.x = x;
            this.type = '*';
            button.setIcon(new ImageIcon(Main.class.getResource(".").getPath() + "/Images/Bonus02.png"));
        }
    }

    public boolean verifyMovement(int target) {
        /*verifica se as pecas a serem movidas sao adjacentes*/
        if (((index - board[target].getIndex()) * (index - board[target].getIndex()) == 1) || ((index - board[target].getIndex()) * (index - board[target].getIndex()) == 81)) {
            moves[0] = new MovementComponent();
            moves[1] = new MovementComponent();
            moves[0].setMoveType('2');
            moves[0].setV(true);
            verifyTargetMovement(target);
            return true;
        }
        return false;  //movimentos com os bonus sao sempre validos
    }
}
