package equipe_26.IHM;

import equipe_26.Controleur;
import equipe_26.metier.Joueur;

import javax.swing.*;

public class FrameStat extends JFrame
{
	private Joueur joueur;
	private PanelStat panelStat;
	
	public FrameStat(Joueur joueur)
	{
		this.joueur = joueur;
		this.setTitle("Statistique de " + this.joueur.getNom() );
		this.setLocation(50,50);
		this.setSize(300, 600);
		
		
		this.panelStat = new PanelStat(joueur);
		
		this.add(this.panelStat);
		
		//activation
		this.addWindowListener(Controleur.getInstance());

		//autres
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setResizable(false);
		this.setVisible(true);
	}
}