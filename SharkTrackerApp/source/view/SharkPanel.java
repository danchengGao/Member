package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import api.jaws.Ping;
import api.jaws.Shark;

public class SharkPanel extends JPanel{
	
	private Shark shark;
	private Ping ping;
	private boolean isFollowing;
	
	private JLabel name = new JLabel("Name");
	private JLabel sname = new JLabel();
	private JLabel gender = new JLabel("Gender");
	private JLabel sgender = new JLabel();
	private JLabel stage = new JLabel("Stage of Life");
	private JLabel sstage = new JLabel();
	private JLabel species = new JLabel("Species");
	private JLabel sspecies = new JLabel();
	private JLabel length = new JLabel("Length");
	private JLabel slength = new JLabel();
	private JLabel weight = new JLabel("Weight");
	private JLabel sweight = new JLabel();
	private JLabel description = new JLabel("Description");
	private JTextArea sdescription = new JTextArea();
	private JLabel lastPing = new JLabel();
	
	private JButton follow = new JButton();
	
	private JPanel north = new JPanel();
	private JPanel centre = new JPanel();
	private JPanel south = new JPanel();
	private JPanel south_follow = new JPanel();
	/**
	 * Construct a SharkPanel
	 * @param s
	 * @param p
	 * @param b
	 */
	public SharkPanel(Shark s, Ping p, boolean b){
		shark = s;
		ping = p;
		isFollowing = b;
		setButtonText();
		configure();
	}
	/**
	 * Settings for the new SharkPanel
	 */
	public void configure(){
		this.setLayout(new BorderLayout());
		this.add(north,BorderLayout.NORTH);
		this.add(south,BorderLayout.SOUTH);
		this.add(centre,BorderLayout.CENTER);
		
		sname.setText(shark.getName());
		sgender.setText(shark.getGender());
		sstage.setText(shark.getStageOfLife());
		sspecies.setText(shark.getSpecies());
		slength.setText(shark.getLength());
		sweight.setText(shark.getWeight());
		sdescription.setText(shark.getDescription());
		lastPing.setText("Last ping: " + ping.getTime());
		
		north.setLayout(new GridLayout(6,2));
		north.add(name);north.add(sname);
		north.add(gender);north.add(sgender);
		north.add(stage);north.add(sstage);
		north.add(species);north.add(sspecies);
		north.add(length);north.add(slength);
		north.add(weight);north.add(sweight);
		
		south.setLayout(new BorderLayout());
		south.add(lastPing, BorderLayout.WEST);
		south.add(south_follow, BorderLayout.EAST);
		south_follow.add(follow);
		
		centre.setLayout(new GridLayout(2,1));
		centre.add(description);
		centre.add(sdescription);
		sdescription.setLineWrap(true);
		sdescription.setEditable(false);
		sdescription.setBackground(description.getBackground());
		
		this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));		
	}	
	/**
	 * Method to set the text of a button
	 */
	public void setButtonText(){
		if (isFollowing == true){
			follow.setText("Following");
		}
		else{
			follow.setText("Follow");
		}
	}

	/**
	 * 
	 * @return the follow button
	 */
	public JButton getFollowButton(){
		return follow;
	}
	/**
	 * 
	 * @return shark
	 */
	public Shark getShark(){
		return shark;
	}
}
