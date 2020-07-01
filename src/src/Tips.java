import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Tips extends JLabel{
	
	private static final long serialVersionUID = -2564648613395772212L;
	private ImageIcon image;
	private Color color01;
	private Color color02;
	
	public Tips(ImageIcon image) {
		super();
		setIcon(image);
	}
	
	public Tips(ImageIcon image, Color color01, Color color02) {
		super();
		setIcon(image);
		this.color01 = color01;
		this.color02 = color02;
	}
	
	public void setImage(ImageIcon image) {
		this.image = image;
		setIcon(image);
	}
	
	public void setColors(Color color01, Color color02) {
		this.color02 = color02;
		this.color01 = color01;
	}
	
	
	public void setColor01(Color color01) {
		this.color01 = color01;
	}
	
	public void setColor02(Color color02) {
		this.color02 = color02;
	}
	
	public ImageIcon getImage() {
		return image;
	}
	
	public Color getColor01() {
		return color01;
	}
	
	public Color getColor02() {
		return color02;
	}
}
