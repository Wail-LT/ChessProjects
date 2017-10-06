package jeux;
import java.awt.List;
import java.util.ArrayList;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import Project.Exception.MauvaisePiece;
import Project.Exception.MouvementMauvais;
// ne pas oublier de cree la methode transfora

public class Pion extends Piece {

    public Pion(Case cases) {
        super(cases);
    }
    public Pion(Case cases,Couleur coul) {
        super(cases);
        couleur=coul;
    }

    public String afficher() {
       return "P"+couleur;
    }

    public String toString() {
        return "P";
    }


    public boolean verification(Case c2) {
    	try{
    		if(couleur==Couleur.blanc)
    		{
    			if ((c2.getRange()==cases.getRange()+1 && c2.getColonne()==cases.getColonne() && c2.getPiece()==null) || (c2.getRange()==cases.getRange()+1 && (c2.getColonne()==cases.getColonne()+1 || c2.getColonne()==cases.getColonne()-1)&& c2.getPiece()!=null && (c2.getPiece().getCouleur() != Couleur.blanc)))
    				return true;

    			if (debut && c2.getRange()==cases.getRange()+2 && c2.getColonne()==cases.getColonne() && Echiquier.ech.gcase(cases.getRange()+1, cases.getColonne()).getPiece()==null && Echiquier.ech.gcase(cases.getRange()+2, cases.getColonne()).getPiece()==null  )
    				return true;
    		}
    		else
    		{
    			if ((c2.getRange()==cases.getRange()-1 && c2.getColonne()==cases.getColonne() && c2.getPiece()==null) || (c2.getRange()==cases.getRange()-1 && (c2.getColonne()==cases.getColonne()+1 || c2.getColonne()==cases.getColonne()-1)&& c2.getPiece()!=null && (c2.getPiece().getCouleur() != Couleur.noir)))
    				return true;
    			if (cases.getPiece().isDebut() && c2.getRange()==cases.getRange()-2 && c2.getColonne()==cases.getColonne() && Echiquier.ech.gcase(cases.getRange()-1, cases.getColonne()).getPiece()==null && Echiquier.ech.gcase(cases.getRange()-2, cases.getColonne()).getPiece()==null)
    				return true;
    		}
    	}catch(NullPointerException e){}
    	
        return false;
    }
    public ArrayList<Case> possibilite()throws MauvaisePiece//toutes les possibilités du pion
    {
    	if(!Arbitre.Tour(this))
    	{
    		throw new MauvaisePiece();
    	}
    	ArrayList<Case>tab=new ArrayList<Case>();
    	if (cases.getPiece().couleur==Couleur.blanc)
       	{
       		if (verification(Echiquier.ech.gcase(cases.getRange()+1,cases.getColonne())))
           		tab.add(Echiquier.ech.gcase(cases.getRange()+1, cases.getColonne()));
           	if (cases.getPiece().debut && verification(Echiquier.ech.gcase(cases.getRange()+2,cases.getColonne())))
           		tab.add(Echiquier.ech.gcase(cases.getRange()+2, cases.getColonne()));
           	if ((verification( Echiquier.ech.gcase(cases.getRange()+1,cases.getColonne()+1)))&& cases.getColonne()!=7 )
           		tab.add(Echiquier.ech.gcase(cases.getRange()+1, cases.getColonne()+1));          		
            if ((verification( Echiquier.ech.gcase(cases.getRange()+1,cases.getColonne()-1)))&& cases.getColonne()!=0 )
           		tab.add(Echiquier.ech.gcase(cases.getRange()+1, cases.getColonne()-1));    		
       	}
       	else
       	{
       		if (verification(Echiquier.ech.gcase(cases.getRange()-1,cases.getColonne())) )
           		tab.add(Echiquier.ech.gcase(cases.getRange()-1, cases.getColonne()));
           	if (verification( Echiquier.ech.gcase(cases.getRange()-2,cases.getColonne())))
           		tab.add(Echiquier.ech.gcase(cases.getRange()-2, cases.getColonne())); 		
           	if ((verification( Echiquier.ech.gcase(cases.getRange()-1,cases.getColonne()+1)))&& cases.getColonne()!=7)
           		tab.add(Echiquier.ech.gcase(cases.getRange()-1, cases.getColonne()+1));
           	if ((verification( Echiquier.ech.gcase(cases.getRange()-1,cases.getColonne()-1)))&& cases.getColonne()!=0)
           		tab.add(Echiquier.ech.gcase(cases.getRange()-1, cases.getColonne()-1));
       	}
    	
		return tab;
    }
    public boolean attVerification(Case c2)//si les possibilités sont faisable ou pas
    {
    	if(couleur==Couleur.blanc)
    	{
    		if ((c2.getRange()==cases.getRange()+1 && ((c2.getColonne()==cases.getColonne()+1 && this.getCase().getColonne()!=7) ||( c2.getColonne()==cases.getColonne()-1 && this.getCase().getColonne()!=0)))&& (c2.getPiece()==null || c2.getPiece().getCouleur()!=couleur))
           		return true;       		
    	}
    	else
       	{
    		if ((c2.getRange()==cases.getRange()-1 && ((c2.getColonne()==cases.getColonne()+1 && this.getCase().getColonne()!=7) ||( c2.getColonne()==cases.getColonne()-1 && this.getCase().getColonne()!=0)))&& (c2.getPiece()==null || c2.getPiece().getCouleur()!=couleur))
    			return true; 
       	}
    	return false;
    }
    public  boolean promotion()//rescusiter une piece morte
    {
    	if (couleur==Couleur.blanc)
    		return cases.getRange()==7;
    	else 
    		return cases.getRange()==0;
    }

}
