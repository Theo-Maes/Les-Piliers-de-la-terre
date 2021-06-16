package equipe_26.metier;

import equipe_26.Controleur;

/** Classe Dalle
  * Classe qui définit une Dalle
  * @author Paul
  * @author Alan
  * @author Théo
  * @author Thomas
  * @author Jason
  * @author Pierre
  */
public class Dalle
{
	private final  int[] MODIF_X  = new int[] {  0, 49, 49,  0,-49,-49};
	private final  int[] MODIF_Y  = new int[] {-67,-33, 33, 67, 33,-33};
	
	/** Tableau de coordonnées du pilier en x*/
	public  final  int[] PILIER_X = new int[] {-16, 16, 33, 16,-16,-33};
	
	/** Tableau de coordonnées du pilier en y*/
	public  final  int[] PILIER_Y = new int[] {-33,-33,  0, 33, 33,  0};

	private        boolean[] bConstruire;
	private        boolean[] bBlocageTour;
	private static char      cNbDalle = 'A';
	private        char      cNom;
	
	private char cControle; // p=personne G=joueur gris M=joueur marron
	
	private static int iNbPiliersDetruitParJ1;
	private static int iNbPiliersDetruitParJ2;

	private Pilier[] pPiliers;
	private Dalle[] dDallesVoisines;
	private int x;
	private int y;
	
	/** Contructeur par défaut
	  * Il appelle le constructeur avec paramètre 
	  * et donne comme paramètres les coordonnées du centre du plateau
	  */
	public Dalle(){this(-33+700/2,-66*2+600/2);}
	
	/** Constructeur avec paramètres
	  * Initialise les informations 
	  * nécessaire à la création d'une Dalle
	  *
	  * @param x coordonées en x de la Dalle
	  * @param y coordonées en y de la Dalle
	  */
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
	}
	
	/** Définit la coordonnée x de la Dalle
	  * @param x la coordonnée x de la Dalle à définir
	  */
	public void setX  (int x){this.x = x           ;}
	
	/** Définit la coordonnée y de la Dalle
	  * @param y la coordonnée y de la Dalle à définir
	  */
	public void setY  (int y){this.y = y           ;}
	
	/** Retourne la coordonnée x de la Dalle
	  * @return la coordonnée x de la Dalle
	  */
	public int  getX       (){return this.x        ;}
	
	/** Retourne la coordonnée y de la Dalle
	  * @return la coordonnée y de la Dalle
	  */
	public int  getY       (){return this.y        ;}
	
	/** Retourne le nom de la Dalle
	  * @return le nom de la Dalle
	  */
	public char getNom     (){return this.cNom      ;}
	
	/** Retourne le caractère du joueur qui controle la Dalle
	  * @return le caractère du joueur qui controle la Dalle
	  */
	public char getControle(){return this.cControle ;}
	
	/** Retourne les Piliers de la Dalle
	  * @return les Piliers de la Dalle
	  */
	public Pilier[] getPiliers(){return this.pPiliers;}
	
	/** Retourne le Pilier précedent au coin passé en paramètre
	  * @param coin coin du Pilier suivant
	  * @return le Pilier précedent au coin passé en paramètre
	  */
	public Pilier getPrc(int coin)
	{
		coin = coin-1;
		if (coin<0)coin = 5;
		return this.pPiliers[coin];
	}
	
	/** Retourne le Pilier suivant au coin passé en paramètre
	  * @param coin coin du Pilier précedent
	  * @return le Pilier suivant au coin passé en paramètre
	  */
	public Pilier getSvt(int coin)
	{
		coin = coin+1;
		if(coin>5)
			coin = 0;
		return this.pPiliers[coin];
	}
	
	/** Retourne la Dalle voisine au coin passé en paramètre
	  * @param coin coin de la Dalle voisine
	  * @return la Dalle voisine au coin passé en paramètre
	  */
	public Dalle getDalleV(int coin)
	{
		return this.dDallesVoisines[coin];
	}
	
	/** Définit la voisine de la Dalle
	  * @param coin coin de la Dalle où va être la voisine
	  * @param d Dalle voisine
	  */
	private void setVoisine(int cote, Dalle d)
	{
		if(cote > 2 ){cote -= 3;}
		else         {cote += 3;}
		this.dDallesVoisines[cote] = d;
	}
	
	/** Définit un Pilier 
	  * @param coin coin de la Dalle où va être le Pilier
	  * @param p Pilier à définir
	  */
	private void setPilier(int coin, Pilier p)
	{
		this.pPiliers[coin] = p;
	}
	
	/** Permet de savoir si on peut construire sur le coté de la Dalle mit en paramètre
	  * @param cote coté où l'on veut savoir si on peut construire
	  * @return true si on peut contruire
	  */
	public boolean isConstructible(int cote) {return this.bConstruire[cote];}
	
	/** Remet le nom des Dalles à partir de A*/
	public static void reinitialiser()
	{
		Dalle.cNbDalle = 'A';
	}
	
	/** Permet d'ajouter un Pilier au coin donné en paramètre
	  * @param coin coin de la Dalle où l'on veut ajouter le Pilier
	  * @return true si on a bien ajouté le Pilier
	  */
	public boolean ajouterPilier(int coin)
	{
		if(this.pPiliers[coin] != null || !this.bConstruire[coin] ){return false;}
		
		Pilier tmp = new Pilier(this.x+33 + PILIER_X[coin], this.y+33 + PILIER_Y[coin]);
		this.pPiliers[coin] = tmp;
		
		Controleur.getInstance().getPlateau().enfermement(this, coin);
		
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
	
	/** Permet de savoir si on peut capturer une dalle 
	  * Si le nombre de pilier de la même couleur est de 4
	  * @param tmp pilier que l'on vient d'ajouter pour savoir sa couleur
	  */
	public void piliersCapture(Pilier tmp)
	{
		int cpt = 0;
		for(Pilier p : this.pPiliers) {
			if (p!=null)
			if(p != null && p.getCoul() == tmp.getCoul()){cpt++;}
		}
		if(cpt == 4){this.priseControle(tmp);}
	}
	
	/** Permet de capturer une dalle 
	  * @param tmp pilier que l'on vient d'ajouter pour savoir sa couleur
	  */
	private void priseControle(Pilier tmp)
	{
		this.cControle = tmp.getCoul();
		int cpt=0;

		for(Pilier p : this.pPiliers) {
			if(p != null && p.getCoul() != tmp.getCoul()) {this.detruire(cpt);}
			cpt++;
		}

		if(Controleur.getInstance().getPlateau().getNbTour()%2 == 0) Controleur.getInstance().getJoueur1().priseDalle(1);
		else                                                         Controleur.getInstance().getJoueur2().priseDalle(1);
	}
	
	/** Detruit un Pilier au coin donné en paramètre
	  * @param coin coin où l'on détruit le pilier
	  */
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

			this.dDallesVoisines[voisine].pPiliers    [v] = null;
			this.dDallesVoisines[voisine].bConstruire [v] = false;
			this.dDallesVoisines[voisine].bBlocageTour[v] = false;
			this.dDallesVoisines[voisine].perteControle();
		}
		
		this.perteControle();
	}
	
	/** Enleve le controle de la Dalle*/
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
	
	/** Définit le controle de la Dalle
	  * @param c caractère du Joueur du controle la Dalle
	  */
	public void setControle(char c){this.cControle = c;}
	
	/** Ajoute une voisine
	  * @param coin coin de la Dalle où va être la voisine
	  * @param d Dalle voisine	
	  */
	public boolean ajouterVoisine(int cote, Dalle d)
	{
		if(this.dDallesVoisines[cote] != null){return false;}
		d.setX(this.x + MODIF_X[cote]);
		d.setY(this.y + MODIF_Y	[cote]);
		this.dDallesVoisines[cote] =  d;
		d.setVoisine(cote, this);
		return true;
	}
	
	/** Ajoute une voisine de manière choisie
	  * @param coin coin de la Dalle où va être la voisine
	  * @param d Dalle voisine	
	  */
	public boolean ajouterVoisineCustom(int cote, Dalle d)
	{
		if(this.dDallesVoisines[cote] != null){return false;}
		this.dDallesVoisines[cote] =  d;
		d.setVoisine(cote, this);
		return true;
	}
	
	/** Remet à zéro la possibilité de posé un Pilier sur la Dalle*/
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
	
	/** Retourne le nombre de Pilier détruit par le joueur 1
	  * @return le nombre de Pilier détruit par le joueur 1
	  */
	public int getNbPiliersDetruitParJ1() {return Dalle.iNbPiliersDetruitParJ1;}
	
	/** Retourne le nombre de Pilier détruit par le joueur 2
	  * @return le nombre de Pilier détruit par le joueur 2
	  */
	public int getNbPiliersDetruitParJ2() {return Dalle.iNbPiliersDetruitParJ2;}
	
	/** Retourne les informations des voisines de la Dalle
	 * @return les informations des voisines de la Dalle
	 */
	public String toString()
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
	
	/** Retourne les informations des piliers de la Dalle
	 * @return les informations des piliers de la Dalle
	 */
	public String toStringP()
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
	
	/** Retourne les informations du controle de la Dalle
	 * @return les informations du controle de la Dalle
	 */
	public String toStringC()
	{
		return "Dalle " + this.cNom + "|" + this.cControle;
	}
	
	/** Retourne les informations des coordonnées de la Dalle
	 * @return les informations des coordonnées de la Dalle
	 */
	public String toStringXY()
	{
		return "Dalle " + this.cNom + " : (" + this.x + "," + this.y + ")";
	}
}
