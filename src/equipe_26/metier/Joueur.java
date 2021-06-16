package equipe_26.metier;

import java.awt.Color;

/** Classe Joueur
  * Classe qui définit un Joueur
  * @author Paul
  * @author Alan
  * @author Théo
  * @author Thomas
  * @author Jason
  * @author Pierre
  */
public class Joueur
{
	//Attributs
	private String sNom;
	private String sNomCouleur;
	private String sNomAvatar;
	
	private Color cCouleur;
	
	private int iNbPilier     ;
	private int iNbDalle      ;
	private int iPilierDetruit;
	private int iNbDallePerdue;
	
	/** Constructeur avec paramètres
	  * Initialise les informations 
	  * nécessaire à la pratique du jeu
	  *
	  * @param sNom nom du Joueur
	  * @param sCoul couleur du Joueur
	  * @param sAvatar avatar du Joueur
	  */
	public Joueur(String sNom, String sCoul, String sAvatar)
	{
		this.sNom        = sNom   ;
		this.sNomCouleur = sCoul  ;
		this.sNomAvatar  = sAvatar;
		
		this.iNbPilier         = 24;
		this.iNbDalle          =  0;
		this.iPilierDetruit    =  0;
		this.iNbDallePerdue    =  0;

		if(sCoul != null)
		this.cCouleur = this.setCouleur();
	}
	
	/** Contructeur par défaut
	  * Il appelle le constructeur avec paramètre 
	  * et donne comme paramètres null
	  */
	public Joueur() { this(null,null,null); }
	
	/** Retourne le nom du Joueur
	  * @return le nom du Joueur
	  */
	public String getNom            () {return this.sNom          ;}
	
	/** Retourne le nom de la couleur du Joueur
	  * @return le nom de la couleur du Joueur
	  */
	public String getCoul           () {return this.sNomCouleur   ;}
	
	/** Retourne l'avatar du Joueur
	  * @return l'avatar du Joueur
	  */
	public String getAvatar         () {return this.sNomAvatar    ;}
	
	/** Retourne la couleur du Joueur
	  * @return la couleur du Joueur
	  */
	public Color  getCouleur        () {return this.cCouleur      ;}
	
	/** Retourne le nombre de Pilier du Joueur
	  * @return le nombre de Pilier du Joueur
	  */
	public int    getNbPilier       () {return this.iNbPilier     ;}
	
	/** Retourne le nombre de Dalle du Joueur
	  * @return le nombre de Dalle du Joueur
	  */
	public int    getNbDalle        () {return this.iNbDalle      ;}
	
	/** Retourne le nombre de Pilier detruit du Joueur
	  * @return le nombre de Pilier detruit du Joueur
	  */
	public int    getPilierDetruit  () {return this.iPilierDetruit;}
	
	/** Retourne le nombre de Dalle perdue du Joueur
	  * @return le nombre de Dalle perdue du Joueur
	  */
	public int    getDallePerdue    () {return this.iNbDallePerdue;}
	
	/** Définit le nom du Joueur
	  * @param sNom nom du Joueur à définir
	  */
	public void setNom   (String sNom)    {this.sNom       = sNom    ;}
	
	/** Définit l'avatar du Joueur
	  * @param sAvatar avatar du Joueur à définir
	  */
	public void setAvatar(String sAvatar) {this.sNomAvatar = sAvatar ;}
	
	/** Définit le nom de la couleur du Joueur
	  * @param sCoul nom de la couleur du Joueur à définir
	  */
	public void setCoul  (String sCoul)   
	{
		this.sNomCouleur = sCoul;
		this.cCouleur = this.setCouleur();
	}
	
	/** Définit la couleur du Joueur
	  * @return la couleur du Joueur
	  */
	private Color setCouleur()
	{
		switch(this.sNomCouleur)
		{
			case "rouge"  -> {return new Color( 255,   0,   0  );}
			case "marron" -> {return new Color(  96,  64,  44  );}
			case "violet" -> {return new Color( 132,  11, 199  );}
			case "vert"   -> {return new Color(  67, 216,  76  );}
			case "cyan"   -> {return new Color(  24, 237, 230  );}
			case "bleu"   -> {return new Color(  27,  32, 240  );}
			case "jaune"  -> {return new Color( 196, 205,  36  );}
			case "orange" -> {return new Color( 255, 129,  50  );}
			default       -> {return new Color(  48,  46,  46  );}//gris
		}
	}

	/** Remet les paramètres du joueur par défaut*/
	public void reinitialiser()
	{
		this.iNbPilier         =  24;
		this.iNbDalle          =  0;
		this.iPilierDetruit    =  0;
		this.iNbDallePerdue    =  0;
	}
	
	/** Incrémente de nombre de Dalle possédées
	  * @param nb nombre de Dalle prises
	  * @return true si le nombre passé en paramètre est supérieur à 0
	  */
	public boolean priseDalle(int nb)
	{
		if(nb < 0){return false;}
		this.iNbDalle += nb;
		return true;
	}
	
	/** Incrémente de nombre de Dalle perdue
	  * Décrémente le nombre de Dalle possédées
	  * @param nb nombre de Dalle perdues
	  * @return true si le nombre passé en paramètre est inferieur aux nombre de Dalle possédées
	  */
	public boolean perteDalle(int nb)
	{
		if(nb > this.iNbDalle ){return false;}
		this.iNbDalle -= nb;
		this.iNbDallePerdue += nb;

		return true;
	}
	
	/** Incrémente de nombre de Pilier détruit
	  * @param nb nombre de Pilier détruit
	  * @return true si le nombre passé en paramètre est inferieur à 0
	  */
	public boolean detruirePilier(int nb)
	{
		if(nb < 0){return false;}
		this.iPilierDetruit += nb;
		return true;
	}
	
	/** Décrémente de nombre de Pilier du Joueur
	  * @return true si le nombre de Pilier est supérieur à 0
	  */
	public boolean decrementer()
	{
		if(this.iNbPilier<1){return false;}
		this.iNbPilier--;
		return true;
	}
	
	/** Retourne les informations du Joueur
	 * @return les informations du Joueur
	 */
	public String toString()
	{
		return this.sNom + " [ " + this.sNomCouleur + " ] ";
	}
}