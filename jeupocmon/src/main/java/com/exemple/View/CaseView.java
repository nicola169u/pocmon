package main.java.com.exemple.View;

import main.java.com.exemple.Model.Case;

import javax.swing.*;
import java.awt.*;

public class CaseView extends JPanel {

    private Case c;
    private Color color;

    public CaseView(Case c) {
        if (c.estMur()) {
            this.color = Color.black;
        } else if (c.estTresor()) {
            this.color = Color.yellow;
        } else {
            this.color = Color.white;
        }
        //this.color = Color.blue;  //A changer en fonction du type de case
        this.c = c;
        setPreferredSize(new Dimension(30, 30));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public void setC(Case c) {
        this.c = c;
        if (c.estMur()) {
            this.color = Color.black;
        } else if (c.estTresor()) {
            this.color = Color.yellow;
        } else {
            this.color = Color.white;
        }
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
