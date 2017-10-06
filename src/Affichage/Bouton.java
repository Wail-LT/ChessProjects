package Affichage;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import Affichage.Fenetre.ActionJouer;
import Affichage.PanJeux.ActionMouvement;
import jeux.Case;
import jeux.Couleur;
import jeux.Echiquier;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("cbb55709-3d0e-4683-84f9-ca0a735a8265")
public class Bouton extends JButton {


    @objid ("16c78118-5dff-4c73-8648-1fffdf38bfc5")
    public static final  ImageIcon b = new ImageIcon("data/b.png");
    public static final  ImageIcon p = new ImageIcon("data/p.jpg");
    
    private Case cases=null;

    @objid ("512ac2c2-0436-4b87-8147-b8c5be635759")
    public Bouton(ImageIcon im, int i, int j, ActionListener actionMouvement) {
        super(im);
        setForeground(Color.BLACK);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setFocusPainted(false);
        cases=Echiquier.ech.gcase(i, j);
        setHorizontalAlignment(SwingConstants.CENTER);
        setHorizontalTextPosition(SwingConstants.CENTER);
        this.addActionListener(actionMouvement);
    }
    public Bouton(ImageIcon im, ActionListener actionMouvement) {
        super(im);
        setBorderPainted(true);
        setContentAreaFilled(false);
        setOpaque(false);
        setFocusPainted(false);
        setHorizontalAlignment(SwingConstants.CENTER);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setHorizontalTextPosition(SwingConstants.CENTER);
        this.addActionListener(actionMouvement);
    }

    @objid ("3a05bbe9-a87f-49ae-9309-52f9c945aa71")
    public Bouton(String txt) {
        super(txt);
        setForeground(Color.white);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setFocusPainted(true);
        setPressedIcon(new ImageIcon("data/FHbouton.png"));
        setIcon(new ImageIcon("data/Fbouton.png"));
        setRolloverIcon(new ImageIcon("data/FHbouton.png"));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setHorizontalAlignment(SwingConstants.CENTER);
        setHorizontalTextPosition(SwingConstants.CENTER);
    }

    @objid ("b5c8e756-b45c-4f77-804c-cc17e5ecfb18")
    public Bouton(String txt,ActionListener a) {
        super(txt);
        setForeground(Color.WHITE);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setFocusPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setIcon(new ImageIcon("data/Fbouton.png"));
        setPressedIcon(new ImageIcon("data/FHbouton.png"));
        setRolloverIcon(new ImageIcon("data/FHbouton.png"));
        setHorizontalAlignment(SwingConstants.CENTER);
        setHorizontalTextPosition(SwingConstants.CENTER);
        this.addActionListener(a);
    }

    public Case getCases()
    {
    	return cases;
    }

}
