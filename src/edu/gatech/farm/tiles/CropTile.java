package edu.gatech.farm.tiles;

public abstract class CropTile extends Tile {
	private boolean hasWorker;

	public CropTile(int x, int y) {
		super(x, y);
	}
	
	public abstract void grow();
	
	public abstract boolean isGrown();
	
	public boolean hasWorker() {
		return hasWorker;
	}
	
	public void addWorker() {
		hasWorker = true;
	}
}