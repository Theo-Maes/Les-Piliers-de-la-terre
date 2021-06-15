package equipe_26.IHM;

import equipe_26.metier.Joueur;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PanelStat extends JPanel
{
	private Joueur   joueur;
	private JLabel   lblNbPilierDetruit;
	private JLabel   lblNbDalle;
	private JLabel   lblDallePerdue;
	private JLabel   lblDalleTotal;
	private JLabel   lblPilierRestant;
	private JLabel[] ensLabelPilierRestant;
	
	public PanelStat(Joueur joueur)
	{
		this.joueur = joueur;

		this.setLayout(new BorderLayout(5,5));

		this.ensLabelPilierRestant = new JLabel[24];


		JPanel panelHaut          = new JPanel (                                   );
		JPanel panelMilieu        = new JPanel (new GridLayout   (2,1)             );
		JPanel panelInfoMilieu    = new JPanel (new GridLayout   (2,1)             );	
		JPanel panelPilierRestant = new JPanel (new BorderLayout ()                );
		JPanel panelImgPilier     = new JPanel (                                   );
		JPanel panelTmp           = new JPanel (                                   );
		JPanel panelInfo          = new JPanel (new FlowLayout   (FlowLayout.LEFT) );


		JLabel lblNomJoueur = new JLabel ( this.joueur.getNom(), JLabel.CENTER );

		lblNomJoueur.setFont( new Font ("Courier", Font.BOLD, 30 ) );
		lblNomJoueur.setForeground( this.joueur.getCouleur() );
	
		for(int i=0; i < this.ensLabelPilierRestant.length; i++)
			this.ensLabelPilierRestant[i] = new JLabel(new ImageIcon(new ImageIcon("ressource/images/pilier.png").getImage().getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH)));
		
		this.lblNbPilierDetruit = new JLabel("Nombre de pilier detruit : "             +   this.joueur.getPilierDetruit ()                                  );
		this.lblNbDalle         = new JLabel("Nombre de dalle(s) possédé(s) : "        +   this.joueur.getNbDalle       () + "/9"                           );
		this.lblDallePerdue     = new JLabel("Nombre de dalle(s) perdue(s) : "         +   this.joueur.getDallePerdue   ()                                  );
		this.lblDalleTotal      = new JLabel("Nombre de dalle(s) gagnée(s) en tout : " + ( this.joueur.getNbDalle       () + this.joueur.getDallePerdue() ) );
		this.lblPilierRestant   = new JLabel("Nombre de pilier restant : "             +   this.joueur.getNbPilier()       + "/24"                          );

		

		panelHaut.add ( new JLabel(new ImageIcon(new ImageIcon("ressource/avatar/" + this.joueur.getAvatar() + ".png").getImage().getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH))));
		panelHaut.add (lblNomJoueur);


		for(int i=0; i < 24; i++)
			panelImgPilier.add(ensLabelPilierRestant[i]);


		panelPilierRestant.add( this.lblPilierRestant, BorderLayout.NORTH  );
		panelPilierRestant.add( panelImgPilier       , BorderLayout.CENTER );



		panelInfo      .add (lblNbPilierDetruit );
		panelInfo      .add (lblNbDalle         );
		panelInfo      .add (lblDallePerdue     );
		panelInfo      .add (lblDalleTotal      );

		panelInfoMilieu.add (panelPilierRestant );
		panelInfoMilieu.add (panelInfo          );

		panelMilieu    .add (panelInfoMilieu    );
		panelMilieu    .add (panelTmp           );
		
		this.add(panelHaut  , BorderLayout.NORTH );
		this.add(panelMilieu, BorderLayout.CENTER);
	}

	public void majIHM()
	{
		for(int cpt = 0; cpt < 24; cpt++)
			this.ensLabelPilierRestant[cpt].setVisible(cpt < this.joueur.getNbPilier() ? true : false);

		this.lblPilierRestant  .setText("Nombre de pilier restant : "             +   this.joueur.getNbPilier()       + "/24"                          );
		this.lblNbPilierDetruit.setText("Nombre de pilier detruit : "             +   this.joueur.getPilierDetruit ()                                  );
		this.lblNbDalle        .setText("Nombre de dalle(s) possédé(s) : "        +   this.joueur.getNbDalle       () + "/9"                           );
		this.lblDallePerdue    .setText("Nombre de dalle(s) perdue(s) : "         +   this.joueur.getDallePerdue   ()                                  );
		this.lblDalleTotal     .setText("Nombre de dalle(s) gagnée(s) en tout : " + ( this.joueur.getNbDalle       () + this.joueur.getDallePerdue() ) );
	}
}