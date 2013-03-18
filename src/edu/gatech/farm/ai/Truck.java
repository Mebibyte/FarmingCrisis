package edu.gatech.farm.ai;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import edu.gatech.farm.tiles.CityTile;
import edu.gatech.farm.tiles.FactoryTile;
import edu.gatech.farm.tiles.GrassTile;

public class Truck {
	public int destX, destY, homeX, homeY, x, y;
	private int process;
	private CityTile city;
	private FactoryTile factory;
	private static final ImageIcon TRUCK_RIGHT = 
			new ImageIcon(GrassTile.class.getResource("/edu/gatech/farm/res/truckRight.png")),
			TRUCK_LEFT = new ImageIcon(GrassTile.class.getResource("/edu/gatech/farm/res/truckLeft.png"));
	private ImageIcon sprite = TRUCK_RIGHT;
	
	public Truck(CityTile city, int homeX, int homeY) {
		this.city = city;
		destX = city.x + 16;
		destY = city.y + 16;
		this.homeX = homeX;
		this.homeY = homeY;
		x = homeX;
		y = homeY;
	}
	
	public Truck(FactoryTile factory, int homeX, int homeY) {
		this.factory = factory;
		destX = factory.x + 16;
		destY = factory.y + 16;
		this.homeX = homeX;
		this.homeY = homeY;
		x = homeX;
		y = homeY;
	}
	
	public void draw(Graphics g) {
		sprite.paintIcon(null, g, x, y);
	}
	
	public void move() {
		if (process == 0) {
			if (x < destX) {
				x += 2;
				if (!sprite.equals(TRUCK_RIGHT)) {
					sprite = TRUCK_RIGHT;
				}
			} else if (x > destX) {
				x -= 2;
				if (!sprite.equals(TRUCK_LEFT)) {
					sprite = TRUCK_LEFT;
				}
			} else if (y < destY) {
				y += 2;
			} else if (y > destY) {
				y -= 2;
			}
		} else if (process == 2) {
			if (y < destY) {
				y += 2;
			} else if (y > destY) {
				y -= 2;
			} else if (x < destX) {
				x += 2;
				if (!sprite.equals(TRUCK_RIGHT)) {
					sprite = TRUCK_RIGHT;
				}
			} else if (x > destX) {
				x -= 2;
				if (!sprite.equals(TRUCK_LEFT)) {
					sprite = TRUCK_LEFT;
				}
			}
		}
		
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
	
	public CityTile getCity() {
		return city;
	}
	
	public FactoryTile getFactory() {
		return factory;
	}
}
