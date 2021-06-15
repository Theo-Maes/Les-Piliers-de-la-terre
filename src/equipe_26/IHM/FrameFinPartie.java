package equipe_26.IHM;

import javax.swing.*;

import equipe_26.Controleur;
import equipe_26.metier.Joueur;

import java.awt.event.*;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.FlowLayout;
import java.awt.Font;

public class FrameFinPartie extends JFrame implements ActionListener
{
	private final static Color   COULEUR_BACKGROUND = new Color (211,209,197);
	
	//private PanelStat panelStat;
	private        JButton btnQuitter;

	public FrameFinPartie(Joueur[] conclJoueur, String typeVictoire)
	{
		this.setTitle("Fin de partie");
		this.setLocation(50,50);
		this.setSize(700,600);
		this.setLayout(new BorderLayout(5,5));
		

		// Affectation vainqueur


		//Création des differents panel
		JPanel panelMilieu     = new JPanel( new GridLayout  (2,1,5,5) );
		JPanel panelMilieuHaut = new JPanel( new BorderLayout(5,5    ) );

		panelMilieuHaut.setOpaque(false);
		
		
		JPanel panelAvatar       = new JPanel(new BorderLayout(5,5));
		JPanel panelStatFin      = new JPanel(new BorderLayout(   ));
		JPanel panelTmp          = new JPanel(new GridLayout  (5,2));//Panel en attendant le panel stat
		JPanel panelTypeVictoire = new JPanel(new BorderLayout(5,5));
		
		JPanel panelBouton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		//Création des polices pour les differents label
		Font font1 = new Font("Courier", Font.BOLD, 25);
		Font font2 = new Font("Courier", Font.BOLD, 20);
		Font font3 = new Font("Courier", Font.BOLD, 20);
		
		
		//Label de victoire
		JLabel lblVictoire = new JLabel("Fin de partie", JLabel.CENTER);	//Victoire de
		lblVictoire.setForeground(new Color( 128, 96, 0));
		lblVictoire.setFont(font1);
		
		JLabel lblJoueur = new JLabel();
		lblJoueur.setHorizontalAlignment(JLabel.CENTER);

		ImageIcon avatar = new ImageIcon();


		if ( typeVictoire == "Egalité parfaite")
		{
			lblJoueur.setText (conclJoueur[0].getNom() + " et " + conclJoueur[1].getNom());
			avatar.setImage ( new ImageIcon("ressource/avatar/equipe.png").getImage().getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH));
		}
		else
		{
			lblJoueur.setText(conclJoueur[0].getNom());
			avatar.setImage(new ImageIcon("ressource/avatar/" + conclJoueur[0].getAvatar() + ".png").getImage().getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH));	
		}


		
		lblJoueur.setFont(font2);
		
			
		Image image = avatar.getImage();
		Image newimg = image.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH); 
		avatar = new ImageIcon(newimg);
		
		JLabel lblAvatar = new JLabel( avatar, JLabel.CENTER );
		
		//Panel Stats
		JLabel lblTmp = new JLabel("Statistiques :", JLabel.CENTER);
		
		//Label type de victoire
		JLabel lblTypeVictoire = new JLabel( typeVictoire, JLabel.CENTER);
		lblTypeVictoire.setFont(font3);
		lblTypeVictoire.setForeground(new Color(160,137,66));
		
		lblVictoire.setBackground(FrameFinPartie.COULEUR_BACKGROUND);
		panelMilieu.setBackground(FrameFinPartie.COULEUR_BACKGROUND);
		panelBouton.setBackground(FrameFinPartie.COULEUR_BACKGROUND);
		
		//Bouton quitter
		this.btnQuitter = new JButton("Retour au menu");
		this.btnQuitter.addActionListener(this);
		
		
			/************************/
			/*     Positionnement   */
			/************************/
			
		panelAvatar.add(lblAvatar, BorderLayout.CENTER);
		
		panelStatFin.add(lblTmp, BorderLayout.NORTH);

		panelTmp.add(new JLabel ("Nom du joueur : "            + conclJoueur[0].getNom           ())); 
		panelTmp.add(new JLabel ("Nom du joueur : "            + conclJoueur[1].getNom           ()));

		panelTmp.add(new JLabel ("Nombre de pilier restant : " + conclJoueur[0].getNbPilier      ()));
		panelTmp.add(new JLabel ("Nombre de pilier restant : " + conclJoueur[1].getNbPilier      ()));

		panelTmp.add(new JLabel ("Nombre de pilier détruit : " + conclJoueur[0].getPilierDetruit ()));
		panelTmp.add(new JLabel ("Nombre de pilier détruit : " + conclJoueur[1].getPilierDetruit ()));

		panelTmp.add(new JLabel ("Nombre de dalles totales : " + conclJoueur[0].getNbDalle       ()));
		panelTmp.add(new JLabel ("Nombre de dalles totales : " + conclJoueur[1].getNbDalle       ()));

		panelTmp.add(new JLabel ("Nombre de dalles perdues : " + conclJoueur[0].getDallePerdue   ()));
		panelTmp.add(new JLabel ("Nombre de dalles perdues : " + conclJoueur[1].getDallePerdue   ()));

		
		

		panelStatFin.add(panelTmp, BorderLayout.CENTER);
		
		panelMilieuHaut.add(lblJoueur,       BorderLayout.NORTH);
		panelMilieuHaut.add(panelAvatar,     BorderLayout.WEST);
		panelMilieuHaut.add(panelStatFin,    BorderLayout.CENTER);

		panelTypeVictoire.add(lblTypeVictoire, BorderLayout.CENTER);
		
		panelMilieu.add(panelMilieuHaut);
		panelMilieu.add(panelTypeVictoire);
		
		panelBouton.add(this.btnQuitter);
		
		
		this.add(lblVictoire, BorderLayout.NORTH);
		this.add(panelMilieu, BorderLayout.CENTER);
		this.add(panelBouton, BorderLayout.SOUTH);
		
		
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Controleur.getInstance().setFrameSuiviVisible(true);

		Controleur.getInstance().setframeSuiviActuelle(new FrameJoueur(Controleur..getInstance().getJoueur1()), new FrameJoueur(Controleur..getInstance().getJoueur2()) ) );
		Controleur.getInstance().setFrameJeuActuelle(new FrameMenu() );
	}
}
