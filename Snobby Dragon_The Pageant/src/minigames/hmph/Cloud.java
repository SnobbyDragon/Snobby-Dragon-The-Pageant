package minigames.hmph;
import java.awt.Graphics;

import com.madgag.gif.fmsware.GifDecoder;

import minigames.Thing;

public class Cloud extends Thing {
	
	private GifDecoder clouds = new GifDecoder();
	private int speed, isSlow, whichCloud; //type of cloud

	public Cloud(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		
		speed = (int) (Math.random()*3 + 3);
		isSlow = -1; //not slow moving
		
		clouds.read("Clouds.gif");
		whichCloud = (int) (Math.random()*(clouds.getFrameCount() - 1));
	}

	@Override
	public void draw(Graphics window) {
		// TODO Auto-generated method stub
		window.drawImage(clouds.getFrame(whichCloud), getXPos(), getYPos(), null);
		if (isSlow != -1) {
			moveX(getXPos() - speed + 2);
			isSlow++;
			if (isSlow == 24) { //3*8 = 24, which is the hmph animation time
				setSlow(-1);
			}
		}
		else {
			moveX(getXPos() - speed);
		}
	}
	
	public void setCloud() {
		speed = (int) (Math.random()*2 + 3);
		if (Math.random() > 0.05) {
			whichCloud = (int) (Math.random()*(clouds.getFrameCount() - 4));
		}
		else {
			whichCloud = clouds.getFrameCount() - (int) (Math.random()*4) - 1;
			if (whichCloud == 15) {
				System.out.println("whale cloud!");
			}
			else if (whichCloud == 16) {
				System.out.println("umbrella cloud!");
			}
			else if (whichCloud == 17) {
				System.out.println("monocle cloud!");
			}
			else if (whichCloud == 18) {
				System.out.println("hat cloud!");
			}
		}
	}
	
	public int getSlow() {
		return isSlow;
	}
	
	public void setSlow(int i) {
		isSlow = i;
	}

}
