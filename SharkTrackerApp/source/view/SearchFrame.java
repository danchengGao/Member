package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import api.jaws.Jaws;

public class SearchFrame extends JFrame {
	
	private Jaws jaws;
	private List<SharkPanel> sharkPanelList = new ArrayList();
	
	private String[] trackingRange = {"Last 24 Hours", "Last Week", "Last Month"};
	private String[] gender = {"All", "Male", "Female"};
	private String[] stageOfLife = { "All", "Mature", "Immature", "Undetermined"};
	private String[] location = { "All"};	
	
	private JPanel infoPanel = new JPanel();
	private JPanel searchPanel = new JPanel();
	private JPanel acknowledgementPanel = new JPanel();
	
	private JComboBox JCBtrackingRange;
	private JComboBox JCBgender;
	private JComboBox JCBsol;
	private JComboBox JCBlocation;
	
    private JButton search;
    private JButton statistic;
    private JButton video;
    
    private SharkPanel sharkPanel;
	
	private JLabel acknowledgement;
	/**
	 * Constructor for SearchFrame which takes mainframe as parameter
	 * @param main 
	 */
	public SearchFrame(MainFrame main) {
			super("Search");
			this.setSize(1200, 775);
			
			jaws = main.getJaws();
			
			this.createWidgets();
			this.setMiddle();
	}
	/**
	 * Create all the frames and Panels and set layouts...
	 */
	private void createWidgets() {      
		
		search = new JButton("Search");
		statistic = new JButton("Statistic");
		video = new JButton("Shark of The Day");

		JScrollPane JSPinfo = new JScrollPane(infoPanel);
		JSPinfo.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		JSPinfo.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JSPinfo.setBorder(new EmptyBorder(5, 5, 5, 5));
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, searchPanel, JSPinfo);
		splitPane.setDividerLocation(300);
		
		acknowledgement = new JLabel(jaws.getAcknowledgement());	
		acknowledgementPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		acknowledgementPanel.add(acknowledgement);
		
		this.setLayout(new BorderLayout());
		this.add(acknowledgementPanel, BorderLayout.SOUTH);
		this.add(splitPane, BorderLayout.CENTER);
		
		searchPanel.setLayout(new BorderLayout());
		searchPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		JPanel upperPanel = new JPanel();
		upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.Y_AXIS));
		JPanel lowerPanel = new JPanel();
		lowerPanel.setLayout(new BorderLayout());
		searchPanel.add(upperPanel, BorderLayout.NORTH);
		searchPanel.add(lowerPanel, BorderLayout.SOUTH);
		
		JLabel JLTitle = new JLabel("Shark Tracker");		
		
		JPanel JP1 = new JPanel();
		JP1.setLayout(new BoxLayout(JP1, BoxLayout.Y_AXIS));
		JLabel JLTrackingRange = new JLabel("Tracking Range");
		JCBtrackingRange = new JComboBox(trackingRange);
		JLTrackingRange.setAlignmentX(LEFT_ALIGNMENT);
		JCBtrackingRange.setAlignmentX(LEFT_ALIGNMENT);
		JP1.add(JLTrackingRange);
		JP1.add(JCBtrackingRange);		
		
		JPanel JP2 = new JPanel();
		JP2.setLayout(new BoxLayout(JP2, BoxLayout.Y_AXIS));
		JLabel JLGender = new JLabel("Gender");
		JCBgender = new JComboBox(gender);
		JLGender.setAlignmentX(LEFT_ALIGNMENT);
		JCBgender.setAlignmentX(LEFT_ALIGNMENT);
		JP2.add(JLGender);
		JP2.add(JCBgender);		
		
		JPanel JP3 = new JPanel();
		JP3.setLayout(new BoxLayout(JP3, BoxLayout.Y_AXIS));
		JLabel JLStageOfLife = new JLabel("Stage of Life");
		JCBsol = new JComboBox(stageOfLife);
		JLStageOfLife.setAlignmentX(LEFT_ALIGNMENT);
		JCBsol.setAlignmentX(LEFT_ALIGNMENT);
		JP3.add(JLStageOfLife);
		JP3.add(JCBsol);		
		
		JPanel JP4 = new JPanel();
		JP4.setLayout(new BoxLayout(JP4, BoxLayout.Y_AXIS));
		JLabel JLTagLocation = new JLabel("Tag Location");
		ArrayList<String> locationList = jaws.getTagLocations();locationList.add(0,"All");
		location = locationList.toArray(new String[0]);
		JCBlocation = new JComboBox(location);
		JLTagLocation.setAlignmentX(LEFT_ALIGNMENT);
		JCBlocation.setAlignmentX(LEFT_ALIGNMENT);
		JP4.add(JLTagLocation);
		JP4.add(JCBlocation);		
	
		search.setMaximumSize(new Dimension(300,0));
		statistic.setMaximumSize(new Dimension(300,0));
		video.setMaximumSize(new Dimension(300,0));
		
		upperPanel.add(JLTitle);
		upperPanel.add(Box.createRigidArea(new Dimension(0,15)));
		upperPanel.add(JP1);
		upperPanel.add(Box.createRigidArea(new Dimension(0,15)));
		upperPanel.add(JP2);
		upperPanel.add(Box.createRigidArea(new Dimension(0,15)));
		upperPanel.add(JP3);
		upperPanel.add(Box.createRigidArea(new Dimension(0,15)));
		upperPanel.add(JP4);
		upperPanel.add(Box.createRigidArea(new Dimension(0,15)));
		upperPanel.add(search);	
		upperPanel.add(Box.createRigidArea(new Dimension(0,5)));
		upperPanel.add(statistic);
		upperPanel.add(Box.createRigidArea(new Dimension(0,5)));
		upperPanel.add(video);
		
		ImageIcon image = new ImageIcon("library/images/shark.png");	
		JLabel imageLabel = new JLabel(image);	
		lowerPanel.add(imageLabel, BorderLayout.SOUTH);
		
		GridLayout infoPanelLayout = new GridLayout(0,1);
		infoPanel.setLayout(infoPanelLayout);
	}
	/**
	 * 
	 * @return JCBtrackingRange
	 */
	public JComboBox getTrackingRangeBox(){
		return JCBtrackingRange;
	}
	/**
	 * 
	 * @return JCBgender
	 */
	public JComboBox getGenderBox(){
		return JCBgender;
	}
	/**
	 * 
	 * @return JCB of Stage of Life
	 */
	public JComboBox getStageOfLifeBox(){
    	return JCBsol;
	}
	/**
	 * 
	 * @return JCB for TagLocation
	 */
	public JComboBox getTagLocationBox(){
		return JCBlocation;
	}
	/**
	 * 
	 * @return search Button
	 */
	public JButton getSearchButton(){
		return search;
	}
	/**
	 * 
	 * @return statistic button
	 */
	public JButton getStatisticButton() {
		return statistic;
	}
	/**
	 * 
	 * @return video button
	 */
	public JButton getVideoButton() {
		return video;
	}
	/**
	 * 
	 * @return jaws
	 */
	public Jaws getJaws(){
		return jaws;
	}
	/**
	 * Set the Frame at the middle of the screen
	 */
    public void setMiddle() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
 		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
 	}
	/**
	 * 
	 * @return list of shark panel
	 */
    public List<SharkPanel> getSharkPanels(){
		return sharkPanelList;
	}
    /**
     * method to add shark panel into infopanel and add it into sharkpanel list
     * @param SP
     */
    public void addSharkPanel(SharkPanel SP){
       	sharkPanel = SP;
        infoPanel.add(sharkPanel);
        infoPanel.updateUI();
        sharkPanelList.add(sharkPanel);
    }	
	/**
	 * JLabel which says no result add the label in and update the UI
	 */
	public void noResult(){
		JLabel label = new JLabel("No results");
		infoPanel.add(label);
	    infoPanel.updateUI();
	}
	/**
	 * Clear the result added in the infopanel
	 */
	public void clearResult(){
    	infoPanel.removeAll();
		infoPanel.updateUI();
		sharkPanelList.clear();
	}
	/**
	 * Create Error Frame
	 * @param text
	 */
	public void alert(String text){
		JOptionPane alert = new JOptionPane();
		alert.showMessageDialog(this, text);
	}
}

