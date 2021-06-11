package iut.equipe26.projetTut.IHM;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import iut.equipe26.projetTut.Controleur;
import iut.equipe26.projetTut.metier.Dalle;
import iut.equipe26.projetTut.metier.Pilier;

public class PanelPlateau extends JPanel implements MouseListener, MouseMotionListener {
	private static Image imgDalle = Toolkit.getDefaultToolkit().getImage("./src/ressource/images/Dalle.png");
	private static Color background = new Color( 128, 96, 0);

	private ArrayList<JButton> btnPlateau = new ArrayList<>();

	private int oldX = -1;
	private int oldY = -1;

	public PanelPlateau() {
		this.setLayout(null);

		if (Controleur.getInstance().getPlateau().getEnsDalles().size() < 1) {
			Controleur.getInstance().getPlateau().getEnsDalles().add(new Dalle(294,268));
			for (int i = 0; i+49 < 700; i += 49) {
				for (int j = 0; j+67 < 600; j += 67) {
					JButton btn = new JButton();
					btn.setName("btnDalle");
					btn.setOpaque(false);
					btn.setContentAreaFilled(false);
					btn.setFocusPainted(false);
					btn.setBorderPainted(false);
					btn.setEnabled(false);
					
					btn.setBounds(i, i%2 == 0?j:j+33, 49, 67);
					this.btnPlateau.add(btn);
					this.add(btn);
	
					btn.addMouseMotionListener(this);
					btn.addMouseListener(this);
				}
			}
		} else {
			this.addBtnPiliers();
		}
		this.repaint();
	}

	private void addBtnPiliers() {
		for (JButton btn : btnPlateau) {
			this.remove(btn);
			this.removeMouseListener(this);
			this.removeMouseMotionListener(this);
		}
		for (Dalle d : Controleur.getInstance().getPlateau().getEnsDalles()) {
			JButton btn0 = new JButton();
			btn0.setName(d.getNom() + "0");
			btn0.setOpaque(false);
			btn0.setContentAreaFilled(false);
			btn0.setFocusPainted(false);
			btn0.setBorderPainted(false);
			btn0.setEnabled(false);
			
			btn0.setBounds(d.getX()-33+16, d.getY()-16, 67-16, 33);
			this.btnPlateau.add(btn0);
			this.add(btn0);

			btn0.addMouseMotionListener(this);
			btn0.addMouseListener(this);

			JButton btn1 = new JButton();
			btn1.setName(d.getNom() + "1");
			btn1.setOpaque(false);
			btn1.setContentAreaFilled(false);
			btn1.setFocusPainted(false);
			btn1.setBorderPainted(false);
			btn1.setEnabled(false);
			
			btn1.setBounds(d.getX()+33, d.getY()-16, 67-16, 33);
			this.btnPlateau.add(btn1);
			this.add(btn1);

			btn1.addMouseMotionListener(this);
			btn1.addMouseListener(this);

			JButton btn2 = new JButton();
			btn2.setName(d.getNom() + "2");
			btn2.setOpaque(false);
			btn2.setContentAreaFilled(false);
			btn2.setFocusPainted(false);
			btn2.setBorderPainted(false);
			btn2.setEnabled(false);
			
			btn2.setBounds(d.getX()+33, d.getY()+16, 67-16, 33);
			this.btnPlateau.add(btn2);
			this.add(btn2);

			btn2.addMouseMotionListener(this);
			btn2.addMouseListener(this);

			JButton btn3 = new JButton();
			btn3.setName(d.getNom() + "3");
			btn3.setOpaque(false);
			btn3.setContentAreaFilled(false);
			btn3.setFocusPainted(false);
			btn3.setBorderPainted(false);
			btn3.setEnabled(false);
			
			btn3.setBounds(d.getX()+33, d.getY()+67-16, 67-16, 33);
			this.btnPlateau.add(btn3);
			this.add(btn3);

			btn3.addMouseMotionListener(this);
			btn3.addMouseListener(this);

			JButton btn4 = new JButton();
			btn4.setName(d.getNom() + "4");
			btn4.setOpaque(false);
			btn4.setContentAreaFilled(false);
			btn4.setFocusPainted(false);
			btn4.setBorderPainted(false);
			btn4.setEnabled(false);
			
			btn4.setBounds(d.getX()-33+16, d.getY()+67-16, 67-16, 33);
			this.btnPlateau.add(btn4);
			this.add(btn4);

			btn4.addMouseMotionListener(this);
			btn4.addMouseListener(this);

			JButton btn5 = new JButton();
			btn5.setName(d.getNom() + "5");
			btn5.setOpaque(false);
			btn5.setContentAreaFilled(false);
			btn5.setFocusPainted(false);
			btn5.setBorderPainted(false);
			btn5.setEnabled(false);
			
			btn5.setBounds(d.getX()-67/2+16, d.getY()+16, 67-16, 33);
			this.btnPlateau.add(btn5);
			this.add(btn5);

			btn5.addMouseMotionListener(this);
			btn5.addMouseListener(this);
		}
		this.repaint();
	}

	public void update (Graphics g) {this.paintComponent(g);}

	public void paintComponent (Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D)g;

		g2.setColor(PanelPlateau.background);
		// g2.fillRect(0, 0, 700, 600);

		for (Dalle d : Controleur.getInstance().getPlateau().getEnsDalles()) {
			// System.out.println(d.getX() + " | " + d.getY());
			g2.drawImage(PanelPlateau.imgDalle, d.getX(), d.getY(), this);
			g2.drawString(d.getNom() + "", d.getX()+30, d.getY()+38);

			if (d.getControle() == 'G') {
				g2.drawImage(Toolkit.getDefaultToolkit().getImage("./src/ressource/images/anneau_" + Controleur.getInstance().getJoueur1().getCoul() + ".png"), d.getX(), d.getY(), this);
			} else if (d.getControle() == 'M') {
				g2.drawImage(Toolkit.getDefaultToolkit().getImage("./src/ressource/images/anneau_" + Controleur.getInstance().getJoueur2().getCoul() + ".png"), d.getX(), d.getY(), this);
			}

			for (Pilier p : d.getPiliers())
				if (p != null) {
					if(p.getCoul() == 'G') {
						g2.drawImage(Toolkit.getDefaultToolkit().getImage("./src/ressource/images/pilier_" + Controleur.getInstance().getJoueur1().getCoul() + ".png"), p.getX()-13/2, p.getY()-13/2, this);
					} else {
						g2.drawImage(Toolkit.getDefaultToolkit().getImage("./src/ressource/images/pilier_" + Controleur.getInstance().getJoueur2().getCoul() + ".png"), p.getX()-13/2, p.getY()-13/2, this);
					}
				}
		}
		// DEBUG Pointeur center
		// g2.drawLine(700/2-10, 600/2, 700/2+10, 600/2);
		// g2.drawLine(700/2, 600/2-10, 700/2, 600/2+10);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// System.out.println(e.getComponent().getName());
		String btnName = e.getComponent().getName();
		
		int x = e.getComponent().getX();
		int y = e.getComponent().getY();
		
		boolean superpose = false;
		for (Dalle d : Controleur.getInstance().getPlateau().getEnsDalles())
			if (d.getX() == x && d.getY() == y) {
				superpose = true;
			}
		if (x != this.oldX || y != this.oldY || superpose)
			this.repaint();

		if ("btnDalle".equals(btnName)) {
			if (Controleur.getInstance().getPlateau().getEnsDalles().size() < 16) {
				for (Dalle d : Controleur.getInstance().getPlateau().getEnsDalles()) {
					if (((d.getX()    == x                    ) && (d.getY()-67 == y || d.getY()+67 == y)) ||
						((d.getX()+49 == x || d.getX()-49 == x) && (d.getY()-33 == y || d.getY()+33 == y)) ||
						((d.getX()+49 == x || d.getX()-49 == x) && (d.getY()-34 == y || d.getY()+34 == y))) {
							Graphics g = this.getGraphics();
							Graphics2D g2 = (Graphics2D)g;
							g2.drawImage(PanelPlateau.imgDalle, x, y, this);
					}
				}
			} else {
				this.addBtnPiliers();
			}
		} else {
			int numPilier = Integer.parseInt(btnName.charAt(1)+"");
			for (Dalle d : Controleur.getInstance().getPlateau().getEnsDalles()) {
				if (d.getNom() == btnName.charAt(0)) {
					if (d.getPiliers()[numPilier] == null && d.isConstructible(numPilier)) {
						Graphics g = this.getGraphics();
						Graphics2D g2 = (Graphics2D)g;
						if( Controleur.getInstance().getPlateau().getNbTour()%2 == 0) {
							g2.drawImage(Toolkit.getDefaultToolkit().getImage("./src/ressource/images/pilier_" + Controleur.getInstance().getJoueur1().getCoul() + ".png"), 
										d.getX()+33 + d.PILIER_X[numPilier] - 13/2, d.getY()+33 + d.PILIER_Y[numPilier] - 13/2, this);
						} else {
							g2.drawImage(Toolkit.getDefaultToolkit().getImage("./src/ressource/images/pilier_" + Controleur.getInstance().getJoueur2().getCoul() + ".png"), 
										d.getX()+33 + d.PILIER_X[numPilier] - 13/2, d.getY()+33 + d.PILIER_Y[numPilier] - 13/2, this);
						}
					}
				}
			}
		}
		this.oldX = x;
		this.oldY = y;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// System.out.println(e.getComponent().getName());
		String btnName = e.getComponent().getName();

		int x = e.getComponent().getX();
		int y = e.getComponent().getY();

		if ("btnDalle".equals(btnName)) {
			boolean superpose = false;
			boolean addDalle  = false;

			if (Controleur.getInstance().getPlateau().getEnsDalles().size() < 16) {
				int last = Controleur.getInstance().getPlateau().getEnsDalles().size();

				for (Dalle d : Controleur.getInstance().getPlateau().getEnsDalles())
					if (d.getX() == x && d.getY() == y) 
						superpose = true;

				if (!superpose) {
					for (Dalle d : Controleur.getInstance().getPlateau().getEnsDalles()) {
						if (((d.getX()    == x                    ) && (d.getY()-67 == y || d.getY()+67 == y)) ||
							((d.getX()+49 == x || d.getX()-49 == x) && (d.getY()-33 == y || d.getY()+33 == y)) ||
							((d.getX()+49 == x || d.getX()-49 == x) && (d.getY()-34 == y || d.getY()+34 == y))) 
								addDalle = true;
					}
					if (addDalle) {
						Controleur.getInstance().getPlateau().getEnsDalles().add(new Dalle(x, y));

						for (Dalle d : Controleur.getInstance().getPlateau().getEnsDalles()) {
							if (d.getX() == x && d.getY() == y-67) {
								Controleur.getInstance().getPlateau().getEnsDalles().get(last).ajouterVoisineCustom(0, d);
							} else if (d.getX() == x+49 && (d.getY() == y-33 || d.getY() == y-34)) {
								Controleur.getInstance().getPlateau().getEnsDalles().get(last).ajouterVoisineCustom(1, d);
							} else if (d.getX() == x+49 && (d.getY() == y+33 || d.getY() == y+34)) {
								Controleur.getInstance().getPlateau().getEnsDalles().get(last).ajouterVoisineCustom(2, d);
							} else if (d.getX() == x && d.getY() == y+67) {
								Controleur.getInstance().getPlateau().getEnsDalles().get(last).ajouterVoisineCustom(3, d);
							} else if (d.getX() == x-49 && (d.getY() == y+33 || d.getY() == y+34)) {
								Controleur.getInstance().getPlateau().getEnsDalles().get(last).ajouterVoisineCustom(4, d);
							} else if (d.getX() == x-49 && (d.getY() == y-33 || d.getY() == y-34)) {
								Controleur.getInstance().getPlateau().getEnsDalles().get(last).ajouterVoisineCustom(5, d);
							}
						}
					}
				}
				this.repaint();
			}
		} else {
			int numPilier = Integer.parseInt(btnName.charAt(1)+"");
			for (Dalle d : Controleur.getInstance().getPlateau().getEnsDalles())
				if (d.getNom() == btnName.charAt(0))
					if (d.isConstructible(numPilier))
						Controleur.getInstance().getPlateau().ajoutPilier(d, numPilier);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (!e.getComponent().getName().equals("btnDalle")) {
			this.repaint();
		}
	}
}
