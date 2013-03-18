package edu.gatech.farm.main;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * @author Glenn
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Farm extends JFrame {
	public static final GamePanel PANEL = new GamePanel();
	public static final int WIDTH = 1000, HEIGHT = (WIDTH * 3) / 4;
	private static final double TIME_BETWEEN_UPDATES = 1000000000 / 30.0;
	private static final int MAX_UPDATES_BEFORE_RENDER = 5;
	private static final int ONEBILLION = 1000000000;
	private static final double TARGET_FPS = 60;
	private static final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;
	private boolean gameRunning = true;
	private int fps, frameCount = 0;

	/**
	 * Constructor for Farm. Creates a new GamePanel and adds it to the
	 * JFrame. Also starts the game loop.
	 */
	public Farm() {
		super("Farming Crisis");
		setResizable(false);
		setPreferredSize(new Dimension(WIDTH + 6, HEIGHT + 28));
		Dimension size = new Dimension(WIDTH, HEIGHT);
		PANEL.setPreferredSize(size);
		add(PANEL);
		
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

		runGameLoop();
	}

	/**
	 * Method runGameLoop. Creates a new thread to run the game loop.
	 */
	public void runGameLoop() {
		final Thread loop = new Thread() {
			public void run() {
				gameLoop();
			}
		};
		loop.start();
	}

	/**
	 * Method gameLoop. Draws to the panel and updates the game. Attempts to
	 * maintain 60 FPS.
	 */
	private void gameLoop() {
		double lastUpdateTime = System.nanoTime();
		double lastRenderTime = System.nanoTime();

		int lastSecondTime = (int) (lastUpdateTime / ONEBILLION);

		while (gameRunning) {
			double now = System.nanoTime();
			int updateCount = 0;

			while (now - lastUpdateTime > TIME_BETWEEN_UPDATES
					&& updateCount < MAX_UPDATES_BEFORE_RENDER) {
				tick();
				lastUpdateTime += TIME_BETWEEN_UPDATES;
				updateCount++;
			}

			if (now - lastUpdateTime > TIME_BETWEEN_UPDATES) {
				lastUpdateTime = now - TIME_BETWEEN_UPDATES;
			}

			draw();
			lastRenderTime = now;

			int thisSecond = (int) (lastUpdateTime / ONEBILLION);
			if (thisSecond > lastSecondTime) {
				fps = frameCount;
				frameCount = 0;
				lastSecondTime = thisSecond;
			}

			while (now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS
					&& now - lastUpdateTime < TIME_BETWEEN_UPDATES) {
				Thread.yield();
				now = System.nanoTime();
			}

			this.setTitle("Farming Crisis" + " || " + fps + " fps");
		}
	}

	/**
	 * Method drawGame. Calls the repaint method of the GamePanel.
	 */
	private void draw() {
		PANEL.repaint();
		frameCount++;
	}

	/**
	 * Method tick. Causes the panel to update.
	 */
	private void tick() {
		PANEL.tick();
	}

	/**
	 * Method main. When run, creates a new instance of SpaceTrader.
	 * 
	 * @param args String[]
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Farm();
			}
		});
	}

	/**
	 * Method quitGame. Ends the game loop.
	 */
	public void quitGame() {
		gameRunning = false;
	}
}
