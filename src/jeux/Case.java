package jeux;
//modifier
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("273d72f0-ede8-4245-bb25-19824ffb34c3")
public class Case {
//  Attribute
    @objid ("4d6f1af6-70ee-4c10-a4d7-9d520bd31f05")
    private int range;

    @objid ("8e74f7a0-c3f8-46d7-9e84-d8219e5a51f3")
    private int colonne;

    @objid ("3bfa8d01-9758-4371-86b5-45530297507b")
    private Piece piece;

// Methods
    @objid ("d8f5b1d2-81a1-49dd-8dbf-5f33bc26022c")
    public Case(int range, int colonne) {//initialiser les pions dans le bonne ordre
        this.range=range;
        this.colonne=colonne;
        if (this.range==1 || this.range==6)
        {
            this.piece= new Pion(this);
        }
        else if (this.range>1 && this.range<6)
        {
            this.piece=null;
        }
        else if(this.colonne==0 || this.colonne==7)
        {
            this.piece= new Tour(this); 
        }
        else if(this.colonne==1 || this.colonne==6)
        {
            this.piece= new Cavalier(this); 
        }
        else if(this.colonne==2 || this.colonne==5)
        {
            this.piece= new Fou(this); 
        }
        else if (this.colonne==3)
        {
            this.piece=new Dame (this);
        }
        else if (this.colonne==4)
        {
            this.piece=new Roi(this);
        }
    }
    public Case (int i,int j,Piece p)
    {
    	range=i;
    	colonne=j;
    	setPiece(p);
    }
    public Case (int i,int j,String p,Couleur coul,Boolean debut,int index)
    {
    	range=i;
    	colonne=j;
    	switch (p) {
		case "P":
			this.piece=new Pion(this, coul);
			piece.setI(index);
			piece.setDebut(debut);
			if (coul==Couleur.blanc)
				Echiquier.blanc.add(piece);
			else
				Echiquier.noir.add(piece);		
			break;
		case "T":
			this.piece=new Tour(this, coul);
			piece.setI(index);
			piece.setDebut(debut);
			if (coul==Couleur.blanc)
				Echiquier.blanc.add(piece);
			else
				Echiquier.noir.add(piece);		
			break;
		case "F":
			this.piece=new Fou(this, coul);
			piece.setI(index);
			piece.setDebut(debut);
			if (coul==Couleur.blanc)
				Echiquier.blanc.add(piece);
			else
				Echiquier.noir.add(piece);		
			break;
		case "R":
			this.piece=new Roi(this, coul);
			piece.setI(index);
			piece.setDebut(debut);
			if (coul==Couleur.blanc)
				Echiquier.blanc.add(piece);
			else
				Echiquier.noir.add(piece);		
			break;
		case "C":
			this.piece=new Cavalier(this, coul);
			piece.setI(index);
			piece.setDebut(debut);
			if (coul==Couleur.blanc)
				Echiquier.blanc.add(piece);
			else
				Echiquier.noir.add(piece);		
			break;
		case "D":
			this.piece=new Dame(this, coul);
			piece.setI(index);
			piece.setDebut(debut);
			if (coul==Couleur.blanc)
				Echiquier.blanc.add(piece);
			else
				Echiquier.noir.add(piece);		
			break;
		}
    	
    }

    @objid ("b5353494-c538-4d67-a173-27c62ed8306a")
    public void afficher() {//afficher le contenu de la case
        if (this.piece==null)
        {
            System.out.print(" vide  ");
        }
        else
        {
        	 System.out.print(this.piece.afficher()+" ");
        }
    }

    @objid ("33fff413-a861-424a-bd2a-15f54b7e77ab")
    public int getColonne() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.colonne;
    }

    @objid ("155f9ebb-81d8-4b7d-aeed-d44fb512533d")
    public int getRange() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.range;
    }

    @objid ("f3c51d87-626a-4e5f-a0b7-6ba622f0f9f6")
    public void setPiece(Piece value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.piece = value;
    }

    @objid ("acdb0ba9-931c-40bc-bf0c-3fe70e0a7670")
    public Piece getPiece() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.piece;
    }
    public boolean equals(Case a)
    {//pour savoir si deux cases sont itendiques
    	if (a.range==range && a.colonne==colonne)
    		return true;
		return false;
    }
    public String information()
    {
    	if (piece==null)
    		return "null\n";
    	return piece.information();
    }
    public String toString()
    {
    		return "case : "+this.range+" "+this.colonne;
    }
}

