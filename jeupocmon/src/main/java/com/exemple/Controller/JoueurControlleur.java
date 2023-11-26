package main.java.com.exemple.Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.java.com.exemple.Model.Joueur;
import main.java.com.exemple.Tools.Direction;
import main.java.com.exemple.View.JeuView;


/**
 * Classe qui permet de gérer les mouvements du joueur
 */
public class JoueurControlleur extends Observateur implements KeyListener {
    /**
     * Le joueur à controler
     */
    private Joueur joueur;
    /**
     * La vue du jeu
     */
    private JeuView jeuView;

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

        jeuView.getJeu().boucler();

        //jeuView.rafraichirAffichage();
    }

    /**
     * Procédure venant de KeyListener, inutilsée ici
     * @param keyEvent l'événement
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        // Ne rien faire ici
    }

    /**
     * Dans le cas où on voudrait implémenter le DP Observer
     */
    @Override
    public void reagir() {

    }
}
