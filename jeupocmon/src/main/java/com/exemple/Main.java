package main.java.com.exemple;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Jeu jeu = new Jeu();
        jeu.lancer();
        Thread JeuViewThread = new Thread(jeu.getJeuView());
        Thread MonstreThread = new Thread(jeu.getMonstre());
        MonstreThread.start();
        JeuViewThread.start();
    }
}
