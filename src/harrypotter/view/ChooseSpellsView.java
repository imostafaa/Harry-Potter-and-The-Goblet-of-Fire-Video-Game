package harrypotter.view;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ChooseSpellsView extends JFrame{
	JLabel ChooseSpellsBackGround;
	JLabel MovingGirl;
	public ArrayList<JCheckBox> First=new ArrayList<JCheckBox>();
	public ArrayList<JCheckBox> Second=new ArrayList<JCheckBox>();
	public ArrayList<JCheckBox> Third=new ArrayList<JCheckBox>();
	public ArrayList<JCheckBox> Fourth=new ArrayList<JCheckBox>();
	

	public ChooseSpellsView() {
		// Start Loading and adding the background
		URL url = ChooseSpellsView.class.getResource("ChooseSpellsBackGround.png");
		 ChooseSpellsBackGround = new JLabel(new ImageIcon(url));
		getContentPane().add(ChooseSpellsBackGround);
		//end Loading and adding the background
		
		 URL url2 = ChooseSpellsView.class.getResource("girl.gif");
		 MovingGirl = new JLabel(new ImageIcon(url2));
		 MovingGirl.setBounds(1013, 48, 332, 471);
		 ChooseSpellsBackGround.add(MovingGirl);		 
		
	    	
		// Start preparing the frame
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setUndecorated(true);
	    pack();
	   // setVisible(true);
	   // setVisible(true);
	   // End preparing the frame
		
		
		
	}
	
	public void addCheckBoxesHEL1(MouseListener l){
		int y = 227;
		Color m = new Color(56,73,74);
		
		for(int i = 1 ; i<7 ;i++){
			 JCheckBox tmp = new JCheckBox();
			 tmp.setBounds(90, y, 19, 15);
			 tmp.setName("1"+i);
			 tmp.setBackground(m);
			    
			 ChooseSpellsBackGround.add(tmp);
			 First.add(tmp);
			 y = y+24;
		}

		
	}
	public void addCheckBoxesDMG1(MouseListener l){
		int y = 385;
		Color m = new Color(97,97,83);
		
		for(int i = 7 ; i<18 ;i++){
			 JCheckBox tmp = new JCheckBox();
			 tmp.setBounds(90, y, 19, 15);
			 tmp.setName("1"+i);
			 tmp.setBackground(m);
			    
			 ChooseSpellsBackGround.add(tmp);
			 First.add(tmp);
			 y = y+22;
		}		
	}
	public void addCheckBoxesREL1(MouseListener l){
		int y = 640;
		Color m = new Color(73,50,71);
		
		for(int i = 18 ; i<22 ;i++){
			 JCheckBox tmp = new JCheckBox();
			 tmp.setBounds(90, y, 19, 15);
			 tmp.setName("1"+i);
			 tmp.setBackground(m);
			    
			 ChooseSpellsBackGround.add(tmp);
			 First.add(tmp);
			 y = y+23;
		}

		
	}
	
    public void addCheckBoxesHEL2(MouseListener l){
		int y = 227;
		Color m = new Color(56,73,74);
		
		for(int i = 1 ; i<7 ;i++){
			 JCheckBox tmp = new JCheckBox();
			 tmp.setBounds(329, y, 19, 15);
			 tmp.setName("2"+i);
			 tmp.setBackground(m);
			    
			 ChooseSpellsBackGround.add(tmp);
			 y = y+24;
			 Second.add(tmp);
		}

		
	}
	public void addCheckBoxesDMG2(MouseListener l){
		int y = 385;
		Color m = new Color(97,97,83);
		
		for(int i = 7 ; i<18 ;i++){
			 JCheckBox tmp = new JCheckBox();
			 tmp.setBounds(329, y, 19, 15);
			 tmp.setName("2"+i);
			 tmp.setBackground(m);
			    
			 ChooseSpellsBackGround.add(tmp);
			 Second.add(tmp);
			 y = y+22;
		}		
	}
	public void addCheckBoxesREL2(MouseListener l){
		int y = 640;
		Color m = new Color(73,50,71);
		
		for(int i = 18 ; i<22 ;i++){
			 JCheckBox tmp = new JCheckBox();
			 tmp.setBounds(329, y, 19, 15);
			 tmp.setName("2"+i);
			 tmp.setBackground(m);
			    
			 ChooseSpellsBackGround.add(tmp);
			 Second.add(tmp);
			 y = y+23;
		}	
	}
	
	public void addCheckBoxesHEL3(MouseListener l){
		int y = 227;
		Color m = new Color(56,73,74);
		
		for(int i = 1 ; i<7 ;i++){
			 JCheckBox tmp = new JCheckBox();
			 tmp.setBounds(569, y, 19, 15);
			 tmp.setName("3"+i);
			 tmp.setBackground(m);
			    
			 ChooseSpellsBackGround.add(tmp);
			 y = y+24;
			 Third.add(tmp);
		}

		
	}
	public void addCheckBoxesDMG3(MouseListener l){
		int y = 385;
		Color m = new Color(97,97,83);
		
		for(int i = 7 ; i<18 ;i++){
			 JCheckBox tmp = new JCheckBox();
			 tmp.setBounds(569, y, 19, 15);
			 tmp.setName("3"+i);
			 tmp.setBackground(m);
			    
			 ChooseSpellsBackGround.add(tmp);
			 y = y+22;
			 Third.add(tmp);
		}		
	}
	public void addCheckBoxesREL3(MouseListener l){
		int y = 640;
		Color m = new Color(73,50,71);
		
		for(int i = 18 ; i<22 ;i++){
			 JCheckBox tmp = new JCheckBox();
			 tmp.setBounds(569, y, 19, 15);
			 tmp.setName("3"+i);
			 tmp.setBackground(m);
			    
			 ChooseSpellsBackGround.add(tmp);
			 y = y+23;
			 Third.add(tmp);
		}	
	}
	
	public void addCheckBoxesHEL4(MouseListener l){
		int y = 227;
		Color m = new Color(56,73,74);
		
		for(int i = 1 ; i<7 ;i++){
			 JCheckBox tmp = new JCheckBox();
			 tmp.setBounds(807, y, 19, 15);
			 tmp.setName("4"+i);
			 tmp.setBackground(m);
			 ChooseSpellsBackGround.add(tmp);
			 y = y+24;
			 Fourth.add(tmp);
		}

		
	}
	public void addCheckBoxesDMG4(MouseListener l){
		int y = 385;
		Color m = new Color(97,97,83);
		
		for(int i = 7 ; i<18 ;i++){
			 JCheckBox tmp = new JCheckBox();
			 tmp.setBounds(807, y, 19, 15);
			 tmp.setName("4"+i);
			 tmp.setBackground(m);
			    
			 ChooseSpellsBackGround.add(tmp);
			 y = y+22;
			 Fourth.add(tmp);
		}		
	}	
	public void addCheckBoxesREL4(MouseListener l){
		int y = 640;
		Color m = new Color(73,50,71);
		
		for(int i = 18 ; i<22 ;i++){
			 JCheckBox tmp = new JCheckBox();
			 tmp.setBounds(807, y, 19, 15);
			 tmp.setName("4"+i);
			 tmp.setBackground(m);
			    
			 ChooseSpellsBackGround.add(tmp);
			 y = y+23;
			 Fourth.add(tmp);
		}	
	}

    public void addBeginTournamentbtn(JLabel BeginTournamentbtn ){
    	BeginTournamentbtn.setBounds(1103, 668, 238, 74);
    	ChooseSpellsBackGround.add(BeginTournamentbtn);
    }
	
	
	public static void main(String[]args){
		new ChooseSpellsView();
		
	}

	public void addRandom(JLabel Random) {
		Random.setBounds(1203, 610, 30, 30);
		ChooseSpellsBackGround.add(Random);
	}
	
	

}
