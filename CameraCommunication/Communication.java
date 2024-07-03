import java.awt.BorderLayout;
import java.awt.Dimension;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class Communication extends JFrame{
	
	private Camera camera;
	private JButton sendButton;
	private JTextArea message;
	
	public Communication() {
        super("Communication Window");
		setPreferredSize(new Dimension(800,400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        configuration();
        pack();
        setVisible(true);
    }
	
	public void configuration(){
		this.setLayout(new BorderLayout());
		camera = new Camera();		
		this.add(camera,BorderLayout.NORTH);
		sendButton = new JButton("Send");
		this.add(sendButton,BorderLayout.SOUTH);
		message = new JTextArea ();
		this.add(message,BorderLayout.CENTER);
		SendListener sendListener = new SendListener(this);
		sendButton.addActionListener(sendListener);
	}
	
	public void updateCamera(List<Integer> userInput) throws InterruptedException{
		this.camera.acceptPosition(userInput);
	}
	
	public JTextArea getTextArea(){
		return message;
	}         
}
