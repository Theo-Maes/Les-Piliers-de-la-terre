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
	private char cCoul;
	private static int iNbPilier = 0;
	
	public Pilier(){this(0,0);}
	
	public Pilier(int x, int y)
	{
		this.x = x;
		this.y = y;
		
		if( Pilier.iNbPilier++%2 == 0)this.cCoul = 'G';
		else this.cCoul = 'M';
	}
	
	public int  getX       (){return this.x;}
	public int  getY       (){return this.y;}
	public char getCoul    (){return this.cCoul;}
	public int  getNbPilier(){return Pilier.iNbPilier;}
	
	public String toString()
	{
		return this.cCoul + "(" + this.x + "," + this.y + ")";
	}
}
