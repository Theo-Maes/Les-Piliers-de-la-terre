package equipe_26.IHM;

import     equipe_26.metier.Joueur;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Font;

public class PanelJoueur extends JPanel implements DocumentListener
{
    private Joueur     joueur;
    private JTextField txtPseudo;
    private JLabel     lblAvatar;
    private JLabel     lblCouleur;

    private JPanel    panelPseudo;
    
    public PanelJoueur(Joueur joueur)
    {
        this.joueur = joueur;
        this.joueur.reinitialiser();

        this.setPreferredSize(new Dimension (200,200));

        this.setLayout ( new BorderLayout() );

        //creation
        this.panelPseudo = new JPanel();

        this.panelPseudo.setLayout ( new GridLayout ( 2, 1) );

        this.txtPseudo  = new JTextField();
        this.lblAvatar  = new JLabel();
        this.lblCouleur = new JLabel();


        this.txtPseudo.setText ( this.joueur.getNom   () == null ? ""        : this.joueur.getNom   () );
        this.setAvatar         ( this.joueur.getAvatar() == null ? "equipe"  : this.joueur.getAvatar() );
        this.setCouleur        ( this.joueur.getCoul  () == null ? "gris"    : this.joueur.getCoul  () );

        this.txtPseudo.setHorizontalAlignment(JTextField.CENTER);


        //positionnement
        this.panelPseudo.add ( new JLabel( "Pseudo", JLabel.CENTER ) );
        this.panelPseudo.add ( this.txtPseudo );

        this.add ( this.panelPseudo, BorderLayout.NORTH  );
        this.add ( this.lblAvatar  , BorderLayout.WEST   );
        this.add ( this.lblCouleur , BorderLayout.EAST   );
        
        //activation
        this.txtPseudo.getDocument().addDocumentListener(this);
    }
        

    public void setAvatar(String nomAvatar)
    {
        this.lblAvatar.setIcon  ( new ImageIcon(new ImageIcon("ressource/avatar/" + nomAvatar + ".png").getImage().getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH))  );
        this.joueur   .setAvatar(nomAvatar);
    }
    

    public void setCouleur(String nomCouleur)
    {
        this.lblCouleur.setIcon (new ImageIcon(new ImageIcon("ressource/couleur/" + nomCouleur + ".png").getImage().getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH))  );
        this.joueur    .setCoul (nomCouleur);
    }


    public void insertUpdate(DocumentEvent e) { this.changedUpdate(e); }
    public void removeUpdate(DocumentEvent e) { this.changedUpdate(e); }

    public void changedUpdate(DocumentEvent e) 
    {
        this.joueur.setNom(String.format("%-12.12s", this.txtPseudo.getText().replace(" ", "_")));
    }
}
