import javax.swing.JFrame;

public class SnobbyRunner extends JFrame{
	private static final int WIDTH = 1000;
	private static final int HEIGHT = 600;
 
 public SnobbyRunner() {
	 super("Snobby Dragon: The Pageant");
	 setSize(WIDTH, HEIGHT);
	 getContentPane().add(new SnobbyPanel());
	 setVisible(true);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     setLocationRelativeTo(null);
 }
 
 public static void main(String[] args) {
	 SnobbyRunner game = new SnobbyRunner();
 }
}
