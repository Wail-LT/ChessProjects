package Affichage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import Project.Exception.MauvaisePiece;
import Project.Exception.MouvementMauvais;
import SaveCharge.SaveCharge;
import jeux.*;


public class PanJeux extends JPanel{
	// panel + Bouton echequier
    @objid ("a3eec582-8181-4e73-bb57-6b03b1f58501")
    private JPanel prPan = new JPanel();
    @objid ("6ca4043d-a667-411f-85c0-f96967014342")
    private JPanel pan = new JPanel();
    private PanBouton listbut[]=new PanBouton[64];
    private Bouton Save;
    private Bouton Quitter;
    
    //bordure d'indice
    private JPanel Bouest=new JPanel(new GridLayout(8,1));
    private JPanel Best=new JPanel(new GridLayout(2,1));
    private JPanel Bsud=new JPanel(new GridLayout(1,8,-120,-800));
    
    // actionpermformed pour verification click Bouton
    private ActionEvent a = null;
    private JLabel etat= new JLabel("Etat :",JLabel.CENTER);
    Font fonterr = new Font("Arial",Font.BOLD, 14);
   
    
    public PanJeux() {
    	super();
        this.setLayout(new BorderLayout());
        
        
        // bordure nord bouton sauvegarde/quiter + bard d'etat
        // message d'erreur
    	etat.setFont(fonterr); 
    	etat.setPreferredSize(new Dimension(this.getWidth(), 20));
    	EtatJoueur();

    	this.add(etat,BorderLayout.NORTH);
        
        //bordure ouest avec les indice 1..8
        
        Bouest.setPreferredSize(new Dimension(40, pan.getHeight()));
        String a;
        for(int i=1;i<9;i++)
        {
        	a=new String("	");
        	a+=i;
        	Bouest.add(new JLabel(a,JLabel.CENTER));
        }
        this.add(Bouest,BorderLayout.WEST);
        
        //bordure est
    	
        Save =new Bouton("Sauvegarder",new SaveQuitter());
    	Quitter=new Bouton("Quitter",new SaveQuitter());
    	Save.setPreferredSize(new Dimension(120, 40));
    	Quitter.setPreferredSize(new Dimension(120, 40));
    	Best.add(Save);
    	Best.add(Quitter);
    	this.add(Best,BorderLayout.EAST);
        //bordure sud  indice A..g
    
        Bsud.setPreferredSize(new Dimension(pan.getWidth()-160, 40));
        for(int i=0;i<8;i++)
        {
        	a=new String("");
        	a+=Echiquier.lettre[i];
        	Bsud.add(new JLabel(a,JLabel.CENTER));
        }
        this.add(Bsud,BorderLayout.SOUTH);
        
        //JPanel echequier
        pan.setLayout(new GridLayout(8, 8));
        for (int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                if (i%2!=j%2)
                {
                    if (Echiquier.ech.gcase(i, j).getPiece()!=null)
                        pan.add(listbut[i*8+j]=new PanBouton(new ImageIcon("Icon/"+Echiquier.ech.gcase(i, j).getPiece().afficher()+".gif"),i,j,new ActionMouvement(),true));
                    else 
                        pan.add(listbut[i*8+j]=new PanBouton(new ImageIcon(""),i,j,new ActionMouvement(),true));
                }    
                else
                {
                    if (Echiquier.ech.gcase(i, j).getPiece()!=null)
                        pan.add(listbut[i*8+j]=new PanBouton(new ImageIcon ("Icon/"+Echiquier.ech.gcase(i, j).getPiece().afficher()+".gif"),i,j,new ActionMouvement(),false));
                    else 
                        pan.add(listbut[i*8+j]=new PanBouton(new ImageIcon(""),i,j,new ActionMouvement(),false));
                }
            }
        }
        this.add(pan,BorderLayout.CENTER);
      

    }
    public void EtatJoueur()
    {
    	if(Arbitre.coulTour==Couleur.blanc)
    		etat.setText("Etat : c'est à "+Arbitre.getPseudo(Couleur.blanc)+" de jouer");
    	else 
    		etat.setText("Etat : c'est à "+Arbitre.getPseudo(Couleur.noir)+" de jouer");
    }
    class SaveQuitter implements ActionListener
    {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==Save)
				SaveCharge.save();
			else
			{
				Fenetre.getJeuxEchec().setContentPane(Fenetre.getJeuxEchec().getWinPan());
	            SwingUtilities.updateComponentTreeUI(Fenetre.getJeuxEchec());
			}
			
		}
    	
    }
    class ActionMouvement implements ActionListener {
    	public void deplacementRoque(Bouton dep,Bouton arr,Bouton tDep,Bouton tArr)
    	{
    		try {
				dep.getCases().getPiece().mouvement(arr.getCases());
			} catch (Exception e) {
			}
        	dep.setIcon(new ImageIcon(""));
        	arr.setIcon(new ImageIcon("Icon/"+arr.getCases().getPiece().afficher()+".gif"));
        	tDep.getCases().getPiece().mouvRoque(tArr.getCases());
        	tDep.setIcon(new ImageIcon(""));
        	tArr.setIcon(new ImageIcon("Icon/"+tArr.getCases().getPiece().afficher()+".gif"));
    	}
    	public String[] promoPoss(Couleur coul)
    	{
    		ArrayList<String> promotion =new ArrayList<String>();
    		int nbTour=0,nbFou=0,nbDame=0,nbCavalier=0,i=0;
    		if (coul==Couleur.blanc)
    		{
    			while(i<Echiquier.blanc.size() &&( nbTour<2 || nbFou<2 || nbCavalier<2 || nbDame<1))
    			{
    				if (Echiquier.blanc.get(i) instanceof Tour &&  nbTour<2)
    					nbTour++;
    				else if (Echiquier.blanc.get(i) instanceof Fou &&  nbFou<2)
    					nbFou++;
    				else if (Echiquier.blanc.get(i) instanceof Dame &&  nbDame<1)
    					nbDame++;
    				else if (Echiquier.blanc.get(i) instanceof Cavalier &&  nbCavalier<2)
    					nbCavalier++;
    				i++;
    			}
    		}
    		else
    		{
    			while(i<Echiquier.noir.size() &&( nbTour<2 || nbFou<2 || nbCavalier<2 || nbDame<1))
    			{
    				if (Echiquier.noir.get(i) instanceof Tour &&  nbTour<2)
    					nbTour++;
    				else if (Echiquier.noir.get(i) instanceof Fou &&  nbFou<2)
    					nbFou++;
    				else if (Echiquier.noir.get(i) instanceof Dame &&  nbDame<1)
    					nbDame++;
    				else if (Echiquier.noir.get(i) instanceof Cavalier &&  nbCavalier<2)
    					nbCavalier++;
    				i++;
    			}
    		}
    		if (nbTour<2)
    			promotion.add("Tour");
    		if (nbFou<2)
    			promotion.add("Fou");	
    		if (nbCavalier<2)
    			promotion.add("Cavalier");
    		if (nbDame<1)
    			promotion.add("Dame");
    		String[]promo=new String[promotion.size()];
    		promotion.toArray(promo);
    		return promo;
    	}
    	public void ressusciter(Piece pion,String choix)
    	{
			Case casep=pion.getCase();
			int i=pion.getI();
    		if (pion.getCouleur()==Couleur.blanc)
    		{
    			switch (choix) {
				case "Tour":
					Echiquier.blanc.set(i, new Tour(casep,pion.getCouleur()));
					Echiquier.blanc.get(i).setI(i);
					casep.setPiece(Echiquier.blanc.get(i));
					
					break;
				case"Dame":
					Echiquier.blanc.set(i, new Dame(casep,pion.getCouleur()));
					Echiquier.blanc.get(i).setI(i);
					casep.setPiece(Echiquier.blanc.get(i));
					break;
				case"Fou":
					Echiquier.blanc.set(i, new Fou(casep,pion.getCouleur()));
					Echiquier.blanc.get(i).setI(i);
					casep.setPiece(Echiquier.blanc.get(i));
					break;
				case"Cavalier":
					Echiquier.blanc.set(i, new Fou(casep,pion.getCouleur()));
					Echiquier.blanc.get(i).setI(i);
					casep.setPiece(Echiquier.blanc.get(i));
					break;
				}
    		}
    		else
    		{
    			switch (choix) {
				case "Tour":
					Echiquier.noir.set(i, new Tour(casep,pion.getCouleur()));
					Echiquier.noir.get(i).setI(i);
					casep.setPiece(Echiquier.noir.get(i));
					
					break;
				case"Dame":
					Echiquier.noir.set(i, new Dame(casep,pion.getCouleur()));
					Echiquier.noir.get(i).setI(i);
					casep.setPiece(Echiquier.noir.get(i));
					break;
				case"Fou":
					Echiquier.noir.set(i, new Fou(casep,pion.getCouleur()));
					Echiquier.noir.get(i).setI(i);
					casep.setPiece(Echiquier.noir.get(i));
					break;
				case"Cavalier":
					Echiquier.noir.set(i, new Fou(casep,pion.getCouleur()));
					Echiquier.noir.get(i).setI(i);
					casep.setPiece(Echiquier.noir.get(i));
					break;
				}
    		}
    		
    	}
        @objid ("3cfb35d3-8854-49c2-94e0-f99228a27834")
        public void actionPerformed(ActionEvent e)
        {
        	//possibilite
        	try
        	{
        		if(a==null && ((Bouton)e.getSource()).getCases().getPiece()!=null && !((Bouton)e.getSource()).getCases().getPiece().possibilite().isEmpty())
        		{
        			a=e; 
        			for(Case q:((Bouton)e.getSource()).getCases().getPiece().possibilite())
        			{
        				if (q!=null)
        					listbut[(q.getRange())*8+q.getColonne()].setBackground(Color.green);
        			}
        			if(((Bouton)e.getSource()).getCases().getPiece() instanceof Roi && !((Roi)((Bouton)a.getSource()).getCases().getPiece()).roquePoss().isEmpty())
        			{
        				for(Case k:((Roi)((Bouton)a.getSource()).getCases().getPiece()).roquePoss())
        				{
        					if (k!=null)
        						listbut[(k.getRange())*8+k.getColonne()].setBackground(Color.red);
        				}
        			}
        		}
        		else if (a!=null)
        		{
        			//mouvement annule
            	
        			if (a.getSource()==e.getSource())
        			{
        				for(Case q:((Bouton)e.getSource()).getCases().getPiece().possibilite())
        				{
        					if (q!=null)
       							listbut[(q.getRange())*8+q.getColonne()].setBackground(listbut[(q.getRange())*8+q.getColonne()].getBgcolor());
       					}
					
        				if(((Bouton)e.getSource()).getCases().getPiece() instanceof Roi &&  !((Roi)((Bouton)a.getSource()).getCases().getPiece()).roquePoss().isEmpty())
        				{
        					for(Case k:((Roi)((Bouton)e.getSource()).getCases().getPiece()).roquePoss())
        					{
        						if (k!=null)
        							listbut[(k.getRange())*8+k.getColonne()].setBackground(listbut[(k.getRange())*8+k.getColonne()].getBgcolor());
        					}
        				}
                    	
        				a=null;
        			}
        			else 
        			{
        				for(Case q:((Bouton)a.getSource()).getCases().getPiece().possibilite())
        					listbut[(q.getRange())*8+q.getColonne()].setBackground(listbut[(q.getRange())*8+q.getColonne()].getBgcolor());
                	
                	// deplacement roque
                	
        				if(((Bouton)a.getSource()).getCases().getPiece() instanceof Roi && !((Roi)((Bouton)a.getSource()).getCases().getPiece()).roquePoss().isEmpty())
        				{
        					for(Case k:((Roi)((Bouton)a.getSource()).getCases().getPiece()).roquePoss())
        					{
        						if (k!=null)
        							listbut[(k.getRange())*8+k.getColonne()].setBackground(listbut[(k.getRange())*8+k.getColonne()].getBgcolor());
        					}
        					for(Case i:((Roi)((Bouton)a.getSource()).getCases().getPiece()).roquePoss())
        					{
        						if(i.equals(((Bouton)e.getSource()).getCases()))
        						{
        							if (((Bouton)a.getSource()).getCases().getPiece().getCouleur()==Couleur.blanc && ((Bouton)e.getSource()).getCases().getColonne()<((Bouton)a.getSource()).getCases().getColonne())  
        								deplacementRoque(((Bouton)a.getSource()), ((Bouton)e.getSource()), listbut[0].getBut(),listbut[i.getRange()*8+i.getColonne()+1].getBut());
        							else if (((Bouton)a.getSource()).getCases().getPiece().getCouleur()==Couleur.noir && ((Bouton)e.getSource()).getCases().getColonne()>((Bouton)a.getSource()).getCases().getColonne())  
        								deplacementRoque(((Bouton)a.getSource()), ((Bouton)e.getSource()), listbut[listbut.length-1].getBut(),listbut[i.getRange()*8+i.getColonne()-1].getBut());
        							else if (((Bouton)a.getSource()).getCases().getPiece().getCouleur()==Couleur.blanc && ((Bouton)e.getSource()).getCases().getColonne()>((Bouton)a.getSource()).getCases().getColonne())
        							{
        								int j=2;
        								while(j<Echiquier.blanc.size() && !(Echiquier.blanc.get(j) instanceof Tour))
        									j++;
        								deplacementRoque(((Bouton)a.getSource()), ((Bouton)e.getSource()), listbut[Echiquier.blanc.get(j).getCase().getRange()*8+Echiquier.blanc.get(j).getCase().getColonne()].getBut(),listbut[i.getRange()*8+i.getColonne()-1].getBut());
                    					
        							}
        							else if (((Bouton)a.getSource()).getCases().getPiece().getCouleur()==Couleur.noir && ((Bouton)e.getSource()).getCases().getColonne()<((Bouton)a.getSource()).getCases().getColonne())
        							{
        								int j=1;
        								while(j<Echiquier.noir.size()-1 && !(Echiquier.noir.get(j) instanceof Tour))
        									j++;
        								deplacementRoque(((Bouton)a.getSource()), ((Bouton)e.getSource()), listbut[Echiquier.noir.get(j).getCase().getRange()*8+Echiquier.noir.get(j).getCase().getColonne()].getBut(),listbut[i.getRange()*8+i.getColonne()+1].getBut());		
        							}
        						}
        					}
        				}
        				else
        				{
        					// deplacement normale
        					try
        					{
        						((Bouton)a.getSource()).getCases().getPiece().mouvement(((Bouton)e.getSource()).getCases());
        						((Bouton)a.getSource()).setIcon(new ImageIcon(""));
        						((Bouton)e.getSource()).setIcon(new ImageIcon("Icon/"+((Bouton)e.getSource()).getCases().getPiece().afficher()+".gif"));
        						if (((Bouton)e.getSource()).getCases().getPiece() instanceof Pion && ((Pion)((Bouton)e.getSource()).getCases().getPiece()).promotion())
        						{	JOptionPane choixPromoPane=new JOptionPane();
        							String[] promoposs=promoPoss(((Bouton)e.getSource()).getCases().getPiece().getCouleur());
        							String choixPromo=(String)choixPromoPane.showInputDialog(null,"Promotion du Pion "+((Bouton)e.getSource()).getCases().getPiece().getCouleur(),"choisissez la piece à ressusciter ", JOptionPane.QUESTION_MESSAGE, null, promoposs, promoposs[0]);
        							ressusciter(((Bouton)e.getSource()).getCases().getPiece(), choixPromo);
        							((Bouton)e.getSource()).setIcon(new ImageIcon("Icon/"+((Bouton)e.getSource()).getCases().getPiece().afficher()+".gif"));
        						}
        						Arbitre.TourSuivant(((Bouton)e.getSource()).getCases().getPiece().getCouleur());
        						EtatJoueur();
        					}catch(Exception m)
        					{
        						etat.setText("Etat : "+ m.getMessage());                    	
        					}
        				} 
        				a=null;
        				}
        			}
            
        		}catch (MauvaisePiece e2)
        		{
        			etat.setText("Etat : "+ e2.getMessage());                    	
        		}
		}
    }

}

	  

    
