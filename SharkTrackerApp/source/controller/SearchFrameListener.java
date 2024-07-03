package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JComboBox;

import api.jaws.Jaws;
import api.jaws.Ping;
import api.jaws.Shark;

import model.PingComparator;

import view.SearchFrame;
import view.SharkPanel;

public class SearchFrameListener implements ActionListener  {	
	
	private Controller controller;
	private Jaws jaws;	
	private SearchFrame searchFrame;
	private SharkPanel sharkPanel;
	
	private JComboBox JCBtrackingRange;
	private JComboBox JCBgender;
	private JComboBox JCBsol;
	private JComboBox JCBlocation;	
	
	private ArrayList <Ping> pingList;
	
	/**
	 * Constructor which take a controller as its perameter. 
	 *	Construct an actionListener which run actionPerformed code when the assigned button is presses
	 * @param controller
	 */
	public SearchFrameListener(Controller c) {
		controller = c;
		jaws = controller.getJaws();
		searchFrame = controller.getMainFrame().getSearchFrame();
		
		JCBtrackingRange = searchFrame.getTrackingRangeBox();
		JCBgender = searchFrame.getGenderBox();
		JCBsol = searchFrame.getStageOfLifeBox();
		JCBlocation = searchFrame.getTagLocationBox();
	}
	
	/**
	 * ActionListener when the search button is pressed
	 */
	public void actionPerformed(ActionEvent action) {
	
		searchFrame.clearResult();
		controller.getSharkList().clear();
		
		String selectedTrackingRange = (String)JCBtrackingRange.getSelectedItem();
		String selectedGender = (String)JCBgender.getSelectedItem();
		String selectedStageOfLife = (String)JCBsol.getSelectedItem();
		String selectedTagLocation = (String)JCBlocation.getSelectedItem();
		
		if (selectedTrackingRange.equals("Last 24 Hours")){
			pingList = jaws.past24Hours();
		}
		else if(selectedTrackingRange.equals("Last Week")){
			pingList = jaws.pastWeek();
		}
		else if(selectedTrackingRange.equals("Last Month")){
			pingList = jaws.pastMonth();
		}
		
		Collections.sort(pingList, new PingComparator());
		ArrayList <String> existedName = new ArrayList();
		ArrayList <Ping> mostRecentList = new ArrayList();
		for(Ping p : pingList){
			String name = p.getName();
			if(!existedName.contains(name)){
				mostRecentList.add(p);
				existedName.add(name);
			}
			pingList = mostRecentList;
		}
		
		if (selectedGender.equals("Male")){
			selectByGender("Male");
		}
		else if (selectedGender.equals("Female")){
			selectByGender("Female");
		}
		
		if(selectedStageOfLife.equals("Mature")){
			selectByStage("Mature");
		}
		else if(selectedStageOfLife.equals("Immature")){
			selectByStage("Immature");
		}
		else if(selectedStageOfLife.equals("Undetermined")){
			selectByStage("Undetermined");
		}
		
		if(!selectedTagLocation.equals("All")){
			selectByLocation(selectedTagLocation);
		}
		
		if(pingList.isEmpty()){
			searchFrame.noResult();
		}
		else{			
			for(Ping p : pingList){
				sharkPanel = new SharkPanel(jaws.getShark(p.getName()), p, controller.isFollowing(jaws.getShark(p.getName())));
				searchFrame.addSharkPanel(sharkPanel);
				controller.getSharkList().add(jaws.getShark(p.getName()));				
			}
			controller.checkSharkList();
		}
		
		List <SharkPanel> panelList = searchFrame.getSharkPanels();{
			for (SharkPanel sp : panelList){
				sp.getFollowButton().addActionListener(new FollowListener(controller, sp));
			}
		}
	}
	
	/**
	 * Method that sort the pingList into gender 
	 * @param gender
	 */
	public void selectByGender(String gender){
		ArrayList <Ping> selectedList = new ArrayList();
		for (Ping p : pingList){
			if(jaws.getShark(p.getName()).getGender().equals(gender)){
				selectedList.add(p);
			}
		}
		pingList = selectedList;	
	}
	
	/**
	 * Method that sort the pingList into stage of Life 
	 * @param stage
	 */
	public void selectByStage(String stage){
		ArrayList <Ping> selectedList = new ArrayList();
		for (Ping p : pingList){
			if(jaws.getShark(p.getName()).getStageOfLife().equals(stage)){
				selectedList.add(p);
			}
		}
		pingList = selectedList;		
	}
	
	/**
	 * Method that sort the pingList into location 
	 * @param location
	 */
	public void selectByLocation(String location){
		ArrayList <Ping> selectedList = new ArrayList();
		for (Ping p : pingList){
			if(jaws.getShark(p.getName()).getTagLocation().equals(location)){
				selectedList.add(p);
			}
		}
		pingList = selectedList;
	}
}
