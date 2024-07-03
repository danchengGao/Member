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

public class CreateListFrame extends JFrame{
	
	private JButton create = new JButton("Create List");
	private JLabel nameLabel = new JLabel("New List Name:");
	private JTextField name = new JTextField();
	private JPanel south = new JPanel();
	private JPanel center = new JPanel();

	/**
	 * Constructor for the frame.
	 */
	public CreateListFrame(){
		super("Create New Favourites List");
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
		
		south.add(create);
		
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
	 * Method that creates an alert window.
	 * @param text the message for the user.
	 */
	public void alert(String text){
		JOptionPane alert = new JOptionPane();
		alert.showMessageDialog(this, text);
	}
	
	/**
	 * get method for the create JButton.
	 * @return JButton create
	 */
	public JButton getCreateButton(){
		return create;
	}
	
	/**
	 * Method that retrieves the text in the textField.
	 * @return String  
	 */
	public String getListname(){
		return name.getText();
	}
}
