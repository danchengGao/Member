package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import api.jaws.Jaws;
import api.jaws.Ping;

import view.VideoFrame;

public class VideoListener implements ActionListener{

	private Controller controller;
	private Jaws jaws;
	private ArrayList <Ping> pingList;
	private ArrayList <String> sharkName = new ArrayList <String>();
	private File file = new File("source/data/Date.txt");
	private File sharkFile = new File("source/data/Shark.txt");
	private FileWriter fw;
	private BufferedWriter bw;
	private BufferedReader bfReader;
	
	private String dateInText;
	private String date;
	private String shark;
	
	/**
	 * Constructor which take a controller as its perameter. 
	 *	Construct an actionListener which run actionPerformed code when the assigned button is presses
	 * @param controller
	 */
	public VideoListener(Controller controller) {
		this.controller = controller;
		this.jaws = controller.getJaws();
		pingList = jaws.pastMonth();
		
		ArrayList <String> existedName = new ArrayList();
		for(Ping p : pingList){
			String name = p.getName();
			if(!existedName.contains(name)){
				existedName.add(name);
			}
		}
		sharkName = existedName;
	}		
	
	/**
	 * Method which read the file "Date.txt" and "Shark.txt"
	 *	if the date is the same day as the current computer's date, then get the shark from "Shark.txt" and display it 
	 *	if not, edit the "Date.txt" and insert todays day and get a random new shark and store it in "Shark.txt"
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		VideoFrame VF = new VideoFrame();
		Random random = new Random();
	 
		int number = random.nextInt(sharkName.size());
		
	    String link = jaws.getVideo(pingList.get(number).getName());

	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date date = new Date();
	    dateInText = dateFormat.format(date); //PCs Date
	    
	    String datePath = file.getAbsolutePath();
	    try (BufferedReader br = new BufferedReader(new FileReader(datePath))) {
		this.date = br.readLine();
	    } catch (FileNotFoundException e2) {
			System.out.println("File not found");
		} catch (IOException e2) {
			System.out.println("File not found");
		}
	    
	    String sharkPath = sharkFile.getAbsolutePath();
	    try (BufferedReader br = new BufferedReader(new FileReader(sharkPath))) {
		this.shark = br.readLine();
	    } catch (FileNotFoundException e2) {
			System.out.println("File not found");
		} catch (IOException e2) {
			System.out.println("File not found");
		}	    
	    
	    if(!this.date.equals(dateInText) ) {
			this.shark = pingList.get(number).getName();
			try {
				fw = new FileWriter(file);
				bw = new BufferedWriter(fw);
	    		bfReader = new BufferedReader(new FileReader(file));
	    		bw.write(dateInText);
	    		bw.close();
	    		
	    		fw = new FileWriter(sharkFile);
	    		bw = new BufferedWriter(fw);
	    		bfReader = new BufferedReader(new FileReader(sharkFile));
	    		bw.write(this.shark);
	    		bw.close();

			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    }
	    VF.setName(shark);
		VF.setLink(link);	    
	}
}
