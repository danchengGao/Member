package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import model.LoggedinUser;

public class LoginFrameListener implements ActionListener{

    private String username = "";
	private Controller controller;
	private LoggedinUser user;
	
	 /**
		 * Constructor which take a controller as its parameter. 
		 *	Construct an actionListener which run actionPerformed code when the assigned button is presses
		 * 
		 * @param controller
		 */
	public LoginFrameListener(Controller controller) {
		this.controller = controller;
	}
	
	/**
	 * ActionListener for file creation 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		username = controller.getMainFrame().getLoginFrame().getUsername();
		File file = new File("source/data/Users/"+ username, "Default.txt");		
		if (!username.equals("")){						
			try {
				BufferedReader bfReader = new BufferedReader(new FileReader(file));
				user = new LoggedinUser(username, file, controller.getJaws());
				bfReader.close();
				controller.getMainFrame().welcomeMessage(username, "Default");	
		        controller.assignUser(user);
		        controller.getMainFrame().enableMenu(true);
		        controller.updateLoadList(user.getFileList());
		        controller.getMainFrame().getLoginItem().setText("Logout");
		        controller.getMainFrame().getLoginFrame().setVisible(false);
			} 
			catch (FileNotFoundException e1) {
				controller.getMainFrame().getLoginFrame().alert("User not found!");
			} 
			catch (IOException e1) {
				controller.getMainFrame().getLoginFrame().alert("Login failed!");
			}			
		}	
	}
}
