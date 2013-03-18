package edu.gatech.farm.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

import edu.gatech.farm.tiles.GrassTile;

public class PopUpMessage {
	private String message;
	private int x, y, type;
	private long timeCreated, pausedDiff;
	private static final ImageIcon OIL = 
			new ImageIcon(GrassTile.class.getResource("/edu/gatech/farm/res/oil.png")),
			MONEY = new ImageIcon(GrassTile.class.getResource("/edu/gatech/farm/res/coin.png"));
	
	public PopUpMessage(int x, int y, int type, String message) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.message = message;
		timeCreated = System.currentTimeMillis();
	}
	
	public void draw(Graphics g) {
		if (type == 0) {
			OIL.paintIcon(null, g, x, y);
			g.setColor(Color.BLACK);
		} else {
			MONEY.paintIcon(null, g, x, y);
			g.setColor(Color.YELLOW);
		}
		g.drawString(message, x + 9, y + 10);
		g.setColor(Color.BLACK);
	}
	
	public void tick() {
		y--;
	}
	
	public boolean shown() {
		if (System.currentTimeMillis() - timeCreated > 1000) {
			return true;
		}
		return false;
	}

	public void pause() {
		pausedDiff = System.currentTimeMillis() - timeCreated;
	}
	
	public void unpause() {
		timeCreated = System.currentTimeMillis() - pausedDiff;
	}
}
