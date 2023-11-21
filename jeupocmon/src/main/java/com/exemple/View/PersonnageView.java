package main.java.com.exemple.View;

import main.java.com.exemple.Model.Case;
import main.java.com.exemple.Model.Monstre;
import main.java.com.exemple.Model.Personnage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Classe représentant la vue du Personnage
 */
public class PersonnageView extends JPanel {
    /**
     * Lsite des images du Personnage
     */
    private ArrayList<BufferedImage> images = new ArrayList<>();
    /**
     * Le Personnage
     */
    private Personnage p;
    /**
     * Le compteur pour alterner les sprites
     */
    private int compteur = 0;


    /**
     * Constructeur de PersonnageView en fonction du Personnage p
     * @param p
     */
    public PersonnageView(Personnage p){
        this.p = p;
        try {
            if (p.isMonstreIntelligent() && !p.estFantome()) {
                images.add(ImageIO.read(getClass().getResourceAsStream("/sa2.gif")));
            }else if(p.isMonstreAleatoire()){
                images.add(ImageIO.read(getClass().getResourceAsStream("/s2.gif")));
            }else if(p.estFantome()){
                images.add(ImageIO.read(getClass().getResourceAsStream("/reaper/idle_g/frame_00_delay-0.08s.gif")));
            }else{ //C'est le joueur
                BufferedImage bf1 = ImageIO.read(getClass().getResourceAsStream("/spriteperso/d1.gif"));
                BufferedImage bf2 = ImageIO.read(getClass().getResourceAsStream("/spriteperso/d2.gif"));
                BufferedImage bf3 = ImageIO.read(getClass().getResourceAsStream("/spriteperso/d3.gif"));
                BufferedImage bf4 = ImageIO.read(getClass().getResourceAsStream("/spriteperso/d4.gif"));
                images.add(bf1);
                images.add(bf2);
                images.add(bf3);
                images.add(bf4);
            }
            images.add(ImageIO.read(getClass().getResourceAsStream("/spriteperso/mort.png")));
        }catch (Exception e){
            e.printStackTrace();
        }

        this.p = p;
        setPreferredSize(new Dimension(30, 30));

    }


    /**
     * procédure permettant d'afficher le Personnage
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        compteur = (compteur + 1)%images.size();
        if(images.size() > 0){
            if(p.isVivant()){
                g.drawImage(images.get(0), 0, 0, getWidth(), getHeight(), this);

            }else{
                g.drawImage(images.get(images.size()-1), 0, 0, getWidth(), getHeight(), this);
            }

        }
        this.gestionVie(g);
    }


    /**
     * Procédure qui gère l'affichage de la vie (points de vie) du Personnage
     * @param g
     */
    public void gestionVie(Graphics g){
        int pvMax = p.getPvMax();
        int pv = p.getPv();
        int x = p.getPosX() - pvMax / 2;
        int y = p.getPosY() / Case.TAILLE;

        g.setColor(Color.red);
        g.fillRect(0, 0, pvMax, 7);
        g.setColor(Color.green);
        g.fillRect(0, 0, pv, 7);
        if (p.isMort()){
            g.setFont(new Font(null, Font.PLAIN, 100));
            g.setColor(new Color(0,0,0,120));
            g.fillRect(0,0,900,900);
            g.setColor(Color.WHITE);
            this.centrerTexte(g, "GAME OVER", 900,900, g.getFont());
        }

    }


    /**
     * Procédure qui centre le texte text à la position (posx, posy) et avec la police d'écriture font
     * @param g
     * @param text
     * @param posx
     * @param posy
     * @param font
     */
    public void centrerTexte(Graphics g, String text, int posx, int posy , Font font) {

        Rectangle rect = new Rectangle(0,0,posx,posy);
        FontMetrics metrics = g.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, x, y);
    }

}
