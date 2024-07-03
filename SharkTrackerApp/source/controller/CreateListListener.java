package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateListListener implements ActionListener{

    private Controller controller;
	
    /**
	 * Constructor which take a controller as its parameter. 
	 *	Construct an actionListener which run actionPerformed code when the assigned button is presses
	 * 
	 * @param controller
	 */
	public CreateListListener(Controller c) {
		controller = c;
	}
	
	/**
	 * Method which set the listFrame visible- it will appear
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		controller.getMainFrame().getCreateListFrame().setVisible(true);		
	}
}
