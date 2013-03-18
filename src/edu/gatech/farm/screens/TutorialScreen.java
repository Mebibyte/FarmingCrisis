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
public class TutorialScreen extends Screen {
    private static final int WIDTH = Farm.WIDTH, HEIGHT = Farm.HEIGHT;
    private final BigButton menu;
    private static final ImageIcon CREDITS_BG = 
			new ImageIcon(GrassTile.class.getResource("/edu/gatech/farm/res/credits_bg.png")),
			TUTORIAL = new ImageIcon(GrassTile.class.getResource("/edu/gatech/farm/res/tutorial.png"));

    /**
     * Constructor for TitleScreen.
     * 
     * @param panel GamePanel
     * @param WIDTH int
     * @param HEIGHT int
     */
    public TutorialScreen() {
    	menu = new BigButton("Menu", WIDTH / 2, (HEIGHT / 2) + 200);
    }

    /**
     * Method draw.
     * 
     * @param g Graphics
     */
    @Override
    public void draw(Graphics g) {
    	CREDITS_BG.paintIcon(null, g, 0, 0);
    	TUTORIAL.paintIcon(null, g, 360, 200);
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
