package iut.equipe26.projetTut.metier;

import java.awt.Color;

public class Joueur
{
	private String nom;
	private String nomCouleur;
	private String nomAvatar;
	
	private Color couleur;
	
	private int nbPilier;
	private int nbDalle ;
	private int pilierDetruit;
	private int nbDallePerdue;
	private int nbDalleTourAvant;
	
	public Joueur(String nom, String coul, String avatar)
	{
		this.nomCouleur = coul;
		this.nomAvatar  = avatar;
		this.nom = nom;

		if(coul != null)
		this.couleur = this.setCouleur();
		
		this.nbPilier         = 24;
		this.nbDalle          =  0;
		this.pilierDetruit    =  0;
		this.nbDallePerdue    =  0;
		this.nbDalleTourAvant =  0;
	}
	
	public Joueur() { this(null,null,null); }
	
	public String getNom           () {return this.nom          ;}
	public String getCoul          () {return this.nomCouleur   ;}
	public String getAvatar        () {return this.nomAvatar    ;}
	public Color  getCouleur       () {return this.couleur      ;}
	public int    getNbPilier      () {return this.nbPilier     ;}
	public int    getNbDalle       () {return this.nbDalle      ;}
	public int    getPilierDetruit () {return this.pilierDetruit;}
	public int    getDallePerdue   () {return this.nbDallePerdue;}
	
	
	public void setNom   (String nom)    {this.nom = nom;           }
	public void setCoul  (String coul)   {this.nomCouleur = coul;   }
	public void setAvatar(String avatar) {this.nomAvatar  = avatar; }
	
	public Color setCouleur()
	{
		switch(this.nomCouleur)
		{
			case "rouge"  -> {return new Color( 255,   0,   0  );}
			case "marron" -> {return new Color(  96,  64,  44  );}
			case "violet" -> {return new Color( 132,  11, 199  );}
			case "vert"   -> {return new Color(  67, 216,  76  );}
			case "cyan"   -> {return new Color(  24, 237, 230  );}
			case "bleu"   -> {return new Color(  27,  32, 240  );}
			case "jaune"  -> {return new Color( 196, 205,  36  );}
			case "orange" -> {return new Color( 255, 129,  50  );}
			default       -> {return new Color(  48,  46,  46  );}//Gris
			
		}
	}
	
	public boolean priseDalle(int nb)
	{
		if(nb < 0){return false;}
		this.nbDalle += nb;
		return true;
	}
	
	public boolean perteDalle(int nb)
	{
		if(nb > this.nbDalle){return false;}
		this.nbDalle -= nb;
		return true;
	}
	
	public boolean detruirePilier(int nb)
	{
		if(nb < 0){return false;}
		this.pilierDetruit += nb;
		return true;
	}
	
	public boolean decrementer()
	{
		if(this.nbPilier<1){return false;}
		this.nbPilier--;
		return true;
	}
	
	public void dallePerdue()
	{
		if(this.nbDalleTourAvant > this.nbDalle)this.nbDallePerdue++;
		this.nbDalleTourAvant = this.nbDalle;
	}
	public String toString()
	{
		return this.nom + " [ " + this.nomCouleur + " ] ";
	}
}
