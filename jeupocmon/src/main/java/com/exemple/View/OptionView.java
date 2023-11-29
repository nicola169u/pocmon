package main.java.com.exemple.View;

import main.java.com.exemple.Controller.MenuController;
import main.java.com.exemple.Controller.OptionController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;


/**
 * Classe représentant la vue du menu des options
 */
public class OptionView extends JFrame {
    /**
     * La vue du menu
     */
    private MenuView menuView;
    /**
     * Le controller de la vue des options
     */
    private OptionController optionController;

    /**
     * Le panneau des niveaux
     */
    private JPanel stages = new JPanel();
    /**
     * Le panneau des difficultés
     */
    private JPanel difficulty = new JPanel();
    /**
     * Le groupe de boutons pour les niveaux
     */
    private ButtonGroup groupLevels = new ButtonGroup();
    /**
     * Le groupe de boutons pour le niveau de difficultés
     */
    private ButtonGroup groupDifficulty = new ButtonGroup();
    /**
     * Le bouton "quitter"
     */
    private JButton exit = new JButton("Menu");
    /**
     * Le bouton "sauvegarder"
     */
    private JButton save = new JButton("Sauvegarder");

    /**
     * Constructeur de OptionView en fonction de sa taille
     * @param width
     * @param height
     */
    public OptionView(int width,int height , MenuView m) {
        menuView = m;
        optionController = new OptionController(this,menuView);
        addToPane(groupLevels,stages,getContentPane());
        addToPane(groupDifficulty,difficulty,getContentPane());
        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Option");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        requestFocus();
    }

    /**
     * Procédure privée qui ajoute les boutons au panneau
     */
    private void addToPane(ButtonGroup group, JPanel panel, Container pane){
        pane.setLayout(new BoxLayout(pane,BoxLayout.Y_AXIS));
        addLevels(group,panel,pane);
        exit.addActionListener(optionController);
        save.addActionListener(optionController);
        pane.add(save,BorderLayout.CENTER);
        pane.add(exit,BorderLayout.CENTER);

    }

    /**
     * Procédure privée qui construit les boutons radios pour une option.
     */
    private void addLevels(ButtonGroup buttonGroup, JPanel panel, Container pane)
    {
        Border blackline = BorderFactory.createTitledBorder("Choix du niveau");
        JRadioButtonMenuItem menuItem;
        panel.setSize(pane.getWidth()/100,pane.getHeight()/100);
        for (int i = 1; i < 6 ; i++) {
            menuItem = new JRadioButtonMenuItem(""+i);
            buttonGroup.add(menuItem);
            panel.add(menuItem);
        }
        panel.setBorder(blackline);
        pane.add(panel,BorderLayout.CENTER);
    }

    /**
     * Procédure qui lance la vue du menu en fonction du niveau choisi
     * @param niveau
     * @param difficulte
     */
    public void launchMenuView(int niveau, int difficulte) {

        this.menuView.setNiveau(niveau);
        this.menuView.setDifficulte(difficulte);
        this.menuView.setVisible(true);
    }


    /**
     * Fonction qui retourne le bouton "quitter"
     * @return le bouton "quitter"
     */
    public JButton getExit() {
        return exit;
    }


    /**
     * Fonction qui retourne le texte du bouton selectionné dans le groupe de boutons buttonGroup
     * @param buttonGroup
     * @return le texte du bouton selectionné dans le groupe de boutons buttonGroup
     */
    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return "1";
    }


    /**
     * Fonction qui retourne le groupe de boutons pour le choix des niveaux
     * @return le groupe de boutons pour le choix des niveaux
     */
    public ButtonGroup getGroupLevels() {
        return groupLevels;
    }

    /**
     * Fonction qui retourne le groupe de boutons pour le choix de la difficulté
     * @return le groupe de boutons pour le choix de la difficulté
     */
    public ButtonGroup getGroupDifficulty() {
        return groupDifficulty;
    }


    /**
     * Fonction qui retourne le bouton "sauvegarder"
     * @return
     */
    public JButton getSave() {
        return save;
    }
}
