package main.java.com.exemple.Controller;
import main.java.com.exemple.View.OptionView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionController implements ActionListener {
    private OptionView optionView;

    public OptionController(OptionView optionView) {
        this.optionView = optionView;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //TODO logique des options
        Object source = actionEvent.getSource();
        if (source == optionView.getExit()) {
            optionView.setVisible(false);
            optionView.launchMenuView();
        }
    }
}
