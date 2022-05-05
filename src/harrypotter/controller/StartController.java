package harrypotter.controller;

import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class StartController {

	public StartController() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String []args) throws InterruptedException{
		// initialize JFrame 		
		JFrame StartFrame = new JFrame();
		// Add Icon
		URL url = StartController.class.getResource("StartImage.gif");
		Icon StartImageIcon = new ImageIcon(url);
		JLabel StartImage = new JLabel(StartImageIcon);
		
		// Start Adding
	    StartFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        StartFrame.setUndecorated(true);
		StartFrame.getContentPane().add(StartImage);
		StartFrame.setVisible(true);
		StartFrame.pack();
		//End Adding
		Thread.sleep(2500);
		new ChooseChampionsController();
		Thread.sleep(1000);
		StartFrame.setVisible(false);
		
	}

}
