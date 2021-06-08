package ihm;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Image;

import javax.swing.JPanel;

public class BGPanel extends JPanel 
{
	private Image background;
	public BGPanel() 
	{
		this.background = Toolkit.getDefaultToolkit().getImage("./src/ressource/images/Background.jpg");
	}

	public void paint (Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(this.background, 0, 0, this);
	}
}
