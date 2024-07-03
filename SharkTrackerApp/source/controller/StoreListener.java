package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StoreListener implements ActionListener{

    private Controller controller;
	
    /**
	 * Constructor which take a controller as its perameter. 
	 *	Construct an actionListener which run actionPerformed code when the assigned button is presses
	 * @param controller
	 */
	public StoreListener(Controller controller) {
		this.controller = controller;
	}
	
	/**
	 * Method that log the user into the systems
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			controller.updateLoggedinUser();
		} 
		catch (IOException e1) {
			controller.getMainFrame().alert("Error!");
		}		
	}
}
