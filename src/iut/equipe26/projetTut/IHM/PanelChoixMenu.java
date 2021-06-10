package iut.equipe26.projetTut.IHM;

import javax.swing.JButton;
import javax.swing.JPanel;

import iut.equipe26.projetTut.Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class PanelChoixMenu extends JPanel implements ActionListener
{
	private FrameMenu frameMenu;
	private JButton btnPlateauAuto;
	private JButton btnPlateauCustom;

	public PanelChoixMenu(FrameMenu frameMenu) 
	{
		this.frameMenu = frameMenu;

		this.setLayout(null);


		this.btnPlateauAuto   = new JButton("Plateau Automatique");
		this.btnPlateauCustom = new JButton("Plateau Custom"     );


		this.btnPlateauAuto   .setBounds(200, 265, 260, 76);
		this.btnPlateauCustom .setBounds(200, 370, 260, 76);


		this.add(this.btnPlateauAuto   );
		this.add(this.btnPlateauCustom );	

	
		this.btnPlateauAuto   .addActionListener(this);
		this.btnPlateauCustom .addActionListener(this);

	}


	public void paintComponent (Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(Toolkit.getDefaultToolkit().getImage("src/ressource/images/Background.jpg"), 0, 0, this);
	}

	
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == this.btnPlateauAuto) {
			Controleur.getInstance().getPlateau().plateauAuto();
		}

		Controleur.getInstance().setFrameActuelle(new FrameJeu());
	}
}
