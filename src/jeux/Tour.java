package jeux;
import java.util.ArrayList;

import javax.crypto.Cipher;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import Project.Exception.MauvaisePiece;


public class Tour extends Piece {

    public Tour(Case cases) {
        super(cases);
    }
    public Tour(Case cases,Couleur coul) {
        super(cases);
        couleur=coul;
    }

    public String afficher() {
        return "T"+couleur;
    }


    public String toString() {
        return "T";
    }

    public boolean verification (Case c2)
    {
    	return Tour.verification(cases, c2);
    }
    public static boolean verification(Case c1, Case c2) {
    	int i=0;
		if (c1.getColonne()==c2.getColonne()&&c2.getRange()>c1.getRange())
		{
			i=c1.getRange()+1;
			while(Echiquier.ech.gcase(i,c1.getColonne()).getPiece()==null && i<c2.getRange())
				i++;
		}
		if (c1.getColonne()==c2.getColonne()&&c2.getRange()<c1.getRange())
		{
			i=c1.getRange()-1;
			while(Echiquier.ech.gcase(i,c1.getColonne()).getPiece()==null && i>c2.getRange())
				i--;
		}
		if (c1.getColonne()>c2.getColonne()&&c2.getRange()==c1.getRange())
		{
			i=c1.getColonne()-1;
			while(Echiquier.ech.gcase(c1.getRange(),i).getPiece()==null && i>c2.getColonne())
				i--;
		}
		if (c1.getColonne()<c2.getColonne()&&c2.getRange()==c1.getRange())
		{
			i=c1.getColonne()+1;
			while(Echiquier.ech.gcase(c1.getRange(),i).getPiece()==null && i<c2.getColonne())
				i++;
		}
		
		
		if ((Echiquier.ech.gcase(i,c1.getColonne()).equals(c2) || Echiquier.ech.gcase(c1.getRange(),i).equals(c2) ) && (c2.getPiece()==null || c2.getPiece().couleur!=c1.getPiece().couleur))
		{
			
			return true;
		}
			
    	return false;
    	
    }


	public static ArrayList<Case> possibilite(Case c1){
		ArrayList<Case> tab =new ArrayList<Case>();
		for (int j=0;j<2;j++)
		{
			for (int i=0;i<8;i++)
			{
				if (j==0)
				{
					if (Tour.verification(c1, Echiquier.ech.gcase(i, c1.getColonne())))
						tab.add(Echiquier.ech.gcase(i, c1.getColonne()));
				}
				else
					if (Tour.verification(c1, Echiquier.ech.gcase(c1.getRange(),i)))
						tab.add(Echiquier.ech.gcase(c1.getRange(),i));	
			}
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
		return Tour.possibilite(cases);
	}
    


}
