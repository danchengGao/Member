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

public class LoginFrame extends JFrame{
	
	private JButton login = new JButton("Login");
	private JLabel nameLabel = new JLabel("Username:");
	private JTextField name = new JTextField();
	
	private JPanel south = new JPanel();
	private JPanel center = new JPanel();
	
	
	/**
	 * Constructor for the frame.
	 */
	public LoginFrame(){
		super("Login");
		configure();
		setMiddle();
		this.setSize(300, 100);
	}
	
	/**
	 * Method that sets the GUI for the frame.
	 */
	public void configure(){
		this.setLayout(new BorderLayout());
		this.add(south,BorderLayout.SOUTH);
		this.add(center,BorderLayout.CENTER);
		
		south.add(login);
		
		center.setLayout(new GridLayout(1,2));
		center.add(nameLabel);
		center.add(name);
		
	}
	
	/**
	 * Method that sets the frame in the middle when you run program.
	 */
	public void setMiddle() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/5, dim.height/2-this.getSize().height/5);
	}
	
	/**
	 * Method that creates an error window which contains a message.
	 * @param text the message for the user.
	 */
	public void alert(String text){
		JOptionPane alert = new JOptionPane();
		alert.showMessageDialog(this, text);
	}
	
	/** 
	 * get method for the JButton.
	 * @return JButton login
	 */
	public JButton getLoginButton(){
		return login;
	}
	
	/**
	 * Method that retrieves the text in the textField.
	 * @return String  
	 */
	public String getUsername(){
		return name.getText();
	}
}
