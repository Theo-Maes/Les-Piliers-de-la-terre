package equipe_26.IHM;

import equipe_26.Controleur;

import javax.swing.JFrame;

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

	public void majIHM() {
		this.panelPlateau.repaint();
	}
}
