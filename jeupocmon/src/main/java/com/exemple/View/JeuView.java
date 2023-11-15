package main.java.com.exemple.View;

import main.java.com.exemple.Controller.JoueurControlleur;
import main.java.com.exemple.Model.*;

import javax.swing.*;
import java.awt.*;

public class JeuView extends JFrame implements Runnable{
    private Jeu jeu;
    private LabyrintheView labView;
    private PersonnageView p;
    private PersonnageView m;

    public JeuView(Jeu jeu) throws HeadlessException {
        this.jeu = jeu;

        int sizeLab = jeu.getSizeLab();

        labView = new LabyrintheView(sizeLab);
        Joueur joueur = jeu.getJoueur();
        Monstre monstre = jeu.getMonstre();

         p = new PersonnageView(joueur);
         m = new PersonnageView(monstre);



        setTitle("Jeu Pocmon - Niveau " + jeu.getNiveau());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(sizeLab, sizeLab));

        for (int i = 0; i < sizeLab; i++) {
            for (int j = 0; j < sizeLab; j++) {

                    Case currentCase = jeu.getLab().getCase(j, i);
                    CaseView panel = new CaseView(currentCase);
                    if(j == monstre.getPosX() && i == monstre.getPosY()){
                        panel.add(m);
                    }else if(j == joueur.getPosX() && i == joueur.getPosY()){
                        panel.add(p);
                    }
                    labView.setCase(i, j, panel);
                    this.add(panel);







            }
        }



        this.setFocusable(true);
    }

    public void start(){
        this.pack();
        this.setVisible(true);
    }

    public void majNiveau(){
        setTitle("Jeu Pocmon - Niveau " + jeu.getNiveau());
        rafraichirAffichage();
    }

    public void rafraichirAffichage() {
        int sizeLab = jeu.getSizeLab();

        Joueur joueur = jeu.getJoueur();
        Monstre monstre = jeu.getMonstre();


        for (int i = 0; i < sizeLab; i++) {
            for (int j = 0; j < sizeLab; j++) {
                CaseView caseView = labView.getCase(i,j);
                caseView.remove(p);
                caseView.remove(m);
                this.remove(caseView);
                if(joueur.getPosX() == j && joueur.getPosY() == i){
                    caseView.add(p);
                }else if(j == monstre.getPosX() && i == monstre.getPosY()){
                    caseView.add(m);
                }
                labView.setCase(i, j, caseView);
                this.add(caseView);

                //Case currentCase = jeu.getLab().getCase(j, i);

                //labView.getCase(i, j).setC(currentCase);
            }
        }

        this.revalidate();
        this.repaint();
    }

    public Jeu getJeu() {
        return jeu;
    }

    public void afficherMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    public boolean ask(String message){
        int choix = JOptionPane.showOptionDialog(null, message, "Confirmation",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

        return choix == JOptionPane.YES_OPTION;
    }
    @Override
    public void run(){
        while(true) {
            this.rafraichirAffichage();

        }
    }
}
