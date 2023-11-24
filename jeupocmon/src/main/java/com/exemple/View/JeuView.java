package main.java.com.exemple.View;

import main.java.com.exemple.Controller.JoueurControlleur;
import main.java.com.exemple.Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * Classe représentant la vue du Jeu
 */
public class JeuView extends JFrame implements Runnable{
    /**
     * Le jeu concerné
     */
    private Jeu jeu;
    /**
     * La vue du labyrinthe
     */
    private LabyrintheView labView;
    /**
     * La vue du Personnage
     */
    private PersonnageView p;
    /**
     * La liste des vues des monstres
     */
    private ArrayList<PersonnageView> viewmonstres;


    /**
     * Constructeur de JeuView en fonction du jeu
     * @param jeu
     * @throws HeadlessException
     */
    public JeuView(Jeu jeu) throws HeadlessException {
        this.jeu = jeu;

        int sizeLab = jeu.getSizeLab();
        this.viewmonstres = new ArrayList<>();

        labView = new LabyrintheView(sizeLab);
        Joueur joueur = jeu.getJoueur();
        ArrayList<Monstre> monstre = jeu.getMonstre();

         p = new PersonnageView(joueur);
         //m = new PersonnageView(monstre);



        setTitle("Jeu Pocmon - Niveau " + jeu.getNiveau());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(sizeLab, sizeLab));

        for (int i = 0; i < sizeLab; i++) {
            for (int j = 0; j < sizeLab; j++) {
                    Case currentCase = jeu.getLab().getCase(j, i);
                    CaseView panel = new CaseView(currentCase);
                    if(j == joueur.getPosX() && i == joueur.getPosY()){
                        panel.add(p);
                    }
                    for(Monstre m : monstre){
                        if(j == m.getPosX() && i == m.getPosY()){
                            PersonnageView vm = new PersonnageView(m);
                            viewmonstres.add(vm);
                            panel.add(vm);
                        }
                    }

                    labView.setCase(i, j, panel);
                    this.add(panel);
            }
        }



        this.setFocusable(true);
        this.addKeyListener(new JoueurControlleur(joueur, this));
    }


    /**
     * Procédure permettant de lancer l'interface graphique
     */
    public void start(){
        this.pack();
        this.setVisible(true);
    }


    /**
     * Procédure qui met à jour graphiquement le niveau du labyrinthe
     */
    public void majNiveau(){
        setTitle("Jeu Pocmon - Niveau " + jeu.getNiveau());
        rafraichirLab();
        rafraichirAffichage();
    }


    /**
     * Procédure qui met à jour les vues des cases
     */
    public void rafraichirLab(){
        for (int i = 0; i < jeu.getSizeLab(); i++) {
            for (int j = 0; j < jeu.getSizeLab(); j++) {
                this.remove(labView.getCase(i, j));
                Case currentCase = jeu.getLab().getCase(j, i);
                CaseView panel = new CaseView(currentCase);

                labView.setCase(i, j, panel);
                this.add(panel);
            }
        }
    }


    /**
     * Procédure qui met à jour l'affichage des cases, du Personnage et des Monstres
     */
    public void rafraichirAffichage() {
        int sizeLab = jeu.getSizeLab();

        Joueur joueur = jeu.getJoueur();
        ArrayList<Monstre> monstre = jeu.getMonstre();


        for (int i = 0; i < sizeLab; i++) {
            for (int j = 0; j < sizeLab; j++) {
                CaseView caseView = labView.getCase(i,j);
                caseView.remove(p);
                for(PersonnageView pv : viewmonstres){
                    caseView.remove(pv);
                }
                this.remove(caseView);
                for(Monstre m : monstre){
                    if(j == m.getPosX() && i == m.getPosY()){
                        PersonnageView vm = new PersonnageView(m);
                        viewmonstres.add(vm);
                        caseView.add(vm);
                    }else if(j == joueur.getPosX() && i == joueur.getPosY()){
                        caseView.add(p);
                    }
                }
                labView.setCase(i, j, caseView);
                this.add(caseView);


            }
        }
        p.revalidate();
        p.repaint();

        this.revalidate();
        this.repaint();
    }


    /**
     * Getter de jeu
     * @return le jeu
     */
    public Jeu getJeu() {
        return jeu;
    }


    /**
     * Procédure qui affiche un message avec une boîte de dialogue
     * @param message
     */
    public void afficherMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }


    /**
     * Fonction qui retourne la réponse de l'utilisateur à la question message
     * @param message
     * @return true si l'utilisateur a répondu positivement à la question message, false sinon
     */
    public boolean ask(String message){
        int choix = JOptionPane.showOptionDialog(null, message, "Confirmation",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

        return choix == JOptionPane.YES_OPTION;
    }


    /**
     * Procédure qui permet de lancer l'interface graphique en continu
     */
    @Override
    public void run(){
        while(true) {
            this.rafraichirAffichage();

        }
    }
}
