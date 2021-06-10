package iut.equipe26.projetTut.IHM;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.Image;

public class PanelChoix extends JPanel implements ActionListener
{
	private FrameJoueur frmJ;
	private boolean bAvatar = true;
	private JButton[]  boutons;
	private String []  avatars = new String[] {"Lardon","1664","lutin","Cookie","fine","iut","pachimari","krokmou","peppe"};
	private String [] couleurs = new String[] {"gris", "marron", "violet", "rouge", "vert","cyan","bleu","jaune","orange"};
	
	public PanelChoix(FrameJoueur frmJ)
	{
		this.frmJ = frmJ;
		this.setLayout(new GridLayout(3,3,10,10));
		this.boutons = new JButton[this.avatars.length];
		
		
		//creation
		for(int cpt=0; cpt<this.avatars.length; cpt++)
		{
			ImageIcon imgicn = new ImageIcon("./src/ressource/avatar/" + this.avatars[cpt] + ".png");
			Image tmp = imgicn.getImage();
			Image tmp2 = tmp.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH);
			this.boutons[cpt] = new JButton(new ImageIcon(tmp2));
			this.boutons[cpt].setPreferredSize(new Dimension(20,20));
		}


		//positionnement
		for(int cpt=0; cpt<this.boutons.length; cpt++)
			this.add(this.boutons[cpt]);


		//activation
		for(int cpt=0; cpt<this.boutons.length; cpt++)
			this.boutons[cpt].addActionListener(this);
	}
	

	public void actionPerformed(ActionEvent e)
	{
		for(int cpt=0; cpt<this.boutons.length; cpt++)
		{
			if(e.getSource() == this.boutons[cpt])
			{
				if(bAvatar){this.frmJ.setAvatar ( this.avatars [cpt]);}
				else       {this.frmJ.setCouleur( this.couleurs[cpt]);}
			}
		}
	}
	
	public void changerCouleur()
	{
		for(int cpt=0; cpt<this.boutons.length; cpt++)
			this.boutons[cpt].setIcon(new ImageIcon("./src/ressource/couleur/" + this.couleurs[cpt] + ".png"));
		this.bAvatar = false;
	}
	
	public void changerAvatar()
	{
		for(int cpt=0; cpt<this.boutons.length; cpt++)
			this.boutons[cpt].setIcon(new ImageIcon("./src/ressource/avatar/" + this.avatars[cpt] + ".png"));
		this.bAvatar = true;
	}
	public boolean getChoix(){return this.bAvatar;}
}
