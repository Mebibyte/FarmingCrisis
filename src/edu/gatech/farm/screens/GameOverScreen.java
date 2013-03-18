package edu.gatech.farm.screens;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.ImageIcon;

import edu.gatech.farm.gui.BigButton;
import edu.gatech.farm.main.Farm;
import edu.gatech.farm.tiles.GrassTile;

/**
 * @author Glenn
 * @version $Revision: 1.0 $
 */
public class GameOverScreen extends Screen {
    private static final int WIDTH = Farm.WIDTH, HEIGHT = Farm.HEIGHT;
    private final BigButton menu;
    private static final ImageIcon END_BG = 
			new ImageIcon(GrassTile.class.getResource("/edu/gatech/farm/res/end_bg.png"));

    /**
     * Constructor for TitleScreen.
     * 
     * @param panel GamePanel
     * @param WIDTH int
     * @param HEIGHT int
     */
    public GameOverScreen() {
    	menu = new BigButton("Menu", WIDTH / 2, (HEIGHT / 2) + 200);
    }

    /**
     * Method draw.
     * 
     * @param g Graphics
     */
    @Override
    public void draw(Graphics g) {
    	END_BG.paintIcon(null, g, 0, 0);
    	menu.draw(g, Farm.PANEL, WIDTH, HEIGHT);
    }

    /**
     * Method checkForClick.
     * 
     * @param point Point
     */
    @Override
    public void checkForClick(Point p) {
    	if (menu.isClicked(p)) {
    		Farm.PANEL.setActiveScreen(new TitleScreen());
    	}
    }
    
    /**
     * Method setHoverPoint.
     * 
     * @param p Point
     */
    @Override
    public void setHoverPoint(Point p) {
        super.setHoverPoint(p);
        menu.setHovered(p);
    }
}
