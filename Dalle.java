public class Dalle
{
	private int[] MODIF_X = new int[] { 0 , 49, 49, 0 ,-49,-49};
	private int[] MODIF_Y = new int[] {67 , 33,-33,-67,-33, 33};
	
	private static char nbDalle = 'A';
	private char nom;
	
	private Dalle[] dallesVoisines;
	private int x;
	private int y;
	
	public Dalle(){this(0,0);}
	public Dalle(int x, int y)
	{
		this.nom = Dalle.nbDalle++;
		this.dallesVoisines = new Dalle[6];
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x)  {this.x = x;}
	public void setY(int y)  {this.y = y;}
	public int  getX  (){return this.x  ;}
	public int  getY  (){return this.y  ;}
	public char getNom(){return this.nom;}
	
	private void setVoisine(int cote, Dalle d)
	{
		if(cote > 2 ){cote -= 3;}
		else         {cote += 3;}
		this.dallesVoisines[cote] = d;
	}
	
	public boolean ajouterVoisine(int cote, Dalle d)
	{
		if(this.dallesVoisines[cote] != null){return false;}
		d.setX(this.x + MODIF_X[cote]);
		d.setY(this.y + MODIF_Y	[cote]);
		this.dallesVoisines[cote] =  d;
		d.setVoisine(cote, this);
		return true;
	}
	
	public String toString()
	{
		String sRet = this.nom +"";
		for(Dalle d : this.dallesVoisines)
		{
			if(d != null)
			{
				sRet += "|" + d.nom;
			}
		}
		return sRet;
	}
	
	public String toStringXY()
	{
		return "Dalle " + this.nom + " : (" + this.x + "," + this.y + ")";
	}
}

