package harrypotter.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
//import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;

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
import harrypotter.model.tournament.SecondTask;
import harrypotter.model.tournament.Tournament;
import harrypotter.model.world.Cell;
import harrypotter.model.world.ChampionCell;
import harrypotter.model.world.CollectibleCell;
import harrypotter.model.world.Direction;
import harrypotter.model.world.EmptyCell;
import harrypotter.model.world.Merperson;
import harrypotter.model.world.ObstacleCell;
import harrypotter.model.world.TreasureCell;
import harrypotter.view.SecondTaskView;

public class SecondTaskController implements MouseListener {
	//Initializes the required Variables 

	SecondTaskView SecondTaskView;
	JLabel RightArrow; JLabel LeftArrow; JLabel BackwardArrow; JLabel ForwardArrow;
	JLabel ActivateTraitbtn; JLabel CastSpellbtn;
	Tournament tournament;
	Cell[][] map;
	JLabel[][] imageMap = new JLabel[10][10];
	SecondTask SecondTask;
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
	ImageIcon mer1=new ImageIcon(SecondTaskController.class.getResource("Mer1.png"));
	ImageIcon mer2=new ImageIcon(SecondTaskController.class.getResource("Mer2.png"));
	ImageIcon mer3=new ImageIcon(SecondTaskController.class.getResource("Mer3.png"));

	JRadioButton spell1Box = new JRadioButton();
	JRadioButton spell2Box = new JRadioButton();
	JRadioButton spell3Box = new JRadioButton();
	ButtonGroup group = new ButtonGroup();



	JLabel usepotion;
	int potions=0;
	Wizard wiz;



	public SecondTaskController(Tournament tournament) throws IOException {

		//Begin Tournament
		this.tournament =tournament;
		SecondTask = tournament.getSecondTask();
		map = SecondTask.getMap();


		// Start Initialize the frame and add direction buttons
		RightArrow       = new JLabel(new ImageIcon(SecondTaskController.class.getResource("RightArrow.png")));
		LeftArrow        = new JLabel(new ImageIcon(SecondTaskController.class.getResource("LeftArrow.png")));
		ForwardArrow     = new JLabel(new ImageIcon(SecondTaskController.class.getResource("ForwardArrow.png")));
		BackwardArrow    = new JLabel(new ImageIcon(SecondTaskController.class.getResource("BackwardArrow.png")));
		ActivateTraitbtn = new JLabel(new ImageIcon(SecondTaskController.class.getResource("ActivateTrait.png")));
		CastSpellbtn     = new JLabel(new ImageIcon(SecondTaskController.class.getResource("Wand.png")));

		SecondTaskView    = new SecondTaskView();
		// End 
		usepotion =new JLabel(new ImageIcon(SecondTaskController.class.getResource("CorrectSign.png")));
		usepotion.addMouseListener(this);
		usepotion.setName("potion");
		SecondTaskView.setFocusable(true);
		SecondTaskView.addusepotion(usepotion);
		//Start Set Names 
		RightArrow.setName("RightArrow");
		LeftArrow.setName("LeftArrow");
		ForwardArrow.setName("ForwardArrow");
		BackwardArrow.setName("BackwardArrow");
		ActivateTraitbtn.setName("ActivateTraitbtn");
		CastSpellbtn.setName("CastSpellbtn");
		//End Set Names




		//Start Adding To View 
		SecondTaskView.addRightArrow(RightArrow);
		SecondTaskView.addLeftArrow(LeftArrow);
		SecondTaskView.addForwardArrow(ForwardArrow);
		SecondTaskView.addBackwardArrow(BackwardArrow);
		SecondTaskView.addActivateTraitbtn(ActivateTraitbtn);
		SecondTaskView.addCastSpellbtn(CastSpellbtn);
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
		updateChampionsInfo(tournament.getSecondTask().getChampions());
		generateMapImages(map);
		updateCurrentChampionInfo(tournament.getSecondTask().getCurrentChamp());
		updateStateArea();
		//End

		SecondTaskView.setVisible(true);

	}


	public void updateChampionsInfo(ArrayList<Champion> champArr){
		// This Method was used once in the constructor <One-Use Method>
		JLabel Gryff   = new JLabel(new ImageIcon(SecondTaskController.class.getResource("GryffChampSmall.png")));
		JLabel Huffle  = new JLabel(new ImageIcon(SecondTaskController.class.getResource("HuffleChampSmall.png")));
		JLabel Slyth   = new JLabel(new ImageIcon(SecondTaskController.class.getResource("SlythChampSmall.png")));
		JLabel Raven   = new JLabel(new ImageIcon(SecondTaskController.class.getResource("RavenChampSmall.png")));

		JLabel Gryff1   = new JLabel(new ImageIcon(SecondTaskController.class.getResource("GryffChampSmall.png")));
		JLabel Huffle1  = new JLabel(new ImageIcon(SecondTaskController.class.getResource("HuffleChampSmall.png")));
		JLabel Slyth1   = new JLabel(new ImageIcon(SecondTaskController.class.getResource("SlythChampSmall.png")));
		JLabel Raven1   = new JLabel(new ImageIcon(SecondTaskController.class.getResource("RavenChampSmall.png")));

		JLabel Gryff2   = new JLabel(new ImageIcon(SecondTaskController.class.getResource("GryffChampSmall.png")));
		JLabel Huffle2  = new JLabel(new ImageIcon(SecondTaskController.class.getResource("HuffleChampSmall.png")));
		JLabel Slyth2   = new JLabel(new ImageIcon(SecondTaskController.class.getResource("SlythChampSmall.png")));
		JLabel Raven2   = new JLabel(new ImageIcon(SecondTaskController.class.getResource("RavenChampSmall.png")));

		JLabel Gryff3   = new JLabel(new ImageIcon(SecondTaskController.class.getResource("GryffChampSmall.png")));
		JLabel Huffle3  = new JLabel(new ImageIcon(SecondTaskController.class.getResource("HuffleChampSmall.png")));
		JLabel Slyth3   = new JLabel(new ImageIcon(SecondTaskController.class.getResource("SlythChampSmall.png")));
		JLabel Raven3   = new JLabel(new ImageIcon(SecondTaskController.class.getResource("RavenChampSmall.png")));

		JLabel Champion1Name;  JLabel Champion2Name; JLabel Champion3Name; JLabel Champion4Name;
		JLabel Champion1House; JLabel Champion2House; JLabel Champion3House; JLabel Champion4House;
		if(champArr.size()>0){
			if ( champArr.get(0) instanceof GryffindorWizard){
				SecondTaskView.addImage(Gryff, 1020, 429, 52, 79);
				//----------- Add Names And houses
				Champion1Name = new JLabel(((GryffindorWizard)(champArr.get(0))).getName());
				Champion1House = new JLabel("Gryffindor");
				setFont(Champion1Name,"ParryHotter.ttf");
				setFont(Champion1House,"ParryHotter.ttf");
				Champion1Name.setForeground(Color.white);
				Champion1House.setForeground(Color.white);
				SecondTaskView.addImage(Champion1House,1123 , 455, 400, 50);
				SecondTaskView.addImage(Champion1Name, 1123, 426, 400, 50);
				//------------ End Adding Names And houses
			}
			else if ( champArr.get(0) instanceof HufflepuffWizard){
				SecondTaskView.addImage(Huffle, 1020, 429, 52, 79);
				//----------- Add Names And houses
				Champion1Name = new JLabel(((HufflepuffWizard)(champArr.get(0))).getName());
				Champion1House = new JLabel("Hufflepuff");
				setFont(Champion1Name,"ParryHotter.ttf");
				setFont(Champion1House,"ParryHotter.ttf");
				Champion1Name.setForeground(Color.white);
				Champion1House.setForeground(Color.white);
				SecondTaskView.addImage(Champion1House,1123 , 455, 400, 50);
				SecondTaskView.addImage(Champion1Name, 1123, 426, 400, 50);
				//------------ End Adding Names And houses
			}
			else if ( champArr.get(0) instanceof SlytherinWizard){
				SecondTaskView.addImage(Slyth, 1020, 429, 52, 79);
				//----------- Add Names And houses
				Champion1Name = new JLabel(((SlytherinWizard)(champArr.get(0))).getName());
				Champion1House = new JLabel("Slytherinpuff");
				setFont(Champion1Name,"ParryHotter.ttf");
				setFont(Champion1House,"ParryHotter.ttf");
				Champion1Name.setForeground(Color.white);
				Champion1House.setForeground(Color.white);
				SecondTaskView.addImage(Champion1House,1123 , 455, 400, 50);
				SecondTaskView.addImage(Champion1Name, 1123, 426, 400, 50);
				//------------ End Adding Names And houses
			}
			else if ( champArr.get(0) instanceof RavenclawWizard){
				SecondTaskView.addImage(Raven, 1020, 429, 52, 79);
				//----------- Add Names And houses
				Champion1Name = new JLabel(((RavenclawWizard)(champArr.get(0))).getName());
				Champion1House = new JLabel("Ravenclaw");
				setFont(Champion1Name,"ParryHotter.ttf");
				setFont(Champion1House,"ParryHotter.ttf");
				Champion1Name.setForeground(Color.white);
				Champion1House.setForeground(Color.white);
				SecondTaskView.addImage(Champion1House,1123 , 455, 400, 50);
				SecondTaskView.addImage(Champion1Name, 1123, 426, 400, 50);
				//------------ End Adding Names And houses
			}
		}



		//------------
		if(champArr.size()>1){
			if ( champArr.get(1) instanceof GryffindorWizard){
				SecondTaskView.addImage(Gryff1, 1020, 509, 52, 79);
				//----------- Add Names And houses
				Champion2Name = new JLabel(((GryffindorWizard)(champArr.get(1))).getName());
				Champion2House = new JLabel("Gryffindor");
				setFont(Champion2Name,"ParryHotter.ttf");
				setFont(Champion2House,"ParryHotter.ttf");
				Champion2Name.setForeground(Color.white);
				Champion2House.setForeground(Color.white);
				SecondTaskView.addImage(Champion2House,1123 , 537, 400, 50);
				SecondTaskView.addImage(Champion2Name, 1123, 508, 400, 50);
				//------------ End Adding Names And houses
			}
			else if ( champArr.get(1) instanceof HufflepuffWizard){
				SecondTaskView.addImage(Huffle1, 1020, 509, 52, 79);
				//----------- Add Names And houses
				Champion2Name = new JLabel(((HufflepuffWizard)(champArr.get(1))).getName());
				Champion2House = new JLabel("Hufflepuff");
				setFont(Champion2Name,"ParryHotter.ttf");
				setFont(Champion2House,"ParryHotter.ttf");
				Champion2Name.setForeground(Color.white);
				Champion2House.setForeground(Color.white);
				SecondTaskView.addImage(Champion2House,1123 , 537, 400, 50);
				SecondTaskView.addImage(Champion2Name, 1123, 508, 400, 50);
				//------------ End Adding Names And houses
			}
			else if ( champArr.get(1) instanceof SlytherinWizard){
				SecondTaskView.addImage(Slyth1, 1020, 509, 52, 79);
				//----------- Add Names And houses
				Champion2Name = new JLabel(((SlytherinWizard)(champArr.get(1))).getName());
				Champion2House = new JLabel("Slytherin");
				setFont(Champion2Name,"ParryHotter.ttf");
				setFont(Champion2House,"ParryHotter.ttf");
				Champion2Name.setForeground(Color.white);
				Champion2House.setForeground(Color.white);
				SecondTaskView.addImage(Champion2House,1123 , 537, 400, 50);
				SecondTaskView.addImage(Champion2Name, 1123, 508, 400, 50);
				//------------ End Adding Names And houses
			}
			else if ( champArr.get(1) instanceof RavenclawWizard){
				SecondTaskView.addImage(Raven1, 1020, 509, 52, 79);
				//----------- Add Names And houses
				Champion2Name = new JLabel(((RavenclawWizard)(champArr.get(1))).getName());
				Champion2House = new JLabel("Ravenclaw");
				setFont(Champion2Name,"ParryHotter.ttf");
				setFont(Champion2House,"ParryHotter.ttf");
				Champion2Name.setForeground(Color.white);
				Champion2House.setForeground(Color.white);
				SecondTaskView.addImage(Champion2House,1123 , 537, 400, 50);
				SecondTaskView.addImage(Champion2Name, 1123, 508, 400, 50);
				//------------ End Adding Names And houses
			}

		}


		//-------------
		if(champArr.size()>2){
			if ( champArr.get(2) instanceof GryffindorWizard){
				SecondTaskView.addImage(Gryff2, 1020, 593, 52, 79);
				//----------- Add Names And houses
				Champion3Name = new JLabel(((GryffindorWizard)(champArr.get(2))).getName());
				Champion3House = new JLabel("Gryffindor");
				setFont(Champion3Name,"ParryHotter.ttf");
				setFont(Champion3House,"ParryHotter.ttf");
				Champion3Name.setForeground(Color.white);
				Champion3House.setForeground(Color.white);
				SecondTaskView.addImage(Champion3House,1123 , 624, 400, 50);
				SecondTaskView.addImage(Champion3Name, 1123, 595, 400, 50);
				//------------ End Adding Names And houses
			}
			else if ( champArr.get(2) instanceof HufflepuffWizard){
				SecondTaskView.addImage(Huffle2, 1020, 593, 52, 79);
				//----------- Add Names And houses
				Champion3Name = new JLabel(((HufflepuffWizard)(champArr.get(2))).getName());
				Champion3House = new JLabel("Hufflepuff");
				setFont(Champion3Name,"ParryHotter.ttf");
				setFont(Champion3House,"ParryHotter.ttf");
				Champion3Name.setForeground(Color.white);
				Champion3House.setForeground(Color.white);
				SecondTaskView.addImage(Champion3House,1123 , 624, 400, 50);
				SecondTaskView.addImage(Champion3Name, 1123, 595, 400, 50);
				//------------ End Adding Names And houses
			}
			else if ( champArr.get(2) instanceof SlytherinWizard){
				SecondTaskView.addImage(Slyth2, 1020, 593, 52, 79);
				//----------- Add Names And houses
				Champion3Name = new JLabel(((SlytherinWizard)(champArr.get(2))).getName());
				Champion3House = new JLabel("Slytherin");
				setFont(Champion3Name,"ParryHotter.ttf");
				setFont(Champion3House,"ParryHotter.ttf");
				Champion3Name.setForeground(Color.white);
				Champion3House.setForeground(Color.white);
				SecondTaskView.addImage(Champion3House,1123 , 624, 400, 50);
				SecondTaskView.addImage(Champion3Name, 1123, 595, 400, 50);
				//------------ End Adding Names And houses
			}
			else if ( champArr.get(2) instanceof RavenclawWizard){
				SecondTaskView.addImage(Raven2, 1020, 593, 52, 79);
				//----------- Add Names And houses
				Champion3Name = new JLabel(((RavenclawWizard)(champArr.get(2))).getName());
				Champion3House = new JLabel("Ravenclaw");
				setFont(Champion3Name,"ParryHotter.ttf");
				setFont(Champion3House,"ParryHotter.ttf");
				Champion3Name.setForeground(Color.white);
				Champion3House.setForeground(Color.white);
				SecondTaskView.addImage(Champion3House,1123 , 624, 400, 50);
				SecondTaskView.addImage(Champion3Name, 1123, 595, 400, 50);
				//------------ End Adding Names And houses
			}
		}


		//-----------
		if(champArr.size()>3){
			if ( champArr.get(3) instanceof GryffindorWizard){
				SecondTaskView.addImage(Gryff3, 1020, 672, 52, 79);
				//----------- Add Names And houses
				Champion4Name = new JLabel(((GryffindorWizard)(champArr.get(3))).getName());
				Champion4House = new JLabel("Gryffindor");
				setFont(Champion4Name,"ParryHotter.ttf");
				setFont(Champion4House,"ParryHotter.ttf");
				Champion4Name.setForeground(Color.white);
				Champion4House.setForeground(Color.white);
				SecondTaskView.addImage(Champion4House,1123 , 701, 400, 50);
				SecondTaskView.addImage(Champion4Name, 1123, 672, 400, 50);
				//------------ End Adding Names And houses
			}
			else if ( champArr.get(3) instanceof HufflepuffWizard){
				SecondTaskView.addImage(Huffle3, 1020, 672, 52, 79);
				//----------- Add Names And houses
				Champion4Name = new JLabel(((HufflepuffWizard)(champArr.get(3))).getName());
				Champion4House = new JLabel("Hufflepuff");
				setFont(Champion4Name,"ParryHotter.ttf");
				setFont(Champion4House,"ParryHotter.ttf");
				Champion4Name.setForeground(Color.white);
				Champion4House.setForeground(Color.white);
				SecondTaskView.addImage(Champion4House,1123 , 701, 400, 50);
				SecondTaskView.addImage(Champion4Name, 1123, 672, 400, 50);
				//------------ End Adding Names And houses
			}
			else if ( champArr.get(3) instanceof SlytherinWizard){
				SecondTaskView.addImage(Slyth3, 1020, 672, 52, 79);
				//----------- Add Names And houses
				Champion4Name = new JLabel(((SlytherinWizard)(champArr.get(3))).getName());
				Champion4House = new JLabel("Slytherin");
				setFont(Champion4Name,"ParryHotter.ttf");
				setFont(Champion4House,"ParryHotter.ttf");
				Champion4Name.setForeground(Color.white);
				Champion4House.setForeground(Color.white);
				SecondTaskView.addImage(Champion4House,1123 , 701, 400, 50);
				SecondTaskView.addImage(Champion4Name, 1123, 672, 400, 50);
				//------------ End Adding Names And houses
			}
			else if ( champArr.get(3) instanceof RavenclawWizard){
				SecondTaskView.addImage(Raven3, 1020, 672, 52, 79);
				//----------- Add Names And houses
				Champion4Name = new JLabel(((RavenclawWizard)(champArr.get(3))).getName());
				Champion4House = new JLabel("Ravenclaw");
				setFont(Champion4Name,"ParryHotter.ttf");
				setFont(Champion4House,"ParryHotter.ttf");
				Champion4Name.setForeground(Color.white);
				Champion4House.setForeground(Color.white);
				SecondTaskView.addImage(Champion4House,1123 , 701, 400, 50);
				SecondTaskView.addImage(Champion4Name, 1123, 672, 400, 50);
				//------------ End Adding Names And houses
			}}



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
						JLabel ChampionImage = new JLabel(new ImageIcon(SecondTaskController.class.getResource("GryyCell.png")));
						ChampionImage.setName(i+""+j);
						SecondTaskView.addImage(ChampionImage, j*77, i*77, 77, 77);
						imageMap[i][j] = ChampionImage;
					}
					else if ((((ChampionCell)map[i][j]).getChamp()) instanceof HufflepuffWizard){
						JLabel ChampionImage = new JLabel(new ImageIcon(SecondTaskController.class.getResource("HuffleCell.png")));
						ChampionImage.setName(i+""+j);
						SecondTaskView.addImage(ChampionImage, j*77, i*77, 77, 77);
						imageMap[i][j] = ChampionImage;
					}
					else if ((((ChampionCell)map[i][j]).getChamp()) instanceof SlytherinWizard){
						JLabel ChampionImage = new JLabel(new ImageIcon(SecondTaskController.class.getResource("SlythCell.png")));
						ChampionImage.setName(i+""+j);
						SecondTaskView.addImage(ChampionImage, j*77, i*77, 77, 77);
						imageMap[i][j] = ChampionImage;
					}
					else if ((((ChampionCell)map[i][j]).getChamp()) instanceof RavenclawWizard){
						JLabel ChampionImage = new JLabel(new ImageIcon(SecondTaskController.class.getResource("RavenCell.png")));
						ChampionImage.setName(i+""+j);
						SecondTaskView.addImage(ChampionImage, j*77, i*77, 77, 77);
						imageMap[i][j] = ChampionImage;
					}

				}
				if(map[i][j] instanceof ObstacleCell){
					int x=(int)(Math.random()*3);
					ImageIcon icon=new ImageIcon();
					if(x==0){

						icon=mer1;}
					if(x==1){

						icon=mer2;}

					if(x==2){

						icon=mer3;}
					JLabel tmp=new JLabel(icon);
					tmp.setName(i+""+j);
					SecondTaskView.addImage(tmp, j*77, i*77, 77, 77);
					imageMap[i][j] = tmp;
					imageMap[i][j].setToolTipText("HP: "+((ObstacleCell)map[i][j]).getObstacle().getHp()+"\n"+"Damage: "+((Merperson)((ObstacleCell)map[i][j]).getObstacle()).getDamage());

				}
				if(map[i][j] instanceof EmptyCell||map[i][j] instanceof CollectibleCell||map[i][j] instanceof TreasureCell){
					JLabel tmp = new JLabel(new ImageIcon(SecondTaskController.class.getResource("EmptyCell.png")));
					tmp.setName(i+""+j);
					SecondTaskView.addImage(tmp, j*77, i*77, 77, 77);
					imageMap[i][j] = tmp;
				}
				if(map[i][j] instanceof CollectibleCell)
					potions++;


			}		}







	}

	public void updateCurrentChampionInfo(Champion champ){

		group.add(spell1Box);
		group.add(spell2Box);
		group.add(spell3Box);

		/// ADDING to View 
		SecondTaskView.addImage(ChampionImage ,825, 7, 125, 298);
		SecondTaskView.addImage(Name, 1093, 22, 200, 50);
		SecondTaskView.addImage(House, 1093, 78, 200, 50);
		SecondTaskView.addImage(spell1, 1163, 140, 200, 30);
		SecondTaskView.addImage(spell2, 1163, 168, 200, 30);
		SecondTaskView.addImage(spell3, 1163, 196, 200, 30);
		SecondTaskView.addProgressBar(ipBar, 1074, 288, 200, 25);
		SecondTaskView.addProgressBar(hpBar, 1074, 235, 200, 25);


		if(champ instanceof GryffindorWizard){
			ChampionImage.setIcon(new ImageIcon(SecondTaskController.class.getResource("GryyCurrentChamp.png")));

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
			setFont(spell1,"ParryHotter.ttf");
			setFont(spell2,"ParryHotter.ttf");
			setFont(spell3,"ParryHotter.ttf");
			spell1.setForeground(Color.white);
			spell2.setForeground(Color.white);
			spell3.setForeground(Color.white);
			///
			TipSpell(spell1,(Wizard) champ,0 );
			TipSpell(spell2,(Wizard) champ,1 );
			TipSpell(spell3,(Wizard) champ,2 );

			spell1Box.setName(spell1.getName());
			spell2Box.setName(spell2.getName());
			spell3Box.setName(spell3.getName());

			spell1Box.setBackground(new Color(121,183,183));
			spell2Box.setBackground(new Color(121,183,183));
			spell3Box.setBackground(new Color(121,183,183));

			SecondTaskView.addCheckBox(spell1Box, 1130, 140, 30, 30);
			SecondTaskView.addCheckBox(spell2Box, 1130, 168, 30, 30);
			SecondTaskView.addCheckBox(spell3Box, 1130, 196, 30, 30);


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

			ChampionImage.setIcon(new ImageIcon(SecondTaskController.class.getResource("HuffleCurrentChamp.png")));

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
			TipSpell(spell1,(Wizard) champ,0 );
			TipSpell(spell2,(Wizard) champ,1 );
			TipSpell(spell3,(Wizard) champ,2 );



			spell1Box.setName(spell1.getName());
			spell2Box.setName(spell2.getName());
			spell3Box.setName(spell3.getName());

			spell1Box.setBackground(new Color(121,183,183));
			spell2Box.setBackground(new Color(121,183,183));
			spell3Box.setBackground(new Color(121,183,183));

			SecondTaskView.addCheckBox(spell1Box, 1130, 140, 30, 30);
			SecondTaskView.addCheckBox(spell2Box, 1130, 168, 30, 30);
			SecondTaskView.addCheckBox(spell3Box, 1130, 196, 30, 30);

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
			ChampionImage.setIcon(new ImageIcon(SecondTaskController.class.getResource("SlythCurrentChamp.png")));

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
			TipSpell(spell1,(Wizard) champ,0 );
			TipSpell(spell2,(Wizard) champ,1 );
			TipSpell(spell3,(Wizard) champ,2 );


			spell1Box.setName(spell1.getName());
			spell2Box.setName(spell2.getName());
			spell3Box.setName(spell3.getName());

			spell1Box.setBackground(new Color(121,183,183));
			spell2Box.setBackground(new Color(121,183,183));
			spell3Box.setBackground(new Color(121,183,183));

			SecondTaskView.addCheckBox(spell1Box, 1130, 140, 30, 30);
			SecondTaskView.addCheckBox(spell2Box, 1130, 168, 30, 30);
			SecondTaskView.addCheckBox(spell3Box, 1130, 196, 30, 30);

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
			ChampionImage.setIcon(new ImageIcon(SecondTaskController.class.getResource("RavenCurrentChamp.png")));

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
			TipSpell(spell1,(Wizard) champ,0 );
			TipSpell(spell2,(Wizard) champ,1 );
			TipSpell(spell3,(Wizard) champ,2 );


			spell1Box.setName(spell1.getName());
			spell2Box.setName(spell2.getName());
			spell3Box.setName(spell3.getName());

			spell1Box.setBackground(new Color(121,183,183));
			spell2Box.setBackground(new Color(121,183,183));
			spell3Box.setBackground(new Color(121,183,183));

			SecondTaskView.addCheckBox(spell1Box, 1130, 140, 30, 30);
			SecondTaskView.addCheckBox(spell2Box, 1130, 168, 30, 30);
			SecondTaskView.addCheckBox(spell3Box, 1130, 196, 30, 30);

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
		remainingMoves.setText(SecondTask.getAllowedMoves()+"");
		remainingMoves.setForeground(Color.white);
		setFont(remainingMoves,"ParryHotter.ttf",34);
		SecondTaskView.addImage(remainingMoves, 946, 330, 50, 50);
		SecondTaskView.addImage(traitActivatedSign, 930, 364, 42, 43);
		if(SecondTask.isTraitActivated())
			traitActivatedSign.setIcon(new ImageIcon(SecondTaskController.class.getResource("CorrectSign.png")));
		else
			traitActivatedSign.setIcon(new ImageIcon(SecondTaskController.class.getResource("CrossSign.png")));



	}

	public void updateMap(){
		int k=0;
		for(int i = 0 ; i<10 ; i++){
			for(int j=0 ; j<10 ;j++){

				if(map[i][j] instanceof CollectibleCell)
					k++;
				if(map[i][j] instanceof EmptyCell||map[i][j] instanceof CollectibleCell||map[i][j] instanceof TreasureCell){
					imageMap[i][j].setIcon(new ImageIcon(SecondTaskController.class.getResource("EmptyCell.png")));
				}
				else if (map[i][j] instanceof ObstacleCell){
					if(imageMap[i][j].getIcon().equals(mer1)||imageMap[i][j].getIcon().equals(mer2)||imageMap[i][j].getIcon().equals(mer3)){
					}
					else{
						int x=(int)(Math.random()*3);
						ImageIcon tmp=new ImageIcon();
						if(x==0)
							tmp=mer1;
						if(x==1)
							tmp=mer2;
						if(x==2)
							tmp=mer3;

						imageMap[i][j].setIcon(tmp);
						imageMap[i][j].setToolTipText("HP: "+((ObstacleCell)map[i][j]).getObstacle().getHp()+"\n"+"Damage: "+((Merperson)((ObstacleCell)map[i][j]).getObstacle()).getDamage());
					}}
				else if (map[i][j] instanceof ChampionCell){
					if(((ChampionCell)map[i][j]).getChamp() instanceof GryffindorWizard){
						imageMap[i][j].setIcon(new ImageIcon(SecondTaskController.class.getResource("GryyCell.png")));
					}
					else if(((ChampionCell)map[i][j]).getChamp() instanceof HufflepuffWizard){
						imageMap[i][j].setIcon(new ImageIcon(SecondTaskController.class.getResource("HuffleCell.png")));
					}
					else if(((ChampionCell)map[i][j]).getChamp() instanceof SlytherinWizard){
						imageMap[i][j].setIcon(new ImageIcon(SecondTaskController.class.getResource("SlythCell.png")));
					}
					else if(((ChampionCell)map[i][j]).getChamp() instanceof RavenclawWizard){
						imageMap[i][j].setIcon(new ImageIcon(SecondTaskController.class.getResource("RavenCell.png")));
					}
				}


			}
		}if(k!=potions){
			JOptionPane.showMessageDialog(null, "You have got potion of Amount "+((Potion) wiz.getInventory().get(wiz.getInventory().size()-1)).getAmount());
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

		wiz=(Wizard)tournament.getSecondTask().getCurrentChamp();
		int old=wiz.getHp();



		/*

		if(tournament.getThirdTask()!=null){
			try {
				new SecondTaskController(tournament);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}*/


		if(e.getSource() instanceof JLabel && ((JLabel)e.getSource()).getName()=="RightArrow"){
			try {
				SecondTask.moveRight();
				if(wiz.getHp()<old){
					JOptionPane.showMessageDialog(null, "You have been attacked by merpersons!");
				}
				
			} catch (OutOfBordersException | InvalidTargetCellException | IOException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());

			}
			updateMap();
			updateCurrentChampionInfo(SecondTask.getCurrentChamp());
			updateStateArea();

			if(tournament.getThirdTask()!=null){
				try {
					new ThirdTaskController(tournament);
					SecondTaskView.setVisible(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}if(tournament.getThirdTask()==null&&tournament.isGameover()){
				JOptionPane.showMessageDialog(null, "GAME OVER :(");

				new ChooseChampionsController();
				SecondTaskView.setVisible(false);
			}




		}
		if(e.getSource() instanceof JLabel && ((JLabel)e.getSource()).getName()=="LeftArrow"){

			try {
				SecondTask.moveLeft();
				if(wiz.getHp()<old){
					JOptionPane.showMessageDialog(null, "You have been attacked by merperson!");
				}
			} catch (OutOfBordersException | InvalidTargetCellException | IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			updateMap();
			updateCurrentChampionInfo(SecondTask.getCurrentChamp());
			updateStateArea();
			if(tournament.getThirdTask()!=null){
				try {
					new ThirdTaskController(tournament);
					SecondTaskView.setVisible(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}if(tournament.getThirdTask()==null&&tournament.isGameover()){
				JOptionPane.showMessageDialog(null, "GAME OVER :(");

				new ChooseChampionsController();
				SecondTaskView.setVisible(false);
			}


		}

		if(e.getSource() instanceof JLabel && ((JLabel)e.getSource()).getName()=="ForwardArrow"){

			try {
				SecondTask.moveForward();
				if(wiz.getHp()<old){
					JOptionPane.showMessageDialog(null, "You have been attacked by merperson!");
				}
			} catch (OutOfBordersException | InvalidTargetCellException | IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			updateMap();
			updateCurrentChampionInfo(SecondTask.getCurrentChamp());
			updateStateArea();
			if(tournament.getThirdTask()!=null){
				try {
					new ThirdTaskController(tournament);
					SecondTaskView.setVisible(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}if(tournament.getThirdTask()==null&&tournament.isGameover()){
				JOptionPane.showMessageDialog(null, "GAME OVER :(");

				new ChooseChampionsController();
				SecondTaskView.setVisible(false);
			}




		}
		if(e.getSource() instanceof JLabel && ((JLabel)e.getSource()).getName()=="BackwardArrow"){

			try {
				SecondTask.moveBackward();
				if(wiz.getHp()<old){
					JOptionPane.showMessageDialog(null, "You have been attacked by merperson!");
				}
			} catch (OutOfBordersException | InvalidTargetCellException | IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			updateMap();
			updateCurrentChampionInfo(SecondTask.getCurrentChamp());
			updateStateArea();
			if(tournament.getThirdTask()!=null){
				try {
					new ThirdTaskController(tournament);
					SecondTaskView.setVisible(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}if(tournament.getThirdTask()==null&&tournament.isGameover()){
				JOptionPane.showMessageDialog(null, "GAME OVER :(");

				new ChooseChampionsController();
				SecondTaskView.setVisible(false);
			}



		}

		if(e.getSource() instanceof JLabel&& ((JLabel)e.getSource()).getName()=="CastSpellbtn"){

			Wizard current = (Wizard) SecondTask.getCurrentChamp();
			Direction[] optionss = {Direction.BACKWARD , Direction.FORWARD ,Direction.LEFT ,Direction.RIGHT };
			JFrame damagintDialog = new  JFrame();

			if(spell1Box.isSelected()){

				//--- Cast DamagingSpell
				if(current.getSpells().get(0) instanceof DamagingSpell){

					Direction directionDamaging =(Direction)JOptionPane.showInputDialog(damagintDialog, "Choose Direction : ", "Choose Direction", JOptionPane.QUESTION_MESSAGE, null, optionss, optionss[0]);

					try {
						SecondTask.castDamagingSpell((DamagingSpell)current.getSpells().get(0), directionDamaging);
					} catch (InCooldownException | NotEnoughIPException | OutOfBordersException
							| InvalidTargetCellException | IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					updateMap();
					updateCurrentChampionInfo(SecondTask.getCurrentChamp());
					updateStateArea();


				}

				//---- Cast RelocatingSpell

				if(current.getSpells().get(0) instanceof RelocatingSpell){
					Direction non =(Direction)JOptionPane.showInputDialog(damagintDialog, "Choose Target Direction : ", "Choose Target Direction", JOptionPane.QUESTION_MESSAGE, null, optionss, optionss[0]);
					Direction target = (Direction)JOptionPane.showInputDialog(damagintDialog, "Choose Movement Direction : ", "Choose Movement Direction", JOptionPane.QUESTION_MESSAGE, null, optionss, optionss[0]);
					String Range = JOptionPane.showInputDialog("Type Range  : ");
					try {
						SecondTask.castRelocatingSpell((RelocatingSpell)current.getSpells().get(0), non, target,Integer.parseInt(Range));
					} catch (InCooldownException | NotEnoughIPException | NumberFormatException | OutOfBordersException
							| InvalidTargetCellException | OutOfRangeException | IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					updateMap();
					updateCurrentChampionInfo(SecondTask.getCurrentChamp());
					updateStateArea();

				}

				//---- Cast HealingSpell

				if (current.getSpells().get(0) instanceof HealingSpell){
					try {
						SecondTask.castHealingSpell((HealingSpell)current.getSpells().get(0));
					} catch (InCooldownException | NotEnoughIPException | IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					updateMap();
					updateCurrentChampionInfo(SecondTask.getCurrentChamp());
					updateStateArea();

				}

			}

			//------------------------------------------------

			if(spell2Box.isSelected()){

				//--- Cast DamagingSpell

				if(current.getSpells().get(1) instanceof DamagingSpell){	
					Direction directionDamaging =(Direction)JOptionPane.showInputDialog(damagintDialog, "Choose Direction : ", "Choose Direction", JOptionPane.QUESTION_MESSAGE, null, optionss, optionss[0]);	
					try {
						SecondTask.castDamagingSpell((DamagingSpell)current.getSpells().get(1), directionDamaging);
					} catch (InCooldownException | NotEnoughIPException | OutOfBordersException
							| InvalidTargetCellException | IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					updateMap();
					updateCurrentChampionInfo(SecondTask.getCurrentChamp());
					updateStateArea();
				}

				//--- Cast RelocatingSpell

				if(current.getSpells().get(1) instanceof RelocatingSpell){
					Direction non =(Direction)JOptionPane.showInputDialog(damagintDialog, "Choose Target Direction : ", "Choose Target Direction", JOptionPane.QUESTION_MESSAGE, null, optionss, optionss[0]);
					Direction target = (Direction)JOptionPane.showInputDialog(damagintDialog, "Choose Movement Direction : ", "Choose Movement Direction", JOptionPane.QUESTION_MESSAGE, null, optionss, optionss[0]);
					String Range = JOptionPane.showInputDialog("Type Range  : ");
					try {
						SecondTask.castRelocatingSpell((RelocatingSpell)current.getSpells().get(1), non, target,Integer.parseInt(Range));
					} catch (InCooldownException | NotEnoughIPException | NumberFormatException | OutOfBordersException
							| InvalidTargetCellException | OutOfRangeException | IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					updateMap();
					updateCurrentChampionInfo(SecondTask.getCurrentChamp());
					updateStateArea();

				}

				//---- Cast HealingSpell

				if (current.getSpells().get(1) instanceof HealingSpell){
					try {
						SecondTask.castHealingSpell((HealingSpell)current.getSpells().get(1));
					} catch (InCooldownException | NotEnoughIPException | IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					updateMap();
					updateCurrentChampionInfo(SecondTask.getCurrentChamp());
					updateStateArea();
				}

			}

			//---------------------------------------------------------

			if(spell3Box.isSelected()){

				//--- Cast DamagingSpell

				if(current.getSpells().get(2) instanceof DamagingSpell){
					Direction directionDamaging =(Direction)JOptionPane.showInputDialog(damagintDialog, "Choose Direction : ", "Choose Direction", JOptionPane.QUESTION_MESSAGE, null, optionss, optionss[0]);

					try {
						SecondTask.castDamagingSpell((DamagingSpell)current.getSpells().get(2), directionDamaging);
					} catch (InCooldownException | NotEnoughIPException | OutOfBordersException
							| InvalidTargetCellException | IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}

					updateMap();
					updateCurrentChampionInfo(SecondTask.getCurrentChamp());
					updateStateArea();
				}

				//--- Cast RelocatingSpell

				if(current.getSpells().get(2) instanceof RelocatingSpell){
					Direction non =(Direction)JOptionPane.showInputDialog(damagintDialog, "Choose Target Direction : ", "Choose Target Direction", JOptionPane.QUESTION_MESSAGE, null, optionss, optionss[0]);
					Direction target = (Direction)JOptionPane.showInputDialog(damagintDialog, "Choose Movement Direction : ", "Choose Movement Direction", JOptionPane.QUESTION_MESSAGE, null, optionss, optionss[0]);
					String Range = JOptionPane.showInputDialog("Type Range  : ");
					try {
						SecondTask.castRelocatingSpell((RelocatingSpell)current.getSpells().get(2), non, target,Integer.parseInt(Range));
					} catch (InCooldownException | NotEnoughIPException | NumberFormatException | OutOfBordersException
							| InvalidTargetCellException | OutOfRangeException | IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					updateMap();
					updateCurrentChampionInfo(SecondTask.getCurrentChamp());
					updateStateArea();

				}

				//---- Cast HealingSpell

				if (current.getSpells().get(2) instanceof HealingSpell){
					try {
						SecondTask.castHealingSpell((HealingSpell)current.getSpells().get(2));
					} catch (InCooldownException | NotEnoughIPException | IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					updateMap();
					updateCurrentChampionInfo(SecondTask.getCurrentChamp());
					updateStateArea();

				}if(tournament.getThirdTask()==null&&tournament.isGameover()){
					JOptionPane.showMessageDialog(null, "GAME OVER :(");

					new ChooseChampionsController();
					SecondTaskView.setVisible(false);
				}

			}
			if(wiz.getHp()<old){
				JOptionPane.showMessageDialog(null, "You have been attacked by merperson!");
			}


		}

		if(e.getSource() instanceof JLabel && ((JLabel)e.getSource()).getName().equals("ActivateTraitbtn")){

			if(SecondTask.getCurrentChamp() instanceof GryffindorWizard){
				try {
					SecondTask.onGryffindorTrait();
				} catch (InCooldownException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				updateStateArea();
			}
			if(SecondTask.getCurrentChamp() instanceof SlytherinWizard){
				Direction[] optionsTrait = {Direction.BACKWARD , Direction.FORWARD ,Direction.LEFT ,Direction.RIGHT };
				JFrame TraitDialog = new JFrame();
				Direction option =(Direction)JOptionPane.showInputDialog(TraitDialog, "Choose Direction : ", "Choose Direction", JOptionPane.QUESTION_MESSAGE, null, optionsTrait, optionsTrait[0]);
				try {
					SecondTask.onSlytherinTrait(option);
				} catch (InCooldownException | OutOfBordersException | InvalidTargetCellException | IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				updateMap();
				updateCurrentChampionInfo(SecondTask.getCurrentChamp());
				updateStateArea();

			}

			if(SecondTask.getCurrentChamp() instanceof RavenclawWizard){

				try {
					//@SuppressWarnings({ "unchecked"})
					ArrayList<Direction> MarkedCells = (ArrayList<Direction>) SecondTask.onRavenclawTrait();
					updateStateArea();
					Point loc =((Wizard)tournament.getSecondTask().getCurrentChamp()).getLocation();
					for(int i = 0 ; i<MarkedCells.size();i++){
						switch (MarkedCells.get(i)) {
						case BACKWARD:
							imageMap[loc.x+1][loc.y].setIcon(new ImageIcon(SecondTaskController.class.getResource("BackWardArrow.png")));

							break;
						case FORWARD:
							imageMap[loc.x-1][loc.y].setIcon(new ImageIcon(SecondTaskController.class.getResource("ForwardArrow.png")));

							break;
						case LEFT:
							imageMap[loc.x][loc.y-1].setIcon(new ImageIcon(SecondTaskController.class.getResource("LeftArrow.png")));

							break;
						case RIGHT:
							imageMap[loc.x][loc.y+1].setIcon(new ImageIcon(SecondTaskController.class.getResource("RightArrow.png")));

							break;
						default:
							break;
						}
						//imageMap[MarkedCells.get(i).x][MarkedCells.get(i).y].setIcon(new ImageIcon(SecondTaskController.class.getResource("Fire.png")));
					}	
				} catch (InCooldownException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}



			}

			if(SecondTask.getCurrentChamp() instanceof HufflepuffWizard){
				try {
					SecondTask.onHufflepuffTrait();
					updateStateArea();
				} catch (InCooldownException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
			if(tournament.getThirdTask()!=null){
				try {
					new ThirdTaskController(tournament);
					SecondTaskView.setVisible(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}if(tournament.getThirdTask()==null&&tournament.isGameover()){
				JOptionPane.showMessageDialog(null, "GAME OVER :(");
			}
		}if(e.getSource() instanceof JLabel &&  ((JLabel)e.getSource()).getName().equals("potion") ){
			try{
				tournament.getSecondTask().usePotion((Potion)((Wizard)tournament.getSecondTask().getCurrentChamp()).getInventory().get(0));
				updateStateArea();
				updateCurrentChampionInfo(tournament.getSecondTask().getCurrentChamp());
			}catch(NullPointerException|IndexOutOfBoundsException e1){
				JOptionPane.showMessageDialog(null, "You Don't have Potions");
			}
		}






	}


	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() instanceof JLabel){

			if(((JLabel)e.getSource()).getName()=="RightArrow"){
				RightArrow.setIcon(new ImageIcon(SecondTaskController.class.getResource("RightArrowHover.png"))); 
			}
			if(((JLabel)e.getSource()).getName()=="LeftArrow"){
				LeftArrow.setIcon(new ImageIcon(SecondTaskController.class.getResource("LeftArrowHover.png"))); 
			}
			if(((JLabel)e.getSource()).getName()=="BackwardArrow"){
				BackwardArrow.setIcon(new ImageIcon(SecondTaskController.class.getResource("BackwardArrowHover.png"))); 
			}
			if(((JLabel)e.getSource()).getName()=="ForwardArrow"){
				ForwardArrow.setIcon(new ImageIcon(SecondTaskController.class.getResource("ForwardArrowHover.png"))); 
			}
			if(((JLabel)e.getSource()).getName()=="ActivateTraitbtn"){
				ActivateTraitbtn.setIcon(new ImageIcon(SecondTaskController.class.getResource("ActivateTraitHover.png"))); 
			}
			if(((JLabel)e.getSource()).getName()=="CastSpellbtn"){
				CastSpellbtn.setIcon(new ImageIcon(SecondTaskController.class.getResource("WandHover.png"))); 
			}


		}





	}


	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() instanceof JLabel){

			if(((JLabel)e.getSource()).getName()=="RightArrow"){
				RightArrow.setIcon(new ImageIcon(SecondTaskController.class.getResource("RightArrow.png"))); 
			}
			if(((JLabel)e.getSource()).getName()=="LeftArrow"){
				LeftArrow.setIcon(new ImageIcon(SecondTaskController.class.getResource("LeftArrow.png"))); 
			}
			if(((JLabel)e.getSource()).getName()=="BackwardArrow"){
				BackwardArrow.setIcon(new ImageIcon(SecondTaskController.class.getResource("BackwardArrow.png"))); 
			}
			if(((JLabel)e.getSource()).getName()=="ForwardArrow"){
				ForwardArrow.setIcon(new ImageIcon(SecondTaskController.class.getResource("ForwardArrow.png"))); 
			}

			if(((JLabel)e.getSource()).getName()=="ActivateTraitbtn"){
				ActivateTraitbtn.setIcon(new ImageIcon(SecondTaskController.class.getResource("ActivateTrait.png"))); 
			}
			if(((JLabel)e.getSource()).getName()=="CastSpellbtn"){
				CastSpellbtn.setIcon(new ImageIcon(SecondTaskController.class.getResource("Wand.png"))); 
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
