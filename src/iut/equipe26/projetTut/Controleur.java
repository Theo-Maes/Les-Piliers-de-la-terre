package iut.equipe26.projetTut;

import iut.equipe26.projetTut.IHM.FrameJoueur;
import iut.equipe26.projetTut.IHM.FrameMenu;
import iut.equipe26.projetTut.metier.Joueur;
import iut.equipe26.projetTut.metier.Plateau;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import java.awt.Toolkit;

/** Les Piliers de la terres
 * @author Paul
 * @author Alan
 * @author Théo
 * @author Thomas
 * @author Jason
 * @author Pierre
 */

public class Controleur extends ComponentAdapter
{
	
	private Plateau     plateau;
	private FrameJoueur frameJoueurG;
	private FrameJoueur frameJoueurD;
	private JFrame      frameActuelle;

	private static Controleur instance;

	public Controleur() 
	{

		Controleur.instance = this;
		
		this.plateau   = new Plateau();

		this.setFrameActuelle(new FrameMenu());

		this.frameJoueurG = new FrameJoueur(new Joueur());
		this.frameJoueurD = new FrameJoueur(new Joueur());

		this.frameJoueurG.setLocation( (int) this.frameActuelle.getLocation().getX() - this.frameJoueurG.getWidth(), (int) this.frameActuelle.getLocation().getY());
		this.frameJoueurD.setLocation( (int) this.frameActuelle.getLocation().getX() + this.frameActuelle   .getWidth(), (int) this.frameActuelle.getLocation().getY());
	}


	public static Controleur getInstance() { return instance; }


	public Plateau getPlateau() { return plateau; }


	public void setFrameActuelle(JFrame frameActuelle) 
	{
		 this.frameActuelle = frameActuelle;
		 this.frameActuelle.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-this.frameActuelle.getWidth()/2, 
		 								Toolkit.getDefaultToolkit().getScreenSize().height/2-this.frameActuelle.getHeight()/2);
	}


	public void componentMoved(ComponentEvent e)
	{
		if(this.frameActuelle == null || this.frameJoueurD == null || this.frameJoueurG == null ) return;

		if(e.getSource() == this.frameJoueurD) 
		{
			this.frameActuelle.setLocation( (int) this.frameJoueurD.getLocation().getX() - this.frameActuelle.getWidth(), (int) this.frameJoueurD.getLocation().getY());
			this.frameJoueurG .setLocation( (int) this.frameActuelle .getLocation().getX() - this.frameJoueurG.getWidth(), (int) this.frameJoueurD.getLocation().getY());
			return;
		}

		if(e.getSource() == this.frameJoueurG) 
		{
			this.frameActuelle.setLocation( (int) this.frameJoueurG  .getLocation().getX() + this.frameJoueurG.getWidth(), (int) this.frameJoueurG.getLocation().getY());
			this.frameJoueurD .setLocation( (int) this.frameActuelle .getLocation().getX() + this.frameActuelle.getWidth(), (int) this.frameJoueurG.getLocation().getY());
			return;
		}

		this.frameJoueurG.setLocation( (int) this.frameActuelle.getLocation().getX()-  this.frameJoueurG.getWidth(), (int) this.frameActuelle.getLocation().getY());
		this.frameJoueurD.setLocation( (int) this.frameActuelle.getLocation().getX() + this.frameActuelle.getWidth(), (int) this.frameActuelle.getLocation().getY());
	}

	public static void main(String[] args) {
		new Controleur();
	}
}