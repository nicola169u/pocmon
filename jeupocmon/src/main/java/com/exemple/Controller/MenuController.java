package main.java.com.exemple.Controller;

import main.java.com.exemple.Model.Jeu;
import main.java.com.exemple.View.MenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController implements ActionListener {
    private MenuView menuView;
    private Jeu jeu;
    private int niveau;


    public MenuController(MenuView menuView, int lvl) {
        this.menuView = menuView;
        this.niveau = lvl;
    }

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