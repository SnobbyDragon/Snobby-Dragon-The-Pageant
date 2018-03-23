package minigames.insults.physics;

import java.util.ArrayList;

import minigames.Thing;

public abstract class PhysicsThing extends Thing {
	
	private int weight; //weight in kilograms
	private ArrayList<DirectionVector> forces;
	private ArrayList<Torque> torques;

	public PhysicsThing(int x, int y, int kg) {
		super(x, y);
		weight = kg;
		forces = new ArrayList<DirectionVector>();
		forces.add(new Gravity(weight)); //all things affected by gravity
		torques = new ArrayList<Torque>();
	}
	
	public DirectionVector calculateNetForce() { //finds the net force
		DirectionVector net = new DirectionVector();
		for (DirectionVector force : forces) {
			net.setXComponent(net.getXComponent() + force.getXComponent()); //sums x components
			net.setYComponent(net.getYComponent() + force.getYComponent()); //sums y components
		}
		return net;
	}
	
	public Torque calculateNetTorque() { //finds the net torque
		Torque net = new Torque();
		for (Torque torque : torques) {
			net.setMagnitude(net.getMagnitude() + torque.getMagnitude()); //sums the torques
		}
		return net;
	}

}
