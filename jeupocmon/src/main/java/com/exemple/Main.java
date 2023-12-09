package main.java.com.exemple;

import main.java.com.exemple.View.MenuView;
import main.java.com.exemple.Model.Jeu;

import javax.swing.*;
import java.io.IOException;

/**
 * Classe Main qui lance notre jeu
 */
public class Main {
    /**
     * Procédure main
     * @param args les arguments (vides ici)
     */
    public static void main(String[] args)  {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        MenuView menuView = new MenuView(750,750,1);
        menuView.startMenu();
    }
}
