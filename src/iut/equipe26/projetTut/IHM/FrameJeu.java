package iut.equipe26.projetTut.IHM;

import javax.swing.JFrame;
import iut.equipe26.projetTut.Controleur;

public class FrameJeu extends JFrame 
{
	private PanelPlateau panelPlateau;

	public FrameJeu() 
	{
		this.setTitle("Les Piliers de la terre");
		this.setSize(700, 600);

		this.panelPlateau = new PanelPlateau();

		this.add(this.panelPlateau);
		this.majIHM();

		
		this.addComponentListener (Controleur.getInstance());
		this.addWindowListener    (Controleur.getInstance());


		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	private void majIHM() {
		this.panelPlateau.repaint();
	}
}
