import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class SendListener implements ActionListener{
	
	private Communication communication;
	private List<Integer> inputList;
	private Runnable sliderMover;
	
	public SendListener(Communication c){
		communication = c;			
	}
	
	public void actionPerformed(ActionEvent event) {		
		sliderMover = new Runnable(){
			public void run(){
				try {
					communication.setTitle("Sending...");					
					
					try {
						inputList = createInputList();
						communication.getTextArea().setText(null);
						communication.updateCamera(inputList);
					} 
					
					catch (IOException e) {
						e.printStackTrace();
					}					
					
					communication.setTitle("Communication Window");
					
				} 
				
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		new Thread (sliderMover).start();
	}
	
	public List<Integer> createInputList() throws IOException {
		char[] textCharacters = communication.getTextArea().getText().toCharArray();
		inputList = new ArrayList<Integer>();
		int a;
				   	     
		for(char character : textCharacters){ 
		  	BufferedReader reader = new BufferedReader(new FileReader("ascii_table.csv")); 
	        reader.readLine();			    				
	        String line = null;
		   
	        while((line=reader.readLine())!=null){ 
		        String[] row= line.split(",");			            
		        
		        if (Character.toString(character).equals(row[4])){
		          	char[] hexValue = row[2].toCharArray();
		           	
		          	for(char hexCharacter : hexValue){
		           	
		          		if(!Character.isDigit(hexCharacter)){		            			
		           			a = charToNumber(hexCharacter);
		           		}
		           		else{
		           			a = hexCharacter - 48;
		           		}
		           		
		          		inputList.add(a);		            		
		           	}
		           	
		          	break;
		        }
		        
		    }
		    
	        reader.close();		    
		}	
		
		return inputList;		
	}
	
	public int charToNumber(char hex){
		int integer = 0;
		if(hex == 'A'){
			integer = 10;
		}
		if(hex == 'B'){
			integer = 11;
		}
		if(hex == 'C'){
			integer = 12;
		}
		if(hex == 'D'){
			integer = 13;
		}
		if(hex == 'E'){
			integer = 14;
		}
		if(hex == 'F'){
			integer = 15;
		}
		return integer;
	}
	
}
