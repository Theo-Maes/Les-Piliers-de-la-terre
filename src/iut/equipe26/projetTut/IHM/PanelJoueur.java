package iut.equipe26.projetTut.IHM;

import iut.equipe26.projetTut.metier.Joueur;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.Image;
import java.awt.Dimension;

public class PanelJoueur extends JPanel implements ActionListener,FocusListener
{
	private Joueur j;
	private JTextField txtPseudo;
	private JLabel     lblAvatar;
	private JLabel    lblCouleur;
	
	public PanelJoueur(Joueur j)
	{
		this.j = j;
		this.setPreferredSize(new Dimension (200,200));
		//creation
		this.txtPseudo  = new JTextField("<Pseudo>");
		this.lblAvatar  = new JLabel();
		this.setAvatar("iut");
		this.lblCouleur = new JLabel();
		this.setCouleur("gris");
		//positionnement
		this.add(this.txtPseudo, BorderLayout.NORTH);
		this.add(this.lblAvatar,  BorderLayout.WEST);
		this.add(this.lblCouleur, BorderLayout.EAST);
		
		//activation
		
		this.txtPseudo.addActionListener(this);
		txtPseudo.addFocusListener(this); 

	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		this.j.setNom(this.txtPseudo.getText());
	}
	
	public void setAvatar(String avat)
	{
		
		ImageIcon avatar = new ImageIcon("./src/ressource/avatar/" + avat + ".png");
		Image image = avatar.getImage();
		Image newimg = image.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH); 
		avatar = new ImageIcon(newimg);
		this.lblAvatar.setIcon(avatar);
	}
	
	public void setCouleur(String coul)
	{
		ImageIcon couleur = new ImageIcon("./src/ressource/couleur/" + coul + ".png");
		Image image = couleur.getImage();
		Image newimg = image.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH); 
		couleur = new ImageIcon(newimg);
		this.lblCouleur.setIcon(couleur);
	}
	@Override
	public void focusLost(FocusEvent e){}
	@Override
	public void focusGained(FocusEvent e) 
	{
		txtPseudo.setText("");
	}
}

