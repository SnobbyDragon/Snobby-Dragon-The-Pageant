package minigames.hmph;
import java.awt.Graphics;

import com.madgag.gif.fmsware.GifDecoder;

import minigames.Thing;

public class HmphBackground extends Thing {
	
	private static GifDecoder background = new GifDecoder("Hmph Background.gif");
	private int frame, isStop;

	public HmphBackground() {
		super(0, 0);
		// TODO Auto-generated constructor stub
		frame = 0;
		isStop = -1; //background is moving b/c dragon is walking
	}

	@Override
	public void draw(Graphics window) {
		// TODO Auto-generated method stub
		int n = frame%background.getFrameCount();
		window.drawImage(background.getFrame(n), getXPos(), getYPos(), null);
		if (isStop == -1) { //if moving, then animates
			frame++;
			if (frame == background.getFrameCount()) {
				frame = 0;
			}
		}
		else {
			isStop++;
			if (isStop == SnobbyDragon.getHmphRate()*SnobbyDragon.getHmphFrame()) { //3*8 = 24, which is the hmph animation time (rate*frames)
				setStop(-1);
			}
		}
	}
	
	public int getFrame() {
		return frame;
	}
	
	public int getStop() {
		return isStop;
	}
	
	public void setFrame(int i) {
		frame = i;
	}
	
	public void setStop(int i) {
		isStop = i;
	}

}
