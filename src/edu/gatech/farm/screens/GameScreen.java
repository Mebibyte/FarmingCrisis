package edu.gatech.farm.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

import edu.gatech.farm.ai.Truck;
import edu.gatech.farm.ai.Worker;
import edu.gatech.farm.gui.PopUpMessage;
import edu.gatech.farm.main.Farm;
import edu.gatech.farm.tiles.CityTile;
import edu.gatech.farm.tiles.CornTile;
import edu.gatech.farm.tiles.CropTile;
import edu.gatech.farm.tiles.FactoryTile;
import edu.gatech.farm.tiles.GrassTile;
import edu.gatech.farm.tiles.HomeTile;
import edu.gatech.farm.tiles.RoadTile;
import edu.gatech.farm.tiles.Tile;

/**
 * @version 1.0
 * @since 1.0
 * @author Glenn
 */
public class GameScreen extends Screen {
    private static final int WIDTH = Farm.WIDTH, HEIGHT = Farm.HEIGHT;
    private static Tile[][] map = new Tile[25][20];
    private static final Rectangle MAP_BOUNDS = new Rectangle(200, 0, WIDTH - 200, HEIGHT - 110),
    		MENU_BOUNDS = new Rectangle(200, 640, WIDTH - 200, HEIGHT - 640);
    private int money = 1000, fuel = 500, cornCost = 100;
    private static final Tile buyCorn = new CornTile(200, 679, true);
    private boolean buyingCorn = false;
    private String[] months = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", 
    			"September", "October", "November", "December"};
    private int[] days = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private int day = 1, month, year = 2012;
    private long lastSecond, pausedDiff;
    private ArrayList<CropTile> crops = new ArrayList<CropTile>();
    private int[] goods = new int[1];
    private int speed = 1;
    private int homeX, homeY;
    private ArrayList<Worker> workers = new ArrayList<Worker>();
    private ArrayList<Truck> trucks = new ArrayList<Truck>();
    private ArrayList<CityTile> cities = new ArrayList<CityTile>();
	private ArrayList<PopUpMessage> messages = new ArrayList<PopUpMessage>();
    private int cropsGrown, fuelProduced, foodProduced;

    /**
     * Constructor for GameScreen.
     */
    public GameScreen() {
    	//---------------------
        // Grass
        //---------------------
    	for (int x = 0; x < map.length; x++) {
    		for (int y = 0; y < map[0].length; y++) {
    			map[x][y] = new GrassTile(200 + (x * 32), y * 32);
    		}
    	}
    	
    	//---------------------
        // Home
        //---------------------
    	Random rand = new Random();
    	homeX = rand.nextInt(25);
    	homeY = rand.nextInt(20);
    	map[homeX][homeY] = new HomeTile(200 + (32 * homeX), homeY * 32);
    	
    	//---------------------
        // Cities
        //---------------------
    	int cityX1 = rand.nextInt(25), cityY1 = rand.nextInt(20);
    	while (homeX == cityX1 && homeY == cityY1) {
    		cityX1 = rand.nextInt(25);
    		cityY1 = rand.nextInt(20);
    	}
    	map[cityX1][cityY1] = new CityTile(200 + (32 * cityX1), cityY1 * 32);
    	cities.add((CityTile) map[cityX1][cityY1]);
    	
    	int cityX2 = rand.nextInt(25), cityY2 = rand.nextInt(20);
    	while ((homeX == cityX2 && homeY == cityY2) ||  (cityX1 == cityX2 && cityY1 == cityY2)) {
    		cityX2 = rand.nextInt(25);
    		cityY2 = rand.nextInt(20);
    	}
    	map[cityX2][cityY2] = new CityTile(200 + (32 * cityX2), cityY2 * 32);
    	cities.add((CityTile) map[cityX2][cityY2]);
    	
    	//---------------------
        // Factory
        //---------------------
    	int factoryX = rand.nextInt(25), factoryY = rand.nextInt(20);
    	while ((homeX == factoryX && homeY == factoryY) ||  (cityX1 == factoryX && cityY1 == factoryY)
    			|| (cityX2 == factoryX && cityY2 == factoryY)) {
    		factoryX = rand.nextInt(25);
    		factoryY = rand.nextInt(20);
    	}
    	map[factoryX][factoryY] = new FactoryTile(200 + (32 * factoryX), factoryY * 32);
    	
    	//---------------------
        // Roads
        //---------------------
    	genRoads(homeX, homeY, cityX1, cityY1);
    	genRoads(homeX, homeY, cityX2, cityY2);
    	genRoads(homeX, homeY, factoryX, factoryY);
    	
    	lastSecond = System.currentTimeMillis();
    }
    
    private void genRoads(int homeX, int homeY, int destX, int destY) {
    	int[] turn = new int[2];
    	int turnX, turnY;
    	if (homeX < destX) {
    		homeX++;
    		while (homeX < destX) {
        		if (map[homeX][homeY] instanceof GrassTile) {
        			map[homeX][homeY] = new RoadTile(200 + (32 * homeX), homeY * 32, 0);
        		} else if (map[homeX][homeY] instanceof RoadTile) {
        			if (((RoadTile) map[homeX][homeY]).getType() == 2) {
        				map[homeX][homeY] = new RoadTile(200 + (32 * homeX), homeY * 32, 6);
        			} else if (((RoadTile) map[homeX][homeY]).getType() == 3) {
        				map[homeX][homeY] = new RoadTile(200 + (32 * homeX), homeY * 32, 7);
        			} else if (((RoadTile) map[homeX][homeY]).getType() == 9) {
        				map[homeX][homeY] = new RoadTile(200 + (32 * homeX), homeY * 32, 10);
        			}
        		}
        		homeX++;
        	}
    		turn[0] = 1;
    	} else  if (homeX > destX) {
    		homeX--;
    		while (homeX > destX) {
        		if (map[homeX][homeY] instanceof GrassTile) {
        			map[homeX][homeY] = new RoadTile(200 + (32 * homeX), homeY * 32, 0);
        		} else if (map[homeX][homeY] instanceof RoadTile) {
        			if (((RoadTile) map[homeX][homeY]).getType() == 4) {
        				map[homeX][homeY] = new RoadTile(200 + (32 * homeX), homeY * 32, 6);
        			} else if (((RoadTile) map[homeX][homeY]).getType() == 5) {
        				map[homeX][homeY] = new RoadTile(200 + (32 * homeX), homeY * 32, 7);
        			} else if (((RoadTile) map[homeX][homeY]).getType() == 8) {
        				map[homeX][homeY] = new RoadTile(200 + (32 * homeX), homeY * 32, 10);
        			}
        		}
        		homeX--;
        	}
    		turn[0] = 2;
    	}
    	turnY = homeY;
    	homeX = destX;
    	turnX = homeX;
    	if (homeY < destY) {
    		homeY++;
        	while (homeY < destY) {
        		if (map[homeX][homeY] instanceof GrassTile) {
        			map[homeX][homeY] = new RoadTile(200 + (32 * homeX), homeY * 32, 1);
        		}
        		homeY++;
        	}
        	turn[1] = 1;
    	} else if (homeY > destY) {
    		homeY--;
        	while (homeY > destY) {
        		if (map[homeX][homeY] instanceof GrassTile) {
        			map[homeX][homeY] = new RoadTile(200 + (32 * homeX), homeY * 32, 1);
        		}
        		homeY--;
        	}
        	turn[1] = 2;
    	}
    	
    	if (turn[0] == 1 && turn[1] == 2) {
    		if (map[turnX][turnY] instanceof RoadTile) {
    			if (((RoadTile) map[turnX][turnY]).getType() == 3) {
    				map[turnX][turnY] = new RoadTile(200 + (32 * turnX), turnY * 32, 9);
    			} else if (((RoadTile) map[turnX][turnY]).getType() == 2) {
        			map[turnX][turnY] = new RoadTile(200 + (32 * turnX), turnY * 32, 2);
    			} else if (((RoadTile) map[turnX][turnY]).getType() == 0) {
        			map[turnX][turnY] = new RoadTile(200 + (32 * turnX), turnY * 32, 6);
    			} else if (((RoadTile) map[turnX][turnY]).getType() == 7) {
        			map[turnX][turnY] = new RoadTile(200 + (32 * turnX), turnY * 32, 10);
    			}
    		} else if (map[turnX][turnY] instanceof GrassTile) {
    			map[turnX][turnY] = new RoadTile(200 + (32 * turnX), turnY * 32, 2);
    		}
    	} else if (turn[0] == 1 && turn[1] == 1) {
    		if (map[turnX][turnY] instanceof RoadTile) {
    			if (((RoadTile) map[turnX][turnY]).getType() == 2) {
    				map[turnX][turnY] = new RoadTile(200 + (32 * turnX), turnY * 32, 9);
    			} else if (((RoadTile) map[turnX][turnY]).getType() == 3) {
        			map[turnX][turnY] = new RoadTile(200 + (32 * turnX), turnY * 32, 3);
    			} else if (((RoadTile) map[turnX][turnY]).getType() == 0) {
        			map[turnX][turnY] = new RoadTile(200 + (32 * turnX), turnY * 32, 7);
    			} else if (((RoadTile) map[turnX][turnY]).getType() == 6) {
        			map[turnX][turnY] = new RoadTile(200 + (32 * turnX), turnY * 32, 10);
    			}
    		} else if (map[turnX][turnY] instanceof GrassTile) {
    			map[turnX][turnY] = new RoadTile(200 + (32 * turnX), turnY * 32, 3);
    		}
    	} else if (turn[0] == 2 && turn[1] == 2) {
    		if (map[turnX][turnY] instanceof RoadTile) {
    			if (((RoadTile) map[turnX][turnY]).getType() == 5) {
    				map[turnX][turnY] = new RoadTile(200 + (32 * turnX), turnY * 32, 8);
    			} else if (((RoadTile) map[turnX][turnY]).getType() == 4) {
        			map[turnX][turnY] = new RoadTile(200 + (32 * turnX), turnY * 32, 4);
    			} else if (((RoadTile) map[turnX][turnY]).getType() == 0) {
        			map[turnX][turnY] = new RoadTile(200 + (32 * turnX), turnY * 32, 6);
    			} else if (((RoadTile) map[turnX][turnY]).getType() == 7){
        			map[turnX][turnY] = new RoadTile(200 + (32 * turnX), turnY * 32, 10);
    			}
    		} else if (map[turnX][turnY] instanceof GrassTile) {
    			map[turnX][turnY] = new RoadTile(200 + (32 * turnX), turnY * 32, 4);
    		}
    	} else if (turn[0] == 2 && turn[1] == 1) {
    		if (map[turnX][turnY] instanceof RoadTile) {
    			if (((RoadTile) map[turnX][turnY]).getType() == 4) {
    				map[turnX][turnY] = new RoadTile(200 + (32 * turnX), turnY * 32, 8);
    			} else if (((RoadTile) map[turnX][turnY]).getType() == 5) {
        			map[turnX][turnY] = new RoadTile(200 + (32 * turnX), turnY * 32, 5);
    			} else if (((RoadTile) map[turnX][turnY]).getType() == 0) {
        			map[turnX][turnY] = new RoadTile(200 + (32 * turnX), turnY * 32, 7);
    			} else if (((RoadTile) map[turnX][turnY]).getType() == 6) {
        			map[turnX][turnY] = new RoadTile(200 + (32 * turnX), turnY * 32, 10);
    			}
    		} else if (map[turnX][turnY] instanceof GrassTile) {
    			map[turnX][turnY] = new RoadTile(200 + (32 * turnX), turnY * 32, 5);
    		}
    	}
    }
    
    /**
     * Method draw.
     * 
     * @param g Graphics
     */
    @Override
    public void draw(Graphics g) {     
        //---------------------
        // Sidebar
        //---------------------
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 200, HEIGHT);
        g.fillRect(0, HEIGHT - 110, WIDTH, 110);
        g.setColor(Color.WHITE);
        g.drawString("Money: " + money, 1, 15);
        g.drawString("Fuel: " + fuel, 1, 30);
        g.drawString("Date: " + months[month] + " " + day + ", " + year, 1, 45);
        g.drawString("Corn: " + goods[0], 1, 60);
        g.drawString("Stats:", 1, 90);
        g.drawString("Crops Grown: " + cropsGrown, 1, 105);
        g.drawString("Fuel Produced: " + fuelProduced, 1, 120);
        g.drawString("Cities Fed: " + foodProduced, 1, 135);
        
        g.drawString("Food (Green) vs Fuel (Red):", 1, 165);
        double percentFood = ((double) foodProduced) / (foodProduced + fuelProduced);
        if (foodProduced + fuelProduced > 0) {
	        g.drawRect(1, 175, 102, 7);
	        g.setColor(Color.GREEN);
	        for (int i = 0; i < (int) (percentFood * 100); i++) {
	        	g.drawRect(2 + i, 176, 1, 5);
	        }
	        g.setColor(Color.RED);
	        for (int i = (int) (percentFood * 100); i < 100; i++) {
	        	g.drawRect(2 + i, 176, 1, 5);
	        }
        }
        g.setColor(Color.WHITE);
        
        //---------------------
        // Bottom Bar
        //---------------------
        g.drawString("Cost: 100", 200, 669);
        if (buyingCorn) {
        	buyCorn.draw(g, true);
        } else {
        	buyCorn.draw(g);
        }
        g.drawString("Click/Press 1 to buy", 200, 730);
        
        //---------------------
        // Map
        //---------------------
        g.setColor(Color.BLACK);
        for (int x = 0; x < map.length; x++) {
    		for (int y = 0; y < map[0].length; y++) {
    			map[x][y].draw(g);
    		}
    	}
        
        if (buyingCorn && hoverPoint != null && MAP_BOUNDS.contains(hoverPoint)) {
        	if (map[(hoverPoint.x - 200) / 32][hoverPoint.y / 32] instanceof GrassTile) {
        		g.drawRect((((hoverPoint.x - 200) / 32) * 32) + 200, (hoverPoint.y / 32) * 32, 32, 32);
        	}
        }
        
        //---------------------
        // Workers
        //---------------------
        for (Worker w : workers) {
        	w.draw(g);
        }
        
        //---------------------
        // Trucks
        //---------------------
        for (Truck tr : trucks) {
        	tr.draw(g);
        }
        
        //---------------------
        // Messages
        //---------------------
        for (PopUpMessage msg : messages) {
        	msg.draw(g);
        }
        
        super.draw(g);
    }

    /**
     * Method checkForClick.
     * 
     * @param point Point
     */
    @Override
    public void checkForClick(Point p) {
        if (!isPaused()) {
    		if (MAP_BOUNDS.contains(p)) {
				Tile t = map[(p.x - 200) / 32][p.y / 32];
    			if (t.checkForClick(p)) {
    				if (t.getClass() == GrassTile.class && buyingCorn) {
    					map[(p.x - 200) / 32][p.y / 32] = new CornTile(t.x, t.y, false);
    					crops.add((CropTile) map[(p.x - 200) / 32][p.y / 32]);
    					money -= cornCost;
    					if (money < cornCost) buyingCorn = false;
    				} else if (t instanceof CropTile) {
    					CropTile c = (CropTile) t;
    					if (c.isGrown() && !c.hasWorker()) {
    						Worker w = new Worker(c, (homeX * 32) + 216, (homeY * 32) + 16);
    						workers.add(w);
    						c.addWorker();
    					}
    				} else if (t.getClass() == CityTile.class) { //TODO: Change for more crops.
    					CityTile ci = (CityTile) t;
    					if (goods[0] > 0 && fuel >= calculateFuel(ci)) {
    						goods[0] -= 50;
    						Truck tr = new Truck(ci, (homeX * 32) + 216, (homeY * 32) + 16);
    						fuel -= calculateFuel(ci);
    						trucks.add(tr);
    					}
    				} else if (t.getClass() == FactoryTile.class) {
    					FactoryTile ft = (FactoryTile) t;
    					if (goods[0] >= 50 && fuel >= 50) {
    						goods[0] -= 50;
    						fuel -= 50;
    						Truck tr = new Truck(ft, (homeX * 32) + 216, (homeY * 32) + 16);
    						trucks.add(tr);
    					}
    				}
    			}
    		} else if (MENU_BOUNDS.contains(p)) {
    			if (buyCorn.checkForClick(p) && money >= cornCost) {
    				buyingCorn = !buyingCorn;
    			}
    		}
        }
        super.checkForClick(p);
    }
    
    private int calculateFuel(CityTile ci) {
    	int diffX = homeX - ((ci.x - 200) / 32);
    	int diffY = homeY - (ci.y / 32);
    	if (diffX < 0) diffX *= -1;
    	if (diffY < 0) diffY *= -1;
    	return (diffX + diffY) * 5;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    	if (e.getKeyChar() == KeyEvent.VK_1 && money >= cornCost) {
			buyingCorn = !buyingCorn;
		} else if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
			paused = !paused;
			if (paused) {
				pausedDiff = System.currentTimeMillis() - lastSecond;
				for (PopUpMessage msg : messages) {
					msg.pause();
				}
			} else {
				lastSecond = System.currentTimeMillis() - pausedDiff;
				for (PopUpMessage msg : messages) {
					msg.unpause();
				}
			}
		}
    }
    
    @Override
    public void tick() {
    	if (!paused) {
    		if (System.currentTimeMillis() - lastSecond > (1000 / speed)) {
	    		day++;
	    		if (day > days[month]) {
	    			day = 1;
	    			month++;
	    			if (month >= 12) {
	    				month = 0;
	    				year++;
	    			}
	    		}
	    		lastSecond = System.currentTimeMillis();
	    		for (CropTile t : crops) {
	    			t.grow();
	    		}
	    		for (CityTile ct : cities) {
	    			if (ct.deHappy()) {
	    				Farm.PANEL.setActiveScreen(new GameOverScreen());
	    			}
	    		}
    		}
    		
    		ListIterator<Worker> it = workers.listIterator();
    		while (it.hasNext()) {
    			Worker w = it.next();
    			w.move();
    			if (w.isHeadingHome()) {
    				crops.remove(w.getCrop());
    				map[(w.getCrop().x - 200) / 32][w.getCrop().y / 32] = new GrassTile(w.getCrop().x, w.getCrop().y);
    				w.advanceProcess();
    			} else if (w.isHome()) {
    				goods[0] += 125;
    				cropsGrown++;
    				it.remove();
    			}
    		}
    		
    		ListIterator<Truck> itt = trucks.listIterator();
    		while (itt.hasNext()) {
    			Truck w = itt.next();
    			w.move();
    			if (w.isHeadingHome()) {
    				w.advanceProcess();
    				if (w.getCity() != null) {
    					w.getCity().beHappy();
    				}
    			} else if (w.isHome() ) {
    				if (w.getCity() != null) {
    					money += 75;
    					foodProduced++;
    					messages.add(new PopUpMessage((homeX * 32) + 200, homeY * 32, 1, "+ 75"));
    				} else {
    					fuel += 125;
    					fuelProduced++;
    					messages.add(new PopUpMessage((homeX * 32) + 200, homeY * 32, 0, "+ 125"));
    				}
    				itt.remove();
    			}
    		}
    		
    		ListIterator<PopUpMessage> msgs = messages.listIterator();
    		while (msgs.hasNext()) {
    			PopUpMessage w = msgs.next();
    			w.tick();
    			if (w.shown()) {
    				msgs.remove();
    			}
    		}
    	}
    }
}
