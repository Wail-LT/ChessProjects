package Affichage;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import SaveCharge.SaveCharge;
import jeux.Arbitre;
import jeux.Couleur;
import jeux.Echiquier;

public class PanCN extends JPanelPerso{
	private Bouton nPartie;
	private Bouton charge;
	private GridBagLayout layout=new GridBagLayout();
	private GridBagConstraints gbc=new GridBagConstraints();
	
	public PanCN(){
		gbc.gridheight=1;
		gbc.gridwidth=1;
		setLayout(layout);
		nPartie=new Bouton("Nouvelle partie",new Charge());
		gbc.gridx=1;
		charge=new Bouton("charge",new Charge());
		add(nPartie);
		add(charge);
	}
	
	
	public void paintComponent(Graphics g) 
	{ 
		super.paintComponent(g); 
		g.drawImage(new ImageIcon("data/bg.jpg").getImage(), 0, 0,  getWidth(), getHeight(),this); 
	} 
		
	class Charge implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource()==nPartie)
			{
				Echiquier.ech=new Echiquier();
				Arbitre.coulTour=Couleur.blanc;
				Fenetre.getJeuxEchec().setContentPane(new PanJoueur());
	            SwingUtilities.updateComponentTreeUI(Fenetre.getJeuxEchec());
			}
			else
			{
				SaveCharge.charger();
				Fenetre.getJeuxEchec().setContentPane(new PanJeux());
	            SwingUtilities.updateComponentTreeUI(Fenetre.getJeuxEchec());
			}
		}
	}
}
	
		