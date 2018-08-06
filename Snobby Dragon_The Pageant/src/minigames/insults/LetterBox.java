package minigames.insults;

import java.awt.Graphics2D;

import com.madgag.gif.fmsware.GifDecoder;

import minigames.Thing;

public class LetterBox extends Thing {

	//TODO: create an animation where the user types the correct letter so the box gains a letter
	private static GifDecoder idle = new GifDecoder("Letter Box Idle.gif"), typed = new GifDecoder("Letter Box Typed.gif"), letters = new GifDecoder("Alphabet.gif");
	private int isIdle, isBounce, whichLetter; //counts the frames
	private boolean isTyped;
	private static final int IDLE_RATE = 2, IDLE_FRAME = idle.getFrameCount();

	public LetterBox(int x, int y, char c) {
		super(x, y);
		// TODO Auto-generated constructor stub
		isIdle = 0;
		isBounce = -1; //not bouncing
		whichLetter = c%95; //ascii for 'A' = 95
		isTyped = false;
		//System.out.println(idle.getFrameSize());
	}

	@Override
	public void draw(Graphics2D window) {
		// TODO Auto-generated method stub
		int n = isIdle/IDLE_RATE%IDLE_FRAME;
		if (isTyped) {
			window.drawImage(typed.getFrame(n), getXPos(), getYPos(), null); //if typed, green
		}
		else {
			window.drawImage(idle.getFrame(n), getXPos(), getYPos(), null); //otherwise normal
		}
		if (isIdle != -1) {
			isIdle++;
			if (isIdle == IDLE_RATE*IDLE_FRAME) {
				isIdle = 0;
			}
		}
		window.drawImage(letters.getFrame(whichLetter), getXPos(), getYPos(), null);
		window.drawString((char) whichLetter + "", getXPos(), getYPos()); //just to see the letters. temporary until custom alphabet is done
		switch (isBounce) {
		case 6:
			setY(getYPos() - 5);
			break;
		case 5:
			setY(getYPos() - 2);
			break;
		case 4:
			//do nothing
			break;
		case 3:
			setY(getYPos() + 2);
			break;
		case 2:
			setY(getYPos() + 5);
			break;
		case 1:
			setY(getYPos() + 2);
			break;
		case 0:
			setY(getYPos() - 2);
			break;
		}
		if (isBounce != -1) { //counts down frames
			isBounce--;
		}
	}

	public void bounce() { //boing when typed correctly :)
		isBounce = 6;
		isTyped = true;
	}

	public int getIdle() {
		return isIdle;
	}

	public void setIdle(int i) {
		isIdle = i;
	}

}
