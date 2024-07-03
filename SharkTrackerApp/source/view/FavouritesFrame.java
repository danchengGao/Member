package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class FavouritesFrame extends JFrame{
	
	private JPanel north;
	private JPanel centre;
	private JPanel south;
	
	private JLabel label;
	private JTextArea info;
	private JButton map;
	
	/**
	 * Constructor for the frame.
	 */
	public FavouritesFrame(){
		super("Favourites");
		this.setSize(500, 300);
		this.createWidgets();
		this.setMiddle();
	}
	
	/**
	 * Method that creates all the components in the frame.
	 */
	private void createWidgets(){
		
		north = new JPanel();
		centre = new JPanel();
		south = new JPanel();
		label = new JLabel("Your favourite sharks are this far away from you right now:");
		info = new JTextArea();
		map = new JButton("Location");
		
		this.setLayout(new BorderLayout());
		this.add(north, BorderLayout.NORTH);
		this.add(centre, BorderLayout.CENTER);
		
		north.setLayout(new FlowLayout(FlowLayout.LEFT));
		north.add(label);
		
		centre.setLayout(new FlowLayout());
		centre.add(info);
		info.setSize(this.getWidth()-30,this.getHeight());
		info.setLineWrap(true);
		info.setEditable(false);
		
		south.add(map);
		this.add(south, BorderLayout.SOUTH);
	}
	
	/**
	 * Method that sets the frame in the middle when you run program.
	 */
	public void setMiddle() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
	
	/**
	 * Method that adds the name(s) of the shark(s) and the distance between them and King's in the JTextArea.
	 * @param text the name(s) of the shark(s) and the distance between them and King's.
	 */
	public void addText(String text){
		info.setText(text);	
		centre.updateUI();
	}
	
	/**
	 * Method that creates an information window that contains a message.
	 * @param text the message for the user.
	 */
	public void alert(String text){
		JOptionPane alert = new JOptionPane();
		alert.showMessageDialog(this, text);
	}
	
	/**
	 * get method for the JTextArea.
	 * @return JTextArea info
	 */
	public JTextArea getTextArea(){
		return info;
	}
	
	/**
	 * get method for the JButton.
	 * @return JButton map
	 */
	public JButton getMapButton(){
		return map;
	}
}
