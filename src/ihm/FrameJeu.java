package ihm;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FrameJeu extends JFrame 
{
	private BGPanel bgPanel;

	public FrameJeu() 
	{
		this.setSize(700, 600);

		this.bgPanel = new BGPanel();

		// JPanel test = new JPanel();
		// Image background = Toolkit.getDefaultToolkit().createImage("Background.png");
    	// test.drawImage(background, 0, 0, null);
		// test.drawImage(new ImageIcon("src/ressource/images/Background.jpg"), 0, 0, null);
		// JLabel jlTest = new JLabel(new ImageIcon("src/ressource/images/Background.jpg"));
		// test.add(jlTest);
		// JLabel jlTest2 = new JLabel(new ImageIcon("src/ressource/images/Dalle.png"));
		// jlTest2.setLocation(350, 300);
		// test.add(jlTest2);

		
		// this.add(test);
		this.add(bgPanel);
		this.majIHM();

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void majIHM() {
		this.bgPanel.repaint();
	}
}
