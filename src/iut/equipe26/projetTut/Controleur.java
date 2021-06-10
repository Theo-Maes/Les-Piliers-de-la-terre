package iut.equipe26.projetTut;

import iut.equipe26.projetTut.IHM.FrameJoueur;
import iut.equipe26.projetTut.IHM.FrameMenu;
import iut.equipe26.projetTut.metier.Joueur;
import iut.equipe26.projetTut.metier.Plateau;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

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

public class Controleur extends ComponentAdapter implements	WindowListener 
{
	
	private Plateau     plateau;
	private JFrame      frameJeuActuelle;
	private JFrame      frameSuiviActuelleG;
	private JFrame      frameSuiviActuelleD;

	private static Controleur instance;

	public Controleur() 
	{
		Controleur.instance = this;
		
		this.plateau   = new Plateau();
		
		this.setFrameJeuActuelle(new FrameMenu());
		this.setframeSuiviActuelle(new FrameJoueur(new Joueur()), new FrameJoueur(new Joueur()));


		this.frameSuiviActuelleG.setLocation( (int) this.frameJeuActuelle.getLocation().getX() - this.frameSuiviActuelleG.getWidth(), (int) this.frameJeuActuelle.getLocation().getY());
		this.frameSuiviActuelleD.setLocation( (int) this.frameJeuActuelle.getLocation().getX() + this.frameJeuActuelle.getWidth(), (int) this.frameJeuActuelle.getLocation().getY());
	}


	public static Controleur getInstance() { return instance; }	
	public        Plateau    getPlateau()  { return plateau;  }


	public void setFrameJeuActuelle(JFrame frameJeuActuelle) 
	{
		if(this.frameJeuActuelle != null) this.frameJeuActuelle.dispose();
		 this.frameJeuActuelle = frameJeuActuelle;
		 this.frameJeuActuelle.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-this.frameJeuActuelle.getWidth()/2, 
		 								Toolkit.getDefaultToolkit().getScreenSize().height/2-this.frameJeuActuelle.getHeight()/2);
	}

	public void setframeSuiviActuelle(JFrame frameSuiviActuelleG, JFrame frameSuiviActuelleD) 
	{
		if(this.frameSuiviActuelleG != null && this.frameSuiviActuelleD != null)
		{
			this.frameSuiviActuelleD.dispose();
			this.frameSuiviActuelleG.dispose();
		}
		
		this.frameSuiviActuelleG = frameSuiviActuelleG;
		this.frameSuiviActuelleD = frameSuiviActuelleD;

		this.frameSuiviActuelleG.setLocation( (int) this.frameJeuActuelle.getLocation().getX() - this.frameSuiviActuelleG.getWidth(), (int) this.frameJeuActuelle.getLocation().getY());
		this.frameSuiviActuelleD.setLocation( (int) this.frameJeuActuelle.getLocation().getX() + this.frameJeuActuelle.getWidth(), (int) this.frameJeuActuelle.getLocation().getY());
	}


	public void componentMoved(ComponentEvent e)
	{
		
		if(this.frameJeuActuelle == null || this.frameSuiviActuelleD == null || this.frameSuiviActuelleG == null ) return;

		this.frameSuiviActuelleG.setLocation( (int) this.frameJeuActuelle.getLocation().getX() - 5 - this.frameSuiviActuelleG.getWidth(), (int) this.frameJeuActuelle.getLocation().getY());
		this.frameSuiviActuelleD.setLocation( (int) this.frameJeuActuelle.getLocation().getX() + 5 + this.frameJeuActuelle.getWidth(), (int) this.frameJeuActuelle.getLocation().getY());
	}

	public void windowIconified(WindowEvent e) 
	{
		this.frameSuiviActuelleD.setState(JFrame.ICONIFIED);
		this.frameSuiviActuelleG.setState(JFrame.ICONIFIED);
	}

	public void windowDeiconified(WindowEvent e) 
	{
		this.frameJeuActuelle.setState(JFrame.NORMAL);
		this.frameSuiviActuelleD.setState (JFrame.NORMAL);
		this.frameSuiviActuelleG.setState (JFrame.NORMAL);
	}

	public void windowOpened     (WindowEvent e) {}
	public void windowClosing    (WindowEvent e) {}
	public void windowClosed     (WindowEvent e) {}
	public void windowActivated  (WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}

	public static void main(String[] args) 
	{
		new Controleur();
	}
}