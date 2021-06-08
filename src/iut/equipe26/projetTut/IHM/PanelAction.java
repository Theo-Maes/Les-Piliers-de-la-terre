package iut.equipe26.projetTut.IHM;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Dimension;

public class PanelAction extends JPanel implements ActionListener
{
	private FrameJoueur frmJ;
	
	private JRadioButton  rbAvatar;
	private JRadioButton rbCouleur;
	private JButton      btnSelect;
	
	public PanelAction(FrameJoueur frmJ)
	{
		this.frmJ = frmJ;
		this.setPreferredSize(new Dimension (200,50));
		//creation
		this.rbAvatar  = new JRadioButton("Avatar" , true );
		this.rbCouleur = new JRadioButton("Couleur", false);
		this.btnSelect = new JButton     ( "Selectionner" );
		
		//positionnement
		ButtonGroup btgChoix = new ButtonGroup();
		btgChoix.add(this.rbAvatar );
		btgChoix.add(this.rbCouleur);
		this.add(this.rbAvatar );
		this.add(this.rbCouleur);
		this.add(this.btnSelect);
		
		//Activation
		this.btnSelect.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(this.rbAvatar.isSelected() && !this.frmJ.getChoix())
			frmJ.changerAvatar();
		else
			if(this.rbCouleur.isSelected() && this.frmJ.getChoix())
				frmJ.changerCouleur();
	}
}
