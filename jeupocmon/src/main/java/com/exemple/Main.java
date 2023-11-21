package main.java.com.exemple;

import main.java.com.exemple.View.MenuView;
import main.java.com.exemple.Model.Jeu;

import javax.swing.*;

/**
 * Classe Main qui lance notre jeu
 */
public class Main {
    /**
     * Procédure main
     * @param args les arguments (vides ici)
     */
    public static void main(String[] args) {
        //Jeu jeu = new Jeu(1);
        //jeu.lancer();
        //Thread JeuViewThread = new Thread(jeu.getJeuView());
        //Thread MonstreThread = new Thread(jeu.getMonstre());
        //MonstreThread.start();
        //JeuViewThread.start();
        MenuView menuView = new MenuView(300,80,1);
        menuView.startMenu();
    }
}
