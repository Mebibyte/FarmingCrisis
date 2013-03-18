package edu.gatech.farm.tiles;

import javax.swing.ImageIcon;

public class RoadTile extends Tile {
	private static final ImageIcon HORIZONTAL = 
			new ImageIcon(GrassTile.class.getResource("/edu/gatech/farm/res/sprites/roadH.png")),
			VERTICAL = new ImageIcon(GrassTile.class.getResource("/edu/gatech/farm/res/sprites/roadV.png")),
			LEFT_UP = new ImageIcon(GrassTile.class.getResource("/edu/gatech/farm/res/sprites/roadLU.png")),
			LEFT_DOWN = new ImageIcon(GrassTile.class.getResource("/edu/gatech/farm/res/sprites/roadLD.png")),
			RIGHT_UP = new ImageIcon(GrassTile.class.getResource("/edu/gatech/farm/res/sprites/roadRU.png")),
			RIGHT_DOWN = new ImageIcon(GrassTile.class.getResource("/edu/gatech/farm/res/sprites/roadRD.png")),
			LEFT_RIGHT_UP = new ImageIcon(GrassTile.class.getResource("/edu/gatech/farm/res/sprites/roadLRU.png")),
			LEFT_RIGHT_DOWN = new ImageIcon(GrassTile.class.getResource("/edu/gatech/farm/res/sprites/roadLRD.png")),
			RIGHT_UP_DOWN = new ImageIcon(GrassTile.class.getResource("/edu/gatech/farm/res/sprites/roadRUD.png")),
			LEFT_UP_DOWN = new ImageIcon(GrassTile.class.getResource("/edu/gatech/farm/res/sprites/roadLUD.png")),
			FOUR_WAY = new ImageIcon(GrassTile.class.getResource("/edu/gatech/farm/res/sprites/roadLRUD.png"));
	private int type;

	public RoadTile(int x, int y, int type) {
		super(x, y);
		this.type = type;
		if (type == 0) {
			this.sprite = HORIZONTAL;
		} else if (type == 1) {
			this.sprite = VERTICAL;
		} else if (type == 2) {
			this.sprite = LEFT_UP;
		} else if (type == 3) {
			this.sprite = LEFT_DOWN;
		} else if (type == 4) {
			this.sprite = RIGHT_UP;
		} else if (type == 5) {
			this.sprite = RIGHT_DOWN;
		} else if (type == 6) {
			this.sprite = LEFT_RIGHT_UP;
		} else if (type == 7) {
			this.sprite = LEFT_RIGHT_DOWN;
		} else if (type == 8) {
			this.sprite = RIGHT_UP_DOWN;
		} else if (type == 9) {
			this.sprite = LEFT_UP_DOWN;
		} else if (type == 10) {
			this.sprite = FOUR_WAY;
		}
	}
	
	public int getType() {
		return type;
	}
}