package iut.equipe26.projetTut.metier;

/** Les Piliers de la terres
 * @author Paul
 * @author Alan
 * @author Pierre
 * @author Théo
 * @author Thomas
 * @author Jason
 */

public class Plateau
{
	private Dalle[] ensDalles;
	
	private Boolean bMVictoire = false;
	private Boolean bGVictoire = false;
	private Boolean bEgalite   = false;
	
	private static int compteur = 0;
	
	private Joueur j1;	//G
	private Joueur j2;	//M
	
	public Plateau()
	{
		this.ensDalles = new Dalle[16];
		this.j1 = new Joueur();
		this.j2 = new Joueur();
	}
	
	public void dalleCustom(/*int  ,*/ int numVoisine )
	{

	}

	public void plateauAuto() 
	{

		for(int i=0; i<this.ensDalles.length; i++)
			this.ensDalles[i] = new Dalle();

		//this.ensDalles[0] == dalle A					this.ensDalles[8]  == dalle I
		//this.ensDalles[1] == dalle B					this.ensDalles[9]  == dalle J
		//this.ensDalles[2] == dalle C					this.ensDalles[10] == dalle K
		//this.ensDalles[3] == dalle D					this.ensDalles[11] == dalle L
		//this.ensDalles[4] == dalle E					this.ensDalles[12] == dalle M
		//this.ensDalles[5] == dalle F					this.ensDalles[13] == dalle N
		//this.ensDalles[6] == dalle G					this.ensDalles[14] == dalle O
		//this.ensDalles[7] == dalle H					this.ensDalles[15] == dalle P
		
		//this.ensDalles[0] == dalle A
		this.ensDalles[0].ajouterVoisine(4, this.ensDalles[1]);	//Dalle B	
		this.ensDalles[0].ajouterVoisine(3, this.ensDalles[4]);	//Dalle E	
		this.ensDalles[0].ajouterVoisine(2, this.ensDalles[2]);	//Dalle C	
		
		//this.ensDalles[1] == dalle B
		this.ensDalles[1].ajouterVoisine(4, this.ensDalles[3]);	//Dalle D
		this.ensDalles[1].ajouterVoisine(3, this.ensDalles[7]);	//Dalle H
		this.ensDalles[1].ajouterVoisine(2, this.ensDalles[4]);	//Dalle E
		
		//this.ensDalles[2] == dalle C
		this.ensDalles[2].ajouterVoisine(4, this.ensDalles[4]);		//Dalle E
		this.ensDalles[2].ajouterVoisine(3, this.ensDalles[8]);		//Dalle I
		this.ensDalles[2].ajouterVoisine(2, this.ensDalles[5]);		//Dalle F
		
		//this.ensDalles[3] == dalle D
		this.ensDalles[3].ajouterVoisine(4, this.ensDalles[6]);		//Dalle G
		this.ensDalles[3].ajouterVoisine(3, this.ensDalles[10]);	//Dalle K
		this.ensDalles[3].ajouterVoisine(2, this.ensDalles[7]);		//Dalle H
		
		//this.ensDalles[4] == dalle E
		this.ensDalles[4].ajouterVoisine(4, this.ensDalles[7]);		//Dalle H
		this.ensDalles[4].ajouterVoisine(3, this.ensDalles[11]);	//Dalle L
		this.ensDalles[4].ajouterVoisine(2, this.ensDalles[8]);		//Dalle I
		
		//this.ensDalles[5] == dalle F
		this.ensDalles[5].ajouterVoisine(4, this.ensDalles[8]);		//Dalle I
		this.ensDalles[5].ajouterVoisine(3, this.ensDalles[12]);	//Dalle M
		this.ensDalles[5].ajouterVoisine(2, this.ensDalles[9]);		//Dalle J
		
		//this.ensDalles[6] == dalle G
		this.ensDalles[6].ajouterVoisine(2, this.ensDalles[10]);	//Dalle K
		
		//this.ensDalles[7] == dalle H
		this.ensDalles[7].ajouterVoisine(4, this.ensDalles[10]);	//Dalle K
		this.ensDalles[7].ajouterVoisine(3, this.ensDalles[13]);	//Dalle N
		this.ensDalles[7].ajouterVoisine(2, this.ensDalles[11]);	//Dalle L
		
		//this.ensDalles[8] == dalle I
		this.ensDalles[8].ajouterVoisine(4, this.ensDalles[11]);	//Dalle L
		this.ensDalles[8].ajouterVoisine(3, this.ensDalles[14]);	//Dalle O
		this.ensDalles[8].ajouterVoisine(2, this.ensDalles[12]);	//Dalle M
		
		//this.ensDalles[9] == dalle J
		this.ensDalles[9].ajouterVoisine(4, this.ensDalles[12]);	//Dalle M
		
		//this.ensDalles[10] == dalle K
		this.ensDalles[10].ajouterVoisine(2, this.ensDalles[13]);	//Dalle N
		
		//this.ensDalles[11] == dalle L
		this.ensDalles[11].ajouterVoisine(4, this.ensDalles[13]);	//Dalle N
		this.ensDalles[11].ajouterVoisine(3, this.ensDalles[15]);	//Dalle P
		this.ensDalles[11].ajouterVoisine(2, this.ensDalles[14]);	//Dalle O
		
		//this.ensDalles[12] == dalle M
		this.ensDalles[12].ajouterVoisine(4, this.ensDalles[14]);	//Dalle O
		
		//this.ensDalles[13] == dalle N
		this.ensDalles[13].ajouterVoisine(2, this.ensDalles[15]);	//Dalle P
		
		//this.ensDalles[14] == dalle O
		this.ensDalles[14].ajouterVoisine(4, this.ensDalles[15]);	//Dalle P
		
		//this.ensDalles[15] == dalle P
		// Rien
		
		for (Dalle d : this.ensDalles)
			System.out.println(d);
		
		System.out.println();
		
		
		for (Dalle d : this.ensDalles)
			System.out.println(d.toStringXY());
		
		ajoutPilier(this.ensDalles[0], 2);
		ajoutPilier(this.ensDalles[0], 4);
		ajoutPilier(this.ensDalles[1], 2);
		ajoutPilier(this.ensDalles[2], 0);
		ajoutPilier(this.ensDalles[4], 0);
		
		for (Dalle d : this.ensDalles)
			System.out.println(d);
	}
	
	public void verification()
	{
		
		//Si un Architecte possède 9 Dalles
		int g = j1.getNbDalle();
		int m = j2.getNbDalle();
		
		if( m == 9) this.bMVictoire = true;
		if( g == 9) this.bGVictoire = true;
		
		//Lorsque chaque Architectes ont construit 24 Piliers
		int pilierTotal = this.j1.getNbPilier() + this.j2.getNbPilier();
		if ( pilierTotal == 0 )
		{
			if ( m == g )
			{
				verifEgalite();
			}
				
			else
			{
				this.bMVictoire = m>g;
				this.bGVictoire = !this.bMVictoire;
			}
			
		}
		
	}
  
	public void verifEgalite()
	{
		if( this.j1.getPilierDetruit() == this.j2.getPilierDetruit() )  this.bEgalite   = true;
		if( this.j1.getPilierDetruit() > this.j2.getPilierDetruit () )  this.bGVictoire = true;
		if( this.j2.getPilierDetruit() > this.j1.getPilierDetruit () )  this.bMVictoire = true;
	}
	
	public void ajoutPilier(Dalle d, int coin)	//Ajout d'un pilier
	{
		if(d.ajouterPilier(coin))
		{
			for(Dalle dalle : this.ensDalles)dalle.RAZConstruire();
			
			if( getNbTour()%2 == 0)		//Si c'est pair, c'est le premier joueur qui joue
				this.j1.decrementer();	//Nombre de pilier du joueur qui baisse
			else
				this.j2.decrementer();
			
			
			//oui ok alan, enfermement();
			
			
			

			Plateau.ajoutTour();
		}
	}
	
	public boolean enfermement(Dalle d, int coin)//int coin = indice du pilier posé
	{
		Piliers[] tabPilier = d.getPiliers();	//Les piliers de la dalle où ont ajoute le pilier
		
		if (d.getPrc(coin).getCoul() == tabPilier[coin])	return false;	//Si l'un des deux est de la meme couleur
		if (d.getSvt(coin).getCoul() == tabPilier[coin])	return false;
		
		
		//Pour les précedents
		if (d.getPrc(coin).getCoul() != tabPilier[coin])
		{
			Pilier p = d.getPrc(coin);
			
			int coinPrc = coin-1;
			if (coinPrc<0)coinPrc = 5;
			
			if (d.getPrc(coinPrc) != null && d.getPrc(coinPrc).getCoul() != p.getCoul())
			{
				if(d.getDalleV(coinPrc) == null)
				{
					int coin0 = coinPrc-1;
					if (coin0<0)coin0 = 5;
					if(d.getDalleV(coin0) == null)
					{
						d.detruire(coinPrc);
						return true;
					}
						
				}
				else
				{
					Dalle dalle = d.getDalleV(coin0);
					int coin1 = coinPrc + 2;
					if (coin1>5)coin1 = coin1-6;
					if(dalle.getPrc(coin1) == null || dalle.getPrc(coin1).getCoul() == p.getCoul())
						return false; //ça detruit pas
					else
					{
						d.detruire(coinPrc);
						return true;
					}
				}
			}
			else
			{
				Dalle dalle = d.getDalleV(coinPrc);
				int coin1 = coinPrc - 2;
					if (coin1<0)coin1 = coin1+6;
				if(dalle.getSvt(coin1) == null || dalle.getSvt(coin1).getCoul() == p.getCoul())
					return false; //ça detruit pas
				else
				{
					d.detruire(coinPrc);
					return true;
				}
			}
				
			getSvt
		}
		
		//Pour les suivants
		if (d.getSvt(coin).getCoul() != tabPilier[coin])
		{
			Pilier p = d.getSvt(coin);
			
			int getSvt = coin-1;
			if (getSvt<0)getSvt = 5;
			
			if (d.getSvt(getSvt) != null && d.getSvt(getSvt).getCoul() != p.getCoul())
			{
				if(d.getDalleV(getSvt) == null)
				{
					int coin0 = getSvt-1;
					if (coin0<0)coin0 = 5;
					if(d.getDalleV(coin0) == null)
					{
						d.detruire(getSvt);
						return true;
					}
						
				}
				else
				{
					Dalle dalle = d.getDalleV(coin0);
					int coin1 = getSvt + 2;
					if (coin1>5)coin1 = coin1-6;
					if(dalle.getSvt(coin1) == null || dalle.getSvt(coin1).getCoul() == p.getCoul())
						return false; //ça detruit pas
					else
					{
						d.detruire(getSvt);
						return true;
					}
				}
			}
			else
			{
				Dalle dalle = d.getDalleV(getSvt);
				int coin1 = getSvt - 2;
					if (coin1<0)coin1 = coin1+6;
				if(dalle.getPrc(coin1) == null || dalle.getPrc(coin1).getCoul() == p.getCoul())
					return false; //ça detruit pas
				else
				{
					d.detruire(getSvt);
					return true;
				}
			}
				
			
		}
	}
	
	
	public int getNbTour()	{return Plateau.compteur;}
	private static void ajoutTour(){Plateau.compteur++;}
}
