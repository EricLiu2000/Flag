package flag;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.util.Hashtable;

public class Flag extends JFrame implements ChangeListener{

	Star star;
	
	//Default serialVersionUID
	private static final long serialVersionUID = 1L;

	//The width of the flag
	public double width;
	
	//The height of the flag
	public double height;
	
	//The diagonal of the flag
	public double diagonal;
	
	//The slider for user input
	private JSlider slider;
	
	//A panel containing the flag and a slider
	Panel panel;
	
	public Flag() {
		//Creates a new panel, sets the layout to BorderLayout, and adds it to the JFrame
		panel = new Panel();
		panel.setLayout(new BorderLayout());
		add(panel);
		
		//Initializes the slider
		initSlider(1000);
		
		//Adds the slider to the panel
		panel.add(slider, BorderLayout.SOUTH);
		
		//Sets the diagonal to the value of the slider
		diagonal = slider.getValue();
		
		//Sets the size of the window based on the length of the diagonal
		setSizeWithDiagonal(diagonal);
		
		//Paints the flag
		repaint();
		
		//Tells the window how to close
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Makes the JFrame visible
		setVisible(true);
	}
	
	/**
	 * Paints the flag
	 * 
	 * @param g the Graphics object to be used
	 */
	public void paint(Graphics g) {
		System.out.println(diagonal);
	}

	/**
	 * Initializes the slider
	 * 
	 * @param value the initial value of the slider
	 */
	public void initSlider(int value) {
		//Creates a slider from 500 - 2000 that determines the length of the diagonal
		slider = new JSlider(500, 2000, value);
		slider.addChangeListener(this);
		slider.setMajorTickSpacing(100);
		slider.setPaintTicks(true);
		slider.setVisible(true);
		
		//Creates a hashtable for labels
		Hashtable<Integer, JLabel> labels = new Hashtable<Integer, JLabel>();
		
		//Adds labels
		labels.put(500,  new JLabel("500"));
		labels.put(1000, new JLabel("1000"));
		labels.put(1500, new JLabel("1500"));
		labels.put(2000, new JLabel("2000"));
		
		//Sets the LableTable of slider to this hashtable
		slider.setLabelTable(labels);
		
		//Tells the slider to paint the labels
		slider.setPaintLabels(true);
	}
	
	/**
	 * Sets the size of the window based on the length of the diagonal
	 * 
	 * @param diagonal the desired diagonal of the window
	 */
	public void setSizeWithDiagonal(double diagonal) {

		/*
		 * The ratio of width:height is 1.9:1. Using this, we can find the height if given a diagonal.
		 * The formula used is height = diagonal/root(4.61)
		 */
		height = diagonal/Math.sqrt(4.61);
		
		//Using the same 1.9:1 ratio, we calculate the width
		width = height*1.9;

		//Sets the size of the JFrame based on user input
		setSize((int)width, (int)height);
		
	}
	
	@Override
	/**
	 * This method is called whenever the slider is moved
	 * 
	 * @param e The ChangeEvent object used to notify us of a change in the slider
	 */
	public void stateChanged(ChangeEvent e) {
		
		/*
		 * Creates a JSlider equivalent to the one that was moved. 
		 * This saves us the trouble of typing in (Jslider) e.getSource() every time.
		 */
		JSlider value = (JSlider) e.getSource();
		
		//Tests to see if the slider is finished moving
		if(!value.getValueIsAdjusting()) {
			//Sets the value of diagonal to the value of slider, resizes the window, and repaints
			diagonal = value.getValue();
			setSizeWithDiagonal(diagonal);
			repaint();
		}
	}
	
}
