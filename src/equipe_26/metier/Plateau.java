package equipe_26.metier;

import java.util.ArrayList;
import java.util.Scanner;

import equipe_26.Controleur;
import equipe_26.IHM.FrameFinPartie;

import java.io.FileInputStream;

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
	
	private Boolean bMVictoire = false;
	private Boolean bGVictoire = false;
  
	private Boolean bEgalite   = false;
	
	private static int iCompteur = 0;
	
	private int iNum;
	
	private Joueur joueur1;	//G
	private Joueur joueur2;	//M
  
	private Joueur jVainqueur;
	private Joueur jPerdant;
	
	private String sTypeVictoire;
	
	public Plateau(int iNum)
	{
		this.iNum = iNum;

		this.ensDalles = new ArrayList<>();

		this.joueur1 = Controleur.getInstance().getJoueur1();
		this.joueur2 = Controleur.getInstance().getJoueur2();
		
		if (iNum == 1)
		{
			System.out.println("(1) -> Plateau de base"     +
						     "\n(2) -> Plateau personnalisé"+
						     "\n(3) -> Scénarios"            );
			Scanner chx = new Scanner (System.in);
			int iChoix = chx.nextInt();
			
			switch(iChoix)
			{
				case 2   -> System.out.println("choix 2");
				case 3   -> scenario("CUI");
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
		
		int iNumDalle1;
		int iNumDalle2;
		int iCote;
		
		try
		{
			Scanner sc = new Scanner ( new FileInputStream ("./scenario/plateau.txt") );
			
			//Lecture de la position des dalles
			while(sc.hasNext())
			{
				String s = sc.nextLine();
				iNumDalle1 = (int)(s.charAt(0) - 'A');
				iCote     = Character.getNumericValue(s.charAt(1)      );
				iNumDalle2 = (int)(s.charAt(2) - 'A');
				this.ensDalles.get(iNumDalle1).ajouterVoisine(iCote, this.ensDalles.get(iNumDalle2));			
			}
			
		}catch (Exception e){ e.printStackTrace(); }
	}
	
	public void plateauAutoCUI()
	{
		this.initPlateauBase();		
		this.choixJoueur();
		this.jeuCUI();
	}
	
	public void plateauAuto()
	{
		this.initPlateauBase();		
	}
	
	public String getSaisie()
	{
		String sRet = "";
        try
        {
			sRet = this.getSaisie();
        }catch(Exception e){}
		
		return sRet;
	}
	
	public void choixJoueur()
	{		
		//Création des joueurs
		System.out.print("Nom joueur 1 : ");
		String sNom1 = this.getSaisie();
		System.out.print("Couleur joueur 1 : ");
		String sCoul1 = this.getSaisie();
		System.out.print("Avatar joueur 1 : ");
		String sAvatar1 = this.getSaisie();
		
		System.out.print("Nom joueur 2 : ");
		String sNom2 = this.getSaisie();
		System.out.print("Couleur joueur 2 : ");
		String sCoul2 = this.getSaisie();
		System.out.print("Avatar joueur 2: ");
		String sAvatar2 = this.getSaisie();
		
		System.out.println();
		
		/*
		String sNom1 = "Alan";
		String sCoul1 = "bleu";
		String sAvatar1 = "1664";
		String sNom2 = "Paul";
		String sCoul2 = "rouge";
		String sAvatar2 = "Lardon";
		*/

		this.joueur1 = new Joueur(sNom1, sCoul1, sAvatar1);
		this.joueur2 = new Joueur(sNom2, sCoul2, sAvatar2);
		
	}
	
	public void jeuCUI()
	{
		
		String sCase = "";
		while(!this.verification())
		{
			affichage();
			if (this.getNbTour()%2 == 0)System.out.println("Au tour de " + this.joueur1.getNom() + " de poser un pilier");
			else                        System.out.println("Au tour de " + this.joueur2.getNom() + " de poser un pilier");
			
			char cDalle = 'A';
			int iCoin   =  0 ;
			boolean bOk = false;

			while(!bOk)
			{
				System.out.println("Où voulez-vous poser un pilier? (Lettre de la dalle puis coté de la dalle)");
				sCase = this.getSaisie();
				
				if(sCase.length() == 2)
				{
					cDalle    = Character.toUpperCase    (sCase.charAt(0));
					iCoin     = Character.getNumericValue(sCase.charAt(1));			
					bOk = cDalle >= 'A' && cDalle <= 'P' && iCoin >= 0 && iCoin <= 5;
				}				
			}
			
			this.ajoutPilier(this.ensDalles.get((int)(cDalle - 'A') ), iCoin);
			System.out.println("Fin du tour n°" + (this.getNbTour()));
		}
	}
	
	public boolean verification()
	{
		
		//Si un Architecte possède 9 Dalles
		int g = this.joueur1.getNbDalle();
		int m = this.joueur2.getNbDalle();
		
		if( m > 8) this.bMVictoire = true;
		if( g > 8) this.bGVictoire = true;
		
		this.sTypeVictoire = "Victoire après avoir concquit 9 dalles ou plus";
		
		//Lorsque chaque Architectes ont construit 24 Piliers
		int iPilierTotal = this.joueur1.getNbPilier() + this.joueur2.getNbPilier();
    
		if ( iPilierTotal == 0 )
        {
            if ( m == g )
            {
                this.verifEgalite();
            }
            else
            {
                this.bMVictoire    = m>g;
                this.bGVictoire    = !this.bMVictoire;
				this.sTypeVictoire = "Victoire grâce à un nombre de dalle supérieur";
            }   
        }
        return this.bMVictoire || this.bGVictoire || this.bEgalite;
    }
  
	
   public void verifEgalite()
    {
        if( this.joueur1.getPilierDetruit() == this.joueur2.getPilierDetruit() ) this.bEgalite   = true;

        if( this.joueur1.getPilierDetruit() >  this.joueur2.getPilierDetruit() ) this.bGVictoire = true;

        if( this.joueur2.getPilierDetruit() >  this.joueur1.getPilierDetruit() ) this.bMVictoire = true;

		this.sTypeVictoire = this.bEgalite == true ? "Egalité parfaite" : "Victoire par destruction de pilier";
    }
	
	public void ajoutPilier(Dalle d, int iCoin)	//Ajout d'un pilier
	{
		if(d.ajouterPilier(iCoin))
		{
			for(Dalle dalle : this.ensDalles)dalle.RAZConstruire();
			
			if( this.getNbTour()%2 == 0)		//Si c'est pair, c'est le premier joueur qui joue
				this.joueur1.decrementer();	//Nombre de pilier du joueur qui baisse
			else
				this.joueur2.decrementer();
			
			
			//this.enfermement(d, iCoin);
			
			if(this.iNum != 1)
				if ( this.verification() ) {
						if (this.bGVictoire) 
						{
							this.jVainqueur = this.joueur1;
							this.jPerdant   = this.joueur2;
						} 
						else 
						{
							this.jVainqueur = this.joueur2;
							this.jPerdant   = this.joueur1;
						}
					
						Joueur[] conclJoueur = new Joueur[]{ this.jVainqueur,this.jPerdant };
						Controleur.getInstance().setFrameSuiviVisible(true);
						Controleur.getInstance().setFrameJeuActuelle(new FrameFinPartie( conclJoueur , this.sTypeVictoire));
				}

			Plateau.ajoutTour();
		}
	}
	
	// public boolean enfermement(Dalle d, int coin)//int coin = indice du pilier posé
	// {
	// 	Pilier[] tabPilier = d.getPiliers();	//Les piliers de la dalle où ont ajoute le pilier
		
	// 	if(d.getPrc(coin) != null  &&
	// 	  tabPilier[coin] != null  &&
	// 	  d.getPrc(coin).getCoul() == tabPilier[coin].getCoul())	return false;	//Si l'un des deux est de la meme couleur
		
	// 	if(d.getSvt(coin) != null  &&
	// 	  tabPilier[coin] != null  &&
	// 	  d.getSvt(coin).getCoul() == tabPilier[coin].getCoul())	return false;
		
	// 	//Pour les précedents
	// 	if(d.getPrc(coin) != null && d.getPrc(coin).getCoul() != tabPilier[coin].getCoul())
	// 	{
	// 		Pilier p = d.getPrc(coin);
			
	// 		int coinPrc = coin-1;
	// 		if (coinPrc<0)coinPrc = 5;
			
	// 		if (d.getPrc(coinPrc) != null && d.getPrc(coinPrc).getCoul() != p.getCoul())
	// 		{
	// 			int coin0 = coinPrc-1;
	// 			if (coin0<0)coin0 = 5;
				
	// 			if(d.getDalleV(coinPrc) == null)
	// 			{
	// 				if(d.getDalleV(coin0) == null)
	// 				{
	// 					d.detruire(coinPrc);
	// 					return true;
	// 				}
						
	// 			}
	// 			else
	// 			{
	// 				if(d.getDalleV(coin0) != null)
	// 				{
	// 					Dalle dalle = d.getDalleV(coin0);
	// 					int coin1 = coinPrc + 2;		
	// 					if (coin1>5)coin1 = coin1-6;
	// 					if(dalle.getPrc(coin1) == null || dalle.getPrc(coin1).getCoul() == p.getCoul())
	// 						return false; //ça detruit pas
	// 					else
	// 					{
	// 						d.detruire(coinPrc);
	// 						return true;
	// 					}
	// 			}	 }
	// 		}
	// 		else
	// 		{
	// 			if(d.getDalleV(coinPrc) != null)
	// 			{
	// 				Dalle dalle = d.getDalleV(coinPrc);
	// 				int coin1 = coinPrc - 2;
	// 				if (coin1<0)coin1 = coin1+6;
	// 				if(dalle.getSvt(coin1) == null || dalle.getSvt(coin1).getCoul() == p.getCoul())
	// 					return false; //ça detruit pas
	// 				else
	// 				{
	// 					d.detruire(coinPrc);	//les problemes
	// 					return true;
	// 				}
	// 			}
	// 		}
	// 	}
		
	// 	//Pour les suivants
	// 	if(d.getSvt(coin) != null && tabPilier[coin] != null && 
	// 	   d.getSvt(coin).getCoul() != tabPilier[coin].getCoul())
	// 	{
	// 		Pilier p = d.getSvt(coin);
			
	// 		int coinSvt = coin+1;
	// 		if (coinSvt>5)coinSvt = 0;
			
	// 		if (d.getSvt(coinSvt) != null && d.getSvt(coinSvt).getCoul() != p.getCoul())
	// 		{
	// 			int coin0 = coinSvt+1;
	// 			if (coin0>5)coin0 = 0;

	// 			if(d.getDalleV(coinSvt) == null)
	// 			{
	// 				if(d.getDalleV(coin0) == null)
	// 				{
	// 					d.detruire(coinSvt);
	// 					return true;
	// 				}
						
	// 			}
	// 			else
	// 			{
	// 				Dalle dalle = d.getDalleV(coin0);
	// 				int coin1 = coinSvt - 2;
	// 				if (coin1<0)coin1 = coin1+6;
	// 				if(dalle.getSvt(coin1) == null || dalle.getSvt(coin1).getCoul() == p.getCoul())
	// 					return false; //ça detruit pas
	// 				else
	// 				{
	// 					d.detruire(coinSvt);
	// 					return true;
	// 				}
	// 			}
	// 		}
	// 		else
	// 		{
	// 			Dalle dalle = d.getDalleV(coinSvt);
	// 			int coin1 = coinSvt - 2;
	// 				if (coin1<0)coin1 = coin1+6;
	// 			if(dalle.getPrc(coin1) == null || dalle.getPrc(coin1).getCoul() == p.getCoul())
	// 				return false; //ça detruit pas
	// 			else
	// 			{
	// 				d.detruire(coinSvt);
	// 				return true;
	// 			}
	// 		}
	// 	}
	// 	return false;
	// }
	
	
	public int    getNbTour       () { return Plateau.iCompteur;  }
	public Joueur getVainqueur    () { return this.jVainqueur;    }
	public String getTypeVictoire () { return this.sTypeVictoire; }

	private static void ajoutTour(){Plateau.iCompteur++;}
	
	public void affichage()
	{
		System.out.println("\n" + this.toString() + "\n" + this.toStringP() + "\n" + this.toStringC()) ;		
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
	
	public void scenario(String sMode)
	{
		String sChoix = "";
		if(sMode.equals("CUI"))
		{
			
			System.out.println("Numéro du scénario à charger");
			sChoix = this.getSaisie();
			while( Integer.parseInt(sChoix) < 0 || Integer.parseInt(sChoix) > 18)
			{
				System.out.println("Numéro du scénario à charger");
				sChoix = this.getSaisie();
			}
		}
		else
		{
			//récupéré le numéro du scénario dans sChoix
		}
		
		this.chargerScenario(Integer.parseInt(sChoix));
		
	}
	
	public void chargerScenario(int num)
	{
		int iNumDalle1;
		int iNumDalle2;
		int iNumDalle3;
		int iCote;
		int iCoin;
		
		dalleInit();
		
		try
		{
			Scanner sc = new Scanner ( new FileInputStream ("scenario/scenario" + num + ".txt") );
			
			//Lecture de la position des dalles
			while(!sc.hasNext("Pilier"))
			{
				String s = sc.nextLine();
				iNumDalle1 = (int)(s.charAt(0) - 'A');
				iCote     = Character.getNumericValue(s.charAt(1)      );
				iNumDalle2 = (int)(s.charAt(2) - 'A');
				this.ensDalles.get(iNumDalle1).ajouterVoisine(iCote, this.ensDalles.get(iNumDalle2));			
			}			
			
			sc.nextLine();
			
			//Lecture de la position des piliers
			while(sc.hasNext())
			{
				String sPilier = sc.nextLine();
				iNumDalle3 = (int)(sPilier.charAt(0) - 'A');
				iCoin     = Character.getNumericValue(sPilier.charAt(1));
				this.ajoutPilier(this.ensDalles.get(iNumDalle3), iCoin);
			}
			
		}catch (Exception e){ e.printStackTrace(); }
			
		this.jeuCUI();
	}
}