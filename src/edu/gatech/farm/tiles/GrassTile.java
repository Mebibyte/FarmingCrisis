package edu.gatech.farm.tiles;

import javax.swing.ImageIcon;

public class GrassTile extends Tile {
	private static final ImageIcon SPRITE = 
			new ImageIcon(GrassTile.class.getResource("/edu/gatech/farm/res/sprites/grass.png"));

	public GrassTile(int x, int y) {
		super(x, y);
		this.sprite = SPRITE;
	}

}
