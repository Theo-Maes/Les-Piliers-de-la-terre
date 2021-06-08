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
	private static Controleur instance;

	public Controleur() {
		Controleur.instance = this;

		this.plateau = new Plateau();
	}

	public static Controleur getInstance() {
		return instance;
	}

	public static void main(String[] args) {
		new Controleur();
	}
}
