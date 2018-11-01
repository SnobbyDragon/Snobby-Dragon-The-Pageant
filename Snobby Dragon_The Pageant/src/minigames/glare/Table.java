package minigames.glare;

import java.awt.Graphics2D;

import com.madgag.gif.fmsware.GifDecoder;

import minigames.Thing;

public class Table extends Thing {
	
	private static GifDecoder table = new GifDecoder("Table.gif"); //TODO: MAKE TABLE ANIMATION

	public Table(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D window) {
		// TODO Auto-generated method stub
		int n = 0; //no animation right now
		window.drawImage(table.getFrame(n), getXPos(), getYPos(), null);
	}

}
