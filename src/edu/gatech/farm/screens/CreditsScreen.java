package edu.gatech.farm.screens;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.ImageIcon;

import edu.gatech.farm.gui.BigButton;
import edu.gatech.farm.gui.CenterText;
import edu.gatech.farm.main.Farm;
import edu.gatech.farm.tiles.GrassTile;

/**
 * @author Glenn
 * @version $Revision: 1.0 $
 */
public class CreditsScreen extends Screen {
    private static final int WIDTH = Farm.WIDTH, HEIGHT = Farm.HEIGHT;
    private final BigButton menu;
    private static final ImageIcon CREDITS_BG = 
			new ImageIcon(GrassTile.class.getResource("/edu/gatech/farm/res/credits_bg.png"));

    /**
     * Constructor for TitleScreen.
     * 
     * @param panel GamePanel
     * @param WIDTH int
     * @param HEIGHT int
     */
    public CreditsScreen() {
    	menu = new BigButton("Menu", WIDTH / 2, (HEIGHT / 2) + (57 * 2));
    }

    /**
     * Method draw.
     * 
     * @param g Graphics
     */
    @Override
    public void draw(Graphics g) {
    	CREDITS_BG.paintIcon(null, g, 0, 0);
    	CenterText.string(g, "Created by Glenn Hollingsworth", 500, 215);
    	CenterText.string(g, "Created for LCC 3403", 500, 230);
    	CenterText.string(g, "Georgia Institute of Technology", 500, 245);
    	CenterText.string(g, "Based on New York Times article:", 500, 260);
    	CenterText.string(g, "Rush to Use Crops as Fuel Raises Food Prices and Hunger Fears", 500, 275);
    	CenterText.string(g, "Menu Background Pictures from:", 500, 305);
    	CenterText.string(g, "http://www.behrauctioncompany.com/farmland_auctions", 500, 320);
    	CenterText.string(g, "http://en.wikipedia.org/wiki/File:Downtown_Atlanta_skyline_panorama.jpg", 500, 335);
    	CenterText.string(g, "http://murrayashmole.files.wordpress.com/2012/01/nuclear-explosion.jpg", 500, 350);
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
