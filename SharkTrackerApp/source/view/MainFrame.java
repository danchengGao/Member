package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import api.jaws.Jaws;

public class MainFrame extends JFrame  {
	
	private Jaws jaws = new Jaws("6Ucs9k469jmosxNE", "C0VsY9JaHEjPTpbc");
	
	private SearchFrame searchFrame;
	private FavouritesFrame favouritesFrame;
	private LoginFrame loginFrame;
    private NewUserFrame newUserFrame;
    private CreateListFrame createListFrame;
	
	private JButton JBSearch;
	private JButton JBFavourites;
	private ImageIcon image;
	private JLabel imageLabel;
	private JLabel welcome;
	private JPanel JPButtons;
	private JPanel welcomePanel;	
	
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenu favourites;
	private JMenu load;
	private JMenuItem create;
	private JMenuItem store;
	private JMenuItem newUser;
	private JMenuItem login;
	
	private List<JMenuItem> loadItemList = new ArrayList();
	
	
	/**
	 * Constructor for the frame.
	 */
	public MainFrame() {
		super("Anmity Police");
		
		this.setSize(400, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		createWidgets();
		createSearchFrame();
		createFavouritesFrame();
		createLoginFrame();
		createNewUserFrame();
		createCreateListFrame();
		
		this.setVisible(true);
		this.setResizable(false);
		this.setMiddle();
	}

	/**
	 * Method that creates all the components in the frame.
	 */
	private void createWidgets() {
		
		welcome = new JLabel("Initialising...");
		welcomePanel = new JPanel();
		welcomePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		welcomePanel.add(welcome);
		
		JBSearch = new JButton("Search");
		JBFavourites = new JButton("Favourites");
		
		JPButtons = new JPanel();
		JPButtons.setLayout(new GridLayout(3,1));
		
		this.setLayout(new BorderLayout());
		
		Border border = BorderFactory.createEmptyBorder(5,35,10,35);
		JPButtons.setBorder(BorderFactory.createCompoundBorder(border, border));
		
		JPButtons.add(welcomePanel);
		JPButtons.add(JBSearch);
		JPButtons.add(JBFavourites);
		
        image = new ImageIcon("library/images/shark.png");
		imageLabel = new JLabel(image);					
		
		this.add(JPButtons,BorderLayout.SOUTH);
		this.add(imageLabel,BorderLayout.CENTER);
		
		menuBar = new JMenuBar();
		menu = new JMenu("Menu");
		favourites = new JMenu("Favourites Lists");
		load = new JMenu("Load");
		create = new JMenuItem("Create A New List");
		store = new JMenuItem("Store Current List");
		login = new JMenuItem("Login");
		newUser = new JMenuItem("Create New User");
		menu.add(login);
		menu.add(newUser);
		favourites.add(load);
		favourites.add(create);
		favourites.add(store);
		menuBar.add(menu);
		menuBar.add(favourites);
		enableMenu(false);
		
		this.add(menuBar,BorderLayout.NORTH);
		this.add(JPButtons,BorderLayout.SOUTH);
		this.add(imageLabel,BorderLayout.CENTER);		
	}
	
	/**
	 * Method that creates a JFrame called searchFrame which it is not visible.
	 */
	public void createSearchFrame() {
		searchFrame = new SearchFrame(this);
		searchFrame.setVisible(false);
	}
	
	/**
	 * Method that creates a JFrame called favouritesFrame which it is not visible.
	 */
	public void createFavouritesFrame() {
		favouritesFrame = new FavouritesFrame();
		favouritesFrame.setVisible(false);
	}
	
	/**
	 * Method that creates a JFrame called loginFrame which it is not visible.
	 */
	public void createLoginFrame() {
		loginFrame = new LoginFrame();
		loginFrame.setVisible(false);
	}
	
	/**
	 * Method that creates a JFrame called newUserFrame which it is not visible.
	 */
	public void createNewUserFrame() {
		newUserFrame = new NewUserFrame();
		newUserFrame.setVisible(false);
	}
	
	/**
	 * Method that creates a JFrame called createListFrame which it is not visible.
	 */
	public void createCreateListFrame() {
		createListFrame = new CreateListFrame();
		createListFrame.setVisible(false);
	}
	
	/**
	 * Method that sets the frame in the middle when you run program.
	 */
	public void setMiddle() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
	
	/**
	 * Method that sets the welcome message, if the username is not empty then the message will show the username and the list of the user.
	 * @param username String
	 * @param listName String
	 */
	public void welcomeMessage(String username, String listName){
		if(!listName.equals("")){
			welcome.setText("Welcome " + username + "! (Current list : " + listName + ")");
		}
		else{
			welcome.setText("Welcome " + username + "!");
		}
		welcomePanel.updateUI();
	}
	
	/**
	 * Method that shows the favourite lists of the user when "Load" in the menuBar is selected.
	 * @param l List<File>
	 */
	public void addLoadList(List<File> l){
		load.removeAll();
		loadItemList.clear();
		String fileName;
		for(File file : l){
			fileName = file.getName();
			fileName = fileName.replace(".txt", "");
			JMenuItem item = new JMenuItem(fileName);
			load.add(item);
			loadItemList.add(item);
		}
		menuBar.updateUI();
	}
	
	/**
	 * Method that creates an alert window.
	 * @param text the message for the user.
	 */ 
	public void alert(String text){
		JOptionPane alert = new JOptionPane();
		alert.showMessageDialog(this, text);
	}
	
	/**
	 * Method that enable or disable the Favourites option in the menuBar.
	 * @param b boolean
	 */
	public void enableMenu(boolean b){
		favourites.setEnabled(b);
	}
	
	/**
	 * get method for JMenuItem.
	 * @return JMenuItem login
	 */
	public JMenuItem getLoginItem() {
		return login;
	}
	
	/**
	 * get method for JMenuItem.
	 * @return JMenuItem newUser
	 */
	public JMenuItem getNewUserItem() {
		return newUser;
	}
	
	/**
	 * get method for JMenuItem.
	 * @return JMenuItem store
	 */
	public JMenuItem getStoreItem() {
		return store;
	}
	
	/**
	 * get method for JMenuItem.
	 * @return JMenuItem create
	 */
	public JMenuItem getCreateItem() {
		return create;
	}
	
	/**
	 * get method for JButton.
	 * @return JButton JBSearch
	 */
	public JButton getSearchButton() {
		return JBSearch;
	}
	
	/**
	 * get method for JButton.
	 * @return JButton JBFavourites
	 */
	public JButton getJBFavourites() {
		return JBFavourites;
	}
	
	/**
	 * get method for JLabel.
	 * @return JLabel imageLabel
	 */
	public JLabel getImageLabel() {
		return imageLabel;
	}
	
	/**
	 * get method for ImageIcon.
	 * @return ImageIcon image
	 */
	public ImageIcon getImage(){
		return image;		
	}
	
	/**
	 * get method for SearchFrame.
	 * @return SearchFrame searchFrame
	 */
	public SearchFrame getSearchFrame(){
		return searchFrame;
	}
	
	/**
	 * get method for FavouritesFrame.
	 * @return FavouritesFrame
	 */
	public FavouritesFrame getFavouritesFrame() {
		return favouritesFrame;
	}
	
	/**
	 * get method for LoginFrame.
	 * @return LoginFrame loginFrame
	 */
	public LoginFrame getLoginFrame() {
		return loginFrame;
	}
	
	/**
	 * get method for NewUserFrame.
	 * @return NewUserFrame newUserFrame
	 */
	public NewUserFrame getNewUserFrame() {
		return newUserFrame;
	}
	
	/**
	 * get method for CreateListFrame.
	 * @return CreateListFrame createListFrame
	 */
	public CreateListFrame getCreateListFrame() {
		return createListFrame;
	}
	
	/**
	 * get method for Jaws.
	 * @return Jaws jaws
	 */
	public Jaws getJaws(){
		return jaws;
	}
	
	/**
	 * get method for JPanel.
	 * @return JPanel JPButtons
	 */
	public JPanel getButtonsPanel() {
		return JPButtons;
	}
	
	/**
	 * get method for JMenu.
	 * @return JMenu menu
	 */
	public JMenu getMenu(){
		return menu;
	}
	
	/**
	 * get method for List<JMenuItem>
	 * @return List<JMenuItem> loadItemList
	 */
	public List<JMenuItem> getLoadItemList(){
		return loadItemList;
	}
}
