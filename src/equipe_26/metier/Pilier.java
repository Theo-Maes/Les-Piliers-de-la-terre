package equipe_26.metier;

/** Les Piliers de la terres
 * @author Paul
 * @author Alan
 * @author Théo
 * @author Thomas
 * @author Jason
 * @author Pierre
 */

public class Pilier
{
	private int x;
	private int y;
	private char coul;
	private static int nbPilier = 0;
	
	public Pilier(){this(0,0);}

	public Pilier(int x, int y)
	{
		this.x = x;
		this.y = y;
		
		if( Pilier.nbPilier++%2 == 0)this.coul = 'G';
		else this.coul = 'M';
	}
	
	public int getX () {return this.x;}
	public int getY () {return this.y;}
	public char getCoul(){return this.coul;}
	public int getNbPilier(){return Pilier.nbPilier;}
	
	public String toString()
	{
		return this.coul + "(" + this.x + "," + this.y + ")";
	}
}
