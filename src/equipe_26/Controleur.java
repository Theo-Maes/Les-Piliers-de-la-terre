package equipe_26;

import equipe_26.IHM.FrameJoueur;
import equipe_26.IHM.FrameMenu;
import equipe_26.IHM.FrameStat;
import equipe_26.metier.Joueur;
import equipe_26.metier.Plateau;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Toolkit;

/** Classe Controleur
  * Classe qui fait le lien entre l'ihm et le metier
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

	private Joueur joueur1;
	private Joueur joueur2;

	private static Controleur instance;

	
	/** Constructeur de la Classe Controleur
	  * Lance le jeu en mode CUI ou GUI 
	  * selon la chaine en paramètre
	  * @param s mode du jeu (CUI | GUI)
	  */
	public Controleur(String s) 
	{
		Controleur.instance = this;
		
		if(s.equals("GUI"))
		{
			this.joueur1 = new Joueur();
			this.joueur2 = new Joueur();

			this.setFrameJeuActuelle(new FrameMenu());
			this.setFrameSuiviActuelle(new FrameJoueur(this.joueur1), new FrameJoueur(this.joueur2));


			this.frameSuiviActuelleG.setLocation( (int) this.frameJeuActuelle.getLocation().getX() - 5 - this.frameSuiviActuelleG.getWidth(), (int) this.frameJeuActuelle.getLocation().getY());
			this.frameSuiviActuelleD.setLocation( (int) this.frameJeuActuelle.getLocation().getX() + 5 + this.frameJeuActuelle   .getWidth(), (int) this.frameJeuActuelle.getLocation().getY());
		}
		else
			this.plateau   = new Plateau(1);
		
	}
		
	/** Retourne l'instance de ce controleur
	  * @return l'instance de ce controleur
	  */
	public static Controleur getInstance() { return instance; }	
	
	/** Retourne le plateau
	  * @return le plateau
	  */
	public        Plateau    getPlateau () { return plateau;  }
	
	/** Retourne le joueur 1
	  * @return le joueur 1
	  */
	public        Joueur     getJoueur1 () { return joueur1;  }
	
	/** Retourne le joueur 2
	  * @return le joueur 2
	  */
	public        Joueur     getJoueur2 () { return joueur2;  }

	/** Définit le plateau à lancer
	  * @param plateau plateau à définir
	  */
	public void setPlateau(Plateau plateau) { this.plateau = plateau; }

	/**
	 * Lance l'application.
	 * @param args argument au lancement du Controleur
	 */
	public static void main(String[] args) 
	{
		new Controleur(args.length == 0 ? "GUI" : args[0]);
	}
	
	
	public void setFrameJeuActuelle(JFrame frameJeuActuelle) 
	{
		if(this.frameJeuActuelle != null) this.frameJeuActuelle.dispose();
		this.frameJeuActuelle = frameJeuActuelle;
		this.frameJeuActuelle.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width /2-this.frameJeuActuelle.getWidth ()/2, 
										Toolkit.getDefaultToolkit().getScreenSize().height/2-this.frameJeuActuelle.getHeight()/2);
	}


	public void setFrameSuiviActuelle(JFrame frameSuiviActuelleG, JFrame frameSuiviActuelleD) 
	{
		if(this.frameSuiviActuelleG != null && this.frameSuiviActuelleD != null)
		{
			this.frameSuiviActuelleD.dispose();
			this.frameSuiviActuelleG.dispose();
		}
		
		this.frameSuiviActuelleG = frameSuiviActuelleG;
		this.frameSuiviActuelleD = frameSuiviActuelleD;

		this.frameSuiviActuelleG.setLocation( (int) this.frameJeuActuelle.getLocation().getX() - this.frameSuiviActuelleG.getWidth(), (int) this.frameJeuActuelle.getLocation().getY());
		this.frameSuiviActuelleD.setLocation( (int) this.frameJeuActuelle.getLocation().getX() + this.frameJeuActuelle   .getWidth(), (int) this.frameJeuActuelle.getLocation().getY());
	}


	public JFrame getFrameJeuActuelle() {return this.frameJeuActuelle;}

	public void setFrameSuiviVisible(boolean visible)
	{
		this.frameSuiviActuelleD.setVisible(visible);
		this.frameSuiviActuelleG.setVisible(visible);
	}

	public void ActivationButton() 
	{
		FrameJoueur frameJoueurD = (FrameJoueur) this.frameSuiviActuelleD;
		FrameJoueur frameJoueurG = (FrameJoueur) this.frameSuiviActuelleG;

		if(frameJoueurD.getChoix())
			for (JButton Button : frameJoueurD.getButtons())
				Button.setEnabled(true);

		if(frameJoueurG.getChoix())
			for (JButton Button : frameJoueurG.getButtons())
				Button.setEnabled(true);

		if(!frameJoueurD.getChoix() && !frameJoueurG.getChoix())
		{
			for (JButton Button : frameJoueurG.getButtons())
				Button.setEnabled(true);
			for (JButton Button : frameJoueurD.getButtons())
				Button.setEnabled(true);
		}
			
	}


	public void desactivationButton ()
	{
		FrameJoueur frameJoueurD = (FrameJoueur) this.frameSuiviActuelleD;
		FrameJoueur frameJoueurG = (FrameJoueur) this.frameSuiviActuelleG;
		
		this.ActivationButton();

		if(!frameJoueurD.getChoix()) frameJoueurD.getButtons()[frameJoueurG.getButtonBloquer()].setEnabled(false);
		if(!frameJoueurG.getChoix()) frameJoueurG.getButtons()[frameJoueurD.getButtonBloquer()].setEnabled(false);

	}


	public void componentMoved(ComponentEvent e)
	{
		
		if(this.frameJeuActuelle == null || this.frameSuiviActuelleD == null || this.frameSuiviActuelleG == null ) return;

		this.frameSuiviActuelleG.setLocation( (int) this.frameJeuActuelle.getLocation().getX() - 5 - this.frameSuiviActuelleG.getWidth(), (int) this.frameJeuActuelle.getLocation().getY());
		this.frameSuiviActuelleD.setLocation( (int) this.frameJeuActuelle.getLocation().getX() + 5 + this.frameJeuActuelle   .getWidth(), (int) this.frameJeuActuelle.getLocation().getY());
	}

	public void windowIconified(WindowEvent e) 
	{
		this.frameSuiviActuelleD.setState(JFrame.ICONIFIED);
		this.frameSuiviActuelleG.setState(JFrame.ICONIFIED);
	}

	public void windowDeiconified(WindowEvent e) 
	{
		this.frameJeuActuelle   .setState (JFrame.NORMAL);
		this.frameSuiviActuelleD.setState (JFrame.NORMAL);
		this.frameSuiviActuelleG.setState (JFrame.NORMAL);
	}

	public void windowOpened     (WindowEvent e) {}
	public void windowClosing    (WindowEvent e) {}
	public void windowClosed     (WindowEvent e) {}
	public void windowActivated  (WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}

	public void majFrameSuivi()
	{
		if(frameSuiviActuelleD instanceof FrameStat && frameSuiviActuelleG instanceof FrameStat)
		{
			FrameStat frameStatD = (FrameStat) this.frameSuiviActuelleD;
			FrameStat frameStatG = (FrameStat) this.frameSuiviActuelleG;

			frameStatD.majIHM();
			frameStatG.majIHM();
		}
	}
}