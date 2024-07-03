package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VideoFrame extends JFrame{
	
	private JLabel sharkName;
	private JLabel link;
	private JPanel namePanel;
	private JPanel linkPanel;

	/**
	 * set Frame of the Shark of the day
	 */
	public VideoFrame() {
		super("Shark of The Day");
		this.setVisible(true);
		this.setSize(500, 200);
		this.setMiddle();
		
		this.createWidget();
		
	}
	
	/**
	 * settings for the videoFrame
	 */
	private void createWidget() {
		this.setLayout(new BorderLayout());
		
		sharkName = new JLabel("");
		link = new JLabel("");

		namePanel = new JPanel();
		linkPanel = new JPanel();
		namePanel.setBorder(BorderFactory.createLineBorder(Color.PINK, 3));
		linkPanel.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
		
		namePanel.add(sharkName);
		linkPanel.add(link);

		this.add(namePanel, BorderLayout.NORTH);
		this.add(linkPanel, BorderLayout.CENTER);
	}

	/**
	 * set the name of the shark
	 */
	public void setName(String name) {
		this.sharkName.setText(name); 
	}
	/**
	 * Set the link
	 * @param link
	 */
	public void setLink(String link) {
		this.link.setText(link); 
	}
	/**
	 * Method to set the Frame in the middle of the screen
	 */
	public void setMiddle() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
 		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
 	}
	/**
	 * method to update the UI
	 */
	public void updateUI() {
		namePanel.updateUI();
		linkPanel.updateUI();

	}
	
}
