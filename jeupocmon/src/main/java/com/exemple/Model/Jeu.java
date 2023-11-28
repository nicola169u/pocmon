package main.java.com.exemple.Model;

import main.java.com.exemple.Controller.Observateur;
import main.java.com.exemple.View.JeuView;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe principale du projet, qui gère tout ce que doit connaître le jeu
 */
public class Jeu {

    /**
     * Liste des observateurs, utile si on voudra implémenter le DP Observer
     */
    private List<Observateur> observateurs;
    /**
     * Vue du jeu
     */
    private JeuView jeuView;
    /**
     * Le joueur
     */
    private Joueur joueur;
    /**
     * Le numéro de niveau
     */
    private int niveau;
    /**
     * La difficulte du jeu
     */
    private int difficulte;
    /**
     * Le labyrinthe
     */
    private Labyrinthe lab;
    /**
     * La liste des monstres
     */
    private ArrayList<Monstre> monstres;
    /**
     * La taille du labyrinthe (nombre de case en largeur et hauteur)
     */
    private int sizeLab;
    /**
     * Compte le nombre de pas modulo 15, utile pour le changement de sprite pour le déplacement
     */
    private int compteurPas;


    Thread threadMonstres = new Thread(new Runnable() {
        @Override
        public void run() {
            while(true){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                for(Monstre m : monstres){
                    m.comportement();
                    joueur.attaquer(m);
                }
                jeuView.rafraichirAffichage();
            }
        }
    });


    /**
     * Constructeur de Jeu en fonction du niveau
     * @param lvl le niveau à lancer
     * @param difficulte
     */
    public Jeu(int lvl, int difficulte) {
        this.joueur = new Joueur(1, 1, 30);
        this.observateurs = new ArrayList<>();
        this.monstres = new ArrayList<>();
        this.niveau = lvl;
        this.difficulte = difficulte;
        this.sizeLab = 20;
        this.lab = new Labyrinthe(sizeLab);
        //On créé le plateau
        lab.lire_lab(niveau+"");
        createMonstre(difficulte);
        jeuView = new JeuView(this);
        this.compteurPas = 0;
    }

    /**
     * Fonction permettant d'ajouter un observateur v à la liste d'observateurs, utile si on voudra implémenter le DP Observer
     * @param v l'observateur
     */
    public void ajouterObservateur(Observateur v){
        for(Observateur ob : observateurs) {
            observateurs.add(v);
        }
    }

    /**
     * Procédure qui notifie un changement du jeu aux observateurs, utile si on voudra implémenter le DP Observer
     */
    public void notifierObservateur(){
        for(Observateur ob : observateurs){
            ob.reagir();
        }
    }


    /**
     * Fonction qui permet de lancer le jeu
     */
    public void lancer() {
        jeuView.afficherMessage("Bienvenue sur Pocmon !");
        jeuView.start();
        threadMonstres.start();
    }


    /**
     * Fonction qui permet de boucler (un déplacement du joueur et des monstres par boucle)
     */
    public void boucler(){
        //On verifie si le joueur est sur un teleporteur
        lab.isOnTp(joueur);
        lab.trap(joueur);
        lab.isOnMine(joueur);
        lab.isOnPotionForce(joueur);
        lab.isOnPotionVie(joueur);
        lab.isOnEtoile(joueur);


        this.compteurPas++;
        if(this.compteurPas > 15){
            compteurPas = 0;
        }

        if(lab.aGagne(joueur)) {
            jeuView.afficherMessage("Félicitations, vous avez gagné et vous êtes riche maintenant !");

            if (niveau < 5) {
                //On demande si le joueur veut continuer ou quitter la partie
                if (jeuView.ask("Voulez-vous continuer au prochain niveau ?")) {
                    niveau++;
                    lab.lire_lab(niveau + "");
                    this.monstres.clear();
                    createMonstre(difficulte);
                    //On repositionne le joueur
                    joueur.setPosX(1);
                    joueur.setPosY(1);
                    //On lui redonne ses points de vie
                    joueur.revivre();
                    joueur.resetDegat();
                    if(joueur.isInvulnerable()){
                        joueur.setInvulnerable(false);
                    }
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
            fin("Le joueur est mort..");
            System.exit(0);
        }
    }

    /**
     * Fonctsion qui gère la fin du jeu en fonction du message que l'on souhaiterait faire passer au joueur
     * @param message le message à afficher
     */
    private void fin(String message){
        jeuView.afficherMessage(message);
        jeuView.dispose();
    }


    /**
     * Fonction qui créé des nouveaux monstres et les ajoutent à la liste de monstres
     * @param difficulte
     */
    private void createMonstre(int difficulte){
        switch (difficulte)
        {
            case 1:
                this.monstres.add(new MonstreAleatoire(8, 8, 10, 2));
                break;
            case 2:
                this.monstres.add(new MonstreAleatoire(8, 8, 10, 2));
                this.monstres.add(new MonstreAleatoire(9, 9, 10, 2));
                break;
            case 3:
                this.monstres.add(new MonstreIntelligent(9, 9, 10, 2));
                this.monstres.add(new MonstreAleatoire(8, 8, 10, 2));
                break;
            case 4:
                this.monstres.add(new MonstreIntelligent(9, 9, 10, 2));
                this.monstres.add(new MonstreIntelligent(8, 8, 10, 2));
                break;
            case 5:
                this.monstres.add(new MonstreIntelligent(9, 9, 10, 2));
                this.monstres.add(new MonstreIntelligent(8, 8, 10, 2));
                this.monstres.add(new Fantome(1, 9, 10, 2));
                break;
        }

        for(Monstre m : monstres){
            m.setLabyrinthe(lab);
            m.setJoueurCible(joueur);
        }
        this.joueur.setLabyrinthe(lab);

    }


    /**
     * Getter du joueur
     * @return le joueur
     */
    public Joueur getJoueur() {
        return joueur;
    }

    /**
     * Getter du labyrinthe
     * @return le labyrinthe
     */
    public Labyrinthe getLab() {
        return lab;
    }

    /**
     * Getter de la liste de monstres
     * @return la liste de monstres
     */
    public ArrayList<Monstre> getMonstre() {
        return monstres;
    }

    /**
     * Fonction qui retourne la taille du labyrinthe (nombre de case en largeur et en hauteur)
     * @return la taille du labyrinthe
     */
    public int getSizeLab() {
        return sizeLab;
    }


    /**
     * Getter du numéro de niveau
     * @return le numéro de niveau en cours
     */
    public int getNiveau() {
        return niveau;

    }
}