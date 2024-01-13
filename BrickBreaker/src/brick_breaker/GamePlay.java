package brick_breaker;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GamePlay extends JPanel implements KeyListener, ActionListener {

	private boolean play = false;
	private int level = 1;
	private int score = 0;
	private int totalBricks = 20;
	private int delay = 5;
	private int playerX = 450;

	private int ballposX = 985;
	private int ballposY = 500;
	private int ballXdir = -1;
	private int ballYdir = -4;

	private Timer timer;
	private MapGenerator map;

	private long startTime = -1;
	private long duration = 180000; // 180 seconds
	private long clockTime;
	private String remainingTime = null;

	public GamePlay() {
		map = new MapGenerator(4, 5);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
		callRemainingTime();
	}

	public void callRemainingTime() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				timer = new Timer(5, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (startTime < 0) {
							startTime = System.currentTimeMillis();
						}

						long now = System.currentTimeMillis();
						clockTime = now - startTime;

						if (clockTime >= duration) {
							clockTime = duration;
							timer.stop();
						}
					}
				});
				timer.setInitialDelay(5);
			}
		});
	}

	public void paint(Graphics g) {

		// background
		g.setColor(Color.BLACK);
		g.fillRect(1, 1, 1006, 750);

		// drawing map
		map.draw((Graphics2D) g);

		// borders
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 3, 750);
		g.fillRect(0, 0, 1006, 3);
		g.fillRect(1005, 0, 3, 750);
		
		// level
		g.setColor(Color.YELLOW);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString("Level: " + level, 120, 30);
		
		// score
		g.setColor(Color.YELLOW);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString("Score: " + score, 460, 30);

		// remaining time
		SimpleDateFormat df = new SimpleDateFormat("mm:ss");
		remainingTime = df.format(duration - clockTime);
		g.drawString(" Time: " + remainingTime, 780, 30);

		// the paddle
		g.setColor(Color.BLUE);
		g.fillRect(playerX, 700, 120, 10);

		// the ball
		g.setColor(Color.YELLOW);
		g.fillOval(ballposX, ballposY, 20, 20);

		if (totalBricks <= 0) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			timer.stop();

			g.setColor(Color.GREEN);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("You Won, Score: " + score + ", Time: " + remainingTime, 275, 350);

			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("(Press Enter to Restart)", 400, 390);
		}

		if (ballposY > 700) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			timer.stop();

			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Game Over, Score: " + score + ", Time: " + remainingTime, 275, 350);

			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("(Press Enter to Restart)", 400, 390);
		}

		if (clockTime >= duration) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;

			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Game Over, Score: " + score + ", Time: " + remainingTime, 275, 350);

			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("(Press Enter to Restart)", 400, 390);
		}

		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();

		if (play) {
			if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 700, 120, 10))) {
				ballYdir = -ballYdir;
			}

			A: for (int i = 0; i < map.map.length; i++) {
				for (int j = 0; j < map.map[0].length; j++) {
					if (map.map[i][j] > 0) {
						int brickX = j * map.brickWidth + 80;
						int brickY = i * map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;

						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
						Rectangle brickRect = rect;

						if (ballRect.intersects(brickRect)) {
							map.setBrickValue(0, i, j);
							totalBricks--;
							score += 5;

							if (ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width) {
								ballXdir = -ballXdir;
							} else {
								ballYdir = -ballYdir;
							}

							break A;
						}
					}
				}
			}

			ballposX += ballXdir;
			ballposY += ballYdir;

			if (ballposX < 0) {
				ballXdir = -ballXdir;
			}
			if (ballposY < 0) {
				ballYdir = -ballYdir;
			}
			if (ballposX > 985) {
				ballXdir = -ballXdir;
			}
		}

		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (playerX >= 883) {
				playerX = 883;
			} else {
				moveRight();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (playerX <= 10) {
				playerX = 5;
			} else {
				moveLeft();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!play) {
				play = true;
				ballposX = 985;
				ballposY = 500;
				ballXdir = -1;
				ballYdir = -4;
				playerX = 450;
				score = 0;
				totalBricks = 20;
				map = new MapGenerator(4, 5);
				if (!timer.isRunning()) {
					startTime = -1;
					timer.start();
				}
				repaint();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	private void moveRight() {
		play = true;
		playerX += 20;
	}

	private void moveLeft() {
		play = true;
		playerX -= 20;
	}

}
