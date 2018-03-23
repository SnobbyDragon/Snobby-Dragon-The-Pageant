package minigames.insults.physics;

import java.lang.Math;

public class DirectionVector {
	
	private double magnitude; //in newtons
	private double direction; //in radians from [0, 2pi)
	private double xComponent, yComponent; //x and y component vectors
	
	public DirectionVector() {
		magnitude = 0;
		direction = 0;
		xComponent = 0;
		yComponent = 0;
	}

	public DirectionVector(double m, double d) {
		magnitude = m;
		direction = d;
		calculateComponentVectors();
	}

	public void calculateComponentVectors() { //calculates the component vectors
		xComponent = magnitude*Math.cos(direction);
		yComponent = magnitude*Math.sin(direction);
	}
	
	public void calculateVector() { //calculates the magnitude and direction of the vector
		magnitude = Math.sqrt(Math.pow(xComponent, 2) + Math.pow(yComponent, 2));
		direction = Math.atan(yComponent/xComponent);
	}
	
	public void setMagnitude(double m) {
		magnitude = m;
		calculateComponentVectors();
	}
	
	public double getMagnitude() {
		return magnitude;
	}
	
	public void setDirection(double d) {
		direction = d;
		calculateComponentVectors();
	}
	
	public double getDirection() {
		return direction;
	}

	public double getXComponent() {
		return xComponent;
	}

	public void setXComponent(double x) {
		xComponent = x;
		calculateVector();
	}

	public double getYComponent() {
		return yComponent;
	}

	public void setYComponent(double y) {
		yComponent = y;
		calculateVector();
	}
}
