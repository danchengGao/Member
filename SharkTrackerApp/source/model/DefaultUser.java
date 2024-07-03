package model;

import java.io.File;
import java.io.IOException;

import api.jaws.Jaws;

public class DefaultUser extends User{

	private Jaws jaws;
	private FavouritesList favouritesList;
	private String fileContent ;
	private File favouritesListFile = new File("source/data/Users", "DefaultUser.txt");;
	
	/** 
	 * @param jaws.
	 * The constructor. Construct a default user with favouritesListFile stored.
	 * Fill the favouritesList according to the file content.
	 */
	public DefaultUser(Jaws j) {
		jaws = j;
		try {
			favouritesList = new FavouritesList(jaws);
			fileContent = readFile(favouritesListFile);
			favouritesList.stringToList(fileContent);
		} 
		catch (IOException e) {
			try {
				favouritesListFile.createNewFile();
			} 
			catch (IOException e1) {}
		}
	}
	
	/**
	 * @param FavouritesList.
	 * Copy the content from a selected favourite list to the favourite list associated with the default user.
	 */
	public void updateList(FavouritesList f){
		favouritesList = f;
	}
	
	/**
	 * Get the current favourite list of the default user.
	 * @return favouritesList.
	 */
	public FavouritesList getFavouritesList(){
		return favouritesList;	
	}
	
	/**
	 * Get the text file that is used to store default user's favourite list.
	 * @return favouritesListFile.
	 */
	public File getFavouritesListFile(){
		return favouritesListFile;	
	}
    
	/**
	 * return "DefaultUser" when asked to print the default user.
	 */
	public String toString(){
		return "DefaultUser";		
	}
}
