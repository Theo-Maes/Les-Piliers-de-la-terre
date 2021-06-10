package iut.equipe26.projetTut.IHM;

import javax.swing.JFrame;

import iut.equipe26.projetTut.Controleur;

public class FrameMenu extends JFrame 
{
	public FrameMenu() 
	{
		this.setTitle("Menu - Les Pilliers de la Terre");
		this.setSize(700, 600);

		
		this.add(new PanelChoixMenu(this) );


		this.addComponentListener(Controleur.getInstance());
		this.addWindowListener(Controleur.getInstance());

		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
}
