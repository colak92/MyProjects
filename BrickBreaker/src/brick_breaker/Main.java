package brick_breaker;
import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		GamePlay gamePlay = new GamePlay();
		frame.setBounds(10, 10, 1024, 768);
		frame.setTitle("Brick Breaker");
		frame.setResizable(false);
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(gamePlay);
	}

}
