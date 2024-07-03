
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import api.jaws.Shark;
import view.StatisticsFrame;

public class StatisticListener implements ActionListener {

	private Controller controller;
	private StatisticsFrame SF;
	private ArrayList <Shark> sharkList;
	private int male;
	private int female;
	private int mature, immature, undetermined;
	private Hashtable<String,Integer> checkList;
	
	/**
	 * Constructor which take a controller as its perameter. 
	 *	Construct an actionListener which run actionPerformed code when the assigned button is presses
	 * @param controller
	 */
	public StatisticListener(Controller controller) {
		this.controller = controller;
		this.sharkList = controller.getSharkList();		
	}
	
	/**
	 * Method which loop though the sharkList in the controller and if the shark is a male, male will increase by one 
	 *	same logic apply to female, mature, immature and undetermined
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		SF = new StatisticsFrame();
		male = 0;
		female = 0;
		mature = 0;
		immature = 0;
		undetermined = 0;
		
		for (int i = 0; i<sharkList.size(); i++) {
			String sharkGender = sharkList.get(i).getGender();
			String sharkStage = sharkList.get(i).getStageOfLife();
			
			if (sharkGender.equals("Male")) {
				male++;
			} else if (sharkGender.equals("Female")) {
				female++;
			}
			if (sharkStage.equals("Mature")) {
				mature++;
			} else if (sharkStage.equals("Immature")) {
				immature++;
			}else if (sharkStage.equals("Undetermined")) {
				undetermined++;
			}
		}
		
		JFreeChart genderChart = ChartFactory.createPieChart3D("Gender", creategenderDataset());
		SF.setGenderChart(genderChart);
	    JFreeChart stageChart = ChartFactory.createPieChart3D("Stage Of Life", createStageOfLifeDataset());
		SF.setStageChart(stageChart);
	    JFreeChart locationChart = ChartFactory.createPieChart3D("Location", createLocationDataset());
		SF.setLocationChart(locationChart);
		
	}
	/**
	 * Create PieDataset using male and female 
	 * @return
	 */
	private PieDataset creategenderDataset() {
		
		DefaultPieDataset dataset = new DefaultPieDataset( );
		if (male != 0 ) {
			dataset.setValue("Male", male);
		}
		if (female != 0 ) {
		    dataset.setValue( "Female" , female ); 
		}
	    return dataset;         
}
	
	/**
	 * Create PieDataset using mature, immature and undetermined 
	 * @return
	 */
	private PieDataset createStageOfLifeDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset( );
		if (mature != 0 ) {
			dataset.setValue("Mature", mature);
		}
		
		if (immature != 0 ) {
		    dataset.setValue( "Immature" , immature ); 
		}
		
		if (undetermined != 0 ) {
		    dataset.setValue( "Undetermined" , undetermined ); 
		}
	    return dataset;      
	}
	
	/**
	 * Create PieDataset using tag location
	 * @return
	 */
	private PieDataset createLocationDataset() {
		checkList = new Hashtable<String,Integer>();
		DefaultPieDataset dataset = new DefaultPieDataset( );
		
		for(int i = 0; i<sharkList.size(); i++){
			String location = sharkList.get(i).getTagLocation();
			
			if(checkList.containsKey(location)){
				int amount= checkList.get(location);
				checkList.replace(location, ++amount);
			}else{
				checkList.put(location, 1);
			}
		}
		
		for(Map.Entry<String,Integer> entry: checkList.entrySet()) {
			String key = entry.getKey();
			int amount = entry.getValue();
			
			dataset.setValue(key,amount); 
		}
	    return dataset;
	}
}
