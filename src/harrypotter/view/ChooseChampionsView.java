package harrypotter.view;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ChooseChampionsView extends JFrame {
	JLabel ChooseChampionsBackGround;

	public ChooseChampionsView() {
		// Start Loading and adding the background
		URL url = ChooseChampionsView.class.getResource("ChooseChampionsBackGround.png");
		 ChooseChampionsBackGround = new JLabel(new ImageIcon(url));
		getContentPane().add(ChooseChampionsBackGround);
		//end Loading and adding the background
		
	    	
		// Start preparing the frame
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setUndecorated(true);
	    pack();
	   // setVisible(true);
	   // End preparing the frame
	    
	}
	
	// Methods used in view controller
	
	public void addComboBox1(JComboBox<String> Box1){
		Box1.addItem("Choose House : ");
		Box1.addItem("Gryffindor");
		Box1.addItem("Hufflepuff");
		Box1.addItem("Ravenclaw");
		Box1.addItem("Slytherin");
		Box1.setBounds(508, 534, 169, 32);
		ChooseChampionsBackGround.add(Box1);	
	}
	

	public void addComboBox2(JComboBox<String> Box1){
		Box1.addItem("Choose House : ");
		Box1.addItem("Gryffindor");
		Box1.addItem("Hufflepuff");
		Box1.addItem("Ravenclaw");
		Box1.addItem("Slytherin");
		Box1.setBounds(508, 591, 169, 32);
		ChooseChampionsBackGround.add(Box1);	
	}
	
	public void addComboBox3(JComboBox<String> Box1){
		Box1.addItem("Choose House : ");
		Box1.addItem("Gryffindor");
		Box1.addItem("Hufflepuff");
		Box1.addItem("Ravenclaw");
		Box1.addItem("Slytherin");
		Box1.setBounds(508, 648, 169, 32);
		ChooseChampionsBackGround.add(Box1);	
	}
	
	public void addComboBox4(JComboBox<String> Box1){
		Box1.addItem("Choose House : ");
		Box1.addItem("Gryffindor");
		Box1.addItem("Hufflepuff");
		Box1.addItem("Ravenclaw");
		Box1.addItem("Slytherin");
		Box1.setBounds(508, 705, 169, 32);
		ChooseChampionsBackGround.add(Box1);	
	}
	
	public void addTextArea1(JTextField txt1){
		txt1.setText("Enter Your Name : ");
		txt1.setBounds(776, 534, 169, 32);
		ChooseChampionsBackGround.add(txt1);
	
		
	}
	public void addTextArea2(JTextField txt1){
		txt1.setText("Enter Your Name : ");
		txt1.setBounds(776, 591, 169, 32);
		ChooseChampionsBackGround.add(txt1);
	
		
	}
	public void addTextArea3(JTextField txt1){
		txt1.setText("Enter Your Name : ");
		txt1.setBounds(776, 648, 169, 32);
		ChooseChampionsBackGround.add(txt1);
	
		
	}
	public void addTextArea4(JTextField txt1){
		txt1.setText("Enter Your Name : ");
		txt1.setBounds(776, 705, 169, 32);
		ChooseChampionsBackGround.add(txt1);
	
		
	}
	
	public void addChooseSpellsbtn(JLabel ChooseSpellsbtn){
		ChooseSpellsbtn.setBounds(1120, 641, 200, 78);
	    ChooseChampionsBackGround.add(ChooseSpellsbtn);
		
	}
	


}
