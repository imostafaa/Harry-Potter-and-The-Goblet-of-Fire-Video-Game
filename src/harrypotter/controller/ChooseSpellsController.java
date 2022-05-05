package harrypotter.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

import harrypotter.model.character.Champion;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.Spell;
import harrypotter.model.tournament.Tournament;
import harrypotter.view.ChooseSpellsView;


public class ChooseSpellsController  implements MouseListener{
	ChooseSpellsView choosespellsview;
	JLabel BeginTournamentbtn;
	JLabel Random;
	
	String name;
	Tournament tournament;
	ArrayList<Wizard> Champions = new ArrayList<Wizard>();
	ArrayList<Spell> fst=new ArrayList<Spell>();
	ArrayList<Spell> snd=new ArrayList<Spell>();
	ArrayList<Spell> thrd=new ArrayList<Spell>();
	ArrayList<Spell> frth=new ArrayList<Spell>();
	
	


	

	public ChooseSpellsController(Wizard Champion1 , Wizard Champion2 , Wizard Champion3 , Wizard Champion4) throws IOException {
          Champions.add(Champion1);
          Champions.add(Champion2);
          Champions.add(Champion3);
          Champions.add(Champion4);

		choosespellsview = new ChooseSpellsView();
		tournament = new Tournament();
		BeginTournamentbtn = new JLabel(new ImageIcon(ChooseSpellsController.class.getResource("BeginTournament.png")));
		Random=new JLabel(new ImageIcon(ChooseSpellsController.class.getResource("CorrectSign.png")));
		
		BeginTournamentbtn.setName("begin");
		Random.setName("random");
		
		//Adding Listeners
		BeginTournamentbtn.addMouseListener(this);
		Random.addMouseListener(this);
		//End

		
		
		/// Adding CheckBox and btns
		choosespellsview.addCheckBoxesHEL1(this);
		choosespellsview.addCheckBoxesDMG1(this);
		choosespellsview.addCheckBoxesREL1(this);
		choosespellsview.addCheckBoxesHEL2(this);
		choosespellsview.addCheckBoxesDMG2(this);
		choosespellsview.addCheckBoxesREL2(this);
		choosespellsview.addCheckBoxesHEL3(this);
		choosespellsview.addCheckBoxesDMG3(this);
		choosespellsview.addCheckBoxesREL3(this);
		choosespellsview.addCheckBoxesHEL4(this);
		choosespellsview.addCheckBoxesDMG4(this);
		choosespellsview.addCheckBoxesREL4(this);
		
		choosespellsview.addBeginTournamentbtn(BeginTournamentbtn);
		
		choosespellsview.addRandom(Random);
		//End
		
		
			
		choosespellsview.setVisible(true);
		
	//	System.out.print(ChampionsArray.get(0).getName());

		


		
		
	}
	
	
	public static void main(String[]args) throws IOException{
		//new ChooseSpellsController();
		
	}
	

 

	@Override
	public void mouseClicked(MouseEvent e) {
				
		if(e.getSource() instanceof JLabel && ((JLabel)e.getSource()).getName()=="random"){
			ArrayList<JCheckBox> first =choosespellsview.First;
			ArrayList<JCheckBox> second =choosespellsview.Second;
			ArrayList<JCheckBox> third =choosespellsview.Third;
			ArrayList<JCheckBox> fourth =choosespellsview.Fourth;
			for(int i=0;i<21;i++){
				first.get(i).setSelected(false);
				second.get(i).setSelected(false);
				third.get(i).setSelected(false);
				fourth.get(i).setSelected(false);
				
			}
			for(int i=0;i<3;){
				int a=((int)(Math.random()*21));
				if(!((JCheckBox)first.get(a)).isSelected()){
					((JCheckBox)first.get(a)).setSelected(true);
					
					i++;}
			}
			for(int i=0;i<3;){
				int a=((int)(Math.random()*21));
				if(!(((JCheckBox)second.get(a)).isSelected())){
					((JCheckBox)second.get(a)).setSelected(true);
					i++;}
			}
			for(int i=0;i<3;){
				int a=((int)(Math.random()*21));
				if(!((JCheckBox)third.get(a)).isSelected()){
					((JCheckBox)third.get(a)).setSelected(true);
					i++;}
			}
			for(int i=0;i<3;){
				int a=((int)(Math.random()*21));
				if(!((JCheckBox)fourth.get(a)).isSelected()){
					((JCheckBox)fourth.get(a)).setSelected(true);
					i++;}
			}
			
			
		
		}
		
		if(e.getSource() instanceof JLabel && ((JLabel)e.getSource()).getName()=="begin"){
		//	System.out.print(Champions.get(0).getSpells().get(0).getName());
			fst.clear();
			snd.clear();
			thrd.clear();
			frth.clear();
			
			ArrayList<JCheckBox> first =choosespellsview.First;
			ArrayList<JCheckBox> second =choosespellsview.Second;
			ArrayList<JCheckBox> third =choosespellsview.Third;
			ArrayList<JCheckBox> fourth =choosespellsview.Fourth;
			ArrayList<Spell> spl=tournament.getSpells();
			for(int a=0;a<first.size();a++){
				JCheckBox x=first.get(a);
				if(x.isSelected()){
				
					fst.add(spl.get(a).Duplicate());
				}
				
			}
			for(int a=0;a<first.size();a++){
				JCheckBox x=second.get(a);
				if(x.isSelected()){
				
					snd.add(spl.get(a).Duplicate());
				}
				
			}
			for(int a=0;a<first.size();a++){
				JCheckBox x=third.get(a);
				if(x.isSelected()){
					
					thrd.add(spl.get(a).Duplicate());
				}
				
			}
			for(int a=0;a<first.size();a++){
				JCheckBox x=fourth.get(a);
				if(x.isSelected()){
				
					frth.add(spl.get(a).Duplicate());
				}
				
			}
			

			if(fst.size()!=3||snd.size()!=3||thrd.size()!=3||frth.size()!=3){
				System.out.print(fst.size()+""+snd.size()+""+thrd.size()+""+frth.size());
				return;
			}
			
			for(Spell x:fst){
				(Champions.get(0)).getSpells().add(x);
			}
			for(Spell x:snd){
				(Champions.get(1)).getSpells().add(x);
			}
			for(Spell x:thrd){
				(Champions.get(2)).getSpells().add(x);
			}
			for(Spell x:frth){
				(Champions.get(3)).getSpells().add(x);
			}
			
			
			tournament.getChampions().add((Champion)Champions.get(0));
			tournament.getChampions().add((Champion)Champions.get(1));
			tournament.getChampions().add((Champion)Champions.get(2));
			tournament.getChampions().add((Champion)Champions.get(3));
			try {
				tournament.beginTournament();
				
				
				new FirstTaskController(tournament.getFirstTask().getChampions(), tournament);
				choosespellsview.setVisible(false);
				//new FirstTaskController(tournament.getChampions(),tournament);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		
		
	}
	

	@Override
	public void mouseEntered(MouseEvent e) {
		
		if(e.getSource() instanceof JLabel && ((JLabel)e.getSource()).getName()=="begin"){
			URL url = ChooseSpellsController.class.getResource("BeginTournamentHover.png");
			BeginTournamentbtn.setIcon(new ImageIcon(url));
		}
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		
		if(e.getSource() instanceof JLabel && ((JLabel)e.getSource()).getName()=="begin"){
			URL url = ChooseSpellsController.class.getResource("BeginTournament.png");
			BeginTournamentbtn.setIcon(new ImageIcon(url));
		}
		
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
