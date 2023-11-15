package main.java.com.exemple.View;

import main.java.com.exemple.Model.Personnage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PersonnageView extends JPanel {

    private ArrayList<BufferedImage> images = new ArrayList<>();
    private Personnage p;
    private int compteur = 0;


    public PersonnageView(Personnage p){
        try {
            if (p.isMonstreIntelligent() && !p.estFantome()) {
                images.add(ImageIO.read(getClass().getResourceAsStream("/sa2.gif")));
            }else if(p.isMonstreAleatoire()){
                images.add(ImageIO.read(getClass().getResourceAsStream("/s2.gif")));
            }else if(p.estFantome()){
                images.add(ImageIO.read(getClass().getResourceAsStream("/fantome/idle_g/frame_00_delay-0.08s.gif")));
            }else{ //C'est le joueur
                BufferedImage bf1 = ImageIO.read(getClass().getResourceAsStream("/perso/d1.gif"));
                BufferedImage bf2 = ImageIO.read(getClass().getResourceAsStream("/perso/d2.gif"));
                BufferedImage bf3 = ImageIO.read(getClass().getResourceAsStream("/perso/d3.gif"));
                BufferedImage bf4 = ImageIO.read(getClass().getResourceAsStream("/perso/d4.gif"));
                images.add(bf1);
                images.add(bf2);
                images.add(bf3);
                images.add(bf4);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        this.p = p;
        setPreferredSize(new Dimension(30, 30));

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        compteur = (compteur + 1)%images.size();
        if(images.size() > 0){
            g.drawImage(images.get(0), 0, 0, getWidth(), getHeight(), this);
        }
        // Dessine l'image au lieu de remplir un rectangle
//        if (test != null) {
//            g.drawImage(test, 0, 0, getWidth(), getHeight(), this);
//        }
//        g.setColor(color);
//        g.fillRect(0, 0, getWidth(), getHeight());
    }

}
