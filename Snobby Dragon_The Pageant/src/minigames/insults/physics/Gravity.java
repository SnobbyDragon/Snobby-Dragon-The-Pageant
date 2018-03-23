package minigames.insults.physics;

import java.lang.Math;

public class Gravity extends DirectionVector {
	
	private static final double DOWN = Math.PI*3/2;

	public Gravity(int kg) {
		setMagnitude(9.8*kg); //9.8 N/kg
		setDirection(DOWN); //always down
	}

}
