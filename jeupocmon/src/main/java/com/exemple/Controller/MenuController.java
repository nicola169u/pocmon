package main.java.com.exemple.Controller;

import main.java.com.exemple.Model.Jeu;
import main.java.com.exemple.View.MenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe permettant de controller le menu et de gérer les événements
 */
public class MenuController implements ActionListener {
    /**
     * La vue du Menu
     */
    private MenuView menuView;
    /**
     * Le jeu
     */
    private Jeu jeu;
    /**
     * Le numéro de niveau
     */
    private int niveau;


    /**
     * Constructeur de MenuController avec la vue du Menu et le numero de niveau
     * @param menuView la vue du menu
     * @param lvl le niveau actuel
     */
    public MenuController(MenuView menuView, int lvl) {
        this.menuView = menuView;
        this.niveau = lvl;
    }

    /**
     * Procédure venant de ActionListener, permet de capter les événements du menu
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (source == menuView.getExit()) {
            System.exit(0);
        } else if (source == menuView.getPlay()) {
            menuView.setVisible(false);
            jeu = new Jeu(niveau);
            jeu.lancer();
            //TODO voir la logique
        } else if (source == menuView.getSettings()) {
            menuView.setVisible(false);
            menuView.launchOptionView();
            //TODO Autres options
        }
    }
}