package edu.gatech.farm.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import edu.gatech.farm.screens.Screen;
import edu.gatech.farm.screens.TitleScreen;

/**
 * @author Glenn
 * @version 1.0
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	private Screen activeScreen = new TitleScreen();;
	private boolean mouseOnScreen;
	
	/**
	 * Constructor for GamePanel.
	 * Sets focusable and adds Mouse/Key listener.
	 * Creates the title screen to be displayed.
	 * 
	 * @param width int
	 * @param height int
	 */
	public GamePanel(){
		setFocusable(true);
		requestFocus();
		addMouseListener(new MouseListener());
		addKeyListener(new KeyListener());
		setBackground(Color.WHITE);
	}
	
	/**
	 * Method paintComponent.
	 * Clears the screen, then draws the current active screen.
	 * 
	 * @param g Graphics
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if (mouseOnScreen) {
		    final PointerInfo pInfo = MouseInfo.getPointerInfo();
		    activeScreen.setHoverPoint(new Point(
		            pInfo.getLocation().x - this.getLocationOnScreen().x,
	                pInfo.getLocation().y - this.getLocationOnScreen().y));
		}
		activeScreen.draw(g);
	}
	
	/**
	 * Method setActiveScreen.
	 * Changes the current active screen;
	 * 
	 * @param activeScreen Screen
	 */
	public void setActiveScreen(Screen activeScreen){
		this.activeScreen = activeScreen;
	}
	
	/**
	 * Method isMouseOnScreen.
	 * 
	 * @return boolean
	 */
	public boolean isMouseOnScreen() {
		return mouseOnScreen;
	}

	/**
	 * Method setMouseOnScreen.
	 * 
	 * @param mouseOnScreen boolean
	 */
	public void setMouseOnScreen(boolean mouseOnScreen) {
		this.mouseOnScreen = mouseOnScreen;
	}
	
	/**
	 * @author Glenn
	 */
	private class MouseListener extends MouseAdapter {
	    /**
	     * Method mousePressed.
	     * 
	     * @param event MouseEvent
	     * @see java.awt.event.MouseListener#mousePressed(MouseEvent)
	     */
	    public void mousePressed(MouseEvent event){
            activeScreen.checkForClick(event.getPoint());
        }
        
        /**
         * Method mouseEntered.
         * 
         * @param event MouseEvent
         * @see java.awt.event.MouseListener#mouseEntered(MouseEvent)
         */
        public void mouseEntered(MouseEvent event){
            setMouseOnScreen(true);
        }
        
        /**
         * Method mouseExited.
         * @param e MouseEvent
         * @see java.awt.event.MouseListener#mouseExited(MouseEvent)
         */
        public void mouseExited(MouseEvent e){
            setMouseOnScreen(false);
        }
	}
	
	/**
	 * @author Glenn
	 */
	private class KeyListener extends KeyAdapter {
	    /**
	     * Method keyTyped.
	     * 
	     * @param e KeyEvent
	     * @see java.awt.event.KeyListener#keyTyped(KeyEvent)
	     */
	    public void keyTyped(KeyEvent e) {
            activeScreen.keyTyped(e);
	    }
	}
	
	/**
     * Method tick.
     */
    public void tick() {
        activeScreen.tick();
    }
}
