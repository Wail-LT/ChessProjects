package jeux;
import java.util.ArrayList;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import Project.Exception.MauvaisePiece;


public class Fou extends Piece {

    public Fou(Case cases) {
        super(cases);
    }
    public Fou(Case cases,Couleur coul) {
        super(cases);
        couleur=coul;
    }

    public String afficher() {
        return "F"+couleur;
    }


    public String toString() {
        return "F";
    }

	@Override
	public boolean verification(Case c2) {
		return Fou.verification(cases, c2);
	}
    public static boolean verification(Case c1, Case c2) {
    	try
    	{
    		int i=c2.getColonne()-c1.getColonne();int j=c2.getRange()-c1.getRange();
    	        // verifie si les coordon�es saisie sont testable 
    	        if (Math.abs(i)!=Math.abs(j))
    	        {
    	            return false;
    	        }
    	        i=c1.getRange();j=c1.getColonne();
    	        // verifie de des piece dans la trajectoire vers le haut droit
    	        if (c2.getColonne()>c1.getColonne() && c2.getRange()>c1.getRange())
    	        {
    	            i++;j++;
    	            while(i<c2.getRange() && Echiquier.ech.gcase(i, j).getPiece()==null)
    	            {
    	                i++;j++;
    	            }
    	        }
    	        // vers le bas gauche
    	        else if (c2.getColonne()<c1.getColonne() && c2.getRange()<c1.getRange())
    	        {
    	            i--;j--;
    	            while(i>c2.getRange() && Echiquier.ech.gcase(i, j).getPiece()==null)
    	            {
    	                i--;j--;
    	            }
    	        }
    	        // haut gauche
    	        else if (c2.getColonne()>c1.getColonne() && c2.getRange()<c1.getRange())
    	        {
    	            i--;j++;
    	            while(i>c2.getRange() && Echiquier.ech.gcase(i, j).getPiece()==null)
    	            {
    	                i--;j++;
    	            }
    	        }
    	        // bas droit
    	        else if (c2.getColonne()<c1.getColonne() && c2.getRange()>c1.getRange())
    	        {
    	            i++;j--;
    	            while(i<c2.getRange() && Echiquier.ech.gcase(i, j).getPiece()==null)
    	            {
    	                i++;j--;
    	            }
    	        }
    	        // verification du mouvement
    	        if(Echiquier.ech.gcase(i, j).equals(c2)&&(c2.getPiece()==null || c2.getPiece().getCouleur()!=c1.getPiece().getCouleur()))
    	        {
    	            return true;
    	        }	
    	}catch(NullPointerException a)
    	{
    		return false;
    	}
       
        return false;
    }
    public static ArrayList<Case> possibilite(Case c1){
    	ArrayList<Case> tab =new ArrayList<Case>();
		int i=c1.getRange()+1;
		int k=c1.getRange()-1;
		
		//vrs la droite haut et as
		for (int j=c1.getColonne()+1;j<8;j++)
		{
			if (Fou.verification(c1, Echiquier.ech.gcase(i,j)))
				tab.add(Echiquier.ech.gcase(i,j));	
			if (Fou.verification(c1, Echiquier.ech.gcase(k,j)))
				tab.add(Echiquier.ech.gcase(k,j));
			k--;
			i++;
		}
		// haut droite et gauche
		i=c1.getRange()+1;
		k=c1.getRange()-1;
		
		for (int j=c1.getColonne()-1;j>=0;j--)
		{
			if (Fou.verification(c1, Echiquier.ech.gcase(i,j)))
				tab.add(Echiquier.ech.gcase(i,j));	
			if (Fou.verification(c1, Echiquier.ech.gcase(k,j)))
				tab.add(Echiquier.ech.gcase(k,j));
			k--;
			i++;
		}
				
		return tab;
	}


	@Override
	public ArrayList<Case> possibilite()throws MauvaisePiece
    {
    	if(!Arbitre.Tour(this))
    	{
    		throw new MauvaisePiece();
    	}
		// TODO Auto-generated method stub
		return Fou.possibilite(cases);
	}


}
