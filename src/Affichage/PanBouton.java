
package Affichage;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;
import javax.swing.ImageIcon;
import Affichage.PanJeux.ActionMouvement;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("cbb55709-3d0e-4683-84f9-ca0a735a8265")
public class PanBouton extends JPanel {

    
    private Bouton but;
    
    private Color bgcolor;

    public PanBouton(ImageIcon im, int i, int j, ActionMouvement actionMouvement, boolean blanc) {
        super(new BorderLayout());
        but=new Bouton(im, i, j, actionMouvement);
        add(but);
        if (blanc)
        {
        	bgcolor=Color.WHITE;
        	setBackground(bgcolor);
        }
        	
        else 
        {
        	bgcolor=Color.DARK_GRAY;
        	setBackground(bgcolor);
        }
            
    }
    public Bouton getBut()
    {
    	return but;
    }
    public void setIcon(ImageIcon im)
    {
    	but.setIcon(im);
    }
    public Color getBgcolor()
    {
    	return bgcolor;
    }


}
