package harrypotter.view;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

@SuppressWarnings("serial")
public class FirstTaskView extends JFrame{
	JLabel FirstTaskBackGround;

	public FirstTaskView() {
		// Start Adding BackGround
		URL url = FirstTaskView.class.getResource("FirstTaskBackGround.png");
		FirstTaskBackGround = new JLabel(new ImageIcon(url));
		getContentPane().add(FirstTaskBackGround);
		// End Adding BackGround
		
		//Start Preparing The Frame
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setUndecorated(true);
	    pack();
	    setVisible(true);
	    //End Preparing The Frame
	}
	
	//Methods used in Controller
	
	public void addRightArrow(JLabel RightArrow){
		RightArrow.setBounds(890, 514, 91, 78);
		FirstTaskBackGround.add(RightArrow);		
	}
    public void addLeftArrow(JLabel LeftArrow){
    	LeftArrow.setBounds(812, 514, 91, 78);
		FirstTaskBackGround.add(LeftArrow);
		
	}
    
    public void addForwardArrow(JLabel ForwardArrow){
    	ForwardArrow.setBounds(864, 470, 64, 84);
		FirstTaskBackGround.add(ForwardArrow);
		
	}
    public void addBackwardArrow(JLabel BackWardArrow){
    	BackWardArrow.setBounds(864, 551, 64, 84);
		FirstTaskBackGround.add(BackWardArrow);
		
	}
    
    public void addActivateTraitbtn(JLabel ActivateTraitbtn){
    	ActivateTraitbtn.setBounds(879, 624, 100, 100);
    	FirstTaskBackGround.add(ActivateTraitbtn);
    	
    }
    
    public void addCastSpellbtn(JLabel CastSpellbtn ){
    	CastSpellbtn.setBounds(812, 625, 100, 100);
    	FirstTaskBackGround.add(CastSpellbtn);	
    }
    
    public void addImage(JLabel image , int x , int y , int W , int H){
    	image.setBounds(x, y, W, H);
    	FirstTaskBackGround.add(image);    	
    }
    public void addCheckBox(JRadioButton box ,int x , int y , int W , int H ){
    	box.setBounds(x, y,W, H);
    	FirstTaskBackGround.add(box);
    }
    public void addProgressBar(JProgressBar bar ,int x , int y , int W , int H ){
    	bar.setBounds(x, y, W, H);
    	FirstTaskBackGround.add(bar);
    	
    }
    public void removeJLabel(JLabel label){
    	FirstTaskBackGround.remove(label);
    	
    }

    public static void main(String[]args){
    	new FirstTaskView();
    }

	public void addusepotion(JLabel usepotion) {
		usepotion.setBounds(1020, 380, 30, 30);
		FirstTaskBackGround.add(usepotion);
		
	}
    

	

}
