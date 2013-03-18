package edu.gatech.farm.tiles;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class CityTile extends Tile {
	private static final ImageIcon SPRITE = 
			new ImageIcon(GrassTile.class.getResource("/edu/gatech/farm/res/sprites/city.png"));
	private int happiness = 60;

	public CityTile(int x, int y) {
		super(x, y);
		this.sprite = SPRITE;
	}
	
	public void draw(Graphics g) {
		sprite.paintIcon(null, g, x, y);
		if (happiness > 30) {
			g.setColor(Color.GREEN);
		} else if (happiness > 15) {
			g.setColor(Color.ORANGE);
		} else {
			g.setColor(Color.RED);
		}
		int happyBar = happiness / 2;
		for (int i = 0; i < happyBar; i++) {
			g.fillRect((x + 1) + i, y + 1, 1, 3);
		}
		g.setColor(Color.BLACK);
		for (int i = happyBar; i < 30; i++) {
			g.fillRect(x + 1 + i, y + 1, 1, 3);
		}
		g.drawLine(x, y + 4, x + 32, y + 4);
	}

	public boolean deHappy() {
		happiness--;
		return happiness <= 0;
	}
	
	public void beHappy() {
		happiness += 15;
		if (happiness > 60) happiness = 60;
	}
}