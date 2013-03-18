package edu.gatech.farm.gui;

import java.awt.Graphics;

public class CenterText {
	public static void string(Graphics g, String text, int midX, int y) {
		g.drawString(text, midX - (g.getFontMetrics().stringWidth(text) / 2), y);
	}
}
