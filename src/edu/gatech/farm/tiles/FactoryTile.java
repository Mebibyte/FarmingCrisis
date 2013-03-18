package edu.gatech.farm.tiles;

import javax.swing.ImageIcon;

public class FactoryTile extends Tile {
	private static final ImageIcon SPRITE = 
			new ImageIcon(GrassTile.class.getResource("/edu/gatech/farm/res/sprites/factory.png"));

	public FactoryTile(int x, int y) {
		super(x, y);
		this.sprite = SPRITE;
	}
}