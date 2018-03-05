package general;

public class Player implements Comparable<Object> {
	
	private final int ID, hmphScore, insultsScore, glareScore, totalScore;
	private final String name;

	public Player(int IDNumber, int hmph, int insults, int glare, String n) {
		// TODO Auto-generated constructor stub
		ID = IDNumber;
		hmphScore = hmph;
		insultsScore = insults;
		glareScore = glare;
		totalScore = hmph + insults + glare;
		name = n;
	}

	@Override
	public int compareTo(Object o) {
		//first compare total scores
		if (this.getTotal() > ((Player) o).getTotal()) {
			return 1; //bigger score
		}
		if (this.getTotal() < ((Player) o).getTotal()) {
			return -1; //smaller score
		}
		//so if tie in total score... compare ID
		if (this.getID() < ((Player) o).getID()) {
			return 1; //smaller ID (so got score first)
		}
		if (this.getID() > ((Player) o).getID()) {
			return -1; //bigger ID (so got score second. IDs are all unique)
		}
		return 0; //same thing
	}
	
	public String toString() {
		return name + " " + hmphScore + " " + insultsScore + " " + glareScore + " " + totalScore + "\n";
	}
	
	public int getID() {
		return ID;
	}
	
	public int getHmph() {
		return hmphScore;
	}
	
	public int getInsults() {
		return insultsScore;
	}
	
	public int getGlare() {
		return glareScore;
	}

	public int getTotal() {
		return totalScore;
	}
	
	public String getName() {
		return name;
	}
}
