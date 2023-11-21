package main.java.com.exemple.Controller;
import main.java.com.exemple.View.MenuView;
import main.java.com.exemple.View.OptionView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe qui permet de controller les événements du menu des options
 */
public class OptionController implements ActionListener {
    /**
     * La vue des options
     */
    private OptionView optionView;
    /**
     * Le numéro de niveau
     */
    private int niveau = 1;
    /**
     * La vue du menu
     */
    private MenuView menuView;

    /**
     * Constructeur de OptionController avec la vue des options et la vue du menu
     * @param optionView la vue du menu des options
     * @param menuView la vue du menu
     */
    public OptionController(OptionView optionView,MenuView menuView) {
        this.optionView = optionView;
        this.menuView = menuView;
    }

    /**
     * Procédure héritée de ActionListener
     * Permet de gérér les événements du menu
     * @param actionEvent l'événement
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //TODO logique des options
        Object source = actionEvent.getSource();
        if (source == optionView.getExit()) {
            optionView.setVisible(false);
            optionView.launchMenuView(niveau);
        }else if(source == optionView.getSave()){
             this.niveau = Integer.parseInt(optionView.getSelectedButtonText(optionView.getGroupLevels()));

        }
    }
}