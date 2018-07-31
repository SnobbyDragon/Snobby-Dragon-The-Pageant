package minigames.glare;

import java.awt.Color;
import java.awt.Graphics2D;

import com.madgag.gif.fmsware.GifDecoder;

import minigames.Thing;

public class Monocle extends Thing {
	
	private static GifDecoder scope = new GifDecoder("Monocle Scope.gif"); //TODO: make a pew pew animation and monocle chain
	private int isGlaring; //counting glare frames
	private static final int GLARE_RATE = 1, GLARE_FRAME = scope.getFrameCount();
	private static final int RADIUS = scope.getFrameSize().height/2;
	private static final Color LIGHT = new Color(1f, 1f, 1f, 0.25f);
	private static final int CROSSHAIR = 6; //crosshair error diff radii

	public Monocle(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		isGlaring = 0; //not glaring
	}

	@Override
	public void draw(Graphics2D window) {
		// TODO Auto-generated method stub
		window.setColor(LIGHT);
		window.fillOval(getXPos() - RADIUS + CROSSHAIR, getYPos() - RADIUS + CROSSHAIR, (RADIUS - CROSSHAIR)*2, (RADIUS - CROSSHAIR)*2);
		int n = isGlaring/GLARE_RATE%GLARE_FRAME;
		window.drawImage(scope.getFrame(n), getXPos() - RADIUS, getYPos() - RADIUS, RADIUS*2, RADIUS*2, null);
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
