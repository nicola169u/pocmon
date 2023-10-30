package main.java.com.exemple.Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.java.com.exemple.Model.Joueur;
import main.java.com.exemple.Model.Direction;
import main.java.com.exemple.View.JeuView;

public class JoueurControlleur implements KeyListener {
    private Joueur joueur;
    private JeuView jeuView;

    public JoueurControlleur(Joueur joueur, JeuView jeuView) {
        this.joueur = joueur;
        this.jeuView = jeuView;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        // Ne rien faire ici
    }

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

        jeuView.rafraichirAffichage();
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        // Ne rien faire ici
    }
}
