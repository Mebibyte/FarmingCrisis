package edu.gatech.farm.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.ImageIcon;

import edu.gatech.farm.gui.BigButton;
import edu.gatech.farm.main.Farm;

/**
 * @author Glenn
 * @version $Revision: 1.0 $
 */
public class Screen {
	private static final int WIDTH = Farm.WIDTH, HEIGHT = Farm.HEIGHT;
	protected Point hoverPoint;
	public boolean paused;
	private final Rectangle pauseButton = new Rectangle(WIDTH
			- PAUSE.getIconWidth(), 0, PAUSE.getIconWidth(),
			PAUSE.getIconHeight());
	private final BigButton quitGame = new BigButton("Quit Game", (WIDTH / 2),
			(HEIGHT / 2));
	private static final ImageIcon PAUSE = new ImageIcon(
			GameScreen.class.getResource("/edu/gatech/farm/res/pause.png"));

	/**
	 * Method draw.
	 * 
	 * @param g Graphics
	 */
	public void draw(Graphics g) {
		if (paused) {
			g.setColor(new Color(0, 0, 0, 150));
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.WHITE);
			final Font defaultFont = g.getFont(); 
			g.setFont(new Font("serif", Font.PLAIN, 25));
			g.drawString("Paused", (WIDTH / 2)
					- (g.getFontMetrics().stringWidth("Paused") / 2),
					(HEIGHT / 2) - quitGame.getHeight());
			quitGame.draw(g, Farm.PANEL, WIDTH, HEIGHT);
			g.setFont(defaultFont);
		}
		PAUSE.paintIcon(Farm.PANEL, g, WIDTH - PAUSE.getIconWidth(), 0);
	}

	/**
	 * Method checkForClick.
	 * 
	 * @param p Point
	 * @throws IOException
	 */
	public void checkForClick(Point p) {
		if (paused) {
			if (quitGame.isClicked(p)) {
				Farm.PANEL.setActiveScreen(new TitleScreen());
			}
		}
		if (pauseButton.contains(p)) {
			paused = !paused;
		}
	}

	/**
	 * Method keyTyped.
	 * 
	 * @param e KeyEvent
	 */
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
			paused = !paused;
		}
	}

	/**
	 * Method getHoverPoint.
	 * 
	 * @return Point
	 */
	public Point getHoverPoint() {
		return hoverPoint;
	}

	/**
	 * Method setHoverPoint.
	 * 
	 * @param hp Point
	 */
	public void setHoverPoint(Point hp) {
		this.hoverPoint = hp;
		quitGame.setHovered(hp);
	}

	/**
	 * Method tick. Updates objects on the screen.
	 */
	public void tick() {

	}

	/**
	 * Checks if paused.
	 * 
	 * @return paused
	 */
	public boolean isPaused() {
		return paused;
	}
}