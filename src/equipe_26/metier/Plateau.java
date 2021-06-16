package equipe_26.metier;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import equipe_26.Controleur;
import equipe_26.IHM.FrameJeu;

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
	private final int MAX_DALLE = 16;
	
	private ArrayList<Dalle> ensDalles;
	private ArrayList<Pilier> pilierMarque = new ArrayList<>();
	
	private Boolean bMVictoire = false;
	private Boolean bGVictoire = false;
	
	private Boolean bEgalite   = false;
	
	private static int compteur = 0;
	
	private Joueur j1;	//G
	private Joueur j2;	//M
	
	public Plateau(int num)
	{
		this.ensDalles = new ArrayList<>();
		this.j1 = new Joueur();
		this.j2 = new Joueur();
		
		if (num == 1)
		{
			System.out.println("(1) -> Plateau de base"     +
						 "\n(2) -> Plateau personnalisé"+
						 "\n(3) -> Scénarios");
			Scanner chx = new Scanner (System.in);
			int choix = chx.nextInt();
			chx.close();

			switch(choix)
			{
				case 2 -> System.out.println("choix 2");
				case 3 -> scenario();
				default  -> plateauAutoCUI();
				
			}
		}
	}

	public ArrayList<Dalle> getEnsDalles() {return this.ensDalles;}
	
	public void dalleInit()
	{
		for(int i=0; i< this.MAX_DALLE; i++)
			this.ensDalles.add(new Dalle());
	}
	
	public void initPlateauBase()
	{
		this.dalleInit();
		
		int numDalle1;
		int numDalle2;
		int iCote;
		
		try
		{
			Scanner sc = new Scanner ( new FileInputStream ("scenario/plateau.txt") );
			
			//Lecture de la position des dalles
			while(sc.hasNext())
			{
				String s = sc.nextLine();
				numDalle1 = (int)(s.charAt(0) - 'A');
				iCote     = Character.getNumericValue(s.charAt(1)      );
				numDalle2 = (int)(s.charAt(2) - 'A');
				this.ensDalles.get(numDalle1).ajouterVoisine(iCote, this.ensDalles.get(numDalle2));			
			}
			
		}catch (Exception e){ e.printStackTrace(); }
	}
	
	public void plateauAutoCUI()
	{
		initPlateauBase();		
		choixJoueur();
		jeuCUI();
	}
	
	public void plateauAuto()
	{
		initPlateauBase();		
	}
	
	public void choixJoueur()
	{
		Scanner sc = new Scanner ( System.in );
		
		//Création des joueurs
		System.out.print("Nom joueur 1 : ");
		// String sNom1 = sc.nextLine();
		String sNom1 = "Alan";
		System.out.print("Couleur joueur 1 : ");
		// String sCoul1 = sc.nextLine();
		String sCoul1 = "bleu";
		System.out.print("Avatar joueur 1 : ");
		// String sAvatar1 = sc.nextLine();
		String sAvatar1 = "1664";
		
		System.out.print("Nom joueur 2 : ");
		// String sNom2 = sc.nextLine();
		String sNom2 = "Paul";
		System.out.print("Couleur joueur 2 : ");
		// String sCoul2 = sc.nextLine();
		String sCoul2 = "rouge";
		System.out.print("Avatar joueur 2: ");
		// String sAvatar2 = sc.nextLine();
		String sAvatar2 = "Lardon";
		System.out.println();
		
		this.j1 = new Joueur(sNom1, sCoul1, sAvatar1);
		this.j2 = new Joueur(sNom2, sCoul2, sAvatar2);

		sc.close();
	}
	
	public void jeuCUI()
	{
		Scanner sc = new Scanner ( System.in );
		String sCase = "";
		
		while(!verification())
		{
			affichage();
			if (getNbTour()%2 == 0)System.out.println("Au tour de " + this.j1.getNom() + " de poser un pilier");
			else                   System.out.println("Au tour de " + this.j2.getNom() + " de poser un pilier");
			
			char cDalle = 'A';
			int iCoin   =  0 ;
			boolean bOk = false;
			
			while(!bOk)
			{
				System.out.println("Où voulez-vous poser un pilier? (Lettre de la dalle puis coté de la dalle)");
				sCase = sc.nextLine();
				if(sCase.length() == 2)
				{
					cDalle    = Character.toUpperCase    (sCase.charAt(0));
					iCoin     = Character.getNumericValue(sCase.charAt(1));  
					
					System.out.println(cDalle +" "+iCoin);
			
					bOk = cDalle >= 'A' && cDalle <= 'P' && iCoin >= 0 && iCoin <= 5;
				}
				
				
			}
			ajoutPilier(this.ensDalles.get((int)(cDalle - 'A') ), iCoin);
			
			System.out.println("Fin du tour n°" + (getNbTour()));
		}
		sc.close();
	}
	public void jeu()
	{
		/*
		while(!verification())
		{
			
			ajoutPilier(this.ensDalles.get((int)(cDalle - 'A') ), iCoin);
			
			System.out.println("Fin du tour n°" + (getNbTour()));
		}
		*/
	}
	
	public boolean verification()
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
				this.verifEgalite();
			}
				
			else
			{
				this.bMVictoire = m>g;
				this.bGVictoire = !this.bMVictoire;
			}
			
		}
		return this.bMVictoire || this.bGVictoire || this.bEgalite;
	}
  
	public void verifEgalite()
	{
		if( this.j1.getPilierDetruit() == this.j2.getPilierDetruit() )  this.bEgalite   = true;
		if( this.j1.getPilierDetruit() >  this.j2.getPilierDetruit() )  this.bGVictoire = true;
		if( this.j2.getPilierDetruit() >  this.j1.getPilierDetruit() )  this.bMVictoire = true;
	}
	
	public void ajoutPilier(Dalle d, int coin)	//Ajout d'un pilier
	{
		if(d.ajouterPilier(coin))
		{
			this.enfermement(d, coin);

			for(Dalle dalle : this.ensDalles)
				dalle.RAZConstruire();
			
			if( getNbTour()%2 == 0)		//Si c'est pair, c'est le premier joueur qui joue
				this.j1.decrementer();	//Nombre de pilier du joueur qui baisse
			else
				this.j2.decrementer();
				
			this.verification();
			Plateau.compteur++;
		}
	}
	
	public boolean enfermement(Dalle dalle, int coin)
	{
		for (Pilier p : this.getPilierAutour(dalle, coin)) {
			if (p != null && p.getCoul() != dalle.getPiliers()[coin].getCoul()) {
				Dalle d = this.getDallePilier(p);
				int c = this.getCotePilier(d, p);

				if (this.verifEnfermement(d, c)) {
					for (Pilier pDetruit : this.pilierMarque) {
						this.getDallePilier(pDetruit).detruire(this.getCotePilier(this.getDallePilier(pDetruit), pDetruit));
					}
				}
				this.pilierMarque.clear();
			}
		} 
		
		((FrameJeu)Controleur.getInstance().getFrameJeuActuelle()).majIHM();
		return false;
	}
	
	private boolean verifEnfermement(Dalle d, int c) {
		Pilier tmp = d.getPiliers()[c];
		this.pilierMarque.add(tmp);

		for(Pilier p : this.getPilierAutour(d, c)) {
			if (p != null) {
				if (!this.pilierMarque.contains(p) && p.getCoul() == d.getPiliers()[c].getCoul()) {
					Dalle dalle = this.getDallePilier(p);
					int    coin = this.getCotePilier(dalle, p);
					if (!this.verifEnfermement(dalle, coin))
						return false;
				}
			} else {
				return false;
			}
		}
		return true;
	}

	private ArrayList<Pilier> getPilierAutour(Dalle d, int coin) {
		int coinSvt = coin+1;
		int coinPrc = coin-1;
		if (coinSvt > 5) {coinSvt = 0;}
		if (coinPrc < 0) {coinPrc = 5;}

		ArrayList<Pilier> tmp = new ArrayList<>();

		tmp.add(d.getPiliers()[coinSvt]);
		tmp.add(d.getPiliers()[coinPrc]);
		if (d.getDalleV(coin) != null)
			tmp.add(d.getDalleV(coin).getPiliers()[coinPrc]);
		else if (d.getDalleV(coinPrc) != null)
			tmp.add(d.getDalleV(coinPrc).getPiliers()[coinSvt]);

		return tmp;
	}

	private Dalle getDallePilier(Pilier p) {
		for (Dalle d : this.ensDalles) {
			for (Pilier pilier : d.getPiliers()) {
				if (pilier == p) {
					return d;
				}
			}
		}
		return null;
	}

	private int getCotePilier(Dalle d, Pilier p) {
		for (int i = 0; i < d.getPiliers().length; i++) {
			if (d.getPiliers()[i] != null && d.getPiliers()[i] == p) {
				return i;
			}
		}
		return 0;
	}

	public int getNbTour()	{return Plateau.compteur;}
	
	public void affichage()
	{
		System.out.println("\n" + toString() + "\n" + toStringP() + "\n" + toStringC()) ;		
	}

	public String toString()
    {
        String sRet = String.format("%35s",           "+-----------------------+") + "\n"
                    + String.format("%35s",           "|         Lié à         |") + "\n"
                    + String.format("%35s",           "+---+---+---+---+---+---+") + "\n"
                    + String.format("%35s",           "| 0 | 1 | 2 | 3 | 4 | 5 |") + "\n"
                    + String.format("%35s", "+---------+---+---+---+---+---+---+") + "\n" ;
        for(Dalle d : this.ensDalles)
        {
            sRet += d.toString() + "\n";
            sRet += String.format("%35s", "+---------+---+---+---+---+---+---+") + "\n" ;
        }
        return sRet;
	}

    public String toStringC()
    {
    	String sRet="";
    	for(Dalle d : this.ensDalles)
        {
            sRet += d.toStringC() + "\n";
        }
        return sRet;
    }
    
    public String toStringP()
    {
        String sRet = String.format("%35s",           "+-----------------------+") + "\n"
                    + String.format("%35s",           "|         Pilier        |") + "\n"
                    + String.format("%35s",           "+---+---+---+---+---+---+") + "\n"
                    + String.format("%35s",           "| 0 | 1 | 2 | 3 | 4 | 5 |") + "\n"
                    + String.format("%35s", "+---------+---+---+---+---+---+---+") + "\n" ;
        for(Dalle d : this.ensDalles)
        {
            sRet += d.toStringP() + "\n";
            sRet += String.format("%35s", "+---------+---+---+---+---+---+---+") + "\n" ;
        }
        return sRet;
    }
	
	public void scenario()
	{
		System.out.println("Numéro du scénario à charger");
		Scanner sc1 = new Scanner (System.in);
		String sChoix = sc1.next();
		
		if( Integer.parseInt(sChoix) > 0 && Integer.parseInt(sChoix) < 18)
			chargerScenario(Integer.parseInt(sChoix));
		
		sc1.close();
	}
	
	public void chargerScenario(int num)
	{
		int numDalle1;
		int numDalle2;
		int numDalle3;
		int iCote;
		int iCoin;
		
		dalleInit();
		
		try
		{
			Scanner sc = new Scanner ( new FileInputStream ("scenario" + num + ".txt") );
			
			//Lecture de la position des dalles
			while(!sc.hasNext("Pilier"))
			{
				String s = sc.nextLine();
				numDalle1 = (int)(s.charAt(0) - 'A');
				iCote     = Character.getNumericValue(s.charAt(1)      );
				numDalle2 = (int)(s.charAt(2) - 'A');
				this.ensDalles.get(numDalle1).ajouterVoisine(iCote, this.ensDalles.get(numDalle2));			
			}			
			
			sc.nextLine();
			
			//Lecture de la position des piliers
			while(sc.hasNext())
			{
				String sPilier = sc.nextLine();
				numDalle3 = (int)(sPilier.charAt(0) - 'A');
				iCoin     = Character.getNumericValue(sPilier.charAt(1));
				ajoutPilier(this.ensDalles.get(numDalle3), iCoin);
			}
			
		}catch (Exception e){ e.printStackTrace(); }
			
		jeu();
	}
}