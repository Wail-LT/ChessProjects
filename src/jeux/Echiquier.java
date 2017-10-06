package jeux;

import java.util.ArrayList;
import java.util.List;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("dfb17340-ddf1-4679-b2e6-b21373167579")
public class Echiquier {
    @objid ("6ce4c0c6-b4fc-4c55-90b9-9ec55c4254cf")
    public static final char[] lettre = {'A','B','C','D','E','F','G','H'};

    @objid ("8b707ab2-fbaf-4d20-a169-5eee14dc0832")
    private List<Case> table = new ArrayList<Case> ();

    @objid ("4ad453c7-a3c0-435b-9c6e-655a8dae3d77")
    public static List<Piece> blanc = new ArrayList<Piece>();

    @objid ("bf5fbc42-ae81-44fc-a8b9-ba7421cb3049")
    public static List<Piece> noir = new ArrayList<Piece>();

    @objid ("12c25d96-f8d7-492e-bf24-8e36cdacbd1a")
    public static Echiquier ech ;

    @objid ("a55b55d1-22a2-48ee-84a9-f6fade30e847")
	public Echiquier() { // les cases vont s'ajouter dans l'échiquier et selon l'emplacement de cases les pions vont se placer dans les cases qui y corresondent
        Case a;
        for (int i=0;i<8;i++)
        {
            for (int j=0;j<8;j++)
            {
                a= new Case (i,j);
                table.add(a);
                if (i<2)
                {
                	if(a.getPiece().toString()=="R")
                	{
                		blanc.add(blanc.get(j-1));
                		for(int k=j-1;k>0;k--)
                		{
                			blanc.set(k,blanc.get(k-1));
                		}
                		blanc.set(0, a.getPiece());
                	}
                	if (a.getPiece().toString()!="R")
                	{
                		blanc.add(a.getPiece());
                        a.getPiece().setI(i*8+j);
                	}
                    
                }
                else if (i>5)
                {
                	if(a.getPiece().toString()=="R")
                	{
                		noir.add(noir.get(j-1));
                		for(int k=j-1;k>0;k--)
                		{
                			noir.set(k,noir.get(k-1));
                		}
                		noir.set(0, a.getPiece());
                	}
                	if (a.getPiece().toString()!="R")
                	{
                		noir.add(a.getPiece());
                        a.getPiece().setI((i-6)*8+j);
                	}
                }
            }
        }
        a=null;
    }
    public Echiquier(ArrayList<Case>tab)
    {
    	table=tab;
    }
    public List<Case> getTable()
    {
    	return table;
    }
    public void setTable(ArrayList<Case> a)
    {
    	table=a;
    }
    @objid ("dd177289-4db3-4e26-b982-2dd3b928d432")
    public void afficher() {//on s'en fout
        int k=0;
        for (int w=0;w<8;w++)
        {
            for (int i=0;i<4;i++)
            {
                for(int j=0;j<8;j++)
                {
                    if (j==0 && i==1)
                    {
                        System.out.print(w+1+" ");
                    }
                    if (i==0)
                    {
                        if (j==0)
                            System.out.print("  ");
                        if (j==7)
                        {
                            System.out.println("------");
                        }
                        else
                        {
                            System.out.print("-----");
                        }
                    }
                    else if (i==1)
                    {
        
                        if (j==7)
                        {
                            System.out.print("| ");
                            table.get(k).afficher();
                            k++;
                            System.out.println("|");
                        }
                        else
                        {
                            System.out.print("| ");
                            table.get(k).afficher();
                            k++;
                        }
                    }
                    else if (i==2 && w==7)
                    {
                        if (j==0)
                            System.out.print("  ");
                        if (j==7)
                        {
                            System.out.println("------");
                        }
                        else
                        {
                            System.out.print("-----");
                        }
                    }
                    else if (i==3 && w==7)
                        {
                            System.out.print("    "+lettre[j]);
                        }
                }
            }
        }
        System.out.println("");
    }

    /*@objid ("71402d81-be0d-4e87-b3b0-62ca49c2d91b")
    public void mouvement(int li, char col, int lib, char colb) {
        if (Arbitre.verification(gcase(li,col), gcase(lib,colb)))
        {
            try {
                gcase(li, col).getPiece().mouvement(gcase(lib, colb));
            } catch (MouvementMauvais e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            afficher();
        }
        else 
            System.out.println("les coordonnï¿½es saisie sont erronï¿½");
    }*/


  //pour savoir si la case attaqué contient un pion ou non on va dans l'array list case et on vérifie si cette case est bien prise ou non, cette méthode sert à verifier le déplacement
      
    @objid ("56e040de-6622-4a5a-a10b-0c4126ea4318")
    public Case gcase(int li, int col) {
    	try{
    		return table.get((li)*8+col);
    	}catch(IndexOutOfBoundsException e){return null;}
        
    }

}
