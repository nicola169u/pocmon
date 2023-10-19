package main.java.com.exemple;

import main.java.com.exemple.Model.Direction;
import main.java.com.exemple.Model.Joueur;
import main.java.com.exemple.Model.Labyrinthe;
import main.java.com.exemple.Model.Monstre;

import java.util.Random;
import java.util.Scanner;

public class Jeu {
    protected Joueur joueur;
    protected int niveau;
    protected Labyrinthe lab;
    protected Monstre monstre;

    public Jeu() {
        this.joueur = new Joueur();
        this.niveau = 1;
        this.lab = new Labyrinthe();
        //On créé le plateau
        lab.lire_lab(niveau+"");
        this.monstre = new Monstre(lab, joueur);
    }

    public void lancer() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenue sur Pocmon !");
        System.out.println("Voulez-vous démarrer la partie (o pour continuer) ?");

        String response = scanner.nextLine();

        boolean jouer = response.equals("o");

        while(jouer){
            lab.printLab(joueur, monstre);
            System.out.println("Quelle est votre prochaine action (zqsd) ? (Entrez quit pour quitter la partie)");
            response = scanner.nextLine();
            Random random = new Random();
            int dirM = random.nextInt(4);
            Direction directionMonstre = monstre.getOrientation(dirM);
            monstre.avancer(directionMonstre, lab);
            if(response.equals("z") || response.equals("q") || response.equals("s") || response.equals("d")){
                joueur.avancer(joueur.choisirDirection(response), lab);
            } else if (response.equals("quit")) {
                jouer = false;
            }else{
                System.out.println("Commande non reconnue !");
            }

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

    }
}