package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchListener implements ActionListener {

	private Controller controller;
	
	/**
	 * Constructor which take a controller as its parameter. 
	 *	Construct an actionListener which run actionPerformed code when the assigned button is presses
	 * 
	 * @param controller
	 */
	public SearchListener(Controller controller) {
		this.controller = controller;
	}
	
	/**
	 * ActionListener which set the searchFrame visible- it will appear
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		controller.getMainFrame().getSearchFrame().setVisible(true);
	}
}
