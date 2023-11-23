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
     * Liste des images du Personnage
     */
    private ArrayList<BufferedImage> images = new ArrayList<>();
    /**
     * Liste des images vers la gauche
     */
    private ArrayList<BufferedImage> imagesGauche = new ArrayList<>();
    /**
     * Liste des images vers la droite
     */
    private ArrayList<BufferedImage> imagesDroite = new ArrayList<>();
    /**
     * Liste des images vers le haut
     */
    private ArrayList<BufferedImage> imagesHaut = new ArrayList<>();
    /**
     * Liste des images vers le bas
     */
    private ArrayList<BufferedImage> imagesBas = new ArrayList<>();
    /**
     * Image du perso quand il est mort
     */
    private BufferedImage mort;
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
                BufferedImage bf1 = ImageIO.read(getClass().getResourceAsStream("/spriteperso/d1.png"));
                BufferedImage bf2 = ImageIO.read(getClass().getResourceAsStream("/spriteperso/d2.png"));
                BufferedImage bf3 = ImageIO.read(getClass().getResourceAsStream("/spriteperso/d3.png"));
                BufferedImage bf4 = ImageIO.read(getClass().getResourceAsStream("/spriteperso/d4.png"));
                //On les ajoutent aux images de base
                images.add(bf1);
                images.add(bf2);
                images.add(bf3);
                images.add(bf4);
                imagesDroite.add(bf1);
                imagesDroite.add(bf2);
                imagesDroite.add(bf3);
                imagesDroite.add(bf4);
                BufferedImage bf5 = ImageIO.read(getClass().getResourceAsStream("/spriteperso/g1.png"));
                BufferedImage bf6 = ImageIO.read(getClass().getResourceAsStream("/spriteperso/g2.png"));
                BufferedImage bf7 = ImageIO.read(getClass().getResourceAsStream("/spriteperso/g3.png"));
                BufferedImage bf8 = ImageIO.read(getClass().getResourceAsStream("/spriteperso/g4.png"));
                imagesGauche.add(bf5);
                imagesGauche.add(bf6);
                imagesGauche.add(bf7);
                imagesGauche.add(bf8);
                BufferedImage bf9 = ImageIO.read(getClass().getResourceAsStream("/spriteperso/h1.png"));
                BufferedImage bf10 = ImageIO.read(getClass().getResourceAsStream("/spriteperso/h2.png"));
                BufferedImage bf11 = ImageIO.read(getClass().getResourceAsStream("/spriteperso/h3.png"));
                BufferedImage bf12 = ImageIO.read(getClass().getResourceAsStream("/spriteperso/h4.png"));
                imagesHaut.add(bf9);
                imagesHaut.add(bf10);
                imagesHaut.add(bf11);
                imagesHaut.add(bf12);
                BufferedImage bf13 = ImageIO.read(getClass().getResourceAsStream("/spriteperso/b1.png"));
                BufferedImage bf14 = ImageIO.read(getClass().getResourceAsStream("/spriteperso/b2.png"));
                BufferedImage bf15 = ImageIO.read(getClass().getResourceAsStream("/spriteperso/b3.png"));
                BufferedImage bf16 = ImageIO.read(getClass().getResourceAsStream("/spriteperso/b4.png"));
                imagesBas.add(bf13);
                imagesBas.add(bf14);
                imagesBas.add(bf15);
                imagesBas.add(bf16);
            }
            mort = ImageIO.read(getClass().getResourceAsStream("/spriteperso/mort.png"));
        }catch (Exception e){
            e.printStackTrace();
        }

        this.p = p;
        setPreferredSize(new Dimension(25, 25));

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
                if(p.estMonstre()) {
                    g.drawImage(images.get(compteur), 0, 0, getWidth(), getHeight(), this);
                }else{
                    switch (p.getDernDirection()){
                        case DROITE:
                            if(p.isInvulnerable()){
                                Color gold = new Color(255, 215, 0);
                                int rgb = gold.getRGB();
                                imagesDroite.get(compteur).setRGB(p.getPosX(),p.getPosY(), rgb);
                                g.drawImage(imagesDroite.get(compteur), 0, 0, getWidth(), getHeight(), this);

                            }else{
                                g.drawImage(imagesDroite.get(compteur), 0, 0, getWidth(), getHeight(), this);

                            }
                            break;
                        case GAUCHE:
                            if(p.isInvulnerable()){
                                Color gold = new Color(255, 215, 0);
                                int rgb = gold.getRGB();
                                imagesGauche.get(compteur).setRGB(p.getPosX(),p.getPosY(), rgb);
                                g.drawImage(imagesGauche.get(compteur), 0, 0, getWidth(), getHeight(), this);

                            }else{
                                g.drawImage(imagesGauche.get(compteur), 0, 0, getWidth(), getHeight(), this);

                            }
                            break;
                        case BAS:
                            if(p.isInvulnerable()){
                                Color gold = new Color(255, 215, 0);
                                int rgb = gold.getRGB();
                                imagesBas.get(compteur).setRGB(p.getPosX(),p.getPosY(), rgb);
                                g.drawImage(imagesBas.get(compteur), 0, 0, getWidth(), getHeight(), this);

                            }else{
                                g.drawImage(imagesBas.get(compteur), 0, 0, getWidth(), getHeight(), this);

                            }
                            break;
                        case HAUT:
                            if(p.isInvulnerable()){
                                Color gold = new Color(255, 215, 0);
                                int rgb = gold.getRGB();
                                imagesHaut.get(compteur).setRGB(p.getPosX(),p.getPosY(), rgb);
                                g.drawImage(imagesHaut.get(compteur), 0, 0, getWidth(), getHeight(), this);

                            }else{
                                g.drawImage(imagesHaut.get(compteur), 0, 0, getWidth(), getHeight(), this);

                            }
                            break;
                        default:
                            g.drawImage(images.get(compteur), 0, 0, getWidth(), getHeight(), this);
                            break;
                    }
                }

            }else{
                g.drawImage(mort, 0, 0, getWidth(), getHeight(), this);
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
