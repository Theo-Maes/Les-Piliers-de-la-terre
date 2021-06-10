package iut.equipe26.projetTut.IHM;

import javax.swing.*;

import iut.equipe26.projetTut.Controleur;
import iut.equipe26.projetTut.metier.Joueur;

import java.awt.*;
import java.awt.event.*;

public class FrameFinPartie extends JFrame implements ActionListener
{
	//private PanelStat panelStat;
	private JButton btnQuitter;
	private static Color COULEUR_BACKGROUND = new Color (211,209,197);
	
	public FrameFinPartie()
	{
		this.setTitle("Fin de partie");
		this.setLocation(50,50);
		this.setSize(700,600);
		this.setLayout(new BorderLayout(5,5));
		
		//Création des differents panel
		JPanel panelMilieu     = new JPanel(new GridLayout(2,1,5,5));
		
		JPanel panelMilieuHaut = new JPanel(new BorderLayout(5,5)  );
		panelMilieuHaut   .setOpaque(false);
		
		
		JPanel panelAvatar       = new JPanel(new BorderLayout(5,5));
		JPanel panelTmp          = new JPanel();//Panel en attendant le panel stat
		JPanel panelTypeVictoire = new JPanel(new BorderLayout(5,5));
		
		JPanel panelBouton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		//Création des polices pour les differents label
		Font font1 = new Font("Courier", Font.BOLD, 45);
		Font font2 = new Font("Courier", Font.BOLD, 25);
		Font font3 = new Font("Courier", Font.BOLD, 20);
		
		
		//Label de victoire
		JLabel lblVictoire = new JLabel("GG EZ", JLabel.CENTER);	//Victoire de
		lblVictoire.setForeground(new Color( 128, 96, 0));
		lblVictoire.setFont(font1);
		
		//Label Nom Joueur
		JLabel lblJoueur = new JLabel("LARDON" /*+ this.ctrl.getVainqueur().getNom()*/, JLabel.CENTER);
		lblJoueur.setFont(font2);
		
		//Label avatar
		ImageIcon avatar = new ImageIcon("./src/ressource/images/lardon.jpg"/*+ this.ctrl.getVainqueur().getAvatar()*/);		
		Image image = avatar.getImage();
		Image newimg = image.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH); 
		avatar = new ImageIcon(newimg);
		
		JLabel lblAvatar = new JLabel( avatar, JLabel.CENTER );
		
		//Panel Stats
		JLabel lblTmp = new JLabel("les stats", JLabel.CENTER);
		
		//Label type de victoire
		JLabel lblTypeVictoire = new JLabel("Victoire par bg attitude"/* this.ctrl.getTypeVictoire() */, JLabel.CENTER);
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
		
		panelTmp.add(lblTmp);
		
		panelMilieuHaut.add(lblJoueur, BorderLayout.NORTH);
		panelMilieuHaut.add(panelAvatar, BorderLayout.WEST);
		panelMilieuHaut.add(panelTmp, BorderLayout.CENTER);

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
		Controleur.getInstance().setframeSuiviActuelle(new FrameJoueur(new Joueur()), new FrameJoueur(new Joueur() ) );
		Controleur.getInstance().setFrameJeuActuelle(new FrameMenu());
	}
}