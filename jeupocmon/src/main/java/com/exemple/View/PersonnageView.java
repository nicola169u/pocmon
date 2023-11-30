package main.java.com.exemple.View;

import main.java.com.exemple.Model.Case.Case;
import main.java.com.exemple.Model.Joueur;
import main.java.com.exemple.Model.Personnage;
import main.java.com.exemple.Tools.Direction;
import main.java.com.exemple.Tools.ImageManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


/**
 * Classe représentant la vue du Personnage
 */
public class PersonnageView extends JPanel {
    public static final int NBSPRITE = 4;
    /**
     * Le Personnage
     */
    private Personnage p;
    /**
     * Le compteur pour alterner les sprites
     */
    private int compteur = 1;




    /**
     * Constructeur de PersonnageView en fonction du Personnage p
     *
     * @param p
     */
    public PersonnageView(Personnage p) {
        this.p = p;
        setPreferredSize(new Dimension(30, 30));
    }


    /**
     * procédure permettant d'afficher le Personnage
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage image = null;
        BufferedImage epee = null;
        if(p.estMonstre()) {
            image = ImageManager.getInstance().getImage(p.getType());
        }else{
            //C'est le joueur donc on implémente le compteur
            image = ImageManager.getInstance().getImage(p.getType() + compteur);
            //epee = loadSpriteEpee(p.getType());
            if(p.isInvulnerable()){
                //Si invulnerable, on met le filtre gold
                image = filtregold(image);
            }else{
                //Sinon, on reinitialise à la version originale
                resetFiltre(image, image);
            }
        }

        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        if(!this.p.estMonstre() && p.getAttaque()){
            Direction monstre = ((Joueur)p).getDirectionEpee();
            epee = loadSpriteEpee(monstre);
            g.drawImage(epee, this.p.getPosX(), this.p.getPosY(), getWidth()- 10, getHeight()-10, this);
        }


        this.gestionVie(g);
    }

    private BufferedImage loadSpriteEpee(Direction type) {
        BufferedImage res = null;
        switch(type){
            case GAUCHE:
                res = ImageManager.getInstance().getImage("EpeeG");
                break;
            case DROITE:
                res = ImageManager.getInstance().getImage("EpeeD");
                break;
            case HAUT:
                res = ImageManager.getInstance().getImage("EpeeH");
                break;
            case BAS:
                res = ImageManager.getInstance().getImage("EpeeB");
                break;
        }
        return res;
    }


    /**
     * Procédure qui gère l'affichage de la vie (points de vie) du Personnage
     *
     * @param g
     */
    public void gestionVie(Graphics g) {
        int pvMax = p.getPvMax();
        int pv = p.getPv();
        int x = p.getPosX() - pvMax / 2;
        int y = p.getPosY() / Case.TAILLE;

        g.setColor(Color.red);
        g.fillRect(0, 0, pvMax, 7);
        g.setColor(Color.green);
        g.fillRect(0, 0, pv, 7);
        if (p.isMort()) {
            g.setFont(new Font(null, Font.PLAIN, 100));
            g.setColor(new Color(0, 0, 0, 120));
            g.fillRect(0, 0, 900, 900);
            g.setColor(Color.WHITE);
            this.centrerTexte(g, "GAME OVER", 900, 900, g.getFont());
        }

    }


    /**
     * Procédure qui centre le texte text à la position (posx, posy) et avec la police d'écriture font
     *
     * @param g
     * @param text
     * @param posx
     * @param posy
     * @param font
     */
    public void centrerTexte(Graphics g, String text, int posx, int posy, Font font) {

        Rectangle rect = new Rectangle(0, 0, posx, posy);
        FontMetrics metrics = g.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, x, y);
    }

    private BufferedImage filtregold(BufferedImage originalImage) {
        BufferedImage filteredImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < originalImage.getHeight(); y++) {
            for (int x = 0; x < originalImage.getWidth(); x++) {
                Color pixelColor = new Color(originalImage.getRGB(x, y));
                int red = (int) (pixelColor.getRed() * 0.8);
                int green = (int) (pixelColor.getGreen() * 0.8);
                int blue = 0;
                int alpha = pixelColor.getAlpha();
                Color goldFilteredColor = new Color(red, green, blue, alpha);
                filteredImage.setRGB(x, y, goldFilteredColor.getRGB());
            }
        }

        return filteredImage;
    }

    private void resetFiltre(BufferedImage originalImage, BufferedImage filteredImage) {
        // Dessiner l'image filtrée sur l'image originale
        originalImage.getGraphics().drawImage(filteredImage, 0, 0, null);
    }

    public void updateCompteur() {
        this.compteur = (compteur%NBSPRITE) + 1;
    }
}
