package equipe_26.IHM;

import javax.swing.JButton;
import javax.swing.JPanel;

import equipe_26.Controleur;
import equipe_26.metier.Joueur;

import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
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


		this.btnPlateauAuto   = new JButton ("Plateau Automatique"    );
		this.btnPlateauCustom = new JButton ("Plateau Personalisable" );
		//this.btnQuitter       = new JButton ("Quitter"                );


		this.btnPlateauAuto   .setBounds(700/2-340/2, 320   , 340, 50);
		this.btnPlateauCustom .setBounds(700/2-340/2, 385   , 340, 20);
		//this.btnQuitter       .setBounds(700-115    , 600-75, 120, 50);


		this.btnPlateauAuto  .setFont(new Font ("Franklin Gothic Medium", Font.BOLD, 25) );
		this.btnPlateauCustom.setFont(new Font ("Franklin Gothic Medium", Font.BOLD, 25) );
		//this.btnQuitter.setFont(new Font ("Franklin Gothic Medium", Font.BOLD, 25) );
		//this.btnPlateauCustom.setFont(new Font ("Matura MT Script Capitals", Font.BOLD, 30) );


		this.btnPlateauAuto.setOpaque              (false);
		this.btnPlateauAuto.setContentAreaFilled   (false);
		this.btnPlateauAuto.setBorderPainted       (false);
		this.btnPlateauAuto.setFocusPainted(false);

		this.btnPlateauCustom.setOpaque            (false);
		this.btnPlateauCustom.setContentAreaFilled (false);
		this.btnPlateauCustom.setBorderPainted     (false);
		this.btnPlateauAuto.setFocusPainted(false);

		//this.btnQuitter.setOpaque            (false);
		//this.btnQuitter.setContentAreaFilled (false);
		//this.btnQuitter.setBorderPainted     (false);

		this.btnPlateauAuto  .setForeground(new Color(116, 54, 0  ));
		this.btnPlateauCustom.setForeground(new Color(116, 54, 0  ));
		//this.btnQuitter.setForeground(new Color(116, 54, 0  ));
		//this.btnPlateauCustom.setForeground(new Color(123, 54, 37 ));


		this.add(this.btnPlateauAuto   );
		this.add(this.btnPlateauCustom );	
		//this.add(this.btnQuitter       );

	
		this.btnPlateauAuto   .addActionListener(this);
		this.btnPlateauCustom .addActionListener(this);
		//this.btnQuitter       .addActionListener(this);

	}


	public void paintComponent (Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(Toolkit.getDefaultToolkit().getImage("ressource/images/Background.jpg"), 0, 0, this);
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
		
		Controleur.getInstance().setframeSuiviActuelle (new FrameStat(j1), new FrameStat(j2) );
		Controleur.getInstance().setFrameJeuActuelle   (new FrameJeu()                       );
	}
}