package jeux;
import java.util.ArrayList;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import Project.Exception.MatException;
import Project.Exception.MauvaisePiece;
import Project.Exception.MouvementMauvais;
import Project.Exception.PatException;
//modifier
public abstract class Piece {

    protected Couleur couleur;

    protected boolean debut;


    protected Case cases;
    
    protected int i;

    public Piece(Case cases) { //intialiser les couleurs des pions si on est à la ligne 0 ou 1
        this.cases = cases;
        if (cases.getRange()==0 || cases.getRange()==1)
        {
            this.couleur=Couleur.blanc;
        }
        else
        {
            this.couleur=Couleur.noir;
        }
        debut=true;//premier mouvement 
    }
    public Piece(Couleur coul,boolean deb,Integer i)
    {
    	this.i=i;
    	couleur=coul;
    	this.debut=deb;
    }

    public void mouvement(Case arrive) throws Exception {
    	if(this.couleur!=Couleur.blanc && Arbitre.mat(Couleur.blanc))
    		throw new MatException("Blanc");
    	else if(this.couleur!=Couleur.noir && Arbitre.mat(Couleur.noir))
    		throw new MatException("Noir");
    	else if(Arbitre.pat())
    		throw new PatException();
    	else if(!verification(arrive) ||((!Arbitre.Echec(couleur) && Arbitre.mouvEchec(cases,arrive)) || (Arbitre.Echec(couleur) && !Arbitre.mouvProtect(cases, arrive))))
    	{
    		throw new MouvementMauvais();//message d'erreur mauvais mouvement, voir classe MouvaisMouvement
    	}
    	else{
    		if (arrive.getPiece()!=null)//si le pion est détruit on le détruit on l'enleve de l'echequier et on réarange les indexes i
        	{
        		if (arrive.getPiece().getCouleur()==Couleur.noir)
        		{
        			for (int i=arrive.getPiece().getI()+1;i<Echiquier.ech.noir.size();i++)
        				Echiquier.ech.noir.get(i).setI(i-1);
        			Echiquier.noir.remove(arrive.getPiece().getI());
        		}
        		else
        		{
        			for (int i=arrive.getPiece().getI()+1;i<Echiquier.ech.blanc.size();i++)
        				Echiquier.ech.blanc.get(i).setI(i-1);
        			Echiquier.blanc.remove(arrive.getPiece().getI());
        		}
        	}
            cases.setPiece(null);//la case ne contient plus le pion
            cases=arrive;//le pion pointe vers la case arriver
            cases.setPiece(this);//la case arriver contient le pion
            if (debut)
                debut=false;	//mouvement terminer
    	}	
    }
    public void mouvRoque(Case arrive)//mouvement pour la tour lors du roque
    {
    	 cases.setPiece(null);
         cases=arrive;
         cases.setPiece(this);
         if (debut)
             debut=false;	
    }


    public abstract ArrayList<Case> possibilite()throws MauvaisePiece;//abstract car depend de chaque pion
    public abstract String afficher();//abstract car depend de chaque pion
    public abstract boolean verification(Case c2);//abstract car depend de chaque pion

    public Couleur getCouleur() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.couleur;
    }


    void setCouleur(Couleur value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.couleur = value;
    }


    public abstract String toString();


    boolean isDebut() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.debut;
    }
    public Case getCase()
    {
    	return this.cases;
    }
    public void setI(int i)
    {
    	this.i= i;
    }
    public int getI()
    {
    	return i;
    }
    public String information()
    {
    	return this.toString()+"\n"+couleur+"\n"+debut+"\n"+i+"\n";
    }
    public void setDebut(Boolean a)
    {
    	debut=a;
    }

}
