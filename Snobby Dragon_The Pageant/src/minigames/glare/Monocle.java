package minigames.glare;

import java.awt.Graphics2D;

import com.madgag.gif.fmsware.GifDecoder;

import minigames.Thing;

public class Monocle extends Thing {
	
	private static GifDecoder scope = new GifDecoder("Monocle.gif"); //TODO: make a pew pew animation and monocle chain
	private int isGlaring; //counting glare frames

	public Monocle(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		isGlaring = 0; //not glaring
	}

	@Override
	public void draw(Graphics2D window) {
		// TODO Auto-generated method stub
		window.fillOval(getXPos() - 50, getYPos() - 50, 100, 100);
	}

}
