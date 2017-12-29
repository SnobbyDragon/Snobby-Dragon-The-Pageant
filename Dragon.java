import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Dragon extends Thing {

	private Image idle, moving;
	boolean isIdle;

	public Dragon(Graphics window, int x, int y) {
		super(x, y);	
		isIdle = false;
		idle = new ImageIcon(new String("Snobby Idle.gif")).getImage();
		moving = new ImageIcon(new String("Snobby Walk.gif")).getImage();
	}

	public void draw(Graphics window) {
		if (isIdle) {
			window.drawImage(idle, getXPos(), getYPos(), null);
		}
		else {
			window.drawImage(moving, getXPos(), getYPos(), null);
			moveX(getXPos() + 5);
		}
	}
	
}
