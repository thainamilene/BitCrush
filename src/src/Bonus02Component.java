import javax.swing.ImageIcon;

public class Bonus02Component extends Pieces {

	private static ImageIcon image = new ImageIcon();
	
    public Bonus02Component() {
        super();
        style();
        setType(0);
    }

    public void setType(int x) {
        if (x == -1) {
            this.x = -1;
            this.type = 'D';
        } else {
            this.x = x;
            this.type = '*';
            button.setIcon(image);
        }
    }

    public boolean verifyMovement(int target) {
        moves[0] = new MovementComponent();
        moves[1] = new MovementComponent();
        moves[0].setMoveType('2');
        moves[0].setV(true);
        verifyTargetMovement(target);
        return true;
    }
    public void addImage(ImageIcon image) {
		Bonus02Component.image = image;
		button.setIcon(image);
	}
}
