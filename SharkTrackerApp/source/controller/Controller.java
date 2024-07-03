package controller;

import view.MainFrame;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JMenuItem;

import api.jaws.Jaws;
import api.jaws.Ping;
import api.jaws.Shark;
import model.DefaultUser;
import model.FavouritesList;
import model.LoggedinUser;
import model.Map;
import model.PingComparator;
import model.User;

public class Controller {

	private Jaws jaws;	
	private User user;
	private boolean isLoggedin = false;
	private MainFrame frame; 
	private FavouritesList favouritesList;
	private ArrayList <Shark> sharkList;
	private Map map;
	
	/**
	 * 	Create a Controller with frame as parameter which creates a connection between controller and view.
	 * @param f
	 */
	public Controller(MainFrame f) {
		frame = f;
		jaws = frame.getJaws();
		favouritesList = new FavouritesList(jaws);
		sharkList = new ArrayList<Shark>();
		map = new Map(this);
		this.assignUser(new DefaultUser(jaws));
		this.checkList();
		this.checkSharkList();
		this.assignFunction();	
		frame.welcomeMessage("visitor", "");
	}
	
	/**
	 * This is a method created to put all actionListener so when this method is called 
	*	it added all the ActionListener to the individual Buttons.
	 */
	public void assignFunction() {
		frame.getLoginItem().addActionListener(new LoginListener(this));
		frame.getNewUserItem().addActionListener(new NewUserListener(this));
		frame.getStoreItem().addActionListener(new StoreListener(this));
		frame.getCreateItem().addActionListener(new CreateListListener(this));
		frame.getSearchButton().addActionListener(new SearchListener(this));
		frame.getLoginFrame().getLoginButton().addActionListener(new LoginFrameListener(this));
		frame.getNewUserFrame().getCreateButton().addActionListener(new NewUserFrameListener(this));
		frame.getCreateListFrame().getCreateButton().addActionListener(new CreateListFrameListener(this));
		frame.getSearchFrame().getSearchButton().addActionListener(new SearchFrameListener(this));
		frame.getSearchFrame().getStatisticButton().addActionListener(new StatisticListener(this));
		frame.getSearchFrame().getVideoButton().addActionListener(new VideoListener(this));
		frame.getFavouritesFrame().getTextArea().addMouseListener(new FavouritesFrameListener(this));
		frame.getFavouritesFrame().getMapButton().addActionListener(new MapListener(this));
		frame.getJBFavourites().addActionListener(new SharknadoListener(this));
	}
	
	/**
	 * 	Compares the time of the ping within a month and put it into ArrayList as time order.
	 *	return the list with the shark sorted from the latest ping to the earliest ping within a month
	 *	return null when there are no shark that matches
	 * @param shark
	 * @return
	 */
	public Ping mostRecentPing(Shark shark){
		ArrayList<Ping> ping = jaws.pastMonth();
		ArrayList<Ping> selectedPing = new ArrayList();
		for(Ping p : ping){
			if(p.getName().equals(shark.getName())){
				selectedPing.add(p);
			}
		}
		Collections.sort(selectedPing,new PingComparator());
		for(Ping p : selectedPing){
			return p;
		}
		return null;
	}
	
	/**
	 * A method that enables the Favourite Button to be pressed if the FavouriteList is not empty
	 * If the FavouriteList is empty then the Favourite Button will not be able to press.
	 */
	public void checkList() {
		if(favouritesList.isEmpty()){
			frame.getJBFavourites().setEnabled(false);
			frame.getJBFavourites().addActionListener(new FavouritesListener(this));
		}
		else{
			frame.getJBFavourites().setEnabled(true);
			frame.getJBFavourites().addActionListener(new FavouritesListener(this));
		}
	}	
	
	/**
	 * If the sharkList is empty then disable the statisticButton
	 * and enable it when the sharkList is not empty
	 */
	public void checkSharkList() {
		if(sharkList.isEmpty()){
			frame.getSearchFrame().getStatisticButton().setEnabled(false);
		}
		else{
			frame.getSearchFrame().getStatisticButton().setEnabled(true);
		}
	}
	
	/**
	 * Update visitor's text file if the User did not log in.
	 * @throws IOException
	 */
	public void updateDefaultUser() throws IOException{
		user.updateTextFile(((DefaultUser) user).getFavouritesListFile(), favouritesList);
	}
	
	/**
	 * Update the LoggedinUser's text file
	 * @throws IOException
	 */
	public void updateLoggedinUser() throws IOException{
		user.updateTextFile(((LoggedinUser) user).getFavouritesListFile(), favouritesList);
	}
	
	/**
	 * UpdateFavouriteList, see if any shark is added or remove from Favourite and checkList.
	 * @param f
	 */
	public void updateFavouriteList(FavouritesList f){
		favouritesList = f;
		frame.getFavouritesFrame().addText(favouritesList.toString());
		checkList();
	}
	
	/**
	 * Update the load list displayed in the menu
	 * @param l
	 */
	public void updateLoadList(List<File> l){
		frame.addLoadList(l);
		List<JMenuItem> loadItemList = frame.getLoadItemList();
		for(JMenuItem item : loadItemList){
			item.addActionListener(new LoadListListener(this));
		}
	}
	
	/**
	 * Loading the new list created by the loggedinUser, updates the favouriteList
	 * then change the list name which will be display as the current List
	 * @param f
	 * @param listName
	 * @throws IOException
	 */
	public void loadNewListFile(File f, String listName) throws IOException{
		((LoggedinUser) user).newFileLoaded(f);
		updateFavouriteList(((LoggedinUser) user).getFavouritesList());
		frame.welcomeMessage(((LoggedinUser) user).getName(), listName);
	}
	
	/**
	 * Method used to check whether a shark is being followed or not
	 * @param shark
	 * @return true when following
	 * @return false when not following
	 */
	public boolean isFollowing(Shark shark){
		List<Shark> checkList = favouritesList.getList();
		for(Shark s : checkList){
			if (s.getName().equals(shark.getName())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * If the User is not logged in it will be a DefaultUser then updates the favourite for the DefaultUser
	 * It the User is logged in, update the LoggedinUser's favouriteList
	 * @param u
	 */
    public void assignUser(User u) {		
		if (u instanceof DefaultUser){
			user = (DefaultUser) u;
			isLoggedin = false;
			updateFavouriteList(((DefaultUser) u).getFavouritesList());
		}
		else{
			user = (LoggedinUser) u;
			isLoggedin = true;
			updateFavouriteList(((LoggedinUser) u).getFavouritesList());
		}
	}
	
    /**
	 * 
	 * @return whether User is logged in
	 */
	public boolean isLoggedin(){
		return isLoggedin;
	}
	
	/**
	 * 
	 * @return jaws from api
	 */
	public Jaws getJaws() {
		return jaws;
	}
	
	/**
	 * 
	 * @return the User
	 */
	public User getUser(){
		return user;
	}
	
	/**
	 * 
	 * @return the MainFrame
	 */
	public MainFrame getMainFrame() {
		return frame;
	}	
	
	/**
	 * 
	 * @return the list of favourite sharks
	 */
	public FavouritesList getFavouritesList(){		
		return favouritesList;
	}
	
	/**
	 * 
	 * @return the list of shark which we searched for
	 */
	public ArrayList <Shark> getSharkList() {
		return sharkList;
	}

	/**
	 * 
	 * @return the map
	 */
	public Map getMap() {
		return map;
	}
	
}
