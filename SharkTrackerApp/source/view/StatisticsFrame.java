package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public class StatisticsFrame extends JFrame {
	
	private JPanel left;
	private JPanel mid;
	private JPanel right;	
	/**
	 * Method to create a statistic frame
	 */
	public StatisticsFrame() {
		super("Statistics");
		this.setVisible(true);
		this.setSize(1300,350);
		this.setMiddle();
		createWidgets();		
	}
	/**
	 * Settings for the statistic frame
	 */
	public void createWidgets() {
		
		this.setLayout(new GridLayout(1,3));
		
		left = new JPanel();
		mid = new JPanel();
		right = new JPanel();
		
		left.setLayout(new BorderLayout());
		mid.setLayout(new BorderLayout());
		right.setLayout(new BorderLayout());
		
	}
	/**
	 * Method to set the Frame in the middle of the screen
	 */
	public void setMiddle() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
	/**
	 * adding genderCP into chartpanel
	 * @param genderChart
	 */
	public void setGenderChart(JFreeChart genderChart) {
		 ChartPanel genderCP = new ChartPanel(genderChart);
		 left.add(genderCP, BorderLayout.CENTER);
		 this.add(left);
	}
	/**
	 * adding StageCP into chartpanel
	 * @param stageChart
	 */
	public void setStageChart(JFreeChart stageChart) {
		ChartPanel stageCP = new ChartPanel(stageChart);
	    mid.add(stageCP, BorderLayout.CENTER);
		this.add(mid);
	}
	/**
	 * adding locationCP into chartpanel
	 * @param locationChart
	 */
	public void setLocationChart(JFreeChart locationChart) {
		ChartPanel locationCP = new ChartPanel(locationChart);
	    right.add(locationCP, BorderLayout.CENTER);
		this.add(right);
	}	
}
