package main.java.com.exemple.Model;

import main.java.com.exemple.View.JeuView;

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
        this.monstre = new MonstreIntelligent(8, 8, 5, 5);
        this.joueur.setLabyrinthe(lab);
        this.monstre.setLabyrinthe(lab);
        this.monstre.setJoueurCible(joueur);
        jeuView = new JeuView(this);
    }

    public void lancer() {
        jeuView.afficherMessage("Bienvenue sur Pocmon !");
        if(jeuView.ask("Voulez-vous démarrer la partie ?")){
            jeuView.start();
        }else{
            jeuView.afficherMessage("Au revoir !");
        }
    }


    public void boucler(){
        monstre.comportement();
        if(joueur.getPosX() == monstre.getPosX() && monstre.getPosY() == joueur.getPosY()){
            joueur.attaquer(monstre);
        }

        if(lab.aGagne(joueur)) {
            jeuView.afficherMessage("Félicitations, vous avez gagné et vous êtes riche maintenant !");

            if (niveau < 2) {
                //On demande si le joueur veut continuer ou quitter la partie
                if (jeuView.ask("Voulez-vous continuer au prochain niveau ?")) {
                    niveau++;
                    lab.lire_lab(niveau + "");
                    //On repositionne le joueur
                    joueur.setPosX(1);
                    joueur.setPosY(1);
                    jeuView.rafraichirAffichage();
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