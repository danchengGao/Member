package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewUserFrame extends JFrame{
	
	private JButton create = new JButton("Create User");
	private JLabel nameLabel = new JLabel("New Username:");
	private JTextField name = new JTextField();
	
	private JPanel south = new JPanel();
	private JPanel center = new JPanel();
	/**
	 * Create a new UserFrame
	 */
	public NewUserFrame(){
		super("Create New User");
		configure();
		setMiddle();
		this.setSize(300, 100);
	}
	/**
	 * Settings for the new UserFrame
	 */
	public void configure(){
		this.setLayout(new BorderLayout());
		this.add(south,BorderLayout.SOUTH);
		this.add(center,BorderLayout.CENTER);
		
		south.add(create);
		
		center.setLayout(new GridLayout(1,2));		
		center.add(nameLabel);
		center.add(name);
		
	}
	/**
	 * Set the Frame at the middle of the screen
	 */
	public void setMiddle() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/5, dim.height/2-this.getSize().height/5);
	}
	/**
	 * Create error Frame
	 * @param text
	 */
	public void alert(String text){
		JOptionPane alert = new JOptionPane();
		alert.showMessageDialog(this, text);
	}
	/**
	 * 
	 * @return the Button that says "create user"
	 */
	public JButton getCreateButton(){
		return create;
	}
	/**
	 * 
	 * @return the name of the user
	 */
	public String getUsername(){
		return name.getText();
	}
}
