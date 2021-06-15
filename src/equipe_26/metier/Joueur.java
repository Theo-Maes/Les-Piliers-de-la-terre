package equipe_26.metier;

import java.awt.Color;

public class Joueur
{
	private String sNom;
	private String sNomCouleur;
	private String sNomAvatar;
	
	private Color cCouleur;
	
	private int iNbPilier     ;
	private int iNbDalle      ;
	private int iPilierDetruit;
	private int iNbDallePerdue;
	
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
	
	public Joueur() { this(null,null,null); }
	
	public String getNom            () {return this.sNom          ;}
	public String getCoul           () {return this.sNomCouleur   ;}
	public String getAvatar         () {return this.sNomAvatar    ;}
	public Color  getCouleur        () {return this.cCouleur      ;}
	public int    getNbPilier       () {return this.iNbPilier     ;}
	public int    getNbDalle        () {return this.iNbDalle      ;}
	public int    getPilierDetruit  () {return this.iPilierDetruit;}
	public int    getDallePerdue    () {return this.iNbDallePerdue;}
	
	
	public void setNom   (String sNom)    {this.sNom       = sNom    ;}
	public void setAvatar(String sAvatar) {this.sNomAvatar = sAvatar ;}
	public void setCoul  (String sCoul)   
	{
		this.sNomCouleur = sCoul;
		this.cCouleur = this.setCouleur();
	}
	
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

	public void reinitialiser()
	{
		this.iNbPilier         =  24;
		this.iNbDalle          =  0;
		this.iPilierDetruit    =  0;
		this.iNbDallePerdue    =  0;
	}
	
	public boolean priseDalle(int nb)
	{
		if(nb < 0){return false;}
		this.iNbDalle += nb;
		return true;
	}
	
	public boolean perteDalle(int nb)
	{
		if(nb > this.iNbDalle){return false;}
		
    this.iNbDalle -= nb;
		this.iNbDallePerdue += nb;
    
		return true;
	}
	
	public boolean detruirePilier(int nb)
	{
		if(nb < 0){return false;}
		this.iPilierDetruit += nb;
		return true;
	}
	
	public boolean decrementer()
	{
		if(this.iNbPilier<1){return false;}
		this.iNbPilier--;
		return true;
	}

	public String toString()
	{
		return this.sNom + " [ " + this.sNomCouleur + " ] ";
	}
}