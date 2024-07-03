package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextArea;

import api.jaws.Jaws;
import model.FavouritesList;
import view.MainFrame;
import view.SharkPanel;

public class FavouritesFrameListener extends MouseAdapter{
	
	private Controller controller;
	private FavouritesList favouritesList;
	private MainFrame frame;
	private int favouritesNumber;	
	private SharkPanel sharkPanel;
	
	/**
	 * Constructor which take a controller as its parameter. 
	 *	Construct an actionListener which run mouseClicked code when the assigned button is presses
	 * 
	 * @param controller
	 */
	public FavouritesFrameListener(Controller c){
		controller = c;			
		frame = c.getMainFrame();
	}
	
	/**
	 * When user click on a shark on the favourites Frame, on the searchFrame, the search result will be replaced
	 */
	public void mouseClicked(MouseEvent e){
		favouritesList = controller.getFavouritesList();	
		int y = e.getY();
		JTextArea jTextArea = (JTextArea) e.getSource();
		int height = jTextArea.getHeight();
		favouritesNumber = favouritesList.getFavouritesNumber();
		int rowHeight = height/(favouritesNumber+1);
		int lineNumber = y/rowHeight;
		
		frame.getFavouritesFrame().setVisible(false);
		frame.getSearchFrame().clearResult();
		sharkPanel = new SharkPanel(favouritesList.getShark(lineNumber), controller.mostRecentPing(favouritesList.getShark(lineNumber)), true);
		frame.getSearchFrame().addSharkPanel(sharkPanel);
		sharkPanel.getFollowButton().addActionListener(new FollowListener(controller, sharkPanel));
		frame.getSearchFrame().setVisible(true);
	}
}
