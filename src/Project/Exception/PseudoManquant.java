package Project.Exception;

public class PseudoManquant extends Exception{
	private static String mess=new String("L'un des Joueurs n'a pas saisie de Pseudo");
	public PseudoManquant() {
		super(mess);
	}
}
