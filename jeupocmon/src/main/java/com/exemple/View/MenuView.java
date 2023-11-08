package main.java.com.exemple.View;

import main.java.com.exemple.Controller.MenuController;
import main.java.com.exemple.Model.Jeu;
import main.java.com.exemple.Model.Monstre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView extends JFrame {

    private Jeu jeu;
    private OptionView optionView;
    private JButton play = new JButton("Jouer");
    private JButton settings = new JButton("Option");
    private JButton exit = new JButton("Quitter");
    private CardLayout layout = new CardLayout();

    private JPanel panel = new JPanel();
    private JPanel menu = new JPanel();
    private MenuController menuController;

    public MenuView(int width,int height)
    {
        panel.setLayout(layout);
        layout.addLayoutComponent(panel, "Menu");

        menuController = new MenuController(this, jeu);
        addButtons();

        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Pocmon");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        requestFocus();

    }

    private void addButtons() {

        play.addActionListener(menuController);
        settings.addActionListener(menuController);
        exit.addActionListener(menuController);
        menu.add(play);
        menu.add(settings);
        menu.add(exit);
        panel.add(menu,"Menu");
        add(panel);
        layout.show(panel,"Menu");

    }

    public JButton getExit() {
        return exit;
    }

    public JButton getPlay() {
        return play;
    }

    public JButton getSettings() {
        return settings;
    }

    public void launchOptionView() {
        optionView = new OptionView(300,200);
    }
}
