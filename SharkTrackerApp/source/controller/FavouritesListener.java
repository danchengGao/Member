package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.FavouritesFrame;

public class FavouritesListener implements ActionListener{

	private Controller controller;
	
	 /**
		 * Constructor which take a controller as its parameter. 
		 *	Construct an actionListener which run actionPerformed code when the assigned button is presses
		 * 
		 * @param controller
		 */
	public FavouritesListener(Controller c){
		controller = c;
	}
	
	/**
	 * Method which set the Favourites Frame visible- it will appear
	 */
	public void actionPerformed(ActionEvent e) {
		controller.getMainFrame().getFavouritesFrame().setVisible(true);	
	}

}
