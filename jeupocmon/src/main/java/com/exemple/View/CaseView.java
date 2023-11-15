package main.java.com.exemple.View;

import main.java.com.exemple.Model.Case;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CaseView extends JPanel {

    private Case c;
    private Color color;

    private BufferedImage test;



    public CaseView(Case c) {
        try {
            if(c.estMur()){
                test = ImageIO.read(getClass().getResourceAsStream("/wall.png"));
            }else if (c.estTresor()){
                test = ImageIO.read(getClass().getResourceAsStream("/tresor.png"));
            }else if(c.estTeleporteur()){
                test = ImageIO.read(getClass().getResourceAsStream("/porte.png"));
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dessine l'image au lieu de remplir un rectangle
        if (test != null) {
            g.drawImage(test, 0, 0, getWidth(), getHeight(), this);
        }
//        g.setColor(color);
//        g.fillRect(0, 0, getWidth(), getHeight());
    }

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

    public void setColor(Color color) {
        this.color = color;
    }

}
