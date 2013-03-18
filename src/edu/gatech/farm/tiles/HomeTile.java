package edu.gatech.farm.tiles;

import javax.swing.ImageIcon;

public class HomeTile extends Tile {
	private static final ImageIcon SPRITE = 
			new ImageIcon(GrassTile.class.getResource("/edu/gatech/farm/res/sprites/home.png"));

	public HomeTile(int x, int y) {
		super(x, y);
		this.sprite = SPRITE;
	}
}