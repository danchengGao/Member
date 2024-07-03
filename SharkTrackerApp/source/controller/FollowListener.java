package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;

import javax.swing.JButton;

import view.SharkPanel;

public class FollowListener implements ActionListener {
	
	private Controller controller;
	private SharkPanel sharkPanel;
	private JButton follow;
	
	 /**
	 * Constructor which take a controller as its parameter. 
	 *	Construct an actionListener which run actionPerformed code when the assigned button is presses
	 * 
	 * @param controller
	 */
	public FollowListener(Controller c,SharkPanel s){
		controller = c;
		sharkPanel = s;
		follow = sharkPanel.getFollowButton();
	}
	
	/**
	 * ActionListener for the follow button, if pressed, add the shark into Favourites List
	 */
	public void actionPerformed(ActionEvent e){
		if(follow.getText().equals("Follow")){
			follow.setText("Following");
			controller.getFavouritesList().addShark(sharkPanel.getShark());
		}
		else{
			follow.setText("Follow");
			controller.getFavouritesList().removeShark(sharkPanel.getShark());
		}
		controller.checkList();
		controller.getMainFrame().getFavouritesFrame().addText(controller.getFavouritesList().toString());
		try {
			if ( !controller.isLoggedin() ){
				controller.updateDefaultUser();
			}
		} 
		catch (IOException e1) {
			controller.getMainFrame().getSearchFrame().alert("Error!");
		}
	}
}
