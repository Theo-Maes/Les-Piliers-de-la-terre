package equipe_26.IHM;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;


import equipe_26.Controleur;
import equipe_26.metier.Joueur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;


public class PanelChoixMenu extends JPanel implements ActionListener
{
	private JButton btnPlateauAuto;
	private JButton btnPlateauCustom;
	private JButton btnQuitter;

	public PanelChoixMenu() 
	{

		this.setLayout(null);


		this.btnPlateauAuto   = new JButton("Plateau Automatique");
		this.btnPlateauCustom = new JButton("Plateau Custom"     );
		this.btnQuitter       = new JButton("Quitter"            );


		this.btnPlateauAuto   .setBounds(700/2-260/2, 265   , 260, 76);
		this.btnPlateauCustom .setBounds(700/2-260/2, 370   , 260, 76);
		this.btnQuitter       .setBounds(700-100    , 600-75, 100, 50);


		this.add(this.btnPlateauAuto   );
		this.add(this.btnPlateauCustom );	
		this.add(this.btnQuitter       );

	
		this.btnPlateauAuto   .addActionListener(this);
		this.btnPlateauCustom .addActionListener(this);
		this.btnQuitter       .addActionListener(this);

	}


	public void paintComponent (Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(Toolkit.getDefaultToolkit().getImage("./ressource/images/Background.jpg"), 0, 0, this);
	}

	
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.btnQuitter) System.exit(0);
		
		Joueur j1 = Controleur.getInstance().getJoueur1();
		Joueur j2 = Controleur.getInstance().getJoueur2();

		if( j1.getNom() == null || j2.getNom() == null )
		{
			JOptionPane.showMessageDialog(this, "Pseudo(s) manquant(s), veuillez renseigner vos pseudos", "Pseudo manquant", JOptionPane.ERROR_MESSAGE);
			return;
		}
		else
		{
			if(j1.getNom().equals(j2.getNom()))
			{
				JOptionPane.showMessageDialog(this, "Pseudos invalides, il ne peut y avoir deux fois le même nom", "Pseudos identiques", JOptionPane.ERROR_MESSAGE);
				return;
			}
			else
			{
				if(j1.getCoul().equals(j2.getCoul()))
				{
					JOptionPane.showMessageDialog(this, "Couleurs invalides, il ne peut y avoir deux fois la même couleur", "Couleurs identiques", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		}


		if(e.getSource() == this.btnPlateauAuto) {
			Controleur.getInstance().getPlateau().plateauAuto();
		}
		
		Controleur.getInstance().setframeSuiviActuelle(new FrameStat(j1), new FrameStat(j2));
		Controleur.getInstance().setFrameJeuActuelle(new FrameJeu());
	}
}