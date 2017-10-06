package jeux;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("d9350f18-2d00-4edc-ab1c-31ad11a997ce")
public enum Couleur {//enumeration afin de pouvoir accès aux couleurs
    noir ("Noir"),
    blanc ("Blanc");

    @objid ("2f6bed8b-c5b8-4399-b4c7-6ed78cc2341d")
     String couleur = "";

    @objid ("932e458c-65d6-4082-ad54-4bc1566cecd9")
    Couleur(String couleur) {
        this.couleur=couleur;
    }

    @objid ("3af148d6-bde9-4928-8b2c-e6efe5f8b8e8")
    public String toString() {
        return couleur;
    }
    public static Couleur parsecoul(String a)
    {
    	if (a.equals("Noir"))
    		return noir;
    	else if(a.equals("Blanc"))
    		return blanc;
    	return null;
    }

}
