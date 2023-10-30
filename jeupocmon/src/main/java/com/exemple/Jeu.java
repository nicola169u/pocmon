package main.java.com.exemple;

import main.java.com.exemple.Model.*;
import main.java.com.exemple.View.CaseView;
import main.java.com.exemple.View.JeuView;
import main.java.com.exemple.View.LabyrintheView;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Jeu {
    private JeuView jeuView;
    private Joueur joueur;
    private int niveau;
    private Labyrinthe lab;

    private Monstre monstre;
    private int sizeLab;


    public Jeu() {
        this.joueur = new Joueur(1, 1, 10);
        this.niveau = 1;
        this.sizeLab = 10;
        this.lab = new Labyrinthe(sizeLab);
        //On créé le plateau
        lab.lire_lab(niveau+"");
        this.monstre = new MonstreAleatoire(8, 8, 5);
        this.joueur.setLabyrinthe(lab);
        this.monstre.setLabyrinthe(lab);
        this.monstre.setJoueurCible(joueur);
        jeuView = new JeuView(this);
    }

    public void lancer() {
        jeuView.start();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenue sur Pocmon !");
        System.out.println("Voulez-vous démarrer la partie (o pour continuer) ?");

        String response = scanner.nextLine();

        boolean jouer = response.equals("o");

        while(jouer){
            lab.printLab(joueur, monstre);
            System.out.println("Quelle est votre prochaine action (zqsd) ? (Entrez quit pour quitter la partie)");
            response = scanner.nextLine();
            monstre.comportement();
//            Random random = new Random();
//            int dirM = random.nextInt(4);
//            Direction directionMonstre = monstre.getOrientation(dirM);
//            monstre.avancer(directionMonstre, lab);
            if(response.equals("z") || response.equals("q") || response.equals("s") || response.equals("d")){
                joueur.avancer(joueur.choisirDirection(response), lab);
            } else if (response.equals("quit")) {
                jouer = false;
            }else{
                System.out.println("Commande non reconnue !");
            }

            //On met à jour l'interface graphique
            jeuView.rafraichirAffichage();

            if(lab.aGagne(joueur)){
                lab.printLab(joueur, monstre);
                System.out.println("Félicitations, vous avez gagné et vous êtes riche maintenant !");

                if(niveau < 2){
                    //On demande si le joueur veut continuer ou quitter la partie
                    System.out.println("Voulez-vous quitter le jeu (quit) ou continuer au prochain niveau (next) ?");
                    response = scanner.nextLine();
                    if(response.equals("next")){
                        niveau++;
                        lab.lire_lab(niveau + "");
                        //On repositionne le joueur
                        joueur.setPosX(1);
                        joueur.setPosY(1);
                        jeuView.rafraichirAffichage();
                    }else{
                        jouer = false;
                    }
                }else{
                    System.out.println("Vous avez terminé tous les niveaux !");
                    jouer = false;
                }

            }
        }

        System.out.println("Au revoir !");
        jeuView.dispose();

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
}