package iut.equipe26.projetTut.IHM;

import javax.swing.JFrame;

public class FrameMenu extends JFrame 
{
	private static FrameMenu instance;

	public FrameMenu() 
	{
		
		this.setTitle("Menu - Les Pilliers de la Terre");
		this.setSize(700, 600);


		this.add(new PanelChoix(this) );
		
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	// public FrameMenu getFrame() { return instance; }

	// public void close() { this.close(); }
}
