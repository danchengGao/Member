package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A parent class for DefaultUser and LoggedinUser.
 */
public class User {
	
	/**
	 * A method to read the given file, to get the file content.
	 * @param file.
	 * @return String.
	 * @throws IOException
	 */
	public String readFile(File f) throws IOException{
		BufferedReader bfReader = new BufferedReader(new FileReader(f));
		String content = "";
		StringBuilder stringBuilder = new StringBuilder();
		while(content != null){
			content = bfReader.readLine();
			if(content == null){
				break;
		    }
			stringBuilder.append(content.trim());
		}
		bfReader.close();
		return stringBuilder.toString();
	}
	
	/**
	 * Update the given text file by the given favourite list.
	 * @param favouritesListFile
	 * @param favouritesList
	 * @throws IOException
	 */
	public void updateTextFile(File favouritesListFile, FavouritesList favouritesList) throws IOException{
		FileWriter fileWritter = new FileWriter(favouritesListFile);
        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
        bufferWritter.write(favouritesList.toString());
        bufferWritter.close();
	}
}
