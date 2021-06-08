package iut.equipe26.projetTut;

import iut.equipe26.projetTut.IHM.FrameJeu;
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
	private FrameJeu frame;
	private static Controleur instance;

	public Controleur() {
		Controleur.instance = this;

		this.plateau = new Plateau();
		this.frame = new FrameJeu();
	}

	public static Controleur getInstance() {
		return instance;
	}

	public static void main(String[] args) {
		new Controleur();
	}
}
