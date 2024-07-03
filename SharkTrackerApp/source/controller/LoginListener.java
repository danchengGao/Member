package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.DefaultUser;

public class LoginListener implements ActionListener{

	private Controller controller;
	
	 /**
		 * Constructor which take a controller as its parameter. 
		 *	Construct an actionListener which run actionPerformed code when the assigned button is presses
		 * 
		 * @param controller
		 */
	public LoginListener(Controller c) {
		controller = c;
	}
	
	/**
	 * ActionListener that change message text in the mainFrame
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (controller.getMainFrame().getLoginItem().getText().equals("Login")){
			controller.getMainFrame().getLoginFrame().setVisible(true);
		}
		else{			
			controller.assignUser(new DefaultUser(controller.getJaws()));
			controller.getMainFrame().welcomeMessage("visitor", "");
			controller.getMainFrame().enableMenu(false);
			controller.getMainFrame().getLoginItem().setText("Login");
		}
	}
}
