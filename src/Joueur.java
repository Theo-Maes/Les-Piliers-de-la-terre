public class Joueur
{
	private String    nom;
	private String   coul;
	private String avatar;
	
	private int nbPilier;
	private int nbDalle ;
	private int pilierDetruit;
	
	public Joueur(String nom, String coul, String avatar)
	{
		this.nom    = nom   ;
		this.coul   = coul  ;
		this.avatar = avatar;
		
		this.nbPilier      = 24;
		this.nbDalle       =  0;
		this.pilierDetruit =  0;
	}
	
	public Joueur(){this("","","");}
	
	public String getNom       (){return this.nom          ;}
	public String getCoul      (){return this.coul         ;}
	public String getAvatar    (){return this.avatar       ;}
	public int getNbPilier     (){return this.nbPilier     ;}
	public int getNbDalle      (){return this.nbDalle      ;}
	public int getPilierDetruit(){return this.pilierDetruit;}
	
	public void setNom   (String nom)   {this.nom = nom      ;}
	public void setCoul  (String coul)  {this.coul = coul    ;}
	public void setAvatar(String avatar){this.avatar = avatar;}
	
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
	public String toString()
	{
		return this.nom + " [ " + this.coul + " ] ";
	}
}
