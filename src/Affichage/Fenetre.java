package Affichage;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("7f2ab2be-dd58-4d67-a45e-309234eccfd1")
public class Fenetre extends JFrame {
	// JPanel + Layout fenetre
    private CardLayout winLay=new CardLayout();
	private JPanel winPan=new JPanel(winLay);
    
	//list des panel
	public static final String listPan[]={"menu","reglement","parametre"};

	//unique instance de Fenetre 
    @objid ("59977a4e-8338-4238-8c1c-e55a2e29282b")
    private static Fenetre jeuxEchec = new Fenetre();
    
	// panel menu
	@objid ("83d470af-73eb-4006-b058-92de932ef5c3")
    private MenuPan menu = new MenuPan();

    @objid ("d7991bed-d1f4-4d7b-859f-3cefb2f3e214")
    private Fenetre() {
    	// parametre fenetre
    	Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    	int height = (int)dimension.getHeight();
        setSize(90*height/100+160,90*height/100+40);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //ajout Bouton du menu
        menu.add(new Bouton("jouer",new ActionJouer()),menu.getGbc());
        menu.getGbc().gridx=1;
        menu.add(new Bouton("Reglement"),menu.getGbc());
        menu.getGbc().gridx=2;
        menu.add(new Bouton("Paramettre"),menu.getGbc());
        menu.getGbc().gridwidth=GridBagConstraints.REMAINDER;//fin de ligne
        // ajout des panels
        winPan.add(menu,listPan[0]);
        this.setContentPane(winPan);
        this.setVisible(true);
    }

    //panel de jeux 
    
  
    @objid ("4cc18338-708a-42b1-9136-d13af77bfb33")
    public static Fenetre getJeuxEchec() {
        return jeuxEchec;
    }
    public JPanel getWinPan()
    {
    	return winPan;
    }
    public static void setPan(JPanel pan)
    {
    	jeuxEchec.setContentPane(pan);
    	SwingUtilities.updateComponentTreeUI(jeuxEchec);
    }
    
    @objid ("38797a40-4276-49f6-af07-bbbf64b5baff")
    class ActionJouer implements ActionListener {
        @objid ("d9591bad-ef8f-4d61-b65f-ea4d18b3378d")
        @Override
        public void actionPerformed(ActionEvent arg0) {
            jeuxEchec.setContentPane(new PanCN());
            SwingUtilities.updateComponentTreeUI(jeuxEchec);
        }

    }
    

    
    /* la fenetre est constititué de boutons dans un grille layout
     * si on clique sur un bouton sa va changer la couleur du panel et sa affiche ttes les possibilités
     * si on reclique sur ce meme bouton saréaffiche la couleur de base du panel et enlève l'affichage des possibilités
     * si ce n'est pas le meme bouton sa essaye de faire le mouvement et sa remet les couleurs de bases des panels
     * 
     */


}
