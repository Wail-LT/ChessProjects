package Affichage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import Project.Exception.CouleurIdentique;
import Project.Exception.PseudoManquant;
import jeux.Arbitre;
import jeux.Couleur;
import jeux.Joueur;

public class PanJoueur extends JPanelPerso{
	private JTextField pseudo1=new  JTextField(10);
	private JLabel pseudo=new JLabel("Pseudo ");
	private JTextField pseudo2=new  JTextField(10);
	private Bouton noir1;
	private Bouton noir2;
	private Bouton blanc1;
	private Bouton blanc2;
	private Bouton valider;
	private Couleur couleurJ1=null;
	private Couleur couleurJ2=null;
	private Joueur j1;
	private Joueur j2;
	private ImageIcon b=new ImageIcon("Icon/CBlanc.gif");
	private ImageIcon n=new ImageIcon("Icon/CNoir.gif");
	public PanJoueur() {
		
		super();
		setBackground(Color.white);
		JPanelPerso jc =new JPanelPerso(new GridLayout(1, 2));
		setLayout(new BorderLayout());
		setOpaque(false);
		
		//joueur 1
		
		JPanelPerso j1=new JPanelPerso(new GridLayout(2, 1));
		Border brd1=BorderFactory.createTitledBorder("Joueur 1");
		((TitledBorder) brd1).setTitleColor(Color.WHITE);
		j1.setBorder(brd1);
		//joueur 1.Pseudo
		
		JPanelPerso ps=new JPanelPerso(new FlowLayout());
		ps.add(pseudo);
		pseudo.setForeground(Color.white);
		ps.add(pseudo1);
		j1.add(ps);
		
		//joueur 1.couleur
		
		JPanelPerso cl=new JPanelPerso(new FlowLayout());
		JLabel coul1=new JLabel("Couleur :");
		coul1.setForeground(Color.white);
		noir1=new Bouton(n, new ChoixCouleur());
		blanc1=new Bouton(b, new ChoixCouleur());
		cl.add(coul1);
		cl.add(blanc1);
		cl.add(noir1);
		j1.add(cl);
		jc.add(j1);
		
		//joueur 2
		
		JPanelPerso j2=new JPanelPerso(new GridLayout(2, 1));
		Border brd2=BorderFactory.createTitledBorder("Joueur 2");
		((TitledBorder)brd2).setTitleColor(Color.WHITE);
		j2.setBorder(brd2);
		
		//joueur 2.Pseudo
		
		JPanelPerso ps2=new JPanelPerso(new FlowLayout());
		JLabel pseudobis=new JLabel("pseudo");
		ps2.add(pseudobis);
		pseudobis.setForeground(Color.white);
		ps2.add(pseudo2);
		j2.add(ps2);
		
		//joueur 2.couleur
		
		JLabel coul2=new JLabel("Couleur :");
		coul2.setForeground(Color.white);
		JPanelPerso cl2=new JPanelPerso(new FlowLayout());
		cl2.add(coul2);
		noir2=new Bouton(n, new ChoixCouleur());
		blanc2=new Bouton(b, new ChoixCouleur());
		cl2.add(blanc2);
		cl2.add(noir2);
		j2.add(cl2);
		jc.add(j2);
		JPanelPerso boxLValider=new JPanelPerso();
		valider=new Bouton("Valider",new ChoixCouleur());
		boxLValider.setLayout(new FlowLayout());
		boxLValider.add(valider);
		add(jc, BorderLayout.CENTER);
		add(boxLValider,BorderLayout.SOUTH);
		
		
		
		
	}
	public void paintComponent(Graphics g) 
	{ 
		super.paintComponent(g); 
		g.drawImage(new ImageIcon("data/bg.jpg").getImage(), 0, 0,  getWidth(), getHeight(),this); 
	} 
		
	class ChoixCouleur implements ActionListener
	{


		@SuppressWarnings("static-access")
		@Override
		public void actionPerformed(ActionEvent e) {	
		// TODO Auto-generated method stub
			if (e.getSource()==blanc1)
				couleurJ1=Couleur.blanc;
			else if(e.getSource()==blanc2)
				couleurJ2=Couleur.blanc;
			else if (e.getSource()==noir1)
				couleurJ1=Couleur.noir;
			else if(e.getSource()==noir2)
				couleurJ2=Couleur.noir;
			else if(e.getSource()==valider)
			{
				try{
					if (couleurJ1==null)
					{
						j2=new Joueur(pseudo2.getText(), couleurJ2);
						j1=new Joueur(pseudo1.getText(), couleurJ1);
					}
					else
					{
						j1=new Joueur(pseudo1.getText(), couleurJ1);
						j2=new Joueur(pseudo2.getText(), couleurJ2);
					}
					Arbitre.add(j1, j2);
					Fenetre.setPan(new PanJeux());
				}catch (Exception q){
					JOptionPane joperr=new JOptionPane();
					joperr.showMessageDialog(null, q.getMessage(), "Erreur",JOptionPane.ERROR_MESSAGE);
					
				}
				
			}
	
		}
	}
}
