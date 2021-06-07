public class Dalle
{
	private final int[] MODIF_X  = new int[] { 0 , 49, 49, 0 ,-49,-49};
	private final int[] MODIF_Y  = new int[] {-67,-33, 33, 67, 33,-33};
	private final int[] PILIER_X = new int[] {-16, 16, 33, 16,-16,-33};
	private final int[] PILIER_Y = new int[] {-33,-33,  0, 33, 33,  0};
	
	private static char nbDalle = 'A';
	private char nom;
	
	private char controle; // p=personne G=joueur gris M=joueur marron
	
	private Pilier[] piliers;
	private Dalle[] dallesVoisines;
	private int x;
	private int y;
	
	public Dalle(){this(0,0);}
	public Dalle(int x, int y)
	{
		this.nom = Dalle.nbDalle++;
		this.dallesVoisines = new  Dalle[6];
		this.piliers        = new Pilier[6];
		this.x = x;
		this.y = y;
		this.controle = 'p';
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
	private void priseControle(char joueur)
	{
		this.controle = joueur;
		for(Pilier p : this.piliers)
			if(p.controle != joueur){this.detruire(p);}
	}
	public boolean ajouterPilier(int coin)
	{
		if(this.piliers[coin] != null){return false;}
		Pilier tmp = new Pilier(this.x + PILIER_X[coin], this.y + PILIER_Y[coin]);
		this.piliers[coin] = tmp;
		//on vérifie si on arrive à 4 piliers
		int cpt = 0;
		for(Pilier p : this.piliers)
			if(p.getCoul() == tmp.getCoul()){cpt++;}
		if(cpt == 4){this.priseControle(tmp.getCoul());}
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

