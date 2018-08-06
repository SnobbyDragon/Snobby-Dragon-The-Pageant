package minigames;

import java.awt.Graphics2D;

import com.madgag.gif.fmsware.GifDecoder;

import general.SnobbyRunner;

public class GameTimer extends Thing {
	
	private static GifDecoder normal = new GifDecoder(), urgent = new GifDecoder(); //TODO: make clock/hourglass animations
	private long timeMs; //time the game has been active in milliseconds

	public GameTimer() {
		super(SnobbyRunner.WIDTH - 50, 50);
		timeMs = 0;
	}

	@Override
	public void draw(Graphics2D window) {
		// TODO Auto-generated method stub

	}

	public long getTimeMs() {
		return timeMs;
	}

	public void setTimeMs(long t) {
		timeMs = t;
	}

}
