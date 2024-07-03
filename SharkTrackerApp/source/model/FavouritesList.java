package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import api.jaws.Jaws;
import api.jaws.Shark;

public class FavouritesList {
	
	private Jaws jaws;
	private List<Shark> list;
	private int favouritesNumber;
	
	/**
	 * Construct a favourite list to store followed sharks.
	 * @param jaws.
	 */
	public FavouritesList(Jaws j){		
		jaws = j;
		list = new ArrayList();
		favouritesNumber = 0;
	}
	
	/**
	 * Add a shark to the list.
	 * @param shark.
	 */
	public void addShark(Shark s){
		list.add(s);
		favouritesNumber ++ ;
	}
	
	/**
	 * Remove a shark from the list.
	 * @param shark.
	 */
	public void removeShark(Shark s){
		int index = 0;
		for(Shark shark : list){
			if (shark.getName().equals(s.getName())){
				index = list.indexOf(shark);
				break;
			}
		}
		list.remove(index);
		favouritesNumber -- ;
	}
	
	/**
	 * Convert a given string to a list of sharks.
	 * This method is used when loading a local text file that stores the favourite sharks.
	 * The content of the file can be converted to a list of favourite sharks.
	 * @param string.
	 */
	public void stringToList(String s){
		list.clear();
		List<String> nameList = new ArrayList();
		Pattern pattern = Pattern.compile("([A-Z][a-z]*\\s?[A-Z]*[a-z]*)(\\:)");
	    Matcher matcher = pattern.matcher(s);
	    while(matcher.find()){
		    nameList.add(matcher.group(1)); 			
        }
	   for(String name : nameList){
	     	list.add(jaws.getShark(name));
		   	favouritesNumber ++ ;
       }		
	}
	
	/**
	 * Clear the list.
	 */
	public void clearList(){
		list.clear();
		favouritesNumber = 0;
	}
	
	/**
	 * Return true if the list is empty. Otherwise return false.
	 * @return true or false.
	 */
	public boolean isEmpty(){
		return list.isEmpty();
	}
	
	/**
	 * Return the number of favourite sharks stored in the list.
	 * @return favouritesNumber.
	 */
	public int getFavouritesNumber(){
		return favouritesNumber;
	}
	
	/**
	 * Get the shark according to its index in the list.
	 * @param an integer.
	 * @return the selected shark.
	 */
	public Shark getShark(int n){
		return list.get(n);
	}
	
	/**
	 * Get the list that stores the favourite sharks.
	 * @return the list.
	 */
	public List getList(){
		return list;
	}
	
	/**
	 * When printing the favourite list, the names of the sharks in the list and their distance from king's is printed.
	 */
	public String toString(){
		String string = "";
		Collections.sort(list,new DistanceComparator(jaws));
		for (Shark shark : list){
			SharkModel sharkModel = new SharkModel(shark,jaws);
			string = string + sharkModel.getName() + ": " + sharkModel.getDistanceFromKings() + " km.\n";
		}
		return string;
	}
}
