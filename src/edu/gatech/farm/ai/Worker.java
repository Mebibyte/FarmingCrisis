package edu.gatech.farm.ai;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import edu.gatech.farm.tiles.CropTile;
import edu.gatech.farm.tiles.GrassTile;

public class Worker {
	public int destX, destY, homeX, homeY, x, y;
	private int process;
	private CropTile good;
	private static final ImageIcon SPRITE = 
			new ImageIcon(GrassTile.class.getResource("/edu/gatech/farm/res/character.png")),
			SPRITE_WALK = new ImageIcon(GrassTile.class.getResource("/edu/gatech/farm/res/characterWalk.png"));
	private ImageIcon sprite = SPRITE;
	private long lastTime;
	
	public Worker(CropTile good, int homeX, int homeY) {
		this.good = good;
		destX = good.x + 16;
		destY = good.y + 16;
		this.homeX = homeX;
		this.homeY = homeY;
		x = homeX;
		y = homeY;
		lastTime = System.currentTimeMillis();
	}
	
	public void draw(Graphics g) {
		sprite.paintIcon(null, g, x, y);
	}
	
	public void move() {
		if (System.currentTimeMillis() - lastTime > 300 && sprite.equals(SPRITE)) {
			sprite = SPRITE_WALK;
			lastTime = System.currentTimeMillis();
		} else if (System.currentTimeMillis() - lastTime > 300) {
			sprite = SPRITE;
			lastTime = System.currentTimeMillis();
		}
		int dirX = destX - x;
		int dirY = destY - y;
		double mag = Math.sqrt(dirX * dirX + dirY * dirY);
		x += (dirX / mag) * 1.5;
		y += (dirY / mag) * 1.5;
		if (process == 0 && x == destX && y == destY) {
			destX = homeX;
			destY = homeY;
			process = 1;
		} else if (process == 2 && x == destX && y == destY) {
			process = 3;
		}
	}
	
	public boolean isHeadingHome() {
		return process == 1;
	}
	
	public void advanceProcess() {
		process = 2;
	}
	
	public boolean isHome() {
		return process == 3;
	}
	
	public CropTile getCrop() {
		return good;
	}
}
