package SaveCharge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import jeux.Arbitre;
import jeux.Case;
import jeux.Couleur;
import jeux.Echiquier;
import jeux.Joueur;
import jeux.Piece;


public class SaveCharge {
	private static BufferedReader bfr;
	private static FileReader fR;
	private static FileWriter fW;
	public static void save()
	{
		try {
			fW=new FileWriter(new File("save.txt"));
		} catch (IOException e) {
			JOptionPane joperr=new JOptionPane();
			joperr.showMessageDialog(null, "erreur lors de la creation du fichier ! ", "Erreur",JOptionPane.ERROR_MESSAGE);
		}
		String a =new String();
		try{
			fW.write(Arbitre.coulTour+"\n");
			for(int j=0;j<2;j++)
			{
				a=Arbitre.listjoueur[j].information();
				fW.write(a);
			
			}
			
			for (Case i:Echiquier.ech.getTable())
			{
				a=i.information();
				fW.write(a);
			}
			JOptionPane jopinfo=new JOptionPane();
			jopinfo.showMessageDialog(null, "la sauvegarde de votre partie a été un succès !", "reussit",JOptionPane.INFORMATION_MESSAGE);
				fW.close();
			} catch (IOException e) {
				JOptionPane joperr=new JOptionPane();
				joperr.showMessageDialog(null, "La sauvegarde n'a pas pue être realisé suite a une erreur! ", "Erreur",JOptionPane.ERROR_MESSAGE);
			}
	}
	public static void charger()
	{
		ArrayList<Case>table=new ArrayList<Case>();
		String a=new String();
		String piece=new String("");
		try {
			fR=new FileReader(new File("save.txt"));
			bfr=new BufferedReader(fR);
			Arbitre.coulTour=Couleur.parsecoul(bfr.readLine());
			try {
					Arbitre.listjoueur[0]=new Joueur(bfr.readLine(), Couleur.parsecoul(bfr.readLine()));
					Arbitre.listjoueur[1]=new Joueur(bfr.readLine(),  Couleur.parsecoul(bfr.readLine()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i=0;i<8;i++)
			{
				for(int j=0;j<8;j++)
				{
					piece=bfr.readLine();
				
					if (piece.equals("null"))
						table.add(new Case(i,j,null));
					else 
					{				
						table.add(new Case(i,j,piece,Couleur.parsecoul(bfr.readLine()),Boolean.parseBoolean(bfr.readLine()),Integer.parseInt(bfr.readLine())));
					}
					
				}
			}
			Echiquier.ech=new Echiquier(table);
			
		} catch (IOException e) {
			JOptionPane joperr=new JOptionPane();
			joperr.showMessageDialog(null, "erreur lors  de l'ouverture du fichier !", "Erreur",JOptionPane.ERROR_MESSAGE);
		}
	}
}