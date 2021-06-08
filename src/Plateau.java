public class Plateau
{
	private Dalle[] ensDalles;
	
	private Boolean bMVictoire = false;
	private Boolean bGVictoire = false;
	
	private static int compteur = 0;
	
	private Joueur j1;	//G
	private Joueur j2;	//M
	
	public Plateau()
	{
		this.ensDalles = new Dalle[16];
		this.j1 = new Joueur();
		this.j2 = new Joueur();
		
		
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
				this.bMVictoire = verifEgalite();
				this.bGVictoire = !this.bMVictoire;
			}
				
			else
			{
				this.bMVictoire = m>g;
				this.bGVictoire = !this.bMVictoire;
			}
			
		}
		
	}
	//test
	public boolean verifEgalite()
	{
		if ( this.j1.getPilierDetruit() == this.j2.getPilierDetruit() )
		{
			//Afficher ecran egalité
		}
		else
			return this.j2.getPilierDetruit() > this.j1.getPilierDetruit();
		
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
			
			Plateau.ajoutTour();
		}
	
	}
	
	public int getNbTour()	{return Plateau.compteur;}
	private static void ajoutTour(){Plateau.compteur++;}
	
	public static void main(String[] args)
	{
		new Plateau();
		
	}
	
}