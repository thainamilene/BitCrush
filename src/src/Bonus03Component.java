import javax.swing.ImageIcon;

public class Bonus03Component extends Pieces {

	private static ImageIcon image = new ImageIcon();
	
    public Bonus03Component() {
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
            type = 'o';
            button.setIcon(image);
        }
    }

    public boolean verifyMovement(int target) {
        moves[0] = new MovementComponent();
        moves[1] = new MovementComponent();
        moves[0].setMoveType('b');
        moves[0].setV(true);
        verifyTargetMovement(target);
        return true; //movimentos com os bonus sao sempre validos
    }
    
    public void addImage(ImageIcon image) {
		Bonus03Component.image = image;
		button.setIcon(image);
	}
    
    
    
}
