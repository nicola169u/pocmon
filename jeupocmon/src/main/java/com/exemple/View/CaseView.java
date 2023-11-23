package main.java.com.exemple.View;

import main.java.com.exemple.Model.Case;
import main.java.com.exemple.Model.CaseMine;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


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
    private BufferedImage test;


    /**
     * Constructeur de CaseView en fonction de la case c à représenter
     * @param c
     */
    public CaseView(Case c) {
        try {
            if(c.estMur()){
                test = ImageIO.read(getClass().getResourceAsStream("/wall.png"));
            }else if (c.estTresor()){
                test = ImageIO.read(getClass().getResourceAsStream("/tresor.png"));
            }else if(c.estTeleporteur()){
                test = ImageIO.read(getClass().getResourceAsStream("/porte.png"));
            }else if (c.estMine()){
                test = ImageIO.read(getClass().getResourceAsStream("/mine.png"));

            }else if(c.estPiege()){
                test = ImageIO.read(getClass().getResourceAsStream("/piege.png"));

            }else{
                test = ImageIO.read(getClass().getResourceAsStream("/sol.png"));

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        if (c.estMur()) {
//            this.color = Color.black;
//        } else if (c.estTresor()) {
//            this.color = Color.yellow;
//        } else if (c.estTeleporteur()) {
//            this.color = Color.pink;
//        } else {
//            this.color = Color.white;
//        }
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
        // Dessine l'image au lieu de remplir un rectangle
        if (test != null) {
            if(c.estMine() && ((CaseMine) c).exploded()){
                try {
                    g.drawImage(ImageIO.read(getClass().getResourceAsStream("/mineDesac.png")), 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else{
                g.drawImage(test, 0, 0, getWidth(), getHeight(), this);
            }
        }
//        g.setColor(color);
//        g.fillRect(0, 0, getWidth(), getHeight());
    }


    /**
     * Setter de la case c
     * @param c
     */
    public void setC(Case c) {
        this.c = c;
        try {
            if (c.estMur()) {
                //this.color = Color.black;
                test = ImageIO.read(getClass().getResourceAsStream("/wall.png"));
            } else if (c.estTresor()) {
                //this.color = Color.yellow;
                test = ImageIO.read(getClass().getResourceAsStream("/tresor.png"));
            } else if (c.estTeleporteur()) {
                //this.color = Color.pink;
                test = ImageIO.read(getClass().getResourceAsStream("/porte.png"));
            } else {
                this.color = Color.white;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * Setter de la couleur color de la case
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

}
