package iut.equipe26.projetTut.IHM;

import iut.equipe26.projetTut.Controleur;
import iut.equipe26.projetTut.metier.Joueur;
import javax.swing.*;
import java.awt.BorderLayout;

public class FrameJoueur extends JFrame
{
	private PanelJoueur panelJoueur;
	private PanelChoix  panelChoix ;
	private PanelAction panelAction;

	public FrameJoueur(Joueur j)
	{
		this.setTitle("Joueur");
		this.setLocation(50,50);
		this.setSize(200, 600);
		this.setLayout( new BorderLayout());
		//creation
		this.panelJoueur = new PanelJoueur(j)   ;
		this.panelChoix  = new PanelChoix (this);
		this.panelAction = new PanelAction(this);
		//positionnement
		this.add(this.panelJoueur,BorderLayout.NORTH );
		this.add(this.panelChoix, BorderLayout.CENTER);
		this.add(this.panelAction,BorderLayout.SOUTH );
		
		//activation
		
		this.addComponentListener(Controleur.getInstance());
		
		//autres
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void setAvatar(String avatar)
	{
		this.panelJoueur.setAvatar(avatar);
	}
	public void setCouleur(String couleur)
	{
		this.panelJoueur.setCouleur(couleur);
	}
	public boolean getChoix(){return this.panelChoix.getChoix();}
	
	public void changerAvatar (){this.panelChoix.changerAvatar ();}
	public void changerCouleur(){this.panelChoix.changerCouleur();}
	
}
