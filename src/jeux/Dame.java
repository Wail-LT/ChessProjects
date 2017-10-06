package jeux;

import java.util.ArrayList;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import Project.Exception.MauvaisePiece;

@objid ("15576e51-0028-4b2c-b3ed-3c50627652d7")
public class Dame extends Piece {
    @objid ("af95450b-655c-4c70-957a-b1e8ce149db8")
    public Dame(Case cases) {
        super(cases);
    }
    public Dame(Case cases,Couleur coul) {
        super(cases);
        couleur=coul;
    }

    @objid ("eb0059a9-970a-44e8-980e-b5fdc292df15")
    public String afficher() {
        return "D"+couleur;
    }

    @objid ("df45ad35-d885-4574-8a01-7a7e6c8e5623")
    public String toString() {
        return "D";
    }

    @objid ("1c2cc45b-3d8d-482e-88e8-91eda60b7ef2")
    public ArrayList<Case> possibilite()throws MauvaisePiece
    {
    	if(!Arbitre.Tour(this))
    	{
    		throw new MauvaisePiece();
    	}
    	ArrayList a=Fou.possibilite(cases);
    	a.addAll(Tour.possibilite(cases));
        return a;
    }

	@Override
	public boolean verification(Case c2) {
		// TODO Auto-generated method stub
		return Fou.verification(cases, c2) || Tour.verification(cases, c2);
	}

}
