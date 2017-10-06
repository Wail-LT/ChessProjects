package Project.Exception;

public class CouleurIdentique extends Exception{
	private static String mess=new String("Les deux Joueurs ont choisie la même couleur ou aucun d'eux n'en a choisie !");
	public CouleurIdentique() {
		super(mess);
	}

}
