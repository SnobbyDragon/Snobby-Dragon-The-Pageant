package minigames.glare;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.madgag.gif.fmsware.GifDecoder;

import general.SnobbyPanel;
import minigames.Thing;

public class PeasantCutout extends Thing {
	
	private static final String[] ANIMATIONS = new String[]{"Peasant Fly Cutout.gif", "Peasant Walk Cutout.gif", "Peasant Peasant Cutout.gif", "Peasant Hoverboard Cutout.gif"};
	private static final int[] RATES = new int[]{SnobbyPanel.FPS/5, SnobbyPanel.FPS/13, SnobbyPanel.FPS/18, SnobbyPanel.FPS/21};
	private GifDecoder cutout = new GifDecoder(); //fall and up animation for when glared at
	private String animation;
	private int frame, rate, timeout; //counts the animation frames, rate at which the animation plays
	private boolean isShook, isGettingUp, isDown; //peasant glared at??, rising from the ashes of shookness, is down
	private Rectangle hitBox;
	private int tablePositionX, tablePositionY;
	
	public PeasantCutout(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		tablePositionX = x;
		tablePositionY = y;
		randomPeasant(); //random peasant in the beginning
		timeout = (int)(Math.random()*300); //some start up
	}

	@Override
	public void draw(Graphics2D window) {
		// TODO Auto-generated method stub
		if (isShook) { //is falling!! SHOOK because Snobby glared
			frame++;
			if (frame == rate*cutout.getFrameCount() - 1) { //cutout is utterly obliterated by the power of Snobby Dragon's glare, and thus resets
				//randomPeasant(); TODO: implement peasant type switching later... unexplained error right now :(
				isShook = false;
				isDown = true;
				isGettingUp = false;
			}
			//System.out.println("shook");
		}
		else if (isDown) {
			timeout++;
			shouldGetUp();
			//System.out.println("is down");
		}
		else if (isGettingUp) { //is getting up
			frame--;
			if (frame == 0) { //cutout is completely up
				isDown = false;
				isGettingUp = false;
			}
			//System.out.println("getting up");
		}
		int n = frame/rate%cutout.getFrameCount(); //frame counter
//		System.out.println(animation);
//		System.out.println("FRAME=" + frame + " " + n);
//		System.out.println("down=" + isDown + "\tshook=" + isShook + "\tisGettingUp=" + isGettingUp);
//		System.out.println("time=" + timeout);g
		window.drawImage(cutout.getFrame(n), getXPos(), getYPos(), null);
	}

	public void randomPeasant() { //sets this cutout to a random peasant type //TODO: error switching peasant??? unknown cause
		timeout = 0;
		isDown = true; //down
		isGettingUp = false; //not getting up
		isShook = false; //not shooketh till the Snobby glareth
		
		int random = (int)(Math.random()*ANIMATIONS.length);
		animation = ANIMATIONS[random];
		cutout.read(animation); //gets the animation
		rate = RATES[random]; //gets the corresponding animation rate
		rate = 2; //disregard different fps for animations rn
		frame = rate*cutout.getFrameCount() - 1; //ready for get up reverse animation
		System.out.println(rate*cutout.getFrameCount());
		
		//adjusts position
		//this.setX(tablePositionX + (tablePositionX - cutout.getFrameSize().width)/2); //TODO: fix this because it's wrong lmao
		if (random == 0) { //fly peasant TEMPORARY adjustment
			this.setX(tablePositionX - 25);
		}
		this.setY(tablePositionY - cutout.getFrameSize().height);
		
		hitBox = new Rectangle(getXPos(), getYPos(), cutout.getFrameSize().width, cutout.getFrameSize().height); //hitbox
	}
	
	public void shouldGetUp() { //determines if the peasant should get up now
		if (timeout > Math.random()*200+200) {
			timeout = 0;
			isDown = false;
			isGettingUp = true;
		}
	}
	
	public void becomeShooketh() { //got glared at so become shooketh
		if (!isGettingUp && !isShook && !isDown) { //only if up
			isShook = true;
		}
	}
	
	public Rectangle getHitBox() {
		return hitBox;
	}

}
