package minigames.glare;

import java.awt.Graphics2D;

import com.madgag.gif.fmsware.GifDecoder;

import minigames.Thing;

public class Monocle extends Thing {
	
	private static GifDecoder scope = new GifDecoder("Monocle Scope.gif"); //TODO: make a pew pew animation and monocle chain
	private int isGlaring; //counting glare frames
	private static final int GLARE_RATE = 1, GLARE_FRAME = scope.getFrameCount();

	public Monocle(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		isGlaring = 0; //not glaring
	}

	@Override
	public void draw(Graphics2D window) {
		// TODO Auto-generated method stub
		int n = isGlaring/GLARE_RATE%GLARE_FRAME;
		window.drawImage(scope.getFrame(n), getXPos() - 50, getYPos() - 50, 100, 100, null);
		if (isGlaring/GLARE_RATE == GLARE_FRAME) { //default state
			isGlaring = 0;
		}
		if (isGlaring > 0) { //glare animation
			isGlaring++;
		}
	}
	
	public void shoot() { //positively GLARES at the target
		isGlaring = 1;
	}

}
