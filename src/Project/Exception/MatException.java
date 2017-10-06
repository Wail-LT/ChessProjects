package Project.Exception;

import javax.swing.JOptionPane;

public class MatException extends Exception{
	private static String mess=new String("Echec et Mat les ");
	public MatException(String coul) {
		super(mess+coul+" ont gagne");
		JOptionPane jop=new JOptionPane();
		jop.showMessageDialog(null,mess+coul+" ont gagne", "PAT",JOptionPane.INFORMATION_MESSAGE);
	}
	

}
