package iut.equipe26.projetTut.IHM;

import iut.equipe26.projetTut.metier.Joueur;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.Image;
import java.awt.Dimension;

public class PanelJoueur extends JPanel implements DocumentListener
{
	private Joueur     joueur;
	private JTextField txtPseudo;
	private JLabel     lblAvatar;
	private JLabel     lblCouleur;

	private JPanel    panelPseudo;
	
	public PanelJoueur(Joueur joueur)
	{
		this.joueur = joueur;
		this.setPreferredSize(new Dimension (200,200));

		this.setLayout ( new BorderLayout() );

		//creation

		this.txtPseudo  = new JTextField();
		this.lblAvatar  = new JLabel();
		this.lblCouleur = new JLabel();

		this.txtPseudo.setText ( this.joueur.getNom   () == null ? ""        : this.joueur.getNom   () );
		this.setAvatar         ( this.joueur.getAvatar() == null ? "equipe"  : this.joueur.getAvatar() );
		this.setCouleur        ( this.joueur.getCoul  () == null ? "gris"    : this.joueur.getCoul  () );

		this.panelPseudo = new JPanel();

		this.panelPseudo.setLayout ( new GridLayout ( 2, 1) );

		//positionnement

		this.panelPseudo.add ( new JLabel( "Pseudo", JLabel.CENTER ) );
		this.panelPseudo.add ( this.txtPseudo );

		this.add ( this.panelPseudo, BorderLayout.NORTH  );
		this.add ( this.lblAvatar  , BorderLayout.WEST   );
		this.add ( this.lblCouleur , BorderLayout.EAST   );
		
		//activation
		
		this.txtPseudo.getDocument().addDocumentListener(this);
	}
		
	public void setAvatar(String nomAvatar)
	{
		
		ImageIcon avatar = new ImageIcon("./src/ressource/avatar/" + nomAvatar + ".png");
		Image image      = avatar.getImage();
		Image newimg     = image.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH);

		avatar = new ImageIcon(newimg);

		this.lblAvatar.setIcon  (avatar   );
		this.joueur   .setAvatar(nomAvatar);
	}
	
	public void setCouleur(String nomCouleur)
	{
		ImageIcon couleur = new ImageIcon("./src/ressource/couleur/" + nomCouleur + ".png");
		Image image       = couleur.getImage();
		Image newimg      = image.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH); 

		couleur = new ImageIcon(newimg);

		this.lblCouleur.setIcon (couleur   );
		this.joueur    .setCoul (nomCouleur);
	}

	public void insertUpdate(DocumentEvent e) { this.changedUpdate(e); }
	public void removeUpdate(DocumentEvent e) { this.changedUpdate(e); }

	public void changedUpdate(DocumentEvent e) 
	{
		this.joueur.setNom(String.format("%-14.14s", this.txtPseudo.getText().replace(" ", "_")));
	}
}