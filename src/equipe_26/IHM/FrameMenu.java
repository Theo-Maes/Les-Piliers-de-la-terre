package equipe_26.IHM;

import javax.swing.JFrame;

import equipe_26.Controleur;

public class FrameMenu extends JFrame 
{
	public FrameMenu() 
	{
		this.setTitle("Menu - Les Pilliers de la Terre");
		this.setSize(700, 600);

		
		this.add(new PanelChoixMenu() );


		this.addComponentListener(Controleur.getInstance());
		this.addWindowListener   (Controleur.getInstance());

		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
}
