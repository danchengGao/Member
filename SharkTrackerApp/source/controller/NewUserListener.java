package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewUserListener implements ActionListener{

    private Controller controller;
	
    /**
   	 * Constructor which take a controller as its parameter. 
   	 *	Construct an actionListener which run actionPerformed code when the assigned button is presses
   	 * 
   	 * @param controller
   	 */
	public NewUserListener(Controller c) {
		controller = c;
	}
	
	/**
	 * ActionListener which set the New user Frame visible- it will appear
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		controller.getMainFrame().getNewUserFrame().setVisible(true);
	}
}
