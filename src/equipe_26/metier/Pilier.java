package equipe_26.metier;

/** Classe Pilier
  * Classe qui définit un Pilier
  * @author Paul
  * @author Alan
  * @author Théo
  * @author Thomas
  * @author Jason
  * @author Pierre
  */
public class Pilier
{
	//Attributs
	private int x;
	private int y;
	private char cCoul;
	private static int iNbPilier = 0;
	
	/** Contructeur par défaut
	  * Il appelle le constructeur avec paramètre 
	  * et donne comme coordonées 0,0
	  */
	public Pilier(){this(0,0);}
	
	/** Constructeur avec paramètres
	  * @param x coordonées en x du Pilier
	  * @param y coordonées en y du Pilier
	  */
	public Pilier(int x, int y)
	{
		this.x = x;
		this.y = y;
		
		if( Pilier.iNbPilier++%2 == 0)this.cCoul = 'G';
		else this.cCoul = 'M';
	}
	
	/** Retourne la coordonée en x du Pilier
	  * @return la coordonée en x du Pilier
	  */
	public int  getX       (){return this.x;}
	
	/** Retourne la coordonée en y du Pilier
	  * @return la coordonée en y du Pilier
	  */
	public int  getY       (){return this.y;}
	
	/** Retourne la couleur du Pilier
	  * @return la couleur du Pilier
	  */
	public char getCoul    (){return this.cCoul;}
	
	/** Retourne le nombre de Pilier
	  * @return le nombre de Pilier
	  */
	public int  getNbPilier(){return Pilier.iNbPilier;}
	
	/** Retourne les informations du Pilier
	 * @return les informations du Pilier
	 */
	public String toString()
	{
		return this.cCoul + "(" + this.x + "," + this.y + ")";
	}
}