package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import api.jaws.Jaws;

public class LoggedinUser extends User{

	private Jaws jaws;
	private String name;
	private FavouritesList currentFavouritesList;
	private File favouritesListFile;
	private String fileContent;
	private List<File> fileList = new ArrayList();
	
	/**
	 * Create a new user that does not exist.
	 * @param string user name.
	 * @param jaws.
	 * @throws IOException
	 */
	public LoggedinUser(String n, Jaws j) throws IOException{
		jaws = j;
		name = n;
		currentFavouritesList = new FavouritesList(jaws);
		File f = new File("source/data/Users");
		String fileDir = f.getPath();
		fileDir = fileDir + "/" + name;
		File createDir = new File(fileDir);
		createDir.mkdir();
		favouritesListFile = new File("source/data/Users/" + name, "Default.txt");		
		favouritesListFile.createNewFile();
		fileList.add(favouritesListFile);
	}
	
	/** 
	 * Create an existed user.
	 * @param string user name.
	 * @param file. The user's stored favourite list file.
	 * @param jaws.
	 * @throws IOException
	 */
	public LoggedinUser(String n, File f, Jaws j) throws IOException{
		jaws = j;
		name = n;
		currentFavouritesList = new FavouritesList(jaws);
		fileContent = readFile(f);
		currentFavouritesList.stringToList(fileContent);
		favouritesListFile = f;
		findFiles();
	}
	
	/**
	 * Update the favourite list associated with the user by a given favourite list.
	 * @param FavouritesList.
	 */
	public void updateList(FavouritesList l){
		currentFavouritesList = l;
	}
	
	/**
	 * Convert the content of a given file to a favourite list and update the user's favourite list with this list.
	 * Also update the user's file with this file.
	 * @param file.
	 * @throws IOException
	 */
	public void newFileLoaded(File f) throws IOException{
		fileContent = readFile(f);
		currentFavouritesList.stringToList(fileContent);
		favouritesListFile = f;
	}
	
	/**
	 * Create a new favourite list for the user, and create its storing text file.
	 * @param fileName
	 * @throws IOException
	 */
	public void createNewList(String fileName) throws IOException{
		favouritesListFile = new File("source/data/Users/" + name, fileName + ".txt");		
		favouritesListFile.createNewFile();
		fileList.add(favouritesListFile);
		currentFavouritesList.clearList();
	}
	
	/**
	 * Look for all the favourite list files that belong to the user and add them to a list.
	 */
	public void findFiles(){
		String path = "source/data/Users/" + name;
		File f = new File(path);
		File[] tempList = f.listFiles();
		for (int i = 0; i < tempList.length; i++){
			fileList.add(tempList[i]);
		}
	}
	
	/**
	 * Get the favourite list associated with the user.
	 * @return FavouritesList.
	 */
	public FavouritesList getFavouritesList(){
		return currentFavouritesList;	
	}
	
	/**
	 * Get the file that stores the list.
	 * @return file.
	 */
	public File getFavouritesListFile(){	
		return favouritesListFile;
	}	
	
	/**
	 * Get the list that stores the user's favourite list files.
	 * @return list of files.
	 */
	public List<File> getFileList(){
		return fileList;
	}
	
	/**
	 * Get the user name.
	 * @return String name.
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * When printing the user, his name is printed.
	 */
	public String toString(){
		return name;		
	}
}
