package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import model.Map;
import view.MapFrame;

public class MapListener implements ActionListener{
	
	private Controller c;
	private Map map;
	
	 /**
		 * Constructor which take a controller as its parameter. 
		 *	Construct an actionListener which run actionPerformed code when the assigned button is presses
		 * 
		 * @param controller
		 */
	public MapListener(Controller c){
		this.c = c;
		this.map = c.getMap();
		}		
		
	/**
	 * ActionListener that create new map frame when the assigned button is pressed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		MapFrame mapFrame = new MapFrame();
		String URL = "";
		URL = map.generateURL(c.getFavouritesList(), c);
		try {
			mapFrame.showMap(map.locateShark(URL));
				
		} catch (IOException e1) {
			e1.printStackTrace();
		}			
	}
}
	
	
