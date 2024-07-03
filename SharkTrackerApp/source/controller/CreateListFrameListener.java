package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import model.LoggedinUser;

public class CreateListFrameListener implements ActionListener{

    private Controller controller;
    private String fileName;
    private BufferedReader bfReader;
	
    /**
	 * 
	 * @param c 
	 */
	public CreateListFrameListener(Controller c) {
		controller = c;
	}
	
	/**
	 *  ActionListener to set the listname as filename and if the listname exists,
	 *  creates a new ErrorFrame which tells the user that "List Already Exist".
	 *  
	 *  If it is a new User, create a new list and update the favourite list using the LoggedinUser's
	 *  information and set the welcomeMessage.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		fileName = controller.getMainFrame().getCreateListFrame().getListname();
		File file = new File("source/data/Users/"+ ((LoggedinUser) controller.getUser()), fileName + ".txt");
		if (!fileName.equals("")){						
			try {
				bfReader = new BufferedReader(new FileReader(file));
				controller.getMainFrame().getCreateListFrame().alert("List already exists!");
			} 
			catch (FileNotFoundException e1) {
				try {
					((LoggedinUser) controller.getUser()).createNewList(fileName);
					controller.updateFavouriteList(((LoggedinUser) controller.getUser()).getFavouritesList());
					controller.getMainFrame().addLoadList(((LoggedinUser) controller.getUser()).getFileList());
					controller.getMainFrame().welcomeMessage(((LoggedinUser) controller.getUser()).getName(), fileName);
					controller.getMainFrame().getCreateListFrame().setVisible(false);
				} 
				catch (IOException e2) {
					controller.getMainFrame().getCreateListFrame().alert("Cannot create list!");
				}
			} 			
		}		
	}
}
