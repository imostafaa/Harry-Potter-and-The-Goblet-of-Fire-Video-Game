package harrypotter.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.character.Wizard;
import harrypotter.view.ChooseChampionsView;


public class ChooseChampionsController implements MouseListener {
	ChooseChampionsView choosechampionsview ;
	JTextField txt1; JTextField txt2; JTextField txt3; JTextField txt4;
	JComboBox<String> Box1;  JComboBox<String> Box2;  JComboBox<String> Box3;  JComboBox<String> Box4;
	Wizard champion;
	JLabel ChooseSpellsbtn;
	ArrayList<Wizard> ChampionsArray = new ArrayList<Wizard>() ;




	
	

	

	public ChooseChampionsController() {
		
		// Start initialize Variables
		txt1 = new JTextField(); txt2 = new JTextField(); txt3 = new JTextField(); txt4 = new JTextField();
		Box1 = new JComboBox<String>(); Box2 = new JComboBox<String>(); Box3 = new JComboBox<String>(); Box4 = new JComboBox<String>();
		URL url = ChooseChampionsController.class.getResource("ChooseSpellsbtn.png");
		ChooseSpellsbtn = new JLabel(new ImageIcon(url));
		choosechampionsview = new ChooseChampionsView();
		// End initialize variables
	
		//Start Adding names
		Box1.setName("Box1");
		Box2.setName("Box2");
		Box3.setName("Box3");
		Box4.setName("Box4");
		
		txt1.setName("txt1");
		txt2.setName("txt2");
		txt3.setName("txt3");
		txt4.setName("txt4");
		
		ChooseSpellsbtn.setName("ChooseSpellsbtn");
		
		// End Adding names
		
		
		
		
		// Start adding Listener 		
		ChooseSpellsbtn.addMouseListener(this);
		txt1.addMouseListener(this);
		txt2.addMouseListener(this);
		txt3.addMouseListener(this);
		txt4.addMouseListener(this);
		// End adding Listener
		
		
		
		//Start Adding to Frame
		choosechampionsview.addComboBox1(Box1);
		choosechampionsview.addComboBox2(Box2);
		choosechampionsview.addComboBox3(Box3);
		choosechampionsview.addComboBox4(Box4);
		
		choosechampionsview.addTextArea1(txt1);
		choosechampionsview.addTextArea2(txt2);
		choosechampionsview.addTextArea3(txt3);
		choosechampionsview.addTextArea4(txt4);
		
		choosechampionsview.addChooseSpellsbtn(ChooseSpellsbtn);
		
		// End Adding to Frame
		
		
		choosechampionsview.setVisible(true);
		
		
		
		
		
		
	
		
		
		
	}

	public static void main(String[] args) {
		new ChooseChampionsController();
		

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		ChampionsArray.clear();
		
		if(e.getSource() instanceof JLabel){
			if(((JLabel)e.getSource()).getName()=="ChooseSpellsbtn"){
				
				switch((String)Box1.getSelectedItem()){
				
				case("Gryffindor"):{
					champion = new GryffindorWizard(txt1.getText());
					ChampionsArray.add(champion);
					
				}
				break;
				
				case("Hufflepuff"):{
					champion = new HufflepuffWizard(txt1.getText());
					ChampionsArray.add(champion);
				}
				break;
				
				case("Ravenclaw"):{
					champion = new RavenclawWizard(txt1.getText());
					ChampionsArray.add(champion);
					
				}
				break;
				
				case ("Slytherin"):{
					champion = new SlytherinWizard(txt1.getText());
					ChampionsArray.add(champion);
					
				}break;
				case ("Choose House : "):{
					JOptionPane.showMessageDialog(null, "You have to choose champion house!");
					return;}
				
			}
	//------------------------------------------------------------------
				
				switch((String)Box2.getSelectedItem()){
				
				case("Gryffindor"):{
					champion = new GryffindorWizard(txt2.getText());
					ChampionsArray.add(champion);
					
				}
				break;
				
				case("Hufflepuff"):{
					champion = new HufflepuffWizard(txt2.getText());
					ChampionsArray.add(champion);
				}
				break;
				
				case("Ravenclaw"):{
					champion = new RavenclawWizard(txt2.getText());
					ChampionsArray.add(champion);
					
				}
				break;
				
				case ("Slytherin"):{
					champion = new SlytherinWizard(txt2.getText());
					ChampionsArray.add(champion);
					
				}break;
				case ("Choose House : "):{
					JOptionPane.showMessageDialog(null, "You have to choose champion house!");
					return;}
				
			}
	//------------------------------------------------------------------
				
				switch((String)Box3.getSelectedItem()){
				
				case("Gryffindor"):{
					champion = new GryffindorWizard(txt3.getText());
					ChampionsArray.add(champion);
					
				}
				break;
				
				case("Hufflepuff"):{
					champion = new HufflepuffWizard(txt3.getText());
					ChampionsArray.add(champion);
				}
				break;
				
				case("Ravenclaw"):{
					champion = new RavenclawWizard(txt3.getText());
					ChampionsArray.add(champion);
					
				}
				break;
				
				case ("Slytherin"):{
					champion = new SlytherinWizard(txt3.getText());
					ChampionsArray.add(champion);
					
				}break;
				case ("Choose House : "):{
					JOptionPane.showMessageDialog(null, "You have to choose champion house!");
					return;}
				
			}
	//------------------------------------------------------------------
				
				switch((String)Box4.getSelectedItem()){
				
				case("Gryffindor"):{
					champion = new GryffindorWizard(txt4.getText());
					ChampionsArray.add(champion);
					
				}
				break;
				
				case("Hufflepuff"):{
					champion = new HufflepuffWizard(txt4.getText());
					ChampionsArray.add(champion);
				}
				break;
				
				case("Ravenclaw"):{
					champion = new RavenclawWizard(txt4.getText());
					ChampionsArray.add(champion);
					
				}
				break;
				
				case ("Slytherin"):{
					champion = new SlytherinWizard(txt4.getText());
					ChampionsArray.add(champion);
					
				}break;
				case ("Choose House : "):{
					JOptionPane.showMessageDialog(null, "You have to choose champion house!");
					return;
					}
				
			}
				
	//---------------------------------------
					try {
						new ChooseSpellsController(ChampionsArray.get(0),ChampionsArray.get(1),ChampionsArray.get(2),ChampionsArray.get(3));
						choosechampionsview.setVisible(false);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			//	System.out.println(ChampionsArray.get(0).getName());
			//	System.out.println(ChampionsArray.get(1).getName());
				//System.out.println(ChampionsArray.get(2).getName());
				//System.out.println(ChampionsArray.get(3).getName());
				


				
				
			}
			
		}
		
		if(e.getSource() instanceof JTextField){
			((JTextField)e.getSource()).setText(" ");
		}
		

		
		
		
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() instanceof JLabel){
		URL url = ChooseChampionsController.class.getResource("ChooseSpellsBtnHover.png");
		ChooseSpellsbtn.setIcon(new ImageIcon(url));}
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() instanceof JLabel){
		URL url = ChooseChampionsController.class.getResource("ChooseSpellsBtn.png");
		ChooseSpellsbtn.setIcon(new ImageIcon(url));}
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public ArrayList<Wizard> getChampionsArray() {
		return ChampionsArray;
	}

}
