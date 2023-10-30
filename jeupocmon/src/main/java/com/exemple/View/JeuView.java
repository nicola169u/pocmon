package main.java.com.exemple.View;

import main.java.com.exemple.Jeu;
import main.java.com.exemple.Model.Case;
import main.java.com.exemple.Model.Joueur;
import main.java.com.exemple.Model.Labyrinthe;
import main.java.com.exemple.Model.Monstre;

import javax.swing.*;
import java.awt.*;

public class JeuView extends JFrame {
    private Jeu jeu;
    private LabyrintheView labView;
    private JFrame frame;

    public JeuView(Jeu jeu) throws HeadlessException {
        this.jeu = jeu;

        int sizeLab = jeu.getSizeLab();

        labView = new LabyrintheView(sizeLab);

        setTitle("Jeu Pocmon");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(sizeLab, sizeLab));

        for (int i = 0; i < sizeLab; i++) {
            for (int j = 0; j < sizeLab; j++) {
                Case currentCase = jeu.getLab().getCase(j, i);
                CaseView panel = new CaseView(currentCase);
                labView.setCase(i, j, panel);
                this.add(panel);
            }
        }

        Joueur joueur = jeu.getJoueur();
        Monstre monstre = jeu.getMonstre();

        //On affiche le joueur et le monstre
        labView.getCase(joueur.getPosY(), joueur.getPosX()).setColor(Color.blue);
        labView.getCase(monstre.getPosY(), monstre.getPosX()).setColor(Color.green);


    }

    public void start(){
        this.pack();
        this.setVisible(true);
    }

    public void rafraichirAffichage() {
        int sizeLab = jeu.getSizeLab();

        for (int i = 0; i < sizeLab; i++) {
            for (int j = 0; j < sizeLab; j++) {
                Case currentCase = jeu.getLab().getCase(j, i);
                labView.getCase(i, j).setC(currentCase); // Mettez à jour la CaseView correspondante avec la nouvelle case
            }
        }

        Joueur joueur = jeu.getJoueur();
        Monstre monstre = jeu.getMonstre();

        //On affiche le joueur et le monstre
        labView.getCase(joueur.getPosY(), joueur.getPosX()).setColor(Color.blue);
        labView.getCase(monstre.getPosY(), monstre.getPosX()).setColor(Color.green);

        this.revalidate();
        this.repaint();
    }
}
