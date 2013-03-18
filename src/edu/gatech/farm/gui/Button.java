/* Comment
 * 
 */

package edu.gatech.farm.gui;

import java.awt.Graphics;
import java.awt.Point;

import edu.gatech.farm.main.GamePanel;


/**
 * @author Glenn
 * @version $Revision: 1.0 $
 */
public abstract class Button{
        /**
         * Method draw.
         * @param g Graphics
         * @param panel GamePanel
         * @param width int
         * @param height int
         */
        public abstract void draw(Graphics g, GamePanel panel, int width, int height);
        
        /**
         * Method isClicked.
         * @param point Point
        
         * @return boolean */
        public abstract boolean isClicked(Point point);
        
        /**
         * Method isIn.
         * @param p Point
        
         * @return boolean */
        public abstract boolean isIn(Point p);
        
        /**
         * Method getHeight.
        
         * @return int */
        public abstract int getHeight();
}