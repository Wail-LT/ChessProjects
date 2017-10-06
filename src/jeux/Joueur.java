package jeux;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

import Project.Exception.CouleurIdentique;
import Project.Exception.PseudoManquant;


public class Joueur {

    private String pseudo;

    private static Couleur coulverif=null;//La couleur de celui à qui est le tour
    private static int nbj=0;

    private Couleur couleur;

    public Joueur(String pseudo, Couleur couleur)throws Exception {
    	if(coulverif==couleur || coulverif==null && nbj==1)
    	{
    		nbj=0;
    		coulverif=null;
    		throw new CouleurIdentique();
    	}
    	if (pseudo.isEmpty())
    	{
    		nbj=0;
    		coulverif=null;
    		throw new PseudoManquant();
    	}
    	this.pseudo=pseudo;
    	if (couleur==null && coulverif==Couleur.blanc)
    		couleur=Couleur.noir;
    	else if(couleur==null && coulverif==Couleur.noir)
    		couleur=Couleur.blanc;
    	this.couleur=couleur;
       	coulverif=couleur;
       	nbj++;
       	if (nbj==2)
       	{
       		coulverif=null;
       		nbj=0;
       	}
        	
    }

    public Couleur getCouleur()
    {
    	return couleur;
    }
    public String getPseudo()
    {
    	return pseudo;
    }
    public String toString() {
        return this.pseudo+" | "+couleur;
    }
    public String information()
    {
    	return pseudo+"\n"+couleur+"\n";
    }

}
