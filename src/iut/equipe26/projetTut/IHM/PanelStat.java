package iut.equipe26.projetTut.IHM;

import iut.equipe26.projetTut.metier.Joueur;

import javax.swing.*;
import java.awt.*;

public class PanelStat extends JPanel
{
	private Joueur joueur;
	private JLabel[] ensLabelPilierRestant;
	
	public PanelStat(Joueur joueur)
	{
		this.joueur = joueur;
		this.setLayout(new BorderLayout(5,5));
		
		JPanel panelHaut = new JPanel();
		
		Font fontNomJoueur = new Font("Courier", Font.BOLD, 30);
		JLabel lblNomJoueur = new JLabel(this.joueur.getNom(), JLabel.CENTER);
		lblNomJoueur.setForeground(this.joueur.getCouleur());
		lblNomJoueur.setFont(fontNomJoueur);
		
		ImageIcon imgAvatar = new ImageIcon("src/ressource/avatar/" + this.joueur.getAvatar() + ".png");
		Image imageAvt = imgAvatar.getImage();
		Image newAvt = imageAvt.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); 
		imgAvatar = new ImageIcon(newAvt);
		panelHaut.add(new JLabel(imgAvatar));
		panelHaut.add(lblNomJoueur);
		
		JPanel panelMilieu = new JPanel(new GridLayout(2,1));
		JPanel panelInfoMilieu = new JPanel(new GridLayout(2,1));
		
		JPanel panelPilierRestant = new JPanel(new BorderLayout());
		JPanel panelImgPilier = new JPanel();
		JPanel panelTmp = new JPanel();
		
		JLabel lblPilierRestant = new JLabel("Nombre de pilier restant : " + this.joueur.getNbPilier() + "/24");
		
		ImageIcon imgPilier = new ImageIcon("src/ressource/images/pilier.png");
		Image image = imgPilier.getImage();
		Image newimg = image.getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH); 
		imgPilier = new ImageIcon(newimg);
		
		
		JLabel[] ensLabelPilierRestant = new JLabel[24];
		
		for(int i=0; i<ensLabelPilierRestant.length; i++)
			ensLabelPilierRestant[i] = new JLabel(imgPilier);
		
		for(int i=0; i<this.joueur.getNbPilier(); i++)
			panelImgPilier.add(ensLabelPilierRestant[i]);
		
		
		panelPilierRestant.add(lblPilierRestant, BorderLayout.NORTH);
		panelPilierRestant.add(panelImgPilier, BorderLayout.CENTER);
		
		
		
		
		JPanel panelInfo = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblNbPilierDetruit = new JLabel("Nombre de pilier detruit : " + this.joueur.getPilierDetruit());
		JLabel lblNbDalle         = new JLabel("Nombre de dalle(s) possédé(s) : " + this.joueur.getNbDalle() + "/9");
		// JLabel lblDallePerdue     = new JLabel("Nombre de dalle(s) perdue(s) : " + this.joueur.getDallePerdue());
		JLabel lblDalleTotal      = new JLabel("Nombre de dalle(s) gagnée(s) en tout : " + ( this.joueur.getNbDalle() + this.joueur.getDallePerdue() ) );
		
		
		panelInfo.add(lblNbPilierDetruit);
		panelInfo.add(lblNbDalle);
		//panelInfo.add(lblDallePerdue);
		
		panelInfoMilieu.add(panelPilierRestant);
		panelInfoMilieu.add(panelInfo);
		
		panelMilieu.add(panelInfoMilieu);
		panelMilieu.add(panelTmp);
		
		this.add(panelHaut, BorderLayout.NORTH);
		this.add(panelMilieu, BorderLayout.CENTER);
	}
	
	public PanelStat getPanelStat()
	{
		return this;
	}
}