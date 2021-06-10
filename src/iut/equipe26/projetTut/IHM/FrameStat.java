package iut.equipe26.projetTut.IHM;

import iut.equipe26.projetTut.Controleur;
import iut.equipe26.projetTut.metier.Joueur;

import javax.swing.*;
import java.awt.BorderLayout;

public class FrameStat extends JFrame
{
	private Joueur j;
	private PanelStat panelStat;
	
	public FrameStat(Joueur j)
	{
		this.j = j;
		this.setTitle("Statistique de " + this.j.getNom() );
		this.setLocation(50,50);
		this.setSize(300, 600);
		
		
		this.panelStat = new PanelStat(j);
		
		this.add(this.panelStat);
		
		//activation
		this.addWindowListener(Controleur.getInstance());

		//autres
		this.setUndecorated(true);
		this.setResizable(false);
		this.setVisible(true);
	}
}