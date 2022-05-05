package harrypotter.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.Label;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
//import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;

import org.omg.CosNaming.IstringHelper;

import harrypotter.exceptions.InCooldownException;
import harrypotter.exceptions.InvalidTargetCellException;
import harrypotter.exceptions.NotEnoughIPException;
import harrypotter.exceptions.OutOfBordersException;
import harrypotter.exceptions.OutOfRangeException;
import harrypotter.model.character.Champion;
import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.Potion;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.tournament.FirstTask;
import harrypotter.model.tournament.Tournament;
import harrypotter.model.world.Cell;
import harrypotter.model.world.ChampionCell;
import harrypotter.model.world.CollectibleCell;
import harrypotter.model.world.Direction;
import harrypotter.model.world.EmptyCell;
import harrypotter.model.world.ObstacleCell;
import harrypotter.view.FirstTaskView;

public class FirstTaskController implements MouseListener {
	//Initializes the required Variables 

	FirstTaskView firsttaskview;
	JLabel RightArrow; JLabel LeftArrow; JLabel BackwardArrow; JLabel ForwardArrow;
	JLabel ActivateTraitbtn; JLabel CastSpellbtn;
	Tournament tournament;
	Cell[][] map;
	JLabel[][] imageMap = new JLabel[10][10];
	FirstTask firsttask;
	JLabel Name = new JLabel() ;
	JLabel House = new JLabel();
	JLabel spell1 = new JLabel();
	JLabel spell2 = new JLabel();
	JLabel spell3 = new JLabel();
	JLabel ChampionImage = new JLabel();
	JProgressBar ipBar = new JProgressBar();
	JProgressBar hpBar = new JProgressBar();
	JLabel remainingMoves = new JLabel();
	JLabel traitActivatedSign = new JLabel();


	JRadioButton spell1Box = new JRadioButton();
	JRadioButton spell2Box = new JRadioButton();
	JRadioButton spell3Box = new JRadioButton();
	ButtonGroup group = new ButtonGroup();


	JLabel usepotion;
	int potions=0;
	
	Wizard wiz;



	public FirstTaskController(ArrayList<Champion> Champions, Tournament tournament) throws IOException {

		//Begin Tournament
		this.tournament =tournament;
		tournament.beginTournament();
		firsttask = tournament.getFirstTask();
		firsttask.generateMap();
		map = firsttask.getMap();


		// Start Initialize the frame and add direction buttons
		RightArrow       = new JLabel(new ImageIcon(FirstTaskController.class.getResource("RightArrow.png")));
		LeftArrow        = new JLabel(new ImageIcon(FirstTaskController.class.getResource("LeftArrow.png")));
		ForwardArrow     = new JLabel(new ImageIcon(FirstTaskController.class.getResource("ForwardArrow.png")));
		BackwardArrow    = new JLabel(new ImageIcon(FirstTaskController.class.getResource("BackwardArrow.png")));
		ActivateTraitbtn = new JLabel(new ImageIcon(FirstTaskController.class.getResource("ActivateTrait.png")));
		CastSpellbtn     = new JLabel(new ImageIcon(FirstTaskController.class.getResource("Wand.png")));
		usepotion =new JLabel(new ImageIcon(FirstTaskController.class.getResource("CorrectSign.png")));
		usepotion.addMouseListener(this);
		usepotion.setName("potion");


		firsttaskview    = new FirstTaskView();
		// End 


		//Start Set Names 
		RightArrow.setName("RightArrow");
		LeftArrow.setName("LeftArrow");
		ForwardArrow.setName("ForwardArrow");
		BackwardArrow.setName("BackwardArrow");
		ActivateTraitbtn.setName("ActivateTraitbtn");
		CastSpellbtn.setName("CastSpellbtn");
		firsttaskview.setFocusable(true);
		
		//End Set Names




		//Start Adding To View 
		firsttaskview.addRightArrow(RightArrow);
		firsttaskview.addLeftArrow(LeftArrow);
		firsttaskview.addForwardArrow(ForwardArrow);
		firsttaskview.addBackwardArrow(BackwardArrow);
		firsttaskview.addActivateTraitbtn(ActivateTraitbtn);
		firsttaskview.addCastSpellbtn(CastSpellbtn);
		firsttaskview.addusepotion(usepotion);

		// End Adding To View



		//Start Adding Listener
		RightArrow.addMouseListener(this);
		LeftArrow.addMouseListener(this);
		BackwardArrow.addMouseListener(this);
		ForwardArrow.addMouseListener(this);
		ActivateTraitbtn.addMouseListener(this);
		CastSpellbtn.addMouseListener(this);
		//End Adding Listener




		//Start Generate Graphic Panels and Map
		updateChampionsInfo(Champions);
		generateMapImages(map);
		updateCurrentChampionInfo(tournament.getFirstTask().getCurrentChamp());
		updateStateArea();
		//End

		firsttaskview.setVisible(true);

	}


	public void updateChampionsInfo(ArrayList<Champion> champArr){
		// This Method was used once in the constructor <One-Use Method>


		JLabel Gryff   = new JLabel(new ImageIcon(FirstTaskController.class.getResource("GryffChampSmall.png")));
		JLabel Huffle  = new JLabel(new ImageIcon(FirstTaskController.class.getResource("HuffleChampSmall.png")));
		JLabel Slyth   = new JLabel(new ImageIcon(FirstTaskController.class.getResource("SlythChampSmall.png")));
		JLabel Raven   = new JLabel(new ImageIcon(FirstTaskController.class.getResource("RavenChampSmall.png")));

		JLabel Gryff1   = new JLabel(new ImageIcon(FirstTaskController.class.getResource("GryffChampSmall.png")));
		JLabel Huffle1  = new JLabel(new ImageIcon(FirstTaskController.class.getResource("HuffleChampSmall.png")));
		JLabel Slyth1   = new JLabel(new ImageIcon(FirstTaskController.class.getResource("SlythChampSmall.png")));
		JLabel Raven1   = new JLabel(new ImageIcon(FirstTaskController.class.getResource("RavenChampSmall.png")));

		JLabel Gryff2   = new JLabel(new ImageIcon(FirstTaskController.class.getResource("GryffChampSmall.png")));
		JLabel Huffle2  = new JLabel(new ImageIcon(FirstTaskController.class.getResource("HuffleChampSmall.png")));
		JLabel Slyth2   = new JLabel(new ImageIcon(FirstTaskController.class.getResource("SlythChampSmall.png")));
		JLabel Raven2   = new JLabel(new ImageIcon(FirstTaskController.class.getResource("RavenChampSmall.png")));

		JLabel Gryff3   = new JLabel(new ImageIcon(FirstTaskController.class.getResource("GryffChampSmall.png")));
		JLabel Huffle3  = new JLabel(new ImageIcon(FirstTaskController.class.getResource("HuffleChampSmall.png")));
		JLabel Slyth3   = new JLabel(new ImageIcon(FirstTaskController.class.getResource("SlythChampSmall.png")));
		JLabel Raven3   = new JLabel(new ImageIcon(FirstTaskController.class.getResource("RavenChampSmall.png")));

		JLabel Champion1Name;  JLabel Champion2Name; JLabel Champion3Name; JLabel Champion4Name;
		JLabel Champion1House; JLabel Champion2House; JLabel Champion3House; JLabel Champion4House;

		if ( champArr.get(0) instanceof GryffindorWizard){
			firsttaskview.addImage(Gryff, 1020, 429, 52, 79);
			//----------- Add Names And houses
			Champion1Name = new JLabel(((GryffindorWizard)(champArr.get(0))).getName());
			Champion1House = new JLabel("Gryffindor");
			setFont(Champion1Name,"ParryHotter.ttf");
			setFont(Champion1House,"ParryHotter.ttf");
			Champion1Name.setForeground(Color.white);
			Champion1House.setForeground(Color.white);
			firsttaskview.addImage(Champion1House,1123 , 455, 400, 50);
			firsttaskview.addImage(Champion1Name, 1123, 426, 400, 50);
			//------------ End Adding Names And houses
		}
		else if ( champArr.get(0) instanceof HufflepuffWizard){
			firsttaskview.addImage(Huffle, 1020, 429, 52, 79);
			//----------- Add Names And houses
			Champion1Name = new JLabel(((HufflepuffWizard)(champArr.get(0))).getName());
			Champion1House = new JLabel("Hufflepuff");
			setFont(Champion1Name,"ParryHotter.ttf");
			setFont(Champion1House,"ParryHotter.ttf");
			Champion1Name.setForeground(Color.white);
			Champion1House.setForeground(Color.white);
			firsttaskview.addImage(Champion1House,1123 , 455, 400, 50);
			firsttaskview.addImage(Champion1Name, 1123, 426, 400, 50);
			//------------ End Adding Names And houses
		}
		else if ( champArr.get(0) instanceof SlytherinWizard){
			firsttaskview.addImage(Slyth, 1020, 429, 52, 79);
			//----------- Add Names And houses
			Champion1Name = new JLabel(((SlytherinWizard)(champArr.get(0))).getName());
			Champion1House = new JLabel("Slytherinpuff");
			setFont(Champion1Name,"ParryHotter.ttf");
			setFont(Champion1House,"ParryHotter.ttf");
			Champion1Name.setForeground(Color.white);
			Champion1House.setForeground(Color.white);
			firsttaskview.addImage(Champion1House,1123 , 455, 400, 50);
			firsttaskview.addImage(Champion1Name, 1123, 426, 400, 50);
			//------------ End Adding Names And houses
		}
		else if ( champArr.get(0) instanceof RavenclawWizard){
			firsttaskview.addImage(Raven, 1020, 429, 52, 79);
			//----------- Add Names And houses
			Champion1Name = new JLabel(((RavenclawWizard)(champArr.get(0))).getName());
			Champion1House = new JLabel("Ravenclaw");
			setFont(Champion1Name,"ParryHotter.ttf");
			setFont(Champion1House,"ParryHotter.ttf");
			Champion1Name.setForeground(Color.white);
			Champion1House.setForeground(Color.white);
			firsttaskview.addImage(Champion1House,1123 , 455, 400, 50);
			firsttaskview.addImage(Champion1Name, 1123, 426, 400, 50);
			//------------ End Adding Names And houses
		}




		//------------
		if ( champArr.get(1) instanceof GryffindorWizard){
			firsttaskview.addImage(Gryff1, 1020, 509, 52, 79);
			//----------- Add Names And houses
			Champion2Name = new JLabel(((GryffindorWizard)(champArr.get(1))).getName());
			Champion2House = new JLabel("Gryffindor");
			setFont(Champion2Name,"ParryHotter.ttf");
			setFont(Champion2House,"ParryHotter.ttf");
			Champion2Name.setForeground(Color.white);
			Champion2House.setForeground(Color.white);
			firsttaskview.addImage(Champion2House,1123 , 537, 400, 50);
			firsttaskview.addImage(Champion2Name, 1123, 508, 400, 50);
			//------------ End Adding Names And houses
		}
		else if ( champArr.get(1) instanceof HufflepuffWizard){
			firsttaskview.addImage(Huffle1, 1020, 509, 52, 79);
			//----------- Add Names And houses
			Champion2Name = new JLabel(((HufflepuffWizard)(champArr.get(1))).getName());
			Champion2House = new JLabel("Hufflepuff");
			setFont(Champion2Name,"ParryHotter.ttf");
			setFont(Champion2House,"ParryHotter.ttf");
			Champion2Name.setForeground(Color.white);
			Champion2House.setForeground(Color.white);
			firsttaskview.addImage(Champion2House,1123 , 537, 400, 50);
			firsttaskview.addImage(Champion2Name, 1123, 508, 400, 50);
			//------------ End Adding Names And houses
		}
		else if ( champArr.get(1) instanceof SlytherinWizard){
			firsttaskview.addImage(Slyth1, 1020, 509, 52, 79);
			//----------- Add Names And houses
			Champion2Name = new JLabel(((SlytherinWizard)(champArr.get(1))).getName());
			Champion2House = new JLabel("Slytherin");
			setFont(Champion2Name,"ParryHotter.ttf");
			setFont(Champion2House,"ParryHotter.ttf");
			Champion2Name.setForeground(Color.white);
			Champion2House.setForeground(Color.white);
			firsttaskview.addImage(Champion2House,1123 , 537, 400, 50);
			firsttaskview.addImage(Champion2Name, 1123, 508, 400, 50);
			//------------ End Adding Names And houses
		}
		else if ( champArr.get(1) instanceof RavenclawWizard){
			firsttaskview.addImage(Raven1, 1020, 509, 52, 79);
			//----------- Add Names And houses
			Champion2Name = new JLabel(((RavenclawWizard)(champArr.get(1))).getName());
			Champion2House = new JLabel("Ravenclaw");
			setFont(Champion2Name,"ParryHotter.ttf");
			setFont(Champion2House,"ParryHotter.ttf");
			Champion2Name.setForeground(Color.white);
			Champion2House.setForeground(Color.white);
			firsttaskview.addImage(Champion2House,1123 , 537, 400, 50);
			firsttaskview.addImage(Champion2Name, 1123, 508, 400, 50);
			//------------ End Adding Names And houses
		}




		//-------------
		if ( champArr.get(2) instanceof GryffindorWizard){
			firsttaskview.addImage(Gryff2, 1020, 593, 52, 79);
			//----------- Add Names And houses
			Champion3Name = new JLabel(((GryffindorWizard)(champArr.get(2))).getName());
			Champion3House = new JLabel("Gryffindor");
			setFont(Champion3Name,"ParryHotter.ttf");
			setFont(Champion3House,"ParryHotter.ttf");
			Champion3Name.setForeground(Color.white);
			Champion3House.setForeground(Color.white);
			firsttaskview.addImage(Champion3House,1123 , 624, 400, 50);
			firsttaskview.addImage(Champion3Name, 1123, 595, 400, 50);
			//------------ End Adding Names And houses
		}
		else if ( champArr.get(2) instanceof HufflepuffWizard){
			firsttaskview.addImage(Huffle2, 1020, 593, 52, 79);
			//----------- Add Names And houses
			Champion3Name = new JLabel(((HufflepuffWizard)(champArr.get(2))).getName());
			Champion3House = new JLabel("Hufflepuff");
			setFont(Champion3Name,"ParryHotter.ttf");
			setFont(Champion3House,"ParryHotter.ttf");
			Champion3Name.setForeground(Color.white);
			Champion3House.setForeground(Color.white);
			firsttaskview.addImage(Champion3House,1123 , 624, 400, 50);
			firsttaskview.addImage(Champion3Name, 1123, 595, 400, 50);
			//------------ End Adding Names And houses
		}
		else if ( champArr.get(2) instanceof SlytherinWizard){
			firsttaskview.addImage(Slyth2, 1020, 593, 52, 79);
			//----------- Add Names And houses
			Champion3Name = new JLabel(((SlytherinWizard)(champArr.get(2))).getName());
			Champion3House = new JLabel("Slytherin");
			setFont(Champion3Name,"ParryHotter.ttf");
			setFont(Champion3House,"ParryHotter.ttf");
			Champion3Name.setForeground(Color.white);
			Champion3House.setForeground(Color.white);
			firsttaskview.addImage(Champion3House,1123 , 624, 400, 50);
			firsttaskview.addImage(Champion3Name, 1123, 595, 400, 50);
			//------------ End Adding Names And houses
		}
		else if ( champArr.get(2) instanceof RavenclawWizard){
			firsttaskview.addImage(Raven2, 1020, 593, 52, 79);
			//----------- Add Names And houses
			Champion3Name = new JLabel(((RavenclawWizard)(champArr.get(2))).getName());
			Champion3House = new JLabel("Ravenclaw");
			setFont(Champion3Name,"ParryHotter.ttf");
			setFont(Champion3House,"ParryHotter.ttf");
			Champion3Name.setForeground(Color.white);
			Champion3House.setForeground(Color.white);
			firsttaskview.addImage(Champion3House,1123 , 624, 400, 50);
			firsttaskview.addImage(Champion3Name, 1123, 595, 400, 50);
			//------------ End Adding Names And houses
		}



		//-----------
		if ( champArr.get(3) instanceof GryffindorWizard){
			firsttaskview.addImage(Gryff3, 1020, 672, 52, 79);
			//----------- Add Names And houses
			Champion4Name = new JLabel(((GryffindorWizard)(champArr.get(3))).getName());
			Champion4House = new JLabel("Gryffindor");
			setFont(Champion4Name,"ParryHotter.ttf");
			setFont(Champion4House,"ParryHotter.ttf");
			Champion4Name.setForeground(Color.white);
			Champion4House.setForeground(Color.white);
			firsttaskview.addImage(Champion4House,1123 , 701, 400, 50);
			firsttaskview.addImage(Champion4Name, 1123, 672, 400, 50);
			//------------ End Adding Names And houses
		}
		else if ( champArr.get(3) instanceof HufflepuffWizard){
			firsttaskview.addImage(Huffle3, 1020, 672, 52, 79);
			//----------- Add Names And houses
			Champion4Name = new JLabel(((HufflepuffWizard)(champArr.get(3))).getName());
			Champion4House = new JLabel("Hufflepuff");
			setFont(Champion4Name,"ParryHotter.ttf");
			setFont(Champion4House,"ParryHotter.ttf");
			Champion4Name.setForeground(Color.white);
			Champion4House.setForeground(Color.white);
			firsttaskview.addImage(Champion4House,1123 , 701, 400, 50);
			firsttaskview.addImage(Champion4Name, 1123, 672, 400, 50);
			//------------ End Adding Names And houses
		}
		else if ( champArr.get(3) instanceof SlytherinWizard){
			firsttaskview.addImage(Slyth3, 1020, 672, 52, 79);
			//----------- Add Names And houses
			Champion4Name = new JLabel(((SlytherinWizard)(champArr.get(3))).getName());
			Champion4House = new JLabel("Slytherin");
			setFont(Champion4Name,"ParryHotter.ttf");
			setFont(Champion4House,"ParryHotter.ttf");
			Champion4Name.setForeground(Color.white);
			Champion4House.setForeground(Color.white);
			firsttaskview.addImage(Champion4House,1123 , 701, 400, 50);
			firsttaskview.addImage(Champion4Name, 1123, 672, 400, 50);
			//------------ End Adding Names And houses
		}
		else if ( champArr.get(3) instanceof RavenclawWizard){
			firsttaskview.addImage(Raven3, 1020, 672, 52, 79);
			//----------- Add Names And houses
			Champion4Name = new JLabel(((RavenclawWizard)(champArr.get(3))).getName());
			Champion4House = new JLabel("Ravenclaw");
			setFont(Champion4Name,"ParryHotter.ttf");
			setFont(Champion4House,"ParryHotter.ttf");
			Champion4Name.setForeground(Color.white);
			Champion4House.setForeground(Color.white);
			firsttaskview.addImage(Champion4House,1123 , 701, 400, 50);
			firsttaskview.addImage(Champion4Name, 1123, 672, 400, 50);
			//------------ End Adding Names And houses
		}

	}

	public void setFont(JLabel txt , String path){
		try{
			Font font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File(path))).deriveFont(Font.PLAIN, 12);
			txt.setFont(font);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}

	}


	public void generateMapImages(Cell[][] map){


		for(int i = 0; i<10;i++){
			for(int j = 0 ; j<10; j++){
				if(map[i][j] instanceof ChampionCell){
					if ((((ChampionCell)map[i][j]).getChamp()) instanceof GryffindorWizard){
						
						JLabel ChampionImage = new JLabel(new ImageIcon(FirstTaskController.class.getResource("GryyCell.png")));
						/*if(((ChampionCell)map[i][j]).getChamp() == tournament.getFirstTask().getCurrentChamp())
							ChampionImage = new JLabel(new ImageIcon(FirstTaskController.class.getResource("GryyCellBold.png")));*/
						ChampionImage.setName(i+""+j);
						firsttaskview.addImage(ChampionImage, j*77, i*77, 77, 77);
						imageMap[i][j] = ChampionImage;
					}
					else if ((((ChampionCell)map[i][j]).getChamp()) instanceof HufflepuffWizard){
						JLabel ChampionImage = new JLabel(new ImageIcon(FirstTaskController.class.getResource("HuffleCell.png")));
						ChampionImage.setBackground(Color.BLUE);

						/*if(((ChampionCell)map[i][j]).getChamp() == tournament.getFirstTask().getCurrentChamp())
							ChampionImage = new JLabel(new ImageIcon(FirstTaskController.class.getResource("HuffleCellBold.png")));*/
						ChampionImage.setName(i+""+j);
						firsttaskview.addImage(ChampionImage, j*77, i*77, 77, 77);
						imageMap[i][j] = ChampionImage;
					}
					else if ((((ChampionCell)map[i][j]).getChamp()) instanceof SlytherinWizard){
						JLabel ChampionImage = new JLabel(new ImageIcon(FirstTaskController.class.getResource("SlythCell.png")));

						/*if(((ChampionCell)map[i][j]).getChamp() == tournament.getFirstTask().getCurrentChamp())
							ChampionImage = new JLabel(new ImageIcon(FirstTaskController.class.getResource("SlythCellBold.png")));*/
						ChampionImage.setName(i+""+j);
						
						firsttaskview.addImage(ChampionImage, j*77, i*77, 77, 77);
						imageMap[i][j] = ChampionImage;
					}
					else if ((((ChampionCell)map[i][j]).getChamp()) instanceof RavenclawWizard){
						JLabel ChampionImage = new JLabel(new ImageIcon(FirstTaskController.class.getResource("RavenCell.png")));

						/*if(((ChampionCell)map[i][j]).getChamp() == tournament.getFirstTask().getCurrentChamp())
							ChampionImage = new JLabel(new ImageIcon(FirstTaskController.class.getResource("RavenCellBold.png")));*/
						ChampionImage.setName(i+""+j);
						firsttaskview.addImage(ChampionImage, j*77, i*77, 77, 77);
						imageMap[i][j] = ChampionImage;
					}

				}
				else if(map[i][j] instanceof ObstacleCell){
					JLabel tmp = new JLabel(new ImageIcon(FirstTaskController.class.getResource("Obstacle.png")));
					tmp.setName(i+""+j);
					firsttaskview.addImage(tmp, j*77, i*77, 77, 77);
					imageMap[i][j] = tmp;
					imageMap[i][j].setToolTipText("HP: "+((ObstacleCell)map[i][j]).getObstacle().getHp());
				}
				else if(map[i][j] instanceof EmptyCell){
					JLabel tmp = new JLabel(new ImageIcon(FirstTaskController.class.getResource("EmptyCell.png")));
					tmp.setName(i+""+j);
					firsttaskview.addImage(tmp, j*77, i*77, 77, 77);
					imageMap[i][j] = tmp;
				}
				else if (map[i][j] instanceof CollectibleCell){
					JLabel tmp = new JLabel(new ImageIcon(FirstTaskController.class.getResource("CollectibleCell.png")));
					tmp.setName(i+""+j);
					firsttaskview.addImage(tmp, j*77, i*77, 77, 77);
					imageMap[i][j] = tmp;
					potions++;

				}


			}
		}







	}

	public void updateCurrentChampionInfo(Champion champ){

		group.add(spell1Box);
		group.add(spell2Box);
		group.add(spell3Box);

		/// ADDING to View 
		firsttaskview.addImage(ChampionImage ,825, 7, 125, 298);
		firsttaskview.addImage(Name, 1093, 22, 200, 50);
		firsttaskview.addImage(House, 1093, 78, 200, 50);
		firsttaskview.addImage(spell1, 1163, 140, 200, 30);
		firsttaskview.addImage(spell2, 1163, 168, 200, 30);
		firsttaskview.addImage(spell3, 1163, 196, 200, 30);
		firsttaskview.addProgressBar(ipBar, 1074, 288, 200, 25);
		firsttaskview.addProgressBar(hpBar, 1074, 235, 200, 25);


		if(champ instanceof GryffindorWizard){
			ChampionImage.setIcon(new ImageIcon(FirstTaskController.class.getResource("GryyCurrentChamp.png")));

			Name.setText(((GryffindorWizard)champ).getName());
			House.setText("Gryffindor");
			setFont(Name,"ParryHotter.ttf",20);
			Name.setForeground(Color.WHITE);
			setFont(House,"ParryHotter.ttf",20);
			House.setForeground(Color.WHITE);

			////
			spell1.setText(((GryffindorWizard) champ).getSpells().get(0).getName());
			spell2.setText(((GryffindorWizard) champ).getSpells().get(1).getName());
			spell3.setText(((GryffindorWizard) champ).getSpells().get(2).getName());

			TipSpell(spell1, (Wizard)champ,0);
			TipSpell(spell2, (Wizard)champ,1);
			TipSpell(spell3, (Wizard)champ,2);

			setFont(spell1,"ParryHotter.ttf");
			setFont(spell2,"ParryHotter.ttf");
			setFont(spell3,"ParryHotter.ttf");
			spell1.setForeground(Color.white);
			spell2.setForeground(Color.white);
			spell3.setForeground(Color.white);
			///



			spell1Box.setName(spell1.getName());
			spell2Box.setName(spell2.getName());
			spell3Box.setName(spell3.getName());

			spell1Box.setBackground(new Color(121,183,183));
			spell2Box.setBackground(new Color(121,183,183));
			spell3Box.setBackground(new Color(121,183,183));

			firsttaskview.addCheckBox(spell1Box, 1130, 140, 30, 30);
			firsttaskview.addCheckBox(spell2Box, 1130, 168, 30, 30);
			firsttaskview.addCheckBox(spell3Box, 1130, 196, 30, 30);


			//Adding Progress Bar

			hpBar.setStringPainted(true);
			int ratio  = (((((GryffindorWizard) champ).getHp())*100)/(((GryffindorWizard) champ).getDefaultHp()));

			hpBar.setValue(ratio);
			hpBar.setBorderPainted(false);
			hpBar.setBackground(Color.WHITE);
			hpBar.setForeground(new Color(112,168,59));

			/// IP Bar

			ipBar.setStringPainted(true);
			int ratio2 = (((((GryffindorWizard) champ).getIp())*100)/(((GryffindorWizard) champ).getDefaultIp()));
			ipBar.setValue(ratio2);
			ipBar.setBorderPainted(false);
			ipBar.setBackground(Color.WHITE);
			ipBar.setForeground(new Color(138,52,65));
			hpBar.setToolTipText("HP: "+ ((Wizard)champ).getHp());
			ipBar.setToolTipText("IP: "+ ((Wizard)champ).getIp());
		}



		else if(champ instanceof HufflepuffWizard){

			ChampionImage.setIcon(new ImageIcon(FirstTaskController.class.getResource("HuffleCurrentChamp.png")));

			Name.setText(((HufflepuffWizard)champ).getName());
			House.setText("Hufflepuf");
			setFont(Name,"ParryHotter.ttf",20);
			Name.setForeground(Color.WHITE);
			setFont(House,"ParryHotter.ttf",20);
			House.setForeground(Color.WHITE);
			////
			spell1.setText(((HufflepuffWizard) champ).getSpells().get(0).getName());
			spell2.setText(((HufflepuffWizard) champ).getSpells().get(1).getName());
			spell3.setText(((HufflepuffWizard) champ).getSpells().get(2).getName());
			setFont(spell1,"ParryHotter.ttf");
			setFont(spell2,"ParryHotter.ttf");
			setFont(spell3,"ParryHotter.ttf");
			spell1.setForeground(Color.white);
			spell2.setForeground(Color.white);
			spell3.setForeground(Color.white);
			///
			TipSpell(spell1, (Wizard)champ,0);
			TipSpell(spell2, (Wizard)champ,1);
			TipSpell(spell3, (Wizard)champ,2);


			spell1Box.setName(spell1.getName());
			spell2Box.setName(spell2.getName());
			spell3Box.setName(spell3.getName());

			spell1Box.setBackground(new Color(121,183,183));
			spell2Box.setBackground(new Color(121,183,183));
			spell3Box.setBackground(new Color(121,183,183));

			firsttaskview.addCheckBox(spell1Box, 1130, 140, 30, 30);
			firsttaskview.addCheckBox(spell2Box, 1130, 168, 30, 30);
			firsttaskview.addCheckBox(spell3Box, 1130, 196, 30, 30);

			//Adding Progress Bar

			hpBar.setStringPainted(true);
			int ratio  = (((((HufflepuffWizard) champ).getHp())*100)/(((HufflepuffWizard) champ).getDefaultHp()));
			hpBar.setValue(ratio);
			hpBar.setBorderPainted(false);
			hpBar.setBackground(Color.WHITE);
			hpBar.setForeground(new Color(112,168,59));

			/// IP Bar

			ipBar.setStringPainted(true);
			int ratio2 = (((((HufflepuffWizard) champ).getIp())*100)/(((HufflepuffWizard) champ).getDefaultIp()));
			ipBar.setValue(ratio2);
			ipBar.setBorderPainted(false);
			ipBar.setBackground(Color.WHITE);
			ipBar.setForeground(new Color(138,52,65));
			hpBar.setToolTipText("HP: "+ ((Wizard)champ).getHp());
			ipBar.setToolTipText("IP: "+ ((Wizard)champ).getIp());

		}


		else if(champ instanceof SlytherinWizard){
			ChampionImage.setIcon(new ImageIcon(FirstTaskController.class.getResource("SlythCurrentChamp.png")));

			Name.setText(((SlytherinWizard)champ).getName());
			House.setText("Slytherin");
			setFont(Name,"ParryHotter.ttf",20);
			Name.setForeground(Color.WHITE);
			setFont(House,"ParryHotter.ttf",20);
			House.setForeground(Color.WHITE);
			////
			spell1.setText(((SlytherinWizard) champ).getSpells().get(0).getName());
			spell2.setText(((SlytherinWizard) champ).getSpells().get(1).getName());
			spell3.setText(((SlytherinWizard) champ).getSpells().get(2).getName());
			setFont(spell1,"ParryHotter.ttf");
			setFont(spell2,"ParryHotter.ttf");
			setFont(spell3,"ParryHotter.ttf");
			spell1.setForeground(Color.white);
			spell2.setForeground(Color.white);
			spell3.setForeground(Color.white);
			///
			TipSpell(spell1, (Wizard)champ,0);
			TipSpell(spell2, (Wizard)champ,1);
			TipSpell(spell3, (Wizard)champ,2);

			spell1Box.setName(spell1.getName());
			spell2Box.setName(spell2.getName());
			spell3Box.setName(spell3.getName());

			spell1Box.setBackground(new Color(121,183,183));
			spell2Box.setBackground(new Color(121,183,183));
			spell3Box.setBackground(new Color(121,183,183));

			firsttaskview.addCheckBox(spell1Box, 1130, 140, 30, 30);
			firsttaskview.addCheckBox(spell2Box, 1130, 168, 30, 30);
			firsttaskview.addCheckBox(spell3Box, 1130, 196, 30, 30);

			//Adding Progress Bar

			hpBar.setStringPainted(true);
			int ratio  = (((((SlytherinWizard) champ).getHp())*100)/(((SlytherinWizard) champ).getDefaultHp()));
			hpBar.setValue(ratio);
			hpBar.setBorderPainted(false);
			hpBar.setBackground(Color.WHITE);
			hpBar.setForeground(new Color(112,168,59));

			/// IP Bar

			ipBar.setStringPainted(true);
			int ratio2 = (((((SlytherinWizard) champ).getIp())*100)/(((SlytherinWizard) champ).getDefaultIp()));
			ipBar.setValue(ratio2);
			ipBar.setBorderPainted(false);
			ipBar.setBackground(Color.WHITE);
			ipBar.setForeground(new Color(138,52,65));

			hpBar.setToolTipText("HP: "+ ((Wizard)champ).getHp());
			ipBar.setToolTipText("IP: "+ ((Wizard)champ).getIp());

		}


		else if(champ instanceof RavenclawWizard){
			ChampionImage.setIcon(new ImageIcon(FirstTaskController.class.getResource("RavenCurrentChamp.png")));

			Name.setText(((RavenclawWizard)champ).getName());
			House.setText("Ravenclaw");
			setFont(Name,"ParryHotter.ttf",20);
			Name.setForeground(Color.WHITE);
			setFont(House,"ParryHotter.ttf",20);
			House.setForeground(Color.WHITE);
			////
			spell1.setText(((RavenclawWizard) champ).getSpells().get(0).getName());
			spell2.setText(((RavenclawWizard) champ).getSpells().get(1).getName());
			spell3.setText(((RavenclawWizard) champ).getSpells().get(2).getName());
			setFont(spell1,"ParryHotter.ttf");
			setFont(spell2,"ParryHotter.ttf");
			setFont(spell3,"ParryHotter.ttf");
			spell1.setForeground(Color.white);
			spell2.setForeground(Color.white);
			spell3.setForeground(Color.white);
			///
			TipSpell(spell1, (Wizard)champ,0);
			TipSpell(spell2, (Wizard)champ,1);
			TipSpell(spell3, (Wizard)champ,2);

			spell1Box.setName(spell1.getName());
			spell2Box.setName(spell2.getName());
			spell3Box.setName(spell3.getName());

			spell1Box.setBackground(new Color(121,183,183));
			spell2Box.setBackground(new Color(121,183,183));
			spell3Box.setBackground(new Color(121,183,183));

			firsttaskview.addCheckBox(spell1Box, 1130, 140, 30, 30);
			firsttaskview.addCheckBox(spell2Box, 1130, 168, 30, 30);
			firsttaskview.addCheckBox(spell3Box, 1130, 196, 30, 30);

			//Adding Progress Bar

			hpBar.setStringPainted(true);
			int ratio  = (((((RavenclawWizard) champ).getHp())*100)/(((RavenclawWizard) champ).getDefaultHp()));
			hpBar.setValue(ratio);
			hpBar.setBorderPainted(false);
			hpBar.setBackground(Color.WHITE);
			hpBar.setForeground(new Color(112,168,59));

			/// IP Bar

			ipBar.setStringPainted(true);
			int ratio2 = (((((RavenclawWizard) champ).getIp())*100)/(((RavenclawWizard) champ).getDefaultIp()));
			ipBar.setValue(ratio2);
			ipBar.setBorderPainted(false);
			ipBar.setBackground(Color.WHITE);
			ipBar.setForeground(new Color(138,52,65));

			hpBar.setToolTipText("HP: "+ ((Wizard)champ).getHp());
			ipBar.setToolTipText("IP: "+ ((Wizard)champ).getIp());

		}


	}
	public void setFont(JLabel txt , String path ,int size){
		try{
			Font font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File(path))).deriveFont(Font.PLAIN, size);
			txt.setFont(font);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}

	}

	public void updateStateArea(){
		remainingMoves.setText(firsttask.getAllowedMoves()+"");
		remainingMoves.setForeground(Color.white);
		setFont(remainingMoves,"ParryHotter.ttf",34);
		firsttaskview.addImage(remainingMoves, 946, 330, 50, 50);
		firsttaskview.addImage(traitActivatedSign, 930, 364, 42, 43);
		if(firsttask.isTraitActivated())
			traitActivatedSign.setIcon(new ImageIcon(FirstTaskController.class.getResource("CorrectSign.png")));
		else
			traitActivatedSign.setIcon(new ImageIcon(FirstTaskController.class.getResource("CrossSign.png")));



	}

	public void updateMap(){
		int k=0;
		for(int i = 0 ; i<10 ; i++){
			for(int j=0 ; j<10 ;j++){

				if(map[i][j] instanceof EmptyCell){
					imageMap[i][j].setIcon(new ImageIcon(FirstTaskController.class.getResource("EmptyCell.png")));
				}
				else if(map[i][j] instanceof CollectibleCell){
					k++;
				}
				else if (map[i][j] instanceof ObstacleCell){
					imageMap[i][j].setIcon(new ImageIcon(FirstTaskController.class.getResource("Obstacle.png")));
					imageMap[i][j].setToolTipText("HP: "+((ObstacleCell)map[i][j]).getObstacle().getHp());
				}
				else if (map[i][j] instanceof ChampionCell){
					if(((ChampionCell)map[i][j]).getChamp() instanceof GryffindorWizard){
						imageMap[i][j].setIcon(new ImageIcon(FirstTaskController.class.getResource("GryyCell.png")));
					}
					else if(((ChampionCell)map[i][j]).getChamp() instanceof HufflepuffWizard){
						imageMap[i][j].setIcon(new ImageIcon(FirstTaskController.class.getResource("HuffleCell.png")));
					}
					else if(((ChampionCell)map[i][j]).getChamp() instanceof SlytherinWizard){
						imageMap[i][j].setIcon(new ImageIcon(FirstTaskController.class.getResource("SlythCell.png")));
					}
					else if(((ChampionCell)map[i][j]).getChamp() instanceof RavenclawWizard){
						imageMap[i][j].setIcon(new ImageIcon(FirstTaskController.class.getResource("RavenCell.png")));
					}
				}


			}
		}
		if(k!=potions){
			JOptionPane.showMessageDialog(null, "You have got a potion of value " +((Potion) wiz.getInventory().get(wiz.getInventory().size()-1)).getAmount());
			potions--;
		}


	}	

	public void TipSpell(JLabel spell1,Wizard champ,int i ){

		if(champ.getSpells().get(i) instanceof HealingSpell){
			spell1.setToolTipText("HEALING"+ "  "+"Cost: "+((HealingSpell)champ.getSpells().get(i)).getCost()
					+"  "+"Amount: "+((HealingSpell)champ.getSpells().get(i)).getHealingAmount()
					+"  "+"CoolDown: "+((HealingSpell)champ.getSpells().get(i)).getCoolDown());
		}
		if(champ.getSpells().get(i) instanceof RelocatingSpell){
			spell1.setToolTipText("Relocating   "+"Cost: "+((RelocatingSpell)champ.getSpells().get(i)).getCost()
					+" "+"Range: "+((RelocatingSpell)champ.getSpells().get(i)).getRange()
					+"  "+"CoolDown: "+((RelocatingSpell)champ.getSpells().get(i)).getCoolDown());
		}
		if(champ.getSpells().get(i) instanceof DamagingSpell){
			spell1.setToolTipText("Damaging   "+"Cost: "+((DamagingSpell)champ.getSpells().get(i)).getCost()
					+"  "+"Amount: "+((DamagingSpell)champ.getSpells().get(i)).getDamageAmount()
					+"  "+"CoolDown: "+((DamagingSpell)champ.getSpells().get(i)).getCoolDown());
		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {









		wiz=(Wizard) firsttask.getCurrentChamp();
		Boolean flag=false;
		if(e.getSource() instanceof JLabel && ((JLabel)e.getSource()).getName()=="RightArrow"){
			try {
				if(!(wiz instanceof HufflepuffWizard&&firsttask.isTraitActivated()))
					flag=true;
				firsttask.moveRight();
				if(flag)
					JOptionPane.showMessageDialog(null, "Fire!");
				
			} catch (OutOfBordersException | InvalidTargetCellException | IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			updateMap();
			updateCurrentChampionInfo(firsttask.getCurrentChamp());
			updateStateArea();
			if(tournament.getSecondTask()!=null){
				try {
					new SecondTaskController(tournament);
					firsttaskview.setVisible(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}if(tournament.getSecondTask()==null&&tournament.isGameover()){
				JOptionPane.showMessageDialog(null, "GAME OVER :(");
				new ChooseChampionsController();
				firsttaskview.setVisible(false);
			}





		}
		if(e.getSource() instanceof JLabel && ((JLabel)e.getSource()).getName()=="LeftArrow"){

			try {if(!(wiz instanceof HufflepuffWizard&&firsttask.isTraitActivated()))
				flag=true;
			firsttask.moveLeft();
			if(flag)
				JOptionPane.showMessageDialog(null, "Fire!");
			} catch (OutOfBordersException | InvalidTargetCellException | IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			updateMap();
			updateCurrentChampionInfo(firsttask.getCurrentChamp());
			updateStateArea();
			if(tournament.getSecondTask()!=null){
				try {
					new SecondTaskController(tournament);
					firsttaskview.setVisible(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}if(tournament.getSecondTask()==null&&tournament.isGameover()){
				JOptionPane.showMessageDialog(null, "GAME OVER :(");

				new ChooseChampionsController();
				firsttaskview.setVisible(false);
			}


		}

		if(e.getSource() instanceof JLabel && ((JLabel)e.getSource()).getName()=="ForwardArrow"){

			try {
				if(!(wiz instanceof HufflepuffWizard&&firsttask.isTraitActivated()))
					flag=true;
				firsttask.moveForward();
				if(flag)
					JOptionPane.showMessageDialog(null, "Fire!");
			} catch (OutOfBordersException | InvalidTargetCellException | IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			updateMap();
			updateCurrentChampionInfo(firsttask.getCurrentChamp());
			updateStateArea();
			if(tournament.getSecondTask()!=null){
				try {
					new SecondTaskController(tournament);
					firsttaskview.setVisible(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}if(tournament.getSecondTask()==null&&tournament.isGameover()){
				JOptionPane.showMessageDialog(null, "GAME OVER :(");

				new ChooseChampionsController();
				firsttaskview.setVisible(false);
			}




		}
		if(e.getSource() instanceof JLabel && ((JLabel)e.getSource()).getName()=="BackwardArrow"){

			try {
				if(!(wiz instanceof HufflepuffWizard&&firsttask.isTraitActivated()))
					flag=true;
				firsttask.moveBackward();
				if(flag)
					JOptionPane.showMessageDialog(null, "Fire!");
			} catch (OutOfBordersException | InvalidTargetCellException | IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			updateMap();
			updateCurrentChampionInfo(firsttask.getCurrentChamp());
			updateStateArea();
			if(tournament.getSecondTask()!=null){
				try {
					new SecondTaskController(tournament);
					firsttaskview.setVisible(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}if(tournament.getSecondTask()==null&&tournament.isGameover()){
				JOptionPane.showMessageDialog(null, "GAME OVER :(");

				new ChooseChampionsController();
				firsttaskview.setVisible(false);
			}



		}

		if(e.getSource() instanceof JLabel&& ((JLabel)e.getSource()).getName()=="CastSpellbtn"){

			
			if(!(wiz instanceof HufflepuffWizard&&firsttask.isTraitActivated()))
				flag=true;
			Wizard current = (Wizard) firsttask.getCurrentChamp();
			Direction[] optionss = {Direction.BACKWARD , Direction.FORWARD ,Direction.LEFT ,Direction.RIGHT };
			JFrame damagintDialog = new  JFrame();

			if(spell1Box.isSelected()){

				//--- Cast DamagingSpell
				if(current.getSpells().get(0) instanceof DamagingSpell){

					Direction directionDamaging =(Direction)JOptionPane.showInputDialog(damagintDialog, "Choose Direction : ", "Choose Direction", JOptionPane.QUESTION_MESSAGE, null, optionss, optionss[0]);

					try {
						firsttask.castDamagingSpell((DamagingSpell)current.getSpells().get(0), directionDamaging);
					} catch (InCooldownException | NotEnoughIPException | OutOfBordersException
							| InvalidTargetCellException | IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					updateMap();
					updateCurrentChampionInfo(firsttask.getCurrentChamp());
					updateStateArea();


				}

				//---- Cast RelocatingSpell

				if(current.getSpells().get(0) instanceof RelocatingSpell){
					Direction non =(Direction)JOptionPane.showInputDialog(damagintDialog, "Choose Target Direction : ", "Choose Target Direction", JOptionPane.QUESTION_MESSAGE, null, optionss, optionss[0]);
					Direction target = (Direction)JOptionPane.showInputDialog(damagintDialog, "Choose Movement Direction : ", "Choose Movement Direction", JOptionPane.QUESTION_MESSAGE, null, optionss, optionss[0]);
					String Range = JOptionPane.showInputDialog("Type Range  : ");
					try {
						firsttask.castRelocatingSpell((RelocatingSpell)current.getSpells().get(0), non, target,Integer.parseInt(Range));
					} catch (InCooldownException | NotEnoughIPException | NumberFormatException | OutOfBordersException
							| InvalidTargetCellException | OutOfRangeException | IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					updateMap();
					updateCurrentChampionInfo(firsttask.getCurrentChamp());
					updateStateArea();

				}

				//---- Cast HealingSpell

				if (current.getSpells().get(0) instanceof HealingSpell){
					try {
						firsttask.castHealingSpell((HealingSpell)current.getSpells().get(0));
					} catch (InCooldownException | NotEnoughIPException | IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					updateMap();
					updateCurrentChampionInfo(firsttask.getCurrentChamp());
					updateStateArea();

				}

			}

			//------------------------------------------------

			if(spell2Box.isSelected()){

				//--- Cast DamagingSpell

				if(current.getSpells().get(1) instanceof DamagingSpell){	
					Direction directionDamaging =(Direction)JOptionPane.showInputDialog(damagintDialog, "Choose Direction : ", "Choose Direction", JOptionPane.QUESTION_MESSAGE, null, optionss, optionss[0]);	
					try {
						firsttask.castDamagingSpell((DamagingSpell)current.getSpells().get(1), directionDamaging);
					} catch (InCooldownException | NotEnoughIPException | OutOfBordersException
							| InvalidTargetCellException | IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					updateMap();
					updateCurrentChampionInfo(firsttask.getCurrentChamp());
					updateStateArea();
				}

				//--- Cast RelocatingSpell

				if(current.getSpells().get(1) instanceof RelocatingSpell){
					Direction non =(Direction)JOptionPane.showInputDialog(damagintDialog, "Choose Target Direction : ", "Choose Target Direction", JOptionPane.QUESTION_MESSAGE, null, optionss, optionss[0]);
					Direction target = (Direction)JOptionPane.showInputDialog(damagintDialog, "Choose Movement Direction : ", "Choose Movement Direction", JOptionPane.QUESTION_MESSAGE, null, optionss, optionss[0]);
					String Range = JOptionPane.showInputDialog("Type Range  : ");
					try {
						firsttask.castRelocatingSpell((RelocatingSpell)current.getSpells().get(1), non, target,Integer.parseInt(Range));
					} catch (InCooldownException | NotEnoughIPException | NumberFormatException | OutOfBordersException
							| InvalidTargetCellException | OutOfRangeException | IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					updateMap();
					updateCurrentChampionInfo(firsttask.getCurrentChamp());
					updateStateArea();

				}

				//---- Cast HealingSpell

				if (current.getSpells().get(1) instanceof HealingSpell){
					try {
						firsttask.castHealingSpell((HealingSpell)current.getSpells().get(1));
					} catch (InCooldownException | NotEnoughIPException | IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					updateMap();
					updateCurrentChampionInfo(firsttask.getCurrentChamp());
					updateStateArea();
				}

			}

			//---------------------------------------------------------

			if(spell3Box.isSelected()){

				//--- Cast DamagingSpell

				if(current.getSpells().get(2) instanceof DamagingSpell){
					Direction directionDamaging =(Direction)JOptionPane.showInputDialog(damagintDialog, "Choose Direction : ", "Choose Direction", JOptionPane.QUESTION_MESSAGE, null, optionss, optionss[0]);

					try {
						firsttask.castDamagingSpell((DamagingSpell)current.getSpells().get(2), directionDamaging);
					} catch (InCooldownException | NotEnoughIPException | OutOfBordersException
							| InvalidTargetCellException | IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}

					updateMap();
					updateCurrentChampionInfo(firsttask.getCurrentChamp());
					updateStateArea();
				}

				//--- Cast RelocatingSpell

				if(current.getSpells().get(2) instanceof RelocatingSpell){
					Direction non =(Direction)JOptionPane.showInputDialog(damagintDialog, "Choose Target Direction : ", "Choose Target Direction", JOptionPane.QUESTION_MESSAGE, null, optionss, optionss[0]);
					Direction target = (Direction)JOptionPane.showInputDialog(damagintDialog, "Choose Movement Direction : ", "Choose Movement Direction", JOptionPane.QUESTION_MESSAGE, null, optionss, optionss[0]);
					String Range = JOptionPane.showInputDialog("Type Range  : ");
					try {
						firsttask.castRelocatingSpell((RelocatingSpell)current.getSpells().get(2), non, target,Integer.parseInt(Range));
					} catch (InCooldownException | NotEnoughIPException | NumberFormatException | OutOfBordersException
							| InvalidTargetCellException | OutOfRangeException | IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					updateMap();
					updateCurrentChampionInfo(firsttask.getCurrentChamp());
					updateStateArea();

				}

				//---- Cast HealingSpell

				if (current.getSpells().get(2) instanceof HealingSpell){
					try {
						firsttask.castHealingSpell((HealingSpell)current.getSpells().get(2));
					} catch (InCooldownException | NotEnoughIPException | IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					updateMap();
					updateCurrentChampionInfo(firsttask.getCurrentChamp());
					updateStateArea();

				}

			}if(tournament.getSecondTask()!=null){
				try {
					new SecondTaskController(tournament);
					firsttaskview.setVisible(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}if(tournament.getSecondTask()==null&&tournament.isGameover()){
				JOptionPane.showMessageDialog(null, "GAME OVER :(");

				new ChooseChampionsController();
				firsttaskview.setVisible(false);
			}


		}

		if(e.getSource() instanceof JLabel && ((JLabel)e.getSource()).getName().equals("ActivateTraitbtn")){

			if(firsttask.getCurrentChamp() instanceof GryffindorWizard){
				try {
					firsttask.onGryffindorTrait();
				} catch (InCooldownException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				updateStateArea();
			}
			if(firsttask.getCurrentChamp() instanceof SlytherinWizard){
				Direction[] optionsTrait = {Direction.BACKWARD , Direction.FORWARD ,Direction.LEFT ,Direction.RIGHT };
				JFrame TraitDialog = new JFrame();
				Direction option =(Direction)JOptionPane.showInputDialog(TraitDialog, "Choose Direction : ", "Choose Direction", JOptionPane.QUESTION_MESSAGE, null, optionsTrait, optionsTrait[0]);
				try {
					firsttask.onSlytherinTrait(option);
				} catch (InCooldownException | OutOfBordersException | InvalidTargetCellException | IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				updateMap();
				updateCurrentChampionInfo(firsttask.getCurrentChamp());
				updateStateArea();

			}

			if(firsttask.getCurrentChamp() instanceof RavenclawWizard){

				try {
					@SuppressWarnings({ "unchecked"})
					ArrayList<Point> MarkedCells = (ArrayList<Point>) firsttask.onRavenclawTrait();
					for(int i = 0 ; i<MarkedCells.size();i++){
						imageMap[MarkedCells.get(i).x][MarkedCells.get(i).y].setIcon(new ImageIcon(FirstTaskController.class.getResource("Fire.png")));
					}	
				} catch (InCooldownException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				updateStateArea();
				//traitActivatedSign= new JLabel(new ImageIcon(FirstTaskController.class.getResource("CorrectSign.png")));

			}

			if(firsttask.getCurrentChamp() instanceof HufflepuffWizard){
				try {
					firsttask.onHufflepuffTrait();
					updateStateArea();
				} catch (InCooldownException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
			if(tournament.getSecondTask()!=null){
				try {
					new SecondTaskController(tournament);
					firsttaskview.setVisible(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}if(tournament.getSecondTask()==null&&tournament.isGameover()){
				JOptionPane.showMessageDialog(null, "GAME OVER :(");

				new ChooseChampionsController();
				firsttaskview.setVisible(false);
			}
			
			
			if(flag)
				JOptionPane.showMessageDialog(null, "Fire!");
		}
		if(e.getSource() instanceof JLabel &&  ((JLabel)e.getSource()).getName().equals("potion") ){
			try{
				tournament.getFirstTask().usePotion((Potion)((Wizard)tournament.getFirstTask().getCurrentChamp()).getInventory().get(0));
				updateStateArea();
				updateCurrentChampionInfo(tournament.getFirstTask().getCurrentChamp());
			}catch(NullPointerException|IndexOutOfBoundsException e1){
				JOptionPane.showMessageDialog(null, "You Don't have Potions");
			}
		}







	}


	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() instanceof JLabel){

			if(((JLabel)e.getSource()).getName()=="RightArrow"){
				RightArrow.setIcon(new ImageIcon(FirstTaskController.class.getResource("RightArrowHover.png"))); 
			}
			if(((JLabel)e.getSource()).getName()=="LeftArrow"){
				LeftArrow.setIcon(new ImageIcon(FirstTaskController.class.getResource("LeftArrowHover.png"))); 
			}
			if(((JLabel)e.getSource()).getName()=="BackwardArrow"){
				BackwardArrow.setIcon(new ImageIcon(FirstTaskController.class.getResource("BackwardArrowHover.png"))); 
			}
			if(((JLabel)e.getSource()).getName()=="ForwardArrow"){
				ForwardArrow.setIcon(new ImageIcon(FirstTaskController.class.getResource("ForwardArrowHover.png"))); 
			}
			if(((JLabel)e.getSource()).getName()=="ActivateTraitbtn"){
				ActivateTraitbtn.setIcon(new ImageIcon(FirstTaskController.class.getResource("ActivateTraitHover.png"))); 
			}
			if(((JLabel)e.getSource()).getName()=="CastSpellbtn"){
				CastSpellbtn.setIcon(new ImageIcon(FirstTaskController.class.getResource("WandHover.png"))); 
			}


		}







	}


	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() instanceof JLabel){

			if(((JLabel)e.getSource()).getName()=="RightArrow"){
				RightArrow.setIcon(new ImageIcon(FirstTaskController.class.getResource("RightArrow.png"))); 
			}
			if(((JLabel)e.getSource()).getName()=="LeftArrow"){
				LeftArrow.setIcon(new ImageIcon(FirstTaskController.class.getResource("LeftArrow.png"))); 
			}
			if(((JLabel)e.getSource()).getName()=="BackwardArrow"){
				BackwardArrow.setIcon(new ImageIcon(FirstTaskController.class.getResource("BackwardArrow.png"))); 
			}
			if(((JLabel)e.getSource()).getName()=="ForwardArrow"){
				ForwardArrow.setIcon(new ImageIcon(FirstTaskController.class.getResource("ForwardArrow.png"))); 
			}

			if(((JLabel)e.getSource()).getName()=="ActivateTraitbtn"){
				ActivateTraitbtn.setIcon(new ImageIcon(FirstTaskController.class.getResource("ActivateTrait.png"))); 
			}
			if(((JLabel)e.getSource()).getName()=="CastSpellbtn"){
				CastSpellbtn.setIcon(new ImageIcon(FirstTaskController.class.getResource("Wand.png"))); 
			}
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