package edu.gatech.farm.tiles;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public abstract class Tile {
	public ImageIcon sprite, buyingSprite;
	public final int x, y;
	private final Rectangle bounds;
	
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
		bounds = new Rectangle(x, y, 32, 32);
	}
	
	public void draw(Graphics g) {
		sprite.paintIcon(null, g, x, y);
	}
	
	public boolean checkForClick(Point p) {
		return bounds.contains(p);
	}

	public void draw(Graphics g, boolean b) {
		buyingSprite.paintIcon(null, g, x, y);
	}
}
