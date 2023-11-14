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

                if(j == joueur.getPosY() && i == joueur.getPosX()){
                    this.add(p);
                }else if(j == monstre.getPosY() && i == monstre.getPosX()){
                    this.add(m);
                }else{

                    Case currentCase = jeu.getLab().getCase(j, i);
                    CaseView panel = new CaseView(currentCase);
                    labView.setCase(i, j, panel);
                    this.add(panel);
                }




            }
        }




        //On affiche le joueur et le monstre
//        this.add(p);
//        this.add(m);
//        labView.getCase(joueur.getPosY(), joueur.getPosX()).setColor(Color.blue);
//        labView.getCase(monstre.getPosY(), monstre.getPosX()).setColor(Color.green);

        this.addKeyListener(new JoueurControlleur(joueur, this));
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

        for (int i = 0; i < sizeLab; i++) {
            for (int j = 0; j < sizeLab; j++) {
                Case currentCase = jeu.getLab().getCase(j, i);
                labView.getCase(i, j).setC(currentCase);
            }
        }

        Joueur joueur = jeu.getJoueur();
        Monstre monstre = jeu.getMonstre();

        // On met à jour la position des personnages
        this.remove(p);
        this.remove(m);
        this.add(p, joueur.getPosY() * sizeLab + joueur.getPosX());
        this.add(m, monstre.getPosY() * sizeLab + monstre.getPosX());


        //On affiche le joueur et le monstre
//        if(joueur.isMort()){
//            labView.getCase(joueur.getPosY(), joueur.getPosX()).setColor(Color.red);
//        }else{
//            labView.getCase(joueur.getPosY(), joueur.getPosX()).setColor(Color.blue);
//
//        }
//
//        if(monstre.isMort()){
//            labView.getCase(monstre.getPosY(), monstre.getPosX()).setColor(Color.magenta);
//
//        }else{
//            labView.getCase(monstre.getPosY(), monstre.getPosX()).setColor(Color.green);
//
//        }


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
