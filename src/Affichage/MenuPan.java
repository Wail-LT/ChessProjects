package Affichage;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MenuPan extends JPanel {
	private GridBagConstraints gbc = new GridBagConstraints();
	public MenuPan()
	{
		 setLayout(new GridBagLayout());
		 gbc.gridheight=1;
		 gbc.gridwidth=1;
	}
	public void paintComponent(Graphics g) 
	{ 
	super.paintComponent(g); 
	g.drawImage(new ImageIcon("data/bg.jpg").getImage(), 0, 0,  getWidth(), getHeight(),this); 
	} 
	public GridBagConstraints getGbc()
	{
		return gbc;
	}
}
