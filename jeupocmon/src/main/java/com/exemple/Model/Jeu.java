package main.java.com.exemple.Model;

import main.java.com.exemple.View.JeuView;
import main.java.com.exemple.View.MenuView;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe principale du projet, qui gère tout ce que doit connaître le jeu
 */
public class Jeu {
    /**
     * Vue du jeu
     */
    private JeuView jeuView;

    /**
     * Vue du menu
     */
    private MenuView menuView;

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

                //On rajoute la gestion de la temporisation (rien à voir avec les monstres mais ça serait dommage de devoir créer un nouveau thread)
                if(!joueur.canSwim()){
                    lab.isOnSwimmingLesson(joueur);
                }
            }
        }
    });


    /**
     * Constructeur de Jeu en fonction du niveau
     * @param menuView la vue du menu
     */
    public Jeu(MenuView menuView) {
        this.joueur = new Joueur(1, 1, 30);
        this.menuView = menuView;
        this.monstres = new ArrayList<>();
        this.niveau = menuView.getNiveau();
        this.difficulte = menuView.getDifficulte();
        this.sizeLab = 20;
        this.lab = new Labyrinthe(sizeLab);
        //On créé le plateau
        lab.lire_lab(niveau+"");
        createMonstre(difficulte);
        jeuView = new JeuView(this,menuView.getSword());
        this.compteurPas = 0;
    }


    /**
     * Fonction qui permet de lancer le jeu
     */
    public void lancer() {
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

        if(!joueur.canSwim()){
            lab.isOnSwimmingLesson(joueur);
        }



        this.compteurPas++;
        if(this.compteurPas > 15){
            compteurPas = 0;
        }

        if(lab.aGagne(joueur)) {
            jeuView.afficherMessage("Félicitations, vous avez gagné et vous êtes riche maintenant !");
            menuView.setScore(menuView.getScore()+10);

            if (niveau < 5) {
                //On demande si le joueur veut continuer ou quitter la partie
                if (jeuView.ask("Voulez-vous continuer au prochain niveau ?")) {
                    niveau++;
                    lab.lire_lab(niveau + "");
                    this.monstres.clear();
                    createMonstre(difficulte);
                    //On remet à 0 le joueur
                    joueur.reset();

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
        menuView.setVisible(true);
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