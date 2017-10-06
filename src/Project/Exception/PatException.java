package Project.Exception;

import javax.swing.JOptionPane;

public class PatException extends Exception{
	private static String mess=new String("Egalité");
	public PatException() {
		super(mess);
		JOptionPane jop=new JOptionPane();
		jop.showMessageDialog(null,mess, "PAT",JOptionPane.INFORMATION_MESSAGE);
	}
}
