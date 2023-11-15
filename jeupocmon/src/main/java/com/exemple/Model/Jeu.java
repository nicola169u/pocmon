package main.java.com.exemple.Model;

import main.java.com.exemple.Controller.Observateur;
import main.java.com.exemple.Model.*;
import main.java.com.exemple.View.JeuView;

import java.util.ArrayList;
import java.util.List;
import main.java.com.exemple.View.JeuView;


public class Jeu {

    private List<Observateur> observateurs;
    private JeuView jeuView;
    private Joueur joueur;
    private int niveau;
    private Labyrinthe lab;
    private Monstre monstre;
    private int sizeLab;

    public Jeu(int lvl) {
        this.joueur = new Joueur(1, 1, 10);
        this.observateurs = new ArrayList<>();
        this.niveau = lvl;
        this.sizeLab = 20;
        this.lab = new Labyrinthe(sizeLab);
        //On créé le plateau
        lab.lire_lab(niveau+"");
        createMonstre();
        jeuView = new JeuView(this);
    }

    public void ajouterObservateur(Observateur v){
        for(Observateur ob : observateurs) {
            observateurs.add(v);
        }
    }

    public void notifierObservateur(){
        for(Observateur ob : observateurs){
            ob.reagir();
        }
    }

    public void lancer() {
        jeuView.afficherMessage("Bienvenue sur Pocmon !");
        jeuView.start();
    }


    public void boucler(){
        //On verifie si le joueur est sur un teleporteur
        lab.isOnTp(joueur);

        monstre.comportement();
        joueur.attaquer(monstre);

        if(lab.aGagne(joueur)) {
            jeuView.afficherMessage("Félicitations, vous avez gagné et vous êtes riche maintenant !");

            if (niveau < 2) {
                //On demande si le joueur veut continuer ou quitter la partie
                if (jeuView.ask("Voulez-vous continuer au prochain niveau ?")) {
                    niveau++;
                    lab.lire_lab(niveau + "");
                    createMonstre();
                    //On repositionne le joueur
                    joueur.setPosX(1);
                    joueur.setPosY(1);
                    jeuView.majNiveau();  //Appelle rafraichirAffichage() et maj le numero de niveau
                } else {
                    fin("Au revoir !");
                }
            } else {
                fin("Vous avez terminé tous les niveaux !\nAu revoir !");
                System.exit(0);
            }
        }


        //On met à jour l'interface graphique
        jeuView.rafraichirAffichage();
        if(joueur.isMort()){
            fin("Le joueur s'est fait piétiner par le monstre !!");
            System.exit(0);
        }
    }

    private void fin(String message){
        jeuView.afficherMessage(message);
        jeuView.dispose();
    }

    private void createMonstre(){
        this.monstre = new MonstreAleatoire(8, 8, 5, 5);
        this.joueur.setLabyrinthe(lab);
        this.monstre.setLabyrinthe(lab);
        this.monstre.setJoueurCible(joueur);
    }


    public Joueur getJoueur() {
        return joueur;
    }

    public Labyrinthe getLab() {
        return lab;
    }

    public Monstre getMonstre() {
        return monstre;
    }

    public int getSizeLab() {
        return sizeLab;
    }

    public JeuView getJeuView() {
        return jeuView;
    }

    public int getNiveau() {
        return niveau;

    }
}