package main.java.com.exemple.Controller;
import main.java.com.exemple.View.MenuView;
import main.java.com.exemple.View.OptionView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionController implements ActionListener {
    private OptionView optionView;
    private int niveau = 1;
    private MenuView menuView;

    public OptionController(OptionView optionView,MenuView menuView) {
        this.optionView = optionView;
        this.menuView = menuView;
    }

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