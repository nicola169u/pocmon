package main.java.com.exemple.View;

import main.java.com.exemple.Model.Personnage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PersonnageView extends JPanel {

    private BufferedImage perso;
    private Personnage p;


    public PersonnageView(Personnage p){
        try {


            if (p.isMonstreIntelligent() && !p.estFantome()) {
                perso = ImageIO.read(getClass().getResourceAsStream("/sa2.gif"));
            }else if(p.isMonstreAleatoire()){
                perso = ImageIO.read(getClass().getResourceAsStream("/s2.gif"));
            }else if(p.estFantome()){
                perso = ImageIO.read(getClass().getResourceAsStream("/fantome/idle_g/frame_00_delay-0.08s.gif"));

            }else{
                perso = ImageIO.read(getClass().getResourceAsStream("/perso/d1.gif"));
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
        if(perso!=null){
            g.drawImage(perso, p.getPosX(), p.getPosY(), getWidth(), getHeight(), this);
        }
        // Dessine l'image au lieu de remplir un rectangle
//        if (test != null) {
//            g.drawImage(test, 0, 0, getWidth(), getHeight(), this);
//        }
//        g.setColor(color);
//        g.fillRect(0, 0, getWidth(), getHeight());
    }


    public void setImage(BufferedImage mort){
        this.perso = mort;
    }

}
