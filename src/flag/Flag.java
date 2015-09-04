package flag;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.util.Hashtable;

public class Flag extends JFrame implements ChangeListener{
	
	//Default serialVersionUID
	private static final long serialVersionUID = 1L;

	//Constant for the height of the window bar
	private static final int WINDOW_BAR = 20;

	public Color red = new Color(224,22,43);
	
	public Color blue = new Color(0,82,165);
	
	//The height of the frame
	public double frameHeight;
	
	//The height of the flag when taking into account both slider height and window bar height
	public double flagHeight;
	
	//The width of the flag
	public double flagWidth;
	
	//The width of the Union
	public double unionWidth;
	
	//The height of the Union
	public double unionHeight;
	
	//The diagonal of the flag
	public double diagonal;
	
	//The slider for user input
	private JSlider slider;

	//The JPanel
	private JPanel panel;
	
	/**
	 * Constructs a new flag, panel, and slider
	 */
	public Flag() {
		
		//Creates a new JPanel
		panel = new JPanel(new BorderLayout());
		
		//Adds the panel to the JFrame
		getContentPane().add(panel, BorderLayout.CENTER);
		
		//Initializes the slider with the value 1000
		initSlider(1000);
		
		//Adds the slider to the panel
		panel.add(slider, BorderLayout.SOUTH);

		//Sets the diagonal to the value of the slider
		diagonal = slider.getValue();

		//Sets the size of the window based on the length of the diagonal	
		setSizeWithDiagonal(diagonal, 54);
		
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
		//Calls the superclass paint method
		super.paint(g);
		
		//Variable for the height of each stripe
		int stripeHeight = (int) ((flagHeight) * 1/13);
		
		//Sets the width of the union
		unionWidth = flagWidth * .76/1.9;
		
		//Sets the height of the union
		unionHeight = stripeHeight * 7;
		
		//Paints stripes
		for(int i = 1; i < 14; i++) {
			//Paints the white stripes
			if(i%2 == 0) {
				g.setColor(Color.WHITE);
				g.fillRect(0, WINDOW_BAR + stripeHeight * (i-1), (int) flagWidth, stripeHeight);
			}
			
			//Paints the red stripes
			if(i%2 == 1) {
				g.setColor(red);
				g.fillRect(0, WINDOW_BAR + stripeHeight * (i-1), (int) flagWidth, stripeHeight);
			}
		}
		
		//Paints the union
		g.setColor(blue);
		g.fillRect(0, WINDOW_BAR, (int) unionWidth, (int) unionHeight);
		
		//Paints the stars
		g.setColor(Color.WHITE);
		
		//Paints the stars
		for(int x = 1; x <= 6; x ++) {
			for(int y = 1; y <= 5; y++) {
				Star star = new Star((x*2 - 1) * 0.063 * flagHeight, (y*2 - 1) * 0.054 * flagHeight, flagWidth, flagHeight, panel.getGraphics());
				star.draw();
			}
		}
		
		for(int x = 1; x <= 5; x++) {
			for(int y = 1; y <= 4; y++) {
				Star star = new Star(x*2 * 0.063 * flagHeight, (y*2) * 0.054 * flagHeight, flagWidth, flagHeight, panel.getGraphics());
				star.draw();
			}
		}
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
		frameHeight = (diagonal/Math.sqrt(4.61));

		//Calculates the flagHeight by subtracting the height of both the window bar and the slider
		flagHeight = frameHeight - WINDOW_BAR - slider.getHeight();

		//Multiplies flagHeight by 1.9 to calculate the width
		flagWidth = flagHeight * 1.9;
		
		//Sets the size of the JFrame and panel based on user input
		setSize(new Dimension((int) flagWidth, (int) frameHeight));
		panel.setSize(new Dimension((int) flagWidth, (int) flagHeight));

		//Repaints the panel
		panel.repaint();
	}
	
	/**
	 * An overloaded setSizeWithDiagonal method.
	 * This is necessary because when the window is initialized, the slider height is 0.
	 * To fix this problem, we merely pass it a slider height.
	 * 
	 * @param diagonal the desired diagonal of the window
	 * @param sliderHeight the height of the slider
	 */
	public void setSizeWithDiagonal(double diagonal, int sliderHeight) {

		/*
		 * The ratio of width:height is 1.9:1. Using this, we can find the height if given a diagonal.
		 * The formula used is height = diagonal/root(4.61)
		 */
		frameHeight = (diagonal/Math.sqrt(4.61));

		//Calculates the flagHeight by subtracting the height of both the window bar and the slider
		flagHeight = frameHeight - WINDOW_BAR - sliderHeight;
		
		//Multiplies flagHeight by 1.9 to calculate the width
		flagWidth = flagHeight * 1.9;
		
		//Sets the size of the JFrame and panel based on user input
		setSize(new Dimension((int) flagWidth, (int) frameHeight));
		panel.setSize(new Dimension((int) flagWidth, (int) flagHeight));

		//Repaints the panel
		panel.repaint();
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
		if(!value.getValueIsAdjusting() && value.getValue() != this.diagonal) {
			//Sets the value of diagonal to the value of slider, resizes the window, and repaints
			diagonal = value.getValue();
			setSizeWithDiagonal(slider.getValue());
		}
	}
}
