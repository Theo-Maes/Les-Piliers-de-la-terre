package iut.equipe26.projetTut.metier;

import java.util.ArrayList;

/** Les Piliers de la terres
 * @author Paul
 * @author Alan
 * @author Théo
 * @author Thomas
 * @author Jason
 * @author Pierre
 */

public class Plateau
{
	private final int MAX_DALLE = 16;
	
	private ArrayList<Dalle> ensDalles;
	
	private Boolean bMVictoire = false;
	private Boolean bGVictoire = false;
  
	private Boolean bEgalite   = false;
	
	private static int compteur = 0;
	
	private Joueur j1;	//G
	private Joueur j2;	//M
	
	public Plateau()
	{
		this.ensDalles = new ArrayList<>();
		this.j1 = new Joueur();
		this.j2 = new Joueur();
	}
	
	public void dalleCustom(/*int  ,*/ int numVoisine )
	{

	}

	public void plateauAuto() 
	{

		for(int i=0; i< this.MAX_DALLE; i++)
			this.ensDalles.add(new Dalle());

		//this.ensDalles.get(0] == dalle A					this.ensDalles.get(8]  == dalle I
		//this.ensDalles.get(1] == dalle B					this.ensDalles.get(9]  == dalle J
		//this.ensDalles.get(2] == dalle C					this.ensDalles.get(10] == dalle K
		//this.ensDalles.get(3] == dalle D					this.ensDalles.get(11] == dalle L
		//this.ensDalles.get(4] == dalle E					this.ensDalles.get(12] == dalle M
		//this.ensDalles.get(5] == dalle F					this.ensDalles.get(13] == dalle N
		//this.ensDalles.get(6] == dalle G					this.ensDalles.get(14] == dalle O
		//this.ensDalles.get(7] == dalle H					this.ensDalles.get(15] == dalle P
		
		//this.ensDalles.get(0] == dalle A
		this.ensDalles.get(0).ajouterVoisine(4, this.ensDalles.get(1));		//Dalle B	
		this.ensDalles.get(0).ajouterVoisine(3, this.ensDalles.get(4));		//Dalle E	
		this.ensDalles.get(0).ajouterVoisine(2, this.ensDalles.get(2));		//Dalle C	
		
		//this.ensDalles.get(1] == dalle B
		this.ensDalles.get(1).ajouterVoisine(4, this.ensDalles.get(3));		//Dalle D
		this.ensDalles.get(1).ajouterVoisine(3, this.ensDalles.get(7));		//Dalle H
		this.ensDalles.get(1).ajouterVoisine(2, this.ensDalles.get(4));		//Dalle E
		
		//this.ensDalles.get(2] == dalle C
		this.ensDalles.get(2).ajouterVoisine(4, this.ensDalles.get(4));		//Dalle E
		this.ensDalles.get(2).ajouterVoisine(3, this.ensDalles.get(8));		//Dalle I
		this.ensDalles.get(2).ajouterVoisine(2, this.ensDalles.get(5));		//Dalle F
		
		//this.ensDalles.get(3] == dalle D
		this.ensDalles.get(3).ajouterVoisine(4, this.ensDalles.get(6));		//Dalle G
		this.ensDalles.get(3).ajouterVoisine(3, this.ensDalles.get(10));	//Dalle K
		this.ensDalles.get(3).ajouterVoisine(2, this.ensDalles.get(7));		//Dalle H
		
		//this.ensDalles.get(4] == dalle E
		this.ensDalles.get(4).ajouterVoisine(4, this.ensDalles.get(7));		//Dalle H
		this.ensDalles.get(4).ajouterVoisine(3, this.ensDalles.get(11));	//Dalle L
		this.ensDalles.get(4).ajouterVoisine(2, this.ensDalles.get(8));		//Dalle I
		
		//this.ensDalles.get(5] == dalle F
		this.ensDalles.get(5).ajouterVoisine(4, this.ensDalles.get(8));		//Dalle I
		this.ensDalles.get(5).ajouterVoisine(3, this.ensDalles.get(12));	//Dalle M
		this.ensDalles.get(5).ajouterVoisine(2, this.ensDalles.get(9));		//Dalle J
		
		//this.ensDalles.get(6] == dalle G
		this.ensDalles.get(6).ajouterVoisine(2, this.ensDalles.get(10));	//Dalle K
		
		//this.ensDalles.get(7] == dalle H
		this.ensDalles.get(7).ajouterVoisine(4, this.ensDalles.get(10));	//Dalle K
		this.ensDalles.get(7).ajouterVoisine(3, this.ensDalles.get(13));	//Dalle N
		this.ensDalles.get(7).ajouterVoisine(2, this.ensDalles.get(11));	//Dalle L
		
		//this.ensDalles.get(8] == dalle I
		this.ensDalles.get(8).ajouterVoisine(4, this.ensDalles.get(11));	//Dalle L
		this.ensDalles.get(8).ajouterVoisine(3, this.ensDalles.get(14));	//Dalle O
		this.ensDalles.get(8).ajouterVoisine(2, this.ensDalles.get(12));	//Dalle M
		
		//this.ensDalles.get(9] == dalle J
		this.ensDalles.get(9).ajouterVoisine(4, this.ensDalles.get(12));	//Dalle M
		
		//this.ensDalles.get(10] == dalle K
		this.ensDalles.get(10).ajouterVoisine(2, this.ensDalles.get(13));	//Dalle N
		
		//this.ensDalles.get(11] == dalle L
		this.ensDalles.get(11).ajouterVoisine(4, this.ensDalles.get(13));	//Dalle N
		this.ensDalles.get(11).ajouterVoisine(3, this.ensDalles.get(15));	//Dalle P
		this.ensDalles.get(11).ajouterVoisine(2, this.ensDalles.get(14));	//Dalle O
		
		//this.ensDalles.get(12] == dalle M
		this.ensDalles.get(12).ajouterVoisine(4, this.ensDalles.get(14));	//Dalle O
		
		//this.ensDalles.get(13] == dalle N
		this.ensDalles.get(13).ajouterVoisine(2, this.ensDalles.get(15));	//Dalle P
		
		//this.ensDalles.get(14] == dalle O
		this.ensDalles.get(14).ajouterVoisine(4, this.ensDalles.get(15));	//Dalle P
		
		//this.ensDalles.get(15] == dalle P
		// Rien
		
		for (Dalle d : this.ensDalles)
			System.out.println(d);
		
		System.out.println();
		
		
		for (Dalle d : this.ensDalles)
			System.out.println(d.toStringXY());
		
		ajoutPilier(this.ensDalles.get(0), 2);
		ajoutPilier(this.ensDalles.get(0), 4);
		ajoutPilier(this.ensDalles.get(1), 2);
		ajoutPilier(this.ensDalles.get(2), 0);
		ajoutPilier(this.ensDalles.get(4), 0);
		
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
			
			Plateau.ajoutTour();
		}
	}
	
	public int getNbTour()	{return Plateau.compteur;}
	private static void ajoutTour(){Plateau.compteur++;}
}
