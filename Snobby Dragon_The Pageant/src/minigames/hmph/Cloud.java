package minigames.hmph;
import java.awt.Graphics2D;

import com.madgag.gif.fmsware.GifDecoder;

import minigames.Thing;

public class Cloud extends Thing {
	
	private static GifDecoder clouds = new GifDecoder("Clouds.gif");
	private int speed, isSlow, whichCloud; //type of cloud

	public Cloud(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		
		speed = (int) (Math.random()*3 + 3);
		isSlow = -1; //not slow moving
		whichCloud = (int) (Math.random()*(clouds.getFrameCount() - 1));
	}

	@Override
	public void draw(Graphics2D window) {
		// TODO Auto-generated method stub
		window.drawImage(clouds.getFrame(whichCloud), getXPos(), getYPos(), null);
		if (isSlow != -1) {
			setX(getXPos() - speed + 2);
			isSlow++;
			if (isSlow == SnobbyDragon.getHmphRate()*SnobbyDragon.getHmphFrame()) { //3*8 = 24, which is the hmph animation time (rate*frames)
				isSlow = -1;
			}
		}
		else {
			setX(getXPos() - speed);
		}
	}
	
	public void setCloud() {
		speed = (int) (Math.random()*2 + 3);
		if (Math.random() > 0.05) {
			whichCloud = (int) (Math.random()*(clouds.getFrameCount() - 4));
		}
		else {
			whichCloud = clouds.getFrameCount() - (int) (Math.random()*4) - 1;
			switch (whichCloud) {
			case 15:
				System.out.println("whale cloud!");
				break;
			case 16:
				System.out.println("umbrella cloud!");
				break;
			case 17:
				System.out.println("monocle cloud!");
				break;
			case 18:
				System.out.println("hat cloud!");
				break;
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
