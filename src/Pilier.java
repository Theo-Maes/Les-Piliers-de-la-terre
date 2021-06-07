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
		
		if( ++Pilier.nbPilier%2 == 0)this.coul = 'G';
		else this.coul = 'M';
	}
	
	public char getCoul(){return this.coul;}
	public String toString()
	{
		return "" + this.coul;
	}
}
