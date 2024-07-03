import java.util.List;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import java.awt.BorderLayout;

/**
 * Created by Dancheng Gao on 09/02/2016.
 */

public class Camera extends JPanel{	

	private List<Integer> input = new ArrayList<Integer>();
	private JSlider slider;    
    
	public Camera() {                
		slider();
		this.setLayout(new BorderLayout());
		this.add(slider,BorderLayout.CENTER);
    }
	
    public void slider(){
    	slider = new JSlider(JSlider.HORIZONTAL,0,15,0);
    	slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);  
        slider.setLabelTable(createLabelTable());
        slider.setPaintLabels(true);
    }
    
    public Hashtable<Integer,JLabel> createLabelTable(){
    	Hashtable<Integer,JLabel> labelTable = new Hashtable<Integer, JLabel>();
        for(int c = 0; c < 10; c++){
            labelTable.put(new Integer(c), new JLabel(Integer.toString(c)));
        }        
        for(int a = 10; a < 16; a++){
        	int ascIINumber = a + 55;
        	String character = Character.toString((char)ascIINumber);
        	labelTable.put(new Integer(a), new JLabel(character));        	
        }         
        return labelTable;
    }
    
    public void acceptPosition(List<Integer> userInput) throws InterruptedException {
    	input = userInput;
    	for(Integer value : input){
    		this.slider.setValue(value);
    		Thread.sleep(1500);
    	}
    }    
}
