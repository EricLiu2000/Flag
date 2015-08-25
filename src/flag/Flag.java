package flag;

import javax.swing.JFrame;
import java.awt.*;
import java.util.Scanner;

public class Flag extends JFrame{

	//Default serialVersionUID
	private static final long serialVersionUID = 1L;
	
	//The object used for user input
	private Scanner input;
	
	//The width of the flag
	private double width;
	
	//The height of the flag
	private double height;
	
	//The diagonal of the flag
	private double diagonal;
	
	public Flag() {
		//Getting user input
		input = new Scanner(System.in);
		System.out.println("Please enter the desired length of the diagonal");
		
		//Sets our diagonal variable as specified by the user
		diagonal = Double.parseDouble(input.next());
		
		/*
		 * The ratio of width:height is 1.9:1. Using this, we can find the height if given a diagonal.
		 * The formula used is height = diagonal/root(4.61)
		 */
		height = diagonal/Math.sqrt(4.61);
		
		//Using the same 1.9:1 ratio, we calculate the width
		width = height*1.9;
		
		
		//Sets the size of the JFrame based on user input
		setSize((int)width, (int)height);
		
		//Paints the flag
		repaint();
	}
	
	public void paint(Graphics g) {
	}
	

}
