package equipe_26.metier;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import equipe_26.Controleur;
import equipe_26.IHM.FrameFinPartie;
import equipe_26.IHM.FrameJeu;

/** Classe Plateau
  * Classe qui définit un Plateau
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
	private ArrayList<Pilier> pilierMarque = new ArrayList<>();
	
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
	private String sScenario;
	
	/** Constructeur du Plateau
	  * Initialise tous les paramètres selon le mode
	  * @param iNum numéro du mode (0->GUI | 1->CUI)
	  */
	public Plateau(int iNum)
	{
		this.iNum = iNum;
		this.sScenario = "scenario1";

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
	
	/** Retourne la liste des Dalle du Plateau
	  * @return la liste des Dalle du Plateau
	  */
	public ArrayList<Dalle> getEnsDalles() {return this.ensDalles;}
	
	/** Initialise les Dalles*/
	public void dalleInit()
	{
		Dalle.reinitialiser();
		for(int i=0; i< this.MAX_DALLE; i++)
			this.ensDalles.add(new Dalle());
	}
	
	/** Initialise le Plateau de base*/
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
	
	/** Lance le plateau automatique en CUI*/
	public void plateauAutoCUI()
	{
		this.initPlateauBase();		
		this.choixJoueur();
		this.jeuCUI();
	}
	
	/** Lance le plateau automatique en GUI*/
	public void plateauAuto()
	{
		this.initPlateauBase();		
	}
	
	/** Retourne la saisie du cmd
	  * @return la saisie du cmd
	  */
	public String getSaisie()
	{
		Scanner sc = new Scanner()
		String sRet = "";
		try
		{
			sRet = sc.nextLine();
		}catch(Exception e){}
		
		return sRet;
	}
	
	/** Créer les joueurs à partir de donnée fournies par le joueur*/
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
	
	/** Lance le jeu en mode CUI*/
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
	
	/** Vérifie si il y a une victoire ou une égalité
	  * @return true si quelqu'un a gagné ou qu'il y a une égalité 
	  */
	public boolean verification()
	{
		
		//Si un Architecte possède 9 Dalles
		int g = this.joueur1.getNbDalle();
		int m = this.joueur2.getNbDalle();
		
		if( m > 8) this.bMVictoire = true;
		if( g > 8) this.bGVictoire = true;
		
		this.sTypeVictoire = "Victoire après avoir conquis 9 dalles ou plus";
		
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

	/** Vérifie si il y a égalité*/
	public void verifEgalite()
	{
		if( this.joueur1.getPilierDetruit() == this.joueur2.getPilierDetruit() ) this.bEgalite   = true;

		if( this.joueur1.getPilierDetruit() >  this.joueur2.getPilierDetruit() ) this.bGVictoire = true;

		if( this.joueur2.getPilierDetruit() >  this.joueur1.getPilierDetruit() ) this.bMVictoire = true;

		this.sTypeVictoire = this.bEgalite == true ? "Egalité parfaite" : "Victoire par destruction de pilier";
	}
	
	/** Ajoute un pilier
	  * @param d Dalle où l'on veut ajouter un pilier
	  * @param coin coin où l'on veut ajouter un pilier
	  */
	public void ajoutPilier(Dalle d, int iCoin)	//Ajout d'un pilier
	{
		if(d.ajouterPilier(iCoin))
		{

			for(Dalle dalle : this.ensDalles)
				dalle.RAZConstruire();
			
			if( this.getNbTour()%2 == 0)		//Si c'est pair, c'est le premier joueur qui joue
				this.joueur1.decrementer();	//Nombre de pilier du joueur qui baisse
			else
				this.joueur2.decrementer();
				
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
					Controleur.getInstance().setFrameSuiviVisible(false);
					Controleur.getInstance().setFrameJeuActuelle(new FrameFinPartie( conclJoueur , this.sTypeVictoire));
				}
      
			Plateau.iCompteur++;
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
		
		if (Controleur.getInstance().getFrameJeuActuelle() instanceof FrameJeu )
			( (FrameJeu) Controleur.getInstance().getFrameJeuActuelle()).majIHM();
			
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

	/** Retourne le nombre de tour
	  * @return le nombre de tour
	  */
	public int    getNbTour       () { return Plateau.iCompteur;  }
	
	/** Retourne le vainqueur
	  * @return le joueur qui à gagné
	  */
	public Joueur getVainqueur    () { return this.jVainqueur;    }
	
	/** Retourne le type de victoire
	  * @return le type de victoire
	  */
	public String getTypeVictoire () { return this.sTypeVictoire; }

	
	/** Fait l'affichage en CUI*/
	public void affichage()
	{
		System.out.println("\n" + this.toString() + "\n" + this.toStringP() + "\n" + this.toStringC()) ;		
	}

	/** Retourne les informations du Plateau
	 * @return les informations du Plateau
	 */
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
  
	/** Retourne les informations du controle des Dalles
	 * @return les informations du controle des Dalles
	 */
	public String toStringC()
	{
		String sRet="";
		for(Dalle d : this.ensDalles)
		{
			sRet += d.toStringC() + "\n";
		}
		return sRet;
	}

	/** Retourne les informations des piliers des Dalles
	 * @return les informations des piliers des Dalles
	 */
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
	
	/** Permet de lancer le mode scénario
	  * @param sMode mode du jeu (CUI ou GUI)
	  */
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
			sChoix = this.getScenario();
			sChoix = sChoix.substring(8);
		}
		
		this.chargerScenario(Integer.parseInt(sChoix), sMode);
		
	}
	
	/** Définit le scénario à lire
	  * @param s scénario à lire
	  */
	public void setScenario(String s)
	{
		this.sScenario = s;
	}
	
	/** Retourne le scénario actuel
	  * @return le scénario actuel
	  */
	public String getScenario(){return this.sScenario;}
	
	/** Charge le scénario passé en paramètre
	  * @param num numéro du scénario à charger
	  * @param sMode mode du scénario
	  */
	public void chargerScenario(int num, String sMode)
	{
		int iNumDalle1;
		int iNumDalle2;
		int iNumDalle3;
		int iCote;
		int iCoin;
		//Dalle.reinitialiser();
		dalleInit();

		try
		{
			Scanner sc = new Scanner ( new FileInputStream ("scenario/scenario" + num + ".txt") );
			
			//Lecture de la position des dalles
			while(sc.hasNext() && !sc.hasNext("Pilier"))
			{
				String s = sc.nextLine();
				iNumDalle1 = (int)(s.charAt(0) - 'A');
				iCote     = Character.getNumericValue(s.charAt(1));
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

		if(sMode.equals("CUI"))
			this.jeuCUI();

	}
}
