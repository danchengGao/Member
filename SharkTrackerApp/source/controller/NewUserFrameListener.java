package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import model.LoggedinUser;

public class NewUserFrameListener implements ActionListener{

	private String username = "";   
	private Controller controller;
    private LoggedinUser user;
    private BufferedReader bfReader;
	
    /**
   	 * Constructor which take a controller as its parameter. 
   	 *	Construct an actionListener which run actionPerformed code when the assigned button is presses
   	 * 
   	 * @param controller
   	 */
	public NewUserFrameListener(Controller c) {
		controller = c;
	}
	
	/**
	 * ActionListener for creating a new user 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		username = controller.getMainFrame().getNewUserFrame().getUsername();
		File file = new File("source/data/Users/"+ username, "Default.txt");
		if (!username.equals("")){						
			try {
				bfReader = new BufferedReader(new FileReader(file));
				controller.getMainFrame().getNewUserFrame().alert("User already exists!");
				bfReader.close();
			} 
			catch (FileNotFoundException e1) {
				try {
					user = new LoggedinUser(username, controller.getJaws());
					controller.getMainFrame().welcomeMessage(username, "Default");	
		            controller.assignUser(user);
		            controller.getMainFrame().enableMenu(true);
		            controller.updateLoadList(user.getFileList());
		            controller.getMainFrame().getLoginItem().setText("Logout");
		            controller.getMainFrame().getNewUserFrame().setVisible(false);
				} 
				catch (IOException e2) {
					controller.getMainFrame().getNewUserFrame().alert("Error");
				}
			} 
			catch (IOException e1) {
				controller.getMainFrame().getNewUserFrame().alert("Error");
			} 
			
		}
		
	}

}
