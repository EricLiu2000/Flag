package flag;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class Star extends Polygon {

	//Default serialVersionUID
	private static final long serialVersionUID = 1L;

	//X position of the star
	double x;
	
	//Y Position of the star
	double y;
	
	//The width of the flag
	double flagWidth;
	
	//The height of the flag
	double flagHeight;
	
	//The longer radius of the star
	double longRadius;
	
	//The shorter radius of the star
	double shortRadius;

	//The array of x points
	int xArray[] = new int[10];
	
	//The array of y points
	int yArray[] = new int[10];
	
	//The graphics object
	Graphics g;
	
	/**
	 * Constructor for the star
	 * 
	 * @param x the x position of the star
	 * @param y the y position of the star
	 * @param flagWidth the width of the flag
	 * @param flagHeight the height of the flag
	 * @param g the graphics object to be used
	 */
	public Star(double x, double y, double flagWidth, double flagHeight, Graphics g) {
		//Sets the 
		this.x = x;
		this.y = y;
		this.flagWidth = flagWidth;
		this.flagHeight = flagHeight;
		this.g= g;
		
		//Sets the values for the long and short radii of the star
		longRadius = 0.0616 * flagHeight/2;
		shortRadius = longRadius * 0.3819660113;

		//Sets coordinates for the different points of the star
		for(int counter = 0; counter < 10; counter ++) {
			if(counter % 2 == 0) {
				xArray[counter] = (int) (x + longRadius * Math.cos(Math.toRadians(-18 + counter * 36)));
				yArray[counter] = (int) (y + longRadius * Math.sin(Math.toRadians(-18 + counter * 36)));
			}
			
			if(counter % 2 == 1) {
				xArray[counter] = (int) (x + shortRadius * Math.cos(Math.toRadians(-18 + counter * 36)));
				yArray[counter] = (int) (y + shortRadius * Math.sin(Math.toRadians(-18 + counter * 36)));
			}
		}
	}
	
	//Draws the star
	public void draw() {
		g.setColor(Color.WHITE);
		g.fillPolygon(xArray, yArray, 10);
	}
	
	

}
