package equipe_26.IHM;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComboBox;

import equipe_26.Controleur;
import equipe_26.metier.Joueur;
import equipe_26.metier.Plateau;

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
	private JButton   btnPlateauAuto;
	private JButton   btnPlateauCustom;
	private JComboBox cbScenario;
	private JButton   btnScenario;
	private final int NB_SCENARIO = 9;

	public PanelChoixMenu() 
	{

		this.setLayout(null);

		/*-----------------------*/
		/*-------Creation--------*/
		/*-----------------------*/

		this.btnPlateauAuto   = new JButton ("Plateau Automatique"    );
		this.btnPlateauCustom = new JButton ("Plateau Personalisable" );
		this.cbScenario       = new JComboBox                        ();
		this.btnScenario      = new JButton ("Lancer Scenario"        );

		for(int cpt=1; cpt <= NB_SCENARIO; cpt++)
			cbScenario.addItem("scenario" + cpt);


		
		this.btnPlateauAuto  .setBounds(700/2-340/2, 320   , 340, 50);
		this.btnPlateauCustom.setBounds(700/2-360/2, 385   , 360, 20);
		this.cbScenario      .setBounds(700/2-200/2, 450   , 200, 20);
		this.btnScenario     .setBounds(700/2-300/2, 480   , 300, 25);


		this.btnPlateauAuto  .setFont(new Font ("Franklin Gothic Medium", Font.BOLD, 25) );
		this.btnPlateauCustom.setFont(new Font ("Franklin Gothic Medium", Font.BOLD, 25) );
		this.cbScenario      .setFont(new Font ("Franklin Gothic Medium", Font.BOLD, 15) );
		this.btnScenario     .setFont(new Font ("Franklin Gothic Medium", Font.BOLD, 25) );


		this.btnPlateauAuto.setOpaque           (false);
		this.btnPlateauAuto.setContentAreaFilled(false);
		this.btnPlateauAuto.setBorderPainted    (false);
		this.btnPlateauAuto.setFocusPainted     (false);

		this.btnPlateauCustom.setOpaque           (false);
		this.btnPlateauCustom.setContentAreaFilled(false);
		this.btnPlateauCustom.setBorderPainted    (false);
		this.btnPlateauCustom.setFocusPainted     (false);
		
		this.btnScenario.setOpaque           (false);
		this.btnScenario.setContentAreaFilled(false);
		this.btnScenario.setBorderPainted    (false);
		this.btnScenario.setFocusPainted     (false);


		this.btnPlateauAuto  .setForeground(new Color(116, 54, 0  ));
		this.btnPlateauCustom.setForeground(new Color(116, 54, 0  ));
		this.btnScenario     .setForeground(new Color(116, 54, 0  ));
		this.cbScenario      .setForeground(new Color(116, 54, 0  ));

		/*-----------------------*/
		/*----Positionnement-----*/
		/*-----------------------*/

		this.add(this.btnPlateauAuto   );
		this.add(this.btnPlateauCustom );
		this.add(this.cbScenario       );
		this.add(this.btnScenario      );

		/*-----------------------*/
		/*------activation-------*/
		/*-----------------------*/

		this.btnPlateauAuto  .addActionListener(this);
		this.btnPlateauCustom.addActionListener(this);
		this.btnScenario     .addActionListener(this);

	}


	public void paintComponent (Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(Toolkit.getDefaultToolkit().getImage("ressource/images/Background.jpg"), 0, 0, this);
	}

	
	public void actionPerformed(ActionEvent e) 
	{

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

		Controleur.getInstance().setPlateau(new Plateau(0));

		if (e.getSource() == this.btnScenario)
		{
			System.out.println("passage 1");
			Controleur.getInstance().getPlateau().setScenario(cbScenario.getSelectedItem().toString());
			System.out.println("passage 2");
			Controleur.getInstance().getPlateau().scenario("GUI");
			System.out.println("passage 3");
		}

		if(e.getSource() == this.btnPlateauAuto) {
			Controleur.getInstance().getPlateau().plateauAuto();
		}
		System.out.println("passage 4");
		Controleur.getInstance().setFrameSuiviActuelle (new FrameStat(j1), new FrameStat(j2) );
		Controleur.getInstance().setFrameJeuActuelle   (new FrameJeu()                       );
	}
}
