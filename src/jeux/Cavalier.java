package jeux;

import java.util.ArrayList;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import Project.Exception.MauvaisePiece;

@objid ("2f8e5193-db52-49b8-b854-3aee0f9c06fc")
public class Cavalier extends Piece {
    @objid ("6cb4ee11-5d5b-4d4b-8895-1671484547a3")
    public Cavalier(Case cases) {
        super(cases);
    }
    public Cavalier(Case cases,Couleur coul) {
        super(cases);
        setCouleur(coul);
    }

    @objid ("7bcdaba6-bf52-46f0-aa0d-1078eac1a042")
    public String afficher() {
        return "C"+couleur;
    }

    @objid ("58d41483-ec6e-44ab-b01a-4b3919c8c26b")
    public String toString() {
        return "C";
    }

    @objid ("87e660e5-1cf1-430b-ac27-2afb0c8d6030")
    public boolean verification(Case c2) {
        if (c2!=null &&(((c2.getRange()==cases.getRange()+2 || c2.getRange()==cases.getRange()-2) && (c2.getColonne()==cases.getColonne()+1 || c2.getColonne()==cases.getColonne()-1)) || ((c2.getRange()==cases.getRange()+1 || c2.getRange()==cases.getRange()-1) && (c2.getColonne()==cases.getColonne()+2 || c2.getColonne()==cases.getColonne()-2)))&& (c2.getPiece()==null || getCouleur()!=c2.getPiece().getCouleur()))
            return true;
        return false;
    }

    @objid ("e4b8fb63-6b5e-4ce5-9823-9cbb899bad56")
    public ArrayList<Case> possibilite()throws MauvaisePiece {
    	if(!Arbitre.Tour(this))
    	{
    		throw new MauvaisePiece();
    	}
        int i=cases.getRange();
        int j=cases.getColonne();
        ArrayList<Case>tab=new ArrayList<Case>();
        if (verification(Echiquier.ech.gcase(i+2,j+1)))
        {
        		tab.add(Echiquier.ech.gcase(i+2,j+1));
                
        }
        if (verification(Echiquier.ech.gcase(i+2,j-1)))
        {
        		tab.add(Echiquier.ech.gcase(i+2,j-1));
        		            
        }
        if (verification(Echiquier.ech.gcase(i-2,j-1)))
        {
        		tab.add(Echiquier.ech.gcase(i-2,j-1));
        		            
        }
        if (verification(Echiquier.ech.gcase(i-2,j+1)))
        {
        		 tab.add(Echiquier.ech.gcase(i-2,j+1));
        		 
        }
        if (verification(Echiquier.ech.gcase(i+1,j-2)))
        {
        		tab.add(Echiquier.ech.gcase(i+1,j-2));
        		
        }
        if (verification(Echiquier.ech.gcase(i+1,j+2)))
        {
        		tab.add(Echiquier.ech.gcase(i+1,j+2));
        		
        }
        if (verification(Echiquier.ech.gcase(i-1,j-2)))
        {
        		 tab.add(Echiquier.ech.gcase(i-1,j-2));
        		 
        }
        if (verification(Echiquier.ech.gcase(i-1,j+2)))
        {
        		tab.add(Echiquier.ech.gcase(i-1,j+2));
        		
        }
        return tab;
    }

}
