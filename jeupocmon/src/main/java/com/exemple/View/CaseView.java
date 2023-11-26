package main.java.com.exemple.View;

import main.java.com.exemple.Model.Case.*;
import main.java.com.exemple.Tools.ImageManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Classe représentant la vue d'une case
 */
public class CaseView extends JPanel {
    /**
     * La case concernée par la vue
     */
    private Case c;
    /**
     * La couleur de cette case
     */
    private Color color;
    /**
     * L'image de cette case
     */
    private BufferedImage image;
    /**
     * Map des paths des différentes images de case
     */
    private static Map<String, String> imagePaths = new HashMap<>();
    static {
        imagePaths.put("Mur", "/wall.png");
        imagePaths.put("Tresor", "/tresor.png");
        imagePaths.put("Teleporteur", "/porte.png");
        imagePaths.put("Mine", "/mine.png");
        imagePaths.put("Piege", "/piege.png");
        imagePaths.put("PotionForce", "/potionForce.png");
        imagePaths.put("PotionVie", "/potionVie.png");
        imagePaths.put("Etoile", "/etoile.png");
        imagePaths.put("Default", "/sol.png"); // Chemin par défaut
    }


    /**
     * Constructeur de CaseView en fonction de la case c à représenter
     * @param c
     */
    public CaseView(Case c) {
        image = ImageManager.getInstance().getImage(c.getType());
        this.c = c;
        setPreferredSize(new Dimension(30, 30));
    }


    /**
     * Procédure héritée de JPanel, permet d'afficher la case
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Seuls cas où l'image peut changer
        if(c.estMine() || c.estPotionVie() || c.estPotionForce() || c.estEtoile()){
            image = ImageManager.getInstance().getImage(c.getType());
        }

        //On redessine l'image
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);



//        g.setColor(color);
//        g.fillRect(0, 0, getWidth(), getHeight());
    }


    /**
     * Setter de la case c
     * @param c
     */
    public void setC(Case c) {
        this.c = c;
    }


    /**
     * Setter de la couleur color de la case
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

}
