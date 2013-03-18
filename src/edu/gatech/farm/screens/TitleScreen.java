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
public class TitleScreen extends Screen {
    private final BigButton newGame, tutorial, credits, quit;
    private static final int WIDTH = Farm.WIDTH, HEIGHT = Farm.HEIGHT;
    private static final ImageIcon BG = 
			new ImageIcon(GrassTile.class.getResource("/edu/gatech/farm/res/bg.png"));

    /**
     * Constructor for TitleScreen.
     * 
     * @param panel GamePanel
     * @param WIDTH int
     * @param HEIGHT int
     */
    public TitleScreen() {
        newGame = new BigButton("New Game", WIDTH / 2, HEIGHT / 2);
        tutorial = new BigButton("Tutorial", WIDTH / 2, (HEIGHT / 2) + newGame.getHeight(), false);
        credits = new BigButton("Credits", WIDTH / 2, (HEIGHT / 2)
                + newGame.getHeight() + tutorial.getHeight(), false);
        quit = new BigButton("Exit Game", WIDTH / 2, (HEIGHT / 2)
                + newGame.getHeight() + tutorial.getHeight() + credits.getHeight());
    }

    /**
     * Method draw.
     * 
     * @param g Graphics
     */
    @Override
    public void draw(Graphics g) {
        BG.paintIcon(null, g, 0, 0);
        newGame.draw(g, Farm.PANEL, WIDTH, HEIGHT);
        tutorial.draw(g, Farm.PANEL, WIDTH, HEIGHT);
        credits.draw(g, Farm.PANEL, WIDTH, HEIGHT);
        quit.draw(g, Farm.PANEL, WIDTH, HEIGHT);
    }

    /**
     * Method checkForClick.
     * 
     * @param point Point
     */
    @Override
    public void checkForClick(Point point) {
        if (newGame.isClicked(point)) {
        	Farm.PANEL.setActiveScreen(new GameScreen());
        } else if (tutorial.isClicked(point)) {
        	Farm.PANEL.setActiveScreen(new TutorialScreen());
        } else if (credits.isClicked(point)) {
        	Farm.PANEL.setActiveScreen(new CreditsScreen());
        } else if (quit.isClicked(point)) {
            System.exit(0);
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
        newGame.setHovered(p);
        tutorial.setHovered(p);
        credits.setHovered(p);
        quit.setHovered(p);
    }
}
