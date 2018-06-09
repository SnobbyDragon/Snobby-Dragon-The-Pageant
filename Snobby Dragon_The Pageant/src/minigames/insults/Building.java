package minigames.insults;

import java.util.ArrayList;

public class Building extends PhysicsThing {
	
	private int stories;
	private ArrayList<Wall> walls = new ArrayList<Wall>();
	
	public Building(int x, int y, int s, String material) {
		super(x, y);
		stories = s;
		
	}

}
