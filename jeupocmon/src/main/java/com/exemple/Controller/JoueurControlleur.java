package main.java.com.exemple.Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.java.com.exemple.Model.Joueur;
import main.java.com.exemple.Tools.Direction;
import main.java.com.exemple.View.JeuView;
import main.java.com.exemple.View.PersonnageView;


/**
 * Classe qui permet de gérer les mouvements du joueur
 */
public class JoueurControlleur implements KeyListener {
    /**
     * Le joueur à controler
     */
    private Joueur joueur;
    /**
     * La vue du jeu
     */
    private JeuView jeuView;
    /**
     * Temps que le joueur doit attendre entre chaque déplacement
     */
    private static long TIME_BEFORE_MOVING = 100;

    /**
     * Constructeur de JoueurController avec le joueur et jeuView
     * @param joueur le joueur
     * @param jeuView la vue du jeu
     */
    public JoueurControlleur(Joueur joueur, JeuView jeuView) {
        super();
        this.joueur = joueur;
        this.jeuView = jeuView;
    }

    /**
     * Procédure venant de KeyListener, inutilisée ici
     * @param keyEvent l'événement
     */
    @Override
    public void keyTyped(KeyEvent keyEvent) {
        // Ne rien faire ici
    }

    /**
     * Procédure venant de KeyListener, permet de détecter les touches tapée et de gérer les mouvements du joueur
     * @param keyEvent l'événement
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();

        if(joueur.peutAvancer()){
            // Gérer les mouvements du joueur avec les touches ZQSD
            if (keyCode == KeyEvent.VK_Z) {
                joueur.avancer(Direction.HAUT);
            } else if (keyCode == KeyEvent.VK_Q) {
                joueur.avancer(Direction.GAUCHE);
            } else if (keyCode == KeyEvent.VK_S) {
                joueur.avancer(Direction.BAS);
            } else if (keyCode == KeyEvent.VK_D) {
                joueur.avancer(Direction.DROITE);
            }

            joueur.setPeutAvancer(false);

            jeuView.getPJoueur().updateCompteur();
            jeuView.getJeu().boucler();

            // Introduire une pause de 200 millisecondes avant de permettre un nouveau déplacement
            new Thread(() -> {
                try {
                    Thread.sleep(TIME_BEFORE_MOVING);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Réactiver les mouvements après la pause
                // Vous pouvez également utiliser un mécanisme plus sophistiqué pour gérer l'état du mouvement
                // afin de ne pas bloquer complètement le joueur pendant la pause.
                joueur.setPeutAvancer(true);
            }).start();
        }

    }


    /**
     * Procédure venant de KeyListener, inutilsée ici
     * @param keyEvent l'événement
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        // Ne rien faire ici
    }
}
