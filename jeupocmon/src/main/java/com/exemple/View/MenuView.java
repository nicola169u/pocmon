package main.java.com.exemple.View;

import main.java.com.exemple.Controller.MenuController;
import main.java.com.exemple.Model.Jeu;
import main.java.com.exemple.Model.Monstre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Classe représentant la vue du Menu
 */
public class MenuView extends JFrame {
    /**
     * Le niveau choisi
     */
    private int niveau = 1;
    /**
     * La vue du menu des options
     */
    private OptionView optionView;
    /**
     * Le bouton "jouer"
     */
    private JButton play = new JButton("Jouer");
    /**
     * Le bouton "option"
     */
    private JButton settings = new JButton("Option");
    /**
     * Le bouton "quitter"
     */
    private JButton exit = new JButton("Quitter");
    /**
     * Le conteneur des éléments
     */
    private CardLayout layout = new CardLayout();
    /**
     * Le panneau des éléments
     */
    private JPanel panel = new JPanel();
    /**
     * Le panneau du menu
     */
    private JPanel menu = new JPanel();
    /**
     * Le controller du menu
     */
    private MenuController menuController;


    /**
     * Constructeur de MenuView en fonction de la taille et du niveau
     * @param width
     * @param height
     * @param lvl
     */
    public MenuView(int width,int height,int lvl)
    {
        this.niveau = lvl;
        panel.setLayout(layout);
        layout.addLayoutComponent(panel, "Menu");

        menuController = new MenuController(this,niveau);
        addButtons();


        setSize(width, height);

    }

    public void startMenu(){
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Pocmon");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        requestFocus();
    }


    /**
     * Procédure privée qui ajoute les boutons au panneau
     */
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


    /**
     * Getter du bouton "quitter"
     * @return le bouton "quitter"
     */
    public JButton getExit() {
        return exit;
    }

    /**
     * Getter du bouton "jouer"
     * @return le bouton "jouer"
     */
    public JButton getPlay() {
        return play;
    }

    /**
     * Getter le bouton "option"
     * @return le bouton "option"
     */
    public JButton getSettings() {
        return settings;
    }

    /**
     * Procédure qui lance le menu des options
     */
    public void launchOptionView() {
        optionView = new OptionView(300,200);
    }

    /**
     * Setter du niveau
     * @param niveau
     */
    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }
}
