package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MapFrame extends JFrame {
	
	private JLabel label;
	
	/**
	 * Constructor for the frame.
	 */
	public MapFrame(){		
		super("Map");	
		this.setVisible(true);
		this.setMiddle();	
	}
	
	/**
	 * Method that displays a image in the frame.
	 * @param image ImageIcon 
	 */
	public void showMap(ImageIcon image){
		label = new JLabel(image);
		this.add(label);
		this.pack();
	}
	
	/**
	 * Method that sets the frame in the middle when you run program.
	 */
	public void setMiddle() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
}
