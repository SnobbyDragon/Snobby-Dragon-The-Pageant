package minigames.insults.physics;

public class Torque {
	
	private DirectionVector applied; //the applied force
	private DirectionVector r; //the position
	private double magnitude; //newton meters. positive is counterclockwise, negative is clockwise

	public Torque() {
		// TODO Auto-generated constructor stub
		applied = new DirectionVector();
		r = new DirectionVector();
		magnitude = 0;
	}
	
	public Torque(DirectionVector f) {
		applied = f;
	}
	
	public void calculateTorque() { //calculates magnitude and direction of rotation
		
	}

	public DirectionVector getApplied() {
		return applied;
	}

	public void setApplied(DirectionVector f) {
		applied = f;
	}

	public DirectionVector getR() {
		return r;
	}

	public void setR(DirectionVector position) {
		r = position;
	}

	public double getMagnitude() {
		return magnitude;
	}

	public void setMagnitude(double m) {
		magnitude = m;
	}
}
