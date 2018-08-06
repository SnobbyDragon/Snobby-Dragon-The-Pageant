package minigames.insults;

import java.awt.Graphics2D;

import com.madgag.gif.fmsware.GifDecoder;

import minigames.Thing;

public class Sky extends Thing {

	private static GifDecoder sky = new GifDecoder("Insults Sky.gif"); //TODO maybe make animated later
	
	public Sky(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D window) {
		// TODO Auto-generated method stub
		window.drawImage(sky.getImage(), getXPos(), getYPos(), null);
	}

}
