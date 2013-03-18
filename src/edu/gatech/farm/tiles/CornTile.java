package edu.gatech.farm.tiles;

import javax.swing.ImageIcon;

public class CornTile extends CropTile {
	private static final ImageIcon BUY_SPRITE = 
			new ImageIcon(GrassTile.class.getResource("/edu/gatech/farm/res/sprites/corn.png")),
			BUYING_SPRITE = new ImageIcon(GrassTile.class.getResource("/edu/gatech/farm/res/sprites/corn_active.png")),
			SPRITE1 = new ImageIcon(GrassTile.class.getResource("/edu/gatech/farm/res/sprites/corn1.png")),
			SPRITE2 = new ImageIcon(GrassTile.class.getResource("/edu/gatech/farm/res/sprites/corn2.png")),
			SPRITE3 = new ImageIcon(GrassTile.class.getResource("/edu/gatech/farm/res/sprites/corn3.png")),
			SPRITE4 = new ImageIcon(GrassTile.class.getResource("/edu/gatech/farm/res/sprites/corn4.png")),
			SPRITE5 = new ImageIcon(GrassTile.class.getResource("/edu/gatech/farm/res/sprites/corn5.png"));
	private int growth = 1;

	public CornTile(int x, int y, boolean menu) {
		super(x, y);
		if (menu) {
			this.sprite = BUY_SPRITE;
			this.buyingSprite = BUYING_SPRITE;
		} else {
			this.sprite = SPRITE1;
		}
	}
	
	public void grow() {
		if (growth < 5) {
			growth++;
			switch (growth) {
			case 1:
				this.sprite = SPRITE1;
				break;
			case 2:
				this.sprite = SPRITE2;
				break;
			case 3:
				this.sprite = SPRITE3;
				break;
			case 4:
				this.sprite = SPRITE4;
				break;
			case 5:
				this.sprite = SPRITE5;
				break;
			default:
				this.sprite = BUY_SPRITE;
				break;
			}
		}
	}

	@Override
	public boolean isGrown() {
		return growth == 5;
	}
}