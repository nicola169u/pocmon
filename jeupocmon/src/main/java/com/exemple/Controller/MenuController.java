package main.java.com.exemple.Controller;

import main.java.com.exemple.Model.Jeu;
import main.java.com.exemple.View.MenuView;
import main.java.com.exemple.View.OptionView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController implements ActionListener {
    private MenuView menuView;
    private Jeu jeu;


    public MenuController(MenuView menuView, Jeu jeu) {
        this.menuView = menuView;
        this.jeu = jeu;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (source == menuView.getExit()) {
            System.exit(0);
        } else if (source == menuView.getPlay()) {
            menuView.setVisible(false);
            jeu = new Jeu();
            jeu.lancer();
            //TODO voir la logique
        } else if (source == menuView.getSettings()) {
            menuView.setVisible(false);
            menuView.launchOptionView();
            //TODO Autres options
        }
    }
}
