import javax.swing.ImageIcon;

public class Bonus01Component extends Pieces {

	private static ImageIcon image = new ImageIcon();
	
    public Bonus01Component() {
        super();
        style();
        setType(0);
    }

    public void setType(int x) {
        if (x == -1) {
            this.x = -1;
            type = 'D';
        } else {
            this.x = x;
            type = '+';
            button.setIcon(image);
        }
    }

    public boolean verifyMovement(int target) {
        moves[0] = new MovementComponent();
        moves[1] = new MovementComponent();
        moves[0].setMoveType('1');
        moves[0].setV(true);
        verifyTargetMovement(target);
        return true;  //movimentos com os bonus sao sempre validos
    }

	public void addImage(ImageIcon image) {
		Bonus01Component.image = image;
		button.setIcon(image);
	}
}