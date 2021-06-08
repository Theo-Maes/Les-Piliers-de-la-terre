package iut.equipe26.projetTut;

import iut.equipe26.projetTut.IHM.FrameJeu;
import iut.equipe26.projetTut.IHM.FrameMenu;
import iut.equipe26.projetTut.metier.Plateau;

/** Les Piliers de la terres
 * @author Paul
 * @author Alan
 * @author Théo
 * @author Thomas
 * @author Jason
 * @author Pierre
 */

public class Controleur {
	
	private Plateau plateau;
	private FrameJeu frameJeu;
	private FrameMenu frameMenu;
	private static Controleur instance;

	public Controleur() {
		Controleur.instance = this;

		this.plateau   = new Plateau();
		this.frameJeu  = new FrameJeu();
		this.frameMenu = new FrameMenu();

	}

	public static Controleur getInstance() {
		return instance;
	}

	public Plateau getPlateau() { return plateau; }

	public static void main(String[] args) {
		new Controleur();
	}
}
