package iut.equipe26.projetTut.IHM;

import javax.swing.*;

import java.awt.event.*;
import java.awt.Dimension;

public class PanelAction extends JPanel implements ItemListener
{
	private FrameJoueur frmJ;
	
	private ButtonGroup cbChoix;

	private JRadioButton  rbAvatar;
	private JRadioButton rbCouleur;
	
	public PanelAction(FrameJoueur frmJ)
	{
		this.frmJ = frmJ;
		this.setPreferredSize(new Dimension (200,50));


		//creation
		this.cbChoix   = new ButtonGroup();

		this.rbAvatar  = new JRadioButton("Avatar", true );
		this.rbCouleur = new JRadioButton("Couleur",false );
		
		
		//positionnement
		ButtonGroup btgChoix = new ButtonGroup();

		btgChoix.add(this.rbAvatar );
		btgChoix.add(this.rbCouleur);

		this.add(this.rbAvatar );
		this.add(this.rbCouleur);
		

		//Activation
		this.rbAvatar .addItemListener(this);
		this.rbCouleur.addItemListener(this);
	}
	
	
	public void itemStateChanged(ItemEvent e)
	{
		if ( e.getSource() == this.rbAvatar )
		{
			frmJ.changerAvatar();
		}
		else
		{
			frmJ.changerCouleur();
		}		
	}
}
