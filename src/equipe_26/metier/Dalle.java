package equipe_26.metier;

import equipe_26.Controleur;


public class Dalle
{
	private final  int[] MODIF_X  = new int[] {  0, 49, 49,  0,-49,-49};
	private final  int[] MODIF_Y  = new int[] {-67,-33, 33, 67, 33,-33};
	public  final  int[] PILIER_X = new int[] {-16, 16, 33, 16,-16,-33};
	public  final  int[] PILIER_Y = new int[] {-33,-33,  0, 33, 33,  0};

	private        boolean[] bConstruire;
	private        boolean[] bBlocageTour;
	private static char      cNbDalle = 'A';
	private        char      cNom;
	
	private char cControle; // p=personne G=joueur gris M=joueur marron
	//private static int[] detruit;  nombre de pilier detruit par joueur 0=G et 1=M 
	
	private static int iNbPiliersDetruitParJ1;
	private static int iNbPiliersDetruitParJ2;

	private Pilier[] pPiliers;
	private Dalle[] dDallesVoisines;
	private int x;
	private int y;
	
	public Dalle(){this(-33+700/2,-66*2+600/2);}
	public Dalle(int x, int y)
	{
		this.cNom = Dalle.cNbDalle++;
		this.dDallesVoisines = new   Dalle[6];
		this.pPiliers        = new  Pilier[6];
		this.bConstruire     = new boolean[6];
		this.bBlocageTour    = new boolean[]{true,true,true,true,true,true};
		this.RAZConstruire();
		this.x = x;
		this.y = y;
		this.cControle = 'p';
		//this.detruit = new int[] {0,0};
	}
	
	public void setX  (int x){this.x = x           ;}
	public void setY  (int y){this.y = y           ;}
	public int  getX       (){return this.x        ;}
	public int  getY       (){return this.y        ;}
	public char getNom     (){return this.cNom      ;}
	public char getControle(){return this.cControle ;}
	public Pilier[] getPiliers(){return this.pPiliers;}
	
	public Pilier getPrc(int coin)
	{
		coin = coin-1;
		if (coin<0)coin = 5;
		return this.pPiliers[coin];
	}
	
	public Pilier getSvt(int coin)
	{
		coin = coin+1;
		if(coin>5)
			coin = 0;
		return this.pPiliers[coin];
	}
	
	public Dalle getDalleV(int coin)
	{
		return this.dDallesVoisines[coin];
	}
	
	
	private void setVoisine(int cote, Dalle d)
	{
		if(cote > 2 ){cote -= 3;}
		else         {cote += 3;}
		this.dDallesVoisines[cote] = d;
	}
	
	private void setPilier(int coin, Pilier p)
	{
		this.pPiliers[coin] = p;
	}
	
	public boolean isConstructible(int cote) {return this.bConstruire[cote];}
	
	public static void reinitialiser()
	{
		Dalle.cNbDalle = 'A';
	}
	
	public boolean ajouterPilier(int coin)
	{
		if(this.pPiliers[coin] != null || !this.bConstruire[coin] ){return false;}
		
		Pilier tmp = new Pilier(this.x+33 + PILIER_X[coin], this.y+33 + PILIER_Y[coin]);
		this.pPiliers[coin] = tmp;
		
		this.piliersCapture(tmp);
		
		if(this.dDallesVoisines[coin] != null)
		{
			int c = coin - 2;
			if(c < 0){c += 6;}
			this.dDallesVoisines[coin].setPilier(c, tmp);
			this.dDallesVoisines[coin].piliersCapture(this.dDallesVoisines[coin].getSvt(c-1));
		}
		int voisine = coin - 1;
		if(voisine == -1){voisine = 5;}
		if(this.dDallesVoisines[voisine] != null)
		{
			int v = voisine - 3;
			if(v < 0){v += 6;}
			this.dDallesVoisines[voisine].setPilier(v, tmp);
			this.dDallesVoisines[voisine].piliersCapture(this.dDallesVoisines[voisine].getSvt(v-1));
		}
		
		return true;
	}
	
	public void piliersCapture(Pilier tmp)
	{
		int cpt = 0;
		for(Pilier p : this.pPiliers) {
			if (p!=null)
			if(p != null && p.getCoul() == tmp.getCoul()){cpt++;}
		}
		if(cpt == 4){this.priseControle(tmp);}
	}
	
	private void priseControle(Pilier tmp)
	{
		this.cControle = tmp.getCoul();
		int cpt=0;

		for(Pilier p : this.piliers) {
			if(p != null && p.getCoul() != tmp.getCoul()) {this.detruire(cpt);}
			cpt++;
		}

		if(Controleur.getInstance().getPlateau().getNbTour()%2 == 0) Controleur.getInstance().getJoueur1().priseDalle(1);
		else                                                         Controleur.getInstance().getJoueur2().priseDalle(1);
	}
	
	
	public void detruire(int coin)
	{
		switch(this.pPiliers[coin].getCoul())
		{
			case 'G' -> { Dalle.iNbPiliersDetruitParJ1++; Controleur.getInstance().getJoueur2().detruirePilier(1);}
			case 'M' -> { Dalle.iNbPiliersDetruitParJ2++; Controleur.getInstance().getJoueur1().detruirePilier(1);}
		}
			
		this.pPiliers[coin]    =  null;
		this.bConstruire[coin] = false;
		this.bBlocageTour[coin] = false;

		if(this.dDallesVoisines[coin] != null)
		{
			int c = coin - 2;
			if(c < 0){c += 6;}
			this.dDallesVoisines[coin].pPiliers[c] = null;
			this.dDallesVoisines[coin].bConstruire[c] = false;
			this.dDallesVoisines[coin].bBlocageTour[c] = false;

			this.dDallesVoisines[coin].perteControle();
		}
		int voisine = coin - 1;
		if(voisine == -1){voisine = 5;}
		if(this.dDallesVoisines[voisine] != null)
		{
			int v = voisine - 3;
			if(v < 0){v += 6;}

			this.dallesVoisines[voisine].piliers    [v] = null;
			this.dallesVoisines[voisine].construire [v] = false;
			this.dallesVoisines[voisine].blocageTour[v] = false;
			this.dallesVoisines[voisine].perteControle();
		}
		
		this.perteControle();
	}
	
	public void perteControle()
	{
		int pilier=0;
		for(Pilier p : this.pPiliers)
			if(p != null && p.getCoul() == this.getControle()){pilier ++;}
		if(pilier < 4)
		{
			this.setControle('p');
			if(Controleur.getInstance().getPlateau().getNbTour()%2 == 0) Controleur.getInstance().getJoueur2().perteDalle(1);
			else                                                         Controleur.getInstance().getJoueur1().perteDalle(1);
		}

		
		
	}
	
	public void setControle(char c){this.cControle = c;}

	public boolean ajouterVoisine(int cote, Dalle d)
	{
		if(this.dDallesVoisines[cote] != null){return false;}
		d.setX(this.x + MODIF_X[cote]);
		d.setY(this.y + MODIF_Y	[cote]);
		this.dDallesVoisines[cote] =  d;
		d.setVoisine(cote, this);
		return true;
	}
	
	public boolean ajouterVoisineCustom(int cote, Dalle d)
	{
		if(this.dDallesVoisines[cote] != null){return false;}
		this.dDallesVoisines[cote] =  d;
		d.setVoisine(cote, this);
		return true;
	}
	
	public void RAZConstruire()
	{
		for(int cpt=0;cpt<6;cpt++)
		{
			if (!this.bBlocageTour[cpt]) {
				this.bBlocageTour[cpt] = true;
			} else {
				this.bConstruire[cpt] = true;
			}
		}
	}

	public int getNbPiliersDetruitParJ1() {return Dalle.iNbPiliersDetruitParJ1;}
	public int getNbPiliersDetruitParJ2() {return Dalle.iNbPiliersDetruitParJ2;}
	
	public String toString()//toString dalles voisines
	{
		String sRet = "| Dalle " + this.cNom + " |" ;
		for(Dalle d : this.dDallesVoisines)
		{
			if(d == null)
				sRet += "   |";
			else
				sRet += " " + d.cNom + " |";
		}
		return sRet;
	}
	
	
	public String toStringP()//toString pilier
	{
		String sRet = "| Dalle " + this.cNom + " |" ;
		for(Pilier p : this.pPiliers)
		{
			if(p == null)
				sRet += "   |";
			else
				sRet += " " + p.getCoul() + " |";
		}
		return sRet;
	}
	
	/*public int getDetruit(char joueur)
	{
		if(joueur == 'G')
			return this.detruit[0];
		return this.detruit[1];
	}*/
	
	public String toStringC()
	{
		return "Dalle " + this.cNom + "|" + this.cControle;
	}
	public String toStringXY()
	{
		return "Dalle " + this.cNom + " : (" + this.x + "," + this.y + ")";
	}
}