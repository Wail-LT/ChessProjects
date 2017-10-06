package jeux;

public class Arbitre{

	public static Joueur[] listjoueur=new Joueur[2];
	public static Couleur coulTour=Couleur.blanc;//blanc
	public static boolean Tour(Piece a)
	{
		if(a.getCouleur()==coulTour)
			return true;
		else
			return false;	
	}
	public static void TourSuivant(Couleur coul)
	{
		if (coul==Couleur.blanc)
			coulTour=Couleur.noir;
		else
			coulTour=Couleur.blanc;
	}
	public static void add(Joueur j,Joueur j2)
	{
		listjoueur[0]=j;listjoueur[1]=j2;
	}
	public static String getPseudo(Couleur coul)
	{
		if (listjoueur[0].getCouleur()==coul)
			return listjoueur[0].getPseudo();
		return listjoueur[1].getPseudo();
	}
    public static boolean Echec(Couleur cl)//Verifie si le roi est en echec
    {
    	if (cl==Couleur.blanc)
    		return Echec(cl, Echiquier.blanc.get(0).getCase());
    	return Echec(cl, Echiquier.noir.get(0).getCase());
    	
    }
    public static boolean Echec(Couleur cl,Case crt)//roi en echec verifie si la position est en echec pour verif deplacement roi
    //true roi en echec
    {
    	boolean ech=false;
    	if (cl==Couleur.blanc)
    	{
    		for (Piece i:Echiquier.noir)
    		{
    			if (i instanceof Pion && ((Pion)i).attVerification(crt))
    				ech=true;	
    			else if (!(i instanceof Roi) && (!(i instanceof Pion)))
    			{
    				if (i.verification(crt))
    					ech=true;
    			}
    		} 		
    	}
    	else if (cl==Couleur.noir)
    	{
    		for (Piece i:Echiquier.blanc)
    		{
    			if (i instanceof Pion && ((Pion)i).attVerification(crt))
    				ech=true;	
    			else if (!(i instanceof Roi) && (!(i instanceof Pion)))
    			{
    				if(i.verification(crt))
    					ech=true;			
    			}		
    		}
    	}
		return ech;   	
    }
    public static boolean mat(Couleur coul)// verifie l'etat du jeux 1 echec etmat du coté blanc -1 noir 0 pas d'echec et mat
    {
    	Roi b=(Roi)Echiquier.blanc.get(0);
    	Roi n=(Roi)Echiquier.noir.get(0);
    	boolean mat=false;
    	switch (coul) {
		case blanc:
			if (b.possibilitemat().isEmpty() && Echec(b.getCouleur(), b.getCase()));
				for(Piece i:Echiquier.blanc);
					
		case noir:
			return (n.possibilitemat().isEmpty() && Echec(n.getCouleur(), n.getCase()));
		}
    	return mat;
    }
    public static boolean mouvEchec(Case c,Case arriver) //verifie si un mouvement cause la mise en echec du roi true si c'est le cas
    {
    	boolean g=false;
    	Piece a;
    	Piece b;
    	a=arriver.getPiece();
    	b=c.getPiece();
    	arriver.setPiece(c.getPiece());
    	c.setPiece(null);
		try{
			if (b.couleur==Couleur.noir && Echec(Couleur.noir, Echiquier.noir.get(0).getCase()))
				g=true;
			else if (b.couleur==Couleur.blanc && Echec(Couleur.blanc, Echiquier.blanc.get(0).getCase()))
	    		g=true;
		}catch (NullPointerException e){}
		arriver.setPiece(a);
		c.setPiece(b);
		a=null;b=null;
		return g;
    }
    public static boolean pat()//Fauat que roi pas en echec et que 2 tetes donc plus assez de pieces pour mettre en echec
    {
    	Roi roib=(Roi)Echiquier.blanc.get(0);
    	Roi roin=(Roi)Echiquier.noir.get(0);
		if((!Echec(roin.getCouleur()) && roin.possibilitemat().isEmpty() && nbTete(roin.getCouleur())<3)||(!Echec(roib.getCouleur()) && roib.possibilitemat().isEmpty() && nbTete(roib.getCouleur())<3))
			return true;
		return false;
    }
    public static int nbTete(Couleur coul)//retourne le nombre de tetes, important pour le pat
    {
    	int i=0;
    	if (coul==Couleur.blanc)
    	{
    		for(Piece h:Echiquier.blanc)
    		{
    			if(h instanceof Tour || h instanceof Dame || h instanceof Fou)
    				i++;
    		}
    	}
    	else
    	{
    		for(Piece h:Echiquier.noir)
    		{
    			if(h instanceof Tour || h instanceof Dame || h instanceof Fou)
    				i++;
    		}
    	}
    	return i;
    }
    public static boolean mouvProtect(Case c,Case arriver) //verifie si un mouvement proteges le roi qui est en echec, true si c'est le cas
    {
    	boolean g=true;
    	Piece a;
    	Piece b;
    	a=arriver.getPiece();
    	b=c.getPiece();
    	arriver.setPiece(c.getPiece());
    	c.setPiece(null);
		try{

			if (arriver.getPiece().couleur==Couleur.noir && Echec(Couleur.noir, Echiquier.noir.get(0).getCase()))
				g=false;
			else if (arriver.getPiece().couleur==Couleur.blanc && Echec(Couleur.blanc, Echiquier.blanc.get(0).getCase()))
	    		g=false;
		}catch (NullPointerException e){}
		arriver.setPiece(a);
		c.setPiece(b);
		a=null;b=null;
		return g;
    }

}
