package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JMenuItem;

import model.LoggedinUser;

public class LoadListListener implements ActionListener{

	private Controller controller;
	private String selectedFileName = "";
	private String username = "";
	
	 /**
	 * Constructor which take a controller as its parameter. 
	 *	Construct an actionListener which run actionPerformed code when the assigned button is presses
	 * @param controller
	 */
	public LoadListListener(Controller c) {
		controller = c;
	}
	
	/**
	 * ActionListener to check if the user file exist or not 
	 * if yes, read file 
	 * if no, pop up error message box
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		selectedFileName = ((JMenuItem) e.getSource()).getText() + ".txt";
		username = ((LoggedinUser)controller.getUser()).getName();
		File file = new File("source/data/Users/"+ username, selectedFileName);
		try {
			BufferedReader bfReader = new BufferedReader(new FileReader(file));
			controller.loadNewListFile(file, ((JMenuItem) e.getSource()).getText());
		} 
		catch (FileNotFoundException e1) {
			controller.getMainFrame().alert("Cannot find list!");
		} 
		catch (IOException e1) {
			controller.getMainFrame().alert("Error!");
		}
	}
}
